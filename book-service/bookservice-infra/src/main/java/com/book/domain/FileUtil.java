package com.book.domain;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {


    public static JSONArray readFromFile(String filename) {

        JSONArray bookList = new JSONArray();
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filename))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            bookList = (JSONArray) obj;
            System.out.println(bookList);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
//            e.printStackTrace();
        }

        return bookList;
    }


    public static void writeInFile(JSONArray bookList, String filename){

        //Write JSON file
        try  {
            FileWriter file = new FileWriter(filename);
            //We can write any JSONArray or JSONObject instance to the file
            file.write(bookList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
