package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import GDrawingPanel.ClipFrame;
import GDrawingPanel.GDrawingPanel;
import global.GConstants.EEditMenu;

public class GEditMenu extends JMenu {

	private GDrawingPanel drawingPanel;
	private ActionHandler actionHandler;

	public void initialize(GDrawingPanel drawingPanel2) {

		this.drawingPanel = drawingPanel2;
	}

	public GEditMenu(String text) {
		super(text);


		this.actionHandler = new ActionHandler();

		for (EEditMenu eMenuItem : EEditMenu.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.setActionCommand(eMenuItem.getMethod());
			menuItem.addActionListener(actionHandler);
			menuItem.setToolTipText(eMenuItem.gettip());
			this.add(menuItem);
		}

	}

	public void redo() {
		this.drawingPanel.showRedo();
		
	}

	public void undo() {
		this.drawingPanel.showUndo();
	}
	public void cut() {
		this.drawingPanel.cut();
	}

	public void copy() {
		this.drawingPanel.copy();
	}

	public void paste() {
		this.drawingPanel.paste();
	}
	
	public void clipboard() {
		this.drawingPanel.showclipboard();
		ClipFrame c = new ClipFrame();
	}
	

	private void invokeMethod(String name) {
		try {
			this.getClass().getMethod(name).invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			invokeMethod(e.getActionCommand());
		}

	}

}
