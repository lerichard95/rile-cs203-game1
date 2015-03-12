import javalib.worldimages.Posn;

/*
pinhole, posn
Pinhole is used solely for drawing— represents the center of a rectangle.
posnPixel is used to keep the position— represents the top left pixeL.
    TO GET A POSNPIXEL: posnIndex * BLOCK_SIZE
*/

public class Pinhole extends Posn {
    int indexX;
    int indexY;
    int pinX;
    int pinY;
    //  Constructor converts a Posn to a pinhole
    public Pinhole(Posn pp) {
        super(
                (pp.x + (ColumnsWorld.BLOCK_SIZE / 2)),
                (pp.y + (ColumnsWorld.BLOCK_SIZE / 2))
        );
        this.indexX = pp.x;
        this.indexY = pp.y;
        this.pinX = (pp.x + (ColumnsWorld.BLOCK_SIZE / 2));
        this.pinY = (pp.y + (ColumnsWorld.BLOCK_SIZE / 2));
    }

    /*
    * Returns true if the Pinhole X/Y's are the same as the posn's X/Y's
    *
     */
    public boolean sameValues(Posn pp) {
        System.out.println("this.pinX: " +this.pinX+ ", this.pinY: " + this.pinY);
        System.out.println("this.x: " + this.x + ", this.y: " + this.y);
        System.out.println("pp.x: " + pp.x + ", pp.y: " + pp.y);
        return ((this.pinX == pp.x)
                &&
                (this.pinY == pp.y));
    }

    public String toString() {
        return "this.x: " + this.x
                + ", this.y: " + this.y;
    }
}
