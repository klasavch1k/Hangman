package clases;

public class GameConfig {
    private int maxAttempts = 6;
    private int revealedLetters = 2;

    public GameConfig(){}
    public GameConfig(GameConfig gameConfig) {
        this.maxAttempts = gameConfig.maxAttempts;
        this.revealedLetters = gameConfig.revealedLetters;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public boolean trySetMaxAttempts(int maxAttempts) {
        if (maxAttempts == 6 || maxAttempts == 8 || maxAttempts == 10) {
            this.maxAttempts = maxAttempts;
            return true;
        }
        return false;
    }

    public int getRevealedLetters() {
        return revealedLetters;
    }

    public boolean trySetRevealedLetters(int revealedLetters) {
        if (revealedLetters >= 0 && revealedLetters < 3) {
            this.revealedLetters = revealedLetters;
            return true;
        }
        return false;
    }

}
