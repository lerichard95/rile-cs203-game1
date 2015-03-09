import javalib.colors.Blue;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

import java.awt.*;
import java.util.ArrayList;

public class PlayField implements TwoDSpaces {
    // Rectangular

    // implementation of the playing field
    // An ArrayList of Blocks
    int playAreaWidth;
    int playAreaHeight;

    ArrayList<Block> field;

    // TODO: player holds the "PlayerPiece"
    // Movement done in PlayField
    PlayerPiece playerPiece;

    public PlayField(int w, int h) {
        this.playAreaWidth = w;
        this.playAreaHeight = h;

        field = new ArrayList<Block>(w * h);

        //  For loop; fill the field with empty Blocks
        System.out.println("Filling field with EMT Blocks");
        for (int i = 0; i <= this.playAreaWidth; i++) {
            for (int j = 0; j <= this.playAreaHeight; j++) {
                // TODO: Check the i and j values
                System.out.println("i: " + i + ", j: " + j);
                field.add(
                        new Block(
                                new Posn(i * ColumnsWorld.BLOCK_SIZE, j * ColumnsWorld.BLOCK_SIZE),
                                BlockType.EMT));
            }
        }


        //  TODO: Make a new player piece on init
        //  Choose a random x for the PlayerPiece
        int playerInitX = Main.rand.nextInt(this.playAreaWidth) * ColumnsWorld.BLOCK_SIZE;
        this.playerPiece = new PlayerPiece(playerInitX);
    }

    public int width() {
        return this.playAreaWidth;
    }

    public int height() {
        return this.playAreaHeight;
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

    public PlayField empty(int width, int height) {
        return new PlayField(width, height);
    }

    // TODO: Check for playerPiece + field matches
    //

    // TODO: Check for playerPiece "landing"
    // When the playerPiece lands, remove all the Blocks from field that have same Posn as playerPiece
    // and replace with new Blocks with same Posn as the landed playerPiece

    // TODO: Move player left
    // TODO: Move player right
    // TODO: Quickdrop player down

    // TODO: Gravity- move player down one

    // Where should collision checking be done?

    public WorldImage draw() {
        // TODO: Draw gridlines for background... It doesn't work!! Why??

        /*
        //  Initial grid rectangle
        WorldImage imgGrid =
                new RectangleImage(
                        new Posn(ColumnsWorld.BLOCK_SIZE / 2, ColumnsWorld.BLOCK_SIZE / 2),
                        ColumnsWorld.BLOCK_SIZE,
                        ColumnsWorld.BLOCK_SIZE,
                        new javalib.colors.Green()
                );

        //  Draw the rest of the grid
        for (int xx = 1; xx >= this.width(); xx++) {
            for (int yy = 1; yy >= this.height(); yy++) {
                //  Generating a "pinhole:" Pinhole is the "center" of the block.
                Posn newPos = new Posn(xx + (ColumnsWorld.BLOCK_SIZE / 2), yy + (ColumnsWorld.BLOCK_SIZE / 2));
                WorldImage imgGPiece = new RectangleImage(
                        newPos,
                        ColumnsWorld.BLOCK_SIZE,
                        ColumnsWorld.BLOCK_SIZE,
                        Color.BLACK);
                imgGrid = new OverlayImages(imgGrid, imgGPiece);
            }
        }
        System.out.println("Finished drawing grid");

    */

        //  Draw the first block
        WorldImage initBlock = field.get(0).draw();
        System.out.println("Finished drawing first block");

        //  Overlay rectangles for all blocks
        for (Block bb : this.field) {
            WorldImage imgBlock = initBlock;
            // TODO: Why are the X/Y values so high? Shouldn't they be from 0 to playAreaWidth?
            System.out.println("bb: " + bb.posn().x + "," + bb.posn().y + " TYPE: " + bb.type());
            initBlock = new OverlayImages(imgBlock, bb.draw());
        }
        System.out.println("Finished drawing all blocks");

        //  Overlay the blocks onto the grid

        return initBlock;
    }
}