package frontend;
import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;
import java.util.HashSet;

import frontend.Button.ButtonListener;
import processing.core.PApplet;

public class Window extends PApplet{
	
	private Board board;
	private int[][] correct =  {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,-1}};
	private WinScreen winScreen;
	private boolean win;
	private HashSet<Button> buttons;
	private int moves;
	
	public Window() {
		board = new Board();
		moves = 0;
		win = false;
		//board.shuffle();
		buttonSetup();
	}
	
	public void draw() {
		this.pushStyle();
		background(Color.LIGHT_GRAY.getRGB());
		board.draw(this);
		for(Button b : buttons) {
			b.draw(this);
		}
		this.fill(0);
		this.textAlign(PApplet.CENTER,PApplet.CENTER);
		this.text("Moves:\n"+moves, 250, 10,100,100);
		if(win) {
			winScreen.draw(this,moves);
		}
		this.popStyle();
	}

	public void resetGame() {
		win = false;
		board.shuffle();
		moves = 0;
	}
	
	public void quit() {
		System.exit(0);
	}
	
	@Override
	public void mouseMoved() {
		if(!win) {
			for(Button b : buttons) {
				if(b.detectCollision(mouseX, mouseY)) {
					b.hover(true);
				}else {
					b.hover(false);
				}
			}
		}
	}
	
	public boolean checkWin() {
		return Arrays.deepEquals(board.getBoard(), correct);
	}
	
	@Override
	public void mousePressed() {
		if(!win) {
			for(Button b : buttons) {
				if(b.detectCollision(this.mouseX, this.mouseY)) {
					b.click();
				}
			}
			Point point = board.getCollision(mouseX,mouseY);
			Cell c = board.checkAdjacent((int)point.getX(), (int)point.getY());
			if(c != null) {
				board.switchCells(board.getCell((int)point.getX(), (int)point.getY()), c);
				moves++;
			}
			if(checkWin()) {
				win = true;
			}
		}else{
			winScreen.click(mouseX,mouseY);
		}
	}
	
	public void buttonSetup() {
		buttons = new HashSet<Button>();
		Button btn = new Button(150,30,100,50,"Reset");
		btn.setListener(new ButtonListener() {
			@Override
			public void onClicked() {
				resetGame();
			}
		});
		buttons.add(btn);
		btn = new Button(350,30,100,50,"Quit");
		btn.setListener(new ButtonListener() {
			@Override
			public void onClicked() {
				quit();
			}
		});
		buttons.add(btn);
		
		ButtonListener bl1 = new ButtonListener() {
			@Override
			public void onClicked() {
				resetGame();
			}
		};
		ButtonListener bl2 = new ButtonListener() {
			@Override
			public void onClicked() {
				quit();
			}
		};
		winScreen = new WinScreen(150,150,300,175,bl1,bl2);
		
	}
	
}
