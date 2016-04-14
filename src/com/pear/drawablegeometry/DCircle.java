package com.pear.drawablegeometry;

import com.pear.core.Renderer;
import com.pear.core.fx.ShadowType;
import com.pear.fx.drawables.Drawable;
import com.pear.geometry.Circle;
import com.pear.geometry.Point;

public class DCircle extends Circle implements Drawable
{
	int color;
	
	public DCircle(Point p0, int radius, int color)
	{
		super(p0, radius);
		
		this.color = color;
	}

	@Override
	public void draw(Renderer r)
	{
		for(int x = 0; x < radius * 2; x++)
		{
			for(int y = 0; y < radius * 2; y++)
			{
				if(contains(x + p0.x - radius, y + p0.y - radius))
					r.setPixel(x + p0.x - radius, y + p0.y - radius, color, ShadowType.NONE);
			}
		}
	}

}
