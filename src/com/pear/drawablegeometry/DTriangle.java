package com.pear.drawablegeometry;

import com.pear.core.Renderer;
import com.pear.core.fx.ShadowType;
import com.pear.fx.drawables.Drawable;
import com.pear.geometry.Point;
import com.pear.geometry.Triangle;

public class DTriangle extends Triangle implements Drawable
{

	private int color;
	
	private Point p;
	private int width, height;
	
	public DTriangle(Point p, Point p1, Point p2, int color)
	{
		super(p, p1, p2);
		
		this.color = color;
		
		int xl;
		int yl;
		
		int yb;
		int xr;
		
		//rework this
		if(p.x < p1.x && p.x < p2.x)
		{
			xl = p.x;
		}
		else if(p1.x < p.x && p1.x < p2.x)
		{
			xl = p1.x;
		}
		else
		{
			xl = p2.x;
		}
		
		if(p.y < p1.y && p.y < p2.y)
		{
			yl = p.y;
		}
		else if(p1.y < p.y && p1.y < p2.y)
		{
			yl = p1.y;
		}
		else
		{
			yl = p2.y;
		}
		
		if(p.x > p1.x && p.x > p2.x)
		{
			xr = p.x;
		}
		else if(p1.x > p.x && p1.x > p2.x)
		{
			xr = p1.x;
		}
		else
		{
			xr = p2.x;
		}
		
		if(p.y > p1.y && p.y > p2.y)
		{
			yb = p.y;
		}
		else if(p1.y > p.y && p1.y > p2.y)
		{
			yb = p1.y;
		}
		else
		{
			yb = p2.y;
		}
		
		this.p = new Point(xl, yl);
		this.width = xr - xl;
		this.height = yb - yl;
	}

	@Override
	public void draw(Renderer r)
	{
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				if(contains(x + p.x, y + p.y))
				{
					r.setPixel(x + p.x, y + p.y, color, ShadowType.NONE);
				}
			}
		}
	}

}
