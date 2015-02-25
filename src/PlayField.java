public class PlayField implements TwoDSpaces {
    // Rectangular

    // implementation of the playing field

    // A 2d array of blocks
    int width;
    int height;

    Block[][] field;

    public PlayField(int w, int h) {
        this.width = w;
        this.height = h;
        field = new Block[width][height];
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

        }
        return new PlayField(width, height);


    }


    public Block getXY(int x, int y) {
        return field[x][y];
    }

    // Where should collision checking be done?

}