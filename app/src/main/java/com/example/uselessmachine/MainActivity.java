package com.example.uselessmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonSD;
    private Switch switchUseless;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wiredWidgets();
        setLitseners();
    }

    public void wiredWidgets(){
        buttonSD = findViewById(R.id.button_self_destruct);
        switchUseless = findViewById(R.id.switch_useless);
    }

    private void setLitseners() {
        buttonSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "SDed", Toast.LENGTH_SHORT).show();
            }
        });
        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                   new CountDownTimer(2000, 1000)
                    {
                        @Override
                        public void onTick ( long l){
                        if(!switchUseless.isChecked())  {
                            cancel();
                        }
                    }

                        @Override
                        public void onFinish () {
                        switchUseless.setChecked(false);
                    }
                    }.start();
                }
            }

        });
    }

}
