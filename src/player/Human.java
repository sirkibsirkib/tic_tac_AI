package player;

import game.Board;
import graphs.Pti;
import simpleFrame.Fun;
import simpleFrame.SimpleFrame;

public class Human implements Player{
	private SimpleFrame sf;
	private Pti clickChoice;
	private String name;

	public Human(String name) {
		this.name = name;
	}
	
	public void humanInput(Pti clickChoice){
		this.clickChoice = clickChoice;
	}

	@Override
	public Pti chooseMove(Board b, int myID, int moveTimeMillis) {
		clickChoice = null;
		long time = Fun.timeNow();
		while(clickChoice == null && Fun.timeSince(time) < moveTimeMillis-200){
			Fun.sleep(60);
		}
		return clickChoice;
	}

	@Override
	public String getName() {
		return name;
	}
}
