package main;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import GDrawingPanel.GDrawingPanel;
import global.GConstants;
import menu.GMenuBar;
import toolbar.GToolBar;

public class GMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	private winH winH;
	
	public GMainFrame() {

		this.setLocation(GConstants.MAINFRAME_X, GConstants.MAINFRAME_Y);
		this.setSize(GConstants.MAINFRAME_W, GConstants.MAINFRAME_H);
		this.setLayout(new BorderLayout());
		this.winH = new winH();
		this.addWindowListener(winH);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);

		this.toolBar = new GToolBar();
		this.add(this.toolBar, BorderLayout.NORTH);

		this.drawingPanel = new GDrawingPanel();
		this.add(this.drawingPanel, BorderLayout.CENTER);

		Toolkit tk = Toolkit.getDefaultToolkit();
		java.awt.Image img = tk.getImage("image/icon1.png");
		this.setIconImage(img);
		this.setTitle("Drawing Board");

	}

	public void initialize() {

		this.drawingPanel.initialize();
		this.menuBar.initialize(this.drawingPanel);
		this.toolBar.initialize(this.drawingPanel);
	}

	private class winH implements WindowListener {

		@Override
		public void windowActivated(java.awt.event.WindowEvent e) {
			// TODO 자동 생성된 메소드 스텁
			
		}

		@Override
		public void windowClosed(java.awt.event.WindowEvent e) {
			// TODO 자동 생성된 메소드 스텁
			
		}

		@Override
		public void windowClosing(java.awt.event.WindowEvent e) {
			// TODO 자동 생성된 메소드 스텁
			Winexit a = new Winexit();
		}

		@Override
		public void windowDeactivated(java.awt.event.WindowEvent e) {
			// TODO 자동 생성된 메소드 스텁
			
		}

		@Override
		public void windowDeiconified(java.awt.event.WindowEvent e) {
			// TODO 자동 생성된 메소드 스텁
			
		}

		@Override
		public void windowIconified(java.awt.event.WindowEvent e) {
			// TODO 자동 생성된 메소드 스텁
			
		}

		@Override
		public void windowOpened(java.awt.event.WindowEvent e) {
			// TODO 자동 생성된 메소드 스텁
			
		}


	}
}
