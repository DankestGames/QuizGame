package com.github.AndroidGames.QuizGames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChooseGameActivity extends Activity implements OnClickListener {
	public static final String TAG = "QuizGame";

	Button onTimeButton;
	Button survivalButton;
	Button hardcoreButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_game_activity);
		Log.i(TAG, "ChooseGameActivity created");

		onTimeButton = (Button) findViewById(R.id.on_time_button);
		onTimeButton.setOnClickListener(this);
		survivalButton = (Button) findViewById(R.id.survival_button);
		survivalButton.setOnClickListener(this);
		hardcoreButton = (Button) findViewById(R.id.hardcore_button);
		hardcoreButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent intent;
		Log.i(TAG, "The " + v.getId() + " was clicked");
		switch (v.getId()) {
		case R.id.on_time_button:
			Log.i(TAG, "OnTimeButton was clicked. Creating intent");
			intent = new Intent(this, GameActivity.class);
			Log.i(TAG, "Starting GameActivity");
			startActivity(intent);
			finish();
			break;
		case R.id.survival_button:
			Log.i(TAG, "SurvivalButton was clicked. Creating intent");
			intent = new Intent(this, GameActivity.class);
			Log.i(TAG, "Starting GameActivity");
			startActivity(intent);
			finish();
			break;
		case R.id.hardcore_button:
			Log.i(TAG, "HardcoreButton was clicked. Creating intent");
			intent = new Intent(this, GameActivity.class);
			Log.i(TAG, "Starting GameActivity");
			startActivity(intent);
			finish();
			break;
		}

	}
}
