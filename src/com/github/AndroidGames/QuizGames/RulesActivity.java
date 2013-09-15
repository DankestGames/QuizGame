package com.github.AndroidGames.QuizGames;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class RulesActivity extends Activity {
	public static final String TAG = "QuizGame";
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rules_activity);
		Log.i(TAG, "RulesActivity created");
		
		textView = (TextView) findViewById(R.id.rules_text);
		AssetManager assetManager = getAssets();
		InputStream inputStream = null;
		try {
			inputStream = assetManager.open("texts/rules.txt");
			Scanner scanner = new Scanner(inputStream);
			String text = "";
			while (scanner.hasNext()){
				text += scanner.nextLine() + "\n";
			}
			scanner.close();
			textView.setText(text);
		} catch (IOException e){
			Log.i(TAG,"Couldn't load file");
		} finally {
			if (inputStream != null)
				try{
					inputStream.close();
				} catch(IOException e) {
					Log.i(TAG,"Couldn't close file");
				}
		}
	}
}
