import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;
import javalib.colors.*;

import java.awt.*;


public class Block {

    int h = ColumnsWorld.BLOCK_SIZE;
    int w = ColumnsWorld.BLOCK_SIZE;

    //  TODO: this posn represents the index
    Posn posnIndex;
    protected BlockType type = BlockType.EMT;

    public Block(Posn pp, BlockType tt) {
        this.posnIndex = pp;
        this.type = tt;
    }

    // TODO: This constructor is used to generate a new PlayerBlock at the posn
    public Block(Posn ppi) {
        this.posnIndex = ppi;

        // TODO: Randomly generate a type
        int ran = Main.rand.nextInt(BlockType.values().length);
        this.type = BlockType.values()[ran];
    }

    /*
    * Returns the BlockType of this block
    * @return the corresponding BlockType of this
    */
    public BlockType type() {
        return this.type;
    }

    /*
    * Returns the Posn of this block
    * @return The Posn of the block
     */
    public Posn posn() {
        return this.posnIndex;
    }

    /*
     * Returns a Block of BlockType MT in the same x y position.
     * @param pp The Posn position of the original block
     * @return A new Block, reset to BlockType MT
     *
     */
    public Block clear(Posn pp) {
        return new Block(pp, BlockType.EMT);
    }

    /*
    * Returns a boolean if this block is empty
    * @return A boolean
    */
    public boolean isEmpty() {
        return (this.type == BlockType.EMT);
    }


    public WorldImage draw() {
        //Return a RectangleImage representing the rectangle
        // TODO: Switch statement, decide what color the block should be drawn

        Posn posnPixel = new Posn(
                this.posnIndex.x * ColumnsWorld.BLOCK_SIZE,
                this.posnIndex.y * ColumnsWorld.BLOCK_SIZE
        );
        // TODO: System.out.println("Block draw() pos: " + pos.x + ", " + pos.y);

        //  TODO: Yellow is stuff drawn with Block
        return new RectangleImage(
                //  TODO: Turn posnPixel into Pinhole...
                new Pinhole(posnPixel)
                , this.h, this.w, Color.GREEN);
    }

    /*
    * Returns a boolean if this block is the same as Block bb
    * @return Returns true if this has the same properties as bb
     */
    public boolean equals(Block bb) {
        return ((this.posnIndex.x == bb.posnIndex.x)
                && (this.posnIndex.y == bb.posnIndex.y) &&
                (this.isSameType(bb))
        );
    }

    /*
    * Returns a boolean if Block b has the same BlockType as this block.
    * @param b A Block to compare to
    * @return A boolean if b has same BlockType as this
     */
    public boolean isSameType(Block b) {
        return (this.type == b.type());
    }

    /*
    * Returns a boolean true if this block has same Posn values as pp
    * @return Returns true if this has the same Posn values as pp
     */
    public boolean isSamePosn(Posn pp) {
        return ((this.posnIndex.x == pp.x)
                && (this.posnIndex.y == pp.y));
    }
}
