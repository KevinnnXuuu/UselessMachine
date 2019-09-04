package com.example.uselessmachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button buttonSD;
    private Switch switchUseless;
    private ConstraintLayout constraintLayout;
    private Button buttonLB;
    private ProgressBar progressBarLoading;
    private TextView loadingText;

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
        constraintLayout = findViewById(R.id.constraint_layout_main);
        buttonLB = findViewById(R.id.button_look_busy);
        progressBarLoading = findViewById(R.id.progress_bar_moving);
        loadingText = findViewById(R.id.text_loading);
    }

    private void setLitseners() {
        // set the onclick listener for the self destruct button
        // make a 10 second countdown timer
        // display how much time is left on the countdown on the button
        //when the time is complete, call the finnish() method to close
        buttonSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            new CountDownTimer(10000, 100) {
                @Override
                public void onTick(long l) {
                    buttonSD.setText(String.valueOf(l/1000));
                    if(l>5000 && (l/1000)%2 == 0) {
                        int color = Color.rgb(255, 100, 100);
                        constraintLayout.setBackgroundColor(color);
                    }
                    else if(l>5000 && (l/1000)%2 == 1) {
                        int color = Color.rgb(255, 255, 255);
                        constraintLayout.setBackgroundColor(color);
                    }
                    else if(l<=5000 && (l/100)%5 == 0) {
                        int color = Color.rgb(255, 0, 0);
                        constraintLayout.setBackgroundColor(color);
                    }
                    else {
                        int color = Color.rgb(255, 255, 255);
                        constraintLayout.setBackgroundColor(color);
                    }

                }

                @Override
                public void onFinish() {
                    finish();
                }
            }.start();

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
        buttonLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBarLoading.setVisibility(View.VISIBLE);
                loadingText.setVisibility(View.VISIBLE);
                switchUseless.setVisibility(View.INVISIBLE);
                buttonSD.setVisibility(View.INVISIBLE);
                buttonLB.setVisibility(View.INVISIBLE);
                new CountDownTimer(5000, 1) {
                    @Override
                    public void onTick(long l) {
                        progressBarLoading.setProgress((int)((5000-l)/50));
                        loadingText.setText("Saving Planets: " + l +" remaining");
                    }
                    @Override
                    public void onFinish() {
                        progressBarLoading.setVisibility(View.INVISIBLE);
                        loadingText.setVisibility(View.INVISIBLE);
                        switchUseless.setVisibility(View.VISIBLE);
                        buttonSD.setVisibility(View.VISIBLE);
                        buttonLB.setVisibility(View.VISIBLE);
                    }
                }.start();
             }
        });

    }


}
