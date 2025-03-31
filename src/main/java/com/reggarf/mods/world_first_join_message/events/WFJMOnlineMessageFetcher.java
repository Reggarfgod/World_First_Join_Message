package com.reggarf.mods.world_first_join_message.events;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



//@Mod.EventBusSubscriber(modid = WFJMessage.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WFJMOnlineMessageFetcher {

    public static String fetchOnlineMessage(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // 5 seconds timeout
            connection.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            return content.toString().trim(); // Return message content
        } catch (Exception e) {
            System.err.println("Failed to fetch online message: " + e.getMessage());
            return ""; // Return empty string on failure
        }
    }
}