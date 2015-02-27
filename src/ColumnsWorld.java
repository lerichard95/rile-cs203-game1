//  G1 represents the current state of world... Maybe it should extend
//  javalib.funworld.World???

import javalib.worldimages.OverlayImages;
import javalib.worldimages.WorldImage;

public class ColumnsWorld extends javalib.funworld.World {

    static final int BLOCK_SIZE = 20;

    Block testBlock;
    int score = 0;
    boolean winState = false;

    /* Constructor */
    public ColumnsWorld() {
        super();

    }


    //  World on-tick()
    // - What should happen when the game starts???
    // - Spawn a PlayerPiece for the player to control
    // - Advance block down once for gravity
    // - Check for fusion (when PlayerPiece is laid down)
    // - Check for matches

    //  World on-key()
        /*  If direction keys are pressed, check for collision— if a move does not collide, move a the correct direction
        *   If cycle keys pressed, cycle the PlayerPiece blocks
        *   If rotation keys are pressed, rotate the PlayerPiece
        *   If quickdrop key is pressed, instantly drop the PlayerPiece
        */

    //  WorldEnd worldEnds()
        /*
        * Override this method for the conditions to check for failure
        * - Check for collision (failure)
         */


    //Need to research this
    @Override
    public WorldImage makeImage() {
        // TODO Auto-generated method stub
        // Returns a picture of the game
        this.testBlock = new Block(0);
        return testBlock.draw();
    }

}
