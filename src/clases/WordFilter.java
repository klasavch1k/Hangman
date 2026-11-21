package clases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordFilter{
        private final int minLength;
        private final int maxLength;
        private final Pattern allowedLetters;

        public WordFilter(int minLength, int maxLength, Pattern allowedLetters) {
                if(minLength < 1 || minLength > maxLength){
                        throw new IllegalArgumentException("Invalid word length range");
                }
                this.minLength = minLength;
                this.maxLength = maxLength;
                this.allowedLetters = allowedLetters;
        }

        public boolean isValid(String word){
                if (word.length() < minLength || word.length() > maxLength){
                        return false;
                }
                Matcher matcher = allowedLetters.matcher(word);
                return matcher.matches();
        }
}
