package project.bzu.assignment2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;

import project.bzu.assignment2.Models.CV;
import project.bzu.assignment2.R;

public class EduExp extends AppCompatActivity {
    CV cv;
    EditText edtTxtEducation,edtTxtExperience,edtTxtSkills,edtTxtLanguages,edtTxtReferences;
    CheckBox rememberInfo2;
    Intent intent,intent2;
    private SharedPreferences prefs;
    private  SharedPreferences.Editor editor;

    public static final String EDUCATION="EDUCATION";
    public static final String EXPERIENCE="EXPERIENCE";
    public static final String SKILLS="SKILLS";
    public static final String LANGUAGES="LANGUAGES";
    public static final String REFERENCES="REFERENCES";
    public static final String NAME="NAME";
    public static final String EMAIL="EMAIL";
    public static final String DateOfBirth="DOB";
    public static final String GENDER="GENDER";
    public static final String CV="CV";
   // public static final String CV2="CV2";
    public static final String FLAG="FLAG";

    private boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edu_exp_layout);

        intent=getIntent();
        String name=intent.getStringExtra(NAME);
        String email=intent.getStringExtra(EMAIL);
        String DOB=intent.getStringExtra(DateOfBirth);
        String gender=intent.getStringExtra(GENDER);
        Gson gson=new Gson();
        String json=intent.getStringExtra(CV);
        Log.d("TAG", "onCreate: TESTT"+json);
        if(json.equals(null)){
        cv=new CV();}
        else{
        cv = gson.fromJson(json, CV.class);
        }


        edtTxtEducation=findViewById(R.id.edtTxtEducation);
        edtTxtExperience=findViewById(R.id.edtTxtExperience);
        edtTxtSkills=findViewById(R.id.edtTxtSkills);
        edtTxtLanguages=findViewById(R.id.edtTxtLanguages);
        edtTxtReferences=findViewById(R.id.edtTxtReferences);
        rememberInfo2=findViewById(R.id.rememberInfo2);

        intent2=new Intent(this,Summary.class);
        intent2.putExtra(NAME,name);
        intent2.putExtra(EMAIL,email);
        intent2.putExtra(DateOfBirth,DOB);
        intent2.putExtra(GENDER,gender);

        setupSharedPrefs();
        checkPrefs();
    }
    private void checkPrefs() {

        flag=prefs.getBoolean(FLAG,false);
        if (flag){
            Gson gson = new Gson();
            String json = prefs.getString(CV, "");
            Log.d("TAG", "checkPrefs: hello2 "+json);
            cv = gson.fromJson(json, CV.class);
            String education=cv.getEducation();
            String experience=cv.getExperience();
            String skills=cv.getSkills();
            String languages=cv.getLanguages();
            String references=cv.getReferences();
            edtTxtEducation.setText(education);
            edtTxtExperience.setText(experience);
            edtTxtSkills.setText(skills);
            edtTxtLanguages.setText(languages);
            edtTxtReferences.setText(references);
            rememberInfo2.setChecked(true);
        }


    }

    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor=prefs.edit();
    }
    public void submit_onClick(View view) {

        String education=edtTxtEducation.getText().toString().trim();
        String experience=edtTxtExperience.getText().toString().trim();
        String skills=edtTxtSkills.getText().toString().trim();
        String languages=edtTxtLanguages.getText().toString().trim();
        String references=edtTxtReferences.getText().toString().trim();

        cv.setEducation(education);
        cv.setExperience(experience);
        cv.setSkills(skills);
        cv.setLanguages(languages);
        cv.setReferences(references);

        flag=false;
        if (rememberInfo2.isChecked()){
            if (!flag) {
                Gson gson=new Gson();
                String json= gson.toJson(cv);
                editor.putString(CV,json);
                Log.d("TAG", "submit_onClick: json "+json);
                editor.putBoolean(FLAG,true);
                intent2.putExtra(CV,json);
                editor.commit();
            }
        }
        if (!rememberInfo2.isChecked()){
            Log.d("TAG", "next_onClick: sup");
            editor.putBoolean(FLAG,false);
            editor.putString(CV,"{\"DOB\":\"1-1-2021\",\"education\":\"\",\"email\":\"\",\"experience\":\"\",\"gender\":\"Male\",\"languages\":\"\",\"name\":\"\",\"references\":\"\",\"skills\":\"\"}");
            editor.commit();
        }

        intent2.putExtra(EDUCATION,education);
        intent2.putExtra(EXPERIENCE,experience);
        intent2.putExtra(SKILLS,skills);
        intent2.putExtra(LANGUAGES,languages);
        intent2.putExtra(REFERENCES,references);
        startActivity(intent2);

    }

    public void exit_onClick(View view) {

        String education=edtTxtEducation.getText().toString().trim();
        String experience=edtTxtExperience.getText().toString().trim();
        String skills=edtTxtSkills.getText().toString().trim();
        String languages=edtTxtLanguages.getText().toString().trim();
        String references=edtTxtReferences.getText().toString().trim();

        cv.setEducation(education);
        cv.setExperience(experience);
        cv.setSkills(skills);
        cv.setLanguages(languages);
        cv.setReferences(references);

        flag=false;
        if (rememberInfo2.isChecked()){
            if (!flag) {
                Gson gson=new Gson();
                String json= gson.toJson(cv);
                editor.putString(CV,json);
                Log.d("TAG", "submit_onClick: json "+json);
                editor.putBoolean(FLAG,true);
                editor.commit();
            }
        }
        if (!rememberInfo2.isChecked()){
            Log.d("TAG", "next_onClick: sup");
            editor.putBoolean(FLAG,false);
            editor.putString(CV,"{\"DOB\":\"1-1-2021\",\"education\":\"\",\"email\":\"\",\"experience\":\"\",\"gender\":\"Male\",\"languages\":\"\",\"name\":\"\",\"references\":\"\",\"skills\":\"\"}");
            editor.commit();
        }
        finish();
        System.exit(0);

    }
}