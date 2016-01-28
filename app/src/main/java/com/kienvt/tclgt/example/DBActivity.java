package com.kienvt.tclgt.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.kienvt.tclgt.R;
import com.kienvt.tclgt.activities.HomeActivity;
import com.kienvt.tclgt.models.MOffence;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DBActivity extends AppCompatActivity {
    @Bind(R.id.ed_detail) EditText edDetail;
    @Bind(R.id.ed_money) EditText edMoney;
    @Bind(R.id.ed_info) EditText edInfo;
    @Bind(R.id.tv_count) TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_add)
    public void onClickButtonAdd() {
        MOffence offence = new MOffence();
//        offence.detail = edDetail.getText().toString();
//        offence.money = edMoney.getText().toString();
//        offence.info = edInfo.getText().toString();

        offence.save();

        List<MOffence> offences = new Select().from(MOffence.class).execute();

        int count = 0;
        if (offences != null)
            count = offences.size();

        tvCount.setText("Count: " + count);
    }

    @OnClick(R.id.btn_show_all)
    public void onClickButtonShowAll() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
