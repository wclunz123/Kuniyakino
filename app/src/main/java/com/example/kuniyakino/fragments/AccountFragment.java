package com.example.kuniyakino.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kuniyakino.CreateAccount;
import com.example.kuniyakino.DBHelper;
import com.example.kuniyakino.MainActivity;
import com.example.kuniyakino.MainPage;
import com.example.kuniyakino.R;
import com.example.kuniyakino.model.Account;

public class AccountFragment extends Fragment {

    private View view;
    DBHelper dbHelper;
    String currentUser;
    Account currentUserAccount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        dbHelper = new DBHelper(getContext());
        currentUser = ((MainPage)getActivity()).getCurrentUser();
        currentUserAccount = dbHelper.getUser(currentUser);

        // Get the account data from the database and display within the EditText component
        EditText inputName = view.findViewById(R.id.inputName);
        inputName.setText(currentUserAccount.getName());
        EditText inputMobileNumber = view.findViewById(R.id.inputMobileNumber);
        inputMobileNumber.setText(String.valueOf(currentUserAccount.getMobileNumber()));
        EditText addressLine1 = view.findViewById(R.id.addressLine1);
        addressLine1.setText(currentUserAccount.getAddressLine1());
        EditText addressLine2 = view.findViewById(R.id.addressLine2);
        addressLine2.setText(currentUserAccount.getAddressLine2());
        EditText paymentMethod = view.findViewById(R.id.paymentMethod);
        paymentMethod.setText(currentUserAccount.getPaymentMethod());
        EditText cardNo = view.findViewById(R.id.cardNo);
        cardNo.setText(currentUserAccount.getCardNo());

        // Setup on click listener for update account button
        Button updateAccountBtn = (Button) getView().findViewById(R.id.updateAccount);
        updateAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update database on new user account credentials
                boolean result = dbHelper.updateAccount(currentUser,
                        inputName.getText().toString(),
                        addressLine1.getText().toString(),
                        addressLine2.getText().toString(),
                        Long.parseLong(inputMobileNumber.getText().toString()),
                        paymentMethod.getText().toString(),
                        cardNo.getText().toString());

                // Display the update result
                if (result) {
                    Toast toast = Toast.makeText(getContext(),
                            "Account Update Success!",
                            Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getContext(),
                            "Account Update Failed.",
                            Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        // Setup on click listener for log out button
        Button logoutBtn = (Button) getView().findViewById(R.id.logoutButton);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}