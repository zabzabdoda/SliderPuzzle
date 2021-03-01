package frontend;

import java.awt.Point;
import java.util.Random;

import processing.core.PApplet;

public class Board {
	
	private Cell[][] cells;
	private int x,y;
	
	public Board() {
		this.x = 100;
		this.y = 100;
		cells = new Cell[4][4];
		initCells();
	}
	
	public void draw(PApplet p) {
		p.rect(x, x, cells.length*Cell.WIDTH, cells[0].length*Cell.WIDTH);
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[0].length; j++) {
				cells[i][j].draw(p);
			}
		}
	}
	
	private void initCells() {
		
		int count = 1;
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[0].length; j++) {
				if(count == cells.length*cells[0].length) {
					count = -1;
				}
				cells[i][j] = new Cell((j*Cell.WIDTH)+Cell.WIDTH,(i*Cell.WIDTH)+Cell.WIDTH,count);
				count++;
			}
		}
	}
	
	public int[][] getBoard() {
		int[][] intBoard = new int[cells.length][cells[0].length];
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[0].length; j++) {
				intBoard[i][j] = cells[i][j].getValue();
			}
		}
		return intBoard;
	}
	
	public Cell checkAdjacent(int x, int y) {
		Cell up = null,down = null,left = null,right = null;
		try {
			up = cells[y][x-1];
		}catch(ArrayIndexOutOfBoundsException ex) {}
		
		try {
			down = cells[y][x+1];
		}catch(ArrayIndexOutOfBoundsException ex) {}
		
		try {
			right = cells[y+1][x];
		}catch(ArrayIndexOutOfBoundsException ex) {}
		
		try {
			left = cells[y-1][x];
		}catch(ArrayIndexOutOfBoundsException ex) {}
		
		if(up != null && up.getValue() == -1) {
			return up;
		}else if(down != null && down.getValue() == -1) {
			return down;
		}else if(right != null && right.getValue() == -1) {
			return right;
		}else if(left != null && left.getValue() == -1) {
			return left;
		}
		
		return null;
	}

	
	public Point getCollision(int x, int y){
	    if(x > this.x && y > this.y){
	        if(x < this.x+(cells.length*Cell.WIDTH) && y < this.y+(cells.length*Cell.WIDTH)){
	            int cellX = (x-this.x)/Cell.WIDTH;
	            int cellY = (y-this.y)/Cell.WIDTH;
	            return new Point(cellX,cellY);
	        }
	    }
	    return new Point(-1,-1);
	}

	
	public void switchCells(Cell c1, Cell c2) {
		int temp = c1.getValue();
		c1.setValue(c2.getValue());
		c2.setValue(temp);
	}
	
	public Cell getCell(int x, int y) {
		return cells[y][x];
	}
	
	//Fisher–Yates algorithm modified
	//credit: bvitaliyg StackOverflow
	private void shuffle(Cell[][] a) {
	    Random random = new Random();

	    for (int i = a.length - 1; i > 0; i--) {
	        for (int j = a[i].length - 1; j > 0; j--) {
	            int m = random.nextInt(i + 1);
	            int n = random.nextInt(j + 1);

	            int temp = a[i][j].getValue();
	            a[i][j].setValue(a[m][n].getValue());
	            a[m][n].setValue(temp);
	        }
	    }
	}
	
	public void shuffle() {
		shuffle(cells);
	}
	
}
