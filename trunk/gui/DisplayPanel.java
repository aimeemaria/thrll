import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.io.*;
import java.util.*;

public class DisplayPanel extends JPanel {
	int xs;
	int ys;
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
	int SCREEN_WIDTH=(int)dim.getWidth(); //screen size for panel
	int SCREEN_HEIGHT=(int)dim.getHeight();
	ImageIcon girlicon;
	Graphics2D g;
	boolean flag=false; //flag if graphics needs to be painted
	
	public DisplayPanel() {	
		setBackground(Color.white);
		setPreferredSize( new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		//setVisible(true);
	}
	
	protected ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	protected void paintComponent(Graphics g1) {
		 super.paintComponent(g1);

		  // Cast Graphics to Graphics2D
		  g = (Graphics2D) g1;

	//revalidate(); //update the component panel*/
		  try {
			this.drawhub(g);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//this.paintppl();
	}

	public void drawhub(Graphics2D g) throws InterruptedException {//actually draws the tree
		
		//center and radius of hub
		int cx = (int)SCREEN_WIDTH/3;
		int cy = (int) SCREEN_HEIGHT/3;
		int cr = 60; //radius
		g.setStroke(new BasicStroke(5));
		//GradientPaint grad1 = new GradientPaint(0, 0, Color.cyan, 175, 175, Color.green, true);
		//GradientPaint grad2 = new GradientPaint(0, 0, Color.green, 175, 175, Color.cyan, true);
		
		
		ImageIcon boy = createImageIcon("boy.gif"), girl =createImageIcon("girl.gif"), female = createImageIcon("female.gif"), 
		male = createImageIcon("boss.gif"),	attraction = createImageIcon("flag.gif"), store = createImageIcon("Shopping car.gif"),
				restaurant = createImageIcon("home.gif");

				
		//hub for circle
		Ellipse2D.Double circle = new Ellipse2D.Double(cx-cr,cy-cr,cr*2,cr*2);
		
		//TITLE
		g.setColor(Color.black); //set color and fonts
		Font MyFont = new Font("Serif",Font.ROMAN_BASELINE,25);
		g.setFont(MyFont);
		g.drawString("Theme Park Layout",cx - 150,50);
		
		double rad=0 ; //radians
		int x=0,x1=0,y=0,y2=0 ;
		int degrees = 60;
		
		int idx = 5;
		Polygon tri;
		for (int i = 1 ; i < 7 ; i++){
			rad = degrees * Math.PI / 180; 
			x=(int)( (idx*cr) * Math.cos(rad)+ cx);
			y=(int)( (idx*cr) * Math.sin(rad)+ cy);

			degrees += 60;
			rad = degrees * Math.PI / 180; 
			x1=(int)( (idx*cr) * Math.cos(rad)+ cx);
			y2=(int)( (idx*cr) * Math.sin(rad)+ cy);

			int[] xPoints = { cx, x, x1 };
			int[] yPoints = { cy, y, y2};

			tri = new Polygon(xPoints, yPoints, 3);
			if(i==1 || i==4)
				g.setColor(Color.blue);
			else if(i==2 || i==5)
				g.setColor(Color.red);
			else
				g.setColor(Color.green);
			g.draw(tri);
		}
		
		g.setColor(Color.blue);
		g.drawLine(cx, cy, x1, y2);
		
		
		degrees = 270;
		idx = 3;
		String t = "Land ";
		for (int i = 0 ; i < 6 ; i++){
			rad = degrees * Math.PI / 180; 
			x=(int)( (idx*cr) * Math.cos(rad)+ cx);
			y=(int)( (idx*cr) * Math.sin(rad)+ cy);
			degrees += 60;
			g.setColor(Color.black);
			g.drawString(t+(i+1), x-40, y);
		}
		  
		
		// g.translate(0, 0);
		//g.setStroke(new BasicStroke(5)); // 8-pixel wide pen
		g.setColor(Color.white);
		g.fill(circle);
		g.setColor(Color.black);
		g.draw(circle);
		
		

		
		//draw people icons
		int xpos=cx; //position of person
		int ypos=cy;
		double r=0.5;
		degrees = 0;
		if(flag){
		for (int i = 1 ; i < 7 ; i++){
			rad = degrees * Math.PI / 180; 
			xpos=(int)( (r*cr) * Math.cos(rad)+ cx);
			ypos=(int)( (r*cr) * Math.sin(rad)+ cy);
			degrees +=60;
		boy.paintIcon(this,g,xpos,ypos);
		
		}
		}
		else{
			g.drawString("Hub",cx-25,cy+10);
		}
			

	
	}//end of drawhub
	
	public void paintppl(int x, int y){
		setBackground(Color.gray);
		//g.setColor(Color.black);
		//g.drawLine(0,0,100,100);
		//paintBorder(g);
		flag = true;
		int degrees = 15;
		double rad = 0;
		int xpos=x; //position of person
		int ypos=y;
		int cr = 30;
		int cx = 200, cy = 200;
		double r=2;
		for (int i = 1 ; i < 7 ; i++){
			rad = degrees * Math.PI / 180; 
			xpos=(int)( (r*cr) * Math.cos(rad)+ cx);
			ypos=(int)( (r*cr) * Math.sin(rad)+ cy);
			degrees +=60;
		
			//Thread.sleep(1000);
		}
		//repaint();
		
		String phrase = "the music made   it   hard      to        concentrate";
		String delims = "[ ]+";
		String[] tokens = phrase.split(delims);
		for (int i = 0; i < tokens.length; i++)
		    System.out.println(tokens[i]);


	}

}

