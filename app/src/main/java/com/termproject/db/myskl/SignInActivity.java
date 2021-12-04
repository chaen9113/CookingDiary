package com.termproject.db.myskl;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class SignInActivity extends AppCompatActivity {
    private TextInputLayout layoutEmail, layoutPw;
    private EditText email, pw;
//    private DBHelper dbHelper;
    private boolean noError = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        /*dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();*/

        // 화면에 입력받은 값 가져오기
        layoutEmail = findViewById(R.id.layout_emailA);
        layoutPw = findViewById(R.id.layout_pwA);
        email = layoutEmail.getEditText();
        pw = layoutPw.getEditText();

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty() || !s.toString().matches("(.*)@(.*)+\\.(.*)")) {
                    layoutEmail.setError("올바른 이메일을 입력해주세요.");
                } else {
                    layoutEmail.setError(null);
                }
            }
        });

        pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // 회원가입 버튼 (회원가입 화면으로 이동)
        Button cancel = findViewById(R.id.joinA_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
    }
}
