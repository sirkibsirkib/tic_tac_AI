package game;

import simpleFrame.Colour;
import simpleFrame.Fun;
import simpleFrame.SimpleFrame;

public class Drawer implements Runnable {
	private SimpleFrame sf;
	private Game daddy;

	public Drawer(SimpleFrame sf, Game daddy) {
		this.sf = sf;
		this.daddy = daddy;
	}

	@Override
	public void run() {
		while(daddy.running()){
			sf.clear(Colour.BLACK);
			Board board = daddy.getBoard();
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					Colour col = Fun.even(i-j)?Colour.BLACK:daddy.p1Turn()?Colour.D_BLUE:Colour.D_RED;
					switch(board.get(i, j)){
					case 1: col = Colour.BLUE;	break;
					case 2: col = Colour.RED;	break;
					}
					sf.dot(i, j, col);
				}
			}
			sf.render();
		}
	}

}
