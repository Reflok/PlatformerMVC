package org.suai.platformermvc.model;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class PlayerModel extends MovingObjectModel {
	public PlayerModel(int x, int y, GameModel map) {
		super(x, y, map);
	}
	//private GameModel map;
	
	//private double xPos;
	//private double yPos;
	
	//private double shiftX;
	//private double shiftY;
	
	//private int width;
	//private int height;

	private int health = 1;

	//private double moveSpeed;
	//private double maxMoveSpeed;
	private double maxFallingSpeed;
	private double stopSpeed;
	private double jumpSpeed;
	private double gravity;
	
	private boolean left;
	private boolean right;
	private boolean jumping;
	private boolean falling;

	//boolean topLeft;
	//boolean topRight;
	//boolean bottomLeft;
	//boolean bottomRight;
	
	//private Color color1;
	//private Color color2;
	
	
	public void init(int x, int y, GameModel map) {
		this.map = map;
		System.out.println(this.map);
		//if in air
		falling = true;
		
		//position
		xPos = x;
		yPos = y;
		
		//metrics
		width = 19;
		height = 19;
		
		//speeds
		moveSpeed = 3;
		maxMoveSpeed = 8;  
		maxFallingSpeed = 13;
		stopSpeed = 0.5;
		jumpSpeed = -15;
		gravity = 0.64;
		
		//shifts per frame in X and Y directions
		shiftX = 0;
		shiftY = 0;
		
		color1 = new Color(128, 0, 0);
		color2 = new Color(0, 0, 128);
	}
	
	
	public void respondToChangingConditions() {
		
		if (!falling) {
			calculateCorners(xPos, yPos + 3); //check ground directly below
			if (!bottomRight && ! bottomLeft) { // if nothing below
				falling = true; // set player falling
			}
		}
		
		if (left) {
			shiftX -= moveSpeed;
			
			if(shiftX < -maxMoveSpeed) { 
				shiftX = -maxMoveSpeed;
				}
		} else if (right) {
			shiftX += moveSpeed;
			
			if (shiftX > maxMoveSpeed) { 
				shiftX = maxMoveSpeed; 
				}
		} else {
			if (shiftX > 0) {
				shiftX -= stopSpeed;
				
				if (shiftX < 0) {
					shiftX = 0;
				}
			} else if (shiftX < 0) {
				shiftX += stopSpeed;
				
				if (shiftX > 0) {
					shiftX = 0;
				}
			}
		}
		
		if (right && left) {
			shiftX = 0;
		}
		
		if (jumping && !falling) {
			shiftY = jumpSpeed;
			falling = true;
			//jumping = false;
		}
		if (falling) {
			shiftY  += gravity;
			
			if (shiftY > maxFallingSpeed) {
				shiftY = maxFallingSpeed;
			}
		} else {
			shiftY = 0;
		}
		
		//jumping = false;
	}
	
	
	/*public void gotHit(int damage) {
		health -= damage;
	}*/
	
	
	public void setJumping(boolean val) {
			jumping = val;
	}
	
	
	@Override
	public void blockOnLeft() {
		shiftX = 0; // stop moving
		tempX = currentCol * map.getTileSize() + width / 2; // fix position
		
	}


	@Override
	public void blockOnRight() {
		shiftX = 0;
		tempX = (currentCol + 1) * map.getTileSize() - width / 2; // fix position
		
	}


	@Override
	public void blockBelow() {
		shiftY = 0; //stop moving down
		falling = false; // player must stand on ground
		tempY = (currentRow + 1) * map.getTileSize()  -  height / 2; // fix player Y position on block belowd
		
	}


	@Override
	public void blockAbove() {
		shiftY = 0; //stop moving up
		tempY = currentRow * map.getTileSize() + height / 2; // fix player Y position under block above
		
	}
	
	public void keyPressed(int code) {
		if (code == KeyEvent.VK_A) {
			left = true;
		}
		
		if (code == KeyEvent.VK_D) {
			right = true;
		}
		
		if (code == KeyEvent.VK_W) {
			jumping = true;
		}
	}
	
	public void keyReleased(int code) {
		if (code == KeyEvent.VK_A) {
			left = false;
		}
		
		if (code == KeyEvent.VK_D) {
			right = false;
		}
		
		if (code == KeyEvent.VK_W) {
			jumping = false;
		}
	}
	
	
	//getters
	//public double getX() { return xPos; }
	//public double getY() { return yPos; }
	//public double getShiftX() { return shiftX; }
	//public double getShiftY() { return shiftY; }
	//public double getMaxMoveSpeed() { return maxMoveSpeed; }
	//public int getWidth() { return width; }
	//public int getHeight() { return height; }
	//public Color getColor1() { return color1; }
	//public Color getColor2() { return color2; }
	public boolean getLeft() { return left; }
	public boolean getRight() { return right; }
	public int getHealth() { return health; }
	public double getJumpSpeed() { return jumpSpeed; }
	public boolean getFalling() { return falling; }
		
	
	//setters
	//public void setX(double x) { xPos = x; }
	//public void setY(double y) { yPos = y; }
	//public void setShiftX(double speed) { shiftX = speed; }
	//public void setShiftY(double speed) { shiftY = speed; }
	//public void setMaxMoveSpeed(int maxMoveSpeed) { this.maxMoveSpeed = maxMoveSpeed; }
	//public void setWidth(int val) { width = val; }
	//public void setHeight(int val) { height = val; }
	//public void setColor1(Color color) { color1 = color; }
	//public void setColor2(Color color) { color2 = color; }
	public void setHealth(int hp) { health = hp;}
	public void setJumpSpeed(int val) { jumpSpeed = val; }
	public void setLeft(boolean val) { left = val; }
	public void setRight(boolean val) { right = val; }
	public void setFalling(boolean val) { falling = val; }


	
	

}
