package com.project250.applock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.project250.applock.passStrings.password;
import com.shuhart.stepview.StepView;

import java.util.List;

public class Pattern_Lock extends AppCompatActivity {

    StepView stepView;
    LinearLayout linearLayout;
    RelativeLayout relativeLayout;
    password Mpassword;
    String userPass;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_lock);

        stepView = findViewById(R.id.stepView);
        linearLayout = findViewById(R.id.LL);
        relativeLayout = findViewById(R.id.main_layout);
        Mpassword = new password(this);
        textView = findViewById(R.id.state_text);
        textView.setText(Mpassword.FIRST_USE);
        if(Mpassword.getPASS_KEY()==null){
            linearLayout.setVisibility(View.GONE);
            stepView.setVisibility(View.VISIBLE);
            stepView.setStepsNumber(2);
            stepView.go(0,true);
        }
        else{
            linearLayout.setVisibility(View.VISIBLE);
            stepView.setVisibility(View.GONE);
            int BackgroundColor = ResourcesCompat.getColor(getResources(),R.color.BLUE,null);
            relativeLayout.setBackgroundColor(BackgroundColor);
            textView.setTextColor(Color.WHITE);
        }
        setUpPatternListener();
    }

    private void setUpPatternListener() {
        final PatternLockView patternLockView = findViewById(R.id.patternView);
        patternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                String pass = PatternLockUtils.patternToString(patternLockView, pattern);
                if(pass.length()<4){
                    textView.setText(Mpassword.SCHEMA);
                    patternLockView.clearPattern();
                    return;
                }
                if(Mpassword.getPASS_KEY()==null){
                    if(Mpassword.isFirst){
                        userPass = pass;
                        Mpassword.setFirst(false);
                        textView.setText(Mpassword.CONFIRM);
                        stepView.go(1,true);
                    }
                    else{
                        if(userPass.equals(pass)){
                            Mpassword.setPASS_KEY(pass);
                            textView.setText(Mpassword.PATTERN);
                            stepView.done(true);
                            goToMainActivity();
                        }
                        else{
                            textView.setText(Mpassword.PATTERN);
                        }
                    }
                }
                else{
                    if(Mpassword.isCorrect(pass)){
                        textView.setText(Mpassword.PATTERN);
                        goToMainActivity();
                    }
                    else{
                        textView.setText(Mpassword.INCORRECT);
                    }
                }
                patternLockView.clearPattern();
            }

            @Override
            public void onCleared() {

            }
        });
    }

    private void goToMainActivity() {
        Intent i = new Intent(Pattern_Lock.this,MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        if(Mpassword.getPASS_KEY()==null && !Mpassword.isFirst){
            stepView.go(0,true);
            Mpassword.setFirst(true);
            textView.setText(Mpassword.FIRST_USE);
        }
        else {
            super.onBackPressed();
        }
    }
}