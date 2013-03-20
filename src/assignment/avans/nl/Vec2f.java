package assignment.avans.nl;

/**
 * 
 * @author Tektaara
 */
public class Vec2f
{
	public float x, y;

	public Vec2f()
	{
		x = y = 0.0f;
	}

	public Vec2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public Vec2f(Vec2f v)
	{
		this.x = v.x;
		this.y = v.y;
	}
	
	public void assign(Vec2f v)
	{
		this.x = v.x;
		this.y = v.y;
	}

	public Vec2f plus(Vec2f v)
	{
		return new Vec2f(this.x + v.x, this.y + v.y);
	}
	
	public void plusEquals(Vec2f v)
	{
		this.x += v.x;
		this.y += v.y;
	}

	public Vec2f minus(Vec2f v)
	{
		return new Vec2f(this.x - v.x, this.y - v.y);
	}
	
	public void minusEquals(Vec2f v)
	{
		this.x -= v.x;
		this.y -= v.y;
	}

	public Vec2f mul(Vec2f v)
	{
		return new Vec2f(this.x * v.x, this.y * v.y);
	}
	
	public void mulEquals(Vec2f v)
	{
		this.x *= v.x;
		this.y *= v.y;
	}

	public Vec2f div(Vec2f v)
	{
		return new Vec2f(this.x / v.x, this.y / v.y);
	}
	
	public void divEquals(Vec2f v)
	{
		this.x /= v.x;
		this.y /= v.y;
	}

	public Vec2f rcp()
	{
		return new Vec2f(1.0f / this.x, 1.0f / this.y);
	}

	public float dot(Vec2f v)
	{
		return (this.x * v.x + this.y * v.y);
	}

	public float length()
	{
		return (float) Math.sqrt(dot(this));
	}

	public float lengthSquared()
	{
		return dot(this);
	}
	
	public Vec2f scale(float s)
	{
		return new Vec2f(this.x*s, this.y*s);
	}

	public void scaleEquals(float s)
	{
		this.x *= s;
		this.y *= s;
	}
	
	public Vec2f scalercp(float s)
	{
		return new Vec2f(this.x/s, this.y/s);
	}

	public void scalercpEquals(float s)
	{
		this.x /= s;
		this.y /= s;
	}

	public void normalise()
	{
		scalercpEquals(length());
	}

	public Vec2f normalisedCopy()
	{
		Vec2f v = new Vec2f(this);
		v.scalercpEquals(this.length());
		return v;
	}

}