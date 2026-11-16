import Enums.HangmanArt;
import Enums.MenuAction;
import Enums.SettingsAction;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);

    public MenuAction showStartMenu() {
        printStartMenu();
        return readMenuAction();
    }
    private void printStartMenu() {
        clearConsole();
        System.out.println("ВИСЕЛИЦА");
        System.out.println();
        System.out.println("МЕНЮ");
        System.out.println("1 - начать играть");
        System.out.println("2 - настройки");
        System.out.println("3 - выйти из игры");
        System.out.print("Ваш выбор: ");
    }
    private MenuAction readMenuAction() {
        int choose = readNumber(1, 3);
        return switch (choose) {
            case 1 -> MenuAction.START_GAME;
            case 2 -> MenuAction.SETTINGS;
            case 3 -> MenuAction.EXIT;
            default -> throw new IllegalStateException("Unexpected value: " + choose);
        };
    }

    public SettingsAction showSettingsMenu(int currentRevealedLetters, int currentMaxAttempts) {
        printSettingsMenu(currentRevealedLetters, currentMaxAttempts);
        return readSettingsAction();
    }
    private void printSettingsMenu(int currentRevealedLetters, int currentMaxAttempts) {
        clearConsole();
        System.out.println("НАСТРОЙКИ");
        System.out.println("        ------      ");
        System.out.println("текущие значение:");
        System.out.println("кол-во открытых букв: " + currentRevealedLetters);
        System.out.println("кол-во попыток:             " + currentMaxAttempts);
        System.out.println("        ------      ");
        System.out.println("1 - изменить кол-во открытых букв");
        System.out.println("2 - изменить кол-во попыток");
        System.out.println("3 - вернуться в меню");


    }
    private SettingsAction readSettingsAction() {
        System.out.print("Ваш выбор: ");
        int choose = readNumber(1,3);
        return switch (choose) {
            case 1 -> SettingsAction.SET_REVEALED_LETTERS;
            case 2 -> SettingsAction.SET_MAX_ATTEMPTS;
            case 3 -> SettingsAction.BACK_TO_MENU;
            default -> throw new IllegalStateException("Unexpected value: " + choose);
        };
    }

    public void printSetRevealedLettersMenu(int currentValue) {
        clearConsole();
        System.out.println("Введи новое колличество открытых букв");
        System.out.println("текущее значение: " + currentValue);
        System.out.println("разрешены значения от 0 до 2");
    }
    public int readSetRevealedLettersMenu() {
        System.out.print("Ввод: ");
        return readNumber();
    }

    public void printSetMaxAttemptMenu(int currentValue) {
        clearConsole();
        System.out.println("Введи новое колличество попыток");
        System.out.println("текущее значение: " + currentValue);
        System.out.println("разрешены значения 6, 8, 10");
    }
    public int readSetMaxAttemptMenu() {
        System.out.print("Ввод: ");
        return readNumber();
    }

    public void printGameMenu(HangmanArt hangmanArt, List<String> guessLetters, Set<String> enterLetters) {
        System.out.println(hangmanArt.getArt());
        System.out.println(guessLetters);
        System.out.println("уже использованные буквы: " + enterLetters.toString());
    }
    public String readGameMenu() {
        System.out.print("Ввод: ");
        char[] charArray = scanner.next().trim().toLowerCase().toCharArray();
        return charArray[0] + "";
    }

    public void showWinMessage() {
        System.out.println("поздравляю, ты смог победить!");
        System.out.println("нажми enter для выхода в меню");
        scanner.nextLine();
    }
    public void showLoseMessage(String word) {
        System.out.println("к сожалению ты проиграл(");
        System.out.println("я загадал слово: " + word);
        System.out.println("нажми enter для выхода в меню");
        scanner.nextLine();
    }

    private void clearConsole() {
        //имитация очистки консоли
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
    private int readNumber(int min, int max){
        int choose;
        while (true){
            try {
                choose = scanner.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Введите число!");
                System.out.print("Вaш выбор: ");
                scanner.nextLine();
                continue;
            }
            if(choose >= min && choose <= max){
                break;
            }
            System.out.println("число должно быть от "+ min+ " до "+ max);
            System.out.print("Вaш выбор: ");
        }
        return choose;
    }
    private int readNumber(){
        int choose;
        while (true){
            try {
                String enteredLine = scanner.next();
                choose = Integer.parseInt(enteredLine);
            }catch (NumberFormatException e){
                System.out.println("Введите число!");
                System.out.print("Вaш выбор: ");
                scanner.nextLine();
                continue;
            }
            break;
        }
        return choose;

    }

    public void printMessage(String message){
        System.out.println(message);
    }

}