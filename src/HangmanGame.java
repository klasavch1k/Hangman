import Enums.HangmanArt;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HangmanGame {
    private final static String LETTER_ALREADY_USED = "ты уже называл эту букву(";
    private String word;
    private final GameConfig gameConfig;
    private ConsoleUI consoleUI;
    private final Set<String> enteredLetters = new HashSet<>();
    private final List<String> revealedLetters;
    private int failedAttempts;

    public HangmanGame(String word, GameConfig gameConfig, ConsoleUI consoleUI) {
        this.word = word;
        this.gameConfig = new GameConfig(gameConfig);
        this.consoleUI = consoleUI;
        revealedLetters = new ArrayList<>();
        for (int i = 0; i<word.length(); i++){
            revealedLetters.add("_");
        }
    }

    public void startSession() {
        openStartLetters(gameConfig.getRevealedLetters());
        while (true) {
            consoleUI.printGameMenu(getCurrentStage(), revealedLetters, enteredLetters);
            if(isWin()){
                consoleUI.showWinMessage();
                break;
            }
            if(isLoss()){
                consoleUI.showLoseMessage(word);
                break;
            }
            String enteredLetter = consoleUI.readGameMenu();
            while (!isLetterNotUsed(enteredLetter)) {
                consoleUI.printGameMenu(getCurrentStage(), revealedLetters, enteredLetters);
                consoleUI.printMessage(LETTER_ALREADY_USED);
                enteredLetter = consoleUI.readGameMenu();
            }
            processGuess(enteredLetter);
        }
    }

    private HangmanArt getCurrentStage() {
        HangmanArt[] values = HangmanArt.values();
        if (gameConfig.getMaxAttempts() == 10) {
            return values[failedAttempts];
        }
        return values[failedAttempts + 2];
    }
    private boolean isLetterNotUsed(String currentLetter) {
        for (String enteredLetter : enteredLetters) {
            if (enteredLetter.equals(currentLetter)) {
                return false;
            }
        }
        return true;
    }
    private void processGuess(String letter) {
        boolean isMistake = true;
        for (int i = 0; i < word.length(); i++) {
            if (letter.charAt(0) == word.charAt(i)) {
                revealedLetters.set(i, letter);
                isMistake = false;
            }
        }
        if(isMistake){
            failedAttempts++;
        }
        enteredLetters.add(letter);
    }
    private boolean isWin() {
        if(revealedLetters.contains("_")){
            return false;
        }
        return true;
    }
    private boolean isLoss(){
        return failedAttempts == gameConfig.getMaxAttempts();
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
                processGuess(letter);
            }
            return;
        }

        boolean hasDouble = !doubleLetters.isEmpty();
        boolean hasTwoSingles = singleLetters.size() >= 2;

        if (hasDouble && !hasTwoSingles) {
            String letter = doubleLetters.get(random.nextInt(doubleLetters.size()));
            processGuess(letter);

        } else if (!hasDouble && hasTwoSingles) {
            Collections.shuffle(singleLetters, random);
            processGuess(singleLetters.get(0));
            processGuess(singleLetters.get(1));

        } else if (hasDouble && hasTwoSingles) {
            if (random.nextBoolean()) {
                String letter = doubleLetters.get(random.nextInt(doubleLetters.size()));
                processGuess(letter);
            } else {
                Collections.shuffle(singleLetters, random);
                processGuess(singleLetters.get(0));
                processGuess(singleLetters.get(1));
            }

        } else {
            if (singleLetters.size() == 1) {
                processGuess(singleLetters.get(0));
            }
        }
    }
}
