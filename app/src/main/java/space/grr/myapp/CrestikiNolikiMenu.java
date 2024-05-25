package space.grr.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Scanner;

public class CrestikiNolikiMenu extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    Scanner in = new Scanner(System.in);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.krestikinoliki_menu);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button BT1 = findViewById(R.id.ChangeActivityComputer);
        BT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent1 = new Intent(CrestikiNolikiMenu.this, CrestikiNolikiGame.class);
                    startActivity(intent1);
                    finish();
                }catch (Exception e){
                    //past
                }
            }
        });
        Button BT2 = (Button) findViewById(R.id.ChangeActivityPlayer);
        BT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button menu_back = (Button) findViewById(R.id.to_menu);
        menu_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent2 = new Intent(CrestikiNolikiMenu.this, ChooseGameMenu.class);
                    startActivity(intent2);
                    finish();
                }catch (Exception e){
                    //past
                }

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