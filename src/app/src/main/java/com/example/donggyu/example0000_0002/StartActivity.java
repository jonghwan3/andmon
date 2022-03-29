package com.example.donggyu.example0000_0002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by on 2016-10-29.
 */
public class StartActivity extends Activity{

    Button start_button;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start_button=(Button)findViewById(R.id.button);//여기를 클릭하세요 버튼
        button4=(Button)findViewById(R.id.button4);



        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,Main2Activity.class);
                startActivity(intent);
                finish();

            }
        });

    }

}
