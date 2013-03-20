package assignment.avans.nl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class
Ball {

	private Vec2f pos, vel, acc;
	
	private int radiusSquared;
	
	private int h, w,r;

	private Color color;

	
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
	
	public Color randomColor()
	{
	return new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
	}
	
	public Ball(Vec2f pos, Vec2f vel, int h, int w)
	{
		this.pos = pos;
		this.h = h;
		this.w = w;
		this.vel = vel;
		acc = new Vec2f(0, 0);
		r = h/2;
		radiusSquared = (int) (r*r);
		color = randomColor();
	}
	
	public void update(int x, int y)
	{
		pos.plusEquals(vel);
		vel.plusEquals(acc);
		
		
		/*if ((pos.x-(h/2) >= 0 && pos.x-(h/2) <= x-h) && (pos.y-(w/2) >= 0 && pos.y-(w/2) <= y-w))
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
		*/
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(color);
		g2.fill(new Ellipse2D.Double(pos.x-(h/2), pos.y-(w/2), h, w));
	}
	public void collide(ArrayList<Ball> balls)
	{
		for(int i = 0; i < balls.size(); i++)
		{
			for (Ball ball : balls)
			{
				//Ball ball = balls.get(i);
				if (balls.indexOf(ball) > i && !this.equals(ball))
				{
					Vec2f d = ball.pos.minus(this.pos);
					
					if(d.lengthSquared() <= ((this.r + ball.r)*(this.r + ball.r)))
					{
						//this.vel = new Vec2f(-this.vel.x, -this.vel.y);
						//ball.vel = new Vec2f(-ball.vel.x, -ball.vel.y);
						this.vel.x = -this.vel.x;
						this.vel.y = -this.vel.y;
						ball.vel.x = -ball.vel.x;
						ball.vel.y = -ball.vel.y;
					}
				}
			}
		}
	}
}
