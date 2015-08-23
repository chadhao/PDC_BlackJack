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
public class Card {
    public static final int NUM_OF_SET = 4;
    private static final String[] SUIT = {"Spade", "Heart", "Club", "Diamond"};
    private static final String[] RANK = {"Two", "Three", "Four", "Five", "Six",
        "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
    public static HashMap power = new HashMap();
    private static String[] card = new String[52*Card.NUM_OF_SET];
    private static int cardNow;
    
    static
    {
        for (int i = 0; i < 13; i++)
        {
            if (i < 8)
            {
                power.put(RANK[i], i+2);
            }
            else if (i == 12)
            {
                power.put(RANK[i], 11);
            }
            else
            {
                power.put(RANK[i], 10);
            }
        }
        
        for (int i = 0; i < NUM_OF_SET; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                for (int k = 0; k < 13; k++)
                {
                    card[i*52+j*13+k] = "The " + RANK[k] + " of " + SUIT[j];
                }
            }
        }
        
        cardNow = 0;
    }
    
    public static void shuffleCards()
    {
        int r;
        Random rand = new Random();
        String temp;
        
        for (int i = 0; i < 52*NUM_OF_SET; i++)
        {
            r = rand.nextInt(52*NUM_OF_SET);
            temp = card[i];
            card[i] = card[r];
            card[r] = temp;
        }
        cardNow = 0;
    }
    
    public static String getCard(int index)
    {
        return card[index];
    }
    
    public static int getValue(int index)
    {
        int value;
        try
        {
            value = Integer.parseInt(power.get(getCard(index).split(" ")[1]).toString());
        }
        catch (NumberFormatException nfe)
        {
            value = 0;
        }
        return value;
    }
    
    public static int dealCard()
    {
        return ++cardNow-1;
    }
    
    public static int getCardNow()
	{
		return cardNow;
	}
}
