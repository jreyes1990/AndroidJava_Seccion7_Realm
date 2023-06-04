package com.example.seccion7_realm.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.seccion7_realm.Models.Note;
import com.example.seccion7_realm.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class NoteAdapter extends BaseAdapter {
  private Context context;
  private List<Note> list;
  private int layout;

  public NoteAdapter(Context context, List<Note> list, int layout) {
    this.context = context;
    this.list = list;
    this.layout = layout;
  }

  @Override
  public int getCount() {
    return list.size();
  }

  @Override
  public Note getItem(int position) {
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
      vh.description = (TextView) convertView.findViewById(R.id.textViewNoteDescription);
      vh.createdAt = (TextView) convertView.findViewById(R.id.textViewNoteCreatedAt);
      convertView.setTag(vh);
    }else {
      vh = (ViewHolder) convertView.getTag();
    }

    Note note = list.get(position);

    vh.description.setText(note.getDescripcion());

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String createdAt = dateFormat.format(note.getCreatedAt());
    vh.createdAt.setText(createdAt);

    return convertView;
  }

  public class ViewHolder{
    TextView description;
    TextView createdAt;
  }
}
