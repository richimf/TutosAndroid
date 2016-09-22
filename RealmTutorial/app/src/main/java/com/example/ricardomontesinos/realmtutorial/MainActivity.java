package com.example.ricardomontesinos.realmtutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import model.Country;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.- Instanciamos Realm
        Realm myRealm =
                Realm.getInstance(
                        new RealmConfiguration.Builder(this)
                                .name("myOtherRealm.realm")
                                .build()
                );

        myRealm.beginTransaction();

        // 2.- Creamos un objeto de tipo Country
        Country country1 = myRealm.createObject(Country.class);
        country1.setName("Norway");
        country1.setPopulation(5165800);
        country1.setCode("NO"); //primary key

        myRealm.commitTransaction();

        // Create the object
        Country country2 = new Country();
        country2.setName("Russia");
        country2.setPopulation(146430430);
        country2.setCode("RU"); //primary key

        myRealm.beginTransaction(); //paso inicial
        Country copyOfCountry2 = myRealm.copyToRealm(country2);
        myRealm.commitTransaction(); //paso final

        //3.- Obtener los datos guardados
        RealmResults<Country> results1 =
                myRealm.where(Country.class).findAll();

        for(Country c:results1) {
            Log.d("results1", c.getName());
        }

        //4.- Obtener objetos mayores a un valor
        RealmResults<Country> results2 =
                myRealm.where(Country.class)
                        .greaterThan("population", 100000000)
                        .findAll();

        for(Country c:results2) {
            Log.d("results2", c.getName());
        }

    }
}
