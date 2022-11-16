package com.example.j6demo4.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Rest {
    private static ObjectMapper mapper = new ObjectMapper();

    public static JsonNode get(String path) {
        return request("GET",path, null);
    }

    public static JsonNode post(String path, Object data) {
        return request("POST",path, data);
    }

    public static JsonNode put(String path, Object data) {
        return request("PUT",path, data);
    }

    public static void delete(String path) {
         request("DELETE",path, null);
    }
    private static JsonNode request(String method, String path, Object data) {
        try {
        // 1. REQUEST
        String uri = "https://poly-java-6-dd2ca-default-rtdb.firebaseio.com" + path + ".json";
        URL url = new URL(uri); // tạo đối tượng URL

            // mở kết nối tới REST API
            HttpURLConnection conn =(HttpURLConnection) url.openConnection();
            // đặt 1 số thuộc tính
            conn.setRequestProperty("Accept", "application/json");
            // gọi operation nào?
            conn.setRequestMethod(method);

            //1.1 DATA (POST & PUT only)
            if(method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
                conn.setDoOutput(true);// khả năng ghi dữ liệu lên
                // chuyển đổi data sang json và gửi lên server thông qua writeValue()
                mapper.writeValue(conn.getOutputStream(), data);
            }

            // 2 RESPONSE
            int responseCode = conn.getResponseCode();
            if(responseCode == 200) {
                // nếu là 200 thì đọc và chuyển json về jsonNode
                JsonNode jsonNode = mapper.readTree(conn.getInputStream());
                return jsonNode;
            }
            conn.disconnect();
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
