package com.example.ismart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.tomer.fadingtextview.FadingTextView;

public class SplashScreen extends AppCompatActivity {
    private static boolean FLAG;
    private static final String TAG = "PhoneAuthActivity";
    private boolean first_inst , USER_IN_FIREBASE;
    private long DELAY;

    FadingTextView fadingTextView;
    String[] text
            = { "Make your vehicle", "Safe",
            "Smart","Intelligent","Let's start.." };
    String[] text1
            = { "We are back","Please wait"};
    public static RelativeLayout relativeLayout;

    private FirebaseAuth mAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        fadingTextView = findViewById(R.id.fadingTextView);
        fadingTextView.setTexts(text);
        SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        first_inst = sharedPreferences.getBoolean("first time", true);
        if(first_inst) {
            FLAG = true;
            DELAY = 9100;
        }
        else{
          FLAG = false;
          fadingTextView.setTexts(text1);
          DELAY = 3600;
        }
        new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (FLAG) {
                        Intent Onboard = new Intent(SplashScreen.this, OnboardActivity.class);
                        startActivity(Onboard);
                        finish();
                    } else {
                        if (currentUser != null) {
                            Intent homepage = new Intent(SplashScreen.this, home_pag.class);
                            startActivity(homepage);
                            finish();
                        } else {
                            Intent sp = new Intent(SplashScreen.this, sign_up.class);
                            startActivity(sp);
                            finish();
                        }
                    }
                    SharedPreferences sharedPreferences1 = getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putBoolean("first time", false);
                    editor.apply();
                }
            }, DELAY);
    }
}