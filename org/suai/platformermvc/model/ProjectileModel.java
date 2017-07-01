package org.suai.platformermvc.model;

import org.suai.platformermvc.model.states.GameState1;

public class ProjectileModel extends MovingObjectModel {
	
	private double angle;
	
	private boolean destroy = false;
	private boolean toDestroy = false;
	
	private int damage;
	
	public ProjectileModel(int x, int y, int width, int height, GameState1 map, double direction) {
		super(x, y, width, height, map);
		setAngle(direction);
	}

	@Override
	public void init() {		
		moveSpeed = 7;
		maxMoveSpeed = 15;  
		damage = 1;

	}

	
	public void setAngle(double direction) {
		angle = direction;
		shiftX = Math.cos(Math.toRadians(angle)) * moveSpeed;
		shiftY = Math.sin(Math.toRadians(angle)) * moveSpeed;
	}
	
	
	@Override
	public void respondToChangingConditions() {
		collisionCheck();
		if (toDestroy || destroy) {
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

	
	public void collisionCheck() {
		for (int i = 0; i < map.getEnemyNum(); i++) {
			EnemyModel enemy = map.getEnemy(i);
			if (getRectangle().intersects(enemy.getRectangle())){ 
				enemy.takeDamage(damage);
				destroy = true;
				
				return;
			}
		}
	}
}
