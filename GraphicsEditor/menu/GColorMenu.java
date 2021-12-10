package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import GDrawingPanel.GDrawingPanel;
import global.GConstants.EColorMenu;

public class GColorMenu extends JMenu {
	
	private ActionHandler actionHandler;
	private GDrawingPanel drawingPanel;
	private Color FColor;
	private Color LColor;
	
	public GColorMenu(String text) {
		super(text);
		// TODO 자동 생성된 생성자 스텁
		this.actionHandler = new ActionHandler();

		for (EColorMenu eMenuItem : EColorMenu.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.setActionCommand(eMenuItem.getMethod());
			menuItem.addActionListener(actionHandler);
			menuItem.setToolTipText(eMenuItem.gettip());
			this.add(menuItem);
		}
		
		this.FColor = Color.white;
		this.LColor = Color.black;

	}	

	
	public void initialize(GDrawingPanel drawingPanel2) {
		this.drawingPanel = drawingPanel2;
		
	}	
	
	public void fill() {
		Color color = JColorChooser.showDialog(null, "색선정", Color.blue); 
		this.drawingPanel.setFColor(color);
	}
	
	public void line() {
		Color color2 = JColorChooser.showDialog(null, "색선정", Color.blue); 
		this.drawingPanel.setLColor(color2);
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
