import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;
import javalib.colors.*;

import java.awt.*;

public class Block {
    int h = ColumnsWorld.BLOCK_SIZE;
    int w = ColumnsWorld.BLOCK_SIZE;

    // Posn
    Posn posn;
    protected BlockType type = BlockType.EMT;

    public Block(Posn pp, BlockType tt) {
        posn = pp;
        this.type = tt;
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
        return this.posn;
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
    * Returns a boolean if Block b has the same BlockType as this block.
    * @param b A Block to compare to
    * @return A boolean if b has same BlockType as this
     */
    public boolean isSameType(Block b) {
        return (this.type == b.type());
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
        return new RectangleImage(this.posn, this.h, this.w, Color.blue);
    }

    /*
    * Returns a boolean if this block is the same as Block bb
    * @return Returns true if this has the same properties as bb
     */
    public boolean equals(Block bb) {
        return ((this.posn().equals(bb.posn())) &&
                (this.isSameType(bb))
        );
    }

}