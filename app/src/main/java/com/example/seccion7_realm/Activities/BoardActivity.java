package com.example.seccion7_realm.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.seccion7_realm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BoardActivity extends AppCompatActivity {
  private FloatingActionButton fab;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_board);

    fab = (FloatingActionButton) findViewById(R.id.fabAddBoard);
  }
}