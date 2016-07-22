package kr.sumeng.sumproject.DB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.*;

import net.daum.mf.map.api.*;
import net.daum.mf.map.api.MapView;

import kr.sumeng.sumproject.MapUtil.AddMaker;

/**
 * Created by sum on 2016-07-19.
 */
public class DataReader {
    String Url = "http://192.168.1.44:8080/item.php";
    //back task;
    phpDown task;
    ArrayList<ListItem> listItem= new ArrayList<>();


    public DataReader(String Url,MapView map){
        this.Url = Url;
        task = new phpDown(map);
        task.execute("http://192.168.1.44:8080/item.php");
    }

    private class phpDown extends AsyncTask<String, Integer,String>{

        private MapView map;
        public phpDown(MapView map){
          this.map=map;
        }


        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{
                // 연결 url 설정
                URL url = new URL(urls[0]);
                // 커넥션 객체 생성
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                // 연결되었으면.
                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    // 연결되었음 코드가 리턴되면.
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        for(;;){
                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                            String line = br.readLine();
                            if(line == null) break;
                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
            return jsonHtml.toString();

        }

        protected void onPostExecute(String str){
            try{

//                JSONObject root = new JSONObject(str);
//                JSONArray ja = root.getJSONArray("results");
                JSONArray json = new JSONArray(str);
                for(int i=0; i<json.length(); i++){
                    JSONObject jo = json.getJSONObject(i);
                    listItem.add(new ListItem(jo.getString("addr1"),
                            jo.getString("addr2"),
                            jo.getString("areacode"),
                            jo.getString("cat1"),
                            jo.getString("cat2"),
                            jo.getString("cat3"),
                            jo.getString("contentid"),
                            jo.getString("contenttypeid"),
                            jo.getString("createdtime"),
                            jo.getString("firstimage"),
                            jo.getString("firstimage2"),
                            jo.getString("mapx"),
                            jo.getString("mapy"),
                            jo.getString("mlevel"),
                            jo.getString("modifiedtime"),
                            jo.getString("readcount"),
                            jo.getString("sigungucode"),
                            jo.getString("tel"),
                            jo.getString("title"),
                            jo.getString("zipcode")

                    ));

                }
                AddMaker am = new AddMaker(map, listItem);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }

//
//    public ArrayList<ListItem> getListItem(){
//        //Log.d("123123",String.valueOf(listItem.size()));
//        return listItem;
//
//
//    }
}
