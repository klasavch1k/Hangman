package clases;

import java.util.List;

public class HangmanPicture {
    private static final List<String> pictures = List.of(
            """
                      +------+
                       |     |
                       |
                       |
                       |
                       |
                    =========
                    """,
            """
                      +------+
                      |      |
                      |      0
                      |
                      |
                      |
                    =========
                    """, """
                      +------+
                      |      |
                      |      0
                      |      |
                      |
                      |
                    =========
                    """,
            """
                      +------+
                      |      |
                      |      0
                      |    / |
                      |
                      |
                    =========
                    """,
            """
                      +------+
                      |      |
                      |      0
                      |    / |\\
                      |
                      |
                    =========
                    """,
            """
                      +------+
                      |      |
                      |      0
                      |    / |\\
                      |     /
                      |
                    =========
                    """,
            """
                      +------+
                      |      |
                      |      0
                      |    / |\\
                      |     / \\
                      |
                    =========
                    """
    );

    public String get(int countMistakes) {
        return pictures.get(countMistakes);
    }
}
