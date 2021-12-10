package shape;

import java.awt.Polygon;

public class GPolygon extends GShape {
	private static final long serialVersionUID = 1L;
	private Polygon polygon;
	private int px, py;

	public GPolygon() {
		super(new Polygon());
		this.polygon = (Polygon) this.getShape();
	}

	public GShape newInstance() {
		return new GPolygon();
	}

	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}

	public void setLocation(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}

	public void saveCurrentPosition(int x, int y) {
		this.px = x;
		this.py = y;
	}
	

	public void resize(int x, int y) {
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;
	}

	@Override
	public void rotate(int newX, int newY) {
	}

	@Override
	public void move(int newX, int newY) {
	}

	@Override
	public void initPoint(int x, int y) {
		// TODO 자동 생성된 메소드 스텁
		
	}

}
