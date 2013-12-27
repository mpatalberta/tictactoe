package com.patco.tutorial.tictactoe;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TicTacToeGame mGame;
    private Button mBoardButtons[];
    private TextView mInfoTextView;
    private boolean bGameOver = false;
    public static String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		mBoardButtons = new Button[TicTacToeGame.BOARD_SIZE];
		mBoardButtons[0] = (Button) findViewById(R.id.one);
		mBoardButtons[1] = (Button) findViewById(R.id.two);
		mBoardButtons[2] = (Button) findViewById(R.id.three);
		mBoardButtons[3] = (Button) findViewById(R.id.four);
		mBoardButtons[4] = (Button) findViewById(R.id.five);
		mBoardButtons[5] = (Button) findViewById(R.id.six);
		mBoardButtons[6] = (Button) findViewById(R.id.seven);
		mBoardButtons[7] = (Button) findViewById(R.id.eight);
		mBoardButtons[8] = (Button) findViewById(R.id.nine);

		this.mInfoTextView = (TextView) findViewById(R.id.information);
		
		
		
		startNewGame();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		menu.add("New Game");
		menu.add("version 1.0");
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		startNewGame();
		return true;
	}
	private void startNewGame()
	{
		mGame = new TicTacToeGame();
		bGameOver = false;
		mGame.clearBoard();
		for(int i = 0;i<this.mBoardButtons.length;i++)
		{
			mBoardButtons[i].setText("");
			mBoardButtons[i].setEnabled(true);
			mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
			mInfoTextView.setText("you go first please..");
		}
	}
	private void setMove(char player,int location)
	{
		if(location > 8)
		{
			Log.e(TAG,"array index exceeded");
		}
		mGame.setMove(player, location);
		mBoardButtons[location].setEnabled(false);
		mBoardButtons[location].setText(String.valueOf(player));
		if(player == TicTacToeGame.HUMAN_PLAYER)
		{
			mBoardButtons[location].setTextColor(Color.rgb(0, 200, 0));
		}
		else
		{
			mBoardButtons[location].setTextColor(Color.rgb(200, 0, 0));
		}
		
	}
	private class ButtonClickListener implements View.OnClickListener
	{
		int location;
		public ButtonClickListener(int location)
		{
			this.location = location;
		}

		@Override
		public void onClick(View v) {
			if(bGameOver == true)
			{
				Log.v(TAG,"game is over");
				return;
			}
			if(mBoardButtons[location].isEnabled())
			{
				setMove(TicTacToeGame.HUMAN_PLAYER,location);
				
				int move = 0;
				int winner = 0;
				winner = mGame.checkForWinner();
				if(winner==0)
				{
					
					mInfoTextView.setText("it's androids move");
					move = mGame.getComputerMove();
					setMove(TicTacToeGame.COMPUTER_PLAYER,move);
					
					winner = mGame.checkForWinner();
				}
				switch(winner)
				{
					case 0:
					mInfoTextView.setText("It's your turn.");
					bGameOver = false;
					break;
					
					case 1:
					mInfoTextView.setText("It's a tie.");
					bGameOver = true;
					break;
					case 2:
						mInfoTextView.setText("You have won.");
					bGameOver = true;	
					break;
					
					default:
						mInfoTextView.setText("Android has won");
						
					    bGameOver = true;	
					
				}
			}
			
		}
		
	}

	
}