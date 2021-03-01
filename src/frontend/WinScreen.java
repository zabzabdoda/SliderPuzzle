package frontend;
import java.util.HashSet;

import frontend.Button.ButtonListener;
import processing.core.PApplet;

public class WinScreen {
	
	private int x,y,width,height;
	private HashSet<Button> buttons;
	
	public WinScreen(int x, int y, int width, int height, ButtonListener resetListener, ButtonListener quitListener) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setupButtons(resetListener,quitListener);
	}
	
	public void draw(PApplet p, int moves) {
		p.pushStyle();
		p.fill(200);
		p.rect(y, x, width, height);
		p.fill(0);
		p.textAlign(PApplet.CENTER,PApplet.CENTER);
		p.textSize(20);
		p.text("You Win!!!\n"+"You made "+moves+" moves. Try Again?", x, y,width,height-height/2);
		for(Button b : buttons) {
			b.draw(p);
			if(b.detectCollision(p.mouseX, p.mouseY)) {
				b.hover(true);
			}else {
				b.hover(false);
			}
		}
		p.popStyle();
	}
	
	public void click(int x, int y) {
		for(Button b : buttons) {
			if(b.detectCollision(x, y)) {
				b.click();
			}
		}
	}
	
	public void setupButtons(ButtonListener resetListener, ButtonListener quitListener) {
		buttons = new HashSet<Button>();
		Button b = new Button(x+25,y+100,100,50,"Reset");
		b.setListener(resetListener);
		buttons.add(b);
		b = new Button(x+175,y+100,100,50,"Quit");
		b.setListener(quitListener);
		buttons.add(b);
	}
	
}
