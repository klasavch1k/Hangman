package clases;

import enums.MenuAction;
import enums.SettingsAction;

public class GameRunner {
    private  final WordDictionary wordDictionary = new WordDictionary();
    private  final ConsoleUI consoleUI = new ConsoleUI();
    private static final String INVALID_VALUE = "недопустимое значение!";

    public static void main(String[] args) {
        GameRunner gameRunner = new GameRunner();
        ConsoleUI consoleUI = gameRunner.getConsoleUI();
        loop:
        while (true) {
            MenuAction menuAction = consoleUI.showStartMenu();
            switch (menuAction) {
                case START_GAME -> gameRunner.startGame();
                case EXIT -> {
                    break loop;
                }
            }
        }
    }
    public void startGame() {
        SecretWord secretWord = new SecretWord(wordDictionary.getRandomWord());
        HangmanGame hangmanGame = new HangmanGame(secretWord, consoleUI);
        hangmanGame.startSession();
    }

    public ConsoleUI getConsoleUI() {
        return consoleUI;
    }

}