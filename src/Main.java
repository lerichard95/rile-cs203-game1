public class Main {
    //This is the main class
    static final int width = 480;
    static final int height = 600;

    /* Main method */

    public static void main(String[] args) {

        //This class is specifically for testing— it will run all the tests for the game
        Tester tests = new Tester();
        System.out.println("testsPassedHuh() -> " + tests.testsPassedHuh());


        System.out.println("starting game...");
        ColumnsWorld game = new ColumnsWorld();
        System.out.println("big banging");
        game.bigBang(width, height, 1.0);


    }

}
