import java.util.Random;
import tester.*;

public class Main {
    //This is the main class
    static int WINDOW_HEIGHT = 1000;
    static int WINDOW_WIDTH = 1000;
    static Random rand = new Random();

    public static void main(String[] args) {

        //  Using the tester library...
        ColumnsExamples columnsExamples = new ColumnsExamples();
        Tester.run(columnsExamples);

        System.out.println("starting game...");
        ColumnsWorld game = new ColumnsWorld();

        //  Set window height and width based on the columns and rows of the game
        WINDOW_HEIGHT = ColumnsWorld.PLAY_COLUMNS * ColumnsWorld.BLOCK_SIZE;
        WINDOW_WIDTH = ColumnsWorld.PLAY_ROWS * ColumnsWorld.BLOCK_SIZE;

        System.out.println("big bang starting");
        game.bigBang(WINDOW_WIDTH, WINDOW_HEIGHT, 1.0);


    }

}
