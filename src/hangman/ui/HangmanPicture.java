package hangman.ui;

import java.util.List;

public class HangmanPicture {
    private static final List<String> PICTURES = List.of(
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

    public String get(int mistakeCount) {
        return PICTURES.get(mistakeCount);
    }
}
