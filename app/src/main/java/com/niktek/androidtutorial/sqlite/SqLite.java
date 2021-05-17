package com.niktek.androidtutorial.sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.niktek.androidtutorial.R;

public class SqLite extends AppCompatActivity {

    // reference to buttons and other components of the layout
    Button btn_viewAll, btn_add;
    EditText et_name, et_age;
    Switch sw_activeCustomer;
    ListView lv_customerList;

    ArrayAdapter customerArrayAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq_lite);

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);
        sw_activeCustomer = findViewById(R.id.sw_activeCustomer);
        lv_customerList = findViewById(R.id.lv_customerList);

        // database action class instance
        databaseHelper = new DatabaseHelper(SqLite.this);

        // display items on startup
        showCustomersOnListView(databaseHelper);

        // add a new item
        btn_add.setOnClickListener(v -> {
            CustomerModel customerModel;

            try {
                customerModel = new CustomerModel(
                        -1,
                        et_name.getText().toString(),
                        Integer.parseInt(et_age.getText().toString()),
                        sw_activeCustomer.isChecked()
                );

                Toast.makeText(this, customerModel.toString(), Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(this, "Error Creating Customer", Toast.LENGTH_SHORT).show();
                customerModel = new CustomerModel(-1, "error", 0, false);
            }
            boolean success = databaseHelper.addOne(customerModel);

            // display items just after adding a new item
            showCustomersOnListView(databaseHelper);
        });

        // view all items
        btn_viewAll.setOnClickListener(v -> {

            // display items from ViewAll button
            showCustomersOnListView(databaseHelper);
        });

        lv_customerList.setOnItemClickListener((parent, view, position, id) -> {
            CustomerModel customerModel = (CustomerModel) parent.getItemAtPosition(position);
            databaseHelper.deleteOne(customerModel);
            showCustomersOnListView(databaseHelper);
            Toast.makeText(this, "Deleted: " + customerModel.toString(), Toast.LENGTH_SHORT).show();
        });
    }

    private void showCustomersOnListView(DatabaseHelper databaseHelper) {
        customerArrayAdapter = new
                ArrayAdapter<CustomerModel>(SqLite.this, android.R.layout.simple_list_item_1, databaseHelper.getEveryone());
        lv_customerList.setAdapter(customerArrayAdapter);
    }

    // To show items on ListView
}