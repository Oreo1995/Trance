package com.trance.multi.trance.recyclerview;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.trance.multi.trance.R;

public class ProgressDialogActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dialog);
        Button bt = findViewById(R.id.show);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show:
                showProgressDialog();
                break;

            default:
                break;
        }
    }

    public void showProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setTitle("Trance");
        progressDialog.setMessage("加载中");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
}
