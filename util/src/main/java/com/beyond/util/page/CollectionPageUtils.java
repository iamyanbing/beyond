package com.beyond.util.page;

import java.util.Arrays;
import java.util.List;

/**
 * @author huangyanbing
 * @date 2019-12-09 11:25
 */
public class CollectionPageUtils {
    public static void pageList(String mobiles) {
        int pageSize = 100;
        String[] mobilePhoneArray = mobiles.split(",");
        List<String> list = Arrays.asList(mobilePhoneArray);
        int totalCount = list.size();
        int pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        //int pageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        for (int j = 0; j < pageCount; j++) {
            int from = j * pageSize;
            int to = from + pageSize;
            if (to > totalCount) {
                to = totalCount;
            }
            List<String> batch = list.subList(from, to);
        }
    }
}
