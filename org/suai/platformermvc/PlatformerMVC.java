package org.suai.platformermvc;

import javax.swing.JFrame;

import org.suai.platformermvc.controller.GameController;
import org.suai.platformermvc.model.GameModel;
import org.suai.platformermvc.view.GameView;

public class PlatformerMVC implements Runnable {
	
	private GameController gameController;

	private final static int windowWidth = 1000;
	private final static int windowHeight = 700;
	
	private static final int framesPerSec = 30;
	private static final int millisPerFrame = 1000 / framesPerSec;
	
	public PlatformerMVC() {
		GameModel gameModel = new GameModel();
		GameView gamePanel = new GameView(gameModel);
		gameController = new GameController(gamePanel, gameModel);
		
		
		
	}
	
	
	public void run() {
		long startTime;
		
		while (true) {
			startTime = System.nanoTime();
			
			gameController.updateGame();
			gameController.renderAndDraw();
			
			long timeElapsed = (System.nanoTime() - startTime) / 1000000;
			if (timeElapsed < millisPerFrame) {
				try {
					Thread.sleep(millisPerFrame - timeElapsed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		new Thread(new PlatformerMVC()).start();
	}
}
