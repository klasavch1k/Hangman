package clases;

import enums.SettingsAction;

public class GameRunner {
    private  final WordDictionary wordDictionary = new WordDictionary();
    private  final ConsoleUI consoleUI = new ConsoleUI();
    private  final GameConfig gameConfig = new GameConfig();

    private static final String INVALID_VALUE = "недопустимое значение!";

    public void startGame() {
        HangmanGame hangmanGame = new HangmanGame(wordDictionary.getRandomWord(),gameConfig,consoleUI);
        hangmanGame.startSession();
    }

    public ConsoleUI getConsoleUI() {
        return consoleUI;
    }

    public void openSettings() {
        loop:
        while (true) {
            SettingsAction settingsAction = consoleUI.showSettingsMenu(gameConfig.getRevealedLetters(), gameConfig.getMaxAttempts());
            switch (settingsAction) {
                case SET_REVEALED_LETTERS -> {
                    int newValue;
                    consoleUI.printSetRevealedLettersMenu(gameConfig.getRevealedLetters());
                    newValue = consoleUI.readSetRevealedLettersMenu();
                    while (true){
                    if(gameConfig.trySetRevealedLetters(newValue)){
                        break;
                        }
                        consoleUI.printSetRevealedLettersMenu(gameConfig.getRevealedLetters());
                        consoleUI.printMessage(INVALID_VALUE);
                       newValue=  consoleUI.readSetRevealedLettersMenu();
                    }
                }
                case SET_MAX_ATTEMPTS -> {
                    int newValue;
                    consoleUI.printSetMaxAttemptMenu(gameConfig.getMaxAttempts());
                    newValue = consoleUI.readSetMaxAttemptMenu();
                    while (true){
                        if(gameConfig.trySetMaxAttempts(newValue)){
                            break;
                        }
                        consoleUI.printSetMaxAttemptMenu(gameConfig.getMaxAttempts());
                        consoleUI.printMessage(INVALID_VALUE);
                       newValue= consoleUI.readSetMaxAttemptMenu();
                    }
                }
                case BACK_TO_MENU -> {
                    break loop;
                }
            }
        }

    }
}