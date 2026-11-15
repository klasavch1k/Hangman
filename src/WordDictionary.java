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

    public WordDictionary() {
        Path path = Path.of("src", "resources", "words.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            words = reader.lines()
                    .map(String::trim)
                    .filter(word -> !word.isBlank())
                    .filter(word -> word.length()>5)
                    .map(String::toLowerCase)
                    .toList();
        } catch (IOException e) {
            throw new  UncheckedIOException("проблемы с загрузкой словаря",e);
        }
        if (words.isEmpty()) {
            throw new IllegalStateException("словарь пуст");
        }
    }

    //неплохо бы добавить валидацию слова перед тем, как его отдать
    public String getRandomWord(){

        if (words.isEmpty()) {
            throw new IllegalStateException("словарь пуст");
        }
        return words.get(random.nextInt( words.size()));
    }}
