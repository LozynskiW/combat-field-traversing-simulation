### compass: enum
NORTH, 
NORTH-EAST, 
EAST, 
SOUTH-EAST, 
SOUTH, 
SOUTH-WEST, 
WEST, 
NORTH-WEST

int[2] - modification to current position to move to another position 
in format {x, y}

f. e. int[]{0, 1} - add 0 to E and 1 to N - NORTH

### position: class

coordinates: int[2] {x, y}
nextMove: compass

### unit: class

startingPosition: position
meansOfCombat: List
movementPlanned: Deque<position>
movementMade = Deque<position>
currentPosition: movementPlanned[last]
timeToMove: int