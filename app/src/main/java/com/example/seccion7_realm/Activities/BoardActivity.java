package com.example.seccion7_realm.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seccion7_realm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BoardActivity extends AppCompatActivity {
  private FloatingActionButton fab;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_board);

    fab = (FloatingActionButton) findViewById(R.id.fabAddBoard);

    showAlertForCreatingBoard("title", "message");
  }

  private void showAlertForCreatingBoard(String title, String message){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);

    if (title != null) builder.setTitle(title);
    if (message != null) builder.setMessage(message);

    View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_board, null);
    builder.setView(viewInflated);

    final EditText input = (EditText) viewInflated.findViewById(R.id.editTextNewBoard);

    builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        String boardName = input.getText().toString().trim();
        
        if (boardName.length() > 0){
          createNewBoard(boardName);
        }else{
          Toast.makeText(getApplicationContext(), "The name is required to create a new board", Toast.LENGTH_SHORT).show();
        }
      }
    });

    AlertDialog dialog = builder.create();
    dialog.show();
  }

  private void createNewBoard(String boardName) {
  }
}