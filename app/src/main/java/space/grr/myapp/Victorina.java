package space.grr.myapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Scanner;

public class Victorina extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    public int nm_lft;
    public int nm_rhyt;
    public int count =0;
    public Array arr = new Array();
    public Random rndm = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Dialog dialog;


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.viktorina_game);

        TextView text_lvls = findViewById(R.id.text_levels);
        text_lvls.setText(R.string.lvl);

        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        final TextView txt_lft = findViewById(R.id.textleft);
        final TextView txt_rght = findViewById(R.id.textright);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.preview);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалога
        dialog.setCancelable(false);

        TextView btn_cl = (TextView) dialog.findViewById(R.id.btn_cls);
        btn_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent11 = new Intent(Victorina.this, ChooseGameMenu.class);
                    startActivity(intent11);
                    finish();
                }catch (Exception e){
                    //f
                }
                dialog.dismiss();
            }
        });

        Button btnctn = (Button) dialog.findViewById(R.id.btn_cont);
        btnctn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();


        Button btm_bck = (Button) findViewById(R.id.button_bck);
        btm_bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent10 = new Intent(Victorina.this, ChooseGameMenu.class);
                    startActivity(intent10);
                    finish();
                }catch (Exception e){
                    //9
                }
            }
        });

        //прогресс игры
        final int[] progress={
                R.id.point1,R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20
        };

        //анимация
        final Animation a = AnimationUtils.loadAnimation(Victorina.this, R.anim.alpha);


        nm_lft = rndm.nextInt(10);
        img_left.setImageResource(arr.ings[nm_lft]);
        txt_lft.setText(arr.text1[nm_lft]);

        nm_rhyt = rndm.nextInt(10);
        while(nm_lft==nm_rhyt){
            nm_rhyt = rndm.nextInt(10);
        }
        img_right.setImageResource(arr.ings[nm_rhyt]);
        txt_rght.setText(arr.text1[nm_rhyt]);


        //Нажатие на картинки
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);
                    if(nm_lft>nm_rhyt){
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_left.setImageResource(R.drawable.img_false);
                    }
                }else if(event.getAction()== MotionEvent.ACTION_UP){
                    if(nm_lft>nm_rhyt){
                        if(count<20){
                            count=count+1;
                        }
                        for (int i=0; i<20;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_pnt);
                        }
                        for (int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_pnt_green);
                        }
                    }else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count-2;
                            }
                        }
                        for (int i = 0; i < 19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_pnt);
                        }
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_pnt_green);
                        }
                    }
                    if (count == 20) {
                        //8
                    } else {
                        nm_lft = rndm.nextInt(10);
                        img_left.setImageResource(arr.ings[nm_lft]);
                        img_left.startAnimation(a);
                        txt_lft.setText(arr.text1[nm_lft]);
                        nm_rhyt = rndm.nextInt(10);
                        while (nm_lft == nm_rhyt) {
                            nm_rhyt = rndm.nextInt(10);
                        }
                        img_right.setImageResource(arr.ings[nm_rhyt]);
                        img_right.startAnimation(a);
                        txt_rght.setText(arr.text1[nm_rhyt]);

                        img_right.setEnabled(true);
                    }
                }
                return false;
            }
        });

        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    img_left.setEnabled(false);
                    if(nm_lft>nm_rhyt){
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_right.setImageResource(R.drawable.img_false);
                    }
                }else if(event.getAction()== MotionEvent.ACTION_UP){
                    if(nm_lft<nm_rhyt){
                        if(count<20){
                            count=count+1;
                        }
                        for (int i=0; i<20;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_pnt);
                        }
                        for (int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_pnt_green);
                        }
                    }else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count-2;
                            }
                        }
                        for (int i = 0; i < 19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_pnt);
                        }
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_pnt_green);
                        }
                    }
                    if (count == 20) {
                        //8
                    } else {
                        nm_lft = rndm.nextInt(10);
                        img_left.setImageResource(arr.ings[nm_lft]);
                        img_left.startAnimation(a);
                        txt_lft.setText(arr.text1[nm_lft]);
                        nm_rhyt = rndm.nextInt(10);
                        while (nm_lft == nm_rhyt) {
                            nm_rhyt = rndm.nextInt(10);
                        }
                        img_right.setImageResource(arr.ings[nm_rhyt]);
                        img_right.startAnimation(a);
                        txt_rght.setText(arr.text1[nm_rhyt]);

                        img_left.setEnabled(true);
                    }
                }
                return false;
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast = Toast.makeText(getBaseContext(), "Нажмите ещё раз чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}