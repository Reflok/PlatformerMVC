
package org.suai.platformermvc.model.states;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.suai.platformermvc.model.PlayerModel;
import org.suai.platformermvc.model.ProjectileModel;


public class GameState1 implements State {
	private PlayerModel player;
	
	private GameStateManager gsm;
	
	private ArrayList<Rectangle2D> blocks;
	private ArrayList<ProjectileModel> projectiles;
	
	private int[][] mapData;
	
	private int xOffset;
	private int yOffset;
	
	private int mapWidth;
	private int mapHeight;
	
	private int tileSize;
	
	private String pathToMap;// = "/home/aleph/EclipseProjects/PlatformerMVC/src/org/suai/platformermvc/model/map.txt";
		
	private boolean canShoot = true;

	
	public GameState1(GameStateManager gsm, String path) {
		pathToMap = path;
		this.gsm = gsm;
		init();
	}
	
	
	private void init() {
		//pathToMap = = "/home/aleph/EclipseProjects/PlatformerMVC/src/org/suai/platformermvc/model/map.txt";
		projectiles = new ArrayList<ProjectileModel>();
		
		try {
			player = new PlayerModel(70, 70, 20, 20, this);
			
			blocks = new ArrayList<Rectangle2D>();
			
			tileSize = 20;
			xOffset = 0;
			yOffset = 0;
			BufferedReader reader = new BufferedReader(new FileReader(pathToMap));
			
			mapWidth = Integer.parseInt(reader.readLine());
			mapHeight = Integer.parseInt(reader.readLine());
			
			mapData = new int[mapHeight][mapWidth];
			
			String delim = " ";
			
			for (int i = 0; i < mapHeight; i++) {
				String[] tokens = reader.readLine().split(delim);
				for (int j = 0; j < mapWidth; j++) {
					mapData[i][j] = Integer.parseInt(tokens[j]);
					if (mapData[i][j] == 0){
						//cntr++;
						blocks.add(new Rectangle(j * tileSize, i * tileSize, tileSize, tileSize));
					}
				}
			}
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	public void update() {
		//player update
		player.update();
		/*for (int i = 0; i < blocks.size(); i++) {
			System.out.println(blocks.get(i));
		}*/
		//projectiles update
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			boolean destroy = projectiles.get(i).getDestroy();
			if (destroy) {
				projectiles.remove(i);
				i--;
			}
		}
		
	}
	
	public ProjectileModel getProjectile(int n) { return projectiles.get(n); }
	public int getProjectilesNum() { return projectiles.size(); }
	
	public void mousePressed(int x, int y) {
		/*double xLen = player.getX() - x;
		double yLen = player.getY() - y;
		double angle = Math.toDegrees(Math.atan2(yLen, xLen)) + 180;
		projectiles.add(new ProjectileModel((int) player.getX(), (int) player.getY(), 4, 4, this, angle));*/
		
	}
	
	
	public void keyPressed(int code) {
		if (code == KeyEvent.VK_ESCAPE){
			gsm.setState(GameStateManager.MENUSTATE);
		}
		
		if (code == KeyEvent.VK_SPACE && canShoot) {
			double angle = 180;
			
			if (player.getFacingRight()) {
				angle = 0;
			}
			
			projectiles.add(new ProjectileModel((int) player.getX(), (int) player.getY(), 3, 3, this, angle));
			
			canShoot = false;
		}
		
		player.keyPressed(code);
	}
	
	
	public void keyReleased(int code) {
		if (code == KeyEvent.VK_SPACE) {
			canShoot = true;
		}
		
		player.keyReleased(code);
	}
	
	
	//getters
	public PlayerModel getPlayer() { return player; }
	public int getMapData(int row, int col){ return mapData[row][col]; }
	public int getMapHeight(){ return mapHeight; }
	public int getMapWidth(){ return mapWidth; }
	public int getOffsetX(){ return xOffset; }
	public int getOffsetY(){ return yOffset; }
	public int getRowTile(int x) { return x / tileSize; }
	public int getColTile(int y) { return y / tileSize; }
	public int getTileSize() { return tileSize; }
	public int getBlocksAmount() { return blocks.size(); }
	public Rectangle2D getBlock(int n) { return blocks.get(n); }

	//setter
	public void setOffsetX(int offset){ xOffset = offset; }
	public void setOffsetY(int offset){ yOffset = offset; }
	public int getColFromCoord(int x) { return x / tileSize; }
	public int getRowFromCoord(int y) { return y / tileSize; } 
}
	