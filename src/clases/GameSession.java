package clases;
import java.util.*;


public class GameSession {
    private final int COUNT_OF_REVEALED_LETTERS = 2;
    private final static String LETTER_ALREADY_USED = "ты уже называл эту букву(";

    private final ConsoleUI consoleUI;
    private final SecretWord secretWord;
    private final List<Character> enteredLetters = new ArrayList<>();
    private int countMistakes;

    public GameSession(SecretWord secretWord, ConsoleUI consoleUI) {
        this.secretWord = secretWord;
        this.consoleUI = consoleUI;
    }

    public void startSession() {
        openStartLetters();
        while (true) {
            consoleUI.printGameStatus(countMistakes, secretWord.getMask(), enteredLetters);
            if(isWin()){
                consoleUI.printMessage("Ты победил");
                break;
            }
            if(isLoss()){
                consoleUI.printMessage("Ты проиграл(");
                consoleUI.printMessage("Загаданное слово: %s".formatted(secretWord.getWord()));
                break;
            }
            char enteredLetter = consoleUI.readLetter();
            while (!isLetterNotUsed(enteredLetter)) {
                consoleUI.printGameStatus(countMistakes, secretWord.getMask(), enteredLetters);
                consoleUI.printMessage(LETTER_ALREADY_USED);
                enteredLetter = consoleUI.readLetter();
            }
            processGuess(enteredLetter);
        }
    }

    private boolean isLetterNotUsed(char currentLetter) {
        for (Character enteredLetter : enteredLetters) {
            if (enteredLetter.equals(currentLetter)) {
                return false;
            }
        }
        return true;
    }
    private void processGuess(char letter) {
        boolean isMistake = true;
            if (secretWord.isContainsLetter(letter)) {
                secretWord.openLetterInMask(letter);
                isMistake = false;
            }
        if(isMistake){
            countMistakes++;
        }
        enteredLetters.add(letter);
    }
    private boolean isWin() {
        return secretWord.isMaskFullyRevealed();
    }
    private boolean isLoss(){
        int COUNT_MAX_MISTAKES = 6;
        return countMistakes == COUNT_MAX_MISTAKES;
    }

    //TODO: реализовать метод
    private void openStartLetters() {
    }
}
