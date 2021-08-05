package com.lyp.learn.dp.pattern.chainpattern1;

/**
 * 小写字母变大写
 */
public class CharFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        System.out.println("\t\tCharFilter   start");

        request.setRequestStr(request.getRequestStr()
                                .toUpperCase()
                                + " CharFilter" );
        response.setResponseStr(response.getResponseStr() + " CharFilter ");
        chain.doFilter(request,response,chain);

        System.out.println("\t\tCharFilter   end");

    }
}
