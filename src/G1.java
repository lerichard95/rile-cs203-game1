//  G1 represents the current state of world... Maybe it should extend
//  javalib.funworld.World???

public class G1 extends javalib.funworld.World {
    int score = 0;
    boolean winState = false;


    /* Main method */
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Tester tests = new Tester();
        System.out.println(tests.testsPassedHuh());

        //  Big-bang - starts on-tick etc

        //  on-tick - returns world

        //  on-key - returns world

    }


    //Need to research this
    @Override
    public WorldImage makeImage() {
        // TODO Auto-generated method stub
        // Returns a picture of the game

    }

}
