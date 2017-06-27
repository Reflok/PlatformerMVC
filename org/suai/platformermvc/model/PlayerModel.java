package org.suai.platformermvc.model;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import org.suai.platformermvc.model.states.GameState1;

public class PlayerModel extends MovingObjectModel {
	public PlayerModel(int x, int y, int width, int height, GameState1 map) {
		super(x, y, width, height, map);
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
	
	private boolean facingRight;
	//boolean topLeft;
	//boolean topRight;
	//boolean bottomLeft;
	//boolean bottomRight;
	
	//private Color color1;
	//private Color color2;
	
	
	public void init() {
		
		//if in air
		falling = true;

		moveSpeed = 1.5;
		maxMoveSpeed = 4;  
		maxFallingSpeed = 10;
		stopSpeed = 1;
		jumpSpeed = -10;
		gravity = 0.32;
		
		//shifts per frame in X and Y directions
		shiftX = 0;
		shiftY = 0;
		
		color1 = new Color(128, 0, 0);
		color2 = new Color(0, 0, 128);
	}
	
	
	public void collisionCheck() {
	
	}
	
	
	public void respondToChangingConditions() {

		falling = true;
		double tmpY = shiftY;
		double tmpX = shiftX;
		shiftY = 1;
		shiftX = 0;
		body = getNextPosRect();
		calculateCorners(xPos, yPos + 1);
		if (bottomLeft || bottomRight) {
			falling = false;
		}
		/*for (int i = 0;i < map.getTileAmount(); i++) {
			Rectangle other = (Rectangle) map.getTile(i).getRectangle();
			if (body.intersects(other)) {
				falling = false;
				break;
			}
		}*/
		
		shiftX = tmpX;
		shiftY = tmpY;
		
		
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
		
		tempY += shiftY;
		tempX += shiftX;
		
		//jumping = false;
	}
	
	
	public void setJumping(boolean val) {
			jumping = val;
	}
	
	
	public void blockOnLeft() {
		System.out.println("left");
		shiftX = 0; // stop moving
		tempX = currentCol * map.getTileSize() + width / 2; // fix position
		
	}


	public void blockOnRight() {
		shiftX = 0;
		tempX = (currentCol + 1) * map.getTileSize() - width / 2; // fix position
		
	}


	public void blockBelow() {
		shiftY = 0; //stop moving down
		falling = false; // player must stand on ground
		calculateCorners((int) xPos + shiftX, (int) yPos + shiftY);
		if (map.getMapData(currentRow + 1, currentCol) != 0 && (bottomLeft || bottomRight)) {
			currentRow++;
		}
		tempY = (currentRow + 1) * map.getTileSize()  -  height / 2; // fix player Y position on block belowd
		
	}


	public void blockAbove() {
		shiftY = 0; //stop moving up
		tempY = currentRow * map.getTileSize() + height / 2; // fix player Y position under block above
		
	}
	
	public void keyPressed(int code) {
		if (code == KeyEvent.VK_A) {
			left = true;
			facingRight = false;
			//right = false;
		}
		
		if (code == KeyEvent.VK_D) {
			right = true;
			facingRight = true;
			//left = false;
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
	
	@Override
	public void onCollision(Rectangle intersection) {
		// TODO Auto-generated method stub
		
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
	public boolean getFacingRight() {return facingRight; }
	
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
