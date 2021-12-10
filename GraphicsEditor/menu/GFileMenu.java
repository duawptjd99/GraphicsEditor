package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import GDrawingPanel.GDrawingPanel;
import global.GConstants.EFileMenu;

public class GFileMenu extends JMenu  {
	private static final long serialVersionUID = 1L;

	private File directory, file;

	private GDrawingPanel drawingPanel;
	private ActionHandler actionHandler;

	
	public GFileMenu(String text) {
		super(text);
		this.directory = null;
		this.actionHandler = new ActionHandler();

	
		for (EFileMenu eMenuItem : EFileMenu.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.setActionCommand(eMenuItem.getMethod());
			menuItem.addActionListener(actionHandler);
			menuItem.setToolTipText(eMenuItem.gettip());
			this.add(menuItem);
		}
	}

	public void initialize(GDrawingPanel drawingPanel2) {

		this.drawingPanel = drawingPanel2;
	}

	public void nnew() {
		if (this.drawingPanel.isUpdated()) {
			if (file == null) {
				this.saveAs();
			} else {
				this.writeObject();
			}

		}
		this.drawingPanel.StartDraw();
		this.drawingPanel.restoreShapeVector(null);
		this.drawingPanel.setUpdate(true);
	}

	private void readObject() {

		try {
			ObjectInputStream objectInputStream;
			objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
			Object object = objectInputStream.readObject();
			this.drawingPanel.restoreShapeVector(object);
			objectInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void open() throws IOException {
		if (this.drawingPanel.isUpdated()) {
			if (file == null) {
				this.saveAs();
			} else {
				this.writeObject();
			}
		}
		JFileChooser chooser = new JFileChooser(this.directory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Data", "god");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this.drawingPanel);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.directory = chooser.getCurrentDirectory();
			this.file = chooser.getSelectedFile();
			this.readObject();
			this.drawingPanel.setUpdate(true);
			System.out.println("오픈완료");
		}
		this.drawingPanel.StartDraw();
	}

	private void writeObject() {
		try {
			ObjectOutputStream objectOutputStream;
			objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			objectOutputStream.writeObject(this.drawingPanel.getShapeVector());
			objectOutputStream.close();
			this.drawingPanel.setUpdate(false);
			System.out.println("저장완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		if (this.drawingPanel.isStarted()) {
			if (this.drawingPanel.isUpdated()) {
				if (file == null) {
					this.saveAs();
				} else {
					this.writeObject();
				}

			}
		}
	}

	public void saveAs() {
		if (this.drawingPanel.isStarted()) {
			JFileChooser chooser = new JFileChooser(this.directory);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Data", "god");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showSaveDialog(this.drawingPanel);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				this.directory = chooser.getCurrentDirectory();
				this.file = chooser.getSelectedFile();
				this.writeObject();
			}
		}
	}

	public void close() {
		this.save();
		System.exit(0);
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
