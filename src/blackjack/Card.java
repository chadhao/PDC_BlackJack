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
    private static final String[] suit = {"Spade", "Heart", "Club", "Diamond"};
    private static final String[] rank = {"Two", "Three", "Four", "Five", "Six",
        "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
    public static final HashMap power = new HashMap();
    private static final String[] card = new String[52];
    private static int cardNow;
    
    static
    {
        for (int i = 0; i < 13; i++)
        {
            if (i < 8)
            {
                Card.power.put(Card.rank[i], i+2);
            }
            else if (i == 12)
            {
                Card.power.put(Card.rank[i], 11);
            }
            else
            {
                Card.power.put(Card.rank[i], 10);
            }
        }
        
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 13; j++)
            {
                Card.card[i*13+j] = "The " + Card.rank[j] + " of " + Card.suit[i];
            }
        }
        
        Card.cardNow = 0;
    }
    
    public static void shuffleCards()
    {
        int r;
        Random rand = new Random();
        String temp;
        
        for (int i = 0; i < 52; i++)
        {
            r = rand.nextInt(52);
            temp = Card.card[i];
            Card.card[i] = Card.card[r];
            Card.card[r] = temp;
        }
        Card.cardNow = 0;
    }
    
    public static String getCard(int index)
    {
        return Card.card[index];
    }
    
    public static int getValue(int index)
    {
        int value;
        try
        {
            value = Integer.parseInt(Card.power.get(Card.getCard(index).split(" ")[1]).toString());
        }
        catch (NumberFormatException nfe)
        {
            value = 0;
        }
        return value;
    }
    
    public static int dealCard()
    {
        return (Card.cardNow++)-1;
    }
}
