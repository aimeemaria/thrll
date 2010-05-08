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
	ImageIcon[] boys;
	int xposition[], yposition[];//array of people positions
	int eposx[],eposy[];//array of land element positions
	char type[];//array that holds type of each land element at index
	boolean flag=false; //flag if graphics needs to be painted

	//peopleSize = crowd size
	int peopleSize;
	//numObjs = LandObjs.size()
	int numObjs;

	public DisplayPanel() {	
		setBackground(Color.white);
		setPreferredSize( new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		peopleSize = 0;

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
		int cr = 40; //hub radius
		g.setStroke(new BasicStroke(5));
		//GradientPaint grad1 = new GradientPaint(0, 0, Color.cyan, 175, 175, Color.green, true);
		//GradientPaint grad2 = new GradientPaint(0, 0, Color.green, 175, 175, Color.cyan, true);


		ImageIcon boy = createImageIcon("boy.gif"), girl =createImageIcon("girl.gif"), female = createImageIcon("Female.gif"), 
		male = createImageIcon("Boss.gif"),	attraction = createImageIcon("Flag.gif"), store = createImageIcon("Shopping cart.gif"),
		restaurant = createImageIcon("Home.gif");

		//hub for circle
		Ellipse2D.Double circle = new Ellipse2D.Double(cx-cr,cy-cr,cr*2,cr*2);

		//TITLE
		g.setColor(Color.black); //set color and fonts
		Font MyFont = new Font("Serif",Font.ROMAN_BASELINE,25);
		g.setFont(MyFont);

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




		// g.translate(0, 0);
		//g.setStroke(new BasicStroke(5)); // 8-pixel wide pen
		g.setColor(Color.white);
		g.fill(circle);
		g.setColor(Color.black);
		g.draw(circle);


		if(flag){
			g.drawString("Your Park Snapshot",cx - 150,50);

			//find the peopleSize
			for(int i = 0; i < peopleSize; i++ ) {
				//System.out.println("xposition: " + xposition[i] + " yposition: " + yposition[i]);
				g.setColor(Color.red);
				//g.drawOval(xposition[i], yposition[i], 1, 1);
				boy.paintIcon(this,g,xposition[i],yposition[i]);
			}
			
			for(int j = 0;j < numObjs;j++){
				switch(type[j]){
				case 'a': 
					attraction.paintIcon(this,g,eposx[j],eposy[j]);
					break;
				case 's':
					store.paintIcon(this,g,eposx[j],eposy[j]);
					break;
				case 'r':restaurant.paintIcon(this,g,eposx[j],eposy[j]);
				}
			}

			//draw people icons
		//	int xpos=cx; //position of person
			//int ypos=cy;
			//double r=0.5;
			//degrees = 0;

//			for (int i = 1 ; i < 18 ; i++){
//				rad = degrees * Math.PI / 180; 
//				xpos=(int)( (r*cr) * Math.cos(rad)+ cx);
//				ypos=(int)( (r*cr) * Math.sin(rad)+ cy);
//				degrees +=30;
//				//System.out.println(xpos + " " + ypos);
//				//boy.paintIcon(this,g,xpos,ypos);
//			}
		}
		else{
			g.drawString("Theme Park Layout",cx - 150,50);
			g.drawString("Hub",cx-25,cy+10);
			degrees = 30;
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
		}



	}//end of drawhub

	public void paintppl(int x, int y){

		//read this information from position.txt

		flag = true;

		//read the positions from position.txt and put it on the file..
		try {
			String read;
			int prevId = 0;
			int id = 1;
			Random generator = new Random();
			BufferedReader reader =  new BufferedReader(new FileReader(new File("position.txt")));

			//read first line with number of land elements
			read = reader.readLine();
			numObjs = Integer.parseInt(read);
			eposx = new int[numObjs];
			eposy = new int[numObjs];
			type = new char[numObjs];
			for(int i = 0 ; i < numObjs;i++){
				read = reader.readLine();
				String[] line = read.split(" ");
				type[i] = line[0].charAt(0);
				eposx[i] = Integer.parseInt(line[1]);
				eposy[i] = Integer.parseInt(line[2]);
				
			}
			
			//read with the number of people.
			read = reader.readLine();
			peopleSize = Integer.parseInt(read);
			
			//create Everyone, with position things...
			//may not need all these boys... only the position may be sufficient. Not sure of this one.
			//boys = new ImageIcon[peopleSize];
			xposition = new int[peopleSize];
			yposition = new int[peopleSize];
			//for(int i=0;i<peopleSize;i++) {
			//	boys[i] = createImageIcon("boy.gif");
			//}

			Thread.sleep(200);
			paintImmediately(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
			
			//next few lines are positions of the elements(store, attraction, store)

			//read people positions
			while ((read = reader.readLine()) != null) {
				String[] line = read.split(":");
				prevId = id;
				id = Integer.parseInt(line[0]);
				double posx = Double.parseDouble(line[1]);
				double posy = Double.parseDouble(line[2]);
				
				xposition[id] = (int)((posx == 0) ? 0: (posx + 240 + generator.nextInt(40)));
				yposition[id] = (int)((posy == 0) ? 0: (posy + 240 + generator.nextInt(30)));
				
				//boy.paintIcon(this,g,(int)xposition[id],(int)posy+240);
				//paintImmediately(xposition[id] - 4, yposition[id] - 4, 10, 10);
				paintImmediately(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				if(id < prevId ) { //when the next tick starts.
					repaint();
					Thread.sleep(10);
				}
			}
		}catch(IOException ioexception) {

		}catch(InterruptedException e) {

		}

		repaint();
		
		//
		//	
		//		int degrees = 15;
		//		double rad = 0;
		//		int xpos=x; //position of person
		//		int ypos=y;
		//		int cr = 30;
		//		int cx = 200, cy = 200;
		//		double r=2;
		//		for (int i = 1 ; i < 7 ; i++){
		//			rad = degrees * Math.PI / 180; 
		//			xpos=(int)( (r*cr) * Math.cos(rad)+ cx);
		//			ypos=(int)( (r*cr) * Math.sin(rad)+ cy);
		//			degrees +=60;
		//		
		//			//Thread.sleep(1000);
		//		}

		/*String phrase = "the music made   it   hard      to        concentrate";
		String delims = "[ ]+";
		String[] tokens = phrase.split(delims);
		for (int i = 0; i < tokens.length; i++)
		    System.out.println(tokens[i]);
		 */

	}

}

