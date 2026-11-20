package clases;

import enums.HangmanArt;

import java.util.*;


public class HangmanGame {
    private final int COUNT_OF_REVEALED_LETTERS = 2;
    private final int COUNT_MAX_MISTAKES = 6;
    private final static String LETTER_ALREADY_USED = "ты уже называл эту букву(";

    private ConsoleUI consoleUI;
    private final SecretWord secretWord;
    private final Set<Character> enteredLetters = new HashSet<>();
    private int failedAttempts;

    public HangmanGame(SecretWord secretWord, ConsoleUI consoleUI) {
        this.secretWord = secretWord;
        this.consoleUI = consoleUI;
    }

    public void startSession() {
        openStartLetters();
        while (true) {
            consoleUI.printGameMenu(getCurrentStage(), secretWord.getMask(), enteredLetters);
            if(isWin()){
                consoleUI.showWinMessage();
                break;
            }
            if(isLoss()){
                consoleUI.showLoseMessage(secretWord.getWord());
                break;
            }
            char enteredLetter = consoleUI.readGameMenu();
            while (!isLetterNotUsed(enteredLetter)) {
                consoleUI.printGameMenu(getCurrentStage(), secretWord.getMask(), enteredLetters);
                consoleUI.printMessage(LETTER_ALREADY_USED);
                enteredLetter = consoleUI.readGameMenu();
            }
            processGuess(enteredLetter);
        }
    }

    private HangmanArt getCurrentStage() {
        HangmanArt[] values = HangmanArt.values();
        return values[failedAttempts + 2];
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
            failedAttempts++;
        }
        enteredLetters.add(letter);
    }
    private boolean isWin() {
        return secretWord.isMaskFullyRevealed();
    }
    private boolean isLoss(){
        return failedAttempts == COUNT_MAX_MISTAKES;
    }

    //TODO: реализовать метод
    private void openStartLetters() {
    }
}
