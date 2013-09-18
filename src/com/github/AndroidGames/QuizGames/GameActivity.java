package com.github.AndroidGames.QuizGames;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class GameActivity extends Activity implements OnClickListener {
	public static final String TAG = "QuizGame";

	Button answerAButton;
	Button answerBButton;
	Button answerCButton;
	Button answerDButton;
	String rightAnswer = "";
	ImageView questionImage;
	HashSet<Integer> intSet;
	int n; // Total amount of questions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_activity);
		Log.i(TAG, "GameActivity created");

		answerAButton = (Button) findViewById(R.id.answer_a_button);
		answerAButton.setOnClickListener(this);
		answerBButton = (Button) findViewById(R.id.answer_b_button);
		answerBButton.setOnClickListener(this);
		answerCButton = (Button) findViewById(R.id.answer_c_button);
		answerCButton.setOnClickListener(this);
		answerDButton = (Button) findViewById(R.id.answer_d_button);
		answerDButton.setOnClickListener(this);
		questionImage = (ImageView) findViewById(R.id.question_image);

		getTotal();
		intSet = new HashSet<Integer>();
		putNextQuestion();
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
				endGame();//For a while!!! 
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
			// points++;
			putNextQuestion();
		} else {
			Log.i(TAG, "Answer is wrong! Going to EndGameActivity");
			endGame();
		}
	}

	public void endGame() {
		Intent intent = new Intent(this, EndGameActivity.class);
		startActivity(intent);
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
}
