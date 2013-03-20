package assignment.avans.nl;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class
Ball {

	private Vec2f pos, vel, acc;
	
	private int h, w;

	
	public Vec2f getPos() {
		return pos;
	}
	public void setPos(Vec2f pos) {
		this.pos = pos;
	}
	public Vec2f getVel() {
		return vel;
	}
	public void setVel(Vec2f vel) {
		this.vel = vel;
	}
	public Vec2f getAcc() {
		return acc;
	}
	public void setAcc(Vec2f acc) {
		this.acc = acc;
	}
	public Ball()
	{
		pos = new Vec2f();
		vel = new Vec2f(1, 1);
		acc = new Vec2f(0, 0);
	}
	
	public Ball(Vec2f pos, int h, int w)
	{
		this.pos = pos;
		this.h = h;
		this.w = w;
		vel = new Vec2f(1, 1);
		acc = new Vec2f(0, 0);
	}
	
	public void update(int x, int y)
	{
		if ((pos.x-(h/2) >= 0 && pos.x-(h/2) <= x-h) && (pos.y-(w/2) >= 0 && pos.y-(w/2) <= y-w))
		{
			pos.plusEquals(vel);
			vel.plusEquals(acc);
		}
		else if (!(pos.x-(h/2) >= 0 && pos.x-(h/2) <= x-h))
		{
			vel.x = -vel.x;
			pos.plusEquals(vel);
			vel.plusEquals(acc);
		}
		else if (!(pos.y-(w/2) >= 0 && pos.y-(w/2) <= y-w))
		{
			vel.y = -vel.y;
			pos.plusEquals(vel);
			vel.plusEquals(acc);
		}
		
	}
	public void draw(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.fill(new Ellipse2D.Double(pos.x-(h/2), pos.y-(w/2), h, w));
	}
	public void collide(ArrayList<Ball> balls)
	{
		for (Ball ball : balls)
		{
			if (!(ball.pos.equals(this.pos)))
			{
				Vec2f dis = this.pos.minus(ball.pos);
				if (dis.x <= (h/2) && dis.y <= (w/2))
				{
					System.out.println(dis.x + "x" + dis.y);
				}
			}
		}
	}
}
