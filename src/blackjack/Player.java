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
public class Player {
    private String name;
    private int chip;
    private int win;
    private int lose;
    private int push;
    private ArrayList<ArrayList<Integer>> inHand;
    private boolean isInsured;
    private boolean[] isDoubled;
    private boolean isSplit;
    
    public Player(String name, int chip)
    {
        this.name = name;
        this.chip = chip;
        this.win = 0;
        this.lose = 0;
        this.push = 0;
        this.isInsured = false;
        this.isDoubled = new boolean[2];
        this.isDoubled[0] = false;
        this.isDoubled[1] = false;
        this.isSplit = false;
        this.inHand = new ArrayList<ArrayList<Integer>>();
    }
    
    public Player()
    {
        this.name = "Dealer";
        this.chip = -1;
        this.win = -1;
        this.lose = -1;
        this.push = -1;
        this.isInsured = false;
        this.isDoubled = new boolean[2];
        this.isDoubled[0] = false;
        this.isDoubled[1] = false;
        this.isSplit = false;
        this.inHand = new ArrayList<ArrayList<Integer>>();
    }
    
    public String toString()
    {
        return "\nName: " + this.name + "\nChips: " + this.chip + "\nWin: " + this.win
                + "\nLose: " + this.lose + "\nPush: " + this.push + "\n";
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public ArrayList<ArrayList<Integer>> getInHand()
	{
		return this.inHand;
	}
    
    public boolean isDoubled(int whichHand)
	{
		return this.isDoubled[whichHand];
	}
    
    public boolean isInsured()
	{
		return this.isInsured;
	}
    
    public boolean isSplit()
	{
		return this.isSplit;
	}
    
    public int getChip()
	{
		return this.chip;
	}
    
    public void setChip(int chip)
	{
		this.chip = chip;
	}
    
    public void setDoubled(boolean isDoubled)
	{
		this.isDoubled[0] = isDoubled;
		this.isDoubled[1] = isDoubled;
	}
    
    public void setDoubled(int whichHand, boolean isDoubled)
    {
    	this.isDoubled[whichHand] = isDoubled;
    }
    
    public void setInsured(boolean isInsured)
	{
		this.isInsured = isInsured;
	}
    
    public void setSplit(boolean isSplit)
	{
		this.isSplit = isSplit;
	}
    
    public void addWin()
	{
		this.win++;
	}
    
    public void addLose()
	{
		this.lose++;
	}
    
    public void addPush()
	{
		this.push++;
	}
}
