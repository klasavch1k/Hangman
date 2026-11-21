package hangman.game;
import hangman.ui.ConsoleUI;

import java.util.*;


public class GameSession {
    private final static int COUNT_OF_REVEALED_LETTERS = 2;
    private final static String LETTER_ALREADY_USED = "ты уже называл эту букву(";

    private final ConsoleUI consoleUI;
    private final SecretWord secretWord;
    private final List<Character> usedLetters = new ArrayList<>();
    private int mistakeCount;

    public GameSession(SecretWord secretWord, ConsoleUI consoleUI) {
        this.secretWord = secretWord;
        this.consoleUI = consoleUI;
    }

    public void startSession() {
        openStartLetters();
        while (true) {
            consoleUI.printGameStatus(mistakeCount, secretWord.getMask(), usedLetters);
            if(isWin()){
                consoleUI.printMessage("Ты победил");
                break;
            }
            if(isLoss()){
                consoleUI.printMessage("Ты проиграл(");
                consoleUI.printMessage("Загаданное слово: %s".formatted(secretWord.getWord()));
                break;
            }
            char letter = consoleUI.readLetter();
            while (!isLetterNotUsed(letter)) {
                consoleUI.printGameStatus(mistakeCount, secretWord.getMask(), usedLetters);
                consoleUI.printMessage(LETTER_ALREADY_USED);
                letter = consoleUI.readLetter();
            }
            processGuess(letter);
        }
    }

    private boolean isLetterNotUsed(char letter) {
        for (Character usedLetter : usedLetters) {
            if (usedLetter.equals(letter)) {
                return false;
            }
        }
        return true;
    }
    private void processGuess(char letter) {
        if (secretWord.isContainsLetter(letter)) {
            secretWord.openLetterInMask(letter);
        }else{
            mistakeCount++;
        }
        usedLetters.add(letter);
    }
    private boolean isWin() {
        return secretWord.isMaskFullyRevealed();
    }
    private boolean isLoss(){
        int COUNT_MAX_MISTAKES = 6;
        return mistakeCount == COUNT_MAX_MISTAKES;
    }

    private void openStartLetters() {
        Random random = new Random();
        List<Character> nonRepeatingLetters = secretWord.getNonRepeatingLetters();
        if(nonRepeatingLetters.size() < COUNT_OF_REVEALED_LETTERS){
            throw new IllegalStateException("The word contains fewer than two non-repeating letters");
        }
        for (int i = 0; i < COUNT_OF_REVEALED_LETTERS; i++) {
            int randomIndex = random.nextInt(nonRepeatingLetters.size());
            char letter = nonRepeatingLetters.remove(randomIndex);
            processGuess(letter);
        }
    }
}
