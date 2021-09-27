package com.example.kuniyakino;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {

    SQLiteDatabase mDatabase;
    DBHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
    }

    public void onCreateNewAccount(View view) {
        dbHelper = new DBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        // Get user input from the edittext components
        EditText name = (EditText) findViewById(R.id.inputName);
        EditText email = (EditText) findViewById(R.id.inputEmailAddress);
        EditText mobile = (EditText) findViewById(R.id.inputMobileNumber);
        EditText password = (EditText) findViewById(R.id.inputPassword);
        EditText verifyPassword = (EditText) findViewById(R.id.inputPassword2);

        // Regular expression to check name consist of any special characters
        String nameRegex = "^[a-zA-Z\\s]+";
        Pattern namePattern = Pattern.compile(nameRegex);
        Matcher nameMatcher = namePattern.matcher(name.getText().toString());

        // Regular expression to check email has @ and . symbol
        String emailRegex = "^(.+)@(.+)$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email.getText().toString());

        try {
            // Verify all inputs are filled
            if (name.getText().toString().isEmpty() ||
                    email.getText().toString().isEmpty() ||
                    mobile.getText().toString().isEmpty() ||
                    password.getText().toString().isEmpty() ||
                    verifyPassword.getText().toString().isEmpty()) {
                throw new FailedVerifyInputException("You must fill in all the fields.");
            }

            if (!nameMatcher.matches()) {
                throw new FailedVerifyInputException("No special characters are allowed in your name.");
            }

            int mobileNumber = Integer.parseInt(mobile.getText().toString());

            if (!emailMatcher.matches()) {
                throw new FailedVerifyInputException("Please enter a valid email address.");
            }

            if (!password.getText().toString().equalsIgnoreCase(verifyPassword.getText().toString())) {
                throw new FailedVerifyInputException("Passwords entered are not the same.");
            }

            // Insert new account credentials into the database
            boolean isInsert = dbHelper.insertAccountData(email.getText().toString(),
                    name.getText().toString(),
                    password.getText().toString(),
                    mobile.getText().toString());

            if (isInsert) displayToast("Account created.");
            else displayToast("Duplicated Email.");



        } catch (FailedVerifyInputException e) {
            displayToast(e.getMessage());
        } catch (NumberFormatException e) {
            displayToast("Mobile Number must be numerical.");
        } catch (Exception e) {
            displayToast("A problem occurred: " + e);
        }
    }

    public void onCancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void displayToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG);
        toast.show();
    }
}
