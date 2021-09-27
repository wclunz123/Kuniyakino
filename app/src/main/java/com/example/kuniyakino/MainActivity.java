package com.example.kuniyakino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText userEmail;
    EditText password;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLogin(View view) {
        // Get user input from the edittext components
        userEmail = (EditText) findViewById(R.id.inputLoginEmail);
        password = (EditText) findViewById(R.id.inputLoginPassword);
        dbHelper = new DBHelper(this);

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userEmail.getText().toString());

        // Verify user input
        try {
            if (userEmail.getText().length() == 0 || userEmail.getText().toString().isEmpty()) { // Email not empty
                throw new FailedVerifyInputException("You must enter your email address to login.");
            }

            if (!matcher.matches()) {
                throw new FailedVerifyInputException("Please enter a valid email address.");
            }

            if (password.getText().length() == 0 || password.getText().toString().isEmpty()) { // Password not empty
                throw new FailedVerifyInputException("You must enter your password to login.");
            }

            if (password.getText().length() < 8) { // Password at least 8 characters
                throw new FailedVerifyInputException("Password must be at least 8 characters.");
            }

            // Check if the email and password entered exist in the database
            boolean accountExist = dbHelper.getUserAccount(userEmail.getText().toString(), password.getText().toString());
            if (accountExist) {
                displayToast("Login Success");
                Intent intent = new Intent(this, MainPage.class);
                intent.putExtra("CurrentUser", userEmail.getText().toString());
                startActivity(intent);
            } else {
                displayToast("Incorrect username or password.");
            }

        } catch (FailedVerifyInputException e) {
            displayToast(e.getMessage());
        } catch (Exception e) {
            displayToast("A problem occurred: " + e);
        }
    }

    public void onCreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    // Method used to display toast message
    private void displayToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG);
        toast.show();
    }

    // Prevent the back button after logout from the application
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}