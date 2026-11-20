package clases;

import java.util.regex.Pattern;

public record WordFilter(
        int minLength,
        int maxLength,
        Pattern allowLetters) {
}
