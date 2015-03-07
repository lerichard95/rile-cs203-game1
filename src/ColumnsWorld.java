//  G1 represents the current state of world... Maybe it should extend
//  javalib.funworld.World???

import javalib.funworld.*;
import javalib.worldimages.*;
import javalib.worldcanvas.*;

public class ColumnsWorld extends javalib.funworld.World {

    static final int BLOCK_SIZE = 40;
    static final int PLAY_ROWS = 7;
    static final int PLAY_COLUMNS= 10;

    Block testBlock;

    PlayField playField;

    int score = 0;
    boolean winState = false;

    //  Holds the state consisting of the playfield and the player piece
    //  TODO: Should the player piece be inside of the PlayField?

    /* Constructor */
    public ColumnsWorld() {
        super();
        System.out.println("init Playfield from CW");
        this.playField = new PlayField(PLAY_ROWS, PLAY_COLUMNS);
    }


    // World on-tick()
    // What should happen when the game starts???
    // - FIRST TIME: Spawn a PlayerPiece for the player to control
    // - Advance block down once for gravity
    // - Check for landing (when PlayerPiece is laid down)
    // - Check for matches -> clear matches



    //  World on-key()
        /*  If direction keys are pressed, check for collision— if a move does not collide, move a the correct direction
        *   If cycle keys pressed, cycle the PlayerPiece blocks
        *   If quickdrop key is pressed, instantly drop the PlayerPiece
        *   EXTRA: If rotation keys are pressed, rotate the PlayerPiece
        */

    //  WorldEnd worldEnds()
        /*
        * Override this method for the conditions to check for failure
        * - Check for collision (failure)
         */





    //Need to research this
    public WorldImage makeImage() {
        // TODO: Draw all the state objects- playfield
        // Returns a picture of the game
        //this.testBlock = new Block(new javalib.worldimages.Posn(100, 100), BlockType.EMT);
        //return testBlock.draw();
        return playField.draw();
    }

}
