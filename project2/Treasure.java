/*
 * Author: Han He
 * Purpose: represents the Treasure in the game
 * Date: Nov 5, 2014
 */
package project2;

import java.util.*;
import java.io.*;

public class Treasure
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
	public Treasure(String name, String image, int goldValue,
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
	 * gets the name of the Treasure
	 * @return String name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * gets the image file name of the treasure
	 * @return image
	 */
	public String getImage()
	{
		return image;
	}
	/**
	 * gets the gold value of the treasure
	 * @return int goldValue
	 */
	public int getGoldValue()
	{
		return goldValue;
	}
	/**
	 * gets the health value that the treasure could restore
	 * @return int healthValue
	 */
	public int getHealthValue()
	{
		return healthValue;
	}
	/**
	 * gets the width of the image 
	 * @return int width
	 */
	public int getWidth()
	{
		return width;
	}
	/**
	 * gets the height of the image
	 * @return int height
	 */
	public int getHeight()
	{
		return height;
	}
	/**
	 * gets the x coordinate where the image should be drawn
	 * @return int xCord
	 */
	public int getXCord()
	{
		return xCord;
	}
	/**
	 * gets the y coordinate where the image should be drawn
	 * @return int yCord
	 */
	public int getYCord()
	{
		return yCord;
	}
	/**
	 * formats a string that shows all the values of the attributes
	 */
	public String toString()
	{
		String s = "Name: " + getName() + "\n";
		s = s + "Image File: " + getImage() + "\n";
		s = s + "Gold Value: " + getGoldValue() + "\n";
		s = s + "Health Restore Value: " + getHealthValue() + "\n";
		s = s + "Image Width: " + getWidth() + "\n";
		s = s + "Image Height: " + getHeight() + "\n";
		s = s + "x Value in Room: " + getXCord() + "\n";
		s = s + "y Value in Room: " + getYCord() + "\n";
		return s;
	}
	/**
	 * tester
	 * @param args
	 * @throws Exception FileNotFoundException
	 */
	public static void main(String[] args) throws Exception
	{
		Scanner scan = new Scanner(new FileReader("treasure.txt"));
		int numT = scan.nextInt();
		for (int i = 0; i < numT; i++)
		{
			scan.nextLine();
			scan.nextLine();
			String name = scan.nextLine();
			String image = scan.nextLine();
			int goldValue = scan.nextInt();
			int healthValue = scan.nextInt();
			int width = scan.nextInt();
			int height = scan.nextInt();
			int xCord = scan.nextInt();
			int yCord = scan.nextInt();
			Treasure t = new Treasure(name, image, goldValue, healthValue,
					width, height, xCord, yCord);
			System.out.println(t);
		}
		
	}
}
