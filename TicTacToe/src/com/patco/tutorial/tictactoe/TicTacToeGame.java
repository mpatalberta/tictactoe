package com.patco.tutorial.tictactoe;


import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import android.util.Log;

public class TicTacToeGame {

	//private char mBoard[] = {'1','2','3','4','5','6','7','8','9'};
	private char mBoard[];
	public final static int BOARD_SIZE = 9;
	public boolean bGameOver = false;
	public static final char HUMAN_PLAYER = 'X';
	public static final char COMPUTER_PLAYER = 'O';
	public static final char OPEN_SPOT = ' ';
	private Random mRand; 
	public static String TAG = "TicTacToeGame";
	public TicTacToeGame() 
	{
		bGameOver = false;
		
		mBoard = new char[9];
		
		// Seed the random number generator
		mRand = new Random(); 
		
		for(int i=0;i<mBoard.length;i++)
		{
			mBoard[i] = OPEN_SPOT;
		}

	}
	
	public void clearBoard()
	{
		for(int i =0;i<mBoard.length;i++)
		{
			mBoard[i] = OPEN_SPOT;
		}
	}
	public void setMove(char player, int location)
	{
		Log.v(TAG,"player>"+player+"<location>"+location+"<");
		mBoard[location] = player;
	}

	
	private void displayBoard()	{
		System.out.println();
		System.out.println(mBoard[0] + " | " + mBoard[1] + " | " + mBoard[2]);
		System.out.println("-----------");
		System.out.println(mBoard[3] + " | " + mBoard[4] + " | " + mBoard[5]);
		System.out.println("-----------");
		System.out.println(mBoard[6] + " | " + mBoard[7] + " | " + mBoard[8]);
		System.out.println();
	}
	
	// Check for a winner.  Return
	//  0 if no winner or tie yet
	//  1 if it's a tie
	//  2 if X won
	//  3 if O won
	public int checkForWinner() {
		
		// Check horizontal wins
		for (int i = 0; i <= 6; i += 3)	{
			if (mBoard[i] == HUMAN_PLAYER && 
				mBoard[i+1] == HUMAN_PLAYER &&
				mBoard[i+2]== HUMAN_PLAYER)
			{
				Log.v(TAG,"Human won row "+">"+ i + "<");
				return 2;
			}
			if (mBoard[i] == COMPUTER_PLAYER && 
				mBoard[i+1]== COMPUTER_PLAYER && 
				mBoard[i+2] == COMPUTER_PLAYER)
			{
				Log.v(TAG,"Computer won row>"+i+"<");
				return 3;
			}
		}
	
		// Check vertical wins
		for (int i = 0; i <= 2; i++) {
			if (mBoard[i] == HUMAN_PLAYER && 
				mBoard[i+3] == HUMAN_PLAYER && 
				mBoard[i+6]== HUMAN_PLAYER)
			{
				Log.v(TAG,"Human won column"+">"+i+"<");
				return 2;
			}
			if (mBoard[i] == COMPUTER_PLAYER && 
				mBoard[i+3] == COMPUTER_PLAYER && 
				mBoard[i+6]== COMPUTER_PLAYER)
				{
				    Log.v(TAG,"Computer won column"+">"+i+"<");
					return 3;
				}
		}
	
		// Check for diagonal wins
		if ((mBoard[0] == HUMAN_PLAYER &&
			 mBoard[4] == HUMAN_PLAYER && 
			 mBoard[8] == HUMAN_PLAYER) ||
			(mBoard[2] == HUMAN_PLAYER && 
			 mBoard[4] == HUMAN_PLAYER &&
			 mBoard[6] == HUMAN_PLAYER))
		    {
			Log.v(TAG,"Human won diagonal");
				return 2;
		    }
		if ((mBoard[0] == COMPUTER_PLAYER &&
			 mBoard[4] == COMPUTER_PLAYER && 
			 mBoard[8] == COMPUTER_PLAYER) ||
			(mBoard[2] == COMPUTER_PLAYER && 
			 mBoard[4] == COMPUTER_PLAYER &&
			 mBoard[6] == COMPUTER_PLAYER))
			{
			Log.v(TAG,"Computer won diagonal");
				return 3;
			}
	
		// Check for tie
		for (int i = 0; i < BOARD_SIZE; i++) {
			// If we find a number, then no one has won yet
			if (mBoard[i] == OPEN_SPOT)
			{
				Log.v(TAG,"Found an open spot to play");
				return 0;
			}
		}
	
		// If we make it through the previous loop, all places are taken, so it's a tie
		return 1;
	}
	

	
	public int getComputerMove() 
	{
		int move;

		// First see if there's a move O can make to win
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (mBoard[i] == OPEN_SPOT) {
				char curr = mBoard[i];
				mBoard[i] = COMPUTER_PLAYER;
				if (checkForWinner() == 3) {
					move = i;
					System.out.println("Computer is moving to " + move);
					return (move);
				}
				else
					mBoard[i] = curr;
			}
		}

		// See if there's a move O can make to block X from winning
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (mBoard[i] == OPEN_SPOT) {
				char curr = mBoard[i];   // Save the current number
				mBoard[i] = HUMAN_PLAYER;
				if (checkForWinner() == 2) {
					mBoard[i] = COMPUTER_PLAYER;
					move = i;
					System.out.println("Computer is moving to " + move);
					return move;
				}
				else
					mBoard[i] = curr;
			}
		}

		// Generate random move
		
  //     	move = 0;
  //		for(int i=0;i<mBoard.length;i++)
//		{
//		  if(mBoard[i] == OPEN_SPOT)
//		  {
//			  move = i;
//			  break;
//		  }
//		}
		
		do
		{
			move = mRand.nextInt(BOARD_SIZE);
		} while (mBoard[move] == HUMAN_PLAYER || mBoard[move] == COMPUTER_PLAYER);
			
		System.out.println("Computer is moving to " + (move));


		mBoard[move] = COMPUTER_PLAYER;
		return move;
	}	
	
	

}
