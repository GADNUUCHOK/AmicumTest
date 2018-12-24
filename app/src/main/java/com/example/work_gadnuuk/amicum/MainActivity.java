package com.example.work_gadnuuk.amicum;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Spinner smena,mesto,zveno,operaciya;
    private TextView data, Out, Metrov, Strips, Anker, Zatyagka, AnkerBor, Setka, Ampula;
    EditText Cikl;
    TextView Svernut;
    DataBaseHelper DataBase;
    Button AddButton, ReadButton, MapActivity;
    String Num1, Num2, Num3, Num4, Num5, Num6, Num7, Num8, Num9, Num10, Num11, Num12, Num13;
    RadioGroup radioGroup;
    View include;

    int cikl1;
    double Shag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smena = (Spinner) findViewById(R.id.smenaSpinner);                                              //Развертывающийся список смены
        String [] numSmena = {"1","2","3","4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, numSmena);
        smena.setAdapter(adapter);
        Num1 = smena.getSelectedItem().toString();

        mesto = (Spinner) findViewById(R.id.mestoSpinner);                                               //Развертывающийся список места
        String [] numMesto = {"Разрезная печь 16-1","Лотки старательские","Горный карандаш","Кованые коронки"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, numMesto);
        mesto.setAdapter(adapter2);
        Num2 = mesto.getSelectedItem().toString();

        zveno = (Spinner) findViewById(R.id.zvenoSpinner);                                                //Развертывающийся список звена
        String [] numZveno = {"Звено №1","Звено №2","Звено №3","Звено №4"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, numZveno);
        zveno.setAdapter(adapter3);
        Num3 = zveno.getSelectedItem().toString();

        operaciya = (Spinner) findViewById(R.id.operaciyaSpinner);                                          //Развертывающийся список операции
        String [] numOperaciya = {"Работы по проведению","Расчистка","Установка"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, numOperaciya);
        operaciya.setAdapter(adapter4);
        Num4 = operaciya.getSelectedItem().toString();

        data = (TextView) findViewById(R.id.date);
        String currentDateString = DateFormat.getDateInstance().format(new Date());                         //Установка даты
        data.setText(currentDateString);
        Calendar calendar = Calendar.getInstance();

        MapActivity = (Button) findViewById(R.id.Karta);                                                    //Подключение карты
        MapActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setPackage("com.google.android.apps.maps");
                startActivity(i);
            }
        });
        include = (View) findViewById(R.id.include2);

        Svernut = (TextView) findViewById(R.id.svernut);                                                     //Сокрытие контента
        Svernut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (include.getVisibility() == View.VISIBLE) {
                    include.setVisibility(View.INVISIBLE);
                } else {
                    include.setVisibility(View.VISIBLE);
                }
            }
        });

        DataBase = new DataBaseHelper(this);
        AddButton = (Button) findViewById(R.id.Add);
        ReadButton = (Button) findViewById(R.id.Read);
        Out = (TextView) findViewById(R.id.Result);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Content();
            }
        });
        ReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenContent();
            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {                        //Выбор среди трех кнопок
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButton1:
                        Shag = 0.5;
                        break;
                    case R.id.radioButton2:
                        Shag = 0.8;
                        break;
                    case R.id.radioButton3:
                        Shag = 1;
                        break;
                }
            }
        });

        Cikl = (EditText) findViewById(R.id.editText);                                                              //Строка ввода
        Cikl.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                        (i == KeyEvent.KEYCODE_ENTER)) {
                    Editable editable = Cikl.getText();
                    String cikl = editable.toString();
                    //cikl = Integer.valueOf(Cikl.getText());
                    //Num13 = Cikl.getText().toString();
                    cikl1 = Integer.parseInt(cikl);
                    Toast.makeText(getApplicationContext(),cikl, Toast.LENGTH_SHORT).show();

                    return true;
                }
                return false;
            }
        });
        //cikl = Integer.valueOf(cikl);
        //Num13 = Cikl.getText().toString();

        Metrov = (TextView) findViewById(R.id.textView2);                                                              //Перевод и расчет информации в нужные типы данных
        //Metrov.setText(String.valueOf(cikl));
        Num5 = Metrov.getText().toString();
        Strips = (TextView) findViewById(R.id.Strips);
        //Strips.setText(""+cikl);
        Num6 = Strips.getText().toString();
        Anker = (TextView) findViewById(R.id.Anker);
        //Anker.setText(Integer.toString(5*cikl1));
        Num7 = Anker.getText().toString();
        Zatyagka = (TextView) findViewById(R.id.Zatyagka);
        //Zatyagka.setText(String.valueOf(5*cikl));
        Num8 = Zatyagka.getText().toString();
        AnkerBor = (TextView) findViewById(R.id.AnkerBor);
        //AnkerBor.setText(String.valueOf(4*cikl));
        Num9 = AnkerBor.getText().toString();
        Setka = (TextView) findViewById(R.id.Setka);
        //Setka.setText(String.valueOf(4*cikl));
        Num10 = Setka.getText().toString();
        Ampula = (TextView) findViewById(R.id.Ampula);
        //Ampula.setText(String.valueOf(18*cikl));
        Num11 = Ampula.getText().toString();
        Num12 = Double.toString(Shag);


    }

    private void Content() {                                                                                      //Добавление информации в БД
        String SMENA = Num1;
        String MESTO = Num2;
        String ZVENO = Num3;
        String OPERACIYA = Num4;
        String SHAG = Num12;
        String CIKL = Num13;
        String METROV = Num5;
        String STRIPS = Num6;
        String ANKER = Num7;
        String ZATYAGKA = Num8;
        String ANKERBOR = Num9;
        String SETKA = Num10;
        String AMPULA = Num11;
        Boolean result = DataBase.InsertData(SMENA, MESTO, ZVENO, OPERACIYA, CIKL, SHAG, METROV, STRIPS,
                ANKER, ZATYAGKA, ANKERBOR, SETKA, AMPULA);
        if (result == true) {
            Toast.makeText(this, "Успешно", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Неудача", Toast.LENGTH_SHORT).show();
        }
    }

    private void OpenContent() {                                                                                   //Отобразить все данные БД
        Cursor res = DataBase.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res != null && res.getCount()>0) {
            while (res.moveToNext()) {
                stringBuffer.append("ID: " + res.getString(0)+"\n");
                stringBuffer.append("Smena: " + res.getString(1)+"\n");
                stringBuffer.append("Mesto: " + res.getString(2)+"\n");
                stringBuffer.append("Zveno: " + res.getString(3)+"\n");
                stringBuffer.append("Operaciya: " + res.getString(4)+"\n");
                stringBuffer.append("Cikl: " + res.getString(5)+"\n");
                stringBuffer.append("Shag: " + res.getString(6)+"\n");
                stringBuffer.append("Metrov: " + res.getString(7)+"\n");
                stringBuffer.append("Strips: " + res.getString(8)+"\n");
                stringBuffer.append("Anker: " + res.getString(9)+"\n");
                stringBuffer.append("Zatyagka: " + res.getString(10)+"\n");
                stringBuffer.append("Ankerbor: " + res.getString(11)+"\n");
                stringBuffer.append("Setka: " + res.getString(12)+"\n");
                stringBuffer.append("Ampula: " + res.getString(13)+"\n" + "\n");

            }
            Out.setText(stringBuffer.toString());
            Toast.makeText(this, "Успешно отображено", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Пусто", Toast.LENGTH_SHORT).show();
        }
    }
}
