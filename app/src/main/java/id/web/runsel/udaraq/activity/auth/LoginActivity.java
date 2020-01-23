package id.web.runsel.udaraq.activity.auth;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import id.web.runsel.udaraq.R;
import id.web.runsel.udaraq.activity.DashboardActivity;
import id.web.runsel.udaraq.extras.AppPreference;
import id.web.runsel.udaraq.model.User;
import id.web.runsel.udaraq.response.UserResponse;
import id.web.runsel.udaraq.service.AuthService;
import id.web.runsel.udaraq.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public static AppPreference appPreference;
    private EditText editTextEmail, editTextPassword;
    private Button btnLogin;
    private CircularProgressButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appPreference = new AppPreference(this);

        if(appPreference.getLoginStatus()) {
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            finish();
            startActivity(intent);
        }

        setContentView(R.layout.activity_login);
        changeStatusBarColor();
        appPreference = new AppPreference(this);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.cirLoginButton);
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onRegisterClick(View View){
        Intent intent = new Intent(this, RegisterActivity.class);
        finish();
        startActivity(intent);
        //startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

    public void onLoginCLick(View View) {
        btn = findViewById(R.id.cirLoginButton);
        btn.startAnimation();

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        AuthService api = RetrofitInstance.getClient().create(AuthService.class);

        User user = new User(email, password);

        Call<UserResponse> call = api.loginUser(user);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()) {
                    try{
                        btn.revertAnimation();

                        //Save login status
                        appPreference.setLoginStatus(true);
                        appPreference.setToken(response.body().getToken());

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        finish();
                        startActivity(intent);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    btn.revertAnimation();
                    //Log.e("response-failure", call.toString());
                    Toast.makeText(getApplicationContext(), "Authentication failed!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                btn.revertAnimation();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
