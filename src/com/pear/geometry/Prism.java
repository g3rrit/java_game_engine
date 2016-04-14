package com.pear.geometry;

public class Prism
{
	protected Point p0, p1;
	
	public Prism(Point p0, Point p1)
	{
		this.p0 = p0.copy();
		this.p1 = p1.copy();
	}
	
	public Prism(Point p0,int width, int height)
	{
		this.p0 = p0.copy();
		this.p1 = new Point(p0.x - width/2, p0.y + height/2);
	}
}
