import Enums.HangmanArt;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HangmanGame {
    private final static String LETTER_ALREADY_USED = "ты уже называл эту букву(";
    private final String word;
    private final GameConfig gameConfig;
    private final ConsoleUI consoleUI;
    private final Set<String> enterLetters = new HashSet<>();
    private final List<String> guessLetters;
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
                consoleUI.showWinMessage();
                break;
            }
            if(isLoss()){
                consoleUI.showLoseMessage(word);
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
    private void openStartLetters(int count) {
        if(count == 0){
            return;
        }
        if (count < 0 || count > 2) {
            throw new IllegalArgumentException("число должно быть между 0 и 2");
        }
        Random random = new Random();
        Map<String, Long> countLetters = Arrays.stream(word.split(""))
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                ));
        List<String> singleLetters = new ArrayList<>();
        List<String> doubleLetters = new ArrayList<>();

        for (Map.Entry<String, Long> entry : countLetters.entrySet()) {
            if (entry.getValue() == 1) {
                singleLetters.add(entry.getKey());
            } else if (entry.getValue() == 2) {
                doubleLetters.add(entry.getKey());
            }
        }
        if (count == 1) {
            if (!singleLetters.isEmpty()) {
                String letter = singleLetters.get(random.nextInt(singleLetters.size()));
                checkLetterInWord(letter);
            }
            return;
        }

        boolean hasDouble = !doubleLetters.isEmpty();
        boolean hasTwoSingles = singleLetters.size() >= 2;

        if (hasDouble && !hasTwoSingles) {
            String letter = doubleLetters.get(random.nextInt(doubleLetters.size()));
            checkLetterInWord(letter);

        } else if (!hasDouble && hasTwoSingles) {
            Collections.shuffle(singleLetters, random);
            checkLetterInWord(singleLetters.get(0));
            checkLetterInWord(singleLetters.get(1));

        } else if (hasDouble && hasTwoSingles) {
            if (random.nextBoolean()) {
                String letter = doubleLetters.get(random.nextInt(doubleLetters.size()));
                checkLetterInWord(letter);
            } else {
                Collections.shuffle(singleLetters, random);
                checkLetterInWord(singleLetters.get(0));
                checkLetterInWord(singleLetters.get(1));
            }

        } else {
            if (singleLetters.size() == 1) {
                checkLetterInWord(singleLetters.get(0));
            }
        }
    }
}
