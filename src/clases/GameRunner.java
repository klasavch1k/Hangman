package clases;

import java.nio.file.Path;
import java.util.regex.Pattern;

public class GameRunner {
    private static final int MIN_COUNT_LETTERS = 5;
    private static final int MAX_COUNT_LETTERS = 15;
    private static final Pattern ONLY_RUSSIAN_LETTERS_PATTERN = Pattern.compile("[а-яё]+");
    private static final ConsoleUI consoleUI = new ConsoleUI();
    private static final Path path = Path.of("src", "resources", "words.txt");

    private static boolean running = true;

    public static void main(String[] args) {

        Menu menu = new Menu("ВИСЕЛИЦА", consoleUI);
        menu.addOption("начать игру", GameRunner::startGame);
        menu.addOption("выйти", GameRunner::sayGoodbye);

        while (running) {
            menu.show();
            menu.select();
        }
    }

    public static void sayGoodbye() {
        consoleUI.printMessage("До встречи!");
        running = false;
    }

    public static void startGame() {
        WordFilter wordFilter = new WordFilter(
                MIN_COUNT_LETTERS,
                MAX_COUNT_LETTERS,
                ONLY_RUSSIAN_LETTERS_PATTERN);
        WordDictionary wordDictionary = new WordDictionary(path, wordFilter);

        SecretWord secretWord = new SecretWord(wordDictionary.getRandomWord());
        GameSession gameSession = new GameSession(secretWord, consoleUI);
        gameSession.startSession();
    }
}