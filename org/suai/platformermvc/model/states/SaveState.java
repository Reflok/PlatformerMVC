package org.suai.platformermvc.model.states;

import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SaveState extends MenuState {
	File saveDirectory;
	public SaveState(GameStateManager gsm) {
		super(gsm);
		
		init();
	}

	public void init() {
		saveDirectory = new File("/home/aleph/.platformermvc/saves/");
		
		if (!saveDirectory.exists()) {
			saveDirectory.mkdirs();
		}

		File[] saves = saveDirectory.listFiles();
		
		choiceText.clear();
		
		choiceText.add("New save");
		
		for (int i = 0; i < saves.length; i++) {
			choiceText.add(saves[i].getName());
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.GAMESTATE1);
		} else if (code == KeyEvent.VK_W) {
			currentChoice--;
			if (currentChoice < 0){
				currentChoice = choiceText.size() + currentChoice;
			}
		} else if (code == KeyEvent.VK_S) {
			currentChoice++;
			currentChoice = currentChoice % choiceText.size();
			
		} else if (code == KeyEvent.VK_ENTER) {
			File save;
			String name;
		
			if(currentChoice != 0) {
				name = choiceText.get(currentChoice);
				save = new File("/home/aleph/.platformermvc/saves/" + name);
				save.delete();
			}
			
			DateFormat df = new SimpleDateFormat("MM.dd.yyyy-HH:mm:ss");
			Date time = Calendar.getInstance().getTime();  
			name = df.format(time);
			save = new File("/home/aleph/.platformermvc/saves/" + name);
			
			try {
				if (!save.exists()) {
					save.createNewFile();
				}
				
				FileWriter saveWriter =  new FileWriter(save);
				BufferedWriter buff = new BufferedWriter(saveWriter);
				
				Integer lvl = ((GameState1) gsm.getState(GameStateManager.GAMESTATE1)).getLvl();
				saveWriter.write(lvl.toString());
				System.out.println(lvl.toString());
				buff.write(System.lineSeparator());
				buff.flush();
				buff.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			init();
		} else if (code == KeyEvent.VK_DELETE) {
			if(currentChoice != 0) {
				String name = choiceText.get(currentChoice);
				File save = new File("/home/aleph/.platformermvc/saves/" + name);
				save.delete();
				init();
			}
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
