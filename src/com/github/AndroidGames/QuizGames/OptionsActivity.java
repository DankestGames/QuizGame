package com.github.AndroidGames.QuizGames;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class OptionsActivity extends Activity implements OnClickListener,  OnSeekBarChangeListener{
	
	Button resetStatsButton, helpButton, aboutButton;
	SeekBar volumeSeekBar;
	RadioButton soundRadioButton;
	
	AudioManager audioManager;

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
		
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		
		int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        
		volumeSeekBar = (SeekBar) findViewById(R.id.volumeSeekBar);
        volumeSeekBar.setMax(maxVolume);
        volumeSeekBar.setProgress(curVolume);         
        volumeSeekBar.setOnSeekBarChangeListener(this);
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

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Автоматически созданная заглушка метода
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Автоматически созданная заглушка метода
		
	}

}