package com.example.seccion7_realm.App;

import android.app.Application;

import com.example.seccion7_realm.Models.Board;
import com.example.seccion7_realm.Models.Note;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApplication extends Application {
  public static AtomicInteger BoardID = new AtomicInteger();
  public static AtomicInteger NoteID = new AtomicInteger();

  @Override
  public void onCreate() {
    super.onCreate();

    setUpRealmConfig();

    Realm realm = Realm.getDefaultInstance();
    BoardID = getIdByTable(realm, Board.class);
    NoteID = getIdByTable(realm, Note.class);
    realm.close();
  }

  // Configuracion de base de datos en Realm
  private void setUpRealmConfig(){
    Realm.init(getApplicationContext());
    RealmConfiguration config = new RealmConfiguration.Builder(/*getApplicationContext()*/).deleteRealmIfMigrationNeeded().build();
    Realm.setDefaultConfiguration(config);
  }

  // Metodo para definir IDs
  private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
    RealmResults<T> results = realm.where(anyClass).findAll();
    return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
  }
}
