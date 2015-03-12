import javalib.funworld.*;
import javalib.worldimages.*;
import javalib.worldcanvas.*;

public class ColumnsWorld extends javalib.funworld.World {

    static final int BLOCK_SIZE = 40;
    static final int PLAY_ROWS = 4;
    static final int PLAY_COLUMNS = 8;

    Block testBlock;

    TwoDSpaces playField;

    int score = 0;
    boolean winState = false;

    //  Holds the state consisting of the playfield and the player piece
    //  TODO: Should the player piece be inside of the PlayField?

    //  Initial constructor
    public ColumnsWorld() {
        super();

        System.out.println("Init ColumnsWorld created");
        this.playField = new PlayField();
        // - FIRST TIME: Spawn a PlayerPiece for the player to control
    }

    //  Constructor used for updating states
    public ColumnsWorld(PlayField pf, int sc, boolean wS) {
        super();
        //  Update pieces of state
        this.score = sc;
        this.playField = pf;
        this.winState = wS;

        System.out.println("Updated columnsWorld state");
    }


    // World onTick()

    /*
    public World onTick() {
        //  TODO: onTick functions for PlayField should all return PlayField.
        //  So use ColumnsWorld constructor to build a new ColumnsWorld for each function?

        //  TODO: Advance the block down once for gravity
        in/out function scheme is
        playField -> playField -> playField
        ex: ColumnsWorld( this.playField.gravityAdvance().playerLanding().updateMatches()??
        int sc, boolean wS );

        // TODO: Check for landing (when PlayerPiece is laid down)
        this.playField.playerLanding();

        // TODO: Check for matches -> clear matches, increment score...
        this.playField.updateMatches();

    }
    */

    //  World on-key()
        /*  If direction keys are pressed, check for collision— if a move does not collide, move a the correct direction
        *   If cycle keys pressed, cycle the PlayerPiece blocks
        *   If quickdrop key is pressed, instantly drop the PlayerPiece
        *   EXTRA: If rotation keys are pressed, rotate the PlayerPiece
        */
    /*public World onKeyEvent(String ke) {

    }

    */

    //  WorldEnd worldEnds()
        /*
        * Override this method for the conditions to check for failure
        * - Check for overflow (failure)
         */

    public WorldImage makeImage() {
        // TODO: Draw all the state objects- playfield
        // Returns a picture of the game
        //this.testBlock = new Block(new javalib.worldimages.Posn(100, 100), BlockType.EMT);
        //return testBlock.draw();
        return this.playField.draw();
    }

}
