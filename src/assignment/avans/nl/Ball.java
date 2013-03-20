package assignment.avans.nl;

import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ball extends Ellipse2D{
	
	//private Ellipse2D ball;

	public Ball(Double x,Double y, Double h, Double w)
	{
		
	}
	
	public void detectEdge(Point move1, Ellipse2D circle1)
	{
		if (move1.getX() < 0)
		{
			
			if (circle1.getMinX() + move1.getX() > 0)//this.getHeight())
			{
				circle1 = new Ellipse2D.Double(circle1.getX() + move1.getX(), circle1.getY(), 100, 100);
				
			}
			else
			{
				move1.setLocation(-move1.getX(), move1.getY());
				System.out.println("changing move1 X 1");
			}
		}
		else
		{
			if (circle1.getMaxX() + move1.getX() < 0)//this.getHeight())
			{
				circle1 = new Ellipse2D.Double(circle1.getX() + move1.getX(), circle1.getY(), 100, 100);
			}
			else
			{
				move1.setLocation(-move1.getX(), move1.getY());
				System.out.println("changing move1 X 2");
			}
		}
		if (move1.getY() < 0)
		{
			//System.out.println("nn");
			if (circle1.getMaxY() + move1.getY() > 0)//this.getWidth())
			{
				circle1 = new Ellipse2D.Double(circle1.getX(), circle1.getY() + move1.getY(), 100, 100);
			}
			else
			{
				move1.setLocation(move1.getX(), -move1.getY());
				System.out.println("changing move1 X 3");
			}
		}
		else
		{
			if (circle1.getMinY() + move1.getX() < 0)//this.getWidth())
			{
				circle1 = new Ellipse2D.Double(circle1.getX(), circle1.getY() + move1.getY(), 100, 100);
			}
			else
			{
				move1.setLocation(move1.getX(), -move1.getY());
				System.out.println("changing move1 X 4");
			}
		}
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFrame(double arg0, double arg1, double arg2, double arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle2D getBounds2D() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
