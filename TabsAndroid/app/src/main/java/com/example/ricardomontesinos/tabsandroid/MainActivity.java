package com.example.ricardomontesinos.tabsandroid;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //para utilizar los assets
        Resources res = getResources();

        TabHost tabs = (TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        //creamos un objeto de tipo Tabspec para cada una de las pestañas que queramos añadir mediante el metodo newTabSpect()
        //pasando como parametro una etiqueta identificativa "mitab1"
        //tab 1
        TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("",res.getDrawable(android.R.drawable.ic_btn_speak_now));

        tabs.addTab(spec);

        //tab 2, reescribiendo variable spec
        spec = tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("TAB2", res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);


        //Eventos del TabHost
        //Nos informa cuando cambiamos de pestaña

        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // recibimos la tag de la pestaña identificativa, no su ID
                Log.i("TABS","Pulsada la pestaña: "+tabId);
            }
        });


    }
}
