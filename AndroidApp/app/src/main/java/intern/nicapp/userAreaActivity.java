package intern.nicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class userAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        TextView nameTV= (TextView) findViewById(R.id.user_tv);

        Intent intent= getIntent();
        String name= intent.getStringExtra("name");
        // int age= intent.getIntExtra("age",-1)'

        nameTV.setText(name);
    }
}
