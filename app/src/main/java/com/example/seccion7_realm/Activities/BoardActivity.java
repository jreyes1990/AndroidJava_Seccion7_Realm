package com.example.seccion7_realm.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.seccion7_realm.Adapters.BoardAdapter;
import com.example.seccion7_realm.Models.Board;
import com.example.seccion7_realm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class BoardActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Board>>, AdapterView.OnItemClickListener {
  private Realm realm;
  private FloatingActionButton fab;
  private ListView listView;
  private BoardAdapter adapter;
  private RealmResults<Board> boards;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_board);

    // DB Realm
    realm = Realm.getDefaultInstance();
    boards = realm.where(Board.class).findAll();
    boards.addChangeListener(this);

    adapter = new BoardAdapter(this, boards, R.layout.list_view_board_item);
    listView = (ListView) findViewById(R.id.listViewBoard);
    listView.setAdapter(adapter);

    fab = (FloatingActionButton) findViewById(R.id.fabAddBoard);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showAlertForCreatingBoard("Add New Board", "Type a name for yout new board");
      }
    });
  }

  //** CRUD Actions **//


  private void createNewBoard(String boardName) {
    realm.beginTransaction();
    Board board = new Board(boardName);
    realm.copyToRealm(board);
    realm.commitTransaction();

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

  @Override
  public void onChange(RealmResults<Board> boards) {
    adapter.notifyDataSetChanged();
  }

  @Override
  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
    Intent intent = new Intent(BoardActivity.this, NoteActivity.class);
    intent.putExtra("id", boards.get(position).getId());
    startActivity(intent);
  }
}