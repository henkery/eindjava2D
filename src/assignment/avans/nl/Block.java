package assignment.avans.nl;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Block extends PhysShape {

	private Rectangle block;

	public Block(Vec2f pos, Vec2f vel, int h, int w) {
		super(pos, vel, h, w);
		block = new Rectangle((int)pos.x-(h/2), (int)pos.y-(h/2), h*2, w*2);

	}

	@Override
	public void collide(ArrayList<PhysShape> shapes) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(color);
		g2.fill(new Rectangle2D.Double(pos.x-(h/2), pos.y-(w/2), h, w));

	}

	@Override
	public int getType() {
		// I can't into enum
		return 2;
	}

	@Override
	public boolean isClicked(int x, int y) {
		boolean dis = ((pos.x-(h/2) < x) && (pos.x+(h/2) > x) && (pos.y-(h/2) < y) && (pos.y+(h/2) > y));
		held = dis;
		//turn true;
		return (dis);
		//return ((block.getMaxX() > x) && (block.getMinX() < x)) && ((block.getMinY() > y) && block.getMaxY() < y);
//		System.out.println("Mouse at " + x + "x" + y);
//		System.out.println("Block at " + pos.x + "x" + pos.y);
//		System.out.println((pos.x+h) + "x" + (pos.y+h) + " and" + (pos.x-h) + "x" + (pos.y-h));
//		return();
		//return block.contains(x, y);
		//return false;
	}

	@Override
	public int getRadius() {
		// squares don't into radius
		return 0;
	}

	@Override
	public void update(LevelCage cage, boolean grav, boolean b) {
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
		
		int state = cage.collideWithBlock(this);
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

}
