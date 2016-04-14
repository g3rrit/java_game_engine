package com.pear.fx.drawables;

import java.io.Serializable;

import com.pear.core.Renderer;
import com.pear.core.fx.ShadowType;
import com.pear.geometry.Rectangle;

public class Sprite implements Drawable, Serializable
{
	protected int width, height;
	protected int xpos, ypos;
	private ShadowType shadowType;
	protected Image image;
	
	protected Rectangle rect;
	
	boolean invertedx = false;
	boolean invertedy = false;
	
	public Sprite()
	{
		//init(0,0,shadowType.NONE);
	}
	
	public Sprite(String path, int x, int y, ShadowType shadowType)
	{
		image = new Image(path);
		
		
		init(x,y,shadowType);
	}
	
	public Sprite(String path, int scanx, int scany, int scanw, int scanh, int x, int y, ShadowType shadowType)
	{
		image = new Image(path, scanx, scany, scanw, scanh);
		
		init(x,y,shadowType);
	}
	
	protected void init(int x, int y, ShadowType shadowType)
	{
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		this.xpos = x;
		this.ypos = y;
		
		this.rect = new Rectangle(x,y, x + width, y + height);
		
		this.shadowType = shadowType;
	}

	@Override
	public void draw(Renderer r)
	{
		for(int x = 0; x < this.width; x++)
		{
			for(int y = 0; y < this.height; y++)
			{
				r.setPixel(x + xpos, y + ypos, image.pixels[x + y * width], shadowType);
			}
		}
	}
	
	public void invertX()
	{
		this.image.invertX();
	}
	
	public void invertX(boolean isInverted)
	{
		this.image.invertX(isInverted);
	}
	
	public void invertY()
	{
		this.image.invertY();
	}
	
	public void invertY(boolean isInverted)
	{
		this.image.invertY(isInverted);
	}
	
	
	public void setPosition(int x, int y)
	{
		xpos = x;
		ypos = y;
		
		this.rect.moveTo(x,y);
	}
	
	public void setSize(int width, int height)
	{
		image.setSize(width, height);
		
		this.height = height;
		this.width = width;
		
		this.rect.setWidth(width);
		this.rect.setHeight(height);
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{		
		image.setWidth(width);
		
		this.width = width;
		this.rect.setWidth(width);
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		image.setHeight(height);
		
		this.height = height;
		this.rect.setHeight(height);
	}

	public int getXpos()
	{
		return xpos;
	}

	public void setXpos(int xpos)
	{
		this.xpos = xpos;
		
		this.rect.moveTo(xpos, ypos);
	}

	public int getYpos()
	{
		return ypos;
	}

	public void setYpos(int ypos)
	{
		this.ypos = ypos;
		
		this.rect.moveTo(xpos, ypos);
	}

	public ShadowType getShadowType()
	{
		return shadowType;
	}

	public void setShadowType(ShadowType shadowType)
	{
		this.shadowType = shadowType;
	}

	public Rectangle getRect()
	{
		return rect;
	}
	
}
