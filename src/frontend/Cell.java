package frontend;
import java.awt.Color;

import processing.core.PApplet;

public class Cell {
	
	private int x,y;
	private int value;
	private boolean isEmpty;
	
	public static final int WIDTH = 100;
	
	public Cell(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
		if(value == -1) {
			isEmpty = true;
		}
	}
	
	public void draw(PApplet p) {
		if(!isEmpty) {
			p.pushStyle();
			p.fill(Color.YELLOW.getRGB());
			p.rect(x, y, WIDTH, WIDTH,20,20,20,20);
			p.fill(0);
			p.textAlign(PApplet.CENTER,PApplet.CENTER);
			p.textSize(20);
			p.text(value+"", x, y,WIDTH,WIDTH);
			p.popStyle();
		}
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
		if(value == -1) {
			isEmpty = true;
		}else {
			isEmpty = false;
		}
	}
	
	
}
