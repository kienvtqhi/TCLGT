package com.kienvt.tclgt.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kienvt.tclgt.R;
import com.kienvt.tclgt.adapter.OffencesAdapter;
import com.kienvt.tclgt.models.DataSource;
import com.kienvt.tclgt.models.MOffences;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private OffencesAdapter offencesAdapter;
    private List<MOffences> offences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        offences = DataSource.getOffencesSample();
        offencesAdapter = new OffencesAdapter(this, offences);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(offencesAdapter);
    }
}