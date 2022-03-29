package com.example.donggyu.example0000_0002;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    Animation animation;//오른쪽으로 이동
    Animation animation2;
    Animation animationleft;//오른쪽에서 왼쪽으로
    Animation animationleft_base;//왼쪽으로 갔다가 제자리
    ImageButton imageButton;//알 이미지 버튼
    Animation animation_stop;
    Button button;
    Button button2;
    Button button3;
    Button button5;
    Button button6;
    final int[] imageArray = new int[7]; //알깨지는 횟수
    SharedPreferences setting;
    SharedPreferences.Editor editor;


    int Count = 0;//알깨지는 횟수
    //int And = 0;
    //int andm = 0;
    int evol = 0;//대화주기 진화
    int evol_eat = 0;//밥주기 진화
    int evol_fin = 0;//진화제한선
    int picture = 100;//이전에 선택한 안드몬
    boolean a = false;
    int checkpoint=0;
    int check_start=0;//MainActivity에서 넘어왔는지 StartActivity에서 넘어왔는지
    int data=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        animationleft_base = AnimationUtils.loadAnimation(this, R.anim.animationleft_base);
        animationleft = AnimationUtils.loadAnimation(this, R.anim.animationleft);
        animation_stop = AnimationUtils.loadAnimation(this, R.anim.animation_stop);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation);//애니메이션
        animation2 = AnimationUtils.loadAnimation(this, R.anim.animation2);//애니메이션2

        imageButton = (ImageButton) findViewById(R.id.imageButton);//알 이미지 버튼
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        setting=getSharedPreferences("setting",0);
        editor=setting.edit();
        //setContentView(R.layout.activity_main);

        //andm = R.drawable.andmontwo;

        //And = and_mon;
        imageArray[0] = R.drawable.eating_and;
        //imageArray[1] = R.drawable.eating_and;
        //imageArray[2] = R.drawable.eating_and;
        //imageArray[3] = R.drawable.eating_and;
        //imageArray[4] = R.drawable.eating_and;
        //imageArray[5] = R.drawable.and_mon;
        checkpoint=setting.getInt("Check",checkpoint);
        Intent intent = getIntent();
        check_start=intent.getIntExtra("value2",check_start);
        if(checkpoint==3 && check_start==0){
            data=setting.getInt("Store",data);
            //data=data_load;
            evol=setting.getInt("Store1",evol);
            evol_eat=setting.getInt("Store2",evol_eat);
            evol_fin=setting.getInt("Store3",evol_fin);

        }else {
            Intent intent1 = getIntent();
            data = intent1.getIntExtra("value", picture);
        }

        if (data == 0) {
            imageArray[6] = R.drawable.andmontwo;
            imageArray[5] = R.drawable.andmontwo_evol;

        } else if (data == 1) {
            imageArray[6] = R.drawable.andmon;
            imageArray[5] = R.drawable.andmon_evol;
        } else if (data == 2) {
            imageArray[6] = R.drawable.andmonthree;
            imageArray[5] = R.drawable.andmonthree_evol;
        } else if (data == 3) {
            imageArray[6] = R.drawable.andmonfour;
            imageArray[5] = R.drawable.andmonfour_evol;

        } else if (data == 4) {
            imageArray[6] = R.drawable.andmonfive;
            imageArray[5] = R.drawable.andmonfive_evol;
        } else if (data==100) {
            Toast.makeText(Main2Activity.this, "저장된 자료가 없습니다.", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(Main2Activity.this,StartActivity.class);
            startActivity(intent1);
            finish();
        }

        imageButton.setImageResource(imageArray[6]);
        imageButton.setBackgroundResource(imageArray[6]);
        if (evol >= 10 && evol_eat >= 9 && evol_fin == 1) {
            imageArray[6] = imageArray[5];
            imageButton.setImageResource(imageArray[6]);
            imageButton.startAnimation(animation_stop);
        }


        // imageArray[7] = 0;


        //-------------------------------------------------------------------
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                imageButton.setBackgroundResource(imageArray[0]);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageButton.startAnimation(animationleft);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageButton.startAnimation(animationleft);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animationleft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageButton.startAnimation(animationleft_base);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation_stop.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageButton.setBackgroundResource(imageArray[6]);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //부화하는 애니메이션
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                imageButton.startAnimation(animation);
                evol_eat++;


                if (id == R.id.button) {
                    if (Count == 0) {
                        imageButton.setImageResource(imageArray[0]);
                        Toast.makeText(Main2Activity.this, "히힛 조금만 더 줘요~", Toast.LENGTH_SHORT).show();
                        Count = 1;
                    } else if (Count == 1) {
                        imageButton.setImageResource(imageArray[0]);
                        Toast.makeText(Main2Activity.this, "히힛 조금만 더 줘요~", Toast.LENGTH_SHORT).show();
                        Count = 2;
                    } else if (Count == 2) {
                        imageButton.setImageResource(imageArray[6]);
                        //Toast.makeText(Main2Activity.this, "히힛 조금만 더 줘요~", Toast.LENGTH_SHORT).show();
                        Count = 3;
                    }
                    if (Count == 3) {
                        imageButton.startAnimation(animation_stop);
                        //imageButton.setBackgroundResource(imageArray[5]);
                        Toast.makeText(Main2Activity.this, "아이 배불러~감사합니다~~", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(MainActivity.this,"부화했습니다.",Toast.LENGTH_SHORT).show();
                        //imageButton.setImageResource(And);
                        //imageButton.setImageResource(imageArray[5]);
                        Count = 0;

                    }
                }

            /*
               if(id==R.id.imageButton) {


                   Count++;
                   imageButton.setImageResource(imageArray[Count]);


                }
                if(Count==5)
                {
                   // Count=7;

                }

            */
            }


        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id0 = v.getId();
                imageButton.startAnimation(animation2);
                Toast.makeText(Main2Activity.this, "운동중이랍니다~", Toast.LENGTH_LONG).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id1 = v.getId();
                int index = (int) (Math.random() * 5) + 1;
                int picture = index;
                evol++;

                if (picture == 1) {
                    Toast.makeText(Main2Activity.this, "날씨가 추우니 감기 조심하세요~^^", Toast.LENGTH_SHORT).show();

                } else if (picture == 2) {
                    Toast.makeText(Main2Activity.this, "오늘 메뉴는 뭐예요?", Toast.LENGTH_SHORT).show();
                } else if (picture == 3) {
                    Toast.makeText(Main2Activity.this, "제 이름은 안드몬이랍니다~", Toast.LENGTH_SHORT).show();
                } else if (picture == 4) {
                    Toast.makeText(Main2Activity.this, "사랑합니다~하핫!", Toast.LENGTH_SHORT).show();
                } else if (picture == 5) {
                    Toast.makeText(Main2Activity.this, "저를 눌러보세요~", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (evol >= 10 && evol_eat >= 9 && evol_fin == 0) {
                    imageArray[6] = imageArray[5];
                    imageButton.setImageResource(imageArray[6]);
                    imageButton.startAnimation(animation_stop);
                    Toast.makeText(Main2Activity.this, "축하합니다! 진화했습니다!", Toast.LENGTH_LONG).show();
                    evol_fin++;

                } else {
                    Toast.makeText(Main2Activity.this, "조건이 만족되지 않았습니다", Toast.LENGTH_SHORT).show();
                }
                ;

            }
        });
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkpoint=3;
                editor.putInt("Store",data);
                editor.putInt("Check",checkpoint);
                editor.putInt("Store1",evol_eat);
                editor.putInt("Store2",evol);
                editor.putInt("Store3",evol_fin);

                editor.commit();
                Toast.makeText(Main2Activity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();


            }

        });
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               editor.clear();
                editor.commit();
                Toast.makeText(Main2Activity.this, "초기화했습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main2Activity.this,StartActivity.class);
                startActivity(intent);
                finish();
            }

        });
    }


}

                //imageArray[5]=R.drawable.torch3;
                // imageButton.setImageResource(imageArray[5]);
                //imageButton.startAnimation(animation_stop);
                //imageButton.setBackgroundResource(imageArray[5]);








