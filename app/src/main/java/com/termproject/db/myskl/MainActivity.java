package com.termproject.db.myskl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<String> items = Arrays.asList("susi 이재명 일식", "stake 이재연 양식", "kimchi stew 이시연 한식");
    // 여기서 텍스트뷰 대신에 데이터베이스를 끌고와서 리스트뷰나 텍스트뷰로 바꿔줘야하는데 찾아보는데도 잘 모르겠다..

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView searchView = findViewById(R.id.search_view);
        TextView resultTextView = findViewById(R.id.textView);
        resultTextView.setText(getResult());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {    //눌렀을때 호출되는 부분
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {  // 글자를 칠때마다 실시간으로 변하는 부분
                resultTextView.setText(search(newText));
                return true;
            }
        });
    }

    private String search(String query) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            if (item.toLowerCase().contains(query.toLowerCase())){
                sb.append(item);
                if ( i != items.size() - 1) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    private String getResult() {        //결과를 만드는 함수
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            sb.append(item);
            if (i != items.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}