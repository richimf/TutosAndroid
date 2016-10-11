package com.listview.richie.mylistview;

/**
 * Created by richie on 9/18/16.
 */

public class Titular {

    private String titulo;
    private String subtitulo;

    public Titular(String tit, String sub){
        titulo = tit;
        subtitulo = sub;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getSubtitulo(){
        return subtitulo;
    }
}