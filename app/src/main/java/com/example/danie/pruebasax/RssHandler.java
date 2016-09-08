package com.example.danie.pruebasax;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
//import java.util.jar.Attributes;


import org.xml.sax.Attributes;


/**
 * Created by pguti on 26-08-2016.
 */
public class RssHandler extends DefaultHandler {
    private List<Noticia> noticias;
    private Noticia noticiaActual;
    private StringBuilder sbTexto;

    //permitira obtener la lista de Noticia tras la lectura xml
    public List<Noticia> getNoticias(){
        return noticias;
    }

    @Override
    //toma un fragmento de texto y lo guarda en una cadena StringBuilder
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        super.characters(ch, start, length);

        if (this.noticiaActual != null)
            sbTexto.append(ch, start, length);//Anexa una cadena de una submatriz de caracteres
    }

    @Override
    //asigna el xml a las variables de nuestra clase Noticia y los agrega a una lista
    public void endElement(String uri, String localName, String name)
            throws SAXException {

        super.endElement(uri, localName, name);

        if (this.noticiaActual != null) {

            if (localName.equals("title")) {               //asigna lo que contiene la etiqueta item
                noticiaActual.setTitulo(sbTexto.toString());//en nuestros atributos de la clase Noticia
            } else if (localName.equals("pubDate")) {       //compureba si todo pertenece a la etiqueta item
                noticiaActual.setFecha(sbTexto.toString());//si cumple con la condicion lo agrega a la lista de Noticia
            } else if (localName.equals("item")) {
                noticias.add(noticiaActual);
            }

            sbTexto.setLength(0);//borra todo lo que la variable StringBuilder tenia anteriormente
        }
    }

    @Override
    //indica si se a iniciado la lectura del xml
    //instacia una arrayList de Noticia y un StringBuilder
    public void startDocument() throws SAXException {

        super.startDocument();

        noticias = new ArrayList<Noticia>();
        sbTexto = new StringBuilder();
    }

    @Override
    //comienza la etiqueta xml
    //comprueba si recibe la etiqueta item
    // instancia una nueva variable Noticia
    // donde almacenara posteriormente los elementos
    public void startElement(String uri, String localName,
                             String name, Attributes attributes) throws SAXException {

        super.startElement(uri, localName, name, attributes);

        if (localName.equals("item")) {
            noticiaActual = new Noticia();
        }
    }
}
