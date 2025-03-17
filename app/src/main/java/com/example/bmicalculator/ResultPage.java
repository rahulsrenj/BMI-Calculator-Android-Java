package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultPage extends AppCompatActivity {
    private TextView result,desc;
    private Button btn;
    private double bmiRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        result=findViewById(R.id.result);
        btn=findViewById(R.id.mainPage);
        desc=findViewById(R.id.description);
        setResult();
        setDescription();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ResultPage.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    private void setResult()
    {
        Intent intent=getIntent();
        bmiRes=intent.getDoubleExtra("BMI",0);
        result.setText(bmiRes+ "");
    }
    private void setDescription()
    {
        if(bmiRes<18.5)
        {
            desc.setText("Under Weight");
        }
        else if(bmiRes >=18.5 && bmiRes <=24.9)
        {
            desc.setText("Normal Weight");
        }
        else if(bmiRes>=25 && bmiRes <=29.9)
        {
            desc.setText("Over Weight");
        }
        else{
            desc.setText("Obese");

        }
    }
}