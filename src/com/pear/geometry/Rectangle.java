package com.pear.geometry;

import java.io.Serializable;

public class Rectangle implements Serializable
{
	private Point p0, p1;
	private int width, height;
	
	public Rectangle(Point p0, Point p1)
	{
		this.p0 = p0.copy();
		this.p1 = p1.copy();
		
		this.width = p1.x - p0.x;
		this.height = p1.y - p0.y;
	}
	
	public Rectangle(int x0, int y0, int x1, int y1)
	{
		this.p0 = new Point(x0,y0);
		this.p1 = new Point(x1,y1);
		
		this.width = p1.x - p0.x;
		this.height = p1.y - p0.y;
	}
	
	public Rectangle(Point p0, int width, int height)
	{
		this.p0 = p0.copy();
		this.p1 = new Point(p0.x + width,p0.y + height);
		
		this.width = width;
		this.height = height;
	}

	public boolean contains(int x, int y)
	{
		if(x > p0.x && x < p1.x && y > p0.y && y < p1.y)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean contains(Point p)
	{
		if(p.x > p0.x && p.x < p1.x && p.y > p0.y && p.y < p1.y)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean contains(Rectangle r)
	{
		if(this.p0.x < r.p1.x && this.p1.x > r.p0.x && this.p0.y < r.p1.y && this.p1.y > r.p0.y)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean contains(Triangle t)
	{
		return t.contains(this);
	}
	
	public void moveTo(Point p)
	{
		this.p0.x = p.x;
		this.p0.y = p.y;
		this.p1.x = p.x + this.width;
		this.p1.y = p.y + this.height;
	}
	
	public void moveTo(int x, int y)
	{
		this.p0.x = x;
		this.p0.y = y;
		this.p1.x = x + this.width;
		this.p1.y = y + this.height;
	}
	
	public Point getP0()
	{
		return p0;
	}

	public void setP0(Point p0)
	{
		this.p0 = p0.copy();
		this.width = p1.x - p0.x;
		this.height = p1.y - p0.y;
	}

	public Point getP1()
	{
		return p1;
	}

	public void setP1(Point p1)
	{
		this.p1 = p1.copy();
		this.width = p1.x - p0.x;
		this.height = p1.y - p0.y;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
		this.p1.x = p0.x + width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
		this.p1.y = p0.y + height;
	}
	
	public Rectangle copy()
	{
		return new Rectangle(p0.copy(), width, height);
	}
	
}
