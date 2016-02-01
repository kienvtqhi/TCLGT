package com.kienvt.tclgt.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.activeandroid.query.Select;
import com.kienvt.tclgt.R;
import com.kienvt.tclgt.adapter.OffencesAdapter;
import com.kienvt.tclgt.models.DataSource;
import com.kienvt.tclgt.models.MCategory;
import com.kienvt.tclgt.models.MOffence;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private OffencesAdapter offencesAdapter;
    private List<MOffence> offences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        offences = new ArrayList<>();
        offencesAdapter = new OffencesAdapter(this, offences);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(offencesAdapter);

        new LoadOffencesAsyncTask().execute();

        int countCategories = new Select().from(MCategory.class).execute().size();
        Log.e("HomeActivity", "Categories: " + countCategories);
    }

    public class LoadOffencesAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            List<MOffence> result = new Select().from(MOffence.class).execute();

            if (result != null)
                offences.addAll(result);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            offencesAdapter.notifyDataSetChanged();
        }
    }
}
