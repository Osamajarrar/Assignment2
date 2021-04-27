package project.bzu.assignment2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import project.bzu.assignment2.Models.CV;
import project.bzu.assignment2.R;

public class Summary extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);
        Intent intent=getIntent();
        String name=intent.getStringExtra(NAME);
        String email=intent.getStringExtra(EMAIL);
        String DOB=intent.getStringExtra(DateOfBirth);
        String gender=intent.getStringExtra(GENDER);
        String education=intent.getStringExtra(EDUCATION);
        String experience=intent.getStringExtra(EXPERIENCE);
        String skills=intent.getStringExtra(SKILLS);
        String languages=intent.getStringExtra(LANGUAGES);
        String references=intent.getStringExtra(REFERENCES);
        //String json=intent.getStringExtra(CV);
//        Gson gson=new Gson();
//        project.bzu.assignment2.Models.CV cv=gson.fromJson(json, project.bzu.assignment2.Models.CV.class);
        TextView summary=findViewById(R.id.summary);
        summary.setText(
                "Name= " + name + '\n' +
                "Email= " + email + '\n' +
                "DOB= " + DOB + '\n' +
                "Gender= " + gender + '\n' +
                "Education= " + education + '\n' +
                "Experience= " + experience + '\n' +
                "Skills= " + skills + '\n' +
                "Languages= " + languages + '\n' +
                "References= " + references + '\n');
    }
}