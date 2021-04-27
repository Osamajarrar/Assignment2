package project.bzu.assignment2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;

import project.bzu.assignment2.Models.CV;
import project.bzu.assignment2.R;

public class PersonalInfo extends AppCompatActivity {
    Spinner daySpinner,monthSpinner,yearSpinner,genderSpinner;
    EditText txtEdtName,txtEdtEmail;
    CheckBox rememberInfo;
    CV cv;
    public static final String NAME="NAME";
    public static final String EMAIL="EMAIL";
    public static final String DateOfBirth="DOB";
    public static final String GENDER="GENDER";
    public static final String CV="CV";
    public static final String FLAG="FLAG";
    private boolean flag = false;

    private SharedPreferences prefs;
    private  SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_layout);

        daySpinner=findViewById(R.id.day);
        monthSpinner=findViewById(R.id.month);
        yearSpinner=findViewById(R.id.year);
        txtEdtName=findViewById(R.id.edtTxtName);
        txtEdtEmail=findViewById(R.id.edtTxtEmail);
        genderSpinner=findViewById(R.id.gender);
        rememberInfo=findViewById(R.id.rememberInfo);
        cv=new CV();
        populateDaySpinner();
        populateMonthSpinner();
        populateYearSpinner();
        populateGenderSpinner();
        setupSharedPrefs();
        checkPrefs();
    }

    private void populateDaySpinner() {
        ArrayList<Integer> days=new ArrayList<>();
        int dayCounter=31;
        for(int i=1;i<=dayCounter;i++){
            days.add(i);
        }
        ArrayAdapter<Integer> adapter=new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,days);
        daySpinner.setAdapter(adapter);
    }
    private void populateMonthSpinner() {
        ArrayList<Integer> months=new ArrayList<>();
        months.add(1);
        months.add(2);
        months.add(3);
        months.add(4);
        months.add(5);
        months.add(6);
        months.add(7);
        months.add(8);
        months.add(9);
        months.add(10);
        months.add(11);
        months.add(12);

        ArrayAdapter<Integer> adapter=new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,months);
        monthSpinner.setAdapter(adapter);

    }
    private void populateYearSpinner() {
        ArrayList<Integer> years=new ArrayList<>();
        int yearCounter=1970;
        for(int i=2021;i>=yearCounter;i--){
            years.add(i);
        }
        ArrayAdapter<Integer> adapter=new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,years);
        yearSpinner.setAdapter(adapter);
    }
    private void populateGenderSpinner(){
        ArrayList<String> gender=new ArrayList<>();
        gender.add("Male");
        gender.add("Female");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,gender);
        genderSpinner.setAdapter(adapter);
    }

    private void checkPrefs() {

        flag=prefs.getBoolean(FLAG,false);
        if (flag){
            Gson gson = new Gson();
            String json = prefs.getString(CV, "");
            Log.d("TAG", "checkPrefs: hello "+json);
            cv = gson.fromJson(json, CV.class);
            String name=cv.getName();
            String email=cv.getEmail();
            String DOB=cv.getDOB();
            Log.d("TAG", "checkPrefs: "+DOB);
            String[] split=DOB.split("-");
            int day=Integer.parseInt(split[0]);
            int month=Integer.parseInt(split[1]);
            int year=Integer.parseInt(split[2]);
            String genderSelection=cv.getGender();
            txtEdtName.setText(name);
            txtEdtEmail.setText(email);

            daySpinner.setSelection(day-1);
            monthSpinner.setSelection(month-1);

            int yearCounter=1970;
            int j=0;
            for(int k=2021;k>=yearCounter;k--){

                if (year==k) {
                    yearSpinner.setSelection(j);
                }
                j++;

            }


            if (genderSelection.equals("Male")) {genderSpinner.setSelection(0);}
            if (genderSelection.equals("Female")) {genderSpinner.setSelection(1);}

            rememberInfo.setChecked(true);
        }
    }

    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor=prefs.edit();
    }


    public void next_onClick(View view) {

        Intent intent=new Intent(this,EduExp.class);
        String name=txtEdtName.getText().toString().trim();
        String email=txtEdtEmail.getText().toString().trim();
        String day=daySpinner.getSelectedItem().toString();
        String month=monthSpinner.getSelectedItem().toString();
        String year=yearSpinner.getSelectedItem().toString();
        String gender=genderSpinner.getSelectedItem().toString();
        String DOB=day+"-"+month+"-"+year;
        Log.d("TAG", "next_onClick: "+DOB);

        intent.putExtra(NAME,name);
        intent.putExtra(EMAIL,email);
        intent.putExtra(DateOfBirth,DOB);
        intent.putExtra(GENDER,gender);

        cv.setDOB(DOB);
        cv.setEmail(email);
        cv.setName(name);
        cv.setGender(gender);
        flag=false;
        if (rememberInfo.isChecked()){
            if (!flag) {
                Gson gson=new Gson();
                String json= gson.toJson(cv);
                editor.putString(CV,json);
                Log.d("TAG", "next_onClick: json "+json);
                editor.putBoolean(FLAG,true);
                intent.putExtra(CV,json);
                editor.commit();
            }
        }
        if (!rememberInfo.isChecked()){
            Log.d("TAG", "next_onClick: sup");
            editor.putBoolean(FLAG,false);
            editor.putString(CV,"{\"DOB\":\"1-1-2021\",\"email\":\"\",\"gender\":\"Male\",\"name\":\"\"}");
            intent.putExtra(CV,"{\"DOB\":\"1-1-2021\",\"email\":\"\",\"gender\":\"Male\",\"name\":\"\"}");
            editor.commit();
        }


        startActivity(intent);
    }

    public void exit_onClick(View view) {

        String name=txtEdtName.getText().toString().trim();
        String email=txtEdtEmail.getText().toString().trim();
        String day=daySpinner.getSelectedItem().toString();
        String month=monthSpinner.getSelectedItem().toString();
        String year=yearSpinner.getSelectedItem().toString();
        String gender=genderSpinner.getSelectedItem().toString();
        String DOB=day+"-"+month+"-"+year;

        cv.setDOB(DOB);
        cv.setEmail(email);
        cv.setName(name);
        cv.setGender(gender);
        flag=false;
        if (rememberInfo.isChecked()){
            if (!flag) {
                Gson gson=new Gson();
                String json= gson.toJson(cv);
                editor.putString(CV,json);
                Log.d("TAG", "exit_onClick: json "+json);
                editor.putBoolean(FLAG,true);
                editor.commit();
            }
        }
        if (!rememberInfo.isChecked()){
            Log.d("TAG", "exit_onClick: sup");
            editor.putBoolean(FLAG,false);
            editor.putString(CV,"{\"DOB\":\"1-1-2021\",\"email\":\"\",\"gender\":\"Male\",\"name\":\"\"}");
            editor.commit();

        }
        System.exit(0);
    }


}





