package com.github.AndroidGames.QuizGames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OptionsActivity extends Activity implements OnClickListener{
	
	Button resetStatsButton, helpButton, aboutButton;

	private static final String TAG = "QuizGame";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options_activity);
		Log.i(TAG, "OptionsActivity created");
		
		resetStatsButton = (Button) findViewById(R.id.resetStatsButton);
		resetStatsButton.setOnClickListener(this);
		helpButton = (Button) findViewById(R.id.helpButton);
		helpButton.setOnClickListener(this);
		aboutButton = (Button) findViewById(R.id.aboutButton);
		aboutButton.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		Intent intent;
		Log.i(TAG,"The " + v.getId() + " was clicked");
		switch (v.getId()){
		case R.id.helpButton:
			Log.i(TAG, "Help button was clicked. Creating intent");
			intent = new Intent(this, RulesActivity.class);
			Log.i(TAG, "Starting RulesActivity");
			startActivity(intent);
			break;
		}
	}

}
