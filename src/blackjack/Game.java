/*
 * The MIT License
 *
 * Copyright 2015 Chad.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package blackjack;

import java.util.*;

/**
 *
 * @author Chad
 */
public class Game {
    private static Player dealer;
    private static Player player;
    private static Scanner keyboard;
    private static int bet;
    
    static
    {
        keyboard = new Scanner(System.in);
        dealer = new Player();
        bet = 0;
    }
    
    public static int getBet()
	{
		return bet;
	}
    
    public static void initPlayer()
    {
        String name;
        int chip;
        System.out.println("Welcome to Blackjack!");
        System.out.print("Tell me your name: ");
        name = keyboard.next();
        System.out.print("How many chips do you hava: ");
        chip = keyboard.nextInt();
        Game.player = new Player(name, chip);
    }
    
    public static void initRound()
    {
    	dealer.getInHand().clear();
    	dealer.getInHand().add(new ArrayList<Integer>());
        player.getInHand().clear();
        player.getInHand().add(new ArrayList<Integer>());
    	bet = 0;
    	player.setDoubled(false);
    	player.setInsured(false);
    	player.setSplit(false);
    	
    	if (Card.getCardNow() > (52*Card.NUM_OF_SET*3/4))
    	{
    		Card.shuffleCards();
    		System.out.println("\n<Shuffling cards...>\n");
    	}
    }
    
    public static void bet()
    {
    	while (true)
    	{
    		int tempBet = 0;
    		System.out.println("Your current bet: " + bet);
    		System.out.println("Your current chips: " + player.getChip());
    		System.out.print("Please bet: ");
    		tempBet = keyboard.nextInt();
    		if (tempBet > player.getChip())
    		{
    			System.out.println("You don't have enough chips to bet!");
    		}
    		else
    		{
				bet += tempBet;
				player.setChip(player.getChip()-tempBet);
				System.out.println("Your current bet: " + bet);
	    		System.out.println("Your current chips: " + player.getChip());
	    		break;
			}
    	}
    }
    
    public static void printCards(Player who, int whichHand)
    {
    	for (int i = 0; i < who.getInHand().get(whichHand).size(); i++)
    	{
    		System.out.print(Card.getCard(who.getInHand().get(whichHand).get(i).intValue()));
    		if (i != who.getInHand().get(whichHand).size()-1)
    		{
    			System.out.print(", ");
    		}
    	}
    	System.out.println("");
    }
    
    public static int totalValue(Player who, int whichHand)
    {
    	int totalValue = 0;
    	for (Integer integer: who.getInHand().get(whichHand))
    	{
    		totalValue += Card.getValue(integer.intValue());
    	}
    	if (getAce(who, whichHand) > 0)
    	{
    		totalValue -= (getAce(who, whichHand)-1) * 10;
    		if (totalValue > 21)
    		{
    			totalValue -= 10;
    		}
    	}
    	return totalValue;
    }
    
    public static int getAce(Player who, int whichHand)
    {
    	int numOfAce = 0;
    	for (Integer integer: who.getInHand().get(whichHand))
    	{
    		if (Card.getValue(integer.intValue()) == 11)
    		{
    			numOfAce++;
    		}
    	}
    	return numOfAce;
    }
    
    public static void insure()
    {
    	System.out.println("Do you want to insure this hand? (Y/N): ");
    	if (keyboard.nextLine().toUpperCase().charAt(0) == 'Y')
    	{
    		if (player.getChip() >= bet / 2)
    		{
    			player.setChip(player.getChip()-bet/2);
    			player.setInsured(true);
    			System.out.println("This hand is insured with " + bet/2 + " chips.");
    			System.out.println("Your current bet: " + bet);
    			System.out.println("Your current chips: " + player.getChip());
    		}
    		else
    		{
    			System.out.println("You don't have enough chips to insure this hand!");
    			System.out.println("Your current bet: " + bet);
    			System.out.println("Your current chips: " + player.getChip());
    		}
    	}
    	else
    	{
    		System.out.println("You didn't insure this hand!");
			System.out.println("Your current bet: " + bet);
			System.out.println("Your current chips: " + player.getChip());
		}
    }
    
    public static boolean gameEnd()
    {
    	boolean isEnd = false;
    	
    	return isEnd;
    }
    
    public static void gamePlay()
    {
        Card.shuffleCards();
        int menuSelect;
        while(true)
        {
            System.out.println("**********Blackjack**********");
            System.out.println("1. Play");
            System.out.println("2. Player status");
            System.out.println("3. Exit");
            System.out.println("*****************************");
            while (true)
            {
                menuSelect = keyboard.nextInt();
                if (menuSelect < 1 || menuSelect > 3)
                {
                    System.out.println("Please select from menu!");
                }
                else
                {
                    break;
                }
            }
            
            if (menuSelect == 3)
            {
                System.out.println("Thanks for playing! Bye!");
                break;
            }
            else if (menuSelect == 2)
            {
                if (player == null)
                {
                    System.out.println("No player record found!");
                }
                else
                {
                    System.out.println(player);
                }
            }
            else
            {
                if (player == null)
                {
                    initPlayer();
                }
                System.out.println("Welcome " + player.getName() + "\nHave fun!\n\n");
                
                while (true)
                {
                	Game.initRound();
                	
                	if (player.getChip() <= 0)
                	{
                		System.out.println("You are penniless!");
                		player = null;
                		break;
                	}
                	
                	bet();
                	
                	System.out.println("\n<Dealing cards...>\n");
                	dealer.getInHand().get(0).add(Card.dealCard());
                	player.getInHand().get(0).add(Card.dealCard());
                	dealer.getInHand().get(0).add(Card.dealCard());
                	player.getInHand().get(0).add(Card.dealCard());
                	System.out.println("Dealer shows " + Card.getCard(dealer.getInHand().get(0).get(0).intValue()));
                	System.out.println("Dealer's point is " + Card.getValue(dealer.getInHand().get(0).get(0).intValue()));
                	System.out.print("Your cards: ");
                	printCards(player, 0);
                	System.out.println("Your point is " + totalValue(player, 0));
                	
                	if (Card.getValue(dealer.getInHand().get(0).get(0).intValue()) == 11)
                	{
                		insure();
                		if (totalValue(dealer, 0) == 21 && totalValue(player, 0) < totalValue(dealer, 0))
						{
							if (player.isInsured())
							{
								
							}
						}
                	}
                }
            }
        }
    }
}
