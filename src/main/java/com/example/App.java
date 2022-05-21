package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.yaml.snakeyaml.Yaml;




public class App {


    public static void main( String[] args )
    {
        File dir = new File("/Users/Ile/Desktop/pruebaa");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
        for (File child : directoryListing) {
           try {
            new lector().checkBalance(child);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }}

}



}





