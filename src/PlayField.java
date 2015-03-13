import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

import java.util.ArrayList;

public class PlayField implements TwoDSpaces {

    // Since we are using indices for ArrayList, 0,0 is a valid X/Y.
    // Directions: Specify the columns and rows in ColumnsWorld,
    // and they will be converted here to their proper indices
    public static final int MAX_WIDTH_INDEX = (ColumnsWorld.PLAY_COLUMNS - 1);
    public static final int MAX_HEIGHT_INDEX = (ColumnsWorld.PLAY_ROWS - 1);
    public static final int playArea = (ColumnsWorld.PLAY_COLUMNS * ColumnsWorld.PLAY_ROWS);
    public int score = 0;

    ArrayList<Block> field;

    PlayerPiece playerPiece;

    public PlayField() {
        this.field =
                //  Why should this be minus 1?
                new ArrayList<Block>(PlayField.playArea);
        //  For loop; fill the field with empty Blocks
        //System.out.println("Filling field with EMT Blocks");
        for (int ix = 0; ix <= PlayField.MAX_WIDTH_INDEX; ix++) {
            for (int iy = 0; iy <= PlayField.MAX_HEIGHT_INDEX; iy++) {
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
        int playerInitX = Main.rand.nextInt(PlayField.MAX_WIDTH_INDEX) * ColumnsWorld.BLOCK_SIZE;
        this.playerPiece = new PlayerPiece(playerInitX);
    }


    // Constructor for updating state
    public PlayField(ArrayList<Block> ff, PlayerPiece pp, int sc) {
        this.field = ff;
        this.playerPiece = pp;
        this.score = sc;
    }

    public int width() {
        return PlayField.MAX_WIDTH_INDEX;
    }

    public int height() {
        return PlayField.MAX_HEIGHT_INDEX;
    }

    public PlayField replace(Block bb) {
        ArrayList<Block> newField = this.field;
        newField.remove(this.getAtXY(bb.posn()));
        newField.add(bb);
        return new PlayField(newField, this.playerPiece, this.score);
    }

    public PlayField remove(Block bb) {
        Posn newPos = bb.posn();
        Block emptyBlock = new Block(newPos, BlockType.EMT);
        return this.replace(emptyBlock);
    }

    public Block getAtXY(Posn pp) throws RuntimeException {
        Block outB = new Block(new Posn(-5, -5), BlockType.EMT);
        //  Loop through all the blocks in the field
        for (Block b : field) {
            if (b.isSamePosn(pp)) {
                outB = b;
            }
        }

        //  Using a sentinel value : posn x, y can never be -1
        if (outB.equals(new Block(new Posn(-5, -5), BlockType.EMT))) {
            // TODO: Catch the RuntimeException in the caller of outB
            throw new RuntimeException("ERROR: Block with Posn( " + pp.x + ", " + pp.y + " ) not found");
        }
        return outB;
    }

    // Relatively Complex...
    // For each block in playField,
    // Is there a block with X index +1 away with the same type?
    // Is there a block with X index -1 away with the same type?
    // Is there a block with Y index -1 away with the same type?
    // Is there a block with Y index +1 away with the same type?

    // If so, check one more time if there is one nearby ^^, and then
    // remove the matched blocks with empty blocks, increment score by 1
    // Removed blocks— they will be handled by "gravity"


    // TODO: Implement longestSameColor
    public ArrayList<Block> longestSameColor(Block bb, Block prevBlock, ArrayList<Block> acc) {
        try {
            Posn right = new Posn(bb.posn().x + 1, bb.posn().y);
            Block rightBlock = this.getAtXY(right);
            if (rightBlock.isSameType(bb) && !(rightBlock.equals(prevBlock))) {
                acc.add(rightBlock);
                this.longestSameColor(rightBlock, bb, acc);
            }
        } catch (RuntimeException e) {
            System.out.println("longestSameColor() - tried right: " + e.toString());
        }

        try {
            Posn left = new Posn(bb.posn().x - 1, bb.posn().y);
            Block leftBlock = this.getAtXY(left);
            if (leftBlock.isSameType(bb) && !(leftBlock.equals(prevBlock))) {
                acc.add(leftBlock);
                this.longestSameColor(leftBlock, bb, acc);
            }
        } catch (RuntimeException e) {
            System.out.println("longestSameColor() - tried left: " + e.toString());
        }

        try {
            Posn above = new Posn(bb.posn().x, bb.posn().y - 1);
            Block aboveBlock = this.getAtXY(above);
            if (aboveBlock.isSameType(bb) && !(aboveBlock.equals(prevBlock))) {
                acc.add(aboveBlock);
                this.longestSameColor(aboveBlock, bb, acc);
            }
        } catch (RuntimeException e) {
            System.out.println("longestSameColor() - tried above: " + e.toString());
        }

        try {
            Posn below = new Posn(bb.posn().x, bb.posn().y + 1);
            Block belowBlock = this.getAtXY(below);
            if (belowBlock.isSameType(bb) && !(belowBlock.equals(prevBlock))) {
                acc.add(belowBlock);
                this.longestSameColor(belowBlock, bb, acc);
            }
        } catch (RuntimeException e) {
            System.out.println("longestSameColor() - tried below: " + e.toString());
        }
        //  TODO: Tester said one test didn't work... hunch tells me that I forgot to add the first block to the accumulator?...
        //  finally, add the last block
        acc.add(prevBlock);
        return acc;
    }

    // TODO: Implement updateMatches()
    /*
    public PlayField updateMatches() {
        for (Block bb : this.field) {
            this.longestSameColor(bb);
        }

        return new PlayField();
    }
    */

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
    public PlayField movePlayerLeft() {

        PlayerPiece newPiece;
        ArrayList<Block> playerMoved = this.playerPiece.player;

        int newIndexX = this.playerPiece.indexX;

        if (this.playerPiece.indexX > 0) {
            newIndexX = this.playerPiece.indexX - 1;
            for (Block bb : playerMoved) {
                bb.posn().x = newIndexX;
            }
            newPiece = new PlayerPiece(playerMoved, newIndexX, this.playerPiece.indexY);
        } else {
            //  Don't change anything
            newPiece = this.playerPiece;
        }

        PlayField newField = new PlayField(this.field, newPiece, this.score);

        return newField;
    }

    // TODO: Move player right
    // Update all X indices of PlayerPiece by adding one, if they do not cause illegal collisions
    public PlayField movePlayerRight() {

        PlayerPiece newPiece;
        ArrayList<Block> playerMoved = this.playerPiece.player;

        int newIndexX = this.playerPiece.indexX;

        if (this.playerPiece.indexX < PlayField.MAX_WIDTH_INDEX) {
            newIndexX = this.playerPiece.indexX + 1;
            for (Block bb : playerMoved) {
                bb.posn().x = newIndexX;
            }
            newPiece = new PlayerPiece(playerMoved, newIndexX, this.playerPiece.indexY);
        } else {
            //  Don't change anything
            newPiece = this.playerPiece;
        }

        PlayField newField = new PlayField(this.field, newPiece, this.score);

        return newField;
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
                "MAX_WIDTH_INDEX=" + MAX_WIDTH_INDEX +
                ", MAX_HEIGHT_INDEX=" + MAX_HEIGHT_INDEX +
                ", score=" + score +
                ", field=" + field +
                ", playerPiece=" + playerPiece +
                '}';
    }
}