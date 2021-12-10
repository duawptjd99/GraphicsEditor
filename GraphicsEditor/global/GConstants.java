package global;

import java.awt.Cursor;

import shape.GEllipse;
import shape.GLine;
import shape.GPolygon;
import shape.GRectangle;
import shape.GShape;

public class GConstants {
	public final static int MAINFRAME_X = 200;
	public final static int MAINFRAME_Y = 100;
	public final static int MAINFRAME_W = 600;
	public final static int MAINFRAME_H = 600;

	public enum EToolMenu {
		rectangle("image/1-1.JPG", "image/1-2.JPG", new GRectangle()),
		polygon("image/4-1.JPG", "image/4-2.JPG", new GPolygon()),
		ellipse("image/2-1.JPG", "image/2-2.JPG", new GEllipse()), line("image/3-1.JPG", "image/3-2.JPG", new GLine());

		private String iconFileName;
		private String iconSLTFileName;
		private GShape selectedTool;

		private EToolMenu(String iconFileName, String iconSLTFileName, GShape selectedTool) {
			this.iconFileName = iconFileName;
			this.iconSLTFileName = iconSLTFileName;
			this.selectedTool = selectedTool;
		}

		public String getIconFileName() {
			return this.iconFileName;
		}

		public String getIconSLTFileName() {
			return this.iconSLTFileName;
		}

		public GShape getSelectedTool() {
			return this.selectedTool;
		}

	}

	public enum EMenu {

		fileMenu("File"), ColorMenu("color"), EditMenu("edit");

		private String text;

		private EMenu(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}

	}

	public enum EFileMenu {
		newItem("���� �����", "nnew","���� ����ϴ�."), openItem("����", "open","������ ���ϴ�."), saveItem("����", "save","���ݱ��� ���۾��� �����մϴ�."), saveAsItem("�ٸ��̸�����", "saveAs","���ݱ��� ���۾��� �ٸ��̸����� �����մϴ�."),
		closeItem("�ݱ�", "close","�ݽ��ϴ�.");
		private String text;
		private String method;
		private String tip;

		private EFileMenu(String text, String method, String tip) {
			this.text = text;
			this.method = method;
			this.tip = tip;
		}

		public String getText() {
			return this.text;
		}

		public String gettip() {
			return this.tip;
		}

		public String getMethod() {
			return this.method;
		}

	}

	public enum EColorMenu {
		line("�׵θ��� ����", "line","�׵θ� ���� �����մϴ�."), fill("ä���� ����", "fill","ä��� ���� �����մϴ�.");
		private String text;
		private String method;
		private String tip;
		
		private EColorMenu(String text, String method,String tip) {
			this.text = text;
			this.method = method;
			this.tip = tip;
		}

		public String getText() {
			return this.text;
		}

		public String getMethod() {
			return this.method;
		}
		public String gettip() {
			return this.tip;
		}
	}

	public enum EEditMenu {
		undo("undo", "undo",",�������ΰ���"), redo("redo", "redo","�����ΰ���"), cut("�߶󳻱�", "cut","�߶���ϴ�."), copy("�����ϱ�", "copy","�����մϴ�."), paste("�ٿ��ֱ�", "paste","�ٿ��ֽ��ϴ�."),
		clipboard("Ŭ������", "clipboard","Ŭ�������Դϴ�.");
		private String text;
		private String method;
		private String tip;
		
		private EEditMenu(String text, String method,String tip) {
			this.text = text;
			this.method = method;
			this.tip = tip;
		}

		public String getText() {
			return this.text;
		}

		public String getMethod() {
			return this.method;
		}
		public String gettip() {
			return this.tip;
		}
	}

	public enum EPrintMenu {
		Print("����Ʈ", "print"), review("�̸�����", "review");
		private String text;
		private String method;

		private EPrintMenu(String text, String method) {
			this.text = text;
			this.method = method;
		}

		public String getText() {
			return this.text;
		}

		public String getMethod() {
			return this.method;
		}
	}

	public enum EAnchors {
		N(Cursor.N_RESIZE_CURSOR), S(Cursor.S_RESIZE_CURSOR), E(Cursor.E_RESIZE_CURSOR), W(Cursor.W_RESIZE_CURSOR),
		NE(Cursor.NE_RESIZE_CURSOR), NW(Cursor.NW_RESIZE_CURSOR), SE(Cursor.SE_RESIZE_CURSOR),
		SW(Cursor.SW_RESIZE_CURSOR), R(Cursor.W_RESIZE_CURSOR);

		private int cursorType;

		private EAnchors(int cursorType) {
			this.cursorType = cursorType;
		}

		public int getCursor() {
			return this.cursorType;
		}
	}

}
