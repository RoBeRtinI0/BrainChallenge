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

public class ChooseGameMenu extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.choose_game_menu);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button btm_exit = (Button) findViewById(R.id.Btmexit);
        btm_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });

        Button btm_tic_nol = (Button) findViewById(R.id.BtmGame);
        btm_tic_nol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(ChooseGameMenu.this, CrestikiNolikiMenu.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    //Пусто
                }

            }
        });

        Button btm_knb = (Button) findViewById(R.id.BtmGame1);
        btm_knb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent5 = new Intent(ChooseGameMenu.this, KamenNoshnici.class);
                    startActivity(intent5);
                    finish();
                }catch (Exception e) {
                    //get
                }
            }
        });

        Button btm_vkt = (Button) findViewById(R.id.BtmGame2);
        btm_vkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent6 = new Intent(ChooseGameMenu.this, Victorina.class);
                    startActivity(intent6);
                    finish();
                }catch (Exception e){
                    //1
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