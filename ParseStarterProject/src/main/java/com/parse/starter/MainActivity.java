package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

  Boolean loginModeActive = false; // true when returning users are trying to login

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setTitle("Wattsapp - Login or Sign up");
    redirectIfLoggedIn(); // check if current user is logged in

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

  public void redirectIfLoggedIn() {
    // Logged-in case:
    if (ParseUser.getCurrentUser() != null) {
      Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
      startActivity(intent);
    }
  }

  /*
   * This method renders the 'login/sign up' button and 'Or sign up/login' text view in
   * MainActivity.
   */
  public void ToggleLoginMode(View view) {
    Button loginSignupButton = (Button) findViewById(R.id.loginSignupButton);
    TextView toggleLoginModeTextView = (TextView) findViewById(R.id.toggleLoginMode);

    if (loginModeActive) {
      loginModeActive = false;
      loginSignupButton.setText("Sign Up");
      toggleLoginModeTextView.setText("Or, Login");
    } else {
      loginModeActive = true;
      loginSignupButton.setText("Login");
      toggleLoginModeTextView.setText("Or, Sign Up");
    }
  }

  public void SignupLogin(View view) {

    EditText username = (EditText)findViewById(R.id.username);
    EditText password = (EditText)findViewById(R.id.password);

    if (loginModeActive) {

      ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {

        @Override
        public void done(ParseUser user, ParseException e) {
          if (e == null) {
            // logged in case
            Log.i("Info", "user logged in");
            redirectIfLoggedIn();
          } else {
            // Could not log in case:
            String message = e.getMessage();
            if (message.toLowerCase().contains("java")) {
              message = e.getMessage().substring(e.getMessage().indexOf(" "));
            }
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
          }
        }
      });

    } else {

      ParseUser user = new ParseUser();

      user.setUsername(username.getText().toString());
      user.setPassword(password.getText().toString());

      user.signUpInBackground(new SignUpCallback() {

        @Override
        public void done(ParseException e) {
          if (e == null) {

            Log.i("Info", "user signed up");
            redirectIfLoggedIn();

          } else {

            String message = e.getMessage();
            if (message.toLowerCase().contains("java")) {
              message = e.getMessage().substring(e.getMessage().indexOf(" "));
            }

            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
          }
        }
      });
    }
  }
}
