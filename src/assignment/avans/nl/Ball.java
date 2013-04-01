package assignment.avans.nl;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Ball extends PhysShape {

	private int r;
	public Ball(Vec2f pos, Vec2f vel, int h, int w)
	{
		super(pos, vel, h, w);
		r = h/2;
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
	
	public int getRadius() {
		return r;
	}
	public void update(LevelCage cage, boolean grav, boolean b)
	{
		this.grav = grav;
		if (!held)
		{
			if (this.grav)
			{
				gravCalc();
			}
			pos.plusEquals(vel);
			//acc.divideBy(2);
			vel.plusEquals(acc);
			
			
		}
//		else
//			held = false;
		int state = cage.collideWithBall(this);
		switch (state)
		{
			case 0:	break;
			
			case 1: if (!b)
						vel.x = (float) (-vel.x/1.5);
					else
						vel.x = -vel.x;
					
//					System.out.println("x changed");
					break;
			
			case 2: if (!b)
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
	/*public void collide(ArrayList<Ball> balls)
	{
		
	}*/
	@Override
	public void collide(ArrayList<PhysShape> shapes) {
		for(int i = 0; i < shapes.size(); i++)
		{
			for (PhysShape shape : shapes)
			{
				//Ball ball = balls.get(i);
				if (shapes.indexOf(shape) >= i && !this.equals(shape))
				{
					if (shape.getType() == 1)
					{
						Vec2f d = shape.getPos().minus(this.pos);
						
						if(d.lengthSquared() <= ((this.r + shape.getRadius())*(this.r + shape.getRadius())))
						{
							
							//this.vel = new Vec2f(-this.vel.x, -this.vel.y);
							//ball.vel = new Vec2f(-ball.vel.x, -ball.vel.y);
							
							
							float xt = shape.getVel().x;
							float yt = shape.getVel().y;
							shape.getVel().x = -(shape.getVel().x - this.vel.x)/2;
							shape.getVel().y = -(shape.getVel().y - this.vel.y)/2;
							this.vel.x = -(this.vel.x - xt)/2;
							this.vel.y = -(this.vel.y - yt)/2;
	//						float x2 =  ball.pos.x;
	//						ball.pos.x = this.pos.x +((ball.pos.x > this.pos.x)?(ball.r):-(ball.r));//(x2-this.pos.x) + ((ball.pos.x > 0)?-r:+r);
	//						this.pos.x = x2 +((this.pos.x > ball.pos.x)?(this.r):-(this.r));//(this.pos.x-x2) + ((this.pos.x > 0)?-r:+r);
							this.pos.plus(this.vel.negate());
							shape.getPos().plus(shape.getVel().negate());
							
							/*if (d.lengthSquared() < ((this.r + shape.getRadius())*(this.r + shape.getRadius()))){
								float y2 =  shape.getPos().y;
								shape.getPos().y = this.pos.y +((shape.getPos().y > this.pos.y)?(shape.getRadius()):-(shape.getRadius()));//(y2-this.pos.y) + ((ball.pos.y > 0)?-r:+r)
								this.pos.y = y2 +((this.pos.y > shape.getPos().y)?(this.r):-(this.r));//(this.pos.y-y2) + ((this.pos.y > 0)?-r:+r);
							}*/
							
							
							//new version
							
							//Vec2f ld = this.getVel();
							//ball.vel.minusEquals(this.vel);
							//this.vel.minusEquals(ld);
							
						}
					}
				}
			}
		}
		
	}

	@Override
	public int getType() {
		return 1;
	}

	@Override
	public void calcMass() {
		r = h/2;
		
	}
}
