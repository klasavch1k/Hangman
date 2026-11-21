package hangman.ui;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static final String ERROR_MESSAGE = "Некорректный пункт меню. Попробуйте снова";

    private final String title;
    private final ConsoleUI consoleUI;
    private final List<MenuOption> menuOptions = new ArrayList<>();

    public Menu(String title, ConsoleUI consoleUI) {
        this.title = title;
        this.consoleUI = consoleUI;
    }

    public void addOption(String label, Runnable action) {
        MenuOption option = new MenuOption(label, action);
        menuOptions.add(option);
    }

    public void show() {
        consoleUI.printMenu(title, menuOptions);
    }

    public void select() {
        while (true) {
            int index = consoleUI.readNumber() - 1;
            if (index >= 0 && index < menuOptions.size()) {
                MenuOption option = menuOptions.get(index);
                option.action().run();
                break;
            }
            consoleUI.printError(ERROR_MESSAGE);
        }
    }
}
