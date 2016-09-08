package com.example.danie.pruebasax;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import java.net.URL;
import javax.xml.parsers.SAXParser;
import java.net.MalformedURLException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by danie on 26-08-2016.
 */
public class RssParserSax {

    private URL rssUrl;

    //recibe como parametro la url del documento xml
    public RssParserSax(String url)
    {
        try
        {
            this.rssUrl = new URL(url);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);//excepcion en caso de no recibir la url
        }
    }
    //crea un nuevo documento parse
    public List<Noticia> parse()
    {

        SAXParserFactory factory = SAXParserFactory.newInstance(); //instanciamos la clase SAXParseFactory(clase abstracta)

        try
        {
            SAXParser parser = factory.newSAXParser();//instacia nueva clase de SAXParser
            RssHandler handler = new RssHandler();//instacia una clase RssHandler
            parser.parse(this.getInputStream(), handler);//recibe lo obtetenido por el imputStream y lo asigna a la variablr handler
            return handler.getNoticias();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    //se conecta con la url especifica
    private InputStream getInputStream()
    {
        try
        {
            return rssUrl.openConnection().getInputStream();// abre coneccion y obtinene el stream
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
