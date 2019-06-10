package com.luvina.gamecrazymath.gui.panel;

import javax.swing.JPanel;

import com.luvina.gamecrazymath.gui.ICommon;

public abstract class BasePanel extends JPanel implements ICommon {

	public BasePanel() {
		initComp();
		addEvents();
		addComps();
	}
}
