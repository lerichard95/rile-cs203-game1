import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;
import javalib.colors.*;
import java.awt.*;

public class Block {
    // Empty block is type 0
    // Active block types: 1 2 3 4

    // TODO: Make this an ENUM
    int type = 0;
    int h = ColumnsWorld.BLOCK_SIZE;
    int w = ColumnsWorld.BLOCK_SIZE;
    // The coordinates for a block??...
    int x = 50;
    int y = 50;


    public Block(int t) {
        this.type = t;
    }

    //  Clears a Block by returning a block of type 0
    public Block clear() {
        return new Block(0);
    }

    public boolean compareType(int i) {
        return (type == i);
    }

    public boolean isEmpty() {
        return (type == 0);
    }

    public WorldImage draw() {
        //Return a RectangleImage representing the rectangle
        return new RectangleImage(new Posn(x, y), this.h, this.w, Color.blue);
    }


}