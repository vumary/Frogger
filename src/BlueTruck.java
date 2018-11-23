import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BlueTruck {
	
	private int x, y;				//xy position of frog
	private int dir;				//possibly need to track what dir we need to draw
	private int width, height;		//width and height of this car
	private JLabel img;				//
	
	//add constructor that takes ina file name
	public BlueTruck(String filename) {
		String src = new File("").getAbsolutePath()+"/src/";
		ImageIcon ghost = new ImageIcon(src+filename);
		img = new JLabel(ghost); //connect 
		
		//bound img to the object
		width = 40;
		height = 20;
		x = 600/2 - width/2;
		y = 260;
		img.setBounds(x, y, width, height);
	}
	
	public void move() {
		x-=4; //move to the left
		
		//check is x passes left side of frame
		if(x<0) {
			x = 600;	//changing x position so that car will move to the far right
		}
		
		img.setBounds(x, y, width, height); //attaches the bounds to the image when we change its x position
		
	}
	
	public void moveLeft() {
		x-=width;
		//moving object should update its img
		img.setBounds(x, y, width, height);
	}
	
	public void moveRight() {
		x+=width;
		img.setBounds(x, y, width, height);
	}
	
	public void moveUp() {
		y-=height;
		img.setBounds(x, y, width, height);
	}
	
	public void moveDown() {
		y+=height;
		img.setBounds(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public JLabel getImg() {
		return img;
	}

	public void setImg(JLabel img) {
		this.img = img;
	}

}
