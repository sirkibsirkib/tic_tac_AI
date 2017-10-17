package game;

import graphs.Pti;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import player.Human;

public class ClickListener implements MouseListener{
	private Human listening;
	private int scale;
	
	ClickListener(int scale){
		this.scale = scale;
	}
	
	public void setListeningHuman(Human listening){
		this.listening = listening;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point p = e.getPoint();
		Pti pti = new Pti((int)(p.getX()/scale), (int)(p.getY()/scale));
		if(listening != null){
			listening.humanInput(pti);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
