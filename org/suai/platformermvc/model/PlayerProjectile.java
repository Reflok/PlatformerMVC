package org.suai.platformermvc.model;

import org.suai.platformermvc.model.states.GameState1;

public class PlayerProjectile extends ProjectileModel {

	public PlayerProjectile(int x, int y, int width, int height, GameState1 map, double direction) {
		super(x, y, width, height, map, direction);
	}


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
