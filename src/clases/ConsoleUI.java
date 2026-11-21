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
        System.out.print(ENTER_MESSAGE);
    }

    public void printGameStatus(int countMistakes, List<Character> wordMask, List<Character> enterLetters) {
        System.out.println(hangmanPicture.get(countMistakes));
        System.out.println(wordMask);
        System.out.println("уже использованные буквы: " + enterLetters.toString());
    }

    public void printError(String error){
        System.out.println(error);
    }
    public void printMessage(String message){
        System.out.println(message);
    }

    public int readNumber(){
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
            break;
        }
        return choice;
    }
    public char readLetter(){
        String enterString;
        while (true){
            System.out.print("Ввод: ");
            enterString = scanner.nextLine();
            if(SINGLE_RUSSIAN_LETTER_PATTERN.matcher(enterString).matches()){
                break;
            }
            System.out.println("нужно ввести одну русскую букву!");
        }
        return enterString.toLowerCase().charAt(0);
    }



}