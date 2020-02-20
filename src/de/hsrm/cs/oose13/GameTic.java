package de.hsrm.cs.oose13;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class GameTic implements ActionListener {
	GamePanel panel;
	

	public GameTic(GamePanel gamePanel) {
		this.panel = gamePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.game.moveAll();
		panel.game.collisions();
		panel.repaint();

		switch (panel.game.checkGame()) {
		case 0:
			panel.newGame();
			break;
		case 1:
			Object[] option = { "OK" };
			JOptionPane.showOptionDialog(null, "Herzlichen Glueckwunsch",
					"Sieg", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
			panel.timer.stop();
			panel.game.beginn.stop();
			panel.game.death.stop();
			panel.game.intermission.stop();
			panel.f.dispose();
			panel.menu.startGame(panel.menu.lvl++);

		default:
			break;
		}
	}

}