package menu;

import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JMenuBar;

import GDrawingPanel.GDrawingPanel;
import global.GConstants.EMenu;

public class GMenuBar extends JMenuBar implements Runnable {
	private static final long serialVersionUID = 1L;

	private GFileMenu fileMenu;
	private GColorMenu colorMenu;

	private GEditMenu editMenu;
	private Thread t;
	JLabel l;
	JLabel l2;

	private GDrawingPanel drawingPanel;

	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	public GMenuBar() {

		this.fileMenu = new GFileMenu(EMenu.fileMenu.getText());
		this.fileMenu.setToolTipText("파일설정을 합니다.");
		this.add(fileMenu);

		this.colorMenu = new GColorMenu(EMenu.ColorMenu.getText());
		this.colorMenu.setToolTipText("색깔을 선정합니다.");
		this.add(colorMenu);

		this.editMenu = new GEditMenu(EMenu.EditMenu.getText());
		this.editMenu.setToolTipText("수정매뉴를 선정합니다.");
		this.add(editMenu);

		l2 = new JLabel();
		this.add(l2);

		l = new JLabel();
		this.add(l);

		this.t = new Thread(this);
		t.start();

	}

	public void run() {
		// TODO 자동 생성된 메소드 스텁
		while (true) {
			show();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
	}

	public void show() {

		Calendar calendar = Calendar.getInstance();
		int h = calendar.get(Calendar.HOUR);
		int m = calendar.get(Calendar.MINUTE);
		int s = calendar.get(Calendar.SECOND);
		String time = "시간 : " + h + ":" + m + ":" + s;
		l.setText(time);
		String xy = "     x좌표 : " + drawingPanel.Dx + "  y좌표 : " + drawingPanel.Dy + "  ";
		l2.setText(xy);

	}

	public void initialize(GDrawingPanel drawingPanel2) {
		this.fileMenu.initialize(drawingPanel2);
		this.colorMenu.initialize(drawingPanel2);
		this.editMenu.initialize(drawingPanel2);
	}

}