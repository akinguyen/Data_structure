package byog.Core;

public class Game {
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        MenuGenerator a = new MenuGenerator(40,40);
        a.startMenu();

    }
}
