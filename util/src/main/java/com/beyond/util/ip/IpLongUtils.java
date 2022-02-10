package com.beyond.util.ip;

/**
 * MySQL提供了相应的函数来把字符串格式的IP转换成整数的INET_ATON，以及把整数格式的IP转换成字符串的INET_NTOA
 * select inet_aton('192.168.0.1');
 * select inet_ntoa(3232235521);
 *
 * 对于IPv6来说，使用VARBINARY同样可获得相同的好处，同时MySQL也提供了相应的转换函数，即INET6_ATON和INET6_NTOA。
 *
 * @Author huangyanbing
 * @Date 2022/2/10 21:53
 * @description
 */
public class IpLongUtils {
    /**
     * 把字符串IP转换成long
     *
     * @param ipStr 字符串IP
     * @return IP对应的long值
     */
    public static long ip2Long(String ipStr) {
        String[] ip = ipStr.split("\\.");
        return (Long.valueOf(ip[0]) << 24) + (Long.valueOf(ip[1]) << 16)
                + (Long.valueOf(ip[2]) << 8) + Long.valueOf(ip[3]);
    }

    /**
     * 把IP的long值转换成字符串
     *
     * @param ipLong IP的long值
     * @return long值对应的字符串
     */
    public static String long2Ip(long ipLong) {
        StringBuilder ip = new StringBuilder();
        ip.append(ipLong >>> 24).append(".");
        ip.append((ipLong >>> 16) & 0xFF).append(".");
        ip.append((ipLong >>> 8) & 0xFF).append(".");
        ip.append(ipLong & 0xFF);
        return ip.toString();
    }

    public static void main(String[] args) {
        System.out.println(ip2Long("192.168.0.1"));
        System.out.println(long2Ip(3232235521L));
        System.out.println(ip2Long("10.0.0.1"));
    }
}
