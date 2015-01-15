/*
 * Author: Han He
 * Purpose: This creates the game and implements the game loop. When you lost all your health, the game
 * would end, if you killed all the monsters the game would also end. 
 * Date: Nov 12, 2014
 */
package project2;

public class Game
{
	/**
	 * Only data member of the class; GUI
	 */
	private GUI gui;
	
	/**
	 * constructor of the GUI
	 * @throws Exception fileNotFoundException
	 */
	public Game() throws Exception
	{
		gui = new GUI();
	}
	
	/**
	 * game loop, game ends when either all the monsters are killed or the player died.
	 * @throws InterruptedException, in case the game thread got interrupted
	 */
	public void runGame() throws InterruptedException
	{
		// game loop
		while ((Player.getHealth() > 0)
				&& (gui.getDisplay().getMaze().numAliveMonster > 0))
		{
			// updates gui
			gui.revalidate();
			gui.repaint();
			// check if any monster is dead, interrupt the thread that is running that monster
			for (int i = 0; i < gui.getDisplay().getMaze().getThreadList()
					.size(); i++)
			{
				if (!gui.getDisplay().getMaze().getMonsterList().get(i)
						.checkAlive())
				{
					gui.getDisplay().getMaze().getThreadList().get(i)
							.interrupt();
				}
			}
		}
		// end game case 1: player won.
		if (Player.checkAlive())
		{
			System.out.println("You won!!!");
		}// end game case 2: player died. 
		else
		{
			System.out.println("You Lost....Poor Kid...");
			System.out.println("Maybe you should try play it again");
		}
		//Keeps the window up for 3 second and then close it.
		Thread.sleep(3000);
		gui.setVisible(false); 
		gui.dispose();
	}
	
	/**
	 * main game
	 * @param args 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		Game game = new Game();
		game.runGame();  
	}
	
}
