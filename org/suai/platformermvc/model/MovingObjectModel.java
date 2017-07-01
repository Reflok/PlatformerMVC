package org.suai.platformermvc.model;

import java.awt.Color;
import java.awt.Rectangle;

import org.suai.platformermvc.model.states.GameState1;

public abstract class MovingObjectModel {
	protected GameState1 map;
	
	protected Rectangle body;
	protected Rectangle bodyNext;
	
	protected double xPos;
	protected double yPos;
	
	protected double shiftX;
	protected double shiftY;
	
	protected int width;
	protected int height;

	//private int health = 1;

	protected double moveSpeed;
	protected double maxMoveSpeed;

	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	protected Color color1;
	protected Color color2;
	
	protected double nextX;
	protected double nextY;
	
	protected double tempX;
	protected double tempY;
	
	int currentRow;
	int currentCol;
	
	
	public MovingObjectModel(int x, int y, int width, int height, GameState1 map) {
		xPos = x;
		yPos = y;
		this.width = width;
		this.height = height;
		this.map = map;
		body = new Rectangle((int) xPos, (int) yPos, width, height);
		init();
	}
	
	public abstract void init ();
	
	public abstract void respondToChangingConditions();

	public abstract void blockOnLeft();
	public abstract void blockOnRight();
	public abstract void blockBelow();
	public abstract void blockAbove();
	
	
	
	public void update() {
		tempX = xPos;
		tempY = yPos;
		
		respondToChangingConditions();
		tileCheck();
		//collisionCheck();
				
		xPos = tempX;
		yPos = tempY;
		
		body.x = (int) xPos;
		body.y = (int) yPos;
	}
	
	
	//public abstract void collisionCheck();
	
	public void tileCheck() {
		nextX = xPos + shiftX;
		nextY = yPos + shiftY;
		
		tempX = xPos;
		tempY = yPos;
		
		currentRow = map.getRowFromCoord((int) yPos);
		currentCol = map.getColFromCoord((int) xPos);
		
		calculateCorners(xPos, nextY); // check if there will be blocks in next Y position
		
		if (shiftY < 0) { // if moving up
			if (topRight || topLeft) {// if there are blocks above
				blockAbove();
			} else {
				tempY += shiftY;
			}
		}
		
		if (shiftY > 0) { // if moving down
			if (bottomLeft || bottomRight) { // if there are blocks below
				blockBelow();
			} else {
				tempY += shiftY; // move
			}
		}
		
		calculateCorners(nextX, yPos); // check if there will be blocks in next X position
		
		if (shiftX < 0) { // if moving left
			if (topLeft || bottomLeft) { // if there are blocks on the left

				blockOnLeft();
			} else {
				tempX += shiftX; // move
			}
		}
		
		if (shiftX > 0) { // if moving right
			if (topRight || bottomRight) { // if there are blocks on the right
				blockOnRight();
			} else {
				tempX += shiftX; // move
			}
		}
	}

	
	public Rectangle getRectangle() {
		return new Rectangle(((int) xPos - width / 2), (int) yPos - height / 2, width, height);
	}
	
	public Rectangle getNextPosRect() {
		return new Rectangle((int) (xPos - width / 2 + shiftX), (int) (yPos - height / 2 + shiftY), width, height);
	}
	
	
	protected void calculateCorners(double x, double y) {
		int leftTile = map.getColFromCoord((int) x - width / 2);
		int rightTile = map.getColFromCoord(((int) x + width / 2) - 1);
		int topTile = map.getRowFromCoord((int) y - height / 2);
		int bottomTile = map.getColFromCoord(((int) y + height / 2) - 1);
		
		topLeft = map.getMapData(topTile, leftTile) == 0;
		bottomLeft = map.getMapData(bottomTile, leftTile) == 0;
		topRight = map.getMapData(topTile, rightTile) == 0;
		bottomRight = map.getMapData(bottomTile, rightTile) == 0;
		
	}
	
	
	//getters
	public double getX() { return xPos; }
	public double getY() { return yPos; }
	public double getShiftX() { return shiftX; }
	public double getShiftY() { return shiftY; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public Color getColor1() { return color1; }
	public Color getColor2() { return color2; }
		
	
	//setters
	public void setShiftX(double speed) { shiftX = speed; }
	public void setShiftY(double speed) { shiftY = speed; }
	public void setWidth(int val) { width = val; }
	public void setHeight(int val) { height = val; }
	public void setColor1(Color color) { color1 = color; }
	public void setColor2(Color color) { color2 = color; }
	

}

