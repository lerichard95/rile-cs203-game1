[x] If all functions should be pure, how do I change the state of playField in ColumnsWorld.onTick()??

[x] If I am returning new states of PlayField, then should all constructors for each state item (like PlayerPiece) update the state? 


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
[] PlayField
[] getAtXY()

#Pinhole
[] Pinhole() 

#Useful notes
==
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
