import tester.*;
import javalib.worldimages.Posn;

import java.lang.reflect.Array;
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

    public void testPlayFieldReplace(Tester t) {
        int x = Main.rand.nextInt();
        int y = Main.rand.nextInt();
        int tt = Main.rand.nextInt(BlockType.values().length);

        Posn pos = new Posn(x, y);
        //  This block will be added to the playfield
        Block addedBlock = new Block(pos, BlockType.values()[tt]);
        Block emptBlock = new Block(pos, BlockType.EMT);

        //  What is inside this doesn't matter for test purposes
        ArrayList<Block> arPlayerPiece = new ArrayList<Block>(10);
        //  Ints also don't matter for testing purposes
        PlayerPiece playerPiece = new PlayerPiece(arPlayerPiece, x, x);


        // This is the playfield to check against
        // Make an ArrayList, add a non-empty block to it

        //  Fill this with empties
        ArrayList<Block> ar = new ArrayList<Block>(PlayField.playAreaWidth * PlayField.playAreaHeight);
        for (int ix = 0; ix <= PlayField.playAreaWidth; ix++) {
            for (int iy = 0; iy <= PlayField.playAreaHeight; iy++) {
                ar.add(
                        new Block(
                                new Posn(ix, iy),
                                BlockType.EMT));
            }
        }


        // This is the initial playfield
        PlayField pf1 = new PlayField(ar, playerPiece, 0);

        ArrayList<Block> ar2 = ar;
        ar2.remove(emptBlock);
        ar2.add(addedBlock);

        PlayField pf2 = new PlayField(ar2, playerPiece, 0);

        t.checkExpect(
                pf1.replace(addedBlock),
                pf2,
                "testPlayFieldReplace - replace a block");

    }


    public void testPlayFieldRemove(Tester t) {

        // Must be constrained to indices that are actually used
        int x = Main.rand.nextInt() % PlayField.playAreaWidth;
        int y = Main.rand.nextInt() % PlayField.playAreaHeight;
        Posn pos = new Posn(x, y);

        // Only nonempty types
        int tt = 1 + Main.rand.nextInt(BlockType.values().length - 1);

        Block somethingBlock = new Block(pos, BlockType.values()[tt]);
        Block emptyBlock = new Block(pos, BlockType.EMT);

        PlayField pfInit = new PlayField();

        //  Copy for comparison
        PlayField pf1 = pfInit;
        // Replace all empties with somethingBlocks
        for (int ix = 0; ix <= PlayField.playAreaWidth; ix++) {
            for (int iy = 0; iy <= PlayField.playAreaHeight; iy++) {
                pf1 = pf1.replace(somethingBlock);
            }
        }

        PlayField pf2 = pf1;
        // Replace all the somethingBlocks with empties
        for (int ix = 0; ix <= PlayField.playAreaWidth; ix++) {
            for (int iy = 0; iy <= PlayField.playAreaHeight; iy++) {
                pf2 = pf2.replace(emptyBlock);

                t.checkExpect(
                        pf1.remove(somethingBlock),
                        pf2,
                        "testPlayFieldRemove - add somethings, remove somethings");
            }
        }


    }

    /*
    public void testPlayFieldUpdateMatches(Tester t) {
        //  Fill this with empties
        ArrayList<Block> ar = new ArrayList<Block>(PlayField.playAreaWidth * PlayField.playAreaHeight);
        System.out.println("Filling field with EMT Blocks");
        for (int ix = 0; ix <= PlayField.playAreaWidth; ix++) {
            for (int iy = 0; iy <= PlayField.playAreaHeight; iy++) {
                // TODO: Check the i and j values
                //System.out.println("ix: " + ix + ", iy: " + iy);
                this.field.add(
                        new Block(
                                // TODO: All uses of Block constructor need to pass an index
                                new Posn(ix, iy),
                                BlockType.EMT));
            }
        }

        //  Change 0 later
        int rand = 2;
        //  Add a match spanning ALL of the game
        for (int ix = 0; ix <= PlayField.playAreaWidth; ix++) {
            int horiz = ix;
            int ind = -1;
            boolean emptyFound = false;
            Posn psn = new Posn(horiz, rand);
            Block bl = new Block(psn, BlockType.values()[rand]);

            //  Get the empty block at X/Y in the array
            for (Block b : ar) {
                if (b.isSamePosn(psn)) {
                    emptyFound = true;
                    ind = ar.indexOf(b);
                }
            }

            if (emptyFound) {
                //  Remove the empty block from the array
                ar.remove(ar.get(ind));
            } else {
                //  Add the match block
                ar.add(bl);
            }
        }
        //  Make the matchedPlayField
        PlayField matchedPF1 = new PlayField(ar, 0);

        ArrayList<Block> exp;
        // One match found- emptied block AND +1 point
        PlayField expectPF2 = new PlayField();
        t.checkExpect(
                matchedPF1.updateMatches(),
                expectPF2,
                "testUpdateMatches - horizontal matches found");

    }
    */

}
