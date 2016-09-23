package com.example.ricardomontesinos.localizacion;

/* Agregamos la dependencia a Google Play services:

dependencies {
    ...
    compile 'com.google.android.gms:play-services:9.4.0'
}

*O bien, solo agregamos la librería de localización, que incluye reconocimiento de actividad y google places.

dependencies {
    ...
    compile 'com.google.android.gms:play-services-location:9.4.0'
}

Utilizaremos la clase "Location", creando un objeto Location podemos usar los metodos:
getLatitude() y getLongitude()


* Creando un objeto de tipo GoogleApiClient, tendremos un punto de acceso comun a los servicios
* de Google Play

GoogleApiClient apiClient = new GoogleApiClient.Builder(this)
    .enableAutoManage(this, this)
    .addConnectionCallbacks(this)
    .addApi(Location.API)
    .build();


 enableAutoManage(this, this)
 Solicitamos que la gestion de la conexion a los servicios se realice automaticamente.
 El primer parámetro es para indicar en que actividad vivirá
 El segundo parámetro es la referencia al listener, de tipo OnConnectionFailedListener que se llamara en caso de errors

ConnectionCallbacks , para implementar lo smetodos onConnected(), que se ejecutara cuando se realice la conexion con los servicios
y onConnectionSuspended(), que se lanzará cuando la conexión se pierda temporalmente, cuando la conexion se recupera volverá a lanzarse el evento onConnected().

Mediante addApi() indicaremos la API de los servicios a lo que vamos a acceder, en este caso Location.API
En caso de querer usar servicios podriamos realizar varias llamdas al metodo y añadir servicios necesarios.

Por ultimo, construimos el objeto final llamando al método build(), esto iniciará la conexión.

 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks{

    TextView lblLatitud;
    TextView lblLongitud;
    ToggleButton btnActualizar;
    private GoogleApiClient apiClient;
    private static final int PETICION_PERMISO_LOCALIZACION = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblLatitud = (TextView) findViewById(R.id.lblLatitud);
        lblLongitud = (TextView) findViewById(R.id.lblLongitud);
        btnActualizar = (ToggleButton) findViewById(R.id.btnActualizar);

        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this) //nos sirve para conocer cuando se realiza la conexion y cuando se pierde
                .addApi(LocationServices.API) //En caso de querer utilizar diferentes servicios podríamos realizar varias llamadas al método addApi() añadiendo los servicios necesarios.
                .build(); // Por último, construimos el objeto final llamando a este método
    }

    private void updateUI(Location loc) {
        if (loc != null) {
            lblLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
            lblLongitud.setText("Longitud: " + String.valueOf(loc.getLongitude()));
        } else {
            lblLatitud.setText("Latitud: (desconocida)");
            lblLongitud.setText("Longitud: (desconocida)");
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        //Se ha producido un error que no se puede resolver automáticamente
        //y la conexión con los Google Play Services no se ha establecido.
        Log.e("ERROR", "Error grave al conectar con Google Play Services");
    }

    //METODOS DE ConnectionCallbacks
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // se ejecutará cuándo se realice la conexión con los servicios

        //Para ello obtendremos la última posición geográfica conocida, lo que conseguimos llamando al método getLastLocation() del proveedor de datos de localización
        //LocationServices.FusedLocationApi, pasándole como parámetro la referencia al cliente API que hemos creado anteriormente.
        //Por último llamamos a nuestro método auxiliar updateUI() con el valor recibido para mostrar los datos en la actividad principal.
        // Muestro también a continuación el evento onConnectionSuspended(), que para nuestro ejemplo se limitará a mostrar un mensaje en el log.
        //Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient); //este metodo no devuelve la posicion actual, ni solicita una nueva, solo devuelve la ultima pos conocida
        //updateUI(lastLocation);


        //ACCESS_FINE_LOCATION. Permiso para acceder a datos de localización con una precisión alta.
        //ACCESS_COARSE_LOCATION. Permiso para acceder a datos de localización con una precisión baja.
        // ensure that you have the required permission before calling an APi, use the method checkSelfPermission()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO_LOCALIZACION);
        } else {

            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            updateUI(lastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //se lanzará cuando la conexión se pierda temporalmente (cuando la conexión se recupera volverá a lanzarse el evento onConnected())
    }

    //Para conocer la respuesta del usuario, si concedio permiso de usar su ubicacion
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PETICION_PERMISO_LOCALIZACION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Permiso concedido

                @SuppressWarnings("MissingPermission")
                Location lastLocation =
                        LocationServices.FusedLocationApi.getLastLocation(apiClient);

                updateUI(lastLocation);

            } else {
                //Permiso denegado:
                //Deberíamos deshabilitar toda la funcionalidad relativa a la localización.

                Log.e("PERMISO", "Permiso denegado");
            }
        }
    }
}
