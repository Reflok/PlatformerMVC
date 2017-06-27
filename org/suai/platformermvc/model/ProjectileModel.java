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
		
	}

	public void blockOnLeft() {
		shiftX = 0; // stop moving
		tempX = currentCol * map.getTileSize() + width; // fix position
		toDestroy = true;
		
	}


	public void blockOnRight() {
		shiftX = 0;
		tempX = (currentCol + 1) * map.getTileSize() - width; // fix position
		toDestroy = true;
		
	}


	public void blockBelow() {
		
	}


	public void blockAbove() {
		
	}

		
	public boolean getDestroy() { return destroy; }

	@Override
	public void onCollision(Rectangle intersection) {
		
		
	}
}
