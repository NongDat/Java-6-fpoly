package com.example.j6demo4.controller;

import com.example.j6demo4.bean.StudentMap;
import com.example.j6demo4.dao.StudentDao;

public class Application_JAVA_NET_URL {



    StudentDao dao = new StudentDao();
    StudentMap items;
    String key = null;
    public static void main(String[] args) {
//        try{
//        ObjectMapper mapper = new ObjectMapper();
//        String method = "GET";
//        URL url = new URL("https://poly-java-6-dd2ca-default-rtdb.firebaseio.com/students.json");
//        HttpURLConnection conn =(HttpURLConnection) url.openConnection();
//        conn.setRequestProperty("Accept", "application/json");
//        conn.setRequestMethod(method);
//
//        //
//            //1.1 DATA (POST & PUT only)
//            Object data = null;
//            if(method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
//                conn.setDoOutput(true);// khả năng ghi dữ liệu lên
//                // chuyển đổi data sang json và gửi lên server thông qua writeValue()
//                mapper.writeValue(conn.getOutputStream(), data);
//            }
//        int responseCode = conn.getResponseCode();
//        if(responseCode == 200){
//            JsonNode resp = mapper.readTree(conn.getInputStream());
//            StudentMap studentMap = mapper.convertValue(resp, StudentMap.class);
//            studentMap.forEach((key, student)->{
//                System.out.println(student.getFullname());
//            });
//            System.out.println("OK");
//        }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Lỗi load");
//        }
        Application_JAVA_NET_URL a = new Application_JAVA_NET_URL();
        a.load();
    }

       void fillAll() {
         items.keySet().toArray(); //
         items.forEach((key, student) -> {
            System.out.println(student.getArray());
             System.out.println(student.getFullname());
        });
    }

    private void load() {

        try {
            items = dao.findAll();
            fillAll();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi load data");
        }
    }
}
