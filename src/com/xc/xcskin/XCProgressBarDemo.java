package com.xc.xcskin;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.xc.xcskin.view.XCProgressBar;

public class XCProgressBarDemo extends Activity {

	XCProgressBar progressBar ;

	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xc_progressbar_demo);
		progressBar = (XCProgressBar) findViewById(R.id.roundProgressBar);

		progressBar.setMax(100);
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
			progressBar.setProgress(100);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			progressBar.setProgress((int)(values[0]));

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
