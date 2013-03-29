package assignment.avans.nl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BallsnStuff extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		BallsnStuff c =new BallsnStuff();
		

	}
	
	public BallsnStuff()
	{
		super("muh collisions");
		
		JPanel mainPanel = new DragAShapePanel();
		mainPanel.setBackground(Color.black);
		this.setContentPane(mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JToggleButton gravBut = new JToggleButton("toggle gravity");
		gravBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
		this.add(gravBut);
		while (true)
		{
			mainPanel.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Aut-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

class DragAShapePanel extends JPanel 
implements MouseListener, MouseMotionListener 
{

private int teller = 0;

private int x = 100;
private int y = 100;

//private Ball ball1,ball2;
private ArrayList<Ball> balls;

boolean isSelected = false;

private boolean knal;

private Ballcage cage;

private boolean grav;



/* Constructor */
public DragAShapePanel()
{
	setPreferredSize( new Dimension(640,480));
	
	addMouseListener(this);
	addMouseMotionListener(this);
	cage = new Ballcage(0, 0, this.getWidth(), this.getHeight());
	balls = new ArrayList<Ball>();
	//balls.add(new Ball(new Vec2f(100, 100), new Vec2f(0, 0), 100, 100));
	balls.add(new Ball(new Vec2f(200, 100), new Vec2f(1, 2), 100, 100));
	//balls.add(new Ball(new Vec2f(300, 100), new Vec2f(0, 1), 100, 100));
	balls.add(new Ball(new Vec2f(400, 100), new Vec2f(0, 0), 100, 100));
	//balls.add(new Ball(new Vec2f(200, 100), 100, 100));
	balls.add(new Ball(new Vec2f(600, 100), new Vec2f(-1, 0), 100, 100));
	grav = true;
}

// Repaint
public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g;
	g2.setColor(Color.yellow);
	g2.setBackground(Color.black);
	
	//ball1.update(this.getWidth(), this.getHeight());
	//ball1.draw(g);
	cage.resizeCage(this.getHeight(), this.getWidth());
	for (Ball ball : balls)
	{
		ball.update(cage, grav); 
		ball.collide(balls);
		ball.draw(g);
	}
	
	if (knal)
	{
		for (int i=0; i<(int)Math.floor(Math.random()*20); i++)
		{
			g2.setColor(randomColor());
			Shape lel = new Rectangle2D.Double(x-25+ (int) Math.floor(Math.random()*51), y-25+ (int) Math.floor(Math.random()*51), 6, 6);
			g2.fill(lel);
			
			
		}
		knal = false;
	}
	
	
	
	
	
	
}

public void setGrav(boolean grav)
{
	this.grav = grav;
}

public boolean getGrav()
{
	return grav;
}


public void toggleGrav()
{
	grav = !grav;
}
public void ting()
{
	repaint();
}

public Color randomColor()
{
	return new Color((int) Math.floor(Math.random()*256), (int) Math.floor(Math.random()*256), (int) Math.floor(Math.random()*256));
}

@Override
public void mouseDragged(MouseEvent arg0) {
	for (Ball item : balls)
	{
		if (item.isClicked(arg0.getX(), arg0.getY()))
		{
//			Vec2f vel = new Vec2f(largeX - smallX, largeY - smallY);
			Vec2f vel = new Vec2f(arg0.getX() - item.getPos().x, arg0.getY()- item.getPos().y);
			
			item.setPos(new Vec2f(arg0.getX(), arg0.getY()));
			item.setVel(vel);
		}
//		System.out.println("ball is " + item.isClicked(arg0.getX(), arg0.getY()) + "clicked\n\n");
	}
//	System.out.println( "mouseDragged " + arg0.getX() + " " + arg0.getY() );
	
	
}

@Override
public void mouseMoved(MouseEvent arg0) {
//	System.out.println( "mousemove at " + arg0.getX() + " " + arg0.getY() );
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
	for (Ball item : balls)
	{
//		Vec2f vel = new Vec2f(largeX - smallX, largeY - smallY);
		//Vec2f vel = new Vec2f(arg0.getX() - item.getPos().x, arg0.getY()- item.getPos().y);
		item.release();
	}
}
}
