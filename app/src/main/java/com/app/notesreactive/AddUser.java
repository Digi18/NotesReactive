package com.app.notesreactive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Models.User;
import Room.UserRepository;
import ViewModels.AddUserViewModel;

public class AddUser extends AppCompatActivity {

    EditText name,age;
    Button submit;

    AddUserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

            name = findViewById(R.id.name);
            age = findViewById(R.id.age);
            submit = findViewById(R.id.submit);

            userViewModel = ViewModelProviders.of(this).get(AddUserViewModel.class);

            submit.setOnClickListener((View view) -> {

                if(name.getText().toString().equals("") || age.getText().toString().equals("")){

                        Toast.makeText(getApplicationContext(),"Field is empty",Toast.LENGTH_SHORT).show();
                    }else{

                        String username = name.getText().toString();
                        String userage = age.getText().toString();

                        User user = new User(username,userage);

                        userViewModel.insert(user);

                        name.setText("");
                        age.setText("");


                    }

            });

    }

}
