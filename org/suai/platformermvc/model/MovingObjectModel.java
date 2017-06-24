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
	//private double maxFallingSpeed;
	//private double stopSpeed;
	//private double jumpSpeed;
	//private double gravity;
	
	//private boolean left;
	//private boolean right;
	//private boolean jumping;
	//private boolean falling;

	//protected boolean topLeft;
	//protected boolean topRight;
	//protected boolean bottomLeft;
	//protected boolean bottomRight;
	
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
	//public abstract void blockOnLeft();
	//public abstract void blockOnRight();
	//public abstract void blockBelow();
	//public abstract void blockAbove();
	
	public void collisionCheck() {

		//System.out.println();
		for (int i = 0;i < map.getBlocksAmount(); i++) {
			bodyNext = getNextPosRect();
			body = getRectangle();
			Rectangle other = (Rectangle) map.getBlock(i);
			
			/*double wy = (body.width + other.width) * (body.getCenterY() - other.getCenterY());
			double hx = (body.height + other.height) * (body.getCenterX() - other.getCenterY());
			boolean top = false, left = false, right = false, bottom = false;
			
			if (wy > hx) {
				if (wy > -hx) {
					top = true;
				} else {
					left = true;
				}
			} else {
				if (wy > -hx) {
					right = true;
				} else {
					bottom = true;
				}
			}*/
			if (body.intersects(other)) {
				onCollision(body.intersection(other));
				break;
			}
			if (bodyNext.intersects(other)) {
				onCollision(body.intersection(other));
				break;
				
				/*Rectangle intersection = body.intersection(other);
				System.out.println(getRectangle());
				System.out.println(body);
				System.out.println(other);
				System.out.println(intersection);
				if (intersection.x == body.x) {
					tempX = intersection.x + intersection.width + width / 2; 
					shiftX = 0;
				} else if (intersection.x == body.x + width - intersection.width) {
					tempX =  intersection.x - width / 2;
					shiftX = 0;
				}
				
				if (intersection.y == body.y + height - intersection.height) {
					tempY = intersection.y - height / 2; 
					shiftY = 0;
				} else if (intersection.y == body.y) {
					tempY =  intersection.y + intersection.height + height / 2;
					shiftY = 0;
				}*/
			}
		}
	}
	
	public void update() {
		tempX = xPos;
		tempY = yPos;
		respondToChangingConditions();
		
		collisionCheck();
				
		xPos = tempX;
		yPos = tempY;
		body.x = (int) xPos;
		body.y = (int) yPos;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(((int) xPos - width / 2), (int) yPos - height / 2, width, height);
	}
	
	public Rectangle getNextPosRect() {
		return new Rectangle((int) (xPos - width / 2 + shiftX), (int) (yPos - height / 2 + shiftY), width, height);
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

