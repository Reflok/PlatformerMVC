package org.suai.platformermvc.model.states;

import java.awt.event.KeyEvent;

public class PauseState implements MenuState{
	public static final int CONTINUE_CHOICE = 0;
	public static final int FILLER_CHOICE = 1;
	public static final int TO_MAIN_MENU_CHOICE = 2;
	public static final int EXIT_CHOICE = 3;
	
	private String[] choiceText;
	
	private int choicesNum;
	private int currentChoice;
	
	GameStateManager gsm;
	
	public PauseState(GameStateManager gsm) {
		
		currentChoice = 0;
		choicesNum = 4;
		this.gsm = gsm;
		choiceText = new String[choicesNum];
		choiceText[CONTINUE_CHOICE] = "CONTINUE";
		choiceText[FILLER_CHOICE] = "FILLER";
		choiceText[TO_MAIN_MENU_CHOICE] = "MAIN MENU";
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
			if (currentChoice == CONTINUE_CHOICE) {
				executeContinue();
			} else if(currentChoice == TO_MAIN_MENU_CHOICE){
				gsm.setState(GameStateManager.MENUSTATE);
			}else if(currentChoice == EXIT_CHOICE){
				executeExit();
			}
 		}
	}

	private void executeExit() {
		System.exit(0);
	}

	public int getCurrentChoice() { return currentChoice; }

	public String getChoiceText(int n) { return choiceText[n]; }
	
	private void executeContinue() {
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
