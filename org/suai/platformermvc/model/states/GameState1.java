
package org.suai.platformermvc.model.states;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.suai.platformermvc.model.EnemyModel;
import org.suai.platformermvc.model.PlayerModel;
import org.suai.platformermvc.model.PlayerProjectile;
import org.suai.platformermvc.model.ProjectileModel;
import org.suai.platformermvc.model.Tile;


public class GameState1 implements State {
	private PlayerModel player;
	
	private GameStateManager gsm;
	
	private ArrayList<Tile> blocks;
	private ArrayList<ProjectileModel> projectiles;
	private ArrayList<EnemyModel> enemies;
	
	private int[][] mapData;
	
	private int xOffset;
	private int yOffset;
	
	private int mapWidth;
	private int mapHeight;
	private int currentLvl = 1;
	private int tileSize;
	
	private String pathToMap;
	private String[] paths = {"/home/aleph/EclipseProjects/PlatformerMVC/src/org/suai/platformermvc/model/map1.txt", 
			"/home/aleph/EclipseProjects/PlatformerMVC/src/org/suai/platformermvc/model/map2.txt"};
	private boolean canShoot = true;
	private boolean gameOver = false;
	
	public GameState1(GameStateManager gsm) {
		pathToMap = paths[currentLvl - 1];
		this.gsm = gsm;
		init(currentLvl, 70, 70);
	}
	
	
	private void init(int level, int x, int y) {
		currentLvl = level;
		pathToMap = paths[level - 1];
		projectiles = new ArrayList<ProjectileModel>();
		blocks = new ArrayList<Tile>();
		enemies = new ArrayList<EnemyModel>();
		player = new PlayerModel(x, y, 22, 22, this);
		tileSize = 20;
		xOffset = 0;
		yOffset = 0;
		
		try {
			
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
					} else if (mapData[i][j] == 2) {
						enemies.add(new EnemyModel(j * tileSize + 11, i * tileSize, 22, 22, this));
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
		if (player.getX() > tileSize * mapWidth) {
			init(currentLvl + 1, (int) player.getWidth(), (int)player.getY() );
			return;
		}
		if (player.getX() < 0) {
			init(currentLvl - 1, tileSize * mapWidth - (int)player.getWidth(), (int)player.getY()) ;
			return;
		}
		
		
		if (gameOver) {
			init(1, 70, 70);
			gameOver = false;
		}
		player.update();
		
		if (player.getHealth() <= 0) {
			//reload = true;
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
	
	
	public ProjectileModel getProjectile(int n) { return projectiles.get(n); }
	public int getProjectilesNum() { return projectiles.size(); }
	
	public void mousePressed(int x, int y) {
		
	}
	
	
	public void keyPressed(int code) {
		if (code == KeyEvent.VK_ESCAPE){
			gsm.setState(GameStateManager.PAUSESTATE);
		}
		
		if (code == KeyEvent.VK_SPACE && canShoot) {
			double angle = 180;
			
			if (player.getFacingRight()) {
				angle = 0;
			}
			
			projectiles.add(new PlayerProjectile((int) player.getX(), (int) player.getY(), 3, 3, this, angle));
			
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
	public int getMapData(int row, int col){ 
		if (row >= mapData.length || col >= mapData[0].length || row < 0 || col < 0) {
			return 1;
		}
		
		return mapData[row][col]; 
	} 
	public PlayerModel getPlayer() { return player; }
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
	public void addProjectile(ProjectileModel proj) { projectiles.add(proj); }
}
	