package com.pear.geometry;

public class RightTriangle
{
	/*	
	* 		|
	*		||
	*height |||
	*		||||
	*		p0||||
	*		 width
	*/
	
	
	protected Point p0;
	protected int width, height;
	
	public RightTriangle(Point p0, int width, int height)
	{
		this.p0 = p0.copy();
		this.width = width;
		this.height = height;
	}
}
