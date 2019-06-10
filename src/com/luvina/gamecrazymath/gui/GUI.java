package com.luvina.gamecrazymath.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.luvina.gamecrazymath.gui.panel.BasePanel;
import com.luvina.gamecrazymath.gui.panel.PlayGamePanel;
import com.luvina.gamecrazymath.gui.panel.StartGamePanel;

public class GUI extends JFrame implements ICommon, StartGameListener {
	private static final String TITLE = "Crazy Math";
	public static final int FRAME_WIDTH = 350;
	public static final int FRAME_HEIGHT = 600;
	private BasePanel playGamePanel, startGamePanel;

	public GUI() {
		initComp();
		addEvents();
		addComps();
	}

	@Override
	public void initComp() {
		setTitle(TITLE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLayout(new CardLayout());

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void addEvents() {
		WindowListener windowListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int value = JOptionPane.showConfirmDialog(GUI.this, "Do you want to exit?", "NOTICE",
						JOptionPane.YES_NO_OPTION);
				if (value == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		};
		addWindowListener(windowListener);
	}

	@Override
	public void addComps() {
		// startGamePanel = new StartGamePanel();
		// add(startGamePanel);
		// ((StartGamePanel) startGamePanel).addGUIListener(this);
		playGamePanel = new PlayGamePanel();
		add(playGamePanel);
		((PlayGamePanel) playGamePanel).addGUIListener(this);
	}

	@Override
	public void disposeGame() {
		dispose();
	}

	@Override
	public void startGame() {
		startGamePanel.setVisible(false);
		playGamePanel.setVisible(true);
		((PlayGamePanel) playGamePanel).startGame();
	}

}
