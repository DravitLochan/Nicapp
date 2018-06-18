package intern.nicapp.LoginRegister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yugank on 6/8/18.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_URL ="https://gargyashelly.000webhostapp.com/register.php";
    private Map<String, String> params;


    public RegisterRequest(String name, String username, int age, String password, Response.Listener<String> listener)
    {
        super(Method.POST, REGISTER_URL, listener, null);
        params= new HashMap<>();
        params.put("name",name);
        params.put("username",username);
        params.put("password",password);
        params.put("age",age+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
