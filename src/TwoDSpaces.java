import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

public interface TwoDSpaces {
	// Use an interface to allow for a different implementation of this
	// data structure— more upgradability

	// TwoDSpaces is used to represent sets of blocks stored
	// in a 2D matrix.

    // TODO: Does it matter what the params are for an interface?? Can I just leave params blank?
	// Retrieve the block at a current XY
	public Block getAtXY(Posn p);

	// Get properties about a TwoDSpace
	public int width();
	public int height();

   	public WorldImage draw();

    // TODO: Put all relevant functions from PlayField into this interface
	
}
