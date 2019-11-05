package com.example.calarea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn = null;
    private EditText etInputX = null, etInputY = null;
    private TextView tvShowInfo = null;
    private String strInputX = "", strInputY = "";
    private String strOutput = "";
    private RadioButton rBtnC = null, rBtnR = null;
    private RadioGroup rgType = null;

    private double fRadius = 0.0, fRadiusX = 0.0, fRadiusY = 0.0;
    private double fArea = 0.0;
    private int radioId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        etInputX = findViewById(R.id.editTextX);
        etInputY = findViewById(R.id.editTextY);
        tvShowInfo = findViewById(R.id.result);
        rBtnC = findViewById(R.id.radioButtonCir);
        rBtnR = findViewById(R.id.radioButtonRec);
        rgType = findViewById(R.id.radioGroupType);
        rgType.setOnCheckedChangeListener(listen);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioId = rgType.getCheckedRadioButtonId();
                if (radioId == rBtnC.getId()){
                    if (rBtnC.isChecked()){
                        strInputX = etInputX.getText().toString();
                        fRadius = Double.valueOf(strInputX);

                        if (fRadius > 0) {
                            fArea = Math.PI * fRadius * fRadius;
                            strOutput = "The area is " + fArea + "with radius is " + fRadius;
                            tvShowInfo.setText(strOutput);
                        } else if(strOutput == null) {
                            tvShowInfo.setText("You didn't input any value!");
                        }else {
                            tvShowInfo.setText("Invalid value of radius!");
                        }
                    }
                } else if(radioId == rBtnR.getId()){
                    if(rBtnR.isChecked()){
                        strInputX = etInputX.getText().toString();
                        strInputY = etInputY.getText().toString();
                        fRadiusX = Double.valueOf(strInputX);
                        fRadiusY = Double.valueOf(strInputY);

                        if (fRadiusX > 0 && fRadiusY > 0){
                            fArea = fRadiusX * fRadiusY;
                            strOutput = "The area is " + fArea + " with X is " + fRadiusX + " and Y is " + fRadiusY;
                            tvShowInfo.setText(strOutput);
                        } else {
                            tvShowInfo.setText("Invalid values!");
                        }
                    }
                }
            }
        });


    }

    private RadioGroup.OnCheckedChangeListener listen = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int id = group.getCheckedRadioButtonId();

            switch (group.getCheckedRadioButtonId()){
                case R.id.radioButtonCir:
                    etInputY.setVisibility(View.INVISIBLE);
                    etInputX.setHint("Please input radius");
                    etInputX.setText("");
                    break;

                case R.id.radioButtonRec:
                    etInputY.setVisibility(View.VISIBLE);
                    etInputY.setHint("Please input Y");
                    etInputX.setText("");
                    etInputX.setFocusable(true);
                    etInputX.setHint("Please input X");
                    break;

                default:
                    etInputX.setVisibility(View.VISIBLE);
                    etInputY.setVisibility(View.INVISIBLE);
                    etInputX.setHint("Please input radius");
                    break;

            }
        }
    };
}
