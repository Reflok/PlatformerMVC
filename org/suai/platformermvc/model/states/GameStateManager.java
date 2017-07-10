package org.suai.platformermvc.model.states;

import java.util.ArrayList;

public class GameStateManager {
	private ArrayList<State> states;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int GAMESTATE1 = 1;
	public static final int PAUSESTATE = 2;
	public static final int LOADSTATE = 3;
	public static final int SAVESTATE = 4;
	
	
	public GameStateManager() {
		states = new ArrayList<State>();
		
		currentState = GameStateManager.MENUSTATE;
		
		states.add(new MainMenuState(this));
		states.add(new GameState1(this));
		states.add(new PauseState(this));
		states.add(new LoadState(this));
		states.add(new SaveState(this));
	}

	
	public void update() {
		states.get(currentState).update();
	}
	
	
	public void keyPressed(int code) {
		states.get(currentState).keyPressed(code);
	}
	
	
	public void keyReleased(int code) {
		states.get(currentState).keyReleased(code);
	}
	
	
	public void mousePressed(int x, int y) {
		if (currentState == GameStateManager.GAMESTATE1) {
			states.get(currentState).mousePressed(x, y);
		}
	}
	
	public int getCurrentState() { return currentState; }
	
	public void setState(int n) {
		
		currentState = n;
	}
	
	public State getState(int n) { return states.get(n); }
}
