package hangman.game;

import java.util.ArrayList;
import java.util.List;

public class SecretWord {
    private static final char MASK_SYMBOL = '_';
    private final String word;
    private final List<Character> mask;

    public SecretWord(String word) {
        this.word = word;
        mask = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            mask.add(MASK_SYMBOL);
        }
    }

    public boolean isContainsLetter(char letter) {
        return word.indexOf(letter) != -1;
    }

    public void openLetterInMask(char letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                mask.set(i, letter);
            }
        }
    }

    public String getWord() {
        return word;
    }

    public List<Character> getMask() {
        return mask;
    }

    public boolean isMaskFullyRevealed() {
        return !mask.contains(MASK_SYMBOL);
    }

    public List<Character> getNonRepeatingLetters() {
        List<Character> nonRepeatingLetters = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            int occurrenceCount = 0;
            char letter = word.charAt(i);

            for (int j = 0; j < word.length(); j++) {
                if(letter == word.charAt(j)){
                    occurrenceCount ++;
                }
            }

            if(occurrenceCount  == 1){
                nonRepeatingLetters.add(letter);
            }
        }
        return nonRepeatingLetters;
    }
}
