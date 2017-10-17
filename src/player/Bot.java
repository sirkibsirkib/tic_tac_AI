package player;

import game.Board;
import graphs.Pti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simpleFrame.Fun;

public class Bot implements Player{
	private String name;
	private int maxDepth = 10;

	public Bot(String name) {
		this.name = name;
	}

	@Override
	public Pti chooseMove(Board b, int myID, int moveTimeMillis) {
		long time = Fun.timeNow();
		Choice choice = minimax(new Board(b), true, maxDepth, myID);
//		System.out.println("Expecting h value " + choice.hValue);
//		System.out.println(choice.hValue);
		if(choice.hValue > 500){
			int screwedMoves = (int) (1000-choice.hValue-1)/2;
			if(screwedMoves > 1){
				System.out.println("You're fucked in ..." + (screwedMoves) + " moves ;)");
			}
			
		}
		return choice.move;
	}

	private Choice minimax(Board board, boolean myTurn, int depthToGo, int myID) {
		int myWinPlayer = board.winPlayer();
		double h = utilityOfWinner(myWinPlayer, myID);
		if(myWinPlayer == 1 || myWinPlayer == 2){
			return new Choice(null, utilityOfWinner(myWinPlayer, myID));
		}
		if(depthToGo == 0){
			return new Choice(null, h);
		}
		Choice best = new Choice(null, myTurn?-2000:2000);
		
		List<Pti> options = new ArrayList<>();
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				Pti o = new Pti(i, j);
				if(board.emptyPlaceAt(o)){
					options.add(o);
				}
			}
		}
		if(options.size() == 0){
			best.hValue = h;
			return best;
		}
		if(depthToGo == maxDepth){
//			System.out.println("if I do nothing: " + best.hValue);
//			System.out.println("options " + options.size());
		}
		if(depthToGo == maxDepth){
			Collections.shuffle(options);
		}
		for(Pti option : options){
			Board branchBoard = new Board(board);
			branchBoard.set(option, myTurn ? myID : enemyId(myID));
			double branchH = minimax(branchBoard, !myTurn, depthToGo-1, myID).hValue;
			if(myTurn){
				if(branchH > best.hValue){
					best.move = option;
					best.hValue = branchH;
				}
			}else{
				if(branchH < best.hValue){
					best.move = option;
					best.hValue = branchH;
				}
			}
		}
		if(depthToGo == maxDepth){
//			System.out.println("I chose something that gives: " + best.hValue);
		}
		best.hValue = decay(best.hValue);
		return best;
	}
	
	private double decay(double hValue) {
		if(hValue >= 1) return hValue-1;
		if(hValue <= -1) return hValue+1;
		return hValue;
	}

	private double utilityOfWinner(int myWinPlayer, int myID) {
		if(myWinPlayer == myID) return 1000;
		switch(myWinPlayer){
		case 0: return 5;
		case -1: return 0;
		default: return -1000;
		}
	}

	private int enemyId(int myID){
		if(myID == 1){
			return 2;
		}
		return 1;
	}
	
	private static class Choice{
		Pti move;
		double hValue;
		
		Choice(Pti move, double hValue){
			this.move = move;
			this.hValue = hValue;
		}
	}

	@Override
	public String getName() {
		return name;
	}

}
