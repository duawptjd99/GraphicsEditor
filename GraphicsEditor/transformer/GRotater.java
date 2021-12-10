package transformer;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import shape.GShape;

public class GRotater extends GTransfomer {

	double mousex;
	double mousey;
	
	public GRotater(GShape selectedShape) {
		super(selectedShape);
		// TODO 자동 생성된 생성자 스텁
	}

	@Override
	public void initTransforming(Graphics2D g2d, int x, int y) {
		// TODO 자동 생성된 메소드 스텁
		this.getShape().saveCurrentPosition(x, y);
		this.previous.setLocation(x, y);
	}

	@Override
	public void keepTransforming(Graphics2D g2d, int x, int y, Vector<GShape> shapeVector) {

		AffineTransform affineTransform = new AffineTransform();

		for (GShape shape : shapeVector) {

			if (shape.getSeleted()) {
				if (shape.getSelectedAnchor().ordinal() == 8) {
					shape.draw(g2d);

					double angle = Math.atan2(
							(((shape.getCenterY() - shape.getHeight() / 2 - 50) - shape.getCenterY())
									/ (shape.getCenterX() - shape.getCenterX())),
							((y - shape.getCenterY()) / x - shape.getCenterX()));
						
					
					
					affineTransform.rotate((Math.PI / 180) * (angle), (double) shape.getCenterX(),
							(double) shape.getCenterY());
					shape.transformShape(affineTransform);
					shape.draw(g2d);
					shape.rotate(x, y);
					// System.out.println(((Math.PI/180)*25));
				}
			}
		}
	}

	@Override
	public void finishTransforming(Graphics2D g2d, int x, int y) {
		// TODO 자동 생성된 메소드 스텁

	}

}
