package org.suai.platformermvc.model;

import java.awt.Color;
import java.awt.Rectangle;

import org.suai.platformermvc.model.states.GameState1;

public abstract class MovingObjectModel implements Collidable {
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
	//private double maxFallingSpeed;
	//private double stopSpeed;
	//private double jumpSpeed;
	//private double gravity;
	
	//private boolean left;
	//private boolean right;
	//private boolean jumping;
	//private boolean falling;

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
		
		/*//if in ai
		falling = true;
		
		//position
		xPos = x;
		yPos = y;
		
		//metrics
		width = 25;
		height = 25;
		
		//speeds
		moveSpeed = 3;
		maxMoveSpeed = 8;  
		maxFallingSpeed = 12;
		stopSpeed = 0.3;
		jumpSpeed = -11;
		gravity = 0.64;
		
		//shifts per frame in X and Y directions
		shiftX = 0;
		shiftY = 0;
		
		color1 = new Color(128, 0, 0);
		color2 = new Color(0, 0, 128);*/
	}
	
	public abstract void init ();
	
	public abstract void respondToChangingConditions();

	public abstract void onCollision(Rectangle other);
	public abstract void blockOnLeft();
	public abstract void blockOnRight();
	public abstract void blockBelow();
	public abstract void blockAbove();
	
	
	
	public void update() {
		tempX = xPos;
		tempY = yPos;
		
		respondToChangingConditions();
		tileCheck();
		collisionCheck();
				
		xPos = tempX;
		yPos = tempY;
		
		body.x = (int) xPos;
		body.y = (int) yPos;
	}
	
	
	public void collisionCheck() {

		for (int i = 0;i < map.getTileAmount(); i++) {
			//bodyNext = getNextPosRect();
			body = getRectangle();
			Rectangle other = (Rectangle) map.getTile(i).getRectangle();
			
			if (body.intersects(other)) {
				onCollision(body.intersection(other));
				break;
			}
		}
	}
	
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
	
	
	/*public void move() {
		xPos += shiftX;
		yPos += shiftY;
	}*/
	
	
	/*public void gotHit(int damage) {
		health -= damage;
	}*/
	
	
	/*public void setJumping(boolean val) {
			jumping = val;
	}*/
	
	
	//getters
	public double getX() { return xPos; }
	public double getY() { return yPos; }
	public double getShiftX() { return shiftX; }
	public double getShiftY() { return shiftY; }
	//public double getMaxMoveSpeed() { return maxMoveSpeed; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public Color getColor1() { return color1; }
	public Color getColor2() { return color2; }
	//public int getHealth() { return health; }
	//public double getJumpSpeed() { return jumpSpeed; }
	//public boolean getFalling() { return falling; }
		
	
	//setters
	//public void setX(double x) { xPos = x; }
	//public void setY(double y) { yPos = y; }
	public void setShiftX(double speed) { shiftX = speed; }
	public void setShiftY(double speed) { shiftY = speed; }
	//public void setMaxMoveSpeed(int maxMoveSpeed) { this.maxMoveSpeed = maxMoveSpeed; }
	public void setWidth(int val) { width = val; }
	public void setHeight(int val) { height = val; }
	public void setColor1(Color color) { color1 = color; }
	public void setColor2(Color color) { color2 = color; }
	///public void setHealth(int hp) { health = hp;}
	//public void setJumpSpeed(int val) { jumpSpeed = val; }
	//public void setLeft(boolean val) { left = val; }
	//public void setRight(boolean val) { right = val; }
	//public void setFalling(boolean val) { falling = val; }
	

}

