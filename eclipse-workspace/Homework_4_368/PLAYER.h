/*
 * PLAYER.h
 *
 *  Created on: Nov 28, 2018
 *      Author: chrisgeorge
 */

#ifndef PLAYER_H_
#define PLAYER_H_

#include "CAVE.h"
#include <iostream>
#include <map>
#include <vector>
#include <set>
#include <string>
#include <time.h>

using namespace std;

class PLAYER {
	enum STATUS {
		ALIVE, EATEN, FELL, ESCAPED
	};
	STATUS player_status;

	vector<int> position;
	string direction;
	bool hasArrow;
	bool hasGold;

public:
	PLAYER();
	~PLAYER();
	vector<int> Get_Position();
	string Get_Facing();
	void Turn_Left();
	void Turn_Right();
	vector<int> Get_Next_Square();
	void Move_Forward();
	bool Has_Arrow();
	bool Shoot_Arrow(CAVE &cave);
	bool Pickup_Gold(CAVE &cave);
	void Climb_Ladder();
	bool Update_Player_Status(CAVE &cave);
	bool Escaped();
	bool Eaten();
	bool Fell();
	bool Has_Gold();

};

#endif /* PLAYER_H_ */
