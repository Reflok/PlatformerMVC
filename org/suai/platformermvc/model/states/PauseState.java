package org.suai.platformermvc.model.states;

import java.awt.event.KeyEvent;

public class PauseState extends MenuState{
	public static final int CONTINUE_CHOICE = 0;
	public static final int SAVE_CHOICE = 1;
	public static final int TO_MAIN_MENU_CHOICE = 2;
	public static final int EXIT_CHOICE = 3;
	
	
	public PauseState(GameStateManager gsm) {
		super(gsm);
		
		choiceText.add("CONTINUE");
		choiceText.add("SAVE");
		choiceText.add("TO MAIN MENU");
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
			if (currentChoice == CONTINUE_CHOICE) {
				executeContinue();
			} else if(currentChoice == TO_MAIN_MENU_CHOICE){
				gsm.setState(GameStateManager.MENUSTATE);
			} else if(currentChoice == SAVE_CHOICE){
				gsm.setState(GameStateManager.SAVESTATE);
			}else if(currentChoice == EXIT_CHOICE){
				executeExit();
			}
 		}
	}

	private void executeExit() {
		System.exit(0);
	}


	
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




	

}
