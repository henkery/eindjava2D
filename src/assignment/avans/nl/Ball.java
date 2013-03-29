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
	
	private boolean held;
	private int h, w,r, mass;

	private Color color;

	private boolean grav;

	
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
		held = false;
		mass = 1;
	}
	
	public boolean isClicked(int x, int y)
	{
		//System.out.println("this x is " + getPos().x + " it should be " + x);
//		System.out.println("x match is " + ((getPos().x + r >= x) && (getPos().x -r <= x)));
//		System.out.println("y match is " + ((getPos().y + r >= y) && (getPos().y -r <= y)) + "\n\n");
		boolean dis = ((getPos().x + r >= x) && (getPos().x -r <= x)) && ((getPos().y + r >= y) && (getPos().y -r <= y));
		held = dis;
		return (dis);
	}
	
	public void gravCalc()
	{
		vel.y = vel.y + (int)((mass*9.81)/5);
	}
	public void update(Ballcage cage, boolean grav)
	{
		this.grav = grav;
		if (!held)
		{
			if (this.grav)
			{
				gravCalc();
			}
			pos.plusEquals(vel);
			vel.plusEquals(acc);
			
			
		}
//		else
//			held = false;
		int state = cage.collideWithBall(this);
		switch (state)
		{
			case 0:	break;
			
			case 1: if (grav)
						vel.x = (float) (-vel.x/1.5);
					else
						vel.x = -vel.x;
					
//					System.out.println("x changed");
					break;
			
			case 2: if (grav)
						vel.y =  (float) (-vel.y/1.5);
					else
						vel.y =  -vel.y;
//					System.out.println("y changed");
					
					break;
		}
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
				if (balls.indexOf(ball) >= i && !this.equals(ball))
				{
					Vec2f d = ball.pos.minus(this.pos);
					
					if(d.lengthSquared() <= ((this.r + ball.r)*(this.r + ball.r)))
					{
						//this.vel = new Vec2f(-this.vel.x, -this.vel.y);
						//ball.vel = new Vec2f(-ball.vel.x, -ball.vel.y);
						float xt = ball.vel.x;
						float yt = ball.vel.y;
						ball.vel.x = this.vel.x;
						ball.vel.y = this.vel.y;
						this.vel.x = xt;
						this.vel.y = yt;
						
					}
				}
			}
		}
	}
	public int getRadius() {
		return r;
	}
	public void release() {
		held = false;
	}
}
