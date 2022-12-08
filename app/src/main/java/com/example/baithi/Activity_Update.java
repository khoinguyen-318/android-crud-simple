package com.example.baithi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baithi.database.StudentDAO;
import com.example.baithi.model.Student;

public class Activity_Update extends AppCompatActivity {
    private EditText edtHoten,edtDiem;
    private RadioGroup radioGioiTinh;
    private RadioButton rbNam,rbNu;
    private Button btnUpdate,btnBack;
    private StudentDAO dao;
    private int gioiTinh = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //
        edtHoten = findViewById(R.id.edtHoTen);
        edtDiem = findViewById(R.id.edtDiem);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnBack = findViewById(R.id.btnBack);
        radioGioiTinh = findViewById(R.id.radioGioiTinh);
        rbNam = findViewById(R.id.gtNam);
        rbNu = findViewById(R.id.gtNu);
        //
        dao = new StudentDAO(Activity_Update.this);
        //Lấy dữ liệu từ Home
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("DuLieu"); //Chú ý key là DuLieu
        //Binding dữ liệu vào input
        edtDiem.setText(String.valueOf(student.getDiem()));
        edtHoten.setText(student.getHoTen());
        if(student.getGioiTinh()==0){
            rbNam.setChecked(true);
            gioiTinh = 0;
        }
        else {
            rbNu.setChecked(true);
            gioiTinh=1;
        }
        //Check giới tính
        radioGioiTinh.setOnCheckedChangeListener((g,id)->{
            if(id==R.id.gtNam){
                gioiTinh = 0;
            }
            else {
                gioiTinh = 1;
            }
        });
        //Nut update
        btnUpdate.setOnClickListener(l->{
            student.setHoTen(edtHoten.getText().toString().trim());
            student.setGioiTinh(gioiTinh);
            student.setDiem(Float.parseFloat(edtDiem.getText().toString().trim()));
            dao.update(student);
            Intent intent1 = new Intent(Activity_Update.this,Home.class);
            startActivity(intent1);
        });
        //nút back
        btnBack.setOnClickListener(l->{
            Intent intent1 = new Intent(Activity_Update.this,Home.class);
            startActivity(intent1);
        });
    }
}