package intern.nicapp.LoginRegister;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import intern.nicapp.R;
import intern.nicapp.userAreaActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText loginUsername= (EditText) findViewById(R.id.login_username);
        final EditText loginPassword= (EditText) findViewById(R.id.login_password);

        final Button bRegister= (Button) findViewById(R.id.login_button);
        final TextView registerLink= (TextView) findViewById(R.id.signup_text);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent= new Intent(LoginActivity.this, registerActivity.class);
                startActivity(registerIntent);
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = loginUsername.getText().toString();
                final String password = loginPassword.getText().toString();

                Response.Listener<String> responseListener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonreponse  = new JSONObject(response);
                            boolean success= jsonreponse.getBoolean("success");
                            if(success)
                            {
                                String name= jsonreponse.getString("name");
                                Intent intent= new Intent(LoginActivity.this, userAreaActivity.class);
                                intent.putExtra("name", name);

                                startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest= new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
