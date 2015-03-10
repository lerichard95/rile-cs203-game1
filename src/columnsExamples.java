import tester.*;
import javalib.worldimages.Posn;


public class columnsExamples {

    public void testBlockEquals(Tester t) {

        for (int i = 0; i <= 100; i++) {
            //  Test for all BlockTypes
            for (int p = 0; p < BlockType.values().length; p++) {
                Block testBlock1 = new Block(new Posn(i, i), BlockType.values()[p]);
                Block testBlock2 = new Block(new Posn(i, i), BlockType.values()[p]);
                t.checkExpect(
                        testBlock1.equals(testBlock2),
                        true
                        , "BlockPosnEquals, same BlockType");
                
                for (int pp = 0; pp < BlockType.values().length; pp++) {
                //  TODO: How do I test for combinations that should ALWAYS be different?
                    for (int pp2 = 0; pp2 < BlockType.values().length; pp2++) {
                        while (pp == pp2) {
                            pp2 = (pp2 + 1) % BlockType.values().length;
                        }
                        Block testBlock3 = new Block(new Posn(i, i), BlockType.values()[pp]);
                        Block testBlock4 = new Block(new Posn(i, i), BlockType.values()[pp2]);
                        t.checkExpect(
                                testBlock3.equals(testBlock4),
                                false
                                , "BlockPosnEquals, different BlockTypes");
                    }
                }
            }
        }
    }

    public void testBlockClear(Tester t) {

        // Exhaustive testing
        for (int i = 0; i <= 1000; i++) {
            Block testBlock1 = new Block(new Posn(i, i), BlockType.A);
            t.checkExpect(testBlock1.clear(new Posn(i, i)),
                    new Block(new Posn(i, i),
                            BlockType.EMT),
                    "Block.clear()");
        }

    }


}
