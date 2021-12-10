package transformer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import shape.GShape;

public class GResizer extends GTransfomer {

	public GResizer(GShape selectedShape) {
		super(selectedShape);
		// TODO 자동 생성된 생성자 스텁
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
				setSizing(x, y, shape);
				shape.draw(g2d);
			}
		}
	}

	public void setSizing(int x, int y, GShape shape) {
		double Rx = shape.getCenterX() + shape.getWidth() / 2;
		double Ry = shape.getCenterY() + shape.getHeight() / 2;
		double RCX = shape.getCenterX();
		double RCY = shape.getCenterY();
		double RH = shape.getHeight();
		double RW = shape.getWidth();
		double RX = 0;
		double RY = 0;
		if (shape.getSelectedAnchor().ordinal() == 0) {
			// 가운데위
			if (y < RCY + RH / 2) {
				RX = Rx;
				RY = Ry;
				shape.setLocation((int) (RCX - RW / 2), (int) (y));
			}
		} else if (shape.getSelectedAnchor().ordinal() == 1) {
			// 가운데아래
			if (y > RCY - RH / 2) {
				RX = Rx;
				RY = y;
			}
		} else if (shape.getSelectedAnchor().ordinal() == 2) {
			// 오른쪽가운데
			if (x > RCX - RX / 2) {
				RX = x;
				RY = Ry;
			}
		} else if (shape.getSelectedAnchor().ordinal() == 3) {
			// 왼쪽가운데
			if (x < RCX + RW / 2) {
				RX = Rx;
				RY = Ry;
				shape.setLocation((int) x, (int) (RCY - RH / 2));
			}
		} else if (shape.getSelectedAnchor().ordinal() == 4) {
			// 오른쪽위
			if (x > RCX - RW / 2 && y < RCY + RH / 2) {
				RX = x;
				RY = Ry;
				shape.setLocation((int) (RCX - RW / 2), (int) (y));
			}
		} else if (shape.getSelectedAnchor().ordinal() == 5) {
			// 왼쪽위
			if (x < RCX + RW / 2 && y < RCY + RH / 2) {
				RX = Rx;
				RY = Ry;
				shape.setLocation((int) x, (int) y);
			}
		} else if (shape.getSelectedAnchor().ordinal() == 6) {
			// 오른쪽아래
			if (x > RCX - RW / 2 && y > RCY - RH / 2) {
				RX = x;
				RY = y;
			}
		} else if (shape.getSelectedAnchor().ordinal() == 7) {
			// 왼쪽아래
			if (x < RCX + RW / 2 && y > RCY - RH / 2) {
				RX = Rx;
				RY = y;
				shape.setLocation((int) x, (int) (RCY - RH / 2));
			}
		}
		this.previous.setLocation(x, y);
		shape.resize((int) (RX), (int) (RY));
	}

	@Override
	public void finishTransforming(Graphics2D g2d, int x, int y) {
		// TODO 자동 생성된 메소드 스텁

	}

}
