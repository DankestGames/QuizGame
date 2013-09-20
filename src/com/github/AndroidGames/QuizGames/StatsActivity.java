package com.github.AndroidGames.QuizGames;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class StatsActivity extends Activity implements OnClickListener{

	private Button resetStatsButton;
	private TextView totGamesText, lastScoreText, timeRecordText, survRecordText, hardRecordText;
	private String value;
	private static final String TAG = "QuizGame";
	private File textFile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stats_activity);
		Log.i(TAG, "StatsActivity created");
		
		resetStatsButton = (Button) findViewById(R.id.resetStatsButton);
		resetStatsButton.setOnClickListener(this);
		totGamesText = (TextView) findViewById(R.id.totGamesPlayedText);
		lastScoreText = (TextView) findViewById(R.id.lastScoretext);
		timeRecordText = (TextView) findViewById(R.id.timeRecordText);
		survRecordText = (TextView) findViewById(R.id.survRecordText);
		hardRecordText = (TextView) findViewById(R.id.hardRecordText);

		String state = Environment.getExternalStorageState();
		if (!state.equals(Environment.MEDIA_MOUNTED)) {
			Log.i(TAG, "No external storage mounted");
		} 
		else {
			File externalDir = Environment.getExternalStorageDirectory();
			textFile = new File(externalDir.getAbsolutePath()
					+ File.separator + "com.QuizGame.data" + File.separator
					+ "highscore.ini");

			
			try {
				Scanner scanner = new Scanner(textFile);
				Log.i(TAG, "reading stats");
				
				value = scanner.nextLine();
				totGamesText.setText(value);
				value = scanner.nextLine();
				lastScoreText.setText(value);
				value = scanner.nextLine();
				timeRecordText.setText(value);
				value = scanner.nextLine();
				survRecordText.setText(value);
				value = scanner.nextLine();
				hardRecordText.setText(value);
				scanner.close();				
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
	}


	@Override
	public void onClick(View v) {
		Log.i(TAG,"The " + v.getId() + " was clicked");
		switch (v.getId()){
		case R.id.resetStatsButton:
			Log.i(TAG, "Reset stats button was clicked.");
			PrintWriter writer;
			try {
				writer = new PrintWriter(textFile, "UTF-8");
				writer.println("0\n0\n0\n0\n0");
				writer.close();
				
				Scanner scanner = new Scanner(textFile);
				
				value = scanner.nextLine();
				totGamesText.setText(value);
				value = scanner.nextLine();
				lastScoreText.setText(value);
				value = scanner.nextLine();
				timeRecordText.setText(value);
				value = scanner.nextLine();
				survRecordText.setText(value);
				value = scanner.nextLine();
				hardRecordText.setText(value);
				scanner.close();	
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			Toast.makeText(this, "Stats were destroyed!", Toast.LENGTH_LONG).show();
			break;
		}
		
	}
}
