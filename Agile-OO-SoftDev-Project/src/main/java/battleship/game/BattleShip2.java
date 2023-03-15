package battleship.game;

public class BattleShip2 {
    private final Display display;
    private final Game game = new Game();
    private Input input;

    public BattleShip2() {
        this.display = new Display();
    }

    public void start() {
        display.printMenu();
        mainMenu();
    }


    public void mainMenu() {
        input = new Input();

        while (true) {
            display.printMainMenuOptions();
            System.out.println("Enter your choice: ");
            System.out.println();
            switch (input.getIntegerMenuOption()) {
                case 0:
                    display.printMessages("You choose to play the game\n");
                    game.gameLogic();
                    break;
                case 1:
                    display.gameRules();
                    break;
                case 2:
                    display.printMessages("You choose to exit");
                    exitGame();
                    break;
            }
        }
    }

    public void exitGame() {
        display.printExitMessage();
        System.exit(0);
    }
}
