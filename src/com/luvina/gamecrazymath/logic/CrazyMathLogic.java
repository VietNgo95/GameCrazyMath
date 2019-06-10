package com.luvina.gamecrazymath.logic;

import java.util.ArrayList;
import java.util.Random;

import com.luvina.gamecrazymath.gui.panel.PlayGamePanel;

public class CrazyMathLogic {
	private int result;

	public String randomExpress() {
		String str = "";
		Random rd = new Random();
		int a = rd.nextInt(10);
		int b = rd.nextInt(10);

		String math[] = new String[] { "+", "-", "x", "/", };
		int m = rd.nextInt(4);
		switch (m) {
		case 0:
			result = a + b;
			break;
		case 1:
			result = a - b;
			break;
		case 2:
			result = a * b;
			break;
		default:
			if (b == 0 || a % b != 0) {
				b = aRandomDivisor(a);
			}
			result = a / b;
			break;
		}
		str += a + " " + math[m] + " " + b;
		return str;
	}

	private int aRandomDivisor(int a) {
		ArrayList<Integer> div = new ArrayList<>();
		Random rd = new Random();
		if (a == 0) {
			return rd.nextInt(9) + 1;
		}
		for (int i = 1; i <= a; i++) {
			if (a % i == 0) {
				div.add((Integer) i);
			}
		}
		int rdIndex = rd.nextInt(div.size());
		return div.get(rdIndex);
	}

	public int[] randomLbResult() {
		Random rd = new Random();
		int rdNum = rd.nextInt(3);

		int ans[] = new int[3];
		ans[rdNum] = result;
		for (int i = 0; i < ans.length; i++) {
			if (i != rdNum) {
				int rdAns = rd.nextInt(33);
				ans[i] = rdAns;
			}
			if (i == ans.length - 1 && isSameRes(ans)) {
				i = 0;
				continue;
			}
		}
		return ans;
	}

	private boolean isSameRes(int[] ans) {
		ArrayList<Integer> numList = new ArrayList<>();
		for (int i = 0; i < ans.length; i++) {
			if (!numList.contains(ans[i])) {
				numList.add(ans[i]);
			} else {
				return true;
			}
		}
		return false;
	}

	public boolean checkAnswer(PlayGameListener event, String ans1, String ans2, String ans3, String name) {
		switch (name) {
		case PlayGamePanel.KEY_ANS1:
			if (result != Integer.parseInt(ans1)) {
				return false;
			}
			event.updatePlayGamePanel();
			break;
		case PlayGamePanel.KEY_ANS2:
			if (result != Integer.parseInt(ans2)) {
				return false;
			}
			event.updatePlayGamePanel();
			break;
		case PlayGamePanel.KEY_ANS3:
			if (result != Integer.parseInt(ans3)) {
				return false;
			}
			event.updatePlayGamePanel();
			break;
		default:
			break;
		}
		return true;
	}
}
