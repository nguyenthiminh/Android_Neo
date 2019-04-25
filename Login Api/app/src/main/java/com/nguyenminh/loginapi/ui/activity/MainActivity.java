package com.nguyenminh.loginapi.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.nguyenminh.loginapi.R;
import com.nguyenminh.loginapi.model.entity.UserFacebook;
import com.nguyenminh.loginapi.model.entity.UserGoogle;
import com.nguyenminh.loginapi.presenter.LoginPresenter;
import com.nguyenminh.loginapi.presenter.LoginPresenterCom;
import com.nguyenminh.loginapi.ui.MainView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {
    @BindView(R.id.btn_signIn)
    SignInButton btn_signIn;

    @BindView(R.id.btn_facebook)
    LoginButton btn_facebook;

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private LoginPresenter presenter;
    private CallbackManager mCallbackManager;
    private String name, first_name, last_name, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        presenter  = new LoginPresenterCom(this);
//        printKeyHash(this);


        btn_facebook.setReadPermissions(Arrays.asList("email", "public_profile"));
        mCallbackManager = CallbackManager.Factory.create();

        btn_facebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                useLoginInformation(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                final GoogleSignInAccount account = task.getResult(ApiException.class);
                UserGoogle userGoogle = new UserGoogle();
                userGoogle.setId(account.getId());
                userGoogle.setName(account.getDisplayName());
                userGoogle.setEmail(account.getEmail());
                userGoogle.setAvatar(account.getPhotoUrl()+"");

                presenter.doLogin(userGoogle);

                updateUI(account);
            } catch (ApiException e) {
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        updateUI(GoogleSignIn.getLastSignedInAccount(this));

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void updateUI(@Nullable GoogleSignInAccount account) {
        if(account != null){
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user", account);
            startActivity(intent);
            finish();
        }
        else {

        }
    }

    @OnClick(R.id.btn_signIn)
    public void clickSignIn(){
        signIn();
    }

    @Override
    public void isLogin(boolean isLogin) {

    }

    private void useLoginInformation(AccessToken accessToken) {

        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    id          =   object.getString("id");
                    name        =   object.getString("name");
                    first_name  =   object.optString("first_name");
                    last_name   =   object.optString("last_name");

                    UserFacebook userFacebook = new UserFacebook();
                    userFacebook.setId(id);
                    userFacebook.setName(name);
                    userFacebook.setFirstName(first_name);
                    userFacebook.setLastName(last_name);
                    presenter.doLoginFace(userFacebook);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,first_name,last_name");
        request.setParameters(parameters);
        request.executeAsync();

        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
        finish();
    }
}