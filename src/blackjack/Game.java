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
    private static int[] bet;
    
    static
    {
        keyboard = new Scanner(System.in);
        dealer = new Player();
        bet = new int[2];
    }
    
    public static int getBet(int whichBet)
	{
		return bet[whichBet];
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
    	bet[0] = 0;
    	bet[1] = 0;
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
    		System.out.println("Your current bet: " + bet[0]);
    		System.out.println("Your current chips: " + player.getChip());
    		System.out.print("Please bet: ");
    		tempBet = keyboard.nextInt();
    		if (tempBet > player.getChip())
    		{
    			System.out.println("You don't have enough chips to bet!");
    		}
    		else
    		{
				bet[0] += tempBet;
				player.setChip(player.getChip()-tempBet);
				System.out.println("Your current bet: " + bet[0]);
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
    	System.out.println("\nDo you want to insure this hand? (Y/N): ");
    	if (keyboard.next().toUpperCase().charAt(0) == 'Y')
    	{
    		if (player.getChip() >= bet[0] / 2)
    		{
    			player.setChip(player.getChip()-bet[0]/2);
    			player.setInsured(true);
    			System.out.println("This hand is insured with " + bet[0]/2 + " chips.");
    			System.out.println("Your current bet: " + bet[0]);
    			System.out.println("Your current chips: " + player.getChip());
    		}
    		else
    		{
    			System.out.println("You don't have enough chips to insure this hand!");
    			System.out.println("Your current bet: " + bet[0]);
    			System.out.println("Your current chips: " + player.getChip());
    		}
    	}
    	else
    	{
    		System.out.println("You didn't insure this hand!");
			System.out.println("Your current bet: " + bet[0]);
			System.out.println("Your current chips: " + player.getChip());
		}
    }
    
    public static void split()
    {
    	System.out.println("\nDo you want to split this hand? (Y/N): ");
    	if (keyboard.next().toUpperCase().charAt(0) == 'Y')
    	{
    		if (player.getChip() >= bet[0])
    		{
    			player.setChip(player.getChip()-bet[0]);
    			bet[1] = bet[0];
    			player.getInHand().add(new ArrayList<Integer>());
    			player.getInHand().get(1).add(player.getInHand().get(0).get(1).intValue());
    			player.getInHand().get(0).remove(1);
    			player.getInHand().get(0).add(Card.dealCard());
    			player.getInHand().get(1).add(Card.dealCard());
    			System.out.println("You split this hand!");
    			for (int i = 0; i < player.getInHand().size(); i++)
    			{
    				System.out.print("Your hand " + (i+1) + " cards: ");
    				printCards(player, i);
    				System.out.println("Your hand " + (i+1) + " point is " + totalValue(player, i));
    				System.out.println("Your hand " + (i+1) + " bet is " + bet[i]);
    			}
    			System.out.println("Your current chips: " + player.getChip());
    		}
    		else
    		{
    			System.out.println("You don't have enough chips to split this hand!");
    			System.out.println("Your current bet: " + bet[0]);
    			System.out.println("Your current chips: " + player.getChip());
			}
    	}
    	else
    	{
    		System.out.println("You didn't split this hand!");
			System.out.println("Your current bet: " + bet[0]);
			System.out.println("Your current chips: " + player.getChip());
    	}
    }
    
    public static boolean gameEnd()
    {
    	boolean isEnd = false;
    	System.out.println("\nDo you want to play another hand? (Y/N): ");
    	if (keyboard.next().toUpperCase().charAt(0) == 'N')
    	{
    		isEnd = true;
    	}
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
            System.out.print("Please select: ");
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
								player.setChip(player.getChip()+bet[0]);
							}
							player.addLose();
							System.out.println("Dealer's cards: ");
							printCards(dealer, 0);
							System.out.println("Dealer's point is " + totalValue(dealer, 0));
							System.out.println("Dealer has Blackjack!");
							if (gameEnd())
							{
								break;
							}
							else
							{
								continue;
							}
						}
                		else if (totalValue(dealer, 0) == 21 && totalValue(player, 0) == 21)
                		{
                			player.setChip(player.getChip()+bet[0]);
                			player.addPush();
                			System.out.println("Push!");
                			if (gameEnd())
							{
								break;
							}
							else
							{
								continue;
							}
                		}
                	}
                	
                	if (Card.getValue(player.getInHand().get(0).get(0).intValue()) == Card.getValue(player.getInHand().get(0).get(1).intValue()))
                	{
                		split();
                	}
                	
                	if (gameEnd())
                	{
                		break;
                	}
                	continue;
                }
            }
        }
    }
}
