package shape;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

public class GSelect extends GShape {
	private static final long serialVersionUID = 1L;
	private Rectangle2D rectangle;
	private int px, py;
	private int minX = 1000, minY = 1000, maxX, maxY;
	private boolean flag = true;

	public GSelect() {
		super(new Rectangle2D.Double(0, 0, 0, 0));
		this.rectangle = (Rectangle2D.Double) this.getShape();
	}

	public void draw(Graphics2D g2D) {
		Stroke savedStroke = g2D.getStroke();
		Stroke stroke = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f,
				new float[] { 2.0f, 5.0f }, 0.0f);
		g2D.setStroke(stroke);
		g2D.draw(this.rectangle);
		g2D.setStroke(savedStroke);
	}

	public GShape newInstance() {
		return new GSelect();
	}

	public void setLocation(int x, int y) {
		this.rectangle.setFrame(x, y, 
		this.rectangle.getWidth(), this.rectangle.getHeight());
		
		if(this.minX > x ) {
			this.minX = x;
		} else if (this.maxX < x) {
			this.maxX = x;
		}
		if(this.minY > y) {
			this.minY = y;
		} else if (this.maxY < y) {
			this.maxY = y;
		}

	}
 
	public void contains(Vector<GShape> shapeVector) {
		for (GShape vector : shapeVector) {
			if ((this.minX < vector.getCenterX() && vector.getCenterX() < this.maxX && this.minY < vector.getCenterY()
					&& vector.getCenterY() < this.maxY)) {
				vector.setSelected(true);
			}
		}
	}

	public void saveCurrentPosition(int x, int y) {
		this.px = x;
		this.py = y;
	}

	public void move(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;
		double x = this.rectangle.getX() + dx;
		double y = this.rectangle.getY() + dy;
		this.rectangle.setFrame(x, y, this.rectangle.getWidth(), this.rectangle.getHeight());
		this.px = newX;
		this.py = newY;
	}

	public void resize(int newX, int newY) {
		double w = newX - this.rectangle.getX();
		double h = newY - this.rectangle.getY();
		this.rectangle.setFrame(this.rectangle.getX(), this.rectangle.getY(), w, h);
	}

	public void rotate(int newX, int newY) {

	}

	@Override
	public void initPoint(int x, int y) {
		// TODO 자동 생성된 메소드 스텁
		
	}

}