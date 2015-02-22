#Java Columns
###Richard Le • CMPU-203 Spring 2015
----

##Basic Description 

####1. A field of play where the blocks move.

In my implementation of Columns, there will be a 5 x 7 playing field. To adjust for difficulty (and just to add quirks), the playing field should be expandable to 8 x 7 for a different play mode.

####2. A set of "live" blocks that are player controlled. (This "set" may contain one block.)

The player will control a set of falling blocks whose order can be rotated. The default number of blocks in this set should be 3. The player will also be able to rotate the orientation of the falling set, so that blocks can be spread horizontally.

####3. A set of "dead" blocks that are no longer player controlled.

There are 4 types of blocks that the player will stack on the bottom of the playing field. The player will attempt to stack blocks and match 3 adjacent blocks of the same type to clear lines and to gain points.

####4. A scoring system.

Each set of 3 blocks that are of same type and are directly adjacent to each other will disappear, and award the player with 1 point. For the good ol' arcade aesthetic where scores are represented with huge numbers, points * 100 = score. 

####5. A win or fail state.

**Win state (Proposal):** Endless play— player will continue stacking blocks and creating matches until the player reaches the fail state. The speed in which the blocks fall will be increased according to time, so the longer that players take to play, the more difficult the game will be. Players that wish to gain high scores will benefit from playing quickly in the first minutes of the game.

**Win state (A real win state):** Player will win once they reach a set number of points. 

**Fail state:** If the player allows any column of blocks to cross the top margin, the player loses.

####6. A control mechanism.

The player will use the arrow keys move the playing piece. [SPACE] for quick drop. [C] to rotate piece left. [V] for rotate piece right. [E] to cycle blocks up, [R] to cycle blocks down.

##Quirks
* Player could be rewarded for combos done in quick succession
* Konami code might be fun to implement...
* Instead of "Game Start," the game should open with "Ready?" "DROP IT LIKE IT'S HOT!"
