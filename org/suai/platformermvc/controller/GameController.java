package org.suai.platformermvc.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.suai.platformermvc.model.GameModel;
import org.suai.platformermvc.model.PlayerModel;
import org.suai.platformermvc.model.ProjectileModel;
import org.suai.platformermvc.view.GameView;

public class GameController implements KeyListener, MouseListener, MouseMotionListener {
	private GameView gameView;
	private GameModel gameModel;
	private PlayerModel player;
	
	
	public GameController(GameView gameView, GameModel gameModel){
		this.gameView = gameView;
		gameView.addKeyListener(this);
		gameView.addMouseListener(this);
		this.gameModel = gameModel;
		player = gameModel.getPlayer();
	}
	
	
	public void updateGame() {
		gameModel.update();
	}
	
	
	public void renderAndDraw() {
		gameView.render();
		gameView.draw();
	}

	

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		/*if (code == KeyEvent.VK_A) {
			player.setLeft(true);
		}
		
		if (code == KeyEvent.VK_D) {
			player.setRight(true);
		}
		
		if (code == KeyEvent.VK_W) {
			player.setJumping(true);
		}*/
		player.keyPressed(code);
		
	}


	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		/*if (code == KeyEvent.VK_A) {
			player.setLeft(false);
		}
		
		if (code == KeyEvent.VK_D) {
			player.setRight(false);
		}
		
		if (code == KeyEvent.VK_W) {
			player.setJumping(false);
		}*/
		
		//if (code == KeyEvent.VK_SPACE) {
			//gameModel.addProjectile((int) player.getX(),(int) player.getY(), 270);
		//} else {		
		player.keyReleased(code); 
		//}
	}
	

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseClicked(MouseEvent e) {
		
		
	}


	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		double xLen = player.getX() - mouseX;
		double yLen = player.getY() - mouseY;

		gameModel.addProjectile((int) player.getX(),(int) player.getY(), (((Math.toDegrees(Math.atan2(yLen, xLen))) + 180)));
		
	}


	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
