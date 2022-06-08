package com.example;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.DumperOptions;

public class App {

    public static void main( String[] args ) throws SQLException {
        File dir = new File("/Users/Ile/Desktop/pruebaa");
        File[] directoryListing = dir.listFiles();
        Connection connection = DriverManager.getConnection(args[2], args[0], args[1]);
        if (directoryListing != null) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO balance_table(uuid, name, balance) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE balance=balance+?;");
            for (File child : directoryListing) {
                try {
                    if (checkBalance(child, ps)) ps.addBatch();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            ps.executeBatch();
        }
        connection.close();
    }

    private static boolean checkBalance(File child, PreparedStatement ps) throws FileNotFoundException, SQLException {
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        InputStream targetStream = new FileInputStream(child);
        Map<String, Object> data = yaml.load(targetStream);
        String moneyS = data.get("money").toString();
        float money = Float.parseFloat(moneyS);

        ps.setString(1, child.getName().substring(0, child.getName().length() - 4));
        ps.setString(2, (String) data.get("last-account-name"));

        float ratio = 0;

        if (money >= 30000000) {
            ratio = 70;
        } else if (money >= 10000000) {
            ratio = 60;
        } else if (money >= 5000000) {
            ratio = 45;
        } else if (money >= 500000) {
            ratio = 25;
        } else if (money >= 200000) {
            ratio = 10;
        } else return false;

        float leftover = money * ratio;
        ps.setInt(3, (int) (leftover / 500000));
        ps.setInt(4, (int) (leftover / 500000));
        money = money - (leftover / 100);
        data.put("money", String.format("%.0f", money));
        try {
            yaml.dump(data, new FileWriter(child));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}





