package sg.edu.rp.c346.id20018354.mywatchedlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etDescription, etCategory;
    Button btnInsert, btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getTitle().toString() + " ~ Insert to WatchList/ReadList");

        etTitle = (EditText) findViewById(R.id.etTitle);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etCategory = (EditText) findViewById(R.id.etCategory);
        btnInsert = (Button) findViewById(R.id.btnInsertSong);
        btnShowList = (Button) findViewById(R.id.btnShowList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = etTitle.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                String category= etCategory.getText().toString().trim();
                if (title.length() == 0 || description.length() == 0 || category.length() == 0){
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);

                dbh.insertWatched(title, description, category);
                dbh.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                etTitle.setText("");
                etDescription.setText("");
                etCategory.setText("");

            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent show = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(show);
            }
        });

    }

}
