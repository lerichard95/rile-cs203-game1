import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

import java.util.ArrayList;

public class PlayerPiece {
    //  A set of blocks that represents the player's current
    //  blocks— controlled by keyboard

    public static final int BLOCK_COUNT = 3;
    ArrayList<Block> player;
    int indexX = 0;
    int indexY = 0;

    //  Takes in an x so that PlayField knows what x will be and can compare

    public PlayerPiece(int xx) {
        this.indexX = xx;
        this.indexY = 0;

        this.player = new ArrayList<Block>();
        //  Fill the ArrayList with new Blocks
        for (int yy = this.indexY; yy < PlayerPiece.BLOCK_COUNT; yy++) {
            // use the constructor for a random BlockType
            this.player.add(
                    new Block(new Posn(this.indexX, yy)));
        }
    }

    //  Constructor for updating state
    public PlayerPiece(ArrayList<Block> pp, int xx, int yy) {
        this.player = pp;
        this.indexX = xx;
        this.indexY = yy;
    }

    public PlayerPiece moveLeft() {
        ArrayList<Block> outArray = this.player;

        PlayerPiece outPiece;
        if (this.indexX > 0) {
            // decrement the PlayerPiece's field
            int newX = indexX - 1;
            // update blocks
            for (Block bb : outArray) {
                bb.posn().x = newX;
            }
            //  Make a playerPiece out of the shifted indices
            outPiece = new PlayerPiece(outArray, newX, this.indexY);
        } else {
            //  Replace the playerPiece, but don't shift index
            outPiece = new PlayerPiece(outArray, this.indexX, this.indexY);
        }
        return outPiece;
    }

    public PlayerPiece moveRight() {
        ArrayList<Block> outArray = this.player;

        PlayerPiece outPiece = new PlayerPiece(outArray, this.indexX, this.indexY);
        if (this.indexX < PlayField.MAX_WIDTH_INDEX) {
            int newX = this.indexX + 1;
            for (Block bb : outArray) {
                bb.posn().x = newX;
            }
            //  Make a playerPiece out of the shifted indices
            outPiece = new PlayerPiece(outArray, newX, this.indexY);
        } else {
            //  Replace the playerPiece, but don't shift index
            outPiece = new PlayerPiece(outArray, this.indexX, this.indexY);
        }
        return outPiece;
    }


    public PlayerPiece updatePlayerGravity() {
        ArrayList<Block> expectArr = this.player;

        PlayerPiece outPiece;

        if (this.indexY < PlayField.MAX_HEIGHT_INDEX) {
            int newIndexY = this.indexY + 1;
            for (Block bb : expectArr) {
                bb.posn().y = newIndexY;
            }
            outPiece = new PlayerPiece(expectArr, this.indexX, newIndexY);
        } else {
            outPiece = this;
        }
        return outPiece;
    }


    // TODO: cycle the order of the blocks upwards one

        /*
    public PlayerPiece cycleUp(){
    subtract one and modulo something...
    }
    */

    // TODO: Cycle the order of the blocks downwards one
    /*
    public PlayerPiece cycleDown() {
    add one and modulo something...
    }

     */

    public WorldImage draw() {
        WorldImage img = player.get(0).draw();
        for (Block bb : player) {
            img = new OverlayImages(img, bb.draw());
        }
        return img;
    }

    @Override
    public String toString() {
        return "PlayerPiece{" +
                "player=" + player +
                ", indexX=" + indexX +
                ", indexY=" + indexY +
                '}';
    }
}