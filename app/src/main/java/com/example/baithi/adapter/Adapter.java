package com.example.baithi.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baithi.R;
import com.example.baithi.model.Student;

import java.util.List;

public class Adapter extends BaseAdapter {
    private final List<Student> list;

    public Adapter(List<Student> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getMaSv();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.item_list, null);
        } else viewProduct = convertView;

        //Bind sữ liệu phần tử vào View
        Student student = (Student) getItem(position);
        String gioiTinh = student.getGioiTinh()==0?"Nam":"Nữ";
        ((TextView) viewProduct.findViewById(R.id.showHoTen)).setText(String.format("Họ tên: %s",student.getHoTen()));
        ((TextView) viewProduct.findViewById(R.id.showGioiTinh)).setText(String.format("Giới tính: %s",gioiTinh));
        ((TextView) viewProduct.findViewById(R.id.showDiem)).setText(String.format("Điểm: %s",String.valueOf(student.getDiem())));
        if (student.getGioiTinh() == 0) {
            ((ImageView) viewProduct.findViewById(R.id.imageGioiTinh)).setImageResource(R.drawable.male);
        } else {
            ((ImageView) viewProduct.findViewById(R.id.imageGioiTinh)).setImageResource(R.drawable.female);
        }
        return viewProduct;
    }
}
