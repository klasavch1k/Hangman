import Enums.HangmanArt;

import java.util.*;

public class HangmanGame {
    private final static String LETTER_ALREADY_USED = "ты уже называл эту букву(";
    private final String word;
    private final GameConfig gameConfig;
    private final ConsoleUI consoleUI;
    private Set<String> enterLetters = new HashSet<>();
    private List<String> guessLetters;
    private int countFailAttempts;

    public HangmanGame(String word, GameConfig gameConfig, ConsoleUI consoleUI) {
        this.word = word;
        this.gameConfig = new GameConfig(gameConfig);
        this.consoleUI = consoleUI;
        guessLetters = new ArrayList<>();
        for (int i = 0; i<word.length(); i++){
            guessLetters.add("_");
        }
    }

    public void starSession() {
        openStartLetters(gameConfig.getRevealedLetters());
        while (true) {
            // эти два ифа просто ужас
            consoleUI.printGameMenu(stageCalculation(), guessLetters, enterLetters);
            if(isWin()){
                consoleUI.printWinMessage();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            if(isLoss()){
                consoleUI.printLoseMessage(word);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            String enterLetter = consoleUI.readGameMenu();
            while (!isLetterAlreadyUsed(enterLetter)) {
                consoleUI.printGameMenu(stageCalculation(), guessLetters, enterLetters);
                consoleUI.printMessage(LETTER_ALREADY_USED);
                enterLetter = consoleUI.readGameMenu();
            }
            checkLetterInWord(enterLetter);
        }
    }

    private HangmanArt stageCalculation() {
        HangmanArt[] values = HangmanArt.values();
        if (gameConfig.getMaxAttempts() == 10) {
            return values[countFailAttempts];
        }
        return values[countFailAttempts + 2];
    }
    private boolean isLetterAlreadyUsed(String currentLetter) {
        for (String enteredLetter : enterLetters) {
            if (enteredLetter.equals(currentLetter)) {
                return false;
            }
        }
        return true;
    }
    private void checkLetterInWord(String letter) {
        boolean isMistake = true;
        for (int i = 0; i < word.length(); i++) {
            if (letter.charAt(0) == word.charAt(i)) {
                guessLetters.set(i, letter);
                isMistake = false;
            }
        }
        if(isMistake){
            countFailAttempts++;
        }
        enterLetters.add(letter);
    }
    private boolean isWin() {
        HashSet<String> hidden = new HashSet<>(Arrays.asList(word.split("")));
        HashSet<String> guesses = new HashSet<>(guessLetters);
        return hidden.equals(guesses);
    }
    private boolean isLoss(){
        return countFailAttempts == gameConfig.getMaxAttempts();
    }

// не реализован
    private void openStartLetters(int count) {
        //логика
        // можно открыть две одиночны если count = 2
        //можно открыть одну двойную если count = 2
        // можно открыть одну одиночную если count =1
    }
}
