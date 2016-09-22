package com.example.ricardomontesinos.retrofittuto;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ricardomontesinos.retrofittuto.models.Catalog;
import com.example.ricardomontesinos.retrofittuto.models.Course;
import com.example.ricardomontesinos.retrofittuto.models.Instructor;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.- Una variable de tipo retrofit, que usara la URL del servicio a consumir
        // ese servicio se convertira con GSON, gracias el converterFactory usado (hay muchos, aqui se uso GSON)
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Service.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //2.- El objeto retrofit contiene un GSON, y ahora debemoc convertirlo en un tipo service
        // Es decir "service" pasa a ser "gson" luego a "retrofit" y "retrofit" pasa  a ser "service"
        Service service = retrofit.create(Service.class);

        //3.-
        Call<Catalog> requestCatalog = service.listCatalog();

        //4.- Hacemos una llamada "Asincronoa" con "enqueue()"
        // y una "Sincrona" con con "execute()"
        requestCatalog.enqueue(new Callback<Catalog>() {
            @Override
            public void onResponse(Call<Catalog> call, Response<Catalog> response) {
                if (!response.isSuccessful()) {
                    Log.i("TAG", "ERROR = "+response.code());
                } else {

                    Catalog catalogo = response.body();

                    for (Course c : catalogo.courses){
                        Log.i("TAG",String.format("%s: %s",c.title, c.subtitle));

                        for (Instructor i : c.instructors){
                            Log.i("TAG",i.name);
                        }

                        Log.i("TAG","------");
                    }
                }
            }

            @Override
            public void onFailure(Call<Catalog> call, Throwable t) {
                Log.i("TAG","FALLO");
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.ricardomontesinos.retrofittuto/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.ricardomontesinos.retrofittuto/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
