package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Winexit extends JFrame {

	private JPanel a;
	private JPanel b;
	private JPanel c;
	private JPanel d;
	private JButton ok;
	private JButton cancle;
	private JPanel f;

	public Winexit() {

		this.setTitle("�����Ͻðڽ��ϱ�?");

		this.a = new JPanel();
		this.b = new JPanel();
		this.c = new JPanel();
		this.d = new JPanel();

		this.add(a);

		this.ok = new JButton("OK");
		this.ok.setActionCommand("1");

		this.cancle = new JButton("Cancle");
		this.cancle.setActionCommand("2");
		a.add(this.ok);
		a.add(this.cancle);

		ActionHandler ActionHandler = new ActionHandler();
		this.ok.addActionListener(ActionHandler);
		this.setVisible(true);// ȭ�鿡 ���̵��� ��80��

		this.setSize(250, 80);// â ũ��

		this.setLocationRelativeTo(null);// â ��ġ ��ġ

	}



	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO �ڵ� ������ �޼ҵ� ����
			if (arg0.getActionCommand().equals("1")) {
				System.exit(0);
			}
			if(arg0.getActionCommand().equals("2")){
				dispose();
			}
		}

	}
}