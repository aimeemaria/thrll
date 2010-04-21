import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;

public class DisplayPanel extends JPanel {
	int xs;
	int ys;
	int SCREEN_WIDTH=500; //screen size for panel
	int SCREEN_HEIGHT=600;
	
	public DisplayPanel() {
		
		setBackground(Color.white);
		//setForeground(Color.blue);
		setPreferredSize( new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
	}

	protected void paintComponent(Graphics g1) {
		 super.paintComponent(g1);

		  // Cast Graphics to Graphics2D
		  Graphics2D g = (Graphics2D) g1;
/*
		g.setColor(getBackground()); //colors the window
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black); //set color and fonts
		Font MyFont = new Font("SansSerif",Font.BOLD,14);
		g.setStroke(new BasicStroke(5));
		g.setFont(MyFont);
		xs=10;   //where to start printing on the panel
		ys=20;
		g.drawString("Park Lands and Layout\n",xs,ys);
		ys=ys+10;
		
		this.drawhub(g); // draw the tree
		revalidate(); //update the component panel*/
		  this.drawhub(g);
	}

	public void drawhub(Graphics2D g) {//actually draws the tree
		
		//center and radius of hub
		int cx = (int)SCREEN_WIDTH  / 2;
		int cy = (int) SCREEN_HEIGHT / 2;
		int cr = 50; //radius
		
		GradientPaint grad1 = new GradientPaint(0, 0, Color.cyan, 175, 175, Color.green, true);
		GradientPaint grad2 = new GradientPaint(0, 0, Color.green, 175, 175, Color.cyan, true);
		
	 
		Ellipse2D.Double circle = new Ellipse2D.Double(cx-cr,cy-cr,cr*2,cr*2);
		g.setColor(Color.red); //set color and fonts
		Font MyFont = new Font("Serif",Font.ROMAN_BASELINE,25);
		g.setFont(MyFont);
		g.drawString("(^_^)   Layout   (^_^) ",100,50);
		
		double rad ; //radians
		int x,x1,y,y2 ;
		int degrees = 1;
		
		int idx = 5;
		for (int i = 1 ; i < 4 ; i++){
		rad = degrees * Math.PI / 180; 
		x=(int)( (idx*cr) * Math.cos(rad)+ cx);
		y=(int)( (idx*cr) * Math.sin(rad)+ cy);
		
		degrees += 60;
		rad = degrees * Math.PI / 180; 
		x1=(int)( (idx*cr) * Math.cos(rad)+ cx);
		y2=(int)( (idx*cr) * Math.sin(rad)+ cy);
		
		degrees+=60;
		int[] xPoints = { cx, x, x1 };
		int[] yPoints = { cy, y, y2};
		Polygon tri = new Polygon(xPoints, yPoints, 3);
		g.setPaint(grad1);
		g.fill(tri);
		g.setColor(Color.black);
		g.draw(tri);
	}
		
		degrees = 61;
		
		for (int i = 1 ; i < 4 ; i++){
		rad = degrees * Math.PI / 180; 
		x=(int)( (idx*cr) * Math.cos(rad)+ cx);
		y=(int)( (idx*cr) * Math.sin(rad)+ cy);
		
		degrees += 60;
		rad = degrees * Math.PI / 180; 
		x1=(int)( (idx*cr) * Math.cos(rad)+ cx);
		y2=(int)( (idx*cr) * Math.sin(rad)+ cy);
		
		degrees+=60;
		int[] xPoints = { cx, x, x1 };
		int[] yPoints = { cy, y, y2};
		Polygon tri = new Polygon(xPoints, yPoints, 3);
		g.setPaint(grad2);
		g.fill(tri);
		g.setColor(Color.black);
		
		//g.draw(r);
		g.draw(tri);
	}
		
		Rectangle r;
		degrees = 15;
		idx = 3;
		for(int j = 0 ; j<2;j++){
			for (int i = 0 ; i < 12 ; i++){
				rad = degrees * Math.PI / 180; 
				x=(int)( (idx*cr) * Math.cos(rad)+ cx);
				y=(int)( (idx*cr) * Math.sin(rad)+ cy);
				degrees += 30;
				g.setColor(Color.black);
				r = new Rectangle(x, y, 15, 15);
				g.fill(r);
			}
			idx++;
			degrees=15;
		}
		
		Ellipse2D.Double c2;
		degrees = 30;
		idx = 2;
		
		for (int i = 0 ; i < 6 ; i++){
			rad = degrees * Math.PI / 180; 
			x=(int)( (idx*cr) * Math.cos(rad)+ cx);
			y=(int)( (idx*cr) * Math.sin(rad)+ cy);
			degrees += 60;
			c2 = new Ellipse2D.Double(x,y,20,20);
			g.setColor(Color.yellow);
			g.fill(c2);
			g.setColor(Color.black);
			g.draw(c2);
		}
		g.setColor(Color.yellow);
		g.fill(circle);
		//g.setStroke(new BasicStroke(5)); // 8-pixel wide pen
		g.setColor(Color.black);
		g.draw(circle);
		
		
		/*
		int degrees = 54;
		double rad ;
		int x,x1,y,y2 ;
		//System.out.println("x = " + (int)x);
		//System.out.println("y = " + (int)y);
		
	
	    
	    
	    
		for(int i = 1 ; i<=5;i++){
		//for (int i = degrees ;i <360;i+=72){
			rad = degrees * Math.PI / 180; 
			x=(int)( cr * Math.cos(rad) + cx);
			y=(int)( cr * Math.sin(rad)+ cy);
			x1=(int)( (cr+100) * Math.cos(rad) + cx);
			y2=(int)( (cr+100) * Math.sin(rad)+ cy);
			//System.out.println("x = " + (int)x);
			//System.out.println("y = " + (int)y);
			
			g.setColor(Color.red);
			g.drawLine(x,y,x1,y2);
			
			

			degrees += 72;
		}
		
		int br=130;//big circle radius
		g.setStroke(new BasicStroke(8));
		g.setColor(Color.green);
		g.drawOval(cx-br,cy-br,br*2,br*2);
		
		degrees =18;
		int xs,ys;
		
		g.setColor(Color.gray); //set color and fonts
		Font MyFont = new Font("Serif",Font.PLAIN,10);
		g.setFont(MyFont);
		for(int i = 1 ; i<=5;i++){
			//for (int i = degrees ;i <360;i+=72){
				rad = degrees * Math.PI / 180; 
				xs=(int)( (cr+50) * Math.cos(rad) + cx);
				ys=(int)( (cr+50) * Math.sin(rad)+ cy);
				
				g.drawString("Land",xs,ys);
				degrees += 72;
			}
		MyFont = new Font("Serif",Font.BOLD,12);
		g.setFont(MyFont);
		g.setColor(Color.pink);
		g.drawString("(^_^)", cx-18,cy+5 );
		*/
	}//end of drawhub

}

