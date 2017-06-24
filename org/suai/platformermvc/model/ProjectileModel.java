package org.suai.platformermvc.model;

import java.awt.Rectangle;

import org.suai.platformermvc.model.states.GameState1;

public class ProjectileModel extends MovingObjectModel {
	
	private double angle;
	
	private boolean destroy = false;
	private boolean toDestroy = false;
	
	public ProjectileModel(int x, int y, int width, int height, GameState1 map, double direction) {
		super(x, y, width, height, map);
		setAngle(direction);
	}

	@Override
	public void init() {		
		moveSpeed = 10;
		maxMoveSpeed = 15;  
		

	}

	
	public void setAngle(double direction) {
		angle = direction;
		shiftX = Math.cos(Math.toRadians(angle)) * moveSpeed;
		shiftY = Math.sin(Math.toRadians(angle)) * moveSpeed;
	}
	
	
	@Override
	public void respondToChangingConditions() {
		if (toDestroy) {
			destroy = true;
			return;
		}
		//shiftX = Math.cos(Math.toRadians(angle)) * moveSpeed;
		//shiftY = Math.sin(Math.toRadians(angle)) * moveSpeed;
		tempX += shiftX;
		tempY += shiftY;
		
	}

	/*public void blockOnLeft() {
		//destroy = true;
		shiftX = -shiftX;
		tempX = currentCol * map.getTileSize() + width / 2;
		tempX += shiftX;
	}

	public void blockOnRight() {
		//destroy = true;
		shiftX = -shiftX;
		tempX = (currentCol + 1) * map.getTileSize() - width / 2;
		tempX += shiftX;
		
	}

	public void blockBelow() {
		//destroy = true;dw
		shiftY = -shiftY;
		tempY = (currentRow + 1) * map.getTileSize()  -  height / 2;
		tempY += shiftY;
		
	}

	public void blockAbove() {
		//destroy = true;
		shiftY = -shiftY;
		tempY = currentRow * map.getTileSize() + height / 2;
		tempY += shiftY;
		
	}*/

		
	public boolean getDestroy() { return destroy; }

	@Override
	public void onCollision(Rectangle intersection) {
		/*if (intersection.x == body.x) {
			tempX = intersection.x + intersection.width + width / 2; 
			shiftY = 0;
			shiftX = 0;
		} else if (intersection.x == body.x + width - intersection.width) {
			tempX =  intersection.x - width / 2;
			shiftY = 0;
			shiftX = 0;
		}
		
		if (intersection.y == body.y + height - intersection.height) {
			tempY = intersection.y - height / 2; 
			shiftY = 0;
			shiftX = 0;
		} else if (intersection.y == body.y) {
			tempY =  intersection.y + intersection.height + height / 2;
			shiftY = 0;
			shiftX = 0;
		}*/
		destroy = true;
		//toDestroy = true;
		
	}
}
