package clases;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleUI {
    private static final String ENTER_MESSAGE = "Ввод: ";
    private static final Pattern SINGLE_RUSSIAN_LETTER_PATTERN = Pattern.compile("[А-ЯёЁа-я]");

    private final HangmanPicture hangmanPicture = new HangmanPicture();
    private final Scanner scanner = new Scanner(System.in);

    public void printMenu(String title, List<MenuOption> menuOptions) {
        System.out.println();
        System.out.println(title);
        System.out.println("---");
        for (int i = 0; i< menuOptions.size(); i++){
            String label = menuOptions.get(i).label();
            System.out.printf("%d %s \n",i+1, label);
        }
        System.out.println();
    }

    public void printGameStatus(int mistakeCount, List<Character> wordMask, List<Character> usedLetters) {
        System.out.println(hangmanPicture.get(mistakeCount));
        System.out.println(wordMask);
        System.out.println("уже использованные буквы: " + usedLetters.toString());
    }

    public void printError(String errorMessage){
        System.out.println(errorMessage);
    }
    public void printMessage(String message){
        System.out.println(message);
    }

    public int readNumber(){
        int choice;
        String enteredLine;
        while (true){
            System.out.print(ENTER_MESSAGE);
            try {
                enteredLine = scanner.nextLine();
                choice = Integer.parseInt(enteredLine);
            }catch (NumberFormatException e){
                System.out.println("Введите число!");
                continue;
            }
            break;
        }
        return choice;
    }
    public char readLetter(){
        String enteredLine;
        while (true){
            System.out.print(ENTER_MESSAGE);
            enteredLine = scanner.nextLine();
            if(SINGLE_RUSSIAN_LETTER_PATTERN.matcher(enteredLine).matches()){
                return enteredLine.toLowerCase().charAt(0);
            }
            System.out.println("нужно ввести одну русскую букву!");
        }
    }



}