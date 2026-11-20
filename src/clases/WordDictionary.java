package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/*
 В моей трактовке, это игровой словарь,
 который хранит набор подходящих слов, и предоставляет их игре,
 так что я не считаю что здесь есть нарушение SRP.
 */
public class WordDictionary {

    private final List<String> words;
    private final Random random = new Random();

    public WordDictionary(
            Path path,
            WordFilter wordFilter
            ) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            words = reader.lines()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .filter(word -> !word.isBlank())
                    .filter(word -> word.length()>= wordFilter.minLength())
                    .filter(word -> word.length()<= wordFilter.minLength())
                    .filter(word ->wordFilter.allowLetters().matcher(word).matches())
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
