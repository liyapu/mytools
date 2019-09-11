package com.lyp.learn.dp.pattern.chainpattern1;

/**
 * 定义的过滤敏感字眼的过滤规则
 *
 * 被就业 -> 就业
 * 敏感 ->  **
 */
public class SensitiveFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        System.out.println("SensitiveFilter   start");

        request.setRequestStr(request.getRequestStr()
                            .replaceAll("被就业","就业")
                            .replaceAll("敏感","**")
                            + " SensitiveFilter ");
        response.setResponseStr(response.getResponseStr() + " SensitiveFilter ");

        chain.doFilter(request,response,chain);

        System.out.println("SensitiveFilter   end");

    }
}
