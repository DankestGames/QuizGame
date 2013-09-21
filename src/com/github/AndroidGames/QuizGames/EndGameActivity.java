package com.github.AndroidGames.QuizGames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EndGameActivity extends Activity implements OnClickListener {
	
	public static final String TAG = "QuizGame";
	
	int isNewRecord;
	Button mainMenuButton;
	TextView recordText, gameOverText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end_game_activity);
		Log.i(TAG, "EndGameActivity created");
		
		Intent intent = getIntent();
		isNewRecord = intent.getIntExtra("Type", 0);
		
		mainMenuButton = (Button) findViewById(R.id.mainMenuButton);
		mainMenuButton.setOnClickListener(this);
		recordText = (TextView) findViewById(R.id.recordText);
		
		if(isNewRecord > 0)
			recordText.setText("New Record!!!");
		else
			recordText.setText("Loser!");
		
		gameOverText = (TextView) findViewById(R.id.gameOverText);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mainMenuButton:
			Log.i(TAG, "Main Menu was clicked");
			finish();
		}		
	}
}
