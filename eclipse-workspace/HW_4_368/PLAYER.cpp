/*
 * PLAYER.cpp
 *
 *  Created on: Nov 28, 2018
 *      Author: chrisgeorge
 */
#include "PLAYER.h"
// 1) purpose: player constructor
// 2) input: none
// 3) output: a player object with default attributes
PLAYER::PLAYER() {
	position = {1,1};
	player_status = ALIVE;
	hasArrow = true;
	hasGold = false;
	direction = "right";
}
// 1) purpose: player destructor
// 2) input: none
// 3) output: none
PLAYER::~PLAYER() {
}
// 1) purpose: returns current position of the player
// 2) input: none
// 3) output: current player position
vector<int> PLAYER::Get_Position() {
	return position;
}
// 1) purpose: returns current direction player is facing
// 2) input: none
// 3) output: current player direction
string PLAYER::Get_Facing() {
	return direction;
}
// 1) purpose: changes player direction to 90 degrees left of current
// 2) input: none
// 3) output: none
void PLAYER::Turn_Left() {
	//these if-else statements change player direction depending on current direction
	if (direction == "right") {
		direction = "up";
	} else if (direction == "left") {
		direction = "down";
	} else if (direction == "up") {
		direction = "left";
	} else if (direction == "down") {
		direction = "right";
	}
}
// 1) purpose: changes player direction to 90 degrees right of current
// 2) input: none
// 3) output: none
void PLAYER::Turn_Right() {
	//these if-else statements change player direction depending on current direction
	if (direction == "right") {
		direction = "down";
	} else if (direction == "left") {
		direction = "up";
	} else if (direction == "up") {
		direction = "right";
	} else if (direction == "down") {
		direction = "left";
	}
}
// 1) purpose: return coordinates of square 1 space in front of player
// 2) input: none
// 3) output: coordinates of the square 1 space in front of the current position/direction
vector<int> PLAYER::Get_Next_Square() {
	vector<int> nextSquare { position[0], position[1] }; //nextSquare = current position
	if (direction == "right") {
		nextSquare[1] = nextSquare[1] + 1;	//1 column left
		return nextSquare;
	} else if (direction == "left") {
		nextSquare[1] = nextSquare[1] - 1;	//1 column right
		return nextSquare;
	} else if (direction == "up") {
		nextSquare[0] = nextSquare[0] - 1;	//1 row up
		return nextSquare;
	} else if (direction == "down") {
		nextSquare[0] = nextSquare[0] + 1;	//1 row down
		return nextSquare;
	}
	return nextSquare;
}
// 1) purpose: moves player 1 square forward
// 2) input: none
// 3) output: none
void PLAYER::Move_Forward() {
	//makes player position coordinate the result of Get_Next_Square
	position[0] = Get_Next_Square()[0];
	position[1] = Get_Next_Square()[1];
}
// 1) purpose: returns true if player has an arrow
// 2) input: none
// 3) output: hasArrow boolean
bool PLAYER::Has_Arrow() {
	return hasArrow;
}
// 1) purpose: sends an arrow forward in the direction shot, until hits wumpus or wall
// 2) input: cave object
// 3) output: boolean- true if wumpus is killed, false if still alive
bool PLAYER::Shoot_Arrow(CAVE &cave) {
	vector<int> arrowPosition = position;
	if (hasArrow) {
		hasArrow = false;
		//loop while arrow position is in the grid
		while (arrowPosition[0] <= 4 && arrowPosition[0] >= 0
				&& arrowPosition[1] <= 4 && arrowPosition[1] >= 0) {
			if (cave.Square_Contains_Living_Wumpus(arrowPosition)) {
				cave.Shoot_Square(arrowPosition); //kills wumpus in the spot
				return true;
			} else {
				//this block advances the arrow 1 space forward
				if (direction == "right") {
					arrowPosition[1] = arrowPosition[1] + 1;
				} else if (direction == "left") {
					arrowPosition[1] = arrowPosition[1] - 1;
				} else if (direction == "up") {
					arrowPosition[0] = arrowPosition[0] - 1;
				} else if (direction == "down") {
					arrowPosition[0] = arrowPosition[0] + 1;
				}
			}
		}
		if (cave.Is_Wumpus_Alive()) {
			return false;
		}
	} else {
		return false;
	}
}
// 1) purpose: erase gold from cave if the the player position = gold position, return true if
//gold erased
// 2) input: cave object
// 3) output: boolean-- true if gold picked up, false if not
bool PLAYER::Pickup_Gold(CAVE &cave) {
	bool pickedUp = false;
	if (cave.Square_Contains_Gold(position)) {
		pickedUp = cave.Remove_Gold(position); //takes gold off map
		hasGold = true;
		return pickedUp;
	} else {
		return pickedUp;
	}
}
// 1) purpose: change player_status to ESCAPED
// 2) input: none
// 3) output: none
void PLAYER::Climb_Ladder() {
	player_status = ESCAPED;
}
// 1) purpose: check if player position has wumpus or pit, update player_status accordingly
// 2) input: cave object
// 3) output: boolean: true if player alive, false if anything else
bool PLAYER::Update_Player_Status(CAVE &cave) {
	//changes player status depending on whether there is a pit or wumpus in its position
	if (cave.Square_Contains_Living_Wumpus(position)) {
		player_status = EATEN;
		return false;
	} else if (cave.Square_Contains_Pit(position)) {
		player_status = FELL;
		return false;
	}
	return true;
}
// 1) purpose: return true if player has escaped, false if not
// 2) input: none
// 3) output: boolean-- if player_status is ESCAPED- true, false otherwise
bool PLAYER::Escaped() {
	if (player_status == ESCAPED) {
		return true;
	} else {
		return false;
	}
}
// 1) purpose: return true if player has been eaten, false if not
// 2) input: none
// 3) output: boolean-- if player_status is EATEN- true, false otherwise
bool PLAYER::Eaten() {
	if (player_status == EATEN) {
		return true;
	} else {
		return false;
	}
}
// 1) purpose: return true if player has fallen, false if not
// 2) input: none
// 3) output: boolean-- if player_status is FELL- true, false otherwise
bool PLAYER::Fell() {
	if (player_status == FELL) {
		return true;
	} else {
		return false;
	}
}
// 1) purpose: return hasGold variable-- true if player has the gold, false otherwise
// 2) input: none
// 3) output: hasGold variable
bool PLAYER::Has_Gold() {
	return hasGold;
}

