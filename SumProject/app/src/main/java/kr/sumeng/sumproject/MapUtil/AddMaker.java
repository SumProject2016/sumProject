package kr.sumeng.sumproject.MapUtil;

import android.util.Log;

import net.daum.mf.map.api.*;

import java.util.ArrayList;

import kr.sumeng.sumproject.DB.ListItem;


/**
 * Created by sum on 2016-07-18.
 */
public class AddMaker {
    private MapPOIItem marker;

    public AddMaker(MapView mapView, ArrayList<ListItem> list) {
        for (int i = 0; i < list.size(); i++) {
            marker = new MapPOIItem();
            marker.setItemName(list.get(i).getData(18));
            marker.setTag(Integer.parseInt(list.get(i).getData(6)));
            MapPoint mp = MapPoint.mapPointWithGeoCoord(Double.valueOf(list.get(i).getData(12)), Double.valueOf(list.get(i).getData(11)));
            marker.setMapPoint(mp);
            marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
            mapView.addPOIItem(marker);
        }
    }

}
