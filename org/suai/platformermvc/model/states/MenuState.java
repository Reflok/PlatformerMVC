package org.suai.platformermvc.model.states;

public interface MenuState extends State{
	public int getCurrentChoice();
	public int getChoicesNum();
	public String getChoiceText(int n);
}