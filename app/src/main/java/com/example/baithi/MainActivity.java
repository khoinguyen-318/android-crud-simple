package com.example.baithi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtUserName,edtPassWord;
    private Button btnDangNhap,btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUserName = findViewById(R.id.edtUsername);
        edtPassWord = findViewById(R.id.edtPassword);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnThoat = findViewById(R.id.btnThoat);
        /* Nút thoát
        *   Hỏi có xác nhận trước khi thoát
        *   Có thì thoát, không thì ...
        */
        btnThoat.setOnClickListener(l->{
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Warning");
            builder.setMessage("Are you sure to exit this app");
            builder.setPositiveButton("Ok",((dialog, which) -> finish()));
            builder.setNegativeButton("Cancel",((dialog, which) -> dialog.dismiss()));
            builder.create().show();
        });
        /* Nut đăng nhập
        * Nếu ko nhập gì thì báo lỗi
        * Nhập đúng là admin và admin thì chuyển layout
        * còn nhập sai thì báo lỗi
        * */
        btnDangNhap.setOnClickListener(l->{
            String uName = edtUserName.getText().toString().trim();
            String pass = edtPassWord.getText().toString().trim();
            if(uName.isEmpty()||pass.isEmpty()){
                Toast.makeText(MainActivity.this,"Bạn chưa nhập username hoặc password",Toast.LENGTH_LONG).show();
            }
            else {
                if (uName.equalsIgnoreCase("admin")&&pass.equalsIgnoreCase("admin")){
                    Intent intent = new Intent(MainActivity.this,Home.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,"Sai username hoặc password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}