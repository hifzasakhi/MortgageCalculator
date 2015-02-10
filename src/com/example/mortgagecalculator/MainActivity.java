package com.example.mortgagecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Copyright (C) 2013 Code Here Now - A subsidiary of Mobs & Geeks
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file 
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions and 
 * limitations under the License.
 */


public class MainActivity extends Activity implements OnClickListener, OnSeekBarChangeListener {

	private TextView message;
	private TextView principal_amt;
	private TextView interest_rate;
	private TextView time;
	private Button calculate;
	private EditText principal;
	private EditText interest;
	private SeekBar bar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		principal_amt = (TextView)findViewById(R.id.text_principal);
		message = (TextView)findViewById(R.id.message);
		message.setText("");
		interest_rate = (TextView)findViewById(R.id.text_interest);
		time = (TextView)findViewById(R.id.text_time);
		interest = (EditText)findViewById(R.id.edit_interest);
		principal = (EditText)findViewById(R.id.edit_principal);
		calculate = (Button)findViewById(R.id.calculate);
		calculate.setOnClickListener(this);
		
		bar = (SeekBar)findViewById(R.id.seekBar);
		bar.setOnSeekBarChangeListener(this);
	}
	

	@Override
	public void onClick(View v) {
		if (v == calculate) {
			double rate = Double.parseDouble(interest.getText().toString());
			double prin = Double.parseDouble(principal.getText().toString());
			
			//divide annual rate by 12 to get monthly rate
			//divided annual rate by 100 to get rate as a percentage
			double monthly_rate = rate/(12*100); 
			int payment_periods = bar.getProgress() * 12;
			double answer = calculate(prin, monthly_rate, payment_periods);
			String displayed_text = "Monthly payments for a $" + principal.getText() + 
									" home at a rate of " + interest.getText() + 
									"% over "  + bar.getProgress() + " years is $" + answer;
			message.setText(displayed_text);
			System.out.println("displayed the text");
		}
	}
	
	
	private double calculate(double prin, double monthly_interest_rate, int payments) {
		
		double b = monthly_interest_rate * Math.pow(1 + monthly_interest_rate, payments);
		b /= (Math.pow(1 + monthly_interest_rate, payments) - 1);
		return Math.round(prin * b);
		
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
								  boolean fromUser) {
		time.setText(bar.getProgress()  + "  Years");
		
	}


	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

}
