import tester.*;
import javalib.worldimages.Posn;

import java.util.ArrayList;


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
            //  idea of implication... don't consider q if p isn't true
            //  p -> q
            for (int p = 0; p < BlockType.values().length; p++) {
                for (int j = 0; j < BlockType.values().length; j++) {
                    Block testBlock3 = new Block(new Posn(i, i), BlockType.values()[p]);
                    Block testBlock4 = new Block(new Posn(i, i), BlockType.values()[j]);
                    if (p != j) {
                        t.checkExpect(
                                testBlock3.equals(testBlock4),
                                false,
                                "testBlockEquals - Different BlockTypes"
                        );
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

    public void testBlockIsEmpty(Tester t) {

        //  TODO: testing: Use a random number to represent that property works "forall"
        for (int i = 0; i <= 1000; i++) {
            int rand = Main.rand.nextInt();
            Block testBlock1 = new Block(new Posn(rand, rand), BlockType.EMT);
            t.checkExpect(testBlock1.isEmpty(), true, "testBlockIsEmpty - BlockType.EMT");

            // Test for all other BlockTypes
            for (int p = 1; p < BlockType.values().length; p++) {
                Block testBlock2 = new Block(new Posn(rand, rand), BlockType.values()[p]);
                t.checkExpect(testBlock2.isEmpty(), false, "testBlockIsEmpty - OTHER, " + BlockType.values()[p]);
            }
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
            // TODO: Replace 0's with randoms, since posns don't matter
            int a = Main.rand.nextInt();
            int b = Main.rand.nextInt();
            int c = Main.rand.nextInt();
            int d = Main.rand.nextInt();


            Block testBlock1 = new Block(new Posn(a, b), BlockType.values()[p]);
            Block testBlock2 = new Block(new Posn(c, d), BlockType.values()[p]);
            t.checkExpect(
                    testBlock1.isSameType(testBlock2),
                    true,
                    "testBlockIsSameType - Same BlockTypes"
            );
        }


        //  TODO: How do you set up tests to guarantee different values?

        for (int p = 0; p < BlockType.values().length; p++) {
            for (int j = 1; j < BlockType.values().length; j++) {
                if (p != j) {
                    int aa = Main.rand.nextInt();
                    int bb = Main.rand.nextInt();
                    int cc = Main.rand.nextInt();
                    int dd = Main.rand.nextInt();

                    Block testBlock3 = new Block(new Posn(aa, bb), BlockType.values()[p]);
                    Block testBlock4 = new Block(new Posn(cc, dd), BlockType.values()[j]);
                    t.checkExpect(
                            testBlock3.isSameType(testBlock4),
                            false,
                            "testBlockIsSameType - Different BlockTypes"
                    );
                }
            }
        }


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

    public void testPinholeSameValues(Tester t) {
        int a = Main.rand.nextInt();
        int b = Main.rand.nextInt();
        Posn testPosn1 = new Posn(a, b);
        //  Convert the index to a pixel corner
        int aa = a * ColumnsWorld.BLOCK_SIZE;
        int bb = b * ColumnsWorld.BLOCK_SIZE;
        Posn testPosn2 = new Posn(aa, bb);
        //  Convert the pixel corner to a pinhole
        int aaa = aa + (ColumnsWorld.BLOCK_SIZE / 2);
        int bbb = bb + (ColumnsWorld.BLOCK_SIZE / 2);
        Posn testPosn3 = new Posn(aaa, bbb);

        Pinhole testPin1 = new Pinhole(testPosn2);
        t.checkExpect(
                testPin1.sameValues(testPosn3),
                true,
                "Pinhole and a Posn - Same values"
        );

        //  Pinhole and a Posn - Different values

        //  Pinhole and a Pinhole - Same values

        //  Pinhole and a Pinhole - Different values


    }

    
    public void testPlayFieldUpdateMatches(Tester t) {
        ArrayList<Block> ar;
        int rand = 0;
        //  Add a three-pair
        for (int i = 0; i <= 2; i++) {
            //  Change 0 later
            int horiz = i % (ColumnsWorld.PLAY_COLUMNS - 1);
            Posn psn = new Posn(horiz, 0);
            Block bl = new Block(psn ,BlockType.values()[rand]);
            ar.add(bl);
        }

        PlayField testPF1 = new PlayField(ar, 0);

        ArrayList<Block> exp;
        // One match found- emptied block AND +1 point
        PlayField expectPF2 = new PlayField(exp ,1);

    }



}
