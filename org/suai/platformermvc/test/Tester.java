package org.suai.platformermvc.test;

import static org.junit.Assert.assertEquals;

import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;
import org.suai.platformermvc.model.EnemyProjectile;
import org.suai.platformermvc.model.PlayerModel;
import org.suai.platformermvc.model.states.GameState1;
import org.suai.platformermvc.model.states.GameStateManager;

public class Tester {
	private GameState1 game;
	private GameStateManager gsm;
	
	@Before
	public void setUp() throws Exception {
		gsm = new GameStateManager();
		game = new GameState1(gsm);
	}
	
	@Test
	public void testGame() {
		game.init(2, 50, 40);
		int x = (int) game.getPlayer().getX();
		int y = (int) game.getPlayer().getY();
		assertEquals("must be 50", 50, x);
		assertEquals("must be 40", 40, y);
	}

	
	@Test
	public void testGameStateManager() {
		gsm.setState(GameStateManager.GAMESTATE1);
		assertEquals("must be equal", gsm.getCurrentState(), GameStateManager.GAMESTATE1);
		

		gsm.setState(GameStateManager.LOADSTATE);
		assertEquals("must be equal", gsm.getCurrentState(), GameStateManager.LOADSTATE);

		gsm.setState(GameStateManager.MENUSTATE);
		assertEquals("must be equal", gsm.getCurrentState(), GameStateManager.MENUSTATE);

		gsm.setState(GameStateManager.PAUSESTATE);
		assertEquals("must be equal", gsm.getCurrentState(), GameStateManager.PAUSESTATE);

		gsm.setState(GameStateManager.SAVESTATE);
		assertEquals("must be equal", gsm.getCurrentState(), GameStateManager.SAVESTATE);
		
		gsm.getState(GameStateManager.SAVESTATE).keyPressed(KeyEvent.VK_ESCAPE);
		assertEquals("must be equal", gsm.getCurrentState(), GameStateManager.GAMESTATE1);
		
		gsm.getState(GameStateManager.GAMESTATE1).keyPressed(KeyEvent.VK_ESCAPE);
		assertEquals("must be equal", gsm.getCurrentState(), GameStateManager.PAUSESTATE);
		
	}
	
	@Test
	public void testPlayerProjectileCollision() {
		PlayerModel player = game.getPlayer();
		player.setHealth(1);
		
		while (player.getFalling()) {
			game.update();
		}
		
		game.addProjectile(new EnemyProjectile((int) player.getX() + 20, (int) player.getY(), 3, 3, game, 180));
		
		for (int i = 0; i < 10; i++) {
			game.update();
		}
		
		assertEquals("must be equal", player.getHealth(), 0);
		
		
	}
}