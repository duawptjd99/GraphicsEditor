package GDrawingPanel;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class ClipFrame extends JFrame {
	
	public ClipFrame() {
		this.setTitle("ClipBoard");
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.setVisible(true);

	}

}
