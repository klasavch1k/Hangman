package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class WordDictionary {
    private final List<String> words;
    private final Random random = new Random();
    private  static final int MIN_COUNT_LETTERS = 5;
    private static final int MAX_COUNT_LETTERS = 15;
    private static final Pattern ONLY_RUSSIAN_LETTERS_PATTERN = Pattern.compile("[а-яё]+");

    public WordDictionary() {
        Path path = Path.of("src", "resources", "words.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            words = reader.lines()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .filter(word -> !word.isBlank())
                    .filter(word -> word.length()>=MIN_COUNT_LETTERS)
                    .filter(word -> word.length()<=MAX_COUNT_LETTERS)
                    .filter(word ->ONLY_RUSSIAN_LETTERS_PATTERN.matcher(word).matches())
                    .toList();
        } catch (IOException e) {
            throw new  UncheckedIOException("проблемы с загрузкой словаря",e);
        }
        if (words.isEmpty()) {
            throw new IllegalStateException("словарь пуст");
        }
    }

    public String getRandomWord(){
        return words.get(random.nextInt( words.size()));
    }}
