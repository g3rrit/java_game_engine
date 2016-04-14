package com.pear.drawablegeometry;

import com.pear.core.Renderer;
import com.pear.fx.drawables.Drawable;
import com.pear.geometry.Point;
import com.pear.geometry.Prism;

public class DPrism extends Prism implements Drawable
{
	private int color;
	
	private DRightTriangle tri0;
	private DRightTriangle tri1;
	private DRightTriangle tri2;
	private DRightTriangle tri3;
	
	public DPrism(Point p0, Point p1, int color)
	{
		super(p0,p1);
		this.color = color;
		init();
	}
	
	public DPrism(Point p0, int width, int height)
	{
		super(p0, width, height);
		this.color = color;
		init();
	}
	
	private void init()
	{
		this.tri0 = new DRightTriangle(new Point(p0.x, p1.y), p0.x - p1.x , p1.y - p0.y, this.color);
		this.tri1 = new DRightTriangle(new Point(p0.x, p1.y), p1.x - p0.x , p1.y - p0.y, this.color);
		this.tri2 = new DRightTriangle(new Point(p0.x, p1.y), p0.x - p1.x , p0.y - p1.y, this.color);
		this.tri3 = new DRightTriangle(new Point(p0.x, p1.y), p1.x - p0.x , p0.y - p1.y, this.color);
	}

	@Override
	public void draw(Renderer r)
	{
		r.draw(tri0);
		r.draw(tri1);
		r.draw(tri2);
		r.draw(tri3);
	}

}
