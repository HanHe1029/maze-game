/*
 * Author: Han He
 * Purpose: representation of a room. Rooms can have monsters in and could have treasures.
 * There would be wall on 4 different directions. Room could have different textures and 
 * is possible that a monster spawn in this room.
 * Date: Nov 5, 2014
 */
package project2;

public class Cell
{
	/**
	 * Data member Monster. Sets to null if no monster in the room
	 */
	private Monster m;
	/**
	 * Data member Treasure. Sets to null if no treasure in the room
	 */
	private Treasure t;
	/**
	 * Data member, true if there is a wall at this direction
	 */
	private boolean northWall, eastWall, southWall, westWall;
	
	/**
	 * Data member, filename of the texture
	 */
	private String texture;
	
	/**
	 * Data member, true if monster can spawn in this room.
	 */
	private boolean monsterSpawn;
	
	/**
	 * Constructor of the Cell
	 * 
	 * @param m
	 *            Monster, in the room
	 * @param t
	 *            Treasure, in the room
	 * @param n
	 *            boolean, wall on the North
	 * @param e
	 *            boolean, wall on the East
	 * @param s
	 *            boolean, wall on the South
	 * @param w
	 *            boolean, wall on the West
	 * @param texture
	 *            String, name of the texture file
	 * @param ms
	 *            boolean, monster spawn
	 */
	public Cell(Monster m, Treasure t, boolean n, boolean e, boolean s,
			boolean w, String texture, boolean ms)
	{
		this.m = m;
		this.t = t;
		this.northWall = n;
		this.eastWall = e;
		this.southWall = s;
		this.westWall = w;
		this.texture = texture;
		this.monsterSpawn = ms;
	}
	
	/**
	 * gets the monster that is in the cell
	 * 
	 * @return Monster m, t could be null
	 */
	public Monster getMonster()
	{
		return m;
	}
	
	public void setTreasure(Treasure treasure)
	{
		t = treasure;
	}
	
	public void setMonster(Monster monster)
	{
		m = monster;
	}
	
	/**
	 * gets the treasure that is in the cell
	 * 
	 * @return Treasure t, t could be null
	 */
	public Treasure getTreasure()
	{
		return t;
	}
	
	/**
	 * gets the presence of wall of the North
	 * 
	 * @return boolean true if there is a wall false if there is not a wall
	 */
	public boolean getNorthWall()
	{
		return northWall;
	}
	
	/**
	 * gets the presence of wall of the East
	 * 
	 * @return boolean true if there is a wall false if there is not a wall
	 */
	public boolean getEastWall()
	{
		return eastWall;
	}
	
	/**
	 * gets the presence of wall of the South
	 * 
	 * @return boolean true if there is a wall false if there is not a wall
	 */
	public boolean getSouthWall()
	{
		return southWall;
	}
	
	/**
	 * gets the presence of wall of the West
	 * 
	 * @return boolean true if there is a wall false if there is not a wall
	 */
	public boolean getWestWall()
	{
		return westWall;
	}
	
	/**
	 * gets the filename of the texture
	 * 
	 * @return String texture
	 */
	public String getTexture()
	{
		return texture;
	}
	
	/**
	 * gets if this cell can be used as a spawn place for monster
	 * @return boolean, true if it could
	 * 				    false if not
	 */
	public boolean getMonsterSpawn()
	{
		return monsterSpawn;
	}
	
	/**
	 * toString function for the Cell class.
	 * Formats it nicely so that each attribute is shown on one line
	 */
	public String toString()
	{
		String s = "";
		if (m == null)
			s = s + "None" + "\n";
		else
			s = s + m.getName() + "\n";
		if (t == null)
			s = s + "None" + "\n";
		else
			s = s + t.getName() + "\n";
		s = s + northWall + "\n";
		s = s + eastWall + "\n";
		s = s + southWall + "\n";
		s = s + westWall + "\n";
		s = s + texture + "\n";
		s = s + monsterSpawn + "\n";
		return s;
	}
}
