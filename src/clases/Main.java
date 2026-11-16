package clases;

import enums.MenuAction;

public class Main {
    public static void main(String[] args) {
        GameRunner gameRunner = new GameRunner();
        ConsoleUI consoleUI = gameRunner.getConsoleUI();
        loop:
        while (true) {
            MenuAction menuAction = consoleUI.showStartMenu();
            switch (menuAction) {
                case START_GAME -> gameRunner.startGame();
                case SETTINGS -> gameRunner.openSettings();
                case EXIT -> {
                    break loop;
                }
            }
        }
    }
}
