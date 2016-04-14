package com.pear.fx.drawables;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import com.pear.core.fx.Pixel;

public class Image implements Serializable
{
	private int width, height;
	public int[] pixels;
	
	private boolean invertedx = false;
	private boolean invertedy = false;
	
	public Image(String path)
	{
		BufferedImage bimage = null;
		
		try
		{
			bimage = ImageIO.read(getClass().getResourceAsStream(path));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		width = bimage.getWidth();
		height = bimage.getHeight();
		pixels = bimage.getRGB(0, 0, width, height, null, 0 ,width);
		
		bimage.flush();
	}
	
	public Image(String path, int x, int y, int width, int height)
	{
		BufferedImage bimage = null;
		
		try
		{
			bimage = ImageIO.read(getClass().getResourceAsStream(path));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		this.width = width;
		this.height = height;
		pixels = bimage.getRGB(x, y, width, height, null, 0 ,width);
		
		bimage.flush();
	}
	
	public void setSize(int width, int height)
	{
		int[] bpixel = this.pixels.clone();
		this.pixels = new int[height * width];
		this.pixels = Pixel.resizePixels(bpixel,this.width , this.height, width, height);
		
		this.height = height;
		this.width = width;
	}
	
	public void setWidth(int width)
	{
		int[] bpixel = this.pixels.clone();
		this.pixels = new int[this.height * width];
		this.pixels = Pixel.resizePixels(bpixel,this.width , this.height, width, this.height);
		
		this.width = width;
	}
	
	public void setHeight(int height)
	{
		int[] bpixel = this.pixels.clone();
		this.pixels = new int[height * this.width];
		this.pixels = Pixel.resizePixels(bpixel,this.width , this.height, this.width, height);
		
		this.height = height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void invertX()
	{
		invertedx = (!invertedx) ? true : false;
		
		int[] bimage = pixels.clone();
		
		for(int x = 0; x < this.width; x++)
		{
			for(int y = 0; y < this.height; y++)
			{
				pixels[x + this.width * y] = bimage[(this.width - x - 1) + this.width * y];
			}
		}
	}
	
	public void invertX(boolean isInverted)
	{
		if(isInverted == invertedx)
		{
			return;
		}
		else
		{
			invertX();
		}
	}
	
	public void invertY()
	{
		invertedy = (!invertedy) ? true : false;
		
		int[] bimage = pixels.clone();
		
		for(int x = 0; x < this.width; x++)
		{
			for(int y = 0; y < this.height; y++)
			{
				pixels[x + this.width * y] = bimage[x + this.width * (this.height - y - 1)];
			}
		}
	}
	
	public void invertY(boolean isInverted)
	{
		if(isInverted == invertedy)
		{
			return;
		}
		else
		{
			invertY();
		}
	}
	
}

