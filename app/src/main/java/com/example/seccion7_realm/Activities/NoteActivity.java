package com.example.seccion7_realm.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.seccion7_realm.Adapters.NoteAdapter;
import com.example.seccion7_realm.Models.Board;
import com.example.seccion7_realm.Models.Note;
import com.example.seccion7_realm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmList;

public class NoteActivity extends AppCompatActivity {
  private ListView listView;
  private FloatingActionButton fab;

  private NoteAdapter adapter;
  private RealmList<Note> notes;
  private Realm realm;

  private int boardId;
  private Board board;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_note);

    // DB Realm
    realm = Realm.getDefaultInstance();

    if (getIntent().getExtras() != null){
      boardId = getIntent().getExtras().getInt("id");
    }

    board = realm.where(Board.class).equalTo("id", boardId).findFirst();
    notes = board.getNotes();
    this.setTitle(board.getTitle());

    adapter = new NoteAdapter(this, notes, R.layout.list_view_note_item);
    listView = (ListView) findViewById(R.id.listViewNote);
    listView.setAdapter(adapter);

    fab = (FloatingActionButton) findViewById(R.id.fabAddNote);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showAlertForCreatingNote("Add New Note", "Type a note for "+board.getTitle()+".");
      }
    });
  }

  //** CRUD Actions **//
  private void createNewNote(String note) {


    // Otra opcion del uso del begin y commit
    /*
    realm.executeTransaction(new Realm.Transaction() {
      @Override
      public void execute(final Realm realm) {
        Board board = new Board(boardName);
        realm.copyToRealm(board);
      }
    });
     */
  }

  //** Dialogs **//
  private void showAlertForCreatingNote(String title, String message){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);

    if (title != null) builder.setTitle(title);
    if (message != null) builder.setMessage(message);

    View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_note, null);
    builder.setView(viewInflated);

    final EditText input = (EditText) viewInflated.findViewById(R.id.editTextNewNote);

    builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        String note = input.getText().toString().trim();

        if (note.length() > 0){
          createNewNote(note);
        }else{
          Toast.makeText(getApplicationContext(), "The note can't be empty", Toast.LENGTH_LONG).show();
        }
      }
    });

    AlertDialog dialog = builder.create();
    dialog.show();
  }
}