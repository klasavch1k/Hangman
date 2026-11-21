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
        System.out.println(title);
        System.out.println("---");
        for (int i = 0; i< menuOptions.size(); i++){
            String label = menuOptions.get(i).label();
            System.out.printf("%d %s \n",i+1, label);
        }
        System.out.println();
        System.out.print(ENTER_MESSAGE);
    }

    //TODO: переделать методы игры
    public void printGameMenu(int countMistakes, List<Character> wordMask, List<Character> enterLetters) {
        System.out.println(hangmanPicture.get(countMistakes));
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