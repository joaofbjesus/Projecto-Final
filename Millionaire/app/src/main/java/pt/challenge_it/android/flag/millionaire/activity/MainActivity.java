package pt.challenge_it.android.flag.millionaire.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pt.challenge_it.android.flag.millionaire.R;
/**
 * First activity to show on Millionaire Application.
 * @author Challenge.IT
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNewGame = (Button) findViewById(R.id.btn_new_game);
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToOpenGameActivity = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intentToOpenGameActivity);
            }
        });

        Button btnSettings = (Button) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
