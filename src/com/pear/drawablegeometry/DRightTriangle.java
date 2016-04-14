package com.pear.drawablegeometry;

import com.pear.core.Renderer;
import com.pear.core.fx.ShadowType;
import com.pear.fx.drawables.Drawable;
import com.pear.geometry.Point;
import com.pear.geometry.RightTriangle;

public class DRightTriangle extends RightTriangle implements Drawable
{
	private int color;
	
	private float slope;
	
	private boolean invertedX, invertedY;
	
	private int absWidth, absHeight;
	
	public DRightTriangle(Point p0, int width, int height, int color)
	{
		super(p0, width, height);
		
		if(width >= 0)
		{
			absWidth = width;
			invertedX = false;
		}
		else
		{
			absWidth = -width;
			invertedX = true;
		}
		
		if(height >= 0)
		{
			absHeight = height;
			invertedY = false;
		}
		else
		{
			absHeight = -height;
			invertedY = true;
		}
		
		
		this.color = color;
		this.slope = (float)this.absHeight/this.absWidth;
	}

	@Override
	public void draw(Renderer r)
	{
		
		for(int y = 0; y < absHeight; y++)
		{
			for(int x = 0; x < absWidth; x++)
			{
				if(x > 0 && y > 0)
				{
					if(y / ((float)absWidth - x) >= slope) break;
				}
				if(!invertedY)
				{
					if(!invertedX)
					{
						r.setPixel(x + p0.x, y + p0.y, color, ShadowType.NONE);
					}
					else
					{
						r.setPixel(p0.x - x,p0.y + y, color, ShadowType.NONE);
					}
				}
				else
				{
					if(!invertedX)
					{
						r.setPixel(x + p0.x, p0.y - y, color, ShadowType.NONE);
					}
					else
					{
						r.setPixel(p0.x - x,p0.y - y, color, ShadowType.NONE);
					}
				}
			}
		}
	}
}
