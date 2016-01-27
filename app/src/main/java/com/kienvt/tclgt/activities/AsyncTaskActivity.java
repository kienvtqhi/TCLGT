package com.kienvt.tclgt.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kienvt.tclgt.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AsyncTaskActivity extends AppCompatActivity {
    @Bind(R.id.tv_result) TextView tvResult;
    @Bind(R.id.btn_test) Button btnClickMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        ButterKnife.bind(this);

        new CountingAsyncTask().execute(5);
//        tvResult.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                int count = 0;
//                while (count != 5) {
//                    count ++;
//                    tvResult.setText(count + "");
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, 1000);
    }

    @OnClick(R.id.btn_test)
    public void onClickButtonClickMe() {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }

    public class CountingAsyncTask extends AsyncTask<Integer, Integer, String> {
        int  count = 0;

        @Override
        protected String doInBackground(Integer... params) {
            int destination = params[0];
            while (count != destination) {
                count ++;
                publishProgress(count);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "Hoàn thành";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int value = values[0];
            tvResult.setText(value + "");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvResult.setText(s);
        }
    }
}
