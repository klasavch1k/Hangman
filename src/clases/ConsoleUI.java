package clases;

import enums.HangmanArt;
import enums.MenuAction;
import enums.SettingsAction;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);
    private static final Pattern SINGLE_RUSSIAN_LETTER_PATTERN = Pattern.compile("[А-ЯёЁа-я]");

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
        int choice = readNumber(1, 3);
        return switch (choice) {
            case 1 -> MenuAction.START_GAME;
            case 2 -> MenuAction.SETTINGS;
            case 3 -> MenuAction.EXIT;
            default -> throw new IllegalStateException("Unexpected value: " + choice);
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
        int choice = readNumber(1,3);
        return switch (choice) {
            case 1 -> SettingsAction.SET_REVEALED_LETTERS;
            case 2 -> SettingsAction.SET_MAX_ATTEMPTS;
            case 3 -> SettingsAction.BACK_TO_MENU;
            default -> throw new IllegalStateException("Unexpected value: " + choice);
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
        return readLetter();
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
        int choice;
        while (true){
            try {
                String enteredLine = scanner.nextLine();
                choice = Integer.parseInt(enteredLine);
            }catch (NumberFormatException e){
                System.out.println("Введите число!");
                System.out.print("Вaш выбор: ");
                continue;
            }
            if(choice >= min && choice <= max){
                break;
            }
            System.out.println("число должно быть от "+ min+ " до "+ max);
            System.out.print("Вaш выбор: ");
        }
        return choice;
    }
    private int readNumber(){
        return readNumber(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private String readLetter(){
        String enterString;
        while (true){
            enterString = scanner.nextLine();
            if(SINGLE_RUSSIAN_LETTER_PATTERN.matcher(enterString).matches()){
                break;
            }
            System.out.println("нужно ввести одну русскую букву!");
            System.out.print("Ввод: ");
        }
        return enterString.toLowerCase();
    }

    public void printMessage(String message){
        System.out.println(message);
    }

}