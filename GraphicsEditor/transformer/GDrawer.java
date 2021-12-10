package transformer;

import java.awt.Graphics2D;
import java.util.Vector;

import shape.GShape;

public class GDrawer extends GTransfomer {
	
	public GDrawer(GShape selectedShape) {
		super(selectedShape);
	}

	public void initTransforming(Graphics2D g2D,int x, int y) {
		this.getShape().setLocation(x, y);
		this.getShape().draw(g2D);
		this.getShape().initPoint(x,y);
	}
	
	public void keepTransforming(Graphics2D g2D, int x, int y, Vector<GShape> shapeVector) {
		this.getShape().draw(g2D);
		this.getShape().resize(x, y);
		this.getShape().draw(g2D);
	}
	public void continueTransforming(Graphics2D g2D, int x, int y) {
		this.getShape().addPoint(x,y);
	}
	
	public void finishTransforming(Graphics2D g2D,int x, int y) {
	}
	
	public void finishSeletTransforming(Vector<GShape> shapeVector) {
		this.getShape().contains(shapeVector);
	}

}
