package com.example.richimf.actionbartuto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

//action bar o app bar tutorial
/*
Una forma más flexible y personalizable de añadir una action bar
a una aplicación es utilizar el nuevo componente Toolbar proporcionado por la librería appcompat.
De esta forma podemos incluir de forma explícita la action bar en nuestros layouts XML como
si fuera cualquier otro control, y no sólo en la parte superior de la pantalla a modo de app bar,
sino también en cualquier otro lugar de la aplicación donde queramos utilizar esta funcionalidad de barra de acciones.
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
           En nuestro código debemos indicar que esta Toolbar actuará como action bar de la actividad.
           Para ello, en el método onCreate() de la actividad haremos una llamada a setSupportActionBar() con la referencia a la toolbar
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null); //getSupportActionBar().setDisplayShowTitleEnabled(false);

    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_nuevo:
                Log.i("ActionBar", "Nuevo!");
                return true;
            case R.id.action_buscar:
                Log.i("ActionBar", "Buscar!");
                ;
                return true;
            case R.id.action_settings:
                Log.i("ActionBar", "Settings!");
                ;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
