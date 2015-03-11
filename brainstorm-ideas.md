#Brainstorming

[x] If all functions should be pure, how do I change the state of playField in ColumnsWorld.onTick()??

[x] If I am returning new states of PlayField, then should all constructors for each state item (like PlayerPiece) update the state?

[ ] Use ColumnsWorld constructor to build a new ColumnsWorld for each function?

[ ] Do I need to test EVERY function?... Even constructors, toString?

[ ] How do I know if a test is sensible? Which properties of an object should be tested?

[ ] Should I only test the fields/variables that are relevant/referred to in the function?

[ ]

[ ] Do I need to test onTick itself? Or is it acceptable to just test the functions inside of it?... Possibly difficult to recreate states to compare

[ ]


What is a property?
PROPERTIES ARE RULES- see if the functions obey the mechanics of the game?

Test the most complicated thing first???

#TEST CHECKLIST
Functions to run tests on
##Block
[] Block()
Enums are hard to test??
[x] clear() - exhaustive
[~] equals() - exhaustive
[x] isEmpty() - exhaustive
[x] isSamePosn() - exhaustive
[~] isSameType() - ??

##ColumnsWorld
[] ColumnsWorld()

##PlayerPiece
[] PlayerPiece
[] cycleUp()
[] cycleDown()

##PlayField
[] PlayField()
[] getAtXY()

###game mechanics functions
[] updateMatches()
[] playerLanding()
[] updateMatches()
[] movePlayerRight()
[] movePlayerLeft()

// What should quickDrop return anyway???? A new PlayField?
[] quickDrop()

#Pinhole
[] Pinhole()

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
