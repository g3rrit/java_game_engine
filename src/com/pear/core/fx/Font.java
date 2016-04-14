package com.pear.core.fx;

import com.pear.fx.drawables.Image;

public enum Font
{
	STANDART("/font/standart.png");
	
	public final int NUM_UNICODES = 60;
	//public int[] offsets = new int[NUM_UNICODES];
	//public int[] widths = new int[NUM_UNICODES];
	public int dimension;
	public Image image;
	
	Font(String path)
	{
		image = new Image(path);
		
		dimension = 6; //width and height of a letter
		/*
		int unicode = -1;
		int color;
		
		for(int x = 0; x< image.width; x++)
		{
			color = image.pixels[x];
			
			if(color == 0xff0000ff)
			{
				unicode++;
				offsets[unicode] = x;
			}
			
			if(color == 0xffffff00)
			{
				widths[unicode] = x - offsets[unicode];
			}
		}*/
	}
}
