package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView height_display,weight_display;
    private SeekBar seekBar;

    private ImageView increment,decrement;
    private Button btn;

    private double result;

    private int height_val=170;
    private int weight_val=65;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        height_display=findViewById(R.id.height_preview);
        weight_display=findViewById(R.id.weight_preview);
        seekBar=findViewById(R.id.height_seekbar);
        btn=findViewById(R.id.button);
        //init
        weight_display.setText(weight_val+ " kg");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                height_val=i;
                height_display.setText(i+" cm");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        increment=findViewById(R.id.increment);
        decrement=findViewById(R.id.decrement);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementValue();
            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementValue();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result=calculateBMI();
//                Toast.makeText(MainActivity.this, "Answer :"+result, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this, ResultPage.class);
                intent.putExtra("BMI",result);
                startActivity(intent);
            }
        });
    }

    private void incrementValue(){
        if(weight_val==200)
        {
            weight_val=200;

        }else{
            weight_val++;
        }
        weight_display.setText(weight_val+ " kg");
    }
    private void decrementValue(){
        if(weight_val==20)
        {
            weight_val=20;
        }
        else
        {
            weight_val--;
        }
        weight_display.setText(weight_val +" kg");
    }
    private double calculateBMI(){
        double res= (double) weight_val /(((double) height_val /100)*((double) height_val /100));
        String formattedRes=String.format("%.1f",res);
        res=Double.parseDouble(formattedRes);
        return res;
    }
}