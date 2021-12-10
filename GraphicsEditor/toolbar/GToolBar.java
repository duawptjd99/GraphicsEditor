package toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import GDrawingPanel.GDrawingPanel;
import global.GConstants.EToolMenu;

public class GToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;

	private GDrawingPanel drawingPanel;
	private JRadioButton Pen;

	public GToolBar() {

		ActionHandler2 actionHandler2 = new ActionHandler2();
		ActionHandler actionHandler = new ActionHandler();
		ButtonGroup group = new ButtonGroup();

		for (EToolMenu eToolMenu : EToolMenu.values()) {
			JRadioButton button = new JRadioButton();
			button.setActionCommand(eToolMenu.name());
			button.addActionListener(actionHandler);
			button.setIcon(new ImageIcon(eToolMenu.getIconFileName()));
			button.setSelectedIcon(new ImageIcon(eToolMenu.getIconSLTFileName()));
			group.add(button);
			this.add(button);
		}
	}

	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		((JRadioButton) (this.getComponent(0))).doClick();
	}

	private class ActionHandler2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			drawingPanel.setShapeMode(false);
			drawingPanel.setPen(e.getActionCommand());

		}

	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			drawingPanel.setActionCommand(EToolMenu.valueOf(event.getActionCommand()).getSelectedTool());
			drawingPanel.setShapeMode(true);
		}
	}

}
