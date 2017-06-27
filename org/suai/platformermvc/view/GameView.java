package org.suai.platformermvc.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.suai.platformermvc.model.PlayerModel;
import org.suai.platformermvc.model.states.GameState1;
import org.suai.platformermvc.model.states.GameStateManager;
import org.suai.platformermvc.model.states.MenuState;

public class GameView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameStateManager gsm;
	private PlayerModel player;
	private BufferedImage mainImage;
	private Graphics2D imageGraphics;
	GameState1 gameModel;
	
	private static final int windowWidth = 1000;
	private static final int windowHeight = 700;
	
	private static int tileSize;
	
	public GameView() {
		
		mainImage = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
		imageGraphics = (Graphics2D) mainImage.getGraphics();
		
		((Graphics2D)imageGraphics).setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		((Graphics2D)imageGraphics).setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		JFrame frame = new JFrame("PlatformerMVC");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		frame.setContentPane(this);
		frame.pack();
		frame.setResizable(false);
		frame.setSize(windowWidth, windowHeight);
		
		
		frame.setVisible(true);
		
		setFocusable(true);
		requestFocus();
	}
	
	public void setGameStateManager(GameStateManager gsm) {
		this.gsm = gsm;
		gameModel = (GameState1) gsm.getState(GameStateManager.GAMESTATE1);
		player = gameModel.getPlayer();
		tileSize = gameModel.getTileSize();
	}
	
	public void render() {
		if (gsm.getCurrentState() == GameStateManager.GAMESTATE1) {
			//map
			for (int i = 0; i < gameModel.getMapHeight(); i++) {
				for (int j = 0; j < gameModel.getMapWidth(); j++) {
					int tileCode = gameModel.getMapData(i, j);
					
					if(tileCode == 0) {
						imageGraphics.setColor(Color.BLACK);
					} else if (tileCode == 1) {
						imageGraphics.setColor(Color.DARK_GRAY);
					}
					
					imageGraphics.fillRect(gameModel.getOffsetX() + j * tileSize, gameModel.getOffsetY() + i * tileSize, tileSize, tileSize);
				}
			}
			
			//player
			
			int offsetX = gameModel.getOffsetX();
			int offsetY = gameModel.getOffsetY();
			
			imageGraphics.setColor(Color.GREEN);
			imageGraphics.fillRect(
					offsetX + (int) player.getX() - player.getWidth() / 2,
					offsetY + (int) player.getY() - player.getHeight() / 2,
					player.getWidth(), player.getHeight());
			
			//enemies
			imageGraphics.setColor(Color.RED.darker());
			for (int i = 0; i < gameModel.enemies.size(); i++) {
				imageGraphics.fillRect((int) gameModel.enemies.get(i).getX() - 11,(int) gameModel.enemies.get(i).getY() - 11, 22, 22);
			}
			
			//projectiles
			for (int i = 0; i < gameModel.getProjectilesNum(); i++) {
				imageGraphics.setColor(Color.RED);
				int x = (int) gameModel.getProjectile(i).getX();
				int y = (int) gameModel.getProjectile(i).getY();
				int r = gameModel.getProjectile(i).getWidth();
				imageGraphics.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
			}
		} else if (gsm.getCurrentState() == GameStateManager.MENUSTATE) {
			MenuState menuState = (MenuState) gsm.getState(gsm.getCurrentState());
			//imageGraphics.setColor(Color.LIGHT_GRAY);
			//imageGraphics.fillRect(0, 0, windowWidth, windowHeight);
			int currChoice = menuState.getCurrentChoice();
			int amountOfChoices = menuState.getChoicesNum();
			
			Color color;
			String str;
			int len;
			imageGraphics.setColor(Color.BLACK);
			imageGraphics.fillRect(0, 0, windowWidth, windowHeight);
			imageGraphics.setFont(new Font("Century Gothic", Font.BOLD, 20));
			
			for (int i = 0; i< amountOfChoices; i++) {
				color = Color.RED;
				
				if (i == currChoice) {
					color = Color.GREEN;
				}
				str = menuState.getChoiceText(i);
				len = (int) imageGraphics.getFontMetrics().getStringBounds(str, imageGraphics).getWidth();
				
				imageGraphics.setColor(color);
				imageGraphics.drawString(str, windowWidth / 2 - len / 2, (i + 1) * windowHeight / (amountOfChoices + 1));
				
			}
			
			/*imageGraphics.setColor(Color.RED);
			if (menuState.getCurrentChoice() == MenuState.PLAY_CHOICE) {
				imageGraphics.setColor(Color.GREEN);
			}
			String str = "PLAY";
			int len = (int) imageGraphics.getFontMetrics().getStringBounds(str, imageGraphics).getWidth();
			imageGraphics.drawString(str, windowWidth / 2 - len / 2, windowHeight / 3);
			
			imageGraphics.setColor(Color.RED);
			if (menuState.getCurrentChoice() == MenuState.EXIT_CHOICE) {
				imageGraphics.setColor(Color.GREEN);
			}
			str = "EXIT";
			len = (int) imageGraphics.getFontMetrics().getStringBounds(str, imageGraphics).getWidth();
			imageGraphics.drawString(str, windowWidth / 2 - len / 2, 2 * windowHeight / 3);*/
			
		}
		
	}
	
	
	public void draw() {
		Graphics2D panel = (Graphics2D) this.getGraphics();
		panel.drawImage(mainImage, 0, 0 , null);
		panel.dispose();
	}
	
	
	public void drawLoadingScreen() {
		String str = "LOADING";
		int len = (int) imageGraphics.getFontMetrics().getStringBounds(str, imageGraphics).getWidth();

		//imageGraphics.setColor(Color.WHITE);
		//imageGraphics.fillRect(0, 0, windowWidth, windowHeight);
		imageGraphics.setColor(Color.BLACK);
		imageGraphics.setFont(new Font("Century Gothic", Font.BOLD, 20));
		imageGraphics.drawString(str, windowWidth / 2 - len / 2, windowHeight / 2);
		
		this.getGraphics().drawImage(mainImage, 0, 0, null);
	}
	
}
