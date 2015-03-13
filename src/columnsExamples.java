import tester.*;
import javalib.worldimages.Posn;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ColumnsExamples {

    public void testBlockEquals(Tester t) {
        for (int i = 0; i <= 50; i++) {
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

        for (int i = 50; i < 50; i++) {
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
        for (int xx = 0; xx < fieldTest1.MAX_WIDTH_INDEX; xx++) {
            for (int yy = 0; yy < fieldTest1.MAX_HEIGHT_INDEX; yy++) {

                Posn pos = new Posn(xx, yy);
                Block bb = new Block(pos, BlockType.EMT);
                t.checkExpect(fieldTest1.getAtXY(pos),
                        bb,
                        "testPlayFieldGetAtXY - with EMT blocks");
            }
        }

        for (int sp = 0; sp < 50; sp++) {
            // Make an ArrayList with random typed blocks
            ArrayList<Block> field2 = new ArrayList<Block>(PlayField.playArea);
            for (int xx = 0; xx < fieldTest1.MAX_WIDTH_INDEX; xx++) {
                for (int yy = 0; yy < fieldTest1.MAX_HEIGHT_INDEX; yy++) {
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
    }

    public void testPinholeSameValues(Tester t) {
        for (int xt = 0; xt <= 50; xt++) {
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
        for (int asp = 0; asp <= 50; asp++) {
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
            ArrayList<Block> ar = new ArrayList<Block>(PlayField.MAX_WIDTH_INDEX * PlayField.MAX_HEIGHT_INDEX);
            for (int ix = 0; ix <= PlayField.MAX_WIDTH_INDEX; ix++) {
                for (int iy = 0; iy <= PlayField.MAX_HEIGHT_INDEX; iy++) {
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
    }


    public void testPlayFieldRemove(Tester t) {
        for (int o = 0; o <= 50; o++) {
            // Must be constrained to indices that are actually used
            int x = Main.rand.nextInt(PlayField.MAX_WIDTH_INDEX);
            int y = Main.rand.nextInt(PlayField.MAX_HEIGHT_INDEX);
            Posn pos = new Posn(x, y);

            // Only nonempty types
            int tt = 1 + Main.rand.nextInt(BlockType.values().length - 1);

            Block somethingBlock = new Block(pos, BlockType.values()[tt]);
            Block emptyBlock = new Block(pos, BlockType.EMT);

            PlayField pfInit = new PlayField();

            //  Copy for comparison
            PlayField pf1 = pfInit;
            // Replace all empties with somethingBlocks
            for (int ix = 0; ix <= PlayField.MAX_WIDTH_INDEX; ix++) {
                for (int iy = 0; iy <= PlayField.MAX_HEIGHT_INDEX; iy++) {
                    pf1 = pf1.replace(somethingBlock);
                }
            }

            PlayField pf2 = pf1;
            // Replace all the somethingBlocks with empties
            for (int ix = 0; ix <= PlayField.MAX_WIDTH_INDEX; ix++) {
                for (int iy = 0; iy <= PlayField.MAX_HEIGHT_INDEX; iy++) {
                    pf2 = pf2.replace(emptyBlock);

                    t.checkExpect(
                            pf1.remove(somethingBlock),
                            pf2,
                            "testPlayFieldRemove - add somethings, remove somethings");
                }
            }

        }
    }


    //  TODO: write test for PlayField.longestSameColor
    public void testPlayFieldLongestSameColor(Tester t) {
        //  TODO: Reduce number of nested calls so that there won't be a STACK OVERFLOW
        int changeY = Main.rand.nextInt(2);
        // matchLength can be from 0 to MAX_WIDTH_INDEX
        int matchLength = Main.rand.nextInt(PlayField.MAX_WIDTH_INDEX);
        PlayField pf1 = new PlayField();

        // Make an empty list
        ArrayList<Block> list = new ArrayList<Block>(ColumnsWorld.PLAY_COLUMNS);

        //  playerPiece doesn't matter
        PlayerPiece piece = new PlayerPiece(list, 0, 0);
        // Only non-empty types...
        int randType = Main.rand.nextInt(1 + (BlockType.values().length - 1));

        // Fill list with matches for an entire row
        for (int changeX = 0; changeX <= matchLength; changeX++) {
            //  update 0 to be a yy to loop through all columns
            Posn psn = new Posn(changeX, changeY);
            System.out.println("filling lists... psn.x: " + psn.x + ", psn.y: " + psn.y);
            Block bl = new Block(psn, BlockType.values()[randType]);
            list.add(bl);
        }

        //  Add the list of Blocks into the PlayField
        for (Block bb : list) {
            System.out.println("adding to list... bb.posn().x: " + bb.posn().x + ", bb.posn().y: " + bb.posn().y);
            pf1 = pf1.replace(bb);
        }

        //  Run test for a block, check against the list of Blocks with a horizontal match
        //  Initialize this as block representing the previous
        Posn sentPos = new Posn(-1, -1);

        //  Initialize with an empty accumulator
        ArrayList<Block> accum = new ArrayList<Block>();
        int randArrIndex = Main.rand.nextInt(list.size());

        /*for (Block obb : list) {
            System.out.println("obb: " + obb.posn().x + ", " + obb.posn().y);
            System.out.println("is it in the list?? " + pf1.longestSameColor(list.get(randArrIndex), sentBlock, accum).contains(obb));
            t.checkExpect(.contains(obb),
                    true,
                    "pf1.longestSameColor - item");
        }*/

        ArrayList<Block> outputList = pf1.longestSameColor(list.get(randArrIndex), list.get(randArrIndex), accum);
        for (Block bb : list) {
            t.checkExpect(
                    outputList.contains(bb),
                    true,
                    "pf1.longestSameColor - check each list item");
        }

    }

    public void testPlayerPieceMoveLeft(Tester t) {
        for (int initX = -PlayField.MAX_WIDTH_INDEX; initX <= PlayField.MAX_WIDTH_INDEX; initX++) {
            // PlayerPiece -> PlayerPiece
            int initPPX = initX;
            int initPPY = Main.rand.nextInt();

            // Create an initial state for the playerPiece...
            PlayerPiece playerPieceInit = new PlayerPiece(initPPX);
            PlayerPiece playerPieceInit2 = new PlayerPiece(playerPieceInit.player, initPPX, initPPY);

            //  Make an ArrayList for the changed indices
            ArrayList<Block> playerMoved = playerPieceInit2.player;

            // Make a PlayerPiece out of the ArrayList, within bounds
            PlayerPiece playerPieceMoved;
            int newIndexX = initPPX;
            if (initPPX > 0) {
                newIndexX = initPPX - 1;
                for (Block bb : playerMoved) {
                    bb.posn().x = initPPX;
                }
                //  Make a playerPiece out of the shifted indices
                playerPieceMoved = new PlayerPiece(playerMoved, newIndexX, initPPY);
            } else {
                //  Replace the playerPiece, but don't shift index
                playerPieceMoved = new PlayerPiece(playerMoved, initPPX, initPPY);
            }

            //  Make a shifted playerPiece to compare
            t.checkExpect(
                    playerPieceInit2.moveLeft(),
                    playerPieceMoved,
                    "testPlayerPieceMoveLeft() - should work"
            );
        }

    }

    public void testPlayerPieceMoveRight(Tester t) {
        for (int initX = -PlayField.MAX_WIDTH_INDEX; initX <= PlayField.MAX_WIDTH_INDEX; initX++) {
            // PlayerPiece -> PlayerPiece
            int initPPX = initX;
            int initPPY = Main.rand.nextInt();

            // Create an initial state for the playerPiece...
            PlayerPiece playerPieceInit = new PlayerPiece(initPPX);
            PlayerPiece playerPieceInit2 = new PlayerPiece(playerPieceInit.player, initPPX, initPPY);

            //  Make an ArrayList for the changed indice
            ArrayList<Block> playerMoved = playerPieceInit2.player;

            // Make a PlayerPiece out of the ArrayList, within bounds
            PlayerPiece playerPieceMoved;
            int indexX = initPPX;
            if (initPPX < PlayField.MAX_WIDTH_INDEX) {
                indexX = initPPX + 1;
                for (Block bb : playerMoved) {
                    bb.posn().x = indexX;
                }
                //  Make a playerPiece out of the shifted indices
                playerPieceMoved = new PlayerPiece(playerMoved, indexX, initPPY);
            } else {
                //  Replace the playerPiece, but don't shift index
                playerPieceMoved = new PlayerPiece(playerMoved, initPPX, initPPY);
            }

            //  Make a shifted playerPiece to compare
            t.checkExpect(
                    playerPieceInit2.moveRight(),
                    playerPieceMoved,
                    "testPlayerPieceMoveRight() - should work"
            );
        }
    }

    public void testPlayFieldMovePlayerLeft(Tester t) {
        for (int i = (PlayField.MAX_WIDTH_INDEX * -1); i <= PlayField.MAX_WIDTH_INDEX; i++) {
            int initPPX = i;
            int initScore = Main.rand.nextInt();
            // Make an initial PlayerPiece
            PlayerPiece initPiece = new PlayerPiece(initPPX);

            // Use the PlayerPiece to make an initial PlayField
            ArrayList<Block> field = new ArrayList<Block>(PlayField.playArea);
            //  For loop; fill the field with empty Blocks
            //System.out.println("Filling field with EMT Blocks");
            for (int ix = 0; ix <= PlayField.MAX_WIDTH_INDEX; ix++) {
                for (int iy = 0; iy <= PlayField.MAX_HEIGHT_INDEX; iy++) {
                    //System.out.println("ix: " + ix + ", iy: " + iy);
                    field.add(
                            new Block(
                                    new Posn(ix, iy),
                                    BlockType.EMT));
                }
            }
            PlayField initPf = new PlayField(field, initPiece, initScore);

            // Make an expected PlayerPiece from the initial PlayerPiece
            PlayerPiece expectPlayPiece = initPiece.moveLeft();

            // Use the expected PlayerPiece to make a comparison PlayField
            PlayField expectPf = new PlayField(field, expectPlayPiece, initScore);

            // Run the test to check
            t.checkExpect(
                    initPf.movePlayerLeft(),
                    expectPf,
                    "testPlayFieldMovePlayerLeft() - Returns a correct PlayField"
            );
        }
    }


    public void testPlayFieldMovePlayerRight(Tester t) {
        for (int i = 0; i <= (PlayField.MAX_WIDTH_INDEX + 5); i++) {
            int initPPX = i;
            int initScore = Main.rand.nextInt();
            // Make an initial PlayerPiece
            PlayerPiece initPiece = new PlayerPiece(initPPX);

            // Use the PlayerPiece to make an initial PlayField
            ArrayList<Block> field = new ArrayList<Block>(PlayField.playArea);
            //  For loop; fill the field with empty Blocks
            //System.out.println("Filling field with EMT Blocks");
            for (int ix = 0; ix <= PlayField.MAX_WIDTH_INDEX; ix++) {
                for (int iy = 0; iy <= PlayField.MAX_HEIGHT_INDEX; iy++) {
                    //System.out.println("ix: " + ix + ", iy: " + iy);
                    field.add(
                            new Block(
                                    new Posn(ix, iy),
                                    BlockType.EMT));
                }
            }
            PlayField initPf = new PlayField(field, initPiece, initScore);

            // Make an expected PlayerPiece from the initial PlayerPiece
            PlayerPiece expectPlayPiece = initPiece.moveRight();

            // Use the expected PlayerPiece to make a comparison PlayField
            PlayField expectPf = new PlayField(field, expectPlayPiece, initScore);

            // Run the test to check
            t.checkExpect(
                    initPf.movePlayerRight(),
                    expectPf,
                    "testPlayFieldMovePlayerRight() - Returns a correct PlayField"
            );
        }
    }


    public void testPlayerPieceUpdatePlayerGravity(Tester t) {
        // Make an initPlayerPiece
        int initXX = Main.rand.nextInt();
        PlayerPiece initPlayerPiece = new PlayerPiece(initXX);

        // Make an expectedPlayerPiece from the initPlayerPiece
        PlayerPiece outPlayerPiece;
        // Build the expected ArrayList
        ArrayList<Block> expectArr = initPlayerPiece.player;

        if (initPlayerPiece.indexY < PlayField.MAX_HEIGHT_INDEX) {
            int newIndexY = initPlayerPiece.indexY + 1;
            for (Block bb : expectArr) {
                bb.posn().y = newIndexY;
            }
            outPlayerPiece = new PlayerPiece(expectArr, initXX, newIndexY);
        } else {
            outPlayerPiece = initPlayerPiece;
        }


        // Run the function on the initPlayerPiece, expect the expectedPlayerPiece
        t.checkExpect(
                initPlayerPiece.updatePlayerGravity(),
                outPlayerPiece,
                "testPlayerPieceUpdatePlayerGravity - should work"
        );

    }


    public void testPlayFieldUpdateGravity(Tester t) {
        // Make an initial PlayField
        int initScore = Main.rand.nextInt();
        int initPPX = Main.rand.nextInt(PlayField.MAX_WIDTH_INDEX);

        PlayField initPf = new PlayField();
        PlayerPiece initPiece = new PlayerPiece(initPPX);
        initPf = new PlayField(initPf.field, initPf.playerPiece, initScore);

        // update gravity for the player, make expectedPlayField
        ArrayList<Block> expectField = initPf.field;
        PlayField expectPf = new PlayField(initPf.field, initPf.playerPiece.updatePlayerGravity(), initScore);

        // Loop through every non-empty block in PlayField, check if there is anything below...
        // If there is something below, replace the below block with the current block, then clear the current block
        for (Block bb : expectField) {
            // Only check non-empty blocks!
            if (!bb.type().equals(BlockType.EMT)) {
                Posn belowPos = new Posn(bb.posn().x, bb.posn().y + 1);
                Block belowBlock = expectPf.getAtXY(belowPos);
                if (belowBlock.type().equals(BlockType.EMT)) {
                    Block newBlock = new Block(belowPos, bb.type());
                    //  Clear the current Posn
                    expectPf = expectPf.replace(bb.clear(bb.posn()));
                    //  Update the below Posn with the new block
                    expectPf = expectPf.replace(newBlock);
                }
            }
        }

        // Test: run function on the initial, check against expected PlayField.
        t.checkExpect(
                initPf.updateGravity(),
                expectPf,
                "testPlayFieldUpdateGravity - should work"
        );


    }

    /*
    public void testPlayFieldUpdateMatches(Tester t) {
        // Test for all rows
        for (int yy = 0; yy <= PlayField.MAX_HEIGHT_INDEX; yy++) {
            // Make an empty PlayField
            PlayField pf1 = new PlayField();
            PlayField pf2 = pf1;
            int randType = 1;

            // Fill playfield with matches for an entire row
            for (int horizMatch = 0; horizMatch <= PlayField.MAX_WIDTH_INDEX; horizMatch++) {
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
