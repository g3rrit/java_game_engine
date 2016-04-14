package com.pear.geometry;

import java.io.Serializable;

public class Triangle implements Serializable
{
	protected Point p0, p1, p2;
	
	private PointD pd0,pd1,pd2;
	
	public Triangle(Point p, Point p1, Point p2)
	{
		this.p0 = p.copy();
		this.p1 = p1.copy();
		this.p2 = p2.copy();
		
		this.pd0 = p.getPointD();
		this.pd1 = p1.getPointD();
		this.pd2 = p2.getPointD();
	}
	
	public void setPosition(int x, int y)
	{
		this.p0.x += x;
		this.p0.y += y;
		
		this.p1.x += x;
		this.p1.y += y;
		
		this.p2.x += x;
		this.p2.y += y;
		
		
		this.pd0.x += x;
		this.pd0.y += y;

		this.pd1.x += x;
		this.pd1.y += y;
		
		this.pd2.x += x;
		this.pd2.y += y;
	}
	
	private double sign(PointD p1, PointD p2, PointD p3)
	{
		return (p1.x -p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
	}
	
	public boolean contains(int x, int y)
	{
		boolean b1, b2, b3;
		
		PointD p = new PointD(x,y);
		
		b1 = sign(p,pd0,pd1) < 0.0d;
		b2 = sign(p,pd1,pd2) < 0.0d;
		b3 = sign(p,pd2,pd0) < 0.0d;
		
		return ((b1 == b2) && (b2 == b3));
	}
	
	public boolean contains(Rectangle r)
	{
		//optimize this!
		if(r.contains(p0) || r.contains(p1) || r.contains(p2))
		{
			return true;
		}
		else
		{
			Point pr0 = r.getP0();
			Point pr1 = r.getP1();
			Point pr2 = new Point(r.getP0().x,r.getP0().y + r.getHeight());
			Point pr3 = new Point(r.getP0().x + r.getWidth(), r.getP0().y);
			
			if(contains(pr0.x, pr0.y) || contains(pr1.x, pr1.y) || contains(pr2.x, pr2.y) || contains(pr3.x, pr3.y))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public Triangle copy()
	{
		return new Triangle(p0.copy(),p1.copy(), p2.copy());
	}

	public Point getP0()
	{
		return p0;
	}

	public void setP0(Point p0)
	{
		this.p0 = p0.copy();
		this.pd0 = p0.getPointD();
	}

	public Point getP1()
	{
		return p1;
	}

	public void setP1(Point p1)
	{
		this.p1 = p1.copy();
		this.pd1 = p1.getPointD();
	}

	public Point getP2()
	{
		return p2;
	}

	public void setP2(Point p2)
	{
		this.p2 = p2.copy();
		this.pd2 = p2.getPointD();
	}
}
