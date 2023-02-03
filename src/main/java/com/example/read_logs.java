package com.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import com.example.logs.CyverseLog;
import com.example.logs.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class read_logs {
  private static final Object lock = new Object();
  public static void main (String[] args) throws IOException{
    // System.out.println(getLogJson("bash_logs.json"));
    List<CyverseLog> cyverseLogs = readAllCyverseLogs("plugin_logs.json");
    for (int i = 0; i < cyverseLogs.size(); i ++) {
      System.out.println(cyverseLogs.get(i).toString());
    }
    System.out.println("================================================");
    System.out.println("Total Cyverse Logs: " + cyverseLogs.size());
  }

  public static List<CyverseLog> readAllCyverseLogs(String cyverseFileName) throws IOException{
    List<CyverseLog> result = new ArrayList<>();
    JsonObject rawLog = getLogJson(cyverseFileName);
    JsonArray logArray = rawLog.get("logs").getAsJsonArray();
    String[] randomNames = getRandomNames(logArray.size());
    for (int i = 0; i < logArray.size(); i ++) {
      JsonObject log = logArray.get(i).getAsJsonObject();
      String studentId = log.get("log_id").getAsString();
      String fakeName = randomNames[i];
      synchronized (lock) {
        if (getNamePair(studentId) == null) {
          setNamePair(studentId, fakeName);
        }else {
          fakeName = getNamePair(studentId);
        }
      }
      JsonArray allLogs = log.get("log").getAsJsonObject().get("logArray").getAsJsonArray();
      for (int j = 0; j < allLogs.size(); j ++) {
        JsonObject individualLog = allLogs.get(j).getAsJsonObject();
        String event = validateGet(individualLog, "event");
        String eventType = validateGet(individualLog, "eventType");
        String timestamp = validateGet(individualLog, "timestamp");
        String url = validateGet(individualLog, "url");
        Student student = new Student(studentId, fakeName);
        CyverseLog cyverseLog = new CyverseLog(student, timestamp, eventType, event, url);
        result.add(cyverseLog);
      }
    }
    return result;
  }

  public static String validateGet(JsonObject obj, String key) {
    if (obj.get(key) != null) {
      return obj.get(key).getAsString();
    }else {
      return "N/A";
    }
  }

  public static JsonObject getLogJson(String fileName) throws IOException{
    File file = new File(read_logs.class.getClassLoader().getResource(fileName).getFile());
    try (FileReader reader = new FileReader(file)) {
      JsonObject obj = JsonParser.parseReader(reader).getAsJsonObject();
      return obj;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  // return certain numbers of random name in array
  public static String[] getRandomNames(int num) throws ClientProtocolException, IOException {
    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpGet request = new HttpGet("https://names.drycodes.com/" + num + "?nameOptions=boy_names");
    HttpResponse response = httpClient.execute(request);
    String responseBody = EntityUtils.toString(response.getEntity()).replace("[", "").replace("]", "");
    responseBody = responseBody.substring(1, responseBody.length()-1);
    String[] names = responseBody.split("\",\"");
    return names;
  }

  public static String getNamePair(String student_id) throws IOException{
    ObjectMapper objectMapper = new ObjectMapper();
    File file = new File(read_logs.class.getClassLoader().getResource("students.json").getFile());
    Map<String, Object> responseMap = objectMapper.readValue(file, Map.class);
    if (responseMap.get(student_id) != null) {
      return responseMap.get(student_id).toString();
    }else {
      return null;
    }
  }

  public static void setNamePair(String student_id, String randomName) throws IOException{
    JSONObject jsonObject = new JSONObject();
    jsonObject.put(student_id, randomName);
    ClassLoader classLoader = read_logs.class.getClassLoader();
    File file_temp = new File(classLoader.getResource("students.json").getFile());
    try (FileWriter fileWriter = new FileWriter(file_temp)) {
      fileWriter.write(jsonObject.toJSONString());
      //fileWriter.flush();
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
