import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;

public class DisplayPanel extends JPanel {
	int xs;
	int ys;

	public DisplayPanel() {
		
		setBackground(Color.white);
		//setForeground(Color.blue);
		//setPreferredSize( new Dimension(300,300));
	}

	protected void paintComponent(Graphics g1) {
		 super.paintComponent(g1);

		  // Cast Graphics to Graphics2D
		  Graphics2D g = (Graphics2D)g1;
		  
		  
		  
		  
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
		
		int SCREEN_WIDTH=400; //screen size for panel
		int SCREEN_HEIGHT=400;
		
		//center and radius of hub
		int cx = (int)SCREEN_WIDTH  / 2;
		int cy = (int) SCREEN_HEIGHT / 2;
		int cr = 30;
		
		GradientPaint gradient =
		    new GradientPaint(0, 0, Color.blue, 175, 175, Color.green,
		                      true);
		GradientPaint grad2 =
		    new GradientPaint(0, 0, Color.red, 175, 175, Color.yellow,
		                      true);
		Ellipse2D.Double circle = new Ellipse2D.Double(cx-cr,cy-cr,cr*2,cr*2);
		//g.drawOval(cx-cr,cy-cr,cr*2,cr*2);
		double rad ;
		int x,x1,y,y2 ;
		int degrees = 1;
		//int[] xes={0,0,0,0,0};
		//int[] yes={0,0,0,0,0};
		
		int idx = 0;
		for (int i = 1 ; i < 4 ; i++){
		rad = degrees * Math.PI / 180; 
		x=(int)( (4*cr) * Math.cos(rad)+ cx);
		y=(int)( (4*cr) * Math.sin(rad)+ cy);
		//xes[idx]=x;
		//yes[idx++]=y;
		
		degrees += 60;
		rad = degrees * Math.PI / 180; 
		x1=(int)( (4*cr) * Math.cos(rad)+ cx);
		y2=(int)( (4*cr) * Math.sin(rad)+ cy);
		//xes[idx]=x1;
		//yes[idx++]=y2;
		degrees+=60;
		int[] xPoints = { cx, x, x1 };
		int[] yPoints = { cy, y, y2};
		Polygon tri = new Polygon(xPoints, yPoints, 3);
		g.setPaint(grad2);
		g.fill(tri);
		g.draw(tri);
	}
		
		//Polygon hex = new Polygon(xes,yes,5);
		//g.draw(hex);
		
		g.setPaint(gradient);
		g.fill(circle);
		g.setStroke(new BasicStroke(5)); // 8-pixel wide pen
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

