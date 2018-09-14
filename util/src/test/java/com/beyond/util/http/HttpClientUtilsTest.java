package com.beyond.util.http;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class HttpClientUtilsTest {

    @Ignore
    @Test
    public void getUTConvertToCurrentSystemZoneDate() throws Exception {
        String apiUrl = "https://api.cnexps.com/cgi-bin/EmsData.dll?DoApi";
        String json = "{\"icID\":71,\"RequestName\":\"PreInputSet\",\"TimeStamp\":1526259058120,\"MD5\":\"93bf4ef579a7dd03d543959163591574\",\"RecList\":[{\"iID\":0,\"nItemType\":1,\"cRNo\":\"1559989319291114494\",\"cDes\":\"GB\",\"cEmsKind\":\"CNE全球通挂号\",\"fWeight\":0.25,\"iItem\":1,\"nPayWay\":1,\"cReceiver\":\"Kim Mah\",\"cRPhone\":\"01142 745411\",\"cRPostcode\":\"S8 0HY\",\"cRCountry\":\"GB\",\"cRProvince\":\"South Yorkshire\",\"cRCity\":\"Sheffield\",\"cRAddr\":\"14 Periwood Avenue\",\"cAddrFrom\":\"EBAY\",\"cSender\":\"Li Lei\",\"cSAddr\":\"Zheng yang address\",\"cSCity\":\"Beijing\",\"cSPostcode\":\"1000000\",\"cSCountry\":\"CN\",\"cSProvince\":\"Beijing\",\"cSPhone\":\"15458654744\",\"cOrigin\":\"CN\",\"cMoney\":\"USD\",\"GoodsList\":[{\"cxGoods\":\"女士连衣裙\",\"cxGoodsA\":\"Women dress\",\"cxGCodeA\":\"FF39418A3\",\"ixQuantity\":1,\"fxPrice\":2.0,\"cxMoney\":\"USD\",\"cxOrigin\":\"CN\"}]}]}";
        String result = HttpClientUtils.doPostSSL(apiUrl, json);
        System.out.println(result);
    }

    @Ignore
    @Test
    public void connectTimeout() throws Exception {
        String apiUrl = "https://api.cnexps.com/cgi-bin/EmsData.dll?DoApi";
        String json = "{\"icID\":71,\"RequestName\":\"PreInputSet\",\"TimeStamp\":1526259058120,\"MD5\":\"93bf4ef579a7dd03d543959163591574\",\"RecList\":[{\"iID\":0,\"nItemType\":1,\"cRNo\":\"1559989319291114494\",\"cDes\":\"GB\",\"cEmsKind\":\"CNE全球通挂号\",\"fWeight\":0.25,\"iItem\":1,\"nPayWay\":1,\"cReceiver\":\"Kim Mah\",\"cRPhone\":\"01142 745411\",\"cRPostcode\":\"S8 0HY\",\"cRCountry\":\"GB\",\"cRProvince\":\"South Yorkshire\",\"cRCity\":\"Sheffield\",\"cRAddr\":\"14 Periwood Avenue\",\"cAddrFrom\":\"EBAY\",\"cSender\":\"Li Lei\",\"cSAddr\":\"Zheng yang address\",\"cSCity\":\"Beijing\",\"cSPostcode\":\"1000000\",\"cSCountry\":\"CN\",\"cSProvince\":\"Beijing\",\"cSPhone\":\"15458654744\",\"cOrigin\":\"CN\",\"cMoney\":\"USD\",\"GoodsList\":[{\"cxGoods\":\"女士连衣裙\",\"cxGoodsA\":\"Women dress\",\"cxGCodeA\":\"FF39418A3\",\"ixQuantity\":1,\"fxPrice\":2.0,\"cxMoney\":\"USD\",\"cxOrigin\":\"CN\"}]}]}";
        String result = HttpClientUtils.connectTimeout(apiUrl, json);
        System.out.println(result);
    }

    @Ignore
    @Test
    public void socketTimeout() throws Exception {
        String apiUrl = "https://api.cnexps.com/cgi-bin/EmsData.dll?DoApi";
        String json = "{\"icID\":71,\"RequestName\":\"PreInputSet\",\"TimeStamp\":1526259058120,\"MD5\":\"93bf4ef579a7dd03d543959163591574\",\"RecList\":[{\"iID\":0,\"nItemType\":1,\"cRNo\":\"1559989319291114494\",\"cDes\":\"GB\",\"cEmsKind\":\"CNE全球通挂号\",\"fWeight\":0.25,\"iItem\":1,\"nPayWay\":1,\"cReceiver\":\"Kim Mah\",\"cRPhone\":\"01142 745411\",\"cRPostcode\":\"S8 0HY\",\"cRCountry\":\"GB\",\"cRProvince\":\"South Yorkshire\",\"cRCity\":\"Sheffield\",\"cRAddr\":\"14 Periwood Avenue\",\"cAddrFrom\":\"EBAY\",\"cSender\":\"Li Lei\",\"cSAddr\":\"Zheng yang address\",\"cSCity\":\"Beijing\",\"cSPostcode\":\"1000000\",\"cSCountry\":\"CN\",\"cSProvince\":\"Beijing\",\"cSPhone\":\"15458654744\",\"cOrigin\":\"CN\",\"cMoney\":\"USD\",\"GoodsList\":[{\"cxGoods\":\"女士连衣裙\",\"cxGoodsA\":\"Women dress\",\"cxGCodeA\":\"FF39418A3\",\"ixQuantity\":1,\"fxPrice\":2.0,\"cxMoney\":\"USD\",\"cxOrigin\":\"CN\"}]}]}";
        String result = HttpClientUtils.socketTimeout(apiUrl, json);
        System.out.println(result);
    }
}
