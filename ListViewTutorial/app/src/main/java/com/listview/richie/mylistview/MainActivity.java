package com.listview.richie.mylistview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //final String[] datos = new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};

    private Titular[] datos =
            new Titular[]{
                    new Titular("Título 1", "Subtítulo largo 1"),
                    new Titular("Título 2", "Subtítulo largo 2"),
                    new Titular("Título 3", "Subtítulo largo 3"),
                    new Titular("Título 4", "Subtítulo largo 4"),
                    new Titular("Título 15", "Subtítulo largo 15")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        AdaptadorTitulares adaptador = new AdaptadorTitulares(this, datos);
        ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);

        View header = getLayoutInflater().inflate(R.layout.list_header, null);
        lstOpciones.addHeaderView(header);

        lstOpciones.setAdapter(adaptador); //Esto pondra la cellda custom

        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                //Alternativa 1:
                String opcionSeleccionada =
                        ((Titular)a.getItemAtPosition(position)).getTitulo();

                //Alternativa 2:
                //String opcionSeleccionada =
                //      ((TextView)v.findViewById(R.id.LblTitulo))
                //          .getText().toString();

                Toast.makeText(getBaseContext(),""+opcionSeleccionada,Toast.LENGTH_SHORT).show();
            }
        });
    }

    class AdaptadorTitulares extends ArrayAdapter<Titular> {

        public AdaptadorTitulares(Context context, Titular[] datos) {
            super(context, R.layout.listitem_titular, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView;
            ViewHolder holder;

            //save memory, reuse layout
            if(item == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                item = inflater.inflate(R.layout.listitem_titular, null); //COMO LA CELLDA CUSTOM

                holder = new ViewHolder();
                holder.titulo = (TextView)item.findViewById(R.id.LblTitulo);
                holder.subtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);

                item.setTag(holder); //el obj holder, pondra el id a cada unod e sus atributos
            }else {
                holder = (ViewHolder)item.getTag();
            }

            holder.titulo.setText(datos[position].getTitulo());
            holder.subtitulo.setText(datos[position].getSubtitulo());
            // LayoutInflater inflater = LayoutInflater.from(getContext());
            // View item = inflater.inflate(R.layout.listitem_titular, null); //COMO LA CELLDA CUSTOM

            //ELEMENTOS DE LA CELDA
            //TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
            //lblTitulo.setText(datos[position].getTitulo());

            //ELEMENTOS DE LA CELDA
            //TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
            //lblSubtitulo.setText(datos[position].getSubtitulo());

            return(item);
        }
    }

    //usamos esta clase para evitar usar findviewbyid en cada operacion
    // creando una referencia al id una vez
    static class ViewHolder {
        TextView titulo;
        TextView subtitulo;
    }
}



