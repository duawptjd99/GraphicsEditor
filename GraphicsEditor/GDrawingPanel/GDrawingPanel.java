package GDrawingPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import shape.GPolygon;
import shape.GSelect;
import shape.GShape;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
import transformer.GRotater;
import transformer.GTransfomer;

public class GDrawingPanel extends JPanel {

	public enum EDrawingState {
		ready, eTPTransforming, eNPTransforming
	};

	private static final long serialVersionUID = 1L;
	private EDrawingState eDrawingState;

	private Clipboard clipboard;
	private Vector<GShape> shapeVector;
	private GShape selectedShape;
	private GShape selectedTool;
	private GTransfomer transformer;
	private Color FColor;
	private Color LColor;
	private Object[] saveVector;
	private boolean check;
	private boolean blockShape;
	private Graphics g;
	public static int Dx = 0;
	public static int Dy = 0;
	
	
	public Object getShapeVector() {
		return this.shapeVector;
	}

	private String PenMode;
	private boolean updated;
	private boolean startDraw;
	private int i = 0;
	private int j = 0;

	public boolean isUpdated() {
		return this.updated;
	}

	public boolean isStarted() {
		return this.startDraw;
	}

	public void StartDraw() {
		this.startDraw = true;
		record();
		this.setBackground(Color.WHITE);
	}

	public void EndDraw() {
		this.startDraw = false;
		this.setBackground(Color.GRAY);
	}

	public void setUpdate(boolean updated) {
		this.updated = updated;
	}

	@SuppressWarnings("unchecked")

	public void restoreShapeVector(Object shapeVector) {
		if (shapeVector == null) {
			this.shapeVector.clear();
		} else {
			this.shapeVector = (Vector<GShape>) shapeVector;
		}
		this.repaint();
	}

	public void setFColor(Color F) {
		this.FColor = F;
	}

	public void setLColor(Color L) {
		this.LColor = L;
	}

	public void setShapeMode(boolean Mode) {
		this.blockShape = Mode;
	}

	public void setPen(String Pen) {
		// TODO 자동 생성된 메소드 스텁
		this.PenMode = Pen;
	}

	public void drawPen(int x, int y, Graphics g) {
		g.fillOval(x, y, 5, 5);
	}

	public GDrawingPanel() {

		super();
		this.setBackground(Color.GRAY);
		this.eDrawingState = EDrawingState.ready;
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.shapeVector = new Vector<GShape>();
		this.clipboard = new Clipboard();
		new Vector<GShape>();
		this.EndDraw();

		this.blockShape = true;
		this.PenMode = null;
		this.LColor = Color.black;
		this.FColor = Color.white;
		this.saveVector = new Object[500];
		this.selectedShape = null;
	}

	private void record() {
		if (check == true) {
			i = j + 1;
			// System.out.println(i);
			check = false;
		}
		this.saveVector[i] = this.shapeVector.clone();
		i++;
		j = i - 1;
		// System.out.println("I=" + i);
		// System.out.println("J=" + j);
		// System.out.println("-----");
	}
	public void showclipboard() {

	
	}

	public void showUndo() {
		if (j == 0) {

		} else {
			j--;
			this.shapeVector = (Vector<GShape>) saveVector[j];
			this.repaint();
			if (!(j == i - 2)) {
				check = true;
			}
		}
		// System.out.println("결과");
		// System.out.println("I=" + i);
		// System.out.println("J=" + j);
		// System.out.println("-----");
	}

	public void showRedo() {
		if (j >= i - 1) {

		} else {
			j++;
			this.shapeVector = (Vector<GShape>) saveVector[j];
			this.repaint();

		}
	}

	public void drawAnchors() {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.selectedShape.drawAchors(g2D);
	}

	public void initialize() {

		this.selectedShape = null;
		this.selectedTool = null;
		this.transformer = null;
	}

	public void paint(Graphics g) {
		super.paint(g);

		GShape.setLineColor(this.LColor);
		GShape.setFillColor(this.FColor);
		for (GShape shape : this.shapeVector) {
			shape.draw((Graphics2D) g);
		}
		if (this.selectedShape != null) {
			this.selectedShape.drawAchors((Graphics2D) g);
		}
		for (GShape shape : this.shapeVector) {
			if (shape.getSeleted()) {
				shape.drawAchors((Graphics2D) g);
			}
		}
		if (eDrawingState != EDrawingState.eNPTransforming) {
			this.repaint();
		}
	}

	private GShape onShape(int x, int y) {
		for (GShape shape : this.shapeVector) {
			if (shape.contains(x, y)) {
				return shape;
			}
		}
		return null;
	}

	private void setSelected(GShape selectedShape) {
		if (!(selectedTool instanceof GSelect)) {
			Graphics2D g2D = (Graphics2D) this.getGraphics();
			g2D.setXORMode(this.getBackground());
			for (GShape shape : this.shapeVector) {
				if (shape != null) {
					shape.setSelected(false);
					shape.drawAchors(g2D);
				}
			}
			this.selectedShape = selectedShape;
			this.selectedShape.setSelected(true);

		} else {
			Graphics2D g2D = (Graphics2D) this.getGraphics();
			g2D.setXORMode(this.getBackground());
			if (this.selectedShape != null) {
				this.selectedShape.setSelected(false);
				this.selectedShape.drawAchors(g2D);
			}
			this.selectedShape = selectedShape;
			this.selectedShape.setSelected(true);
		}
	}

