import java.util.Random;

// TODO: All functions should be pure functional— return a new instance of itself...


public class Main {
    //This is the main class
    static int WINDOW_HEIGHT = 1000;
    static int WINDOW_WIDTH = 1000;
    static Random rand = new Random();

    public static void main(String[] args) {



        //This class is specifically for testing— it will run all the tests for the game
        Tester tests = new Tester();
        System.out.println("testsPassedHuh() -> " + tests.testsPassedHuh());

        System.out.println("starting game...");
        ColumnsWorld game = new ColumnsWorld();

        //  Set window height and width based on the columns and rows of the game
        WINDOW_HEIGHT = ColumnsWorld.PLAY_COLUMNS * ColumnsWorld.BLOCK_SIZE;
        WINDOW_WIDTH = ColumnsWorld.PLAY_ROWS * ColumnsWorld.BLOCK_SIZE;

        System.out.println("big banging");
        game.bigBang(WINDOW_WIDTH, WINDOW_HEIGHT, 1.0);


    }

}
