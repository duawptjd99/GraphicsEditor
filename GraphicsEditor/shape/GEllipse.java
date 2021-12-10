package shape;

import java.awt.geom.Ellipse2D;

public class GEllipse extends GShape {
	private Ellipse2D ellipse;
	private int px, py;
	private int inpx, inpy;

	public GEllipse() {
		super(new Ellipse2D.Double(0,0,0,0));
		this.ellipse = (Ellipse2D) this.getShape();
	}

	public GShape newInstance() {
		return new GEllipse();
	}

	public void setLocation(int x, int y) {
		this.ellipse.setFrame(x, y, this.ellipse.getWidth(), this.ellipse.getHeight());
	}

	public void saveCurrentPosition(int x, int y) {
		this.px = x;
		this.py = y;
	}

	public void move(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;
		double x = this.ellipse.getX() + dx;
		double y = this.ellipse.getY() + dy;
		this.ellipse.setFrame((float) x, (float)y, (float) this.ellipse.getWidth(), (float) this.ellipse.getHeight());
		this.px = newX;
		this.py = newY;
	}

	public void resize(int newX, int newY) {

		if (newX > this.ellipse.getX() && newY > this.ellipse.getY()) {
			double w = newX - this.ellipse.getX();
			double h = newY - this.ellipse.getY();
			this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), newX - this.ellipse.getX(),
					newY - this.ellipse.getY());
		} else if (newX > this.ellipse.getX() && newY < this.ellipse.getY()) {
			this.ellipse.setFrame(inpx, newY, newX - inpx, inpy - newY);
		} else if (newX < this.ellipse.getX() && newY > this.ellipse.getY()) {
			this.ellipse.setFrame(newX, inpy, inpx - newX, newY - inpy);
		} else if (newX < this.ellipse.getX() && newY < this.ellipse.getY()) {
			this.ellipse.setFrame(newX, newY, inpx - newX, inpy - newY);
		}

	}

	public void rotate(int newX, int newY) {

	}

	@Override
	public void initPoint(int x, int y) {
		inpx = x;
		inpy = y;

	}

}
