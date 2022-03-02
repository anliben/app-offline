package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    public Button btn_add, btn_view;
    public TextView field_name, field_age;
    public Switch field_active;
    public ListView list_view;

    List<Costumers> opcoes;
    ArrayAdapter<Costumers> adaptor;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // load var

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_view = (Button) findViewById(R.id.btn_view);
        field_name = (TextView) findViewById(R.id.fieldName);
        field_age = (TextView) findViewById(R.id.fieldAge);
        field_active = (Switch) findViewById(R.id.switchActive);
        list_view = (ListView) findViewById(R.id.list_view);

        dataBaseHelper = new DataBaseHelper(MainActivity2.this);

        showCostumersOnListView(dataBaseHelper);
        btn_add.setOnClickListener(btnListenerAddCostumers);
        btn_view.setOnClickListener(btnListenerViewCostumers);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Costumers customersClicked = (Costumers) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(customersClicked);
                showCostumersOnListView(dataBaseHelper);
            }
        });
    }

    private void showCostumersOnListView(DataBaseHelper dataBaseHelper) {
        adaptor = new ArrayAdapter<Costumers>(MainActivity2.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryOne());
        list_view.setAdapter(adaptor);
    }

    private View.OnClickListener btnListenerAddCostumers = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Costumers costumers;

            try {
                costumers = new Costumers(-1, field_name.getText().toString(), Integer.parseInt(field_age.getText().toString()), field_active.isChecked());
            } catch (Exception e) {
                costumers = new Costumers(-1, "error", 0, false);
                Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity2.this);
            boolean success = dataBaseHelper.addOne(costumers);
            Toast.makeText(MainActivity2.this, "Success: " + success, Toast.LENGTH_SHORT).show();

            showCostumersOnListView(dataBaseHelper);
        }
    };

    private View.OnClickListener btnListenerViewCostumers = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity2.this);
            showCostumersOnListView(dataBaseHelper);
        }
    };



}