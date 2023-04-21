package com.lyp.learn.dp.pattern.chainpattern1;

/**
 *
 */
public class Client {

    public static void main(String[] args) {
        Request request = new Request();
        String originStr = "aa <替换吧>  我是敏感词 敏感 <aBc>";
        request.setRequestStr(originStr);

        Response response = new Response();
        response.setResponseStr("我是响应：：：");

        HTMLFilter htmlFilter = new HTMLFilter();
        SensitiveFilter sensitiveFilter = new SensitiveFilter();
        CharFilter charFilter = new CharFilter();

        FilterChain chain = new FilterChain();
        chain.addFilter(htmlFilter);
        chain.addFilter(sensitiveFilter);
        chain.addFilter(charFilter);

        chain.doFilter(request, response, chain);

        System.out.println();
        System.out.println("原文请求  : " + originStr);
        System.out.println("-------------------------");
        System.out.println("处理后请求 ： " + request.getRequestStr());
        System.out.println("处理后响应 ： " + response.getResponseStr());
    }
}
