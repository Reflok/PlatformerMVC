package org.suai.platformermvc.model.states;

import java.awt.event.KeyEvent;

public class MenuState implements State{
	public static final int PLAY_CHOICE = 0;
	public static final int FILLER_CHOICE = 1;
	public static final int EXIT_CHOICE = 2;
	;
	private String[] choiceText;
	
	private int choicesNum;
	private int currentChoice;
	
	GameStateManager gsm;
	
	public MenuState(GameStateManager gsm) {
		currentChoice = 0;
		choicesNum = 3;
		this.gsm = gsm;
		choiceText = new String[choicesNum];
		choiceText[PLAY_CHOICE] = "PLAY";
		choiceText[FILLER_CHOICE] = "FILLER";
		choiceText[EXIT_CHOICE] = "EXIT";
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
				currentChoice = choicesNum + currentChoice;
			}
		}
		
		if (code == KeyEvent.VK_S) {
			currentChoice++;
			currentChoice = currentChoice % choicesNum;
			
		}

		
		if (code == KeyEvent.VK_ENTER) {
			if (currentChoice == PLAY_CHOICE) {
				executePlay();
			} else if(currentChoice == EXIT_CHOICE){
				executeExit();
			}else if(currentChoice == FILLER_CHOICE){
				
			}
 		}
	}

	private void executeExit() {
		System.exit(0);
		
	}

	public int getCurrentChoice() { return currentChoice; }

	public String getChoiceText(int n) { return choiceText[n]; }
	
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


	public int getChoicesNum() { return choicesNum; }


	

}
