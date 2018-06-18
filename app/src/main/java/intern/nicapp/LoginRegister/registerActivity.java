package intern.nicapp.LoginRegister;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import intern.nicapp.R;

public class registerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etuserage= (EditText) findViewById(R.id.userage);
        final EditText etname= (EditText) findViewById(R.id.name);
        final EditText etusername= (EditText) findViewById(R.id.username);
        final EditText etuserpassword= (EditText) findViewById(R.id.userpassword);

        final Button bRegister= (Button) findViewById(R.id.reg_button);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = etname.getText().toString();
                final String username = etusername.getText().toString();
                final String password = etuserpassword.getText().toString();
                final int age = Integer.parseInt(etuserage.getText().toString());
                Toast.makeText(registerActivity.this, "Pressed", Toast.LENGTH_SHORT).show();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response);
                            boolean success = jsonresponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(registerActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(registerActivity.this);
                                builder.setMessage("Registration failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(registerActivity.this, "Giving Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(registerActivity.this);
                queue.add(registerRequest);
            }
        });

    }
}
