package org.suai.platformermvc.model;

import java.awt.Color;
import java.awt.event.KeyEvent;

import org.suai.platformermvc.model.states.GameState1;

public class PlayerModel extends MovingObjectModel {
	public PlayerModel(int x, int y, int width, int height, GameState1 map) {
		super(x, y, width, height, map);
	}
	private int health = 100;

	private double maxFallingSpeed;
	private double stopSpeed;
	private double jumpSpeed;
	private double gravity;
	
	private boolean left;
	private boolean right;
	private boolean jumping;
	private boolean falling;
	private boolean invincible;
	private boolean facingRight;
	private long invincibilityTimer;
	private long invincibilityTime;
	
	public void init() {
		
		//if in air
		falling = true;
		invincible = false;
		moveSpeed = 1.5;
		maxMoveSpeed = 3;  
		maxFallingSpeed = 8;
		stopSpeed = 1;
		jumpSpeed = -10;
		gravity = 0.32;
		invincibilityTime = 500;
		//shifts per frame in X and Y directions
		shiftX = 0;
		shiftY = 0;
		invincibilityTimer = 0;
		color1 = new Color(128, 0, 0);
		color2 = new Color(0, 0, 128);
	}
	
	
	public void collisionCheck() {
		for (int i = 0; i < map.getEnemyNum(); i++) {
			EnemyModel enemy = map.getEnemy(i);
			if (getRectangle().intersects(enemy.getRectangle()) && !invincible){ 
				invincibilityTimer = System.currentTimeMillis();
				
				health--;
				int pushBack = 15;
				
				if (xPos < enemy.getX()) {
					pushBack = -15;
				} else if (xPos ==  enemy.getX()) {
					shiftX = 0;
				}
				shiftX = pushBack;
				
				pushBack = 15;
				if (yPos < enemy.getY()) {
					pushBack = 0;
				} else if(yPos == enemy.getY()) {
					pushBack = 0;
				}
				
				shiftY = pushBack;
				left = false;
				right = false;
				
				return;
			}
		}
	}
	
	
	public void respondToChangingConditions() {
		collisionCheck();
		invincible = System.currentTimeMillis() - invincibilityTimer < invincibilityTime;
		
		if (health <= 0) {
			
			return;
		}
		
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
		calculateCorners(xPos, yPos + 1);
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
	

	public boolean getLeft() { return left; }
	public boolean getRight() { return right; }
	public int getHealth() { return health; }
	public double getJumpSpeed() { return jumpSpeed; }
	public boolean getFalling() { return falling; }
	public boolean getFacingRight() {return facingRight; }
	
	public void setHealth(int hp) { health = hp;}
	public void setJumpSpeed(int val) { jumpSpeed = val; }
	public void setLeft(boolean val) { left = val; }
	public void setRight(boolean val) { right = val; }
	public void setFalling(boolean val) { falling = val; }

}
