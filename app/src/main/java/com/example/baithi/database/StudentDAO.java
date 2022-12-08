package com.example.baithi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baithi.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private DBHelper db;

    public StudentDAO(Context context) {
        this.db = new DBHelper(context);
    }
    public List<Student> getAllInformation(){
        SQLiteDatabase database = db.getReadableDatabase();
        List<Student> studentList = new ArrayList<>();
        String query = "SELECT * FROM "+FieldColumn.TABLE;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Student student = new Student(
                    cursor.getInt(0), //Ma Sv
                    cursor.getString(1), // Ho ten
                    cursor.getInt(2), // Ngay sinh
                    cursor.getFloat(3) //Diem
            );
            studentList.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return studentList;
    }
    public List<Student> getAllInformationWithSearch(String key){
        SQLiteDatabase database = db.getReadableDatabase();
        List<Student> studentList = new ArrayList<>();
        String query = "SELECT * FROM " + FieldColumn.TABLE+
                " WHERE masv like '%"+key+"%' or " +
                "hoten like '%"+key+"%' or " +
                "gioitinh like '%"+key+"%' or " +
                "diem like '%"+key+"%'"
                ;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Student student = new Student(
                    cursor.getInt(0), //Ma Sv
                    cursor.getString(1), // Ho ten
                    cursor.getInt(2), // Ngay sinh
                    cursor.getFloat(3) //Diem
            );
            studentList.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return studentList;
    }
    public void insert(Student student){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FieldColumn.HO_TEN,student.getHoTen());
        values.put(FieldColumn.GIOI_TINH,student.getGioiTinh());
        values.put(FieldColumn.DIEM,student.getDiem());
        database.insert(FieldColumn.TABLE,null,values);
        database.close();
    }
    public void update(Student student){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FieldColumn.HO_TEN,student.getHoTen());
        values.put(FieldColumn.GIOI_TINH,student.getGioiTinh());
        values.put(FieldColumn.DIEM,student.getDiem());
        database.update(FieldColumn.TABLE,values,
                FieldColumn.MA_SV+"=?",new String[]{String.valueOf(student.getMaSv())});
        database.close();
    }
    public void delete(int id){
        SQLiteDatabase database = db.getWritableDatabase();
        database.delete(FieldColumn.TABLE,FieldColumn.MA_SV+"=?", new String[]{String.valueOf(id)});
        database.close();
    }
}
