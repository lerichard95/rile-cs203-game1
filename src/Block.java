
public class Block{
	// Individual blocks that are "placed" inside of a 2D structure
	
	
	// Empty block is type 0
	// Active block types: 1 2 3 4
	int type = 0;
	
	// The coordinates for a block??...
	int x;
	int y;

	
	
	public Block(int t) {
		this.type = t;
	}
	
	//  Clears a Block by returning a block of type 0
	public Block clear(){
		return new Block(0);
	}

	public boolean compareType(int i) {
		return (type == i);
	}
	
	public boolean isEmpty(){
		return (type == 0);
	}
	
	
	
}