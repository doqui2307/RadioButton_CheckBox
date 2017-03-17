package com.example.asus.checkbox_radiobutton;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnSave, btnClear;
    EditText txtHoTen, txtGhiChu;
    CheckBox chkDocSach, chkCongNghe, chkDuLich;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = (Button) findViewById(R.id.buttonSave);
        txtHoTen = (EditText) findViewById(R.id.editTextHoTen);
        txtGhiChu = (EditText) findViewById(R.id.editTextGhiChu);
        chkDocSach = (CheckBox) findViewById(R.id.chkDS);
        chkCongNghe = (CheckBox) findViewById(R.id.chkCN);
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        //Click Clear
        btnClear = (Button) findViewById(R.id.buttonClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtHoTen.setText("");
                txtGhiChu.setText("");
                txtHoTen.requestFocus();
                chkCongNghe.setChecked(false);
                chkDuLich.setChecked(false);
                chkDocSach.setChecked(false);
                rg.clearCheck();
            }
        });
        chkDuLich = (CheckBox) findViewById(R.id.chkDL);
        //Click save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doShowInformation();
            }
        });

    }
    public void doShowInformation()
    {
        String ten = txtHoTen.getText().toString();
        ten = ten.trim();
        if(ten.length()<3)
        {
            txtHoTen.findFocus();
            txtHoTen.selectAll();
            Toast.makeText(this,"Tên phải nhiều hơn 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        //Kiểm tra bằng
        String bang ="";
        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup1);
        int id = group.getCheckedRadioButtonId();
        if(id==-1)
        {
            Toast.makeText(this,"Phải chọn bằng cấp", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton rad = (RadioButton) findViewById(id);
        bang = rad.getText()+"";

        //Kiểm tra sở thích
        String sothich ="";
        if(chkDocSach.isChecked())
        {
            sothich+=chkDocSach.getText()+"\n";
        }
        if(chkCongNghe.isChecked())
        {
            sothich+=chkCongNghe.getText()+"\n";
        }
        if(chkDuLich.isChecked())
        {
            sothich+=chkDuLich.getText()+"\n";
        }
        String ghichu = txtGhiChu.getText()+"";
        //Hiển thị hộp thoại AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //Tạo  nội dung
        String msg = ten+"\n";
        msg+=bang+"\n";
        msg+=sothich;
        msg+="____________________\n";
        msg+="Thông tin bổ sung\n";
        msg+=ghichu+"\n";
        msg+="_____________________\n";
        builder.setMessage(msg); // Nội dung
        builder.create().show(); // hiển thị

    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
