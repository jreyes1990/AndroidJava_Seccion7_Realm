package com.example.seccion7_realm.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.seccion7_realm.Models.Board;
import com.example.seccion7_realm.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BoardAdapter extends BaseAdapter {
  private Context context;
  private List<Board> list;
  private int layout;

  public BoardAdapter(Context context, List<Board> list, int layout) {
    this.context = context;
    this.list = list;
    this.layout = layout;
  }

  @Override
  public int getCount() {
    return list.size();
  }

  @Override
  public Board getItem(int position) {
    return list.get(position);
  }

  @Override
  public long getItemId(int id) {
    return id;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup viewGroup) {
    ViewHolder vh;

    if (convertView == null){
      convertView = LayoutInflater.from(context).inflate(layout, null);
      vh = new ViewHolder();
      vh.title = (TextView) convertView.findViewById(R.id.textViewBoardTitle);
      vh.notes = (TextView) convertView.findViewById(R.id.textViewBoardNotes);
      vh.createdAt = (TextView) convertView.findViewById(R.id.textViewBoardDate);
      convertView.setTag(vh);
    }else {
      vh = (ViewHolder) convertView.getTag();
    }

    Board board = list.get(position);

    vh.title.setText(board.getTitle());

    int numberOfNotes = board.getNotes().size();
    String textForNotes = (numberOfNotes ==1) ? numberOfNotes+" Note" : numberOfNotes+" Notes";
    vh.notes.setText(textForNotes);

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String createdAt = dateFormat.format(board.getCreatedAt());
    vh.createdAt.setText(createdAt);

    return convertView;
  }

  public class ViewHolder{
    TextView title;
    TextView notes;
    TextView createdAt;
  }
}
