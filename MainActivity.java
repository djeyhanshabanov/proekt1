package com.example.programa2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText model, marka, regnomer, danak, zastrahovka, pregled, vinetka;
    Button vmakni, obnovi, iztriy, pogledni;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = findViewById(R.id.model);
        marka = findViewById(R.id.marka);
        regnomer = findViewById(R.id.regnomer);
        danak = findViewById(R.id.danak);
        zastrahovka = findViewById(R.id.zastrahovka);
        pregled = findViewById(R.id.pregled);
        vinetka = findViewById(R.id.vinetka);

        vmakni = findViewById(R.id.btnVmakni);
        obnovi = findViewById(R.id.btnObnovi);
        iztriy = findViewById(R.id.btnIztriy);
        pogledni = findViewById(R.id.btnPogledni);

        DB = new DBHelper(this);

        vmakni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String modelTXT = model.getText().toString();
                String markaTXT = marka.getText().toString();
                String regnomerTXT = regnomer.getText().toString();
                String danakTXT = danak.getText().toString();
                String zastrahovkaTXT = zastrahovka.getText().toString();
                String pregledTXT = pregled.getText().toString();
                String vinetkaTXT = vinetka.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(modelTXT, markaTXT, regnomerTXT, danakTXT, zastrahovkaTXT, pregledTXT, vinetkaTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "Новите данни са вкарани", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Новите данни не са вкарани", Toast.LENGTH_SHORT).show();

            }
        });

        obnovi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String modelTXT = model.getText().toString();
                String markaTXT = marka.getText().toString();
                String regnomerTXT = regnomer.getText().toString();
                String danakTXT = danak.getText().toString();
                String zastrahovkaTXT = zastrahovka.getText().toString();
                String pregledTXT = pregled.getText().toString();
                String vinetkaTXT = vinetka.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(modelTXT, markaTXT, regnomerTXT, danakTXT, zastrahovkaTXT, pregledTXT, vinetkaTXT);
                if(checkupdatedata==true)
                        Toast.makeText(MainActivity.this, "Данните са обновени", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Данните не са обновени", Toast.LENGTH_SHORT).show();

            }
        });

        iztriy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regnomerTXT = regnomer.getText().toString();
                Boolean checkdeletedata = DB.deletedata(regnomerTXT);
                if(checkdeletedata==true)
                    Toast.makeText(MainActivity.this, "Данните са изтрити", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Данните не са изтрити", Toast.LENGTH_SHORT).show();

            }
        });

        pogledni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "Данните не съществуват", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Модел: " + res.getString(0) + "\n");
                    buffer.append("Марка: " + res.getString(1) + "\n");
                    buffer.append("Регистрационен номер: " + res.getString(2) + "\n");
                    buffer.append("Данък: " + res.getString(3) + "\n");
                    buffer.append("Застраховка: " + res.getString(4) + "\n");
                    buffer.append("Преглед: " + res.getString(5) + "\n");
                    buffer.append("Винетка: " + res.getString(6) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Потребителски записи:");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}
