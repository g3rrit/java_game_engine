package com.pear.core.fx;

public class Light
{
	public int[] lm;
	private int color, radius, diameter;
	private int xpos,ypos;
	
	public Light(int color, int radius, int xpos, int ypos)
	{
		this.color = color;
		this.radius = radius;
		this.diameter = radius * 2;
		
		this.xpos = xpos;
		this.ypos = ypos;
		
		lm = new int[diameter * diameter];
		
		for(int x = 0; x < diameter; x++)
		{
			for(int y = 0; y < diameter; y++)
			{
				float distance = (float)Math.sqrt((x - radius) * (x - radius) + (y - radius) * (y - radius));
				
				if(distance < radius)
				{
					lm[x + y * diameter] = Pixel.getColorPower(color, 1 - distance / radius);
				}
				else
				{
					lm[x + y * diameter] = 0;
				}
			}
		}
	}
	
	public int getLightValue(int x, int y)
	{
		if(x < 0 || x >= diameter || y < 0 || y >= diameter) return 0xff000000;
		
		return lm[x + y *diameter];
	}

	public void setPosition(int x, int y)
	{
		this.xpos = x;
		this.ypos = y;
	}
	
	public int getColor()
	{
		return color;
	}

	public void setColor(int color)
	{
		this.color = color;
	}

	public int getRadius()
	{
		return radius;
	}

	public void setRadius(int radius)
	{
		this.radius = radius;
		this.diameter = 2 * radius;
	}

	public int getXpos()
	{
		return xpos;
	}

	public void setXpos(int xpos)
	{
		this.xpos = xpos;
	}

	public int getYpos()
	{
		return ypos;
	}

	public void setYpos(int ypos)
	{
		this.ypos = ypos;
	}

	public int getDiameter()
	{
		return diameter;
	}
}
