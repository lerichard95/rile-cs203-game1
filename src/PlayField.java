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
        field = new ArrayList<Block>( w * h );
        for (int i = 0; i <= 0; i++) {
            field.add(new Block(0));
        }
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public PlayField empty(int width, int height) {
        // for loop: fill the array with empty blocks
        for (int i = 0; i <= 0; i++) {
            field.add(new Block(0));
        }
        return new PlayField(width, height);


    }


    //Gets a block at the specified X Y position
    public Block getAtXY(int x, int y) {


    }

    // Where should collision checking be done?

}