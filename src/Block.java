import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;
import javalib.colors.*;

import java.awt.*;

public class Block {
    protected BlockType type = MT;
    int h = ColumnsWorld.BLOCK_SIZE;
    int w = ColumnsWorld.BLOCK_SIZE;

    // The coordinates for a block??...
    int x = 50;
    int y = 50;

    public Block(int xx, int yy, BlockType tt) {
        this.x = xx;
        this.y = yy;
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
     * Returns a Block of BlockType MT in the same x y position.
     * @param xx The x position of the original block
     * @param yy The y position of the original block
     * @return A new Block, reset to BlockType MT
     *
     */
    public Block clear(int xx, int yy) {
        return new Block(xx, yy, MT);
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
        return (this.type == MT);
    }


    public WorldImage draw() {
        //Return a RectangleImage representing the rectangle
        return new RectangleImage(new Posn(x, y), this.h, this.w, Color.blue);
    }


}