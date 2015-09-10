package com.xc.xcskin;

import com.xc.xcskin.view.XCRoundProgressBar;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class XCRoundProgressBarDemo extends Activity {

	XCRoundProgressBar roundProgressBar ;

	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xc_round_progressbar_demo);
		roundProgressBar = (XCRoundProgressBar) findViewById(R.id.roundProgressBar);

		roundProgressBar.setMax(100);
		button = (Button)findViewById(R.id.btn);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new MyAsyncTask().execute(0);
			}
		});
		
		new MyAsyncTask().execute(0);
		
	}
	
	private class MyAsyncTask extends AsyncTask<Integer, Integer, Integer>{

		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			roundProgressBar.setProgress(100);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			roundProgressBar.setProgress((int)(values[0]));

		}

		@Override
		protected Integer doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			Integer timer = 0;
			while(timer <=100){
				try {
					publishProgress(timer);
					timer ++;
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		 
	}

	

}
