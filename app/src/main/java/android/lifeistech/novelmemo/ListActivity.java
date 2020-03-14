package android.lifeistech.novelmemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
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

            setContentView(R.layout.activity_list);

            TextView textView = (TextView) findViewById(R.id.editText);
            registerForContextMenu(textView);

        add.setOnClickListener(new OnClickListener(){
            private void add(OnClickListener){
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.listview1_delete:
                Toast.makeText(this, "削除されました", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


}




