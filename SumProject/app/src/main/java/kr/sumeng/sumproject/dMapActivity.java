package kr.sumeng.sumproject;

import android.app.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import net.daum.mf.map.api.*;

import java.util.Timer;
import java.util.TimerTask;

import kr.sumeng.sumproject.DB.DataReader;

import kr.sumeng.sumproject.MapUtil.AddMaker;
import kr.sumeng.sumproject.MapUtil.GpsInfo;

public class dMapActivity extends Activity implements MapView.POIItemEventListener {
    private String language;
    boolean clikedCheck = true;
    DisplayMetrics dis = new DisplayMetrics();
    private GpsInfo myLoc;
    private AddMaker am;
    public MapView mapView;
    private DataReader dr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_map);


        mapView = new MapView(this);
        mapView.setDaumMapApiKey("80e5712aa4c774085228d93ba58616d3");

//xml에 선언된 map_view 레이아웃을 찾아온 후, 생성한 MapView객체 추가
        RelativeLayout container = (RelativeLayout) findViewById(R.id.map_view);

        container.addView(mapView);

        language = getIntent().getStringExtra("lang");

        myLoc = new GpsInfo(dMapActivity.this);

        // String lo = String.valueOf(myLoc.getLatitude());

        //Toast.makeText(dMapActivity.this, lo, Toast.LENGTH_SHORT).show();

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(myLoc.getLatitude(), myLoc.getLongitude()), true);

        Log.d("12123123123123", mapView.toString());
        dr = new DataReader("http://192.168.1.44:8080/item.php", mapView);

        //am = new AddMaker(mapView,dr.getListItem());

        mapView.setPOIItemEventListener(this);

        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);

        mapView.setShowCurrentLocationMarker(true);

        //setMyLocation();

    }

    private void setMyLocation() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                myLoc.getLocation();
                double lat = myLoc.getLatitude();
                double lng = myLoc.getLongitude();
                Log.d("lat", "lat : " + lat);
                Log.d("lng", "lng : " + lng);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 2000);
    }


    private MapPOIItem temp = null;

    //인터페이스
    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        RelativeLayout map_parent = (RelativeLayout) findViewById(R.id.map_view);
        RelativeLayout map_Preview = (RelativeLayout) findViewById(R.id.preView);
        RelativeLayout.LayoutParams map = (RelativeLayout.LayoutParams) map_parent.getLayoutParams();
        RelativeLayout.LayoutParams preview = (RelativeLayout.LayoutParams) map_Preview.getLayoutParams();

        WebView webView = (WebView) findViewById(R.id.Wview);
        DaumMapWebViewClient client = new DaumMapWebViewClient();
        webView.setWebViewClient(client);
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setAllowContentAccess(true);
//        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
//        webView.getSettings().setBuiltInZoomControls(false);
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webView.getSettings().setLoadWithOverviewMode(true);
//        webView.getSettings().setDomStorageEnabled(true);

        webView.loadUrl("http://192.168.1.44:8080/item_detail.php?contentid=" + mapPOIItem.getTag());//컨텐트아이디 전달
        Log.d("dd", "http://192.168.1.44:8080/item_detail.php?contentid=" + mapPOIItem.getTag());
        getWindowManager().getDefaultDisplay().getMetrics(dis);

        if (temp == null) {
            temp = mapPOIItem;
        }

        if (temp.equals(mapPOIItem)) {
            if (clikedCheck) {
                map.height = dis.heightPixels / 2;
                preview.height = dis.heightPixels / 2;
                clikedCheck = false;

            } else {
                map.height = dis.heightPixels;
                clikedCheck = true;
                temp = null;
            }
        } else {
            temp = mapPOIItem;
        }
        map_parent.setLayoutParams(map);
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {


    }

    class DaumMapWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d("onPageStarted", url);

            RelativeLayout map_Preview = (RelativeLayout) findViewById(R.id.preView);
            RelativeLayout.LayoutParams preview = (RelativeLayout.LayoutParams) map_Preview.getLayoutParams();

            if (url.contains("http://192.168.1.44:8080/write.php")) {
                preview.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                map_Preview.setLayoutParams(preview);
                Toast t = Toast.makeText(getApplicationContext(), "페이지 로딩완료 url : " + url, Toast.LENGTH_LONG);
                t.show();
            }
            if (url.contains("http://192.168.1.44:8080/item_detail.php")) {
                preview.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                map_Preview.setLayoutParams(preview);
                Toast t = Toast.makeText(getApplicationContext(), "페이지 로딩완료 url : " + url, Toast.LENGTH_LONG);
                t.show();
            }

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
//            if(url.contains("http://192.168.1.44:8080/write.php")){
//                RelativeLayout map_Preview = (RelativeLayout) findViewById(R.id.preView);
//                RelativeLayout.LayoutParams preview = (RelativeLayout.LayoutParams) map_Preview.getLayoutParams();
//                preview.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//                Toast t = Toast.makeText(getApplicationContext(), "페이지 로딩완료 url : " + url, Toast.LENGTH_LONG);
//                t.show();
//                preview.height = dis.heightPixels;
//            }

            Log.d("onPageFinished", url);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
