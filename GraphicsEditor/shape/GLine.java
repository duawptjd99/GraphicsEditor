package shape;

import java.awt.geom.Line2D;

public class GLine extends GShape {

	private Line2D line;
	private int px, py;
	private int ix, iy;

	public GLine() {
		super(new Line2D.Double());
		this.line = (Line2D) this.getShape();
		// TODO 자동 생성된 생성자 스텁
	}

	@Override
	public GShape newInstance() {
		// TODO 자동 생성된 메소드 스텁
		return new GLine();
	}

	@Override
	public void initPoint(int x, int y) {
		// TODO 자동 생성된 메소드 스텁
		this.ix = x;
		this.iy = y;
		this.line.setLine(x, y, x, y);
	}

	@Override
	public void saveCurrentPosition(int x, int y) {
		// TODO 자동 생성된 메소드 스텁
		this.px = x;
		this.py = y;
	}

	@Override
	public void setLocation(int x, int y) {
		// TODO 자동 생성된 메소드 스텁
		this.line.setLine(ix, iy, x, y);
	}

	@Override
	public void move(int newX, int newY) {
		// TODO 자동 생성된 메소드 스텁

	}

	@Override
	public void resize(int newX, int newY) {
		this.px = newX;
		this.py = newY;
		this.line.setLine(ix, iy,newX,newY);
	}

	@Override
	public void rotate(int newX, int newY) {
		// TODO 자동 생성된 메소드 스텁

	}

}
