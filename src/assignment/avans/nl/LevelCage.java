package assignment.avans.nl;

import java.awt.Rectangle;

public class LevelCage {

	private Rectangle cage;
	
	public LevelCage(int x, int y, int h, int w)
	{
		cage = new Rectangle(x, y, w, h);
	}
	
	public int collideWithBall(Ball ball)
	{
		int hardness = 5;
		if (ball.getPos().x < 0+ball.getRadius())
		{
			ball.setPos(new Vec2f(0+ball.getRadius(), ball.getPos().y));
//			System.out.println("ball x pos is " + ball.getPos().x + " and cage.width is " + cage.width);
			return 1;
		}
		else if (ball.getPos().x > cage.width-ball.getRadius())
		{
			ball.setPos(new Vec2f(cage.width-ball.getRadius(), ball.getPos().y));
			return 1;
		}
		else if (ball.getPos().y < 0+ball.getRadius())
		{
			ball.setPos(new Vec2f(ball.getPos().x, 0+ball.getRadius()));//ball.getRadius()));
			return 2;
		}
		else if (ball.getPos().y > cage.height-ball.getRadius())
		{
			ball.setPos(new Vec2f(ball.getPos().x,cage.height-ball.getRadius()));//ball.getRadius()));
			return 2;
		}
		else
			return 0;
	}
	public int collideWithBlock(Block block)
	{
		int hardness = 5;
		if (block.getPos().x < 0+(block.h/2))
		{
			block.setPos(new Vec2f(0+(block.h/2), block.getPos().y));
//			System.out.println("block x pos is " + block.getPos().x + " and cage.width is " + cage.width);
			return 1;
		}
		else if (block.getPos().x > cage.width-(block.h/2))
		{
			block.setPos(new Vec2f(cage.width-(block.h/2), block.getPos().y));
			return 1;
		}
		else if (block.getPos().y < 0+(block.h/2))
		{
			block.setPos(new Vec2f(block.getPos().x, 0+(block.h/2)));//block.getRadius()));
			return 2;
		}
		else if (block.getPos().y > cage.height-(block.h/2))
		{
			block.setPos(new Vec2f(block.getPos().x,cage.height-(block.h/2)));//block.getRadius()));
			return 2;
		}
		else
			return 0;
	}
	
	public void resizeCage(int h, int w)
	{
		cage.height = h;
		cage.width = w;
	}
}
