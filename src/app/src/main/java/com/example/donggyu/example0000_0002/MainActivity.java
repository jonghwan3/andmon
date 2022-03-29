package com.example.donggyu.example0000_0002;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int index = (int) (Math.random() * 5);
    int picture = index;

    Animation animation; //오른쪽으로 이동
    Animation animationleft; //오른쪽에서 왼쪽으로
    Animation animationleft_base; //왼쪽으로 갔다가 제자리
    ImageButton imageButton; //알 이미지 버튼
    Animation animation_stop; //알 애니메이션 정지
    Button choiceButton; //안드몬 선택 버튼
    Button againButton; //다시뽑기 버튼

    final int[] imageArray = new int[6]; //알깨지는 횟수

    int Count = 0; //알깨지는 횟수
    int and1 = 0;
    int and2 = 0;
    int and3 = 0;
    int and4 = 0;
    int and5 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animationleft_base = AnimationUtils.loadAnimation(this, R.anim.animationleft_base);
        animationleft = AnimationUtils.loadAnimation(this, R.anim.animationleft);
        animation_stop=AnimationUtils.loadAnimation(this,R.anim.animation_stop);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation);//애니메이션
        imageButton = (ImageButton) findViewById(R.id.imageButton);//알 이미지 버튼
        choiceButton =(Button)findViewById(R.id.choiceButton);//안드몬 선택 버튼
        againButton = (Button)findViewById(R.id.againButton);//다시뽑기 버튼

        and1 = R.drawable.andmon;//기본 안드몬
        and2 = R.drawable.andmontwo;//초콜렛 안드몬
        and3 = R.drawable.andmonthree;//한복 안드몬
        and4 = R.drawable.andmonfour;//기모노 안드몬
        and5 = R.drawable.andmonfive;//군인 안드몬

        imageArray[0] = R.drawable.eggone;
        imageArray[1] = R.drawable.eggtwo;
        imageArray[2] = R.drawable.eggthree;
        imageArray[3] = R.drawable.eggfour;
        imageArray[4] = R.drawable.eggfive;
        imageArray[5] = R.drawable.eggsix;
        animation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }
            @Override
            public void onAnimationEnd(Animation animation)
            {
                imageButton.startAnimation(animationleft);
            }
            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
        });
        animationleft.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }
            @Override
            public void onAnimationEnd(Animation animation)
            {
                imageButton.startAnimation(animationleft_base);
            }
            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
        });
        animation_stop.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }
            @Override
            public void onAnimationEnd(Animation animation)
            {
                imageButton.setBackgroundResource(0);
            }
            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
        });
        //부화하는 애니메이션
        imageButton.setOnClickListener(new View.OnClickListener()//알을 클릭 했을 시
        {
            @Override
            public void onClick(View v)
            {
                int id = v.getId();
                imageButton.startAnimation(animation);//알 깨지는 애니메이션 시작
                if (id == R.id.imageButton)
                {
                    if (Count == 0)
                    {
                        imageButton.setImageResource(imageArray[0]);
                        Count = 1;
                    }
                    else if (Count == 1)
                    {
                        imageButton.setImageResource(imageArray[1]);
                        Count = 2;
                    }
                    else if (Count == 2)
                    {
                        imageButton.setImageResource(imageArray[2]);
                        Count = 3;
                    }
                    else if (Count == 3)
                    {
                        imageButton.setImageResource(imageArray[3]);
                        Count = 4;
                    }
                    else if (Count == 4)
                    {
                        imageButton.setImageResource(imageArray[4]);
                        Count = 5;
                    }
                    else if (Count == 5)
                    {
                        imageButton.setImageResource(imageArray[5]);
                        Count = 6;
                    }
                    if (Count == 6)//6번 터치 하면 알이 깨져 랜덤으로 안드몬이 나온다.
                    {
                        imageButton.startAnimation(animation_stop);
                        if (picture == 0)
                        {
                            imageButton.setImageResource(and2);
                        }
                        else if (picture == 1)
                        {
                            imageButton.setImageResource(and1);
                        }
                        else if (picture == 2)
                        {
                            imageButton.setImageResource(and3);
                        }
                        else if (picture == 3)
                        {
                            imageButton.setImageResource(and4);
                        }
                        else if (picture == 4)
                        {
                            imageButton.setImageResource(and5);
                        }
                        Toast.makeText(MainActivity.this, "부화했습니다.", Toast.LENGTH_SHORT).show();//부화했다는 토스트
                        choiceButton.setVisibility(View.VISIBLE);//선택 버튼 보여주기
                        choiceButton.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {//선택 버튼
                                int check_start=1;
                                Toast.makeText(MainActivity.this, "선택했습니다.", Toast.LENGTH_SHORT).show();//선택했다는 토스트
                                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                                intent.putExtra("value",picture);
                                intent.putExtra("value2",check_start);
                                startActivity(intent);
                                finish();
                            }
                        });
                        againButton.setVisibility(View.VISIBLE);//숨겨져 있던 다시고르기 버튼 보여주기
                        againButton.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            { //다시고르기 버튼
                                Toast.makeText(MainActivity.this,"다시 고르기를 선택하셨습니다.",Toast.LENGTH_SHORT).show();//다시고르기 토스트
                                Intent intent = new Intent(MainActivity.this,MainActivity.class);//같은 액티비티를 띄워준다.
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                }
            }
        });
    }
}
