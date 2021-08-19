package com.example.motorshop.activity.login;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;


public class VerifyActivity extends AppCompatActivity {

    EditText etOTP;
    ImageView imvDone;
    Button btnSubmit;

    AnimatedVectorDrawable avd;
    AnimatedVectorDrawableCompat avdc;
    private String codeBySystem;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        getExtras(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String code  = getIntent().getStringExtra("phone");
        sendOTPCode(code);

        setControl();
        setEvent();
    }

    public void getExtras(Bundle savedInstanceState) {
        if (savedInstanceState == null)
            extras = getIntent().getExtras();
        else
            extras = savedInstanceState;
    }

    public void sendExtras(Class className) {
        if(extras != null) {
            System.out.println(extras.getString("userType"));
            System.out.println(extras.getString("id"));
            Intent i = new Intent(getApplicationContext(), className);
            i.putExtra("userType", extras.getString("userType"));
            i.putExtra("id", extras.getString("id"));
            startActivity(i);
        }
    }

    private void setControl() {
        etOTP = (EditText) findViewById(R.id.etOTP);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        imvDone = (ImageView) findViewById(R.id.imvDone);
        imvDone.setVisibility(View.INVISIBLE);
    }

    private void setEvent() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                imvDone.setVisibility(v.VISIBLE);
                Drawable drawable = imvDone.getDrawable();
                if(drawable instanceof AnimatedVectorDrawableCompat) {
                    avdc = (AnimatedVectorDrawableCompat) drawable;
                    avdc.start();
                    String code = etOTP.getText().toString();
                    sendExtras(MainActivity.class);
//                    if(!code.isEmpty()) {
//                        verifiCode(code);
//                    }
                } else if(drawable instanceof AnimatedVectorDrawable) {
                    avd = (AnimatedVectorDrawable) drawable;
                    avd.start();
                    String code = etOTP.getText().toString();
                    sendExtras(MainActivity.class);
//                    if(!code.isEmpty()) {
//                        verifiCode(code);
//                    }
                }
            }
        });
    }

    public void sendOTPCode(String phone) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                        .setPhoneNumber(phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(@NonNull @NotNull String s, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if(code != null) {
                        etOTP.setText(code);
                        verifiCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
                    Toast.makeText(VerifyActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };

    private void verifiCode (String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(VerifyActivity.this, "Verification completed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(VerifyActivity.this, "Verification not complete! Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public boolean validOTP() {
        //...
        return true;
    }

}