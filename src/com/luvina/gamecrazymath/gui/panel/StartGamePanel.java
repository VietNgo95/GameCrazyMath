package com.luvina.gamecrazymath.gui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.luvina.gamecrazymath.gui.GUI;
import com.luvina.gamecrazymath.gui.StartGameListener;
import com.luvina.gamecrazymath.logic.CrazyMathLogic;
import com.luvina.gamecrazymath.logic.PlayGameListener;

public class StartGamePanel extends BasePanel {
	private JLabel lbTit, lbStart;
	private StartGameListener startGameListener;

	@Override
	public void initComp() {
		setBackground(new Color(43, 60, 79));
		setLayout(null);
	}

	@Override
	public void addEvents() {

	}

	@Override
	public void addComps() {
		// tao lbGameTit, 1 btStart roi them UIListener de vao playgamePanel
		Font f = new Font("Luckiest Guy", Font.PLAIN, 28);
		Font fExpress = new Font("Luckiest Guy", Font.PLAIN, 40);
		FontMetrics metricsFExp = getFontMetrics(fExpress);

		lbTit = new JLabel("CRAZY MATH");
		lbTit.setFont(fExpress);
		lbTit.setForeground(Color.WHITE);
		lbTit.setSize(metricsFExp.stringWidth(lbTit.getText()), metricsFExp.getHeight());
		lbTit.setLocation(GUI.FRAME_WIDTH / 2 - lbTit.getWidth() / 2, 200);

		lbStart = new JLabel("START");
		lbStart.setFont(f);
		lbStart.setHorizontalAlignment(SwingConstants.CENTER);
		lbStart.setForeground(Color.WHITE);
		lbStart.setBackground(Color.RED);
		lbStart.setOpaque(true);
		lbStart.setSize(100, 50);
		lbStart.setLocation(GUI.FRAME_WIDTH / 2 - lbStart.getWidth() / 2, lbTit.getY() + lbTit.getHeight() + 100);

		MouseAdapter mouseAdapter = new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				startGameListener.startGame();
			}
		};
		lbStart.addMouseListener(mouseAdapter);

		add(lbTit);
		add(lbStart);
	}

	public void addGUIListener(StartGameListener event) {
		event = startGameListener;
	}

}
