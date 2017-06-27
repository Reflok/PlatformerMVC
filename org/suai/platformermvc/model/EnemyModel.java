package org.suai.platformermvc.model;

import java.awt.Color;
import java.awt.Rectangle;

import org.suai.platformermvc.model.states.GameState1;

public class EnemyModel extends MovingObjectModel {
	private double maxFallingSpeed;
	private double stopSpeed;
	private double jumpSpeed;
	private double gravity;
	
	private boolean left;
	private boolean right;
	private boolean jumping;
	private boolean falling;
	
	public EnemyModel(int x, int y, int width, int height, GameState1 map) {
		super(x, y, width, height, map);
	}

	@Override
	public void init() {
		falling = true;

		moveSpeed = 1;
		maxMoveSpeed = 2;  
		maxFallingSpeed = 10;
		stopSpeed = 2;
		jumpSpeed = -10;
		gravity = 0.32;
		
		right = false;
		left = true;
		
		//shifts per frame in X and Y directions
		shiftX = 0;
		shiftY = 0;
		
		color1 = new Color(128, 0, 0);
		color2 = new Color(0, 0, 128);
		
	}

	@Override
	public void respondToChangingConditions() {
		calculateCorners(xPos, yPos + 1);
		falling = true;
		
		
		if (bottomLeft || bottomRight) {
			falling = false;
		}

		calculateCorners(xPos + shiftX, yPos + 1);
		if (!falling && (!bottomLeft || !bottomRight)) {
			boolean temp = left;
			left = right;
			right = temp;
		}
		
		/*
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
		}*/
		if (right) {
			shiftX = maxMoveSpeed;
		} else {
			shiftX = -maxMoveSpeed;
		}
		
		if (falling) {
			shiftY  += gravity;
			
			if (shiftY > maxFallingSpeed) {
				shiftY = maxFallingSpeed;
			}
		} else {
			shiftY = 0;
		}
		System.out.println(shiftX);
		System.out.println(left);
		tempY += shiftY;
		tempX += shiftX;
	}

	@Override
	public void onCollision(Rectangle other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void blockOnLeft() {
		right = true;
		left = false;
		
	}

	@Override
	public void blockOnRight() {
		right = false;
		left = true;
		
	}

	@Override
	public void blockBelow() {
		shiftY = 0; //stop moving down
		falling = false; // player must stand on ground
		calculateCorners((int) xPos + shiftX, (int) yPos + shiftY);
		if (map.getMapData(currentRow + 1, currentCol) != 0 && (bottomLeft || bottomRight)) {
			currentRow++;
		}
		tempY = (currentRow + 1) * map.getTileSize()  -  height / 2;
		
	}

	@Override
	public void blockAbove() {
		// TODO Auto-generated method stub
		
	}
}
