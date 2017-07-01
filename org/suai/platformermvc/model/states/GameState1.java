
package org.suai.platformermvc.model.states;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.suai.platformermvc.model.Collidable;
import org.suai.platformermvc.model.EnemyModel;
import org.suai.platformermvc.model.PlayerModel;
import org.suai.platformermvc.model.ProjectileModel;
import org.suai.platformermvc.model.Tile;


public class GameState1 implements State {
	private PlayerModel player;
	
	private GameStateManager gsm;
	
	private ArrayList<Tile> blocks;
	private ArrayList<ProjectileModel> projectiles;
	private ArrayList<EnemyModel> enemies;
	
	private int[][] mapData;
	
	private boolean gameOver = false;
	
	private int xOffset;
	private int yOffset;
	
	private int mapWidth;
	private int mapHeight;
	
	private int tileSize;
	
	private String pathToMap;
		
	private boolean canShoot = true;

	
	public GameState1(GameStateManager gsm, String path) {
		pathToMap = path;
		this.gsm = gsm;
		init();
	}
	
	
	private void init() {
		projectiles = new ArrayList<ProjectileModel>();
		blocks = new ArrayList<Tile>();
		enemies = new ArrayList<EnemyModel>();
		
		try {
			player = new PlayerModel(70, 70, 22, 22, this);
			
			
			
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
						blocks.add(new Tile(j * tileSize, i * tileSize, tileSize, tileSize));
					}
				}
				
				
			}
			
			enemies.add(new EnemyModel(500, 50, 22, 22, this));
			enemies.add(new EnemyModel(600, 50, 22, 22, this));
			enemies.add(new EnemyModel(700, 50, 22, 22, this));
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	public void update() {
		//player update
		if (gameOver) {
			init();
			gameOver = false;
		}
		player.update();
		if (player.getHealth() <= 0) {
			gsm.setState(GameStateManager.MENUSTATE);
			gameOver = true;
		}
		//projectiles update
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			boolean destroy = projectiles.get(i).getDestroy();
			if (destroy) {
				projectiles.remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < enemies.size(); i++) {
			boolean destroy = enemies.get(i).getDestroy();
			if (destroy) {
				enemies.remove(i);
				i--;
			}
		}
	}
	
	
	public void collision(Collidable obj1, Collidable obj2) {
		
	}
	
	
	public ProjectileModel getProjectile(int n) { return projectiles.get(n); }
	public int getProjectilesNum() { return projectiles.size(); }
	
	public void mousePressed(int x, int y) {
		
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
	public int getTileAmount() { return blocks.size(); }
	public Tile getTile(int n) { return blocks.get(n); }
	public int getEnemyNum() { return enemies.size(); }
	public EnemyModel getEnemy(int i) { return enemies.get(i); }

	//setter
	public void setOffsetX(int offset){ xOffset = offset; }
	public void setOffsetY(int offset){ yOffset = offset; }
	public int getColFromCoord(int x) { return x / tileSize; }
	public int getRowFromCoord(int y) { return y / tileSize; } 
}
	