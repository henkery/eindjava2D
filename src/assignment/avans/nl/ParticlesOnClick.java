package assignment.avans.nl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class ParticlesOnClick extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		ParticlesOnClick c =new ParticlesOnClick();
		

	}
	
	public ParticlesOnClick()
	{
		super("muhparticles");
		
		JPanel mainPanel = new DragAShapePanel();
		mainPanel.setBackground(Color.black);
		this.setContentPane(mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

}

class DragAShapePanel extends JPanel 
implements MouseListener, MouseMotionListener 
{

private int teller = 0;

private int x = 100;
private int y = 100;

private Ellipse2D circle1;
private Ellipse2D circle2;
private Point move1;
private Point move2;


boolean isSelected = false;

private boolean knal;

/* Constructor */
public DragAShapePanel()
{
	setPreferredSize( new Dimension(640,480));
	
	circle1 = new Ellipse2D.Double(50, 50, 100, 100);
	circle2 = new Ellipse2D.Double(150, 150, 100, 100);
	move1 = new Point(1, 1);
	move2 = new Point(-1, -1);
	
	addMouseListener(this);
	addMouseMotionListener(this);
	Runnable run1 = new Runnable() {
        public void run() {
        	while (true)
        	{
                try {
                    Thread.sleep(10);
                    repaint();
                    moveCirlces();
                    //System.out.println(ai.getData());
                } catch(Exception e) {
                    System.out.println(e);
                }
        	}
        }
        
    };
    Thread thread1 = new Thread(run1);
    thread1.start();
}

// Repaint
public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g;
	g2.setColor(Color.yellow);
	g2.setBackground(Color.black);
	
	g2.fill(circle1);
	g2.fill(circle2);
	
	
	
	if (knal)
	{
		for (int i=0; i<(int)Math.floor(Math.random()*20); i++)
		{
			g2.setColor(new Color((int) Math.floor(Math.random()*256), (int) Math.floor(Math.random()*256), (int) Math.floor(Math.random()*256)));
			Shape lel = new Rectangle2D.Double(x-25+ (int) Math.floor(Math.random()*51), y-25+ (int) Math.floor(Math.random()*51), 6, 6);
			g2.fill(lel);
			
			
		}
		/*try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.clearRect(0, 0, this.getWidth(), this.getHeight());*/
		knal = false;
	}
	
	
	
	
	
	
}

public void moveCirlces()
{
	//pos += vel;
	
	System.out.println("window size is " + this.getWidth() + "x" + this.getWidth());
	//vel += acc;
	if (circle1.getMinX() > 0 && circle1.getMaxX() < this.getHeight())
	{
		
		if (circle1.getX() > this.getHeight())
		{
			circle1 = new Ellipse2D.Double(circle1.getX() + move1.getX(), circle1.getY(), 100, 100);
			
		}
		else if (circle1.getX() <= this.getHeight())
		{
			circle1 = new Ellipse2D.Double(circle1.getX() + move1.getX(), circle1.getY(), 100, 100);
		}
	}
	else
	{
		move1.setLocation(-move1.getX(), move1.getY());
		System.out.println("changing move1 X 2");
		
	}
	if (circle1.getMinY() > 0 && circle1.getMaxY() < this.getHeight())
	{
		//System.out.println("nn");
		if (circle1.getY() > this.getWidth())
		{
			circle1 = new Ellipse2D.Double(circle1.getX(), circle1.getY() + move1.getY(), 100, 100);
		}
		else if (circle1.getY() < this.getWidth())
		{
			circle1 = new Ellipse2D.Double(circle1.getX(), circle1.getY() + move1.getY(), 100, 100);
		}
		
	}
	else
	{
		move1.setLocation(move1.getX(), -move1.getY());
		System.out.println("changing move1 X 4");
	}
	System.out.println("current location is " + circle1.getX() + "x" + circle1.getY());
}

@Override
public void mouseDragged(MouseEvent arg0) {
//	System.out.println( "mouseDragged " + arg0.getX() + " " + arg0.getY() );
	
	
}

@Override
public void mouseMoved(MouseEvent arg0) {
	System.out.println( "mousemove at " + arg0.getX() + " " + arg0.getY() );
	x = arg0.getX();
	y = arg0.getY();
	knal = true;
	
}

@Override
public void mouseClicked(MouseEvent arg0) {
	
	
	
}

@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
}

@Override
public void mouseReleased(MouseEvent arg0) {
	
}
}
