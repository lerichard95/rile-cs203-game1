import javalib.worldimages.Posn;

import java.util.ArrayList;

public class PlayField implements TwoDSpaces {
    // Rectangular

    // implementation of the playing field
    // An ArrayList of Blocks
    int width;
    int height;
    ArrayList<Block> field;

    public PlayField(int w, int h) {
        this.width = w;
        this.height = h;
        field = new ArrayList<Block>(w * h);

        for (int i = 0; i <= this.width(); i++) {
            for (int j = 0; j <= this.height(); i++) {
                field.add(new Block(new Posn(i + ColumnsWorld.BLOCK_SIZE, j + ColumnsWorld.BLOCK_SIZE),
                        BlockType.EMT));
            }
        }

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
            if (b.posn().equals(pp)) {
                outB = b;
            }
        }
        //  Using a sentinel value : posn x, y can never be -1
        if ( outB.equals(new Block(new Posn(-1, -1), BlockType.EMT))) {
            throw new RuntimeException("ERROR: Block with Posn(x, y) not found");
        }
        return outB;
    }

    public PlayField empty(int width, int height) {
        return new PlayField(width, height);


    }




    // Where should collision checking be done?

}