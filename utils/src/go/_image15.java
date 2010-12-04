package go;

import java.awt.*;
import java.awt.event.*;

public class _image15 extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Image img;

	public static void main(String[] args) {
		// _image15 ai = new _image15();
	}

	public _image15(String r) {
		super("Image Frame");

		MediaTracker mt = new MediaTracker(this);
		img = Toolkit.getDefaultToolkit().getImage(r);
		mt.addImage(img, 0);
		setSize(330, 230);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		if (img != null) {
			g.drawImage(img, 100, 100, this);
		} else {
			g.clearRect(0, 0, getSize().width, getSize().height);
		}
	}
}