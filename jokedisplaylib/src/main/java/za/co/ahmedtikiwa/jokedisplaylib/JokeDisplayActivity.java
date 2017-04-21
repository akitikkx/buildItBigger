package za.co.ahmedtikiwa.jokedisplaylib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "joke";
    private String joke;
    private TextView jokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        jokeText = (TextView) findViewById(R.id.jokeText);

        Intent intent  = getIntent();
        joke = intent.getStringExtra(JOKE_EXTRA);

        if (joke != null) {
            jokeText.setText(joke);
        }
    }
}
