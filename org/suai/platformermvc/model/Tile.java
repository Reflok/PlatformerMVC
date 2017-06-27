package org.suai.platformermvc.model;

import java.awt.Rectangle;

public class Tile implements Collidable {
	private int x;
	private int y;
	private int width;
	private int height;
	
	
	public Tile(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
