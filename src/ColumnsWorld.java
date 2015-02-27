//  G1 represents the current state of world... Maybe it should extend
//  javalib.funworld.World???

import javalib.worldimages.WorldImage;

public class ColumnsWorld extends javalib.funworld.World {
    int width = 480;
    int height = 600;

    int score = 0;
    boolean winState = false;

    /* Constructor */
    public ColumnsWorld() {
        super();

    }

    /* Main method */
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Tester tests = new Tester();
        System.out.println(tests.testsPassedHuh());

        //  Big-bang() - starts on-tick

        //  on-tick() - returns world
        // - What should happen when the game starts???
        // - Spawn a PlayerPiece for the player to control
        // - Check for collision (failure)
        // - Advance block down once for gravity
        // - Check for fusion (when PlayerPiece is laid down)
        // - Check for collision (matches)

        //  on-key() - returns world
        /*  If direction keys are pressed, check for collision— if a move does not collide, move a the correct direction
        *   If cycle keys pressed, cycle the PlayerPiece blocks
        *   If rotation keys are pressed, rotate the PlayerPiece
        *   If quickdrop key is pressed, instantly drop the PlayerPiece
        */
    }


    //Need to research this
    @Override
    public WorldImage makeImage() {
        // TODO Auto-generated method stub
        // Returns a picture of the game

    }

}
