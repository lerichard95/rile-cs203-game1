
#Brainstorming

##CURRENT TASK:
- Write test for playFieldUpdateMatches
[x] Design test for PlayField.longestSameColor
--- test is flawed?? Why does Posn (0,5) not appear in the PlayField?...
- Implement longestSameColor()

- Implement updateMatches()

===

[x] If all functions should be pure, how do I change the state of playField in ColumnsWorld.onTick()??

[x] If I am returning new states of PlayField, then should all constructors for each state item (like PlayerPiece) update the state?

[ ] Use ColumnsWorld constructor to build a new ColumnsWorld for each function?

[ NO ] Do I need to test EVERY function?... NOT constructors, not toString?

[ ] How do I know if a test is sensible? Which properties of an object should be tested?

[ ] Should I only test the fields/variables that are relevant/referred to in the function?

RANDOM TESTING: random is approximation of "for all"

[ ]

[ ] Do I need to test onTick itself? Or is it acceptable to just test the functions inside of it?... Possibly difficult to recreate states to compare
---- Argue if it is worth testing onTick...

[ ]


What is a property?
PROPERTIES ARE RULES- see if the functions obey the mechanics of the game?

Test the most complicated thing first???

Arguing if tests are necessary-
can generalize

DONT TEST CONSTRUCTORS BECAUSE logic doesn't make sense to do that

#TEST CHECKLIST
Functions to run tests on

##ColumnsWorld

##Block
[x] clear() - exhaustive
[x] isSamePosn() - exhaustive
[x] isSameType() - exhaustive, random
[x] isEmpty() - exhaustive
[x] equals() - exhaustive, random

#Pinhole
[x] Pinhole()
[x] samePosn() - Random, sufficient for testing the constructor
because the test uses the constructor.

##PlayField

[x] getAtXY() - exhaustive
[x] replace() - uses random
[x] remove() - uses random
####game mechanics functions
[] longestSameColor()
Block -> List<Block>

[] updateMatches()
PlayField (implicit) -> PlayField

[] playerLanding()
[] movePlayerRight()
[] movePlayerLeft()

// What should quickDrop return anyway???? A new PlayField?
[] quickDrop()
[] findNearestY()

##PlayerPiece

[] cycleUp()
[] cycleDown()

#Useful notes
===

#tester library docs:
types of reports to run:
http://www.ccs.neu.edu/javalib/Tester/Running_Tests.html




http://docs.oracle.com/javase/tutorial/java/data/characters.html
Escape Sequences
Escape Sequence Description
\t  Insert a tab in the text at this point.
\b  Insert a backspace in the text at this point.
\n  Insert a newline in the text at this point.
\r  Insert a carriage return in the text at this point.
\f  Insert a formfeed in the text at this point.
\'  Insert a single quote character in the text at this point.
\"  Insert a double quote character in the text at this point.
\\  Insert a backslash character in the text at this point.
