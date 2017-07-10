package org.suai.platformermvc.model.states;

import java.util.ArrayList;

public abstract class MenuState implements State{
	protected ArrayList<String> choiceText;
	protected int currentChoice;
	protected GameStateManager gsm;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		choiceText = new ArrayList<String>();
	}
	
	/*public abstract int getCurrentChoice();
	public abstract int getChoicesNum();
	public abstract String getChoiceText(int n);*/
	

	public int getChoicesNum() { return choiceText.size(); }
	
	public int getCurrentChoice() { return currentChoice; }

	public String getChoiceText(int n) { return choiceText.get(n); }
}