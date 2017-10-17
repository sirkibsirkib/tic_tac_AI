package player;

import game.Board;
import graphs.Pti;

public interface Player {
	Pti chooseMove(Board b, int myID, int moveTimeMillis);
	String getName();
}
