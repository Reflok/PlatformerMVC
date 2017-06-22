package org.suai.platformermvc.model;

public class ProjectileModel extends MovingObjectModel {
	
	private double angle;
	
	private boolean destroy = false;
	
	public ProjectileModel(int x, int y, GameModel map, double direction) {
		super(x, y, map);
		angle = direction;
	}

	@Override
	public void init(int x, int y, GameModel map) {
		this.map = map;
		xPos = x;
		yPos = y;
		
		width = 5;
		height = 5;
		
		moveSpeed = 13;
		maxMoveSpeed = 13;  
		
		shiftX = Math.cos(Math.toRadians(angle)) * moveSpeed;
		shiftY = Math.sin(Math.toRadians(angle)) * moveSpeed;

	}

	@Override
	public void respondToChangingConditions() {
		shiftX = Math.cos(Math.toRadians(angle)) * moveSpeed;
		shiftY = Math.sin(Math.toRadians(angle)) * moveSpeed;
		
	}

	@Override
	public void blockOnLeft() {
		destroy = true;
		
	}

	@Override
	public void blockOnRight() {
		destroy = true;
		
	}

	@Override
	public void blockBelow() {
		destroy = true;
		
	}

	@Override
	public void blockAbove() {
		destroy = true;
		
	}

		
	public boolean getDestroy() { return destroy; }
}
