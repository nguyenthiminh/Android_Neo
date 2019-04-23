package com.nguyenminh.googleapi.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.nguyenminh.googleapi.R;
import com.nguyenminh.googleapi.model.User;
import com.nguyenminh.googleapi.model.UserFacebook;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_signIn) SignInButton btn_signIn;

    @BindView(R.id.btn_facebook) LoginButton btn_facebook;

    private Realm realm;
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager mCallbackManager;
    private String email, name, first_name, last_name, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        FacebookSdk.sdkInitialize(this.getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();

        btn_facebook.setReadPermissions(Arrays.asList("email", "public_profile"));
        btn_facebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest   =   GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback()
                {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response)
                    {
                        Log.d("JSON", ""+response.getJSONObject().toString());

                        try
                        {
                            id          =   object.getString("id");
                            name        =   object.getString("name");
                            first_name  =   object.optString("first_name");
                            last_name   =   object.optString("last_name");

                            Log.d("name", ""+name);
                            Log.d("first_name", ""+first_name);
                            Log.d("last_name", ""+last_name);
                            realm.beginTransaction();

                            realm.executeTransactionAsync(new Realm.Transaction() {
                                @Override
                                public void execute(Realm bgrealm) {
                                    UserFacebook userFacebook = new UserFacebook();
                                    userFacebook.setLastName(last_name);
                                    userFacebook.setFirstNname(first_name);
                                    userFacebook.setName(name);
                                    userFacebook.setId(id);

                                    bgrealm.copyToRealmOrUpdate(userFacebook);
                                }
                            });
                            realm.commitTransaction();

                            LoginManager.getInstance().logOut();
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,first_name,last_name,email");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();

                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);

            }

            @Override
            public void onCancel() {
                Log.d("JSON", ""+"Cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("JSON", ""+exception.toString());
            }
        });

        printKeyHash(MainActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

        }


    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            final GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            realm.beginTransaction();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm bgrealm) {
                    User user = new User();
                    user.setId(account.getId());
                    user.setEmail(account.getEmail());
                    user.setName(account.getDisplayName());
                    user.setAvatar(account.getPhotoUrl()+"");

                    bgrealm.copyToRealmOrUpdate(user);
                }
            });

            realm.commitTransaction();
            updateUI(account);
        } catch (ApiException e) {
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void updateUI(@Nullable GoogleSignInAccount account) {
        if(account != null){
           Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
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

    public String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (android.content.pm.Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest graphRequest   =   GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback()
        {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response)
            {
                Log.d("JSON", ""+response.getJSONObject().toString());

                try
                {
                    id          =   object.getString("id");
                    email       =   object.getString("email");
                    name        =   object.getString("name");
                    first_name  =   object.optString("first_name");
                    last_name   =   object.optString("last_name");

                    realm.beginTransaction();

                    realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm bgrealm) {
                            UserFacebook userFacebook = new UserFacebook();
                            userFacebook.setLastName(last_name);
                            userFacebook.setFirstNname(first_name);
                            userFacebook.setName(email);

                            bgrealm.copyToRealmOrUpdate(userFacebook);
                        }
                    });
                    realm.commitTransaction();

                    LoginManager.getInstance().logOut();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,first_name,last_name,email");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();

        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
        intent.putExtra("first_name", first_name);
        intent.putExtra("last_name", last_name);
        intent.putExtra("email", email);
        startActivity(intent);
    }

}

