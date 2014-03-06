package com.github.sebastiansam55.luaengine;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by sam on 5/28/13.
 */
public class JSONFileParser{
    Object obj;
    public JSONFileParser(File JSONfile){
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(JSONfile);
            obj = parser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public String getThis(String index){
        JSONObject jsonObject = (JSONObject) obj;
        return (String) jsonObject.get(index);
    }

}

