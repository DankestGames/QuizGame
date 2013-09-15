package com.github.AndroidGames.QuizGames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class OptionsActivity extends Activity implements OnClickListener{

	private static final String TAG = "QuizGame";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options_activity);
		Log.i(TAG, "OptionsActivity created");
	}
	
	@Override
	public void onClick(View v) {
		Intent intent;
		Log.i(TAG,"The " + v.getId() + " was clicked");
		switch (v.getId()){
		case R.id.helpButton:
			Log.i(TAG, "Options button was clicked. Creating intent");
			intent = new Intent(this, OptionsActivity.class);
			Log.i(TAG, "Starting OptionsActivity");
			startActivity(intent);
			break;
		}
	}

}
