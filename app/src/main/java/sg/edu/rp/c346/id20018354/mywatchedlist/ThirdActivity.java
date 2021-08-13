package sg.edu.rp.c346.id20018354.mywatchedlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etTitle, etDescription, etCategory;
    Button btnCancel, btnUpdate, btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setTitle(getTitle().toString() + " ~ Edit");

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etCategory = (EditText) findViewById(R.id.etCategory);

        Intent i = getIntent();
        final WatchList currentWatchlist = (WatchList) i.getSerializableExtra("watchlist");

        etID.setText(currentWatchlist.getId() + "");
        etTitle.setText(currentWatchlist.getTitle());
        etDescription.setText(currentWatchlist.getDescription());
        etCategory.setText(currentWatchlist.getCategory() + "");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentWatchlist.setId(etID.getId());
                currentWatchlist.setTitle(etTitle.getText().toString().trim());
                currentWatchlist.setDescription(etDescription.getText().toString().trim());
                currentWatchlist.setCategory(etCategory.getText().toString().trim());
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                int result = dbh.deleteItem(currentWatchlist.getId());
                if (result > 0) {
                    Toast.makeText(ThirdActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
