package com.example.baithi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.baithi.database.StudentDAO;
import com.example.baithi.model.Student;

public class Activity_Add extends AppCompatActivity {

    private EditText edtHoten,edtDiem;
    private RadioGroup radioGioiTinh;
    private Button btnThem,btnBack;
    private StudentDAO dao;
    private int gioiTinh = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //
        edtHoten = findViewById(R.id.edtHoTen);
        edtDiem = findViewById(R.id.edtDiem);
        btnThem = findViewById(R.id.btnThem);
        btnBack = findViewById(R.id.btnBack);
        radioGioiTinh = findViewById(R.id.radioGioiTinh);
        dao = new StudentDAO(Activity_Add.this);
        //
        radioGioiTinh.setOnCheckedChangeListener((g,id)->{
            if(id==R.id.gtNam){
                gioiTinh = 0;
            }
            else {
                gioiTinh = 1;
            }
        });
        //Nút thêm
        btnThem.setOnClickListener(l->{
            Student student = new Student(
              edtHoten.getText().toString().trim(),
              gioiTinh,
              Float.parseFloat(edtDiem.getText().toString().trim())
            );
            dao.insert(student);
            Intent intent = new Intent(Activity_Add.this,Home.class);
            startActivity(intent);
        });
        //Nút back
        btnBack.setOnClickListener(l->{
            Intent intent = new Intent(Activity_Add.this,Home.class);
            startActivity(intent);
        });
    }


}