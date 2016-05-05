package com.pear.geometry;

import java.io.Serializable;

public class Circle implements Serializable
{
	protected Point p0;
	protected int radius;
	
	public Circle(Point p0, int radius)
	{
		this.p0 = p0.copy();
		
		this.radius = radius;
	}

	public boolean contains(int x, int y)
	{
		return getDistance(new Point(x,y)) <= radius;
	}
	
	public double getDistance(Point p)
	{
		return Math.sqrt((p.x - p0.x) * (p.x - p0.x) + (p.y - p0.y) * (p.y - p0.y));
	}
	
	public Point getP0()
	{
		return p0;
	}

	public void setP0(Point p0)
	{
		this.p0 = p0;
	}

	public int getRadius()
	{
		return radius;
	}

	public void setRadius(int radius)
	{
		this.radius = radius;
	}
	
	
}
