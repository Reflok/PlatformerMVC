package org.suai.platformermvc.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class GameModel {
	private PlayerModel player;
	
	private int[][] mapData;
	
	private int xOffset;
	private int yOffset;
	
	private int mapWidth;
	private int mapHeight;
	
	private int tileSize;
	
	private String pathToMap = "/home/aleph/EclipseProjects/PlatformerMVC/src/org/suai/platformermvc/model/map.txt";
		
	public ArrayList<ProjectileModel> projectiles;
	
	public GameModel() {
		init();
	}
	
	
	public GameModel(String path) {
		pathToMap = path;
		init();
	}
	
	
	private void init() {
		projectiles = new ArrayList<ProjectileModel>();
		
		try {
			player = new PlayerModel(60, 60, this);
			
			tileSize = 20;
			
			BufferedReader reader = new BufferedReader(new FileReader(pathToMap));
			
			mapWidth = Integer.parseInt(reader.readLine());
			mapHeight = Integer.parseInt(reader.readLine());
			
			mapData = new int[mapHeight][mapWidth];
			
			String delim = " ";
			
			for (int i = 0; i < mapHeight; i++) {
				String[] tokens = reader.readLine().split(delim);
				for (int j = 0; j < mapWidth; j++) {
					mapData[i][j] = Integer.parseInt(tokens[j]);
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
		
		//projectiles update
		for (int i = 0; i < projectiles.size(); i++) {
			boolean destroy = projectiles.get(i).getDestroy();
			if (destroy) {
				projectiles.remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
	}
	
	
	public void addProjectile(int x, int y, double angle) {
		projectiles.add(new ProjectileModel(x, y, this, angle));
		
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
	

	//setter
	public void setOffsetX(int offset){ xOffset = offset; }
	public void setOffsetY(int offset){ yOffset = offset; }
	public int getColFromCoord(int x) { return x / tileSize; }
	public int getRowFromCoord(int y) { return y / tileSize; } 
}
	