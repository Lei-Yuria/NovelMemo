package android.lifeistech.novelmemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {
    Button add;
    ListView list;
    EditText editText;
    ArrayAdapter adapter;
    SharedPreferences pref;

    String DATA = "data";
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        list = (ListView) findViewById(R.id.list);
        add = (Button) findViewById(R.id.add);
        editText = (EditText) findViewById(R.id.editText);
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1);

        pref = getSharedPreferences("pref_章", MODE_PRIVATE);

        adapter.add(pref.getString("key_章", ""));

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                ArrayAdapter adapter = (ArrayAdapter) list.getAdapter();

                String item = (String) adapter.getItem(position);
                Intent intent = new Intent(view.getContext(), MemoActivity.class);
                intent.putExtra(DATA, item);

                view.getContext().startActivity(intent);
            }
        });


        TextView textView = (TextView) findViewById(R.id.editText);
        registerForContextMenu(textView);

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = editText.getText().toString();

                adapter.add(text);
                editText.setText("");


                SharedPreferences.Editor editor = pref.edit();
                editor.putString("pref_章", text);
                editor.commit();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItem1:
                Toast.makeText(getApplicationContext(),"削除されました",Toast.LENGTH_SHORT);
                return true;

            case R.id.menuItem2:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}






