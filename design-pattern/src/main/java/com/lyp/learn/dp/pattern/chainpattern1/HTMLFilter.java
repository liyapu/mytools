package com.lyp.learn.dp.pattern.chainpattern1;

/**
 * 处理字符串中的HTML标记
 */
public class HTMLFilter implements Filter {

    /**
     * 将字符串中出现的"<>"符号替换成"[]"
     *
     * 添加 HTMLFilter 是便于我们观察代码执行步骤的字符串
     * @param request
     * @param response
     * @param chain
     */
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        System.out.println("HTMLFilter   start");
        String requestStr = request.getRequestStr();
        requestStr = requestStr.replaceAll("<","[")
                                .replaceAll(">","]")
                                + " HTMLFilter ";
        request.setRequestStr(requestStr);

        response.setResponseStr(response.getResponseStr() + " HTMLFilter ");

        chain.doFilter(request,response,chain);

        System.out.println("HTMLFilter   end");

    }
}
