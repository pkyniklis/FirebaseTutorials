package com.pkyniklis.android.firebasetutorials;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button mFirebaseButton;
    private DatabaseReference mDatabase;
    private EditText mNameField;
    private EditText mEmailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseButton = findViewById(R.id.firebase_btn);
        mNameField = findViewById(R.id.name_field);
        mEmailField = findViewById(R.id.email_field);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mFirebaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameField.getText().toString().trim();
                String email = mEmailField.getText().toString().trim();
                mNameField.setText("");
                mEmailField.setText("");

                Map<String, String> dataMap = new HashMap<>();
                dataMap.put("Name", name);
                dataMap.put("Email", email);

                //mDatabase.child("Name").setValue(name);
                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Stored..", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error..", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
