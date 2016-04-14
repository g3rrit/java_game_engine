package com.pear.geometry;

import java.io.Serializable;

public class PointD implements Serializable
{
	public double x, y;
	
	public PointD(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public PointD copy()
	{
		return new PointD(x,y);
	}
}
