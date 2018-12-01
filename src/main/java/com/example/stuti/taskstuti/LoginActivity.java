package com.example.stuti.taskstuti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    /*@BindView(R.id.buttonFacebookSignIn)
    LoginButton buttonFacebookSignIn;
    @BindView(R.id.linearLayoutFacebook)
    LinearLayout linearLayoutFacebook;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.linearLayoutFacebook)
    public void clickButton(){
        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
    }

}
