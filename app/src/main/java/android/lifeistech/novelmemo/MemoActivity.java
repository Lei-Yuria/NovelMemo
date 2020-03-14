package android.lifeistech.novelmemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MemoActivity extends AppCompatActivity {

    EditText titleEditText;
    EditText contentEditText;
    TextView textView;
    String DATA = "data";

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        titleEditText = (EditText) findViewById(R.id.title);
        contentEditText = (EditText) findViewById(R.id.content);
        textView = findViewById(R.id.textView);

        pref = getSharedPreferences("pref_memo",MODE_PRIVATE);

        Intent intent = getIntent();
        String data = intent.getStringExtra(DATA);
        textView.setText(data);


        titleEditText.setText(pref.getString("key_title",""));
        contentEditText.setText(pref.getString("key_content",""));
    }

    public void save(View v) {

        Toast.makeText(getApplicationContext(),"保存しました",Toast.LENGTH_SHORT).show();


        String titleText = titleEditText.getText().toString();
        String contentText = contentEditText.getText().toString();

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("key_title",titleText);
        editor.putString("key_content",contentText);
        editor.commit();

        finish();

    }
}
