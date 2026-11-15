import Enums.HangmanArt;
import Enums.MenuAction;
import Enums.SettingsAction;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

// добавить очистку консоли!!!!!!!!!
public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);

    //изначально было два метода, но в итоге решил их заприватить
    //и сделать метод, который их вызывает.
    //для остальных аналогично
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
    //валидацию ввода!
    private MenuAction readMenuAction() {
        int choose;
        do {
            choose = scanner.nextInt();
        } while (choose <= 0 || choose > 3);
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
        System.out.print("Ваш выбор: ");

    }
    //валидацию ввода!
    private SettingsAction readSettingsAction() {
        int choose;
        do {
            choose = scanner.nextInt();
        } while (choose <= 0 || choose > 3);
        return switch (choose) {
            case 1 -> SettingsAction.SET_REVEALED_LETTERS;
            case 2 -> SettingsAction.SET_MAX_ATTEMPTS;
            case 3 -> SettingsAction.BACK_TO_MENU;
            default -> throw new IllegalStateException("Unexpected value: " + choose);
        };
    }

    public int showSetRevealedLettersMenu(int currentValue) {
        printSetRevealedLettersMenu(currentValue);
        return readSetRevealedLettersMenu();
    }
    private void printSetRevealedLettersMenu(int currentValue) {
        clearConsole();
        System.out.println("Введи новое колличество открытых букв");
        System.out.println("текущее значение: " + currentValue);
        System.out.println("разрешены значения от 0 до 2");
        System.out.print("Ввод: ");
    }
    //валидацию ввода!
    private int readSetRevealedLettersMenu() {
        return scanner.nextInt();
    }

    public int showSetMaxAttemptMenu(int currentValue) {
        printSetMaxAttemptMenu(currentValue);
        return readSetMaxAttemptMenu();
    }
    private void printSetMaxAttemptMenu(int currentValue) {
        clearConsole();
        System.out.println("Введи новое колличество попыток");
        System.out.println("текущее значение: " + currentValue);
        System.out.println("разрешены значения 6, 8, 10");
        System.out.print("Ввод: ");
    }
    //валидацию ввода!
    private int readSetMaxAttemptMenu() {
        return scanner.nextInt();
    }

    public void printGameMenu(HangmanArt hangmanArt,  List<String> guessLetters,  Set<String> enterLetters){
        System.out.println(hangmanArt.getArt());
        System.out.println(guessLetters);
        System.out.println("уже использованные буквы: " + enterLetters.toString());
        System.out.print("Ввод: ");
    }
    public void printGameMenu(HangmanArt hangmanArt,  List<String> guessLetters,  Set<String> enterLetters, String message){
        System.out.println(hangmanArt.getArt());
        System.out.println(guessLetters);
        System.out.println("уже использованные буквы: " + enterLetters.toString());
        System.out.println("❌" + message);
        System.out.print("Ввод: ");
    }
    public String readGameMenu(){
        char[] charArray = scanner.next().trim().toLowerCase().toCharArray();
        return charArray[0] + "";
    }

    public void printWinMessage(){
        System.out.println("поздравляю, ты смог победить!");
    }
    public void printLossMessage(String word) {
        System.out.println("к сожалению ты проиграл(");
        System.out.println("я загадал слово: "+ word);
    }

    private void clearConsole() {
        //имитация очистки консоли
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

}