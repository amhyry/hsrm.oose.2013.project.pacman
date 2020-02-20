package de.hsrm.cs.oose13;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.hsrm.cs.oose13.basics.Direction;

public class GameKeyListener implements KeyListener {
	PlayLogic game;
	Direction dir;

	public GameKeyListener(PlayLogic game2) {
		this.game = game2;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			dir = Direction.EAST;
			game.player.setMovement(dir);
			break;

		case KeyEvent.VK_LEFT:
			dir = Direction.WEST;
			game.player.setMovement(dir);
			break;

		case KeyEvent.VK_UP:
			dir = Direction.NORTH;
			game.player.setMovement(dir);
			break;

		case KeyEvent.VK_DOWN:
			dir = Direction.SOUTH;
			game.player.setMovement(dir);
			break;

		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}