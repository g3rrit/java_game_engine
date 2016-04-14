package com.pear.geometry;

public class Line
{
	protected Point p0;
	protected Point p1;
	
	public Line(Point p0, Point p1)
	{
		this.p0 = p0.copy();
		this.p1 = p1.copy();
	}
	
	public void setP0(Point p)
	{
		this.p0 = p.copy();
	}
	
	public void setP1(Point p)
	{
		this.p1 = p;
	}
	
	public double getLength()
	{
		return  Math.sqrt((p1.x - p0.x) * (p1.x - p0.x) + (p1.y - p0.y) * (p1.y - p0.y));
	}
	
	public float getSlope()
	{
		return (p1.y - p0.y)/(p1.x - p0.x);
	}
}
