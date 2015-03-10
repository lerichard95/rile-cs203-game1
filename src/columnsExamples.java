import tester.*;
import javalib.worldimages.Posn;


public class columnsExamples {

    public void testEquals(Tester t) {

        Block testBlock1 = new Block(new Posn(100, 100), BlockType.A);
        Block testBlock2 = new Block(new Posn(100, 100), BlockType.A);
        t.checkExpect(
                testBlock1.equals(testBlock2),
                true
                , "BlockPosnEquals 1 & 2");

        Block testBlock3 = new Block(new Posn(100, 100), BlockType.A);
        Block testBlock4 = new Block(new Posn(100, 100), BlockType.B);
        t.checkExpect(
                testBlock3.equals(testBlock4),
                false
                , "BlockPosnEquals 3 & 4");



    }


}
