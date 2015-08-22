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
    
    static
    {
        keyboard = new Scanner(System.in);
        dealer = new Player();
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
            }
            else if (menuSelect == 2)
            {
                if (Game.player == null)
                {
                    System.out.println("No player record found!");
                }
                else
                {
                    System.out.println(Game.player);
                }
            }
            else
            {
                if (Game.player == null)
                {
                    Game.initPlayer();
                }
                System.out.println("Welcome " + Game.player.getName() + "\nHave fun!\n\n");
            }
        }
    }
}
