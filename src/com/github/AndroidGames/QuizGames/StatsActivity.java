package com.github.AndroidGames.QuizGames;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class StatsActivity extends Activity {

	private static final String TAG = "QuizGame";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stats_activity);
		Log.i(TAG, "StatsActivity created");

//		statsButton = (Button) findViewById(R.id.statsButton);
//		statsButton.setOnClickListener(this);
//		helpButton = (Button) findViewById(R.id.helpButton);
//		helpButton.setOnClickListener(this);
//		aboutButton = (Button) findViewById(R.id.aboutButton);
//		aboutButton.setOnClickListener(this);
//		
//		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//		
//		int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//        
//		volumeSeekBar = (SeekBar) findViewById(R.id.volumeSeekBar);
//        volumeSeekBar.setMax(maxVolume);
//        volumeSeekBar.setProgress(curVolume);         
//        volumeSeekBar.setOnSeekBarChangeListener(this);
//        
//        soundCheckBox = (CheckBox) findViewById(R.id.soundCheckBox);
//        soundCheckBox.setOnClickListener(this);
	}

}
