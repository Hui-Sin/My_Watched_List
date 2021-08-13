package sg.edu.rp.c346.id20018354.mywatchedlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<WatchList> watchList;

    ArrayList<String> category;
    Spinner spinner;
    ArrayAdapter<String> spinnerAdapter;
    CustomAdapter caadapter;

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        watchList.clear();
        watchList.addAll(dbh.getAllList());
        caadapter.notifyDataSetChanged();

        category.clear();
        category.addAll(dbh.getcategory());
        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle(getTitle().toString() + " ~ Show List");

        lv = (ListView) this.findViewById(R.id.lv);
        spinner = (Spinner) this.findViewById(R.id.spnCat);

        DBHelper dbh = new DBHelper(SecondActivity.this);
        watchList = dbh.getAllList();
        category = dbh.getcategory();
        dbh.close();

        caadapter = new CustomAdapter(SecondActivity.this, R.layout.row, watchList);
        lv.setAdapter(caadapter);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent modi = new Intent(SecondActivity.this, ThirdActivity.class);
                modi.putExtra("watchlist", watchList.get(position));
                startActivity(modi);
            }
        });

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, category);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                watchList.clear();
                watchList.addAll(dbh.getAllItemByCategory(category.indexOf(position)));
                caadapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
