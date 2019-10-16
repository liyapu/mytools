package com.lyp.learn.utils;

import javax.servlet.http.HttpServletRequest;

public class IpUtils2 {
    private final static String UNKNOWN_STR = "unknown";
    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR",
            "X-Real-IP"
    };

    /***
     * 获取客户端ip地址(可以穿透代理)
     * @param request
     * @return
     */
    public static String getClientAddr(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && !"".equals(ip.trim()) && !UNKNOWN_STR.equalsIgnoreCase(ip)) {
                if ("X-Forwarded-For".equals(header)) {
                    int index = ip.indexOf(',');
                    if (index != -1) {
                        return ip.substring(0, index);
                    }
                }
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

}