	private void selectAction(int x, int y) {

		GShape shape = this.onShape(x, y);

		if (shape == null) {
			shape = this.selectedTool.newInstance();
			this.transformer = new GDrawer(shape);
		} else {
			if (shape.getSelectedAnchor() == null) {
				this.transformer = new GMover(shape);

			} else if (shape.getSelectedAnchor().ordinal() == 8) {
				this.transformer = new GRotater(shape);
			} else if (shape.getSelectedAnchor().ordinal() < 8 && shape.getSelectedAnchor().ordinal() >= 0) {
				this.transformer = new GResizer(shape);
			}

		}
		this.setSelected(shape);
	}

	private void initTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.transformer.initTransforming(g2D, x, y);
	}

	private void keepTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.transformer.keepTransforming(g2D, x, y, this.shapeVector);
	}

	private void continueTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.transformer.continueTransforming(g2D, x, y);
	}

	private void finishTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.transformer.finishTransforming(g2D, x, y);
		if (this.transformer instanceof GDrawer) {
			if (this.selectedShape instanceof GSelect) {
				this.selectedShape.setLocation(x, y);
				this.transformer.finishSeletTransforming(shapeVector);
				this.selectedShape = null;
			} else {
				this.shapeVector.add(this.selectedShape);
			}
		}
		if (this.selectedTool instanceof GPolygon) {
			this.repaint();
		}

	}

	public void cut() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for (int i = this.shapeVector.size() - 1; i >= 0; i--) {
			if (this.shapeVector.get(i).isSelected()) {
				selectedShapes.add(this.shapeVector.get(i));
				this.shapeVector.remove(i);
			}
		}
		this.clipboard.setContents(selectedShapes);
		this.record();
		this.repaint();
	}

	public void copy() {
		if (this.selectedShape != null) {
			Vector<GShape> selectedShapes = new Vector<GShape>();
			for (int i = this.shapeVector.size() - 1; i >= 0; i--) {
				if (this.shapeVector.get(i).isSelected()) {
					GShape shape = this.shapeVector.get(i).clone();
					shape.newInstance();
					selectedShapes.add(shape);
				}
			}
			this.clipboard.setContents(selectedShapes);
			
		}
	}

	public void paste() {
		Vector<GShape> shapes = this.clipboard.getContents();
		this.shapeVector.addAll(shapes);
		this.repaint();
		this.record();
	}

	class MouseHandler implements MouseInputListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (isStarted()) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (e.getClickCount() == 1) {
						this.mouseL1Clicked(e);
					} else if (e.getClickCount() == 2) {
						this.mouseL2Clicked(e);
					}
				}
			}
		}

		private void mouseL1Clicked(MouseEvent e) {
			if (blockShape == true) {
				if (eDrawingState == EDrawingState.ready) {
					if ((selectedTool instanceof GPolygon) && (transformer instanceof GDrawer)) {
						initTransforming(e.getX(), e.getY());
						eDrawingState = EDrawingState.eNPTransforming;
					}
				} else if (eDrawingState == EDrawingState.eNPTransforming) {
					continueTransforming(e.getX(), e.getY());
				}
				// System.out.println(eDrawingState);
			} else if (blockShape == false && PenMode == "Pen") {
			}
		}

		private void mouseL2Clicked(MouseEvent e) {
			if (blockShape == true) {
				Graphics2D g2D = (Graphics2D) getGraphics();
				g2D.setXORMode(getBackground());
				if (eDrawingState == EDrawingState.eNPTransforming) {
					finishTransforming(e.getX(), e.getY());
					selectedShape.drawAchors(g2D);
					eDrawingState = EDrawingState.ready;
				}
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (blockShape == true) {
				if (isStarted()) {// System.out.println(eDrawingState);
					if (eDrawingState == EDrawingState.ready) {
						selectAction(e.getX(), e.getY());
						if (!((selectedTool instanceof GPolygon) && (transformer instanceof GDrawer))) {
							initTransforming(e.getX(), e.getY());
							eDrawingState = EDrawingState.eTPTransforming;
						}
					}
				}
			} // System.out.println(eDrawingState);
			else if (blockShape == false && PenMode == "Pen") {
				drawPen(e.getX(), e.getY(), g);
				// drawPen(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (blockShape == true) {
				if (isStarted()) {
					if (eDrawingState == EDrawingState.eTPTransforming) {
						keepTransforming(e.getX(), e.getY());
					}
				}
			} else if (blockShape == false && PenMode == "Pen") {
				drawPen(e.getX(), e.getY(), g);
				// drawPen(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (blockShape == true) {
				if (isStarted()) {// System.out.println(eDrawingState);
					if (eDrawingState == EDrawingState.eTPTransforming) {
						finishTransforming(e.getX(), e.getY());
						eDrawingState = EDrawingState.ready;
					}
					// System.out.println(eDrawingState);
				}
			} else if (blockShape == false && PenMode == "Pen") {
				// drawPen(e.getX(), e.getY());
				// drawPen(e.getX(), e.getY());
			}
			record();
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			Dx = e.getX();
			Dy = e.getY();
			if (blockShape == true) {
				if (isStarted()) {
					if (eDrawingState == EDrawingState.eNPTransforming) {
						keepTransforming(e.getX(), e.getY());
					}
				}
			}
		}
	}

	public void setActionCommand(GShape selectedTool) {
		this.selectedTool = selectedTool;
	}


}