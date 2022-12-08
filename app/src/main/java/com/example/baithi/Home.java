package com.example.baithi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.baithi.adapter.Adapter;
import com.example.baithi.database.StudentDAO;
import com.example.baithi.model.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private Button btnSearch;
    private EditText edtSearch;
    private ListView listView;
    private List<Student> studentList;
    private StudentDAO dao;
    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //
        btnSearch = findViewById(R.id.btnSearch);
        edtSearch = findViewById(R.id.edtSearch);
        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));
        //
        studentList = new ArrayList<>();
        dao = new StudentDAO(Home.this);
        studentList = dao.getAllInformation();
        adapter = new Adapter(studentList);
        listView.setAdapter(adapter);
        onResume();
        registerForContextMenu(listView);
        btnSearch.setOnClickListener(l->{
            studentList = dao.getAllInformationWithSearch(edtSearch.getText().toString().trim());
            adapter = new Adapter(studentList);
            listView.setAdapter(adapter);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuThem:{
                Intent intent=new Intent(Home.this, Activity_Add.class);
                startActivity(intent);
                break;
            }
            case R.id.mnuThoat:{
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int vitri=info.position;
        Student sv=studentList.get(vitri);
        switch (item.getItemId()){
            case R.id.mnuSua:{
                Intent intent=new Intent(Home.this, Activity_Update.class);
                intent.putExtra("DuLieu", sv); //Chuyển dữ liệu sang Activity_add với key DuLieu
                startActivity(intent);
                break;
            }
            case R.id.mnuXoa:{
                dao.delete(sv.getMaSv());
                adapter.notifyDataSetChanged();
                onResume();
                break;
            }
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentList.clear();
        studentList.addAll(dao.getAllInformation());
        adapter.notifyDataSetChanged();
    }
}