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
            }

            //  TODO: How do you set up tests to guarantee different values?
            /*for (int p = 0; p < BlockType.values().length; p++) {
                for (int j = 1; j < BlockType.values().length; j++) {
                    Block testBlock3 = new Block(new Posn(i, i), BlockType.values()[p]);
                    int sel = (p + j) % (BlockType.values().length - 1);
                    Block testBlock4 = new Block(new Posn(i, i), BlockType.values()[sel]);
                    t.checkExpect(
                            testBlock3.equals(testBlock4),
                            false,
                            "testBlockEquals - Different BlockTypes"
                    );
                }
            }*/

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

    public void testBlockIsEmpty(Tester t) {
        Block testBlock1 = new Block(new Posn(0, 0), BlockType.EMT);
        t.checkExpect(testBlock1.isEmpty(), true, "testBlockIsEmpty - BlockType.EMT");

        // Test for all other BlockTypes
        for (int p = 1; p < BlockType.values().length; p++) {
            Block testBlock2 = new Block(new Posn(0, 0), BlockType.values()[p]);
            t.checkExpect(testBlock2.isEmpty(), false, "testBlockIsEmpty - OTHER, " + BlockType.values()[p]);
        }
    }

    public void testBlockIsSamePosn(Tester t) {
        for (int i = 0; i < 1000; i++) {
            Block testBlock1 = new Block(new Posn(i, i), BlockType.EMT);
            Block testBlock2 = new Block(new Posn(i, i), BlockType.EMT);
            t.checkExpect(testBlock1.isSamePosn(testBlock2.posn()),
                    true,
                    "testBlockIsSamePosn - Same Posn Blocks");
        }
        for (int i = 0; i < 1000; i++) {
            Block testBlock2 = new Block(new Posn(i, i), BlockType.EMT);
            Block testBlock3 = new Block(new Posn(i + 1, i + 1), BlockType.EMT);
            t.checkExpect(testBlock2.isSamePosn(testBlock3.posn()),
                    false,
                    "testBlockIsSamePosn - Different Posn Blocks");
        }
    }

    public void testBlockIsSameType(Tester t) {
        for (int p = 0; p < BlockType.values().length; p++) {
            Block testBlock1 = new Block(new Posn(0, 0), BlockType.values()[p]);
            Block testBlock2 = new Block(new Posn(0, 0), BlockType.values()[p]);
            t.checkExpect(
                    testBlock1.isSameType(testBlock2),
                    true,
                    "testBlockIsSameType - Same BlockTypes"
            );
        }


        //  TODO: How do you set up tests to guarantee different values?
        /*
        for (int p = 0; p < BlockType.values().length; p++) {
            for (int j = 1; j < BlockType.values().length; j++) {
                Block testBlock3 = new Block(new Posn(0, 0), BlockType.values()[p]);
                int sel = (p + j) % (BlockType.values().length - 1);
                Block testBlock4 = new Block(new Posn(0, 0), BlockType.values()[sel]);
                t.checkExpect(
                        testBlock3.isSameType(testBlock4),
                        false,
                        "testBlockIsSameType - Different BlockTypes"
                );
            }
        }
        */

    }

    public void testPlayFieldGetAtXY(Tester t) {
        PlayField fieldTest1 = new PlayField();
        for (int xx = 0; xx < fieldTest1.playAreaWidth; xx++) {
            for (int yy = 0; yy < fieldTest1.playAreaHeight; yy++) {
                Posn pos = new Posn(xx, yy);
                Block bb = new Block(pos, BlockType.EMT);
                t.checkExpect(fieldTest1.getAtXY(pos),
                        bb,
                        "testPlayFieldGetAtXY - with EMT blocks");
            }

        }
    }

}
