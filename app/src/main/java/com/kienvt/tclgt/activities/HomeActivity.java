package com.kienvt.tclgt.activities;

import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.kienvt.tclgt.R;
import com.kienvt.tclgt.adapter.OffencesAdapter;
import com.kienvt.tclgt.models.MCategory;
import com.kienvt.tclgt.models.MOffence;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.spinner) Spinner spinner;
    @Bind(R.id.navigation) NavigationView navigationView;
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;

    private OffencesAdapter offencesAdapter;
    private List<MOffence> mOffences;

    private List<String> categoryNames;
    private ArrayAdapter<String> spinnerAdapter;
    private List<MCategory> categories;
    private List<MCategory> vihicleTypes;
    private int selectedVihicleTypePos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        mOffences = new ArrayList<>();
        offencesAdapter = new OffencesAdapter(this, mOffences);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(offencesAdapter);

        new LoadOffencesAsyncTask().execute();

        int countCategories = new Select().from(MCategory.class).execute().size();
        Log.e("HomeActivity", "Categories: " + countCategories);

        // Init adapter
        categoryNames = new ArrayList<>();
        vihicleTypes = new Select().from(MCategory.class).limit(6).execute();

        spinnerAdapter = new ArrayAdapter<String>(this, R.layout.toolbar_spinner_item_actionbar, categoryNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadOfferenceOfCategoryAtIndex(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.motobike:
                        selectedVihicleTypePos = 0;
                        break;
                    case R.id.car:
                        selectedVihicleTypePos = 1;
                        break;
                    case R.id.tractor:
                        selectedVihicleTypePos = 2;
                        break;
                    case R.id.bicycle:
                        selectedVihicleTypePos = 3;
                        break;
                    case R.id.passenger_transport:
                        selectedVihicleTypePos = 4;
                        break;
                    case R.id.special_case:
                        selectedVihicleTypePos = 5;
                        break;
                }

                item.setChecked(true);
                drawerLayout.closeDrawers();
                loadOffenceOfVihicleType();
                return true;
            }
        });

        selectedVihicleTypePos = 0;
        navigationView.getMenu().getItem(selectedVihicleTypePos).setChecked(true);
        loadOffenceOfVihicleType();
    }

    private void loadOffenceOfVihicleType() {
        MCategory vihicleTypeCategory = vihicleTypes.get(selectedVihicleTypePos);
        categories = new Select().from(MCategory.class).where("parent_id = ?", vihicleTypeCategory.categoryId).execute();
        categoryNames.clear();

        for (MCategory category : categories) {
            categoryNames.add(category.name);
        }

        spinnerAdapter.notifyDataSetChanged();
        spinner.setSelection(0);
    }

    private void loadOfferenceOfCategoryAtIndex(int position) {
        MCategory category = categories.get(position);
        Toast.makeText(this, category.name, Toast.LENGTH_SHORT).show();

        List<MOffence> offences = new Select().from(MOffence.class).where("category_id = ?", category.categoryId).execute();
        mOffences.clear();

        if (offences != null)
            mOffences.addAll(offences);

        offencesAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(0);
    }

    public class LoadOffencesAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            List<MOffence> result = new Select().from(MOffence.class).execute();

            if (result != null)
                mOffences.addAll(result);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            offencesAdapter.notifyDataSetChanged();
        }
    }
}
