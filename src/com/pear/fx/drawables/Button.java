package com.pear.fx.drawables;

import java.io.Serializable;

import com.pear.core.Renderer;
import com.pear.core.fx.ShadowType;

public class Button implements Drawable, Serializable
{
	private ButtonSprite buttonSprite;
	private Image image;
	private ShadowType shadowType;
	private Text text;
	
	private String stext;
	private int xpos, ypos;
	private int width, height;
	
	private boolean mouseOverButton;
	
	public Button(String text, ButtonSprite buttonSprite, int xpos, int ypos, ShadowType shadowType)
	{	
		this.xpos = xpos;
		this.ypos = ypos;
		
		this.buttonSprite = buttonSprite;
		this.image = this.buttonSprite.button;
		
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		this.shadowType = shadowType;
		
		this.stext = text;
		this.text = new Text(text, xpos + (this.width - text.length() * 6)/2, ypos + (height - 6)/2, shadowType);
		this.text.setScale((int)(width - 2)/(this.stext.length() * 6), (int)(height - 2)/6);
		this.text.setXpos(xpos + (width - this.text.getWidth())/2);
		this.text.setYpos(ypos + (height - this.text.getHeight())/2);
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
		r.draw(this.text);
	}
	
	public void mouseMoved(int x , int y)
	{
		if(!mouseOverButton && contains(x,y))
		{
			this.mouseOverButton = true;
			image = this.buttonSprite.buttonPressed;
		}
		else if(mouseOverButton)
		{
			this.mouseOverButton = false;
			image = this.buttonSprite.button;
		}
	}
	
	public boolean contains(int x, int y)
	{
		if(x > this.xpos && x < this.xpos + this.width && y > this.ypos && y < this.ypos + this.height)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	public ShadowType getShadowType()
	{
		return shadowType;
	}

	public void setShadowType(ShadowType shadowType)
	{
		this.shadowType = shadowType;
	}

	public int getXpos()
	{
		return xpos;
	}

	public void setXpos(int xpos)
	{
		this.xpos = xpos;
		
		this.text.setXpos(xpos + (this.width - this.text.getWidth())/2);
	}

	public int getYpos()
	{
		return ypos;
	}

	public void setYpos(int ypos)
	{
		this.ypos = ypos;
		
		this.text.setYpos(ypos + this.text.getHeight()/2);
	}
	
	public void setSize(int width, int height)
	{
		this.text.setScale((int)(width - 2)/(this.stext.length() * 6), (int)(height - 2)/6);
		this.text.setXpos(xpos + (width - this.text.getWidth())/2);
		this.text.setYpos(ypos + (height - this.text.getHeight())/2);
		
		this.width = width;
		this.height = height;
		
		this.buttonSprite.button.setSize(width, height);
		this.buttonSprite.buttonPressed.setSize(width, height);
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.text.setScale((int)(width - 2)/(this.stext.length() * 6),1);
		this.text.setXpos(xpos + (width - this.text.getWidth())/2);
		
		this.width = width;
		
		this.buttonSprite.button.setWidth(width);
		this.buttonSprite.buttonPressed.setWidth(width);
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.text.setScale(1, (int)(height - 2)/6);
		this.text.setYpos(ypos + (height - this.text.getHeight())/2);
		
		this.height = height;
		
		this.buttonSprite.button.setHeight(height);
		this.buttonSprite.buttonPressed.setHeight(height);
	}

	public boolean isMouseOverButton()
	{
		return mouseOverButton;
	}

	public void setMouseOverButton(boolean mouseOverButton)
	{
		this.mouseOverButton = mouseOverButton;
	}
	
	
}

