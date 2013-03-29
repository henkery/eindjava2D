package assignment.avans.nl;

import java.awt.Rectangle;

public class Ballcage {

	private Rectangle cage;
	
	public Ballcage(int x, int y, int h, int w)
	{
		cage = new Rectangle(x, y, w, h);
	}
	
	public int collideWithBall(Ball ball)
	{
		if (ball.getPos().x < 0+ball.getRadius() || ball.getPos().x > cage.width-ball.getRadius())
		{
			System.out.println("ball x pos is " + ball.getPos().x + " and cage.width is " + cage.width);
			return 1;
		}
		else if (ball.getPos().y < 0+ball.getRadius() || ball.getPos().y > cage.height-ball.getRadius())
			return 2;
		else
			return 0;
	}
	
	public void resizeCage(int h, int w)
	{
		cage.height = h;
		cage.width = w;
	}
}
