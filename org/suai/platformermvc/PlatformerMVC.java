package org.suai.platformermvc;

import org.suai.platformermvc.controller.GameController;
import org.suai.platformermvc.model.states.GameStateManager;
import org.suai.platformermvc.view.GameView;

public class PlatformerMVC implements Runnable {
	
	private GameController gameController;
	
	GameView gamePanel;
	
	private static final int framesPerSec = 60;
	private static final int millisPerFrame = 1000 / framesPerSec;
	
	public PlatformerMVC() {
		GameView gamePanel = new GameView();
		gamePanel.drawLoadingScreen();
		GameStateManager gsm = new GameStateManager();
		gamePanel.setGameStateManager(gsm);
		gameController = new GameController(gamePanel, gsm);
		
		
		
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
