package org.suai.platformermvc.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.suai.platformermvc.model.states.GameStateManager;
import org.suai.platformermvc.view.GameView;

public class GameController implements KeyListener, MouseListener, MouseMotionListener {
	private GameView gameView;
	//private GameModel gameModel;
	private GameStateManager gsm;
	
	
	public GameController(GameView gameView, GameStateManager gsm){
		this.gameView = gameView;
		gameView.addKeyListener(this);
		gameView.addMouseListener(this);
		//this.gameModel = gameModel;
		this.gsm = gsm;
		//player = gameModel.getPlayer();
	}
	
	
	public void updateGame() {
		gsm.update();
	}
	
	
	public void renderAndDraw() {
		gameView.render();
		gameView.draw();
	}

	

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		gsm.keyPressed(code);
		
	}


	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
			
		gsm.keyReleased(code); 
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
		gsm.mousePressed(mouseX, mouseY);
		//double xLen = player.getX() - mouseX;
		//double yLen = player.getY() - mouseY;

		//gameModel.addProjectile((int) player.getX(),(int) player.getY(), (((Math.toDegrees(Math.atan2(yLen, xLen))) + 180)));
		
	}


	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
