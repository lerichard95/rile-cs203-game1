import javalib.colors.Blue;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

import java.awt.*;
import java.util.ArrayList;

public class PlayField implements TwoDSpaces {

    int playAreaWidth = ColumnsWorld.PLAY_COLUMNS;
    int playAreaHeight = ColumnsWorld.PLAY_ROWS;
    public int score = 0;

    ArrayList<Block> field;

    PlayerPiece playerPiece;

    public PlayField() {
        field = new ArrayList<Block>(
                ((playAreaWidth * playAreaHeight) - 1)
        );


        //  For loop; fill the field with empty Blocks
        System.out.println("Filling field with EMT Blocks");
        for (int ix = 0; ix <= this.playAreaWidth; ix++) {
            for (int iy = 0; iy <= this.playAreaHeight; iy++) {
                // TODO: Check the i and j values
                System.out.println("ix: " + ix + ", iy: " + iy);
                field.add(
                        new Block(
                                // TODO: All uses of Block constructor need to pass an index
                                new Posn(ix, iy),
                                BlockType.EMT));
            }
        }


        //  TODO: Make a new player piece on init
        //  Choose a random x for the PlayerPiece
        int playerInitX = Main.rand.nextInt(this.playAreaWidth) * ColumnsWorld.BLOCK_SIZE;
        this.playerPiece = new PlayerPiece(playerInitX);
    }

    // Constructor for updating state
    public PlayField(ArrayList<Block> ff, int sc) {
        this.field = ff;
        this.score = sc;
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
        return new PlayField();
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

        WorldImage blockk = initBlock;
        //  Overlay rectangles for all blocks
        for (Block bb : this.field) {
            System.out.println("bb: " + bb.posn().x + "," + bb.posn().y + " TYPE: " + bb.type());
            blockk = new OverlayImages(blockk, bb.draw());
        }

        System.out.println("Finished drawing all blocks");

        return blockk;
    }
}