package game;

import graphs.Pti;
import simpleFrame.Fun;

public class Board {
	private int[][] pieces;

	public Board() {
		pieces = new int[3][3];
		clear();
	}
	
	public Board(Board b) {
		pieces = new int[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				pieces[i][j] = b.pieces[i][j];
			}
		}
	}

	public void set(int i, int j, int val){
		pieces[i][j] = val;
	}
	
	public int get(int i, int j){
		return pieces[i][j];
	}

	public void clear() {
		for(int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++) {
				pieces[i][j] = 0;
			}
		}
	}

	public int winPlayer() {
		if(stalemate()) return 0;
		int[] count = new int[3];
		//horizontal
		for(int j = 0; j < 3; j++){
			Fun.zeroIntArray(count);
			for(int i = 0; i < 3; i++){
				count[pieces[i][j]] += 1;
			}
			if(count[1] == 3) return 1;
			else if(count[2] == 3)return 2;
		}
		//vertical
		for(int j = 0; j < 3; j++){
			Fun.zeroIntArray(count);
			for(int i = 0; i < 3; i++){
				count[pieces[j][i]] += 1;
			}
			if(count[1] == 3) return 1;
			else if(count[2] == 3)return 2;
		}
		//tl diagonal
		Fun.zeroIntArray(count);
		for(int i = 0; i < 3; i++){
			count[pieces[i][i]] += 1;
		}
		if(count[1] == 3) return 1;
		else if(count[2] == 3)return 2;
		//tr diagonal
		Fun.zeroIntArray(count);
		for(int i = 0; i < 3; i++){
			count[pieces[i][2-i]] += 1;
		}
		if(count[1] == 3) return 1;
		else if(count[2] == 3)return 2;
		return -1;
	}

	private boolean stalemate() {
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(pieces[i][j] == 0){
					return false;
				}
			}
		}
		return true;
	}

	public boolean set(Pti position, int val) {
		if(!emptyPlaceAt(position)){
			return false;
		}
		pieces[position.getX()][position.getY()] = val;
		return true;
	}

	public boolean emptyPlaceAt(Pti position) {
		try{
			return pieces[position.getX()][position.getY()] == 0;
		}catch(Exception e){
			System.out.println("tried to read board poz " + position);
			throw e;
		}
	}

}
