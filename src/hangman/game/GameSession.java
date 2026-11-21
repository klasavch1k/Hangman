package hangman.game;
import hangman.ui.ConsoleUI;

import java.util.*;


public class GameSession {
    private static final int INITIAL_REVEALED_LETTERS = 2;
    private static final int COUNT_MAX_MISTAKES = 6;
    private static final String LETTER_ALREADY_USED = "ты уже называл эту букву(";

    private final ConsoleUI consoleUI;
    private final SecretWord secretWord;
    private final List<Character> usedLetters = new ArrayList<>();
    private int mistakeCount;

    /*
    Я намеренно оставил консоль в конструкторе.
    1) Её использует ещё и меню
    2) В ConsoleUI есть поле Scanner, тот содержит Console.in
       для избежания потенциальных ошибок, лучше использовать единый объект.
     */
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
            while (isLetterUsed(letter)) {
                consoleUI.printGameStatus(mistakeCount, secretWord.getMask(), usedLetters);
                consoleUI.printMessage(LETTER_ALREADY_USED);
                letter = consoleUI.readLetter();
            }
            processGuess(letter);
        }
    }

    private boolean isLetterUsed(char letter) {
        return usedLetters.contains(letter);
        }

    private void processGuess(char letter) {
        if (secretWord.containsLetter(letter)) {
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
        return mistakeCount == COUNT_MAX_MISTAKES;
    }

    private void openStartLetters() {
        Random random = new Random();

        Set<Character> uniqueLetters = new HashSet<>();
        for (char letter : secretWord.getWord().toCharArray()) {
            uniqueLetters.add(letter);
        }

        List<Character> letters = new ArrayList<>(uniqueLetters);

        if(letters.size() < INITIAL_REVEALED_LETTERS){
            throw new IllegalStateException("The word contains fewer than two non-repeating letters");
        }
        for (int i = 0; i < INITIAL_REVEALED_LETTERS; i++) {
            int randomIndex = random.nextInt(letters.size());
            char letter = letters.remove(randomIndex);
            processGuess(letter);
        }
    }
}
