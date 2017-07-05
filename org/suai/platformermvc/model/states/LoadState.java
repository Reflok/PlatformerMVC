package org.suai.platformermvc.model.states;

import java.io.File;

public class LoadState implements MenuState {

	@Override
	public void update() {
		File saveDirectory = new File("home/.platformermvc/saves/");
		
		if (!saveDirectory.exists()) {
			saveDirectory.mkdirs();
		}

		File[] saves = saveDirectory.listFiles();
	}

	@Override
	public void keyPressed(int code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCurrentChoice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getChoicesNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getChoiceText(int n) {
		// TODO Auto-generated method stub
		return null;
	}

}
