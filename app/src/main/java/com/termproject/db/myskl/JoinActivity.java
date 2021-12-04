package com.termproject.db.myskl;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class JoinActivity extends AppCompatActivity {

    private TextInputLayout layoutName, layoutEmail, layoutPw, layoutPwck;
    private EditText name, email, pw, pwck;
    private String dbEm = "";
    private boolean emCheck = false;  //이메일 중복 확인 여부
    private boolean noError = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        layoutName = findViewById(R.id.layout_name);
        layoutEmail = findViewById(R.id.layout_email);
        layoutPw = findViewById(R.id.layout_pw);
        layoutPwck = findViewById(R.id.layout_pwck);

        // 화면에 입력받은 값 가져오기
        name = layoutName.getEditText();
        email = layoutEmail.getEditText();
        pw = layoutPw.getEditText();
        pwck = layoutPwck.getEditText();

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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

        pwck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals(pw.toString())) {

                }
            }
        });

        // 회원가입 버튼 동작
        Button joinB = findViewById(R.id.joinB_button);
        joinB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 모든 입력의 오류가 없으면 true
                noError = (layoutName.getError()==null && layoutEmail.getError()==null
                        && layoutPw.getError()==null && layoutPwck.getError()==null);

                // 이메일 중복 확인
                if (db != null) {   // 이 if문 빼보기.
                    Cursor cursor = db.rawQuery("SELECT ID FROM INFO", null);
                    while (cursor.moveToNext()) {
                        dbEm = cursor.getString(0);
                        if (email.toString().equals(dbEm)) {    //중복 존재
                            emCheck = false;
                            Toast.makeText(getApplicationContext(), "이미 가입된 이메일입니다.", Toast.LENGTH_SHORT).show();

                            break;
                        } else {    // 중복 없음
                            emCheck = true;
                        }
                    }
                }else {
                    emCheck = true;
                }

                // 이메일 중복x 오류x 시 데이터 저장, 로그인 화면으로 이동
                if (emCheck && noError) {
                    db.execSQL("INSERT INTO INFO VALUES ('" + email.toString() + "', '" + pw.toString()
                                                            + "', '" + name.toString() + "');");
                    name.setText("");
                    email.setText("");
                    pw.setText("");
      // 회원가입 완료됐다는 toast 나 dialog 구현하기
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);
                }


            }
        });

        /*public void join (View v){
            String name = join_name.getText().toString();
            String id = join_email.getText().toString();
            String pw = join_password.getText().toString();
            String pwck = join_pwck.getText().toString();

            if (pw.equals(pwck)) {

                db.execSQL("INSERT INTO INFO VALUES ('" + id + "', '" + pw + "', '" + name + "');");

                join_name.setText("");
                join_email.setText("");
                join_password.setText("");

            } else {

            }
        }*/

        // 중복확인은 회원가입 누르면 실행되는 걸로 하자.
        // 이메일 중복 확인 (완료: idCheck=true)
        /*Button check = findViewById(R.id.join_button);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = join_email.getText().toString();
                String dbId = "";

                Cursor cursor = db.rawQuery("SELECT ID FROM INFO", null);  //가입한 이메일들
                while (cursor.moveToNext()) {
                    dbId = cursor.getString(0);
                }

                if(id.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "이메일을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else if(id.matches("(.*)@(.*)") == false) {
                    Toast.makeText(getApplicationContext(), "이메일을 바르게 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    if(id.equals(dbId)) {
                        Toast.makeText(getApplicationContext(), "이미 가입된 이메일입니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        idCheck = true;
                        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });*/

        // 취소 (로그인 화면으로 돌아감)
        Button cancel = findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });

    }
}