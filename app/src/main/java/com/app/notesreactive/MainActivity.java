package com.app.notesreactive;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import Adapters.UserAdapter;
import Models.User;
import ViewModels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycle;
    UserAdapter adapter;
    ProgressBar prog;
    MainActivityViewModel viewModel;
    List<User> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        prog = findViewById(R.id.prog);

        recycle = findViewById(R.id.recycle);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));

        mUsers = new ArrayList<>();

        adapter = new UserAdapter(mUsers,this);
        recycle.setAdapter(adapter);

        viewModel.getAllUsers().observe(this, users -> {

                prog.setVisibility(View.GONE);
                adapter.setmUsers(users);

        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((View view) -> {

                Intent i = new Intent(MainActivity.this,AddUser.class);
                startActivity(i);

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
