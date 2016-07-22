package kr.sumeng.sumproject.DB;

/**
 * Created by sum on 2016-07-19.
 */
public class ListItem {
    private String[] mData;

    public ListItem(String[] data) {
        mData = data;
    }

    public ListItem(
            String addr1,
            String addr2,
            String areacode,
            String cat1,
            String cat2,
            String cat3,
            String contentid,
            String contenttypeid,
            String createdtime,
            String firstimage,
            String firstimage2,
            String mapx,
            String mapy,
            String mlevel,
            String modifiedtime,
            String readcount,
            String sigungucode,
            String tel,
            String title,
            String zipcode) {

        mData = new String[20];
        mData[0] = addr1;
        mData[1] = addr2;
        mData[2] = areacode;
        mData[3] = cat1;
        mData[4] = cat2;
        mData[5] = cat3;
        mData[6] = contentid;
        mData[7] = contenttypeid;
        mData[8] = createdtime;
        mData[9] = firstimage;
        mData[10] = firstimage2;
        mData[11] = mapx;
        mData[12] = mapy;
        mData[13] = mlevel;
        mData[14] = modifiedtime;
        mData[15] = readcount;
        mData[16] = sigungucode;
        mData[17] = tel;
        mData[18] = title;
        mData[19] = zipcode;


    }

    public String[] getData() {
        return mData;
    }

    public String getData(int index) {
        return mData[index];
    }

    public void setData(String[] data) {
        mData = data;
    }
}
