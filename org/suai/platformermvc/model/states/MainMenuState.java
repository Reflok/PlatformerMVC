package org.suai.platformermvc.model.states;


import java.awt.event.KeyEvent;

public class MainMenuState extends MenuState {
	public static final int PLAY_CHOICE = 0;
	public static final int LOAD_CHOICE = 1;
	public static final int EXIT_CHOICE = 2;
	
	public MainMenuState(GameStateManager gsm) {
		super(gsm);
		
		choiceText.add("PLAY");
		choiceText.add("LOAD");
		choiceText.add("EXIT");
	}
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int code) {
		if (code == KeyEvent.VK_W) {
			currentChoice--;
			if (currentChoice < 0){
				currentChoice = choiceText.size() + currentChoice;
			}
		}
		
		if (code == KeyEvent.VK_S) {
			currentChoice++;
			currentChoice = currentChoice % choiceText.size();
			
		}

		
		if (code == KeyEvent.VK_ENTER) {
			if (currentChoice == PLAY_CHOICE) {
				executePlay();
			} else if(currentChoice == LOAD_CHOICE){
				executeLoad();
			} else if(currentChoice == EXIT_CHOICE){
				executeExit();
			}
 		}
	}

	private void executeLoad() {
		gsm.setState(GameStateManager.LOADSTATE);
	}
	
	private void executeExit() {
		System.exit(0);
		
	}

	
	private void executePlay() {
		gsm.setState(GameStateManager.GAMESTATE1);
	}


	@Override
	public void keyReleased(int code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
