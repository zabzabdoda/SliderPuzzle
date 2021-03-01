package Backend;

import java.awt.Dimension;

import javax.swing.JFrame;

import frontend.Window;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Main {
	
	public static void main(String[] args) {

		Window drawing = new Window();

		PApplet.runSketch(new String[] { "" }, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame) canvas.getFrame();

		window.setSize(600, 600);
		window.setMinimumSize(new Dimension(100, 100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);
		window.setTitle("Silder Puzzle");
		canvas.requestFocus();
	}
	
}