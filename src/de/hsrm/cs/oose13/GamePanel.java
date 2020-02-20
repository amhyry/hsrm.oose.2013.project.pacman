package de.hsrm.cs.oose13;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.Transient;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * The ImagePanel Class represents a Panel with a single Image.
 * @author      Arnold Riemer <arnold.riemer@gmail.com>
 * @version     1.0                
 * @since       2014-01-26          
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements WindowListener {
	PlayLogic game;
	MenuPanel menu;
	JFrame f;
	int lv = 1;
	Timer timer;
	JLabel lives;
	JLabel points;

	public GamePanel(PlayLogic game, MenuPanel menu) {

		this.game = game;
		this.menu = menu;
		this.lives = new JLabel(game.lives + "");
		this.points = new JLabel(game.points + "");
		lives.setForeground(Color.WHITE);
		points.setForeground(Color.WHITE);

		this.add(lives);
		this.add(points);
		timer = new Timer(10, new GameTic(this));
		this.addKeyListener(new GameKeyListener(game));
		this.setFocusable(true);
		this.requestFocus();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		game.paintAll(g);
		lives.setText(game.lives + " ");
		points.setText(game.points + " ");

	}

	@Override
	@Transient
	public Dimension getPreferredSize() {
		return new Dimension(game.width, game.height);
	}

	void showInFrame() {
		f = new JFrame();
		f.add(this);
		f.addWindowListener(this);
		f.pack();
		f.setVisible(true);
		timer.start();
	}

	public void newGame() {
		//game.removeAll();
		game = new PlayLogic(game.laby);

		this.addKeyListener(new GameKeyListener(game));
		this.setFocusable(true);
		this.requestFocus();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.timer.stop();
		this.game.beginn.stop();
		this.game.death.stop();
		this.game.intermission.stop();
		f.dispose();
		menu.f.setVisible(true);

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}