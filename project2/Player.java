/*
 * Author: Han He
 * Purpose: represents Player in the game. Should have used singleton. But there is only one
 * 			player initialized here. most of the data are static so that they can be referred
 * 			by other class.
 * 			makes a "ta da" sound when treasure is picked up.
 * Acknowledgement: I give thanks to Blaks who suggested making the data members static 
 * 					so that they can be accessed by other classes.
 * Date: Dec 1, 2014
 */
package project2;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.Random;

public class Player
{
	/**
	 * name, String, name of Character health, int, health point of the
	 * character attackCost, int, damage that this character could bring
	 * coolDownTime, int, time between actions
	 */
	private String name;
	private int attackCost;
	private float coolDownTime;
	/**
	 * Data member, wealth, the amount of money that the player has
	 */
	private int wealth;
	private char direction;
	private static int row;
	private static int column;
	private static int defaultHealth;
	private static int health;
	
	/**
	 * Data member, for playing a sound
	 */
	private AudioClip ac;
	/**
	 * Constructor
	 * 
	 * @param name
	 *            , String name of the Player
	 * @param attackCost
	 *            , int damage of the attack
	 * @param coolDownTime
	 *            , float time between actions
	 */
	public Player(int attackCost, float coolDownTime)
	{
		
		this.health = 100;
		this.attackCost = attackCost;
		this.coolDownTime = coolDownTime;
		this.wealth = 0;
		this.direction = 'S';
		this.row = 0;
		this.column = 0;
		defaultHealth = 100;
		ac = Applet.newAudioClip(this.getClass().getResource("/ta-da.wav"));
	}
	
	/**
	 * gets the wealth of the player
	 * 
	 * @return int wealth
	 */
	public int getWealth()
	{
		return wealth;
	}
	
	/**
	 * changes the direction the player is facing
	 * @param d
	 */
	public void changeDirectionFacing(char d)
	{
		this.direction = d;
	}
	
	/**
	 * gets the row the player is at
	 * @return
	 */
	public static int getRow()
	{
		return row;
	}
	
	/**
	 * gets the column the player is at
	 * @return
	 */
	public static int getColumn()
	{
		return column;
	}
	
	/**
	 * adds health point to the player
	 * 
	 * @param points
	 *            , int
	 */
	public void addHealth(int points)
	{
		if (health + points >= defaultHealth)
		{
			health = defaultHealth;
		} else
		{
			health = health + points;
		}
	}
	
	/**
	 * adds wealth to the Player
	 * 
	 * @param money
	 * 
	 */
	public void addWealth(int money)
	{
		wealth += money;
	}
	
	/**
	 * updates the values of health and wealth after pick up a treasure
	 * makes a "TA DA" sound when called.
	 * @param treasure
	 *            Treasure
	 */
	public void pickUpTreasure(Treasure treasure)
	{
		ac.play();
		int points = treasure.getHealthValue();
		int money = treasure.getGoldValue();
		addWealth(money);
		addHealth(points);
	}
	
	
	/**
	 * gets the default health of the player
	 * @return
	 */
	public static int getDefault()
	{
		return defaultHealth;
	}
	
	
	/**
	 * gets the direction facing
	 * @return
	 */
	public char getDirectionFacing()
	{
		return this.direction;
	}
	
	
	/**
	 * turns right. Depends on the direction it is facing
	 */
	public void turnRight()
	{
		if (direction == 'N')
		{
			changeDirectionFacing('E');
			
		} else if (direction == 'E')
		{
			changeDirectionFacing('S');
			
		} else if (direction == 'S')
		{
			changeDirectionFacing('W');
		} else
		{
			changeDirectionFacing('N');
		}
	}
	
	/**
	 * turns left. Depends on the direction it is facing
	 */
	public void turnLeft()
	{
		if (direction == 'N')
		{
			changeDirectionFacing('W');
			
		} else if (direction == 'E')
		{
			changeDirectionFacing('N');
			
		} else if (direction == 'S')
		{
			changeDirectionFacing('E');
		} else
		{
			changeDirectionFacing('S');
		}
	}
	
	/**
	 * moves to its left, depend on direction it is facing
	 */
	public void moveLeft()
	{
		if (direction == 'N')
		{
			column--;
			
		} else if (direction == 'E')
		{
			row--;
			
		} else if (direction == 'S')
		{
			column++;
		} else
		{
			row++;
		}
	}
	
	/**
	 * moves to its right, depend on direction it is facing
	 */
	public void moveRight()
	{
		if (direction == 'N')
		{
			column++;
			
		} else if (direction == 'E')
		{
			row++;
			
		} else if (direction == 'S')
		{
			column--;
		} else
		{
			row--;
		}
	}
	
	/**
	 * moves to its front, depend on direction it is facing
	 */
	public void moveFront()
	{
		if (direction == 'N')
		{
			row--;
			
		} else if (direction == 'E')
		{
			column++;
			
		} else if (direction == 'S')
		{
			row++;
		} else
		{
			column--;
		}
	}
	
	
	/**
	 * moves to its back, depend on direction it is facing
	 */
	public void moveBack()
	{
		if (direction == 'N')
		{
			row++;
			
		} else if (direction == 'E')
		{
			column--;
			
		} else if (direction == 'S')
		{
			row--;
		} else
		{
			column++;
		}
	}
	
	/**
	 * attacks the monster
	 * @param m
	 */
	public void attack(Monster m)
	{
		float ptm = m.getpTmAttackPossibility() * 100;
		Random rn = new Random();
		int randomNumber = rn.nextInt(99) + 0;
		if (randomNumber < ptm)
		{
			m.setHealth(m.getHealth() - this.getAttackCost());
			// System.out.println("Hit!!!");
		}
		/*
		 * else { System.out.println("Missed!!"); }
		 */
	}
	
	/**
	 * gets the damage that the character could bring from attacking
	 * 
	 * @return int attackCost
	 */
	public int getAttackCost()
	{
		return attackCost;
	}
	
	/**
	 * gets the time between actions of this character
	 * 
	 * @return float coolDownTime
	 */
	public float getCoolDownTime()
	{
		return coolDownTime;
	}
	
	/**
	 * sets the health point
	 * 
	 * @param newHealth
	 *            int
	 */
	public void setHealth(int newHealth)
	{
		health = newHealth;
	}
	
	/**
	 * checks if the Character is still alive
	 * 
	 * @return true if health is greather than 0 false if not
	 */
	public static boolean checkAlive()
	{
		return (health > 0);
	}
	
	/**
	 * decreases the health by the point of damage
	 * @param attack int, point of damage
	 */
	public static void gotHit(int attack)
	{
		health = health - attack;
	}
	
	/**
	 * gets the health
	 * @return int health
	 */
	public static int getHealth()
	{
		return health;
	}
}
