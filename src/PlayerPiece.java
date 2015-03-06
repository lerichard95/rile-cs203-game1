import javalib.worldimages.Posn;

import java.util.ArrayList;

public class PlayerPiece {
	//  A set of blocks that represents the player's current
	//  blocks— controlled by keyboard

    ArrayList<Block> player;

    //  Takes in an x so that PlayField knows what x will be and can compare
    public PlayerPiece(int xx) {
        for (int y = 0; y <= 3; y++) {
            // use the constructor for a random BlockType
            player.add(new Block(new Posn(xx, y * ColumnsWorld.BLOCK_SIZE)));
        }
    }

    // TODO: cycle the order of the blocks upwards one
    /*
    public PlayerPiece cycleUp(){

    }
    */

    // TODO: Cycle the order of the blocks downwards one
	
}