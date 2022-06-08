package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.yaml.snakeyaml.Yaml;

public class App {

    public static void main( String[] args ){
        File dir = new File("/Users/Ile/Desktop/pruebaa");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                try {
                    checkBalance(child);
                } catch (FileNotFoundException e) {
                e.printStackTrace();
                }
            }
        }
    }

    private static void checkBalance(File child) throws FileNotFoundException {
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        InputStream targetStream = new FileInputStream(child);
        Map<String, Object> data = yaml.load(targetStream);
        String moneyS = data.get("money").toString();
        float money = Float.parseFloat(moneyS);

        if (money >= 30000000) {
            money = money - ((money * 70) / 100);
            data.put("money", String.format("%.0f", money));
            try {
                yaml.dump(data, new FileWriter(child));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (money >= 10000000) {
            money = money - ((money * 60) / 100);
            data.put("money", String.format("%.0f", money));
            try {
                yaml.dump(data, new FileWriter(child));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (money >= 5000000) {
            money = money - ((money * 45) / 100);
            data.put("money", String.format("%.0f", money));
            try {
                yaml.dump(data, new FileWriter(child));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (money >= 500000) {
            money = money - ((money * 25) / 100);
            data.put("money", String.format("%.0f", money));
            try {
                yaml.dump(data, new FileWriter(child));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (money >= 200000) {
            money = money - ((money * 10) / 100);
            data.put("money", String.format("%.0f", money));
            try {
                yaml.dump(data, new FileWriter(child));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else return;
        
    }
}





