package game;

import graphs.Pti;
import player.Human;
import player.Player;
import simpleFrame.Colour;
import simpleFrame.Fun;
import simpleFrame.SimpleFrame;

public class Game {
	private static final int ROUND_TIME_LIMIT = 10_000;
	private Board board;
	private Player p1, p2, playing;
	private ClickListener cl;
	private boolean running;
	private Drawer drawer;
	private int scale = 150;
	private int[] scores;
	
	public int getScale(){
		return scale;
	}

	public Game(Player p1, Player p2) {
		board = new Board();
		running = true;
		this.p1 = p1;
		this.p2 = p2;
		cl = new ClickListener(scale);
		SimpleFrame sf = new SimpleFrame(3, 3, scale, "TicTacToe Game", Colour.BLACK);
		sf.registerMouseListener(cl);
		drawer = new Drawer(sf, this);
		new Thread(drawer).start();
		scores = new int[3];
	}

	public void play(boolean p1GoesFirst) {
		playing = p1GoesFirst? p1 : p2;
		int winPlayer = -1;
		do{
			round(playing);
			playing = playing==p1? p2 : p1;
			winPlayer = board.winPlayer();
		}while(winPlayer == -1);

		scores[winPlayer]++;
		if(winPlayer != 0){
			System.out.println("Player " + winPlayer + " wins!");
		}
		printScores();
		board.clear();
	}

	private void printScores() {
		System.out.printf(" [draws: %d    p1 wins: %d    p2 wins: %d]\n", scores[0], scores[1], scores[2]);
	}

	private void round(Player playing) {
		if(playing instanceof Human){
			cl.setListeningHuman((Human)playing);
		}
		long time = Fun.timeNow();
		Pti moveChoice = null;
		do{
			moveChoice = playing.chooseMove(board, playerID(playing), ROUND_TIME_LIMIT);
		}while(Fun.timeSince(time) < ROUND_TIME_LIMIT && (moveChoice == null || !board.emptyPlaceAt(moveChoice)));
		if(moveChoice != null){
			board.set(moveChoice, playing==p1?1:2);
		}
		Fun.sleep(50);
	}

	private int playerID(Player p) {
		if(p1 == p) return 1;
		if(p2 == p) return 2;
		return 99;
	}

	public boolean running() {
		return running;
	}

	public Board getBoard() {
		return board;
	}

	public boolean p1Turn() {
		return playing == p1;
	}
}
