package de.hsrm.cs.oose13;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import de.hsrm.cs.oose13.basics.ImagePanel;
/**
 * The ImagePanel Class represents a Panel with a single Image.
 * @author      Arnold Riemer <arnold.riemer@gmail.com>
 * @version     1.0                
 * @since       2014-01-26          
 */
@SuppressWarnings("serial")
public class MenuPanel extends JPanel implements ActionListener {
	JButton start;
	JFrame f;
	JRadioButton easy, normal, hard, insane;
	ButtonGroup bgdifficulty;
	JPanel difficulty;
	ImagePanel header;

	int lvl;
	MenuPanel mp;
	GamePanel gp;

	public MenuPanel() {
		lvl = 1;
		start = new JButton("Start Game");
		easy = new JRadioButton("Easy");
		normal = new JRadioButton("Normal");
		hard = new JRadioButton("Hard");
		insane = new JRadioButton("Insane");
		start.addActionListener(this);
		easy.addActionListener(this);
		normal.addActionListener(this);
		hard.addActionListener(this);
		insane.addActionListener(this);

		header = new ImagePanel("img/header.png", 500, 374);
		bgdifficulty = new ButtonGroup();
		bgdifficulty.add(easy);
		bgdifficulty.add(normal);
		bgdifficulty.add(hard);
		bgdifficulty.add(insane);
		// normal.setSelected(true);

		difficulty = new JPanel();
		difficulty.setLayout(new GridLayout());
		difficulty.add(easy);
		difficulty.add(normal);
		difficulty.add(hard);
		difficulty.add(insane);

		easy.setSelected(true);

		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(start, BorderLayout.SOUTH);
		this.add(difficulty);

	}

	public void startGame(int lvlAuswahl) {
		PlayLogic game = new PlayLogic("lvl/" + lvlAuswahl + "lv.txt");
		gp = new GamePanel(game, this);
		gp.showInFrame();
	}

	public void nextGame() {
		lvl++;
		gp.timer.stop();
		gp.game.beginn.stop();
		gp.game.death.stop();
		gp.game.intermission.stop();
		gp.f.dispose();

		if (lvl > 4)
			lvl = 1;
		this.startGame(lvl);
	}

	public void start(MenuPanel p) {
		f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		f.pack();
		f.setVisible(true);
	}

	public static void main(String args[]) throws IOException {
		String current = new java.io.File( "." ).getCanonicalPath();
		System.out.println("Current dir:"+current);

		MenuPanel p = new MenuPanel();
		p.start(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			startGame(lvl);
			f.setVisible(false);
		}
		if (e.getSource() == easy) {
			lvl = 1;
		}

		if (e.getSource() == normal) {
			lvl = 2;
		}

		if (e.getSource() == hard) {
			lvl = 3;
		}

		if (e.getSource() == insane) {
			lvl = 4;
		}
	}

}
