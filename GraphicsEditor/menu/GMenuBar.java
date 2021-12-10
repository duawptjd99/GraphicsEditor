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
		this.fileMenu.setToolTipText("���ϼ����� �մϴ�.");
		this.add(fileMenu);

		this.colorMenu = new GColorMenu(EMenu.ColorMenu.getText());
		this.colorMenu.setToolTipText("������ �����մϴ�.");
		this.add(colorMenu);

		this.editMenu = new GEditMenu(EMenu.EditMenu.getText());
		this.editMenu.setToolTipText("�����Ŵ��� �����մϴ�.");
		this.add(editMenu);

		l2 = new JLabel();
		this.add(l2);

		l = new JLabel();
		this.add(l);

		this.t = new Thread(this);
		t.start();

	}

	public void run() {
		// TODO �ڵ� ������ �޼ҵ� ����
		while (true) {
			show();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO �ڵ� ������ catch ���
				e.printStackTrace();
			}
		}
	}

	public void show() {

		Calendar calendar = Calendar.getInstance();
		int h = calendar.get(Calendar.HOUR);
		int m = calendar.get(Calendar.MINUTE);
		int s = calendar.get(Calendar.SECOND);
		String time = "�ð� : " + h + ":" + m + ":" + s;
		l.setText(time);
		String xy = "     x��ǥ : " + drawingPanel.Dx + "  y��ǥ : " + drawingPanel.Dy + "  ";
		l2.setText(xy);

	}

	public void initialize(GDrawingPanel drawingPanel2) {
		this.fileMenu.initialize(drawingPanel2);
		this.colorMenu.initialize(drawingPanel2);
		this.editMenu.initialize(drawingPanel2);
	}

}