package com.pear.geometry;

import java.io.Serializable;

public class Point implements Serializable
{
	public int x, y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point copy()
	{
		return new Point(x,y);
	}
	
	public PointD getPointD()
	{
		return new PointD(x,y);
	}
	
	/*
	Function<Point, PointD> convertToPointD = new Function<Point, PointD>()
	{
		@Override
		public PointD apply(Point t)
		{
			return new PointD((double)t.x,(double)t.y);
		}		
		
	};*/
	
	
}
