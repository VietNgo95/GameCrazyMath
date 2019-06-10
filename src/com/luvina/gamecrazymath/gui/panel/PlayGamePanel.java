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

public class PlayGamePanel extends BasePanel implements PlayGameListener {
	public static final String KEY_ANS1 = "ANSWER 1";
	public static final String KEY_ANS2 = "ANSWER 2";
	public static final String KEY_ANS3 = "ANSWER 3";
	private static final String TIME_COUNT = "TIME COUNT";

	private JLabel lbScore, lbTime, lbExpress, lbAns1, lbAns2, lbAns3;
	private CrazyMathLogic craMath;
	private Thread thread;
	private StartGameListener playListener;
	private boolean flag;

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
		craMath = new CrazyMathLogic();
		Font f = new Font("Luckiest Guy", Font.PLAIN, 28);
		FontMetrics metricsF = getFontMetrics(f);
		Font fExpress = new Font("Luckiest Guy", Font.PLAIN, 72);
		FontMetrics metricsFExp = getFontMetrics(fExpress);

		lbScore = new JLabel("score: 0");
		lbScore.setFont(f);
		lbScore.setForeground(Color.WHITE);
		int hF = metricsF.getHeight();
		lbScore.setBounds(5, 5, metricsF.stringWidth(lbScore.getText()), hF);

		lbTime = new JLabel("10");
		lbTime.setFont(f);
		lbTime.setForeground(Color.WHITE);
		lbTime.setSize(metricsF.stringWidth(lbTime.getText()), hF);
		lbTime.setLocation(GUI.FRAME_WIDTH / 2 - lbTime.getWidth() / 2, lbScore.getY() + lbScore.getHeight() + 20);
		startGame();

		lbExpress = new JLabel(craMath.randomExpress());
		lbExpress.setFont(fExpress);
		lbExpress.setForeground(Color.WHITE);
		lbExpress.setSize(metricsFExp.stringWidth(lbExpress.getText()), metricsFExp.getHeight() + 5);
		lbExpress.setLocation(GUI.FRAME_WIDTH / 2 - lbExpress.getWidth() / 2, lbTime.getY() + lbTime.getHeight() + 50);

		int res[] = craMath.randomLbResult();

		lbAns1 = new JLabel("" + res[0]);
		lbAns1.setFont(f);
		lbAns1.setOpaque(true);
		lbAns1.setForeground(Color.WHITE);
		lbAns1.setBackground(Color.RED);
		lbAns1.setHorizontalAlignment(SwingConstants.CENTER);
		lbAns1.setVerticalAlignment(SwingConstants.CENTER);
		lbAns1.setSize(150, 60);
		lbAns1.setLocation(GUI.FRAME_WIDTH / 2 - lbAns1.getWidth() / 2, lbExpress.getY() + lbExpress.getHeight() + 40);

		lbAns2 = new JLabel("" + res[1]);
		lbAns2.setFont(f);
		lbAns2.setOpaque(true);
		lbAns2.setForeground(Color.WHITE);
		lbAns2.setBackground(Color.GREEN);
		lbAns2.setHorizontalAlignment(SwingConstants.CENTER);
		lbAns2.setVerticalAlignment(SwingConstants.CENTER);
		lbAns2.setSize(lbAns1.getWidth(), lbAns1.getHeight());
		lbAns2.setLocation(GUI.FRAME_WIDTH / 2 - lbAns2.getWidth() / 2, lbAns1.getY() + lbAns1.getHeight() + 50);

		lbAns3 = new JLabel("" + res[2]);
		lbAns3.setFont(f);
		lbAns3.setOpaque(true);
		lbAns3.setForeground(Color.WHITE);
		lbAns3.setBackground(Color.MAGENTA);
		lbAns3.setHorizontalAlignment(SwingConstants.CENTER);
		lbAns3.setVerticalAlignment(SwingConstants.CENTER);
		lbAns3.setSize(lbAns1.getWidth(), lbAns1.getHeight());
		lbAns3.setLocation(GUI.FRAME_WIDTH / 2 - lbAns3.getWidth() / 2, lbAns2.getY() + lbAns2.getHeight() + 50);

		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = ((Component) e.getSource()).getName();
				switch (name) {
				case KEY_ANS1:
				case KEY_ANS2:
				case KEY_ANS3:
					if (!craMath.checkAnswer(PlayGamePanel.this, lbAns1.getText(), lbAns2.getText(), lbAns3.getText(),
							name)) {
						flag = true;
					}
					thread.interrupt();
					break;
				default:
					break;
				}
			}
		};
		lbAns1.setName(KEY_ANS1);
		lbAns1.addMouseListener(mouseAdapter);
		lbAns2.setName(KEY_ANS2);
		lbAns2.addMouseListener(mouseAdapter);
		lbAns3.setName(KEY_ANS3);
		lbAns3.addMouseListener(mouseAdapter);

		add(lbScore);
		add(lbTime);
		add(lbExpress);
		add(lbAns1);
		add(lbAns2);
		add(lbAns3);

	}

	protected void countDown() {
		for (int i = 10; i >= 0; i--) {
			if (flag) {
				break;
			}
			lbTime.setText(i + "");
			if (i == 0) {
				flag = true;
				thread.interrupt();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				if (!flag) {
					countDown();
				} else {
					gameOverUI();
				}
			}
		}
	}

	@Override
	public void updatePlayGamePanel() {
		craMath = new CrazyMathLogic();
		Font f = new Font("Luckiest Guy", Font.PLAIN, 28);
		FontMetrics metricsF = getFontMetrics(f);
		Font fExpress = new Font("Luckiest Guy", Font.PLAIN, 72);
		FontMetrics metricsFExp = getFontMetrics(fExpress);

		String str = lbScore.getText();
		int score = Integer.parseInt(str.substring(str.lastIndexOf(" ") + 1));
		str = str.replace("" + score, ++score + "");
		lbScore.setText(str);
		lbScore.setSize(metricsF.stringWidth(lbScore.getText()), metricsF.getHeight());

		lbExpress.setText(craMath.randomExpress());
		lbExpress.setSize(metricsFExp.stringWidth(lbExpress.getText()), metricsFExp.getHeight() + 5);
		lbExpress.setLocation(GUI.FRAME_WIDTH / 2 - lbExpress.getWidth() / 2, lbExpress.getY());

		int res[] = craMath.randomLbResult();
		lbAns1.setText("" + res[0]);
		lbAns2.setText("" + res[1]);
		lbAns3.setText("" + res[2]);
	}

	@Override
	public void gameOverUI() {
		JOptionPane.showMessageDialog(this, "Game over!\nYour " + lbScore.getText());
		playListener.disposeGame();
	}

	public void addGUIListener(StartGameListener event) {
		playListener = event;
	}

	public void startGame() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				if (!flag) {
					countDown();
				}
			}
		};

		thread = new Thread(runnable);
		thread.setName(TIME_COUNT);
		thread.setDaemon(true);
		thread.start();

	}
}
