package transformer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import GDrawingPanel.GDrawingPanel.EDrawingState;
import shape.GShape;

public class GMover extends GTransfomer {
	private double dx, dy;

	public GMover(GShape selectedShape) {
		super(selectedShape);
	}

	@Override
	public void initTransforming(Graphics2D g2d, int x, int y) {
		this.getShape().saveCurrentPosition(x, y);
		this.previous.setLocation(x, y);
	}
	
	@Override
	public void keepTransforming(Graphics2D g2d, int x, int y, Vector<GShape> shapeVector) {
		AffineTransform affineTransform = new AffineTransform();
		for (GShape shape : shapeVector) {
			if (shape.getSeleted()) {
				shape.draw(g2d);
				affineTransform.translate(x-this.previous.getX(), y-this.previous.getY());
				shape.transformShape(affineTransform);
				shape.draw(g2d);
				this.previous.setLocation(x, y);
			}
		}
		
	}

	@Override
	public void finishTransforming(Graphics2D g2d, int x, int y) {
		
	}

	public void moveLittle() {
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(10, 10);
		this.selectedShape.transformShape(affineTransform);

	}

}
