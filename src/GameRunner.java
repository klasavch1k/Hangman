import Enums.MenuAction;
import Enums.SettingsAction;

public class GameRunner {
    private static final WordDictionary wordDictionary = new WordDictionary();
    private static final ConsoleUI consoleUI = new ConsoleUI();
    private static final GameConfig gameConfig = new GameConfig();

    public static void main(String[] args) {
        loop:
        while (true) {
            MenuAction menuAction = consoleUI.showStartMenu();
            switch (menuAction) {
                case START_GAME -> startGame();
                case SETTINGS -> openSettings();
                case EXIT -> {
                    break loop;
                }
            }
        }
    }

    // добавить вывод информации об ошибках!!!!
    private static void startGame() {
        HangmanGame hangmanGame = new HangmanGame(wordDictionary.getRandomWord(),gameConfig,consoleUI);
        hangmanGame.starSession();
    }

    // добавить вывод информации об ошибках!!!!
    public static void openSettings() {
        loop:
        while (true) {
            SettingsAction settingsAction = consoleUI.showSettingsMenu(gameConfig.getRevealedLetters(), gameConfig.getMaxAttempts());
            switch (settingsAction) {
                case SET_REVEALED_LETTERS -> {
                    int newValue;
                    do {
                        newValue = consoleUI.showSetRevealedLettersMenu(gameConfig.getRevealedLetters());
                    } while (!gameConfig.setRevealedLetters(newValue));
                }
                case SET_MAX_ATTEMPTS -> {
                    int newValue;
                    do {
                        newValue = consoleUI.showSetMaxAttemptMenu(gameConfig.getMaxAttempts());
                    } while (!gameConfig.trySetMaxAttempts(newValue));
                }
                case BACK_TO_MENU -> {
                    break loop;
                }
            }
        }
    }
}