import javalib.worldimages.Posn;

public class Tester {
    // This class will be used to generate and run tests.
    public Tester() {

    }

    //  This method will run the tests
    public boolean testsPassedHuh() {
        // TODO: Devise tests, write tests...
        // This function will return false if the tests fail
        // Each test should be functional
        // outputs can be verified with code— fully automated testing without
        // relying on side-effects
        BlockPosnEquals();

        System.out.println("----------  TESTING COMPLETE!  ----------");
        return true;
    }


    public void BlockPosnEquals() {
        Block testBlock1 = new Block(new Posn(100, 100), BlockType.A);
        Block testBlock2 = new Block(new Posn(100, 100), BlockType.A);
        System.out.println("Should be true || " + "BlockPosnEquals -> " + testBlock1.equals(testBlock1));

        Block testBlock3 = new Block(new Posn(100, 100), BlockType.A);
        Block testBlock4 = new Block(new Posn(100, 100), BlockType.B);
        System.out.println("Should be false || " + "BlockPosnEquals -> " + testBlock3.equals(testBlock4));

    }


}
