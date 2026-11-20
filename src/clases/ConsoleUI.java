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
    public void printStartMenu() {
        clearConsole();
        System.out.println("ВИСЕЛИЦА");
        System.out.println();
        System.out.println("МЕНЮ");
        System.out.println("1 - начать играть");
        System.out.println("2 - выйти из игры");
        System.out.print("Ваш выбор: ");
    }
    public MenuAction readMenuAction() {
        int choice = readNumber(1, 2);
        return switch (choice) {
            case 1 -> MenuAction.START_GAME;
            case 2 -> MenuAction.SETTINGS;
            case 3 -> MenuAction.EXIT;
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
    }

    public void printGameMenu(HangmanArt hangmanArt, List<Character> wordMask, Set<Character> enterLetters) {
        System.out.println(hangmanArt.getArt());
        System.out.println(wordMask);
        System.out.println("уже использованные буквы: " + enterLetters.toString());
    }
    public char readGameMenu() {
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

    public void printMessage(String message){
        System.out.println(message);
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
    private char readLetter(){
        String enterString;
        while (true){
            enterString = scanner.nextLine();
            if(SINGLE_RUSSIAN_LETTER_PATTERN.matcher(enterString).matches()){
                break;
            }
            System.out.println("нужно ввести одну русскую букву!");
            System.out.print("Ввод: ");
        }
        return enterString.toLowerCase().charAt(0);
    }


}