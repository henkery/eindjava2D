package assignment.avans.nl;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class PhysShape {

	protected Vec2f pos, vel, acc;
	
	protected boolean held;
	protected int h, w, mass;

	protected Color color;

	protected boolean grav;

	

	public Vec2f getAcc() {
		return acc;
	}

	public Vec2f getPos() {
		return pos;
	}

	public Vec2f getVel() {
		return vel;
	}

	public void setAcc(Vec2f acc) {
		this.acc =acc;
		
	}

	public void setPos(Vec2f pos) {
		this.pos = pos;
		
	}

	public void setVel(Vec2f vel) {
		this.vel = vel;
		
	}
	public Color randomColor()
	{
	return new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
	}
	
	
	public PhysShape(Vec2f pos, Vec2f vel, int h, int w)
	{
		this.pos = pos;
		this.h = h;
		this.w = w;
		this.vel = vel;
		color = randomColor();
		held = false;
		mass = 1;
		acc = new Vec2f(0, 0);
	}
	
	public abstract boolean isClicked(int x, int y);
	
	public void gravCalc()
	{
		acc.y = vel.y + (int)((mass*9.81)/5);
	}
	public abstract void update(LevelCage cage, boolean grav, boolean b);
	public abstract int getType();
	public abstract void draw(Graphics g);
	public abstract void collide(ArrayList<PhysShape> shapes);


	public void release() {
		held = false;
	}

	public abstract int getRadius();

	public boolean isHeld() {
		// TODO Auto-generated method stub
		return held;
	}

	public abstract void calcMass();
}
