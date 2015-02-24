//Do you need to import these to use these .jar libraries?
import javalib.funworld.*;
import javalib.worldimages.*;

//G1 represents the current state of world... Maybe it should extend 
//javalib.funworld.World??? 

public class G1 extends javalib.funworld.World{
	int score = 0;
	boolean winState = false;
	
	
	
	/* Main method */
	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		
	}

	
	//Need to research this
	@Override
	public WorldImage makeImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
