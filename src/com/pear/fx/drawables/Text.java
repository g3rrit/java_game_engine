package com.pear.fx.drawables;

import java.io.Serializable;

import com.pear.core.Renderer;
import com.pear.core.fx.Font;
import com.pear.core.fx.ShadowType;

public class Text implements Drawable, Serializable
{
	public static Font font = Font.STANDART;
	
	private int xpos,ypos;
	private int width, height;
	private int scalex = 1;
	private int scaley = 1;
	private int color;
	
	private String text;
	private ShadowType shadowType;
	
	public Text(String text, int x, int y, ShadowType shadowType)
	{
		this.text = text;
		this.xpos = x;
		this.ypos = y;
		this.shadowType = shadowType;
		this.color = 0xffffffff;
		
		//this.height = font.image.height;
		
		setText(this.text);
		
		this.width = this.text.length() * 6 * scalex;
		this.height = 6 * scaley;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public void setScale(int scalex, int scaley)
	{
		if(scalex < 0 || scaley < 0) return;
		this.scalex = scalex;
		this.scaley = scaley;
		
		this.width = this.text.length() * 6 * scalex;
		this.height = 5 * scaley;
	}

	@Override
	public void draw(Renderer r)
	{
		/*
		this.text = this.text.toUpperCase();
		
		int unicode = 0;
		int offset = 0;
		
		for(int i = 0; i < text.length(); i++)
		{
			unicode = text.codePointAt(i) - 32;
			
			for(int x = 0; x < font.widths[unicode]; x++)
			{
				for(int y = 1; y < font.image.height; y++)
				{
					if(font.image.pixels[(x + font.offsets[unicode]) + y * font.image.width] == 0xffffffff )
					r.setPixel(x + xpos + offset, y + ypos - 1, this.color, ShadowType.NONE);
				}
			}
			
			offset += font.widths[unicode] * scale;
		}*/
		int scaleoffx = 0;
		int scaleoffy = 0;
		
		text = text.toUpperCase();
		
		int unicode = 0;
		int offset = 0;
		
		for(int i = 0; i < text.length(); i++)
		{
			unicode = text.codePointAt(i) - 32;
			
			for(int x = 0; x < font.dimension; x++)
			{
				for (int ys = 0; ys <= scalex; ys++)
				{
					scaleoffy = 0;
					for (int y = 1; y < font.dimension; y++)
					{
						if (font.image.pixels[(x + (font.dimension + 1) * unicode)
								+ y * font.image.getWidth()] == 0xffffffff)
						{
							for (int xs = 0; xs <= scaley; xs++)
							{
								r.setPixel(x + xpos + offset + scaleoffx, y + ypos - 1 + scaleoffy, color,
										ShadowType.NONE);
								if (xs > 0)
									scaleoffy++;
							}
						} else
						{
							for (int xs = 0; xs <= scaley; xs++)
							{
								// r.setPixel(x + xpos + offset + scaleoffx, y +
								// ypos - 1 + scaleoffy, 0xff0000ff,
								// ShadowType.NONE);
								if (xs > 0)
									scaleoffy++;
							}
						}
						scaleoffy--;
					}
					if(ys > 0)
						scaleoffx++;
				}
				scaleoffx--;
			}
			
			offset += font.dimension;
		}
		
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

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public int getColor()
	{
		return color;
	}

	public void setColor(int color)
	{
		this.color = color;
	}
	
}
