package com.example.examcreationmobilapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textLevel;
    TextView textRightAnswered;
    TextView textQuestion;


    Button buttonOp1;
    Button buttonOp2;
    Button buttonOp3;

    int level = 0;
    int great = 0;
    int rightAnswer = 0;
    String realOperation = "";



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



        Objects.requireNonNull(getSupportActionBar()).hide();

        textLevel = findViewById(R.id.textQuestionNumber);
        textRightAnswered = findViewById(R.id.textRightAnswered);
        textQuestion = findViewById(R.id.textQuestion);

        buttonOp1 = findViewById(R.id.buttonOptional1);
        buttonOp2 = findViewById(R.id.buttonOptional2);
        buttonOp3 = findViewById(R.id.buttonOptional3);

        textLevel.setText("Q : "+ level+" / 5");
        textRightAnswered.setText("RA"+ great + " / 5");

        if(level < 5){
            getARandomQuestion();
        }


    }

    private void getARandomQuestion() {

        buttonOp1.setBackgroundResource(R.drawable.buttons_option_bg);
        buttonOp2.setBackgroundResource(R.drawable.buttons_option_bg);
        buttonOp3.setBackgroundResource(R.drawable.buttons_option_bg);


        int firstNumber = new Random().nextInt(20);
        int secondNumber = new Random().nextInt(20);


        int operation = new Random().nextInt(3) + 1;

        int operationA = new Random().nextInt(100);
        int operationB = new Random().nextInt(100);

        if (operation == 1){
            realOperation = "+";
            rightAnswer = firstNumber + secondNumber;
            textQuestion.setText(firstNumber + " " + realOperation + " " + secondNumber + " = ?");
        }else {
            if (operation == 2){
                realOperation = "*";
                rightAnswer = firstNumber * secondNumber;
                textQuestion.setText(firstNumber + " " + realOperation + " " + secondNumber + " = ?");
            }else{
                if (operation == 3){
                    realOperation = "-";
                    if (firstNumber < secondNumber){
                        rightAnswer = secondNumber - firstNumber;
                        textQuestion.setText(secondNumber+ " "+ realOperation + " " + firstNumber+ " = ?");
                    }else {
                        rightAnswer = firstNumber - secondNumber;
                        textQuestion.setText(firstNumber+ " "+ realOperation + " " + secondNumber+ " = ?");
                    }
                }
            }
        }

        int position = new Random().nextInt(3)+1;

        if(position == 1){
            buttonOp1.setText(""+rightAnswer);
            buttonOp2.setText(""+optionA);
            buttonOp3.setText(""+optionB);
        }else{
            buttonOp1.setText(""+optionA);
            if (position == 2){
                buttonOp2.setText(""+rightAnswer);
                buttonOp3.setText(""+optionB);
            }else{
                buttonOp2.setText(""+optionB);
                buttonOp3.setText(""+rightAnswer);
            }
        }

        buttonOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonOp1.getText().equals(""+rightAnswer)){
                    buttonOp1.setBackgroundResource(R.drawable.right_answer_bg);
                    great = great + 1;
                    level = level + 1;
                    textLevel.setText("Q : "+ level + " / 10");
                    textRightAnswered.setText("RA : "+ great +" / 10");
                }else {
                    level = level + 1;
                    textLevel.setText("Q : "+ level + " / 10");
                    buttonOp1.setBackgroundResource(R.drawable.wrong_answer_bg);
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (level < 10){
                            getARandomQuestion();
                        }else {
                            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                            intent.putExtra( "RA", great);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, 1000);
            }
        });




    }
}