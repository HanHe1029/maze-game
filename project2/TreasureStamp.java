/*
 * Author: Han He
 * Purpose: cookie cutters of the treasure. House the values of the attributes that is needed 
 * for the creation of a treasure
 * Date: Nov 6, 2014
 */
package project2;

public class TreasureStamp
{
	/**
	 * Data members to store the values of a type of a Treasure
	 */
	private String name, image;
	private int goldValue, healthValue, width, height, xCord, yCord;
	
	/**
	 * Constructs Treasure with ceitain ability of either restoring the health
	 * or gain wealth
	 * 
	 * @param name, String, name of the treasures
	 * @param image, String, image filename of the treasure
	 * @param goldValue, int, gold value of the treasure
	 * @param healthValue, int, health value of the treasure
	 * @param width, int width of the treasure image
	 * @param height, int height of the treasure image
	 * @param xCord, int, x coordinate of where to draw the treasure
	 * @param yCord, int, y coordinate of where to draw the treasure
	 */
	public TreasureStamp(String name, String image, int goldValue,
			int healthValue, int width, int height, int xCord, int yCord)
	{
		this.name = name;
		this.image = image;
		this.goldValue = goldValue;
		this.healthValue = healthValue;
		this.width = width;
		this.height = height;
		this.xCord = xCord;
		this.yCord = yCord;
	}
	
	/**
	 * getters of the value of the attributes of a type of Treasure
	 * 
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	public String getImage()
	{
		return image;
	}
	
	public int getGoldValue()
	{
		return goldValue;
	}
	
	public int getHealthValue()
	{
		return healthValue;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getXCord()
	{
		return xCord;
	}
	
	public int getYCord()
	{
		return yCord;
	}
}
