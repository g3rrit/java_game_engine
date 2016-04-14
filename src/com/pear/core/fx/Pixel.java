package com.pear.core.fx;

public class Pixel
{
	public static float getAlpha(int color)
	{
		return (float)(0xff & (color >> 24)) / 255f;
	}
	
	public static float getRed(int color)
	{
		return (float)(0xff & (color >> 16)) / 255f;
	}
	
	public static float getGreen(int color)
	{
		return (float)(0xff & (color >> 8)) / 255f;
	}
	
	public static float getBlue(int color)
	{
		return (float)(0xff & (color)) / 255f;
	}
	
	public static int getColor(float a, float r, float g, float b)
	{
		return ((int)(a * 255f + 0.5) << 24 |
				(int)(r * 255f + 0.5) << 16 |
				(int)(g * 255f + 0.5) << 8 |
				(int)(b * 255f + 0.5));
	}
	
	public static int getColorPower(int color, float power)
	{
		return getColor(1, getRed(color) * power,
							getGreen(color) * power,
							getBlue(color) * power);
	}
	
	public static int getLightBlend(int color, int light, int ambientLight)
	{
		float r = getRed(light);
		float g = getGreen(light);
		float b = getBlue(light);
		
		if(r < getRed(ambientLight)) r = getRed(ambientLight);
		if(r < getGreen(ambientLight)) r = getGreen(ambientLight);
		if(r < getBlue(ambientLight)) r = getBlue(ambientLight);
		
		return getColor(1, r * getRed(color), g * getGreen(color), b * getBlue(color));
	}
	
	public static int getMax(int c0, int c1)
	{
		return getColor(1, Math.max(getRed(c0), getRed(c1)),
							Math.max(getGreen(c0), getGreen(c1)),
							Math.max(getBlue(c0), getBlue(c1)));
	}
	
	public static int[] resizePixels(int[] pixels,int w1,int h1,int w2,int h2) {
	    int[] temp = new int[w2*h2] ;
	    // EDIT: added +1 to account for an early rounding problem
	    int x_ratio = (int)((w1<<16)/w2) +1;
	    int y_ratio = (int)((h1<<16)/h2) +1;
	    //int x_ratio = (int)((w1<<16)/w2) ;
	    //int y_ratio = (int)((h1<<16)/h2) ;
	    int x2, y2 ;
	    for (int i=0;i<h2;i++) {
	        for (int j=0;j<w2;j++) {
	            x2 = ((j*x_ratio)>>16) ;
	            y2 = ((i*y_ratio)>>16) ;
	            temp[(i*w2)+j] = pixels[(y2*w1)+x2] ;
	        }                
	    }                
	    return temp ;
	}
	
}


