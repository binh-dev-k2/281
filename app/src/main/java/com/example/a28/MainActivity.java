package com.example.a28;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtHoTen, edtNgaySinh, edtDiaChi;
    RadioButton rbNam, rbNu;
    CheckBox cbKinhTe, cbCNTT, cbSuPham;
    Button btnHienThi, btnLuu;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        db = openOrCreateDatabase("thisinh.db", MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS thisinh(" +
                "ho_ten text," +
                "ngay_sinh text," +
                "dia_chi text," +
                "gioi_tinh text," +
                "yeu_thich text)";
        db.execSQL(sql);

        GetData();

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = getHoTen(),
                        ngaySinh = getNgaySinh(),
                        diaChi = getDiaChi(),
                        gioiTinh = getGioiTinh(),
                        yeuThich = getYeuThich();

                if (hoTen.length() == 0 || ngaySinh.length() == 0 || diaChi.length() == 0 || gioiTinh.length() == 0 || yeuThich.length() == 0) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ dữ liệu!", Toast.LENGTH_SHORT).show();
                } else {
                    String sql1 = "INSERT INTO thisinh(ho_ten,ngay_sinh,dia_chi,gioi_tinh,yeu_thich) values('"+hoTen+"', '"+ngaySinh+"', '"+diaChi+"', '"+gioiTinh+"', '"+yeuThich+"')";
                    db.execSQL(sql1);

                    Toast.makeText(MainActivity.this, "Lưu dữ liệu thành công", Toast.LENGTH_SHORT).show();

                    edtHoTen.setText("");
                    edtNgaySinh.setText("");
                    edtDiaChi.setText("");
                    rbNam.setChecked(true);
                    cbKinhTe.setChecked(false);
                    cbCNTT.setChecked(false);
                    cbSuPham.setChecked(false);
                }
            }
        });

        btnHienThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = getHoTen(),
                        ngaySinh = getNgaySinh(),
                        diaChi = getDiaChi(),
                        gioiTinh = getGioiTinh(),
                        yeuThich = getYeuThich();

                if (hoTen.length() == 0 || ngaySinh.length() == 0 || diaChi.length() == 0 || gioiTinh.length() == 0 || yeuThich.length() == 0) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ dữ liệu!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("hoTen", hoTen);
                    bundle.putString("ngaySinh", ngaySinh);
                    bundle.putString("diaChi", diaChi);
                    bundle.putString("gioiTinh", gioiTinh);
                    bundle.putString("yeuThich", yeuThich);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

    }

    private void AnhXa() {
        edtHoTen = findViewById(R.id.edtHoTen);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        rbNam = findViewById(R.id.rbNam);
        rbNu = findViewById(R.id.rbNu);
        cbKinhTe = findViewById(R.id.cbKinhTe);
        cbCNTT = findViewById(R.id.cbCNTT);
        cbSuPham = findViewById(R.id.cbSuPham);
        btnHienThi = findViewById(R.id.btnHienThi);
        btnLuu = findViewById(R.id.btnLuu);
    }


    @SuppressLint("Range")
    private void GetData() {
        Cursor cursor = db.rawQuery("SELECT * FROM thisinh", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                } while (cursor.moveToNext());
            }
        }
    }

    private String getHoTen() {
        return edtHoTen.getText().toString().trim();
    }

    private String getNgaySinh() {
        return edtNgaySinh.getText().toString().trim();
    }

    private String getDiaChi() {
        return edtDiaChi.getText().toString().trim();
    }

    private String getGioiTinh() {
        if (rbNam.isChecked()) {
           return rbNam.getText().toString();
        } else if (rbNu.isChecked()){
            return rbNu.getText().toString();
        }
        return "";
    }

    private String getYeuThich() {
        String yeuThich = "";
        if (cbSuPham.isChecked()) {
            yeuThich += cbSuPham.getText().toString();

            if (cbCNTT.isChecked()) {
                yeuThich += ", " + cbCNTT.getText().toString();
            }

            if (cbKinhTe.isChecked()) {
                yeuThich += ", " + cbKinhTe.getText().toString();
            }
        } else if (cbCNTT.isChecked()) {
            yeuThich += cbCNTT.getText().toString();

            if (cbKinhTe.isChecked()) {
                yeuThich += ", " + cbKinhTe.getText().toString();
            }
        } else if (cbKinhTe.isChecked()){
            yeuThich += cbKinhTe.getText().toString();
        }

        return yeuThich;
    }
}