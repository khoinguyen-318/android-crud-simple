package com.example.baithi.model;

import java.io.Serializable;

public class Student implements Serializable {
    private int maSv;
    private String hoTen;
    private int gioiTinh;
    private float diem;

    public Student() {
    }

    public Student(int maSv, String hoTen, int gioiTinh, float diem) {
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.diem = diem;
    }

    public Student(String hoTen, int gioiTinh, float diem) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.diem = diem;
    }

    public int getMaSv() {
        return maSv;
    }

    public void setMaSv(int maSv) {
        this.maSv = maSv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }
}
