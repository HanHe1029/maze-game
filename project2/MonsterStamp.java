/*
 * Author: Han He
 * Purpose: cookie cutters of the monster. House the values of the attributes that is needed 
 * for the creation of a Monster
 * Date: Nov 5, 2014
 */

package project2;

public class MonsterStamp
{	
	/**
	 * Data members to store the values of a type of a Monster
	 */
	private String name,image1,image2;
	private int health,attackCost,xCord,yCord;
	private float coolDownTime,mTp, pTm;
	/**
	 * Constructor of the cutters. Stores the values
	 * @param name, String, name of the monster
	 * @param health, int, health point of the monster
	 * @param attackCost, int, damage that monster would bring
	 * @param coolDownTime, float, time between actions
	 * @param image1, String, filename of image1
	 * @param image2, String, filename of image2
	 * @param xCord, int, x coordinate of the point where monster is to be drawn
	 * @param yCord, int, y coordinate of the point where monster is to be drawn
	 * @param num1, float, possibility of a hit when monster attack player 
	 * @param num2, float, possibility of a hit player attack monster 
	 */
	MonsterStamp(String name, int health, int attackCost, float coolDownTime, String image1, String image2, int xCord, int yCord, float mTp, float pTm)
	{
		this.name = name;
		this.image1 = image1;
		this.image2 = image2;
		this.health = health;
		this.attackCost = attackCost;
		this.xCord = xCord;
		this.yCord = yCord;
		this.coolDownTime = coolDownTime;
		this.mTp = mTp;
		this.pTm = pTm;
	}
	/**
	 * gets the name of this monster
	 * @return String name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * gets the filename of image1
	 * @return String image1
	 */
	public String getImage1()
	{
		return image1;
	}
	/**
	 * gets the filename of image2
	 * @return String image2
	 */
	public String getImage2()
	{
		return image2;
	}
	/**
	 * gets the health point of the monster
	 * @return int health
	 */
	public int getHealth()
	{
		return health;
	}
	/**
	 * gets the damage that would cost 
	 * @return int attackCost
	 */
	public int getAttackCost()
	{
		return attackCost;
	}
	/**
	 * gets the x Coordinate
	 * @return int xCord
	 */
	public int getXCord()
	{
		return xCord;
	}
	/**
	 * gets the y Coordinate
	 * @return int yCord
	 */
	public int getYCord()
	{
		return yCord;
	}
	/**
	 * gets the cool Down Time
	 * @return float coolDownTime
	 */
	public float getCDT()
	{
		return coolDownTime;
	}
	/**
	 * gets the monster to player hit possibility
	 * @return float mTp
	 */
	public float getmTp()
	{
		return mTp;
	}
	/**
	 * gets the player to monster hit possibility
	 * @return float pTm
	 */
	public float getpTm()
	{
		return pTm;
	}
}
