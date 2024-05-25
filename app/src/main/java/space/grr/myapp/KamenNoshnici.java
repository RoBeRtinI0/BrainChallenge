package space.grr.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class KamenNoshnici extends AppCompatActivity {
    private ImageButton rock, paper, sizors;
    private ImageView resultYou, resultRobot;
    private Button start,restart;
    private TextView resultWinner, timer, title;
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.kamen_noshnici);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button bt_bck = (Button) findViewById(R.id.back_manu);
        bt_bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent4 = new Intent(KamenNoshnici.this, ChooseGameMenu.class);
                    startActivity(intent4);
                    finish();
                }catch (Exception e){
                    //`;br
                }

            }
        });

        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        sizors = findViewById(R.id.sizors);
        resultWinner = findViewById(R.id.result);
        timer = findViewById(R.id.timer);
        title=findViewById(R.id.title);
        resultYou = findViewById(R.id.you);
        resultRobot = findViewById(R.id.enemy);
        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultYou.setImageResource(R.drawable.rock3);
                playGame("Камень");
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultYou.setImageResource(R.drawable.paper3);
                playGame("Бумага");
            }
        });

        sizors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultYou.setImageResource(R.drawable.sizors3);
                playGame("Ножницы");
            }
        });
    }
    private void start(){

    }
    @SuppressLint("SetTextI18n")
    private void playGame(String playerChoice) {
        String[] choices = {"Камень", "Бумага", "Ножницы"};
        Random random = new Random();
        String computerChoice = choices[random.nextInt(choices.length)];
        String result;
        if(computerChoice=="Камень")resultRobot.setImageResource(R.drawable.rock3);
        else if(computerChoice=="Ножницы")resultRobot.setImageResource(R.drawable.sizors3);
        else resultRobot.setImageResource(R.drawable.paper3);
        if (playerChoice.equals(computerChoice)) {
            result = "Ничья!";
        } else if ((playerChoice.equals("Камень") && computerChoice.equals("Ножницы")) ||
                (playerChoice.equals("Бумага") && computerChoice.equals("Камень")) ||
                (playerChoice.equals("Ножницы") && computerChoice.equals("Бумага"))) {
            result = "Вы победили!";
        } else {
            result = "Вы проиграли!";
        }
        resultWinner.setText("Вы выбрали: " + playerChoice + "\nКомпьютер выбрал: " + computerChoice + "\n" + result);
    }
    public void waitFiveSeconds() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Выполнение действия
                title.setText("");
                resultWinner.setText("");
                timer.setText("");
                resultYou.setImageResource(R.drawable.empty1);
                resultRobot.setImageResource(R.drawable.empty1);
            }
        }, 5000); // Пауза в 5 секунды
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