package org.suai.platformermvc.model;

import java.awt.Rectangle;

public class LevelTransitor implements Collidable{
	private int x;
	private int y;
	private int width;
	private int height;
	
	
	public LevelTransitor(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}


	@Override
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
