import javalib.colors.Blue;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

import java.awt.*;
import java.util.ArrayList;

public class PlayField implements TwoDSpaces {

    // Since we are using indices for ArrayList, 0,0 is a valid X/Y.
    // Directions: Specify the columns and rows in ColumnsWorld,
    // and they will be converted here to their proper indices
    public static final int playAreaWidth = ColumnsWorld.PLAY_COLUMNS - 1;
    public static final int playAreaHeight = ColumnsWorld.PLAY_ROWS - 1;
    public int score = 0;

    ArrayList<Block> field;

    PlayerPiece playerPiece;

    public PlayField() {
        this.field =
                //  Why should this be minus 1?
                new ArrayList<Block>((playAreaWidth * playAreaHeight));
        //  For loop; fill the field with empty Blocks
        System.out.println("Filling field with EMT Blocks");
        for (int ix = 0; ix <= PlayField.playAreaWidth; ix++) {
            for (int iy = 0; iy <= PlayField.playAreaHeight; iy++) {
                // TODO: Check the i and j values
                //System.out.println("ix: " + ix + ", iy: " + iy);
                this.field.add(
                        new Block(
                                // TODO: All uses of Block constructor need to pass an index
                                new Posn(ix, iy),
                                BlockType.EMT));
            }
        }

        //  TODO: Make a new player piece on init
        //  Choose a random x for the PlayerPiece
        int playerInitX = Main.rand.nextInt(PlayField.playAreaWidth) * ColumnsWorld.BLOCK_SIZE;
        this.playerPiece = new PlayerPiece(playerInitX);
    }


    // Constructor for updating state
    public PlayField(ArrayList<Block> ff, PlayerPiece pp, int sc) {
        this.field = ff;
        this.playerPiece = pp;
        this.score = sc;
    }

    public int width() {
        return PlayField.playAreaWidth;
    }

    public int height() {
        return PlayField.playAreaHeight;
    }

    public PlayField replace(Block bb) {
        ArrayList<Block> newField = this.field;
        newField.remove(this.getAtXY(bb.posn()));
        newField.add(bb);
        return new PlayField(newField, this.playerPiece, this.score);
    }

    public Block getAtXY(Posn pp) throws RuntimeException {
        Block outB = new Block(new Posn(-1, -1), BlockType.EMT);

        //  Loop through all the blocks in the field
        for (Block b : field) {
            if (b.isSamePosn(pp)) {
                outB = b;
            }
        }
        //  Using a sentinel value : posn x, y can never be -1

        if (outB.equals(new Block(new Posn(-1, -1), BlockType.EMT))) {
            // TODO: Catch the RuntimeException in the caller of outB
            throw new RuntimeException("ERROR: Block with Posn(x, y) not found");
        }
        return outB;
    }

    // TODO: Check for playerPiece + field matches
    // Relatively Complex...
    // For each block in playField,
    // Is there a block with X index +1 away with the same type?
    // Is there a block with X index 1 away with the same type?
    // Is there a block with Y index -1 away with the same type?
    // Is there a block with Y index +1 away with the same type?
    // If so, check one more time if there is one nearby ^^, and then
    // remove the matched blocks with empty blocks, increment score by 1
    // Removed blocks— they will be handled by "gravity"


    public PlayField updateMatches() {

        return new PlayField();
    }


    // TODO: Gravity- move player down one
    // Update Y indices of playerPiece by adding 1, if they do not cause illegal collision

    // TODO: Check for playerPiece "landing"
    // When the playerPiece lands, remove all the Blocks from field that have same Posn as playerPiece
    // and replace with new Blocks with same Posn as the landed playerPiece
    public PlayField playerLanding() {

        return new PlayField();
    }

    // TODO: Move player left
    // Update all X indices of PlayerPiece one by subtracting one, if they do not cause illegal collisions
    public PlayerPiece movePlayerLeft() {

        return new PlayerPiece(3);
    }

    // TODO: Move player right
    // Update all X indices of PlayerPiece by adding one, if they do not cause illegal collisions
    public PlayerPiece movePlayerRight() {

        return new PlayerPiece(3);
    }

    // TODO: Quickdrop player down
    // Calculate the nearest block in the same Y column from playField that is empty
    // Change the Y indices of PlayerPiece blocks such that the lowest Y index is the same as above
    public PlayField quickDrop() {
        return new PlayField();
    }

    // TODO: quickdrop helper - Find nearest Y block in same column
    public Block findNearestY(int col) {
        return new Block(new Posn(0, 0));
    }

    public WorldImage draw() {
        // TODO: Draw gridlines for background
        // TODO: Research how rectangles with stroke should be drawn...
        //  Initial grid rectangle
        /*
        WorldImage imageGridInit =
                new RectangleImage(
                        new Pinhole(new Posn(0, 0)),
                        ColumnsWorld.BLOCK_SIZE,
                        ColumnsWorld.BLOCK_SIZE,
                        new javalib.colors.Blue()
                );

        //  Draw the rest of the grid
        for (int xx = 1; xx >= this.width(); xx++) {
            for (int yy = 1; yy >= this.height(); yy++) {
                Posn newPos = new Pinhole(new Posn(xx, yy));
                WorldImage imgGPiece = new RectangleImage(
                        newPos,
                        ColumnsWorld.BLOCK_SIZE,
                        ColumnsWorld.BLOCK_SIZE,
                        Color.BLACK);
                imageGridInit = new OverlayImages(imageGridInit, imgGPiece);
            }
        }
        System.out.println("Finished drawing grid");
        */


        //  Draw the first block
        WorldImage imageBlockInit = field.get(0).draw();

        WorldImage imageBlocks = imageBlockInit;
        //  Overlay rectangles for all blocks
        for (Block bb : this.field) {
            // System.out.println("bb: " + bb.posn().x + "," + bb.posn().y + " TYPE: " + bb.type());
            imageBlocks = new OverlayImages(imageBlocks, bb.draw());
        }

        System.out.println("Finished drawing all blocks");

        return imageBlocks;
    }

    @Override
    public String toString() {
        return "PlayField{" +
                "playAreaWidth=" + playAreaWidth +
                ", playAreaHeight=" + playAreaHeight +
                ", score=" + score +
                ", field=" + field +
                ", playerPiece=" + playerPiece +
                '}';
    }
}