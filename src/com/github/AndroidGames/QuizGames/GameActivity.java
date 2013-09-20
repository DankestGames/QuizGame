package com.github.AndroidGames.QuizGames;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Path;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends Activity implements OnClickListener {
	public static final String TAG = "QuizGame";

	Button answerAButton;
	Button answerBButton;
	Button answerCButton;
	Button answerDButton;
	TextView timeText;
	TextView pointsText;
	String rightAnswer = "";
	ImageView questionImage;
	HashSet<Integer> intSet;
	CountDownTimer myTimer;
	private File textFile;
	boolean isTimerOn;
	int n; // Total amount of questions;
	int typeOfGame;
	int points;
	long millisTimerRemains;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_activity);
		Log.i(TAG, "GameActivity created");
		
		String state = Environment.getExternalStorageState();
		if (!state.equals(Environment.MEDIA_MOUNTED)) {
			Log.i(TAG, "No external storage mounted");
		} 
		else {
			Log.i(TAG, "File setting");
			File externalDir = Environment.getExternalStorageDirectory();
			textFile = new File(externalDir.getAbsolutePath()
					+ File.separator + "com.QuizGame.data" + File.separator
					+ "highscore.ini");
		}

		answerAButton = (Button) findViewById(R.id.answer_a_button);
		answerAButton.setOnClickListener(this);
		answerBButton = (Button) findViewById(R.id.answer_b_button);
		answerBButton.setOnClickListener(this);
		answerCButton = (Button) findViewById(R.id.answer_c_button);
		answerCButton.setOnClickListener(this);
		answerDButton = (Button) findViewById(R.id.answer_d_button);
		answerDButton.setOnClickListener(this);
		questionImage = (ImageView) findViewById(R.id.question_image);

		timeText = (TextView) findViewById(R.id.time_text);
		pointsText = (TextView) findViewById(R.id.points_text);
		points = 0;
		isTimerOn = false;
		Intent intent = getIntent();
		typeOfGame = intent.getIntExtra("Type", 0);
		millisTimerRemains = 0;
		tuneGameActivity();
		Log.i(TAG, "Type of the game is " + typeOfGame);
		getTotal();
		intSet = new HashSet<Integer>();
		putNextQuestion();
	}

	
	public void tuneGameActivity(){
		switch (typeOfGame){
		case 1:
			setTimer(60000); //60seconds;
			break;
		case 2:
			timeText.setText("");
			break;
		case 3:
			setTimer(20000); //20seconds;
			break;
		}
		pointsText.setText("0");
	}
	
	public void setTimer(long millisInFuture) {
		millisTimerRemains = millisInFuture;
		int countDownInterval = 1000;
		myTimer = new CountDownTimer(millisInFuture, countDownInterval) {
			
			public void onTick(long millisUntilFinished) {
				timeText.setText("" + millisUntilFinished / 1000);
				millisTimerRemains = millisUntilFinished;
				Log.i(TAG, "Still counting: " + millisUntilFinished / 1000);
				//TODO soundpool tic-tac
			}

			public void onFinish() {
				endGame();
				millisTimerRemains = 0;
			}
		}.start();
		isTimerOn = true;
	}
	public void getTotal() {
		AssetManager assetManager = getAssets();
		InputStream inputStream = null;
		try {
			inputStream = assetManager.open("texts/total.txt");
			Scanner scanner = new Scanner(inputStream);
			n = scanner.nextInt();
			scanner.close();
		} catch (IOException e) {
			Log.i(TAG, "Couldn't load file");
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					Log.i(TAG, "Couldn't close file");
				}
		}
		Log.i(TAG, "Total amount of questions is " + n);
	}

	public void putNextQuestion() {
		Random rand = new Random(30);
		int k = rand.nextInt(n) + 1;
		int i = 0;
		while (intSet.contains(k)) {
			k = rand.nextInt(n) + 1;
			i++;
			if (i == n) {
				Log.i(TAG, "No more questions :(");
				endGame();// For a while!!!
				break;
			}
		}
		Log.i(TAG, "Putting question number " + k);
		intSet.add(k);
		putQuestion(k);
	}

	public void putQuestion(int number) {
		String s = "questions/" + number + ".txt";
		AssetManager assetManager = getAssets();
		InputStream inputStream = null;
		try {
			inputStream = assetManager.open(s);
			Scanner scanner = new Scanner(inputStream);
			answerAButton.setText("A: " + scanner.nextLine());
			answerBButton.setText("B: " + scanner.nextLine());
			answerCButton.setText("C: " + scanner.nextLine());
			answerDButton.setText("D: " + scanner.nextLine());
			rightAnswer = scanner.nextLine();
			scanner.close();
		} catch (IOException e) {
			Log.i(TAG, "Couldn't load file");
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					Log.i(TAG, "Couldn't close file");
				}
		}
		s = "q_" + number;
		int resId = getResources().getIdentifier(s, "drawable",
				getPackageName());
		questionImage.setImageResource(resId);
	}

	public void checkAnswer(String answer) {
		Log.i(TAG, "Checking answer. Rigth is " + rightAnswer + "; Users is "
				+ answer);
		if (rightAnswer.equals(answer)) {
			Log.i(TAG, "Answer is rigth!");
			addPoints();
			putNextQuestion();
		} else {
			Log.i(TAG, "Answer is wrong! Going to EndGameActivity");
			endGame();
		}
	}

	public void addPoints(){
		//TODO good function to count points
		switch (typeOfGame){
		case 1:
			points++;
			break;
		case 2:
			points++;
			break;
		case 3:
			points++;
			break;
		}
		pointsText.setText("" + points);
	}
	
	
	public void endGame() {
										
		try {
			Log.i(TAG, "Writing stats");
			Scanner scanner = new Scanner(textFile);
			int totGamePlayed = Integer.parseInt(scanner.nextLine()) + 1;
			scanner.nextLine();
			int timeModeRecord = Integer.parseInt(scanner.nextLine());
			int survModeRecord = Integer.parseInt(scanner.nextLine());
			int hardModeRecord = Integer.parseInt(scanner.nextLine());
			
			
			switch (typeOfGame){
			case 1:
				if(timeModeRecord < points)
					timeModeRecord = points;
				break;
			case 2:
				if(survModeRecord < points)
					survModeRecord = points;
				break;
			case 3:
				if(hardModeRecord < points)
					hardModeRecord = points;
				break;
			}
			
			scanner.close();
			
			PrintWriter writer;
			writer = new PrintWriter(textFile, "UTF-8");
			writer.println(totGamePlayed+ "\n" + points + "\n" + timeModeRecord + "\n" + survModeRecord + "\n" + hardModeRecord);
			writer.close();			
		} catch (IOException e) {
	        textFile.getParentFile().mkdirs();
	        try {
	        	Log.i(TAG, "Creating stats");
				textFile.createNewFile();
				PrintWriter writer;
				writer = new PrintWriter(textFile, "UTF-8");
				int timeModeRecord = 0, survModeRecord = 0, hardModeRecord = 0;
				switch (typeOfGame){
				case 1:
					if(timeModeRecord < points)
						timeModeRecord = points;
					break;
				case 2:
					if(survModeRecord < points)
						survModeRecord = points;
					break;
				case 3:
					if(hardModeRecord < points)
						hardModeRecord = points;
					break;
				}
				writer.println("1\n" + points + "\n" + timeModeRecord + "\n" + survModeRecord + "\n" + hardModeRecord);
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	        
		
		Intent intent = new Intent(this, EndGameActivity.class);
		startActivity(intent);
		if(typeOfGame != 2) {
			myTimer.cancel();
			isTimerOn = false;
		}
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.answer_a_button:
			Log.i(TAG, "Answer A was clicked");
			checkAnswer("A");
			break;
		case R.id.answer_b_button:
			Log.i(TAG, "Answer B was clicked");
			checkAnswer("B");
			break;
		case R.id.answer_c_button:
			Log.i(TAG, "Answer C was clicked");
			checkAnswer("C");
			break;
		case R.id.answer_d_button:
			Log.i(TAG, "Answer D was clicked");
			checkAnswer("D");
			break;

		}

	}
	
	@Override
	protected void onPause(){
		super.onPause();
		if (typeOfGame != 2) myTimer.cancel();
		isTimerOn = false;
	} 
	@Override 
	protected void onResume(){
		super.onResume();
		if (typeOfGame != 2 && !isTimerOn){
			setTimer(millisTimerRemains);
			
		}
	}
}
