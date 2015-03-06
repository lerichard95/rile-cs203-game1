import javalib.worldimages.Posn;

public interface TwoDSpaces {
	// Should this be an interface or a class??
	
	
	// Use an interface to allow for a different implementation of this
	// data structure— more upgradability
	
	// Possibly not rectangular
	
	// TwoDSpaces is used to represent sets of blocks stored
	// in a 2D matrix.


    // TODO: Does it matter what the params are for an interface?? Can I just leave params blank?
	// Retrieve the block at a current XY
	public Block getAtXY(Posn p);

	// Get properties about a TwoDSpace
	public int width();
	public int height();
	
	
}
