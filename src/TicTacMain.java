import game.Game;
import player.Bot;
import player.Human;
import player.Player;
import simpleFrame.Fun;


public class TicTacMain {

	public TicTacMain() {
		Player p1 = new Human("Bob");
//		Player p1 = new Bot("bot-00");
		Player p2 = new Bot("bot-01");
		Game g = new Game(p1, p2);
		boolean p1GoesFirst = true;
		while(true){
			g.play(p1GoesFirst);
			p1GoesFirst = !p1GoesFirst;
			Fun.sleep(200);
		}
	}
	
	public static void main(String[] args){
		new TicTacMain();
	}

}
