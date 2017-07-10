package org.suai.platformermvc.model.states;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LoadState extends MenuState {
	
	private boolean noSaves = false;
	
	public LoadState(GameStateManager gsm) {
		super(gsm);
		
		init();
	}
	
	public void init() {
		File saveDirectory = new File("/home/aleph/.platformermvc/saves/");
		
		if (!saveDirectory.exists()) {
			saveDirectory.mkdirs();
		}

		File[] saves = saveDirectory.listFiles();
		choiceText.clear();
		
		for (int i = 0; i < saves.length; i++) {
			choiceText.add(saves[i].getName());
		}
		
		if (saves.length == 0) {
			noSaves = true;
			choiceText.add("No saves found!(Press ESC to go back to main menu)");
		}
	}
	
	@Override
	public void update() {
		
		
	}

	@Override
	public void keyPressed(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		} else if (code == KeyEvent.VK_W) {
			currentChoice--;
			if (currentChoice < 0){
				currentChoice = choiceText.size() + currentChoice;
			}
		} else if (code == KeyEvent.VK_S) {
			currentChoice++;
			currentChoice = currentChoice % choiceText.size();
		} else if (code == KeyEvent.VK_ENTER && !noSaves) {
			try {
				FileReader reader = new FileReader(new File("/home/aleph/.platformermvc/saves/" + choiceText.get(currentChoice)));
				BufferedReader buff = new BufferedReader(reader);
				
				int lvl = Integer.parseInt(buff.readLine());
				
				((GameState1)(gsm.getState(GameStateManager.GAMESTATE1))).init(lvl, 35, 650);
				gsm.setState(GameStateManager.GAMESTATE1);
				
				buff.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		} else if (code == KeyEvent.VK_DELETE && !noSaves) {
				String name = choiceText.get(currentChoice);
				File save = new File("/home/aleph/.platformermvc/saves/" + name);
				save.delete();
				init();
			}
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
