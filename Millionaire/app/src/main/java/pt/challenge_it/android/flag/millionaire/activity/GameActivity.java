package pt.challenge_it.android.flag.millionaire.activity;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pt.challenge_it.android.flag.millionaire.R;
import pt.challenge_it.android.flag.millionaire.model.Question;
import pt.challenge_it.android.flag.millionaire.provider.OperationsManager;

public class GameActivity extends ActionBarActivity implements View.OnClickListener{

    private Question[] _questions;
    private int currentQuestionIndex;

    private static int[] array = new int[]{0,0,0,0,500,500,500,500,500,4000,4000,4000,4000,4000,1000000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        new GetQuestionsTask().execute();


        final Button btnCinquenta = (Button) findViewById(R.id.btn_ajuda_50);
        btnCinquenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnCinquenta.setVisibility(View.INVISIBLE);

                final Button btnA = (Button)findViewById(R.id.btn_answer_A);
                if ((Boolean)btnA.getTag()){

                }
            }
        });


        Button btnPublico = (Button)findViewById(R.id.btn_ajuda_Publico);
        Button btnTelf = (Button)findViewById(R.id.btn_ajuda_Telf);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        int wonValue = array[currentQuestionIndex];


        // IF TRUE , ANSWER IS CORRECT
        if ((Boolean)view.getTag()){

            if (currentQuestionIndex == _questions.length-1){
                Toast.makeText(getApplicationContext(),"Ganhas-te 100.000 e chegas-te ao fim", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            this.currentQuestionIndex++;
            changeNextQuestion();

            Toast.makeText(getApplicationContext(), "Ganhou " + wonValue, Toast.LENGTH_SHORT).show();


        }else{

            Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_game_over) + wonValue, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void changeNextQuestion(){

        Question question = this._questions[this.currentQuestionIndex];

        // Encontra a TextView e coloca o indentificador
        TextView txtQuestion = (TextView) findViewById(R.id.txt_question);
        txtQuestion.setText(question.getIdentifier() + " - " + question.getText());

        for (Question.Answer answer : question.getAnswers()){

            switch (answer.getIdentifier()){
                case 'A':
                    Button btnA = (Button)findViewById(R.id.btn_answer_A);
                    btnA.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnA.setTag(answer.isCorrect());
                    btnA.setOnClickListener(this);
                    break;
                case 'B':
                    Button btnB = (Button)findViewById(R.id.btn_answer_B);
                    btnB.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnB.setTag(answer.isCorrect());
                    btnB.setOnClickListener(this);
                    break;
                case 'C':
                    Button btnC = (Button)findViewById(R.id.btn_answer_C);
                    btnC.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnC.setTag(answer.isCorrect());
                    btnC.setOnClickListener(this);
                    break;
                case 'D':
                    Button btnD = (Button)findViewById(R.id.btn_answer_D);
                    btnD.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnD.setTag(answer.isCorrect());
                    btnD.setOnClickListener(this);
                    break;
            }
        }

    }


    public class GetQuestionsTask extends AsyncTask<Void, Void,Question[]>{

        @Override
        protected Question[] doInBackground(Void... voids) {

            return OperationsManager.getAllTemp();
        }

        @Override
        protected void onPostExecute(Question[] questions) {
            super.onPostExecute(questions);

            GameActivity.this._questions = questions;
            GameActivity.this.currentQuestionIndex = 0;

            changeNextQuestion();
        }
    }

}
