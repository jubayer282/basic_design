package com.example.app;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity {
    TextInputLayout regName,regUsername,regEmail,regPhoneNo,regPassword;
    Button regBtn,regToLoginBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    private Boolean validateName(){
        String val =regName.getEditText().getText().toString();

        if (val.isEmpty()){
            regName.setError("Filed cannot be empty");
            return false;
        }
        else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateUsername(){
        String val =regUsername.getEditText().getText().toString();
        String noWhiteSpace="\\A\\w{4,20}\\z";

        if (val.isEmpty()){
            regUsername.setError("Filed cannot be empty");
            return false;
        } else if (val.length()>=15) {
            regUsername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("White Space are not allowed");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val =regEmail.getEditText().getText().toString();
        String emailPattern="[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()){
            regEmail.setError("Filed cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            return true;
        }
    }
    private Boolean validatePhoneNo(){
        String val =regPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()){
            regPhoneNo.setError("Filed cannot be empty");
            return false;
        }
        else {
            regPhoneNo.setError(null);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val =regPassword.getEditText().getText().toString();
        String passwordVal="^"+
                //"(?=.*[0-9])"+       //at least 1 digit
                //"(?=.*[a-z])"+       //at least 1 lower case letter
                //"(?=.*[A_Z])"+       //at least 1 upper case letter
                "(?=.*[a-zA-Z])"+      //any letter
                "(?=.*[@#$%^&+=])"+    //at least 1 special character
                "(?=\\s+$)"+            //no white space
                ".{4,}"+                //at least 4 character 
                "$";

        if (val.isEmpty()){
            regPassword.setError("Filed cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too week");
            return false;
        }
            
         else {
            regPassword.setError(null);
            return true;
        }
    }



    //save data in FireBase on button click
    public void registerUser(View view){


        if(!validateName() | ! validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername()){
            return;
        }

        //get all the value in string
        String name =regName.getEditText().getText().toString();
        String username =regUsername.getEditText().getText().toString();
        String email =regEmail.getEditText().getText().toString();
        String phoneNo =regPhoneNo.getEditText().getText().toString();
        String password =regPassword.getEditText().getText().toString();

        UserHelperClass helperClass=new UserHelperClass(name,username,email,phoneNo,password);
        reference.child(username).setValue(helperClass);
    }
}