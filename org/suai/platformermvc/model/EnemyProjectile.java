package org.suai.platformermvc.model;

import org.suai.platformermvc.model.states.GameState1;

public class EnemyProjectile extends ProjectileModel {

	public EnemyProjectile(int x, int y, int width, int height, GameState1 map, double direction) {
		super(x, y, width, height, map, direction);
	}

	@Override
	public void collisionCheck() {
		PlayerModel player = map.getPlayer();
		
		if (getRectangle().intersects(player.getRectangle())){ 
			if (angle == 0) {
				player.takeDamage(damage, 10, 0);
			} else {
				player.takeDamage(damage, -10, 0);
			}
			destroy = true;
			
			return;
		}
	}

}
