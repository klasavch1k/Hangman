package hangman.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class WordDictionary {

    private final List<String> words;
    private final Random random = new Random();

    public WordDictionary(
            Path path,
            WordFilter wordFilter
            ) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            words = reader.lines()
                    .map(String::strip)
                    .map(String::toLowerCase)
                    .filter(word -> !word.isBlank())
                    .filter(wordFilter::isValid)
                    .toList();
        } catch (IOException e) {
            throw new  UncheckedIOException("Failed to load word dictionary",e);
        }
        if (words.isEmpty()) {
            throw new IllegalStateException("Word dictionary is empty");
        }
    }

    public String getRandomWord(){
        int index = random.nextInt(words.size());
        return words.get(index);
    }}
