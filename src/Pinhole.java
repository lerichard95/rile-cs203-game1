import javalib.worldimages.Posn;

/*
pinhole, posn
Pinhole is used solely for drawing— represents the center of a rectangle.
posnPixel is used to keep the position— represents the top left pixeL.
    TO GET A POSNPIXEL: posnIndex * BLOCK_SIZE


*/

public class Pinhole extends Posn {
    int posnX;
    int posnY;

    //  Converts a Posn to a pinhole
    public Pinhole(Posn pp) {
        super(pp.x + (ColumnsWorld.BLOCK_SIZE / 2),
                pp.y + (ColumnsWorld.BLOCK_SIZE / 2));
        this.posnX = pp.x;
        this.posnY = pp.y;
    }

    public String toString(){
        return "posnX: " + this.posnX + ", posnY: " +
                this.posnY + ". this.x: " + this.x
                + ", this.y: " + this.y;
    }
}
