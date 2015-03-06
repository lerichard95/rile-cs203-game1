import javalib.colors.Blue;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

import java.util.ArrayList;
import java.util.Random;

public class PlayField implements TwoDSpaces {
    // Rectangular

    // implementation of the playing field
    // An ArrayList of Blocks
    int width;
    int height;
    Random rand = new Random();

    ArrayList<Block> field;

    // TODO: player holds the "PlayerPiece"
    // Movement done in PlayField
    PlayerPiece playerPiece;

    public PlayField(int w, int h) {
        // These should be SMALL numbers to be multiplied with the blocksize...
        this.width = w;
        this.height = h;
        field = new ArrayList<Block>(w * h);

        System.out.println("init Playfield inside PF");

        //  For loop; fill the field with empty Blocks
        for (int i = 0; i <= this.width(); i++) {
            for (int j = 0; j <= this.height(); j++) {
                field.add(new Block(new Posn(i + ColumnsWorld.BLOCK_SIZE, j + ColumnsWorld.BLOCK_SIZE),
                        BlockType.EMT));
            }
        }

        System.out.println("loop done");

        //  TODO: Make a new player piece on init
        //  Choose a random x for the PlayerPiece
        int playerInitX = rand.nextInt(this.width) * ColumnsWorld.BLOCK_SIZE;
        this.playerPiece = new PlayerPiece(playerInitX);
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public Block getAtXY(Posn pp) throws RuntimeException {
        Block outB = new Block(new Posn(-1, -1), BlockType.EMT);

        //  Loop through all the blocks in the field
        for (Block b : field) {
            //  TODO: Test if posn equals works correctly...
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
        // TODO: Draw gridlines for background... why doesn't it work?? hmmm
        WorldImage imgGrid = new RectangleImage(
                new Posn(0 + (ColumnsWorld.BLOCK_SIZE / 2), 0 + (ColumnsWorld.BLOCK_SIZE)),
                ColumnsWorld.BLOCK_SIZE,
                ColumnsWorld.BLOCK_SIZE,
                new Blue()
        );

        for (int xx = 1; xx >= this.width(); xx++) {
            for (int yy = 1; yy >= this.height(); yy++) {
                WorldImage imgGPiece = new RectangleImage(
                        new Posn(xx + (ColumnsWorld.BLOCK_SIZE / 2), yy + (ColumnsWorld.BLOCK_SIZE)),
                        ColumnsWorld.BLOCK_SIZE,
                        ColumnsWorld.BLOCK_SIZE,
                        new Blue());
                imgGrid = new OverlayImages(imgGrid, imgGPiece);
            }
        }

        WorldImage imgBlocks;
        // Put the first block there
        imgBlocks = field.get(0).draw();
        //  Overlay images of all on top of each other?
        for (Block b : this.field) {
            WorldImage imgB;
            imgB = b.draw();
            imgBlocks = new OverlayImages(imgGrid, imgB);
        }

        WorldImage imgFinal;
        imgFinal = new OverlayImages(imgGrid, imgBlocks);
        return imgFinal;
    }
}