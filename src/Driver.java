import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*
;
public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	
	//properties of this class - the panel that shows up
	int screen_width 	= 600;
	int screen_height 	= 600;
	int max_vals = 200;
	int size = 30;
	int g = 0;
	String bg = "background.png";
	JLabel background;
	String wn = "win.png";
	JLabel win;
	String frogimg = "frog.png";
	String carimg = "car.png";
	String car2img = "car2.png";
	String truckimg = "truck.png";
	String gameimg = "GameOver.png";
	String car3img = "car3.png";
	
	boolean won = false;		//if the player won this variable will be set to true, else it will be false
	
	Frog frogger = new Frog(frogimg);
	
	//List of car and truck objects below
	ArrayList<GreenCar> greenCars = new ArrayList<GreenCar>();

	ArrayList<RedCar> redCars = new ArrayList<RedCar>();
	
	ArrayList<BlueTruck> blueTrucks = new ArrayList<BlueTruck>();
	
	ArrayList<BlackCar> blackCars = new ArrayList<BlackCar>();
	
	//only do drawing for paint
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setFont(font);
	}//end of paint method - put code above for anything dealing with drawing -
	
	
	
	Font font = new Font ("Courier New", 1, 50);
	
	/* do not draw here */
	public void update() {
		
		if(frogger.getY()<150&&frogger.getX()<350&&frogger.getX()>250) {
			won = true;
			win.setVisible(won);
		}
		
		
		//rectangle representation of frog
		Rectangle frect = new Rectangle(frogger.getX(), frogger.getY(), 20, 20);
		
		for(int i=0; i<redCars.size(); i++) {
			
			//rectangle representation of all red cars
			Rectangle obstacle = new Rectangle(redCars.get(i).getX(), redCars.get(i).getY(), 40, 20);
			
			//rectangle representation of all green cars
			Rectangle obstacle2 = new Rectangle(greenCars.get(i).getX(), greenCars.get(i).getY(), 40, 20);
			
			//rectangle representation of all black cars
			Rectangle obstacle4 = new Rectangle(blackCars.get(i).getX(), blackCars.get(i).getY(), 40, 20);
			
			//rectangle representation of all blue trucks
			Rectangle obstacle3 = new Rectangle(blueTrucks.get(i).getX(), blueTrucks.get(i).getY(), 40, 20);
			
			
			if(frect.intersects(obstacle)||frect.intersects(obstacle2)||frect.intersects(obstacle3)||frect.intersects(obstacle4)) {
				//detected overlap between obstacles
				frogger.reset();
			}
		}
		
		//traverse array list and tell each object to call move()
		for(int i=0; i < redCars.size();i++) {
			RedCar temp = redCars.get(i);
			temp.move();
		}
		
		for(int i=0; i < greenCars.size();i++) {
			GreenCar temp = greenCars.get(i);
			temp.move();
		}
		
		for(int i=0; i < blackCars.size();i++) {
			BlackCar temp = blackCars.get(i);
			temp.move();
		}
		
		for(int i=0; i < blueTrucks.size();i++) {
			BlueTruck temp = blueTrucks.get(i);
			temp.move();
		}
		
		
	}//end of update method - put code above for any updates on variable
		
	
	//==================code above ===========================
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}
	
	public static void main(String[] arg) {
		Driver d = new Driver();
	}
	public Driver(){
		
		JFrame f = new JFrame();
		f.setTitle("Click Em");
		f.setSize(screen_width, screen_height);
		f.getContentPane().setBackground(Color.black);
		String src = new File("").getAbsolutePath()+"/src/"; //path to image setup
		ImageIcon backg = new ImageIcon(src+bg);    //setups icon image
		ImageIcon winner = new ImageIcon(src+wn);    //setups icon image
		background = new JLabel(backg);
		background.setBounds(0,0,600,600); //set location and size of icon
		win= new JLabel(winner);
		win.setBounds(0,0,600,600);
		win.setVisible(false);
		f.add(win);
		f.setResizable(false);
		f.setLayout(null);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);
		
		JLabel scoreLabel = new JLabel("score: "+frogger.getScore());
		scoreLabel = new JLabel("score: "+frogger.getScore());
		scoreLabel.setForeground(Color.white);
		scoreLabel.setBounds(50,70,100,50);
		
		f.add(scoreLabel);
		
		//add 6 Car objects to my cars arrayList
		for(int i =0; i < 8; i++) {
			BlueTruck newCar = new BlueTruck(truckimg);
			newCar.setY(newCar.getY());
			newCar.setX(i*130);
			blueTrucks.add(newCar); //add to arraylist
			
			//add to frame
			f.add(newCar.getImg());
		}
		
		for(int i =0; i < 8; i++) {
			GreenCar newCar = new GreenCar(car2img);
			newCar.setY(newCar.getY());
			newCar.setX(i*120);		//spreads out the spacing of the cars by multiplying the X position
			greenCars.add(newCar); //add to arraylist
			
			//add to frame
			f.add(newCar.getImg());
		}
		
		for(int i =0; i < 8; i++) {
			RedCar newCar = new RedCar(carimg);
			newCar.setY(newCar.getY());
			newCar.setX(i*150);
			redCars.add(newCar); //add to arraylist
			
			//add to frame
			f.add(newCar.getImg()); 
		}
		
		for(int i =0; i < 8; i++) {
			BlackCar newCar = new BlackCar(car3img);
			newCar.setY(newCar.getY());
			newCar.setX(i*100);
			blackCars.add(newCar); //add to arraylist
			
			//add to frame
			f.add(newCar.getImg());
		}
		
		//Add Frog Character (img)
		f.add(frogger.getImg());
		
		//add background after
		f.add(background);
		
		//end creating objects
		t = new Timer(17,this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==38){ //38 is up
			frogger.moveUp();
		}
		
		if(e.getKeyCode()==40){ //40 is down
			frogger.moveDown();
		}
		
		if(e.getKeyCode()==39){ //39 is right
			frogger.moveRight();
		}
		
		if(e.getKeyCode()==37){ //37 is left
			frogger.moveLeft();
		}
		update();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println(e.getX());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
 
	public void reset(){
	
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}