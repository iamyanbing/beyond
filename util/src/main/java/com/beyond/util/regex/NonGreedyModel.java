package com.beyond.util.regex;

import com.beyond.util.ioutil.Base64ToPDF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>非贪婪模式</pre>
 * <pre>
 *     量词 :
 *      +	    匹配一次或多次，相当于{1,}
 *      *	    匹配零次或多次 ，相当于{0,}
 *      ?	    匹配零次或一次 ，相当于{0,1}
 *      {n}	    匹配n次
 *      {n,m}	匹配至少n个，最多m个某某的字符串
 *      {n,}	匹配至少n个某字符串
 *
 * </pre>
 * <pre>
 *     贪婪模式就是尽可能多的匹配，非贪婪模式就是尽可能少的匹配.
 *     贪婪模式量词： {x,y} , {x,} , ? , * , 和 +
 *     非贪婪模式量词： {x,y}?，{x,}?，??，*?，和 +?,所以非贪婪模式就是在贪婪模式后面加了一个问号
 *
 *      非贪婪模式，当它匹配到它需要的第一个满足条件之后，他就会停止了。而贪婪模式则会继续向右边进行匹配下去。
 *
 *      注意：?号在一些量词后面才是指非贪婪模式，如果直接在一些字符串的后面，表示的是匹配0次或1次。
 * </pre>
 *
 * @author huangyanbing
 * @date 2019-07-17 09:49
 */
public class NonGreedyModel {
    private static final Logger LOGGER = LoggerFactory.getLogger(NonGreedyModel.class);

    public static void exe() throws Exception {
        String resouce = "<a href=\"#\">link1</a>other content <a >link2</a>";
        Pattern greedy = Pattern.compile(">.*<");
        Matcher greedyMatcher = greedy.matcher(resouce);
        while (greedyMatcher.find()) {
            LOGGER.info("贪婪匹配结果为：" + greedyMatcher.group());
        }
        Pattern nonGreedy = Pattern.compile(">.*?<");
        Matcher nonGreedyMatcher = nonGreedy.matcher(resouce);
        while (nonGreedyMatcher.find()) {
            LOGGER.info("非贪婪匹配结果为：" + nonGreedyMatcher.group());
        }

    }
}
