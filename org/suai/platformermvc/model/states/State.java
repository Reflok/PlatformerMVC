package org.suai.platformermvc.model.states;

public interface State {
	
	public void update();
	
	public void keyPressed(int code);
	
	public void keyReleased(int code);
	
	public void mousePressed(int x, int y);
	
;}
