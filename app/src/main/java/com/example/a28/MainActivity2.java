package com.example.a28;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TextView txtHoTen, txtNgaySinh, txtDiaChi, txtGioiTinh, txtYeuThich;
    Button btnQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        AnhXa();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String hoTen = bundle.getString("hoTen", "sai key");
            String ngaySinh = bundle.getString("ngaySinh", "sai key");
            String diaChi = bundle.getString("diaChi", "sai key");
            String gioiTinh = bundle.getString("gioiTinh", "sai key");
            String yeuThich = bundle.getString("yeuThich", "sai key");

            txtHoTen.setText(hoTen);
            txtGioiTinh.setText(gioiTinh);
            txtDiaChi.setText(diaChi);
            txtNgaySinh.setText(ngaySinh);
            txtYeuThich.setText(yeuThich);
            Toast.makeText(this, "Co", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Khong co gi", Toast.LENGTH_SHORT).show();
        }

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void AnhXa() {
        txtHoTen = findViewById(R.id.txtHoTen);
        txtNgaySinh = findViewById(R.id.txtNgaySinh);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtGioiTinh = findViewById(R.id.txtGioiTinh);
        txtYeuThich = findViewById(R.id.txtYeuThich);
        btnQuayLai = findViewById(R.id.btnQuayLai);
    }
}