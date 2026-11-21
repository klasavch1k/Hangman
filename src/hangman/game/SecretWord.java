package hangman.game;

import java.util.ArrayList;
import java.util.Collections;
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

    public boolean containsLetter(char letter) {
        return word.indexOf(letter) != -1;
    }

    public void openLetterInMask(char letter) {
        if (!containsLetter(letter)) {
            throw new IllegalArgumentException(
                    "Letter is not present in the secret word: " + letter
            );
        }
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
        return Collections.unmodifiableList(mask);
    }

    public boolean isMaskFullyRevealed() {
        return !mask.contains(MASK_SYMBOL);
    }

}
