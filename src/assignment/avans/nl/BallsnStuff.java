package assignment.avans.nl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BallsnStuff extends JFrame {

	private static boolean grav;
	private static boolean wallA;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		BallsnStuff c =new BallsnStuff();
		

	}
	
	public BallsnStuff()
	{
		
		super("muh collisions");
		grav = false;
		wallA = true;
		
		JPanel mainPanel = new DragAShapePanel();
		mainPanel.setBackground(Color.black);
		this.setSize(800, 600);
		this.setContentPane(mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JButton gravBut = new JButton("toggle gravity");
		gravBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                grav = !grav;
            }
        });
		JButton wallBut = new JButton("toggle walls");
		wallBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wallA = !wallA;
            }
        });
		//wallBut.setSize(wallBut.getWidth(), wallBut.getHeight()+1);
		mainPanel.add(wallBut);
		mainPanel.add(gravBut);
		mainPanel.revalidate();
		
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

	public static boolean getGrav() {
		// TODO Auto-generated method stub
		return grav;
	}

	public static boolean getWallA() {
		// TODO Auto-generated method stub
		return wallA;
	}

}

class DragAShapePanel extends JPanel 
implements MouseListener, MouseMotionListener, MouseWheelListener 
{

private int teller = 0;

private int x = 100;
private int y = 100;

//private Ball ball1,ball2;
private ArrayList<PhysShape> shapes;

boolean isSelected = false;

private boolean knal;

private LevelCage cage;

private boolean grav;

private int typeShape;



/* Constructor */
public DragAShapePanel()
{
	setPreferredSize( new Dimension(640,480));
	
	addMouseListener(this);
	addMouseMotionListener(this);
	addMouseWheelListener(this);
	cage = new LevelCage(0, 0, this.getWidth(), this.getHeight());
	shapes = new ArrayList<PhysShape>();
	//balls.add(new Ball(new Vec2f(100, 100), new Vec2f(0, 0), 100, 100));
	//balls.add(new Ball(new Vec2f(200, 100), new Vec2f(1, 2), 100, 100));
	//balls.add(new Ball(new Vec2f(300, 100), new Vec2f(0, 1), 100, 100));
	//balls.add(new Ball(new Vec2f(400, 100), new Vec2f(0, 0), 100, 100));
	//balls.add(new Ball(new Vec2f(200, 100), 100, 100));
	//balls.add(new Ball(new Vec2f(600, 100), new Vec2f(-1, 0), 100, 100));
	grav = true;
	typeShape = 1;
}

// Repaint
public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g;
	g2.setColor(Color.yellow);
	g2.setBackground(Color.black);
	
	g2.drawString("Gravity is " + (BallsnStuff.getGrav()?"on":"off"), 10, 10);
	g2.drawString("Walls do" + (BallsnStuff.getWallA()?"n't ":" ") + "absorb energy", 10, 25);
	g2.drawString("Selected type shape is " + typeShape, 10, 40);
	
	
	//ball1.update(this.getWidth(), this.getHeight());
	//ball1.draw(g);
	
	cage.resizeCage(this.getHeight(), this.getWidth());
	for (PhysShape shape : shapes)
	{
		shape.update(cage, BallsnStuff.getGrav(), BallsnStuff.getWallA()); 
		shape.collide(shapes);
		
		
		shape.draw(g);
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
	for (PhysShape item : shapes)
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
	if (arg0.getButton() == MouseEvent.BUTTON3)	
	{
		switch (typeShape){
			case 1:
				shapes.add(new Ball(new Vec2f(arg0.getX(), arg0.getY()), new Vec2f(), 50, 50));
				break;
			case 2:
				shapes.add(new Block(new Vec2f(arg0.getX(), arg0.getY()), new Vec2f(), 50, 50));
				break;
			}
	}
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
	for (PhysShape item : shapes)
	{
//		Vec2f vel = new Vec2f(largeX - smallX, largeY - smallY);
		//Vec2f vel = new Vec2f(arg0.getX() - item.getPos().x, arg0.getY()- item.getPos().y);
		item.release();
	}
}

@Override
public void mouseWheelMoved(MouseWheelEvent e) {
	typeShape += e.getWheelRotation();
	if (typeShape < 1)
		typeShape = 2;
	else if (typeShape > 2)
		typeShape = 1;
	
}
}
