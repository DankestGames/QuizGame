package com.github.AndroidGames.QuizGames;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ChooseGameActivity extends Activity {
	public static final String TAG = "QuizGame";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_game_activity);
		Log.i(TAG, "ChooseGameActivity created");
	}
}
