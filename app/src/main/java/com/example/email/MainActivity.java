package com.example.email;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mRecipientEt, mSubjectEt, mMessageEt;
    Button mSendBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipientEt = findViewById(R.id.recipientEt);
        mSubjectEt = findViewById(R.id.subjectEt);
        mMessageEt = findViewById(R.id.messageEt);
        mSendBtn = findViewById(R.id.sendBtn);

        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipient = mRecipientEt.getText().toString().trim();   // trim  обрезка удалит пробел до и после текста
                String subject = mSubjectEt.getText().toString().trim();
                String message = mMessageEt.getText().toString().trim();


                //вызов метода для намерения электронной почты с этими входными данными в качестве параметров
                sendEmail(recipient, subject, message);
            }
        });
    }

    @SuppressLint("IntentReset")
    private void sendEmail(String recipient, String subject, String message) {
        Intent mEmailIntent = new Intent(Intent.ACTION_SEND);
        mEmailIntent.setData(Uri.parse("mailto"));
        mEmailIntent.setType("text/plain");

        mEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        mEmailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mEmailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(mEmailIntent, "Choose an Email Client"));

        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}