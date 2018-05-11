package com.huitu.jzqdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.huitu.jzqdemo.R;

public class ZxingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button zxing_btn;
    private Button zxing_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        zxing_btn = (Button) findViewById(R.id.zxing_btn);
        zxing_btn.setOnClickListener(this);
        zxing_btn2 = (Button) findViewById(R.id.zxing_btn2);
        zxing_btn2.setOnClickListener(this);
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zxing_btn:
                IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setPrompt("Scan a barcode");
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;
            case R.id.zxing_btn2:
                new IntentIntegrator(this).setPrompt("将二维码置于取景框内,即可自动扫描").setCaptureActivity(ToolbarCaptureActivity.class).initiateScan();
                break;
        }
    }
}
