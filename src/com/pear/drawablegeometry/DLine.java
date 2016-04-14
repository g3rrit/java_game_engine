package com.pear.drawablegeometry;

import com.pear.core.Renderer;
import com.pear.core.fx.ShadowType;
import com.pear.fx.drawables.Drawable;
import com.pear.geometry.Line;
import com.pear.geometry.Point;

public class DLine extends Line implements Drawable
{
	private int color;
	
	private int width;
	
	//just for testing now!
	private DTriangle tri1;
	private DTriangle tri2;
	
	public DLine(Point p0, Point p1, int width, int color)
	{
		super(p0, p1);
		
		this.width = width;
		
		this.tri1 = new DTriangle(p0,p1,new Point(p0.x - width, p0.y + width), color);
		this.tri2 = new DTriangle(p0,p1,new Point(p1.x - width, p1.y + width), color);
		
		this.color = color;
	}

	@Override
	public void draw(Renderer r)
	{		
		r.draw(tri1);
	}
	
	@Override
	public void setP0(Point p)
	{
		super.setP0(p);
		
		tri1.setP0(p);
		tri1.setP2(new Point(p.x - width, p.y + width));
		
		tri2.setP0(p);
	}
	
	@Override
	public void setP1(Point p)
	{
		super.setP1(p);
		

		tri1.setP1(p);
		
		tri2.setP1(p);
		tri2.setP2(new Point(p.x - width, p.y + width));
	}
}
