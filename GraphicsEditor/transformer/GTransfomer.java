package transformer;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Vector;

import shape.GShape;

public abstract class GTransfomer {

	protected GShape selectedShape;
	protected Point2D previous, center;

	public GTransfomer(GShape selectedShape) {
		this.selectedShape = selectedShape;
		this.previous = new Point2D.Double();
		this.center = new Point2D.Double();
	}

	public GShape getShape() {
		return this.selectedShape;
	}

	abstract public void initTransforming(Graphics2D g2D, int x, int y);

	abstract public void keepTransforming(Graphics2D g2d, int x, int y, Vector<GShape> shapeVector);

	abstract public void finishTransforming(Graphics2D g2D, int x, int y);

	
	public void continueTransforming(Graphics2D g2d, int x, int y) {
	}

	public void moveLittle() {
	}

	public void finishSeletTransforming(Vector<GShape> shapeVector) {

	}
	
	

}
