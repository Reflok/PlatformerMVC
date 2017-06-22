package org.suai.platformermvc.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.suai.platformermvc.model.GameModel;
import org.suai.platformermvc.model.PlayerModel;

public class GameView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameModel gameModel;
	private PlayerModel player;
	private BufferedImage mainImage;
	private Graphics2D imageGraphics;
	
	private static final int windowWidth = 1000;
	private static final int windowHeight = 700;
	
	private static int tileSize;
	
	public GameView(GameModel gameModel) {
		this.gameModel = gameModel;
		player = gameModel.getPlayer();
		tileSize = gameModel.getTileSize();
		
		mainImage = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
		imageGraphics = (Graphics2D) mainImage.getGraphics();
		
		imageGraphics.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		imageGraphics.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
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
	
	
	public void render() {
		//map
		for (int i = 0; i < gameModel.getMapHeight(); i++) {
			for (int j = 0; j < gameModel.getMapWidth(); j++) {
				int tileCode = gameModel.getMapData(i, j);
				
				if(tileCode == 0) {
					imageGraphics.setColor(Color.BLACK);
				} else if (tileCode == 1) {
					imageGraphics.setColor(new Color(0, 128, 0));
				}
				
				imageGraphics.fillRect(gameModel.getOffsetX() + j * tileSize, gameModel.getOffsetY() + i * tileSize, tileSize, tileSize);
			}
		}
		
		//player
		
		int offsetX = gameModel.getOffsetX();
		int offsetY = gameModel.getOffsetY();
		
		imageGraphics.setColor(Color.RED);
		imageGraphics.fillRect(
				offsetX + (int) player.getX() - player.getWidth() / 2,
				offsetY + (int) player.getY() - player.getHeight() / 2,
				player.getWidth(), player.getHeight());
		
		//projectiles
		for (int i = 0; i < gameModel.projectiles.size(); i++) {
			imageGraphics.setColor(Color.BLACK);
			int x = (int) gameModel.projectiles.get(i).getX();
			int y = (int) gameModel.projectiles.get(i).getY();
			int r = gameModel.projectiles.get(i).getWidth();
			imageGraphics.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
		}
		
	}
	
	
	public void draw() {
		Graphics2D panel = (Graphics2D) this.getGraphics();
		panel.drawImage(mainImage, 0, 0 , null);
		panel.dispose();
	}
}
