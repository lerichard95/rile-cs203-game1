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
        System.out.println("PosnEquals returns " + PosnEquals() + " Should be true");

        System.out.println("----------  TESTING COMPLETE!  ----------");
        return true;
    }


    public boolean PosnEquals() {
        Posn testPosn1 = new Posn(100, 100);
        Posn testPosn2 = new Posn(100, 100);
        return testPosn1.equals(testPosn2);


    }

}
