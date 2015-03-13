import tester.*;
import javalib.worldimages.Posn;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ColumnsExamples {

    public void testBlockEquals(Tester t) {
        for (int i = 0; i <= 100; i++) {
            int a = Main.rand.nextInt();
            int b = Main.rand.nextInt();

            //  Test for all BlockTypes
            for (int p = 0; p < BlockType.values().length; p++) {
                Block testBlock1 = new Block(new Posn(a, b), BlockType.values()[p]);
                Block testBlock2 = new Block(new Posn(a, b), BlockType.values()[p]);
                t.checkExpect(
                        testBlock1.equals(testBlock2),
                        true
                        , "BlockPosnEquals, same Posn, same BlockType");
            }

            //  TODO: How do you set up tests to guarantee different values?
            //  idea of implication... don't consider q if p isn't true
            //  p -> q
            for (int p = 0; p < BlockType.values().length; p++) {
                for (int j = 0; j < BlockType.values().length; j++) {
                    int aa = Main.rand.nextInt();
                    int bb = Main.rand.nextInt();
                    Block testBlock3 = new Block(new Posn(aa, bb), BlockType.values()[p]);
                    Block testBlock4 = new Block(new Posn(bb, aa), BlockType.values()[j]);
                    if (p != j) {
                        t.checkExpect(
                                testBlock3.equals(testBlock4),
                                false,
                                "testBlockEquals - Different Posn, Different BlockType"
                        );
                    }
                }
            }

            for (int p = 0; p < BlockType.values().length; p++) {
                int aa = Main.rand.nextInt();
                int bb = Main.rand.nextInt();
                Block testBlock3 = new Block(new Posn(aa, bb), BlockType.values()[p]);
                Block testBlock4 = new Block(new Posn(bb, aa), BlockType.values()[p]);
                t.checkExpect(
                        testBlock3.equals(testBlock4),
                        false,
                        "testBlockEquals - Different Posns, Same BlockTypes"
                );
            }
        }
    }

    public void testBlockClear(Tester t) {

        // Exhaustive testing
        for (int i = 0; i <= 50; i++) {
            for (int yy = 0; yy <= 50; yy++) {
                int randType = Main.rand.nextInt(BlockType.values().length);
                Block testBlock1 = new Block(new Posn(i, yy), BlockType.values()[randType]);
                t.checkExpect(
                        testBlock1.clear(new Posn(i, yy)),
                        new Block(new Posn(i, yy),
                                BlockType.EMT),
                        "Block.clear()");
            }

        }

    }

    public void CtestBlockIsEmpty(Tester t) {

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

        for (int i = 0; i < 50; i++) {
            for (int yy = 0; yy < 50; yy++) {
                int randType = Main.rand.nextInt(BlockType.values().length);
                Block testBlock1 = new Block(new Posn(i, yy), BlockType.values()[randType]);
                Block testBlock2 = new Block(new Posn(i, yy), BlockType.values()[randType]);
                t.checkExpect(testBlock1.isSamePosn(testBlock2.posn()),
                        true,
                        "testBlockIsSamePosn - Same Posn Blocks");
            }
        }

        for (int i = 100; i < 100; i++) {
            int a = Main.rand.nextInt();
            int b = Main.rand.nextInt();
            int c = Main.rand.nextInt();
            int d = Main.rand.nextInt();
            Posn pos1 = new Posn(a, b);
            Posn pos2 = new Posn(c, d);

            Block testBlock2 = new Block(pos1, BlockType.EMT);
            Block testBlock3 = new Block(pos2, BlockType.EMT);
            if (!pos1.equals(pos2)) {
                t.checkExpect(testBlock2.isSamePosn(testBlock3.posn()),
                        false,
                        "testBlockIsSamePosn - Different Posn");
            }
        }
    }

    public void testBlockIsSameType(Tester t) {
        for (int p = 0; p < BlockType.values().length; p++) {
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

        for (int p = 0; p < BlockType.values().length; p++) {
            for (int j = 0; j < BlockType.values().length; j++) {
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

        // Make an ArrayList with random typed blocks
        ArrayList<Block> field2 = new ArrayList<Block>(PlayField.playArea);
        for (int xx = 0; xx < fieldTest1.playAreaWidth; xx++) {
            for (int yy = 0; yy < fieldTest1.playAreaHeight; yy++) {
                int randType = Main.rand.nextInt(BlockType.values().length);
                Posn pos = new Posn(xx, yy);
                Block bb = new Block(pos, BlockType.values()[randType]);
                field2.add(bb);
            }
        }
        // Copy the ArrayList...
        ArrayList<Block> field3 = field2;
        //  What is inside this doesn't matter for test purposes
        ArrayList<Block> arPlayerPiece = new ArrayList<Block>(10);
        //  Ints also don't matter for testing purposes
        int randInt = Main.rand.nextInt();
        PlayerPiece piece = new PlayerPiece(arPlayerPiece, randInt, randInt);
        int score = 0;

        // Make a playfield from the array
        PlayField testField2 = new PlayField(field2, piece, score);

        // Retrieve blocks from the PlayField and check against the blocks from the array
        for (Block bx : field2) {
            t.checkExpect(
                    testField2.getAtXY(bx.posn()),
                    bx,
                    "testPlayFieldGetAtXY - random BlockType");
        }
    }

    public void testPinholeSameValues(Tester t) {
        for (int xt = 0; xt <= 100; xt++) {
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
        }
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
        for (int o = 0; o <= 10; o++) {
            // Must be constrained to indices that are actually used
            int x = Main.rand.nextInt(PlayField.playAreaWidth);
            int y = Main.rand.nextInt(PlayField.playAreaHeight);
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
    }

    /*
    //  TODO: write test for PlayField.longestSameColor
    public void testPlayFieldLongestSameColor(Tester t) {
        // TODO: Issue- don't forget the empty blocks- use constructor
        //  ISSUE: Why can't Posn (0,5) be found???

        PlayField pf1 = new PlayField();

        //  Make a list to compare
        ArrayList<Block> listMatches = new ArrayList<Block>();

        //  playerPiece doesn't matter
        PlayerPiece piece = new PlayerPiece(listMatches, 0, 0);

        // Make an empty list
        ArrayList<Block> list = new ArrayList<Block>(PlayField.playArea);
        int randType = 1;

        // Fill list with matches for an entire row
        for (int changeY = 0; changeY <= PlayField.playAreaWidth; changeY++) {
            //  update 0 to be a yy to loop through all columns
            Posn psn = new Posn(0, changeY);
            Block bl = new Block(psn, BlockType.values()[randType]);
            listMatches.add(bl);
            list.add(bl);
        }
        //  Add the list of Blocks into the PlayField
        for (Block bb : listMatches) {
            pf1 = pf1.replace(bb);
        }

        //  Run test for a block, check against the list of Blocks with a horizontal match

        //  Initialize with a sentinel block for previous
        Posn sentPos = new Posn(-1, -1);
        Block sentBlock = new Block(sentPos, BlockType.EMT);

        //  Initialize with an empty accumulator
        ArrayList<Block> accum = new ArrayList<Block>();

        t.checkExpect(
                //  TODO: change the input block to a random one in the list - because it doesn't matter
                pf1.longestSameColor(listMatches.get(0), sentBlock, accum),
                list,
                "testPlayFieldLongestSameColor - horizontal row of matches"
        );

    }

    */

    /*
    public void testPlayFieldUpdateMatches(Tester t) {
        // Test for all rows
        for (int yy = 0; yy <= PlayField.playAreaHeight; yy++) {
            // Make an empty PlayField
            PlayField pf1 = new PlayField();
            PlayField pf2 = pf1;
            int randType = 1;

            // Fill playfield with matches for an entire row
            for (int horizMatch = 0; horizMatch <= PlayField.playAreaWidth; horizMatch++) {
                Posn psn = new Posn(horizMatch, yy);
                Block bl = new Block(psn, BlockType.values()[randType]);
                pf1 = pf1.replace(bl);
            }
            // Run test
            t.checkExpect(
                    pf1.updateMatches(),
                    pf2,
                    "testPlayFieldUpdateMatches - entire horiz row"
            );
        }


    }
    */


}
