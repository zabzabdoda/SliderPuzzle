package frontend;
import java.awt.Color;

import processing.core.PApplet;

public class Button{
	
	private int x,y,width,height;
	private String text;
	private boolean isHovered;
	private ButtonListener buttonListener;
	
	public Button(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.isHovered = false;
		buttonListener = null;
	}
	
	public void setListener(ButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}
	
	public void draw(PApplet p) {
		p.pushStyle();
		p.textAlign(PApplet.CENTER,PApplet.CENTER);
		p.textSize(20);
		if(isHovered) {
			p.fill(Color.YELLOW.getRGB());
			p.rect(x, y, width, height,30,30,30,30);
			p.fill(Color.BLACK.getRGB());
			p.text(text, x, y,width,height);
		}else {
			p.fill(Color.WHITE.getRGB());
			p.rect(x, y, width, height,30,30,30,30);
			p.fill(Color.BLACK.getRGB());
			p.text(text, x, y,width,height);
		}

		p.popStyle();
	}
	
	public boolean detectCollision(int x, int y) {
		if(this.x <= x && this.y <= y && this.x + width >= x && this.y + height >= y) {
			return true;
		}
		return false;
	}
	
	public void click() {
		if(buttonListener != null) {
			buttonListener.onClicked();
		}
	}
	
	public void hover(boolean hover) {
		isHovered = hover;
	}
	
	public String getText() {
		return text;
	}

	public interface ButtonListener {
		
		public void onClicked();
		
	}
	
}
