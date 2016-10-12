package com.lishi.recruit.system.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 
 * <P>
 * 网贷自定义过滤器
 * </P>
 * 
 * @user ewt
 * @Author 张太
 * @since 2015年4月7日 下午4:53:17
 * @version 1.0.0
 */
public class SysLoanFitler extends OncePerRequestFilter
{

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
    	
    	request.setCharacterEncoding("UTF-8");

    	 response.setContentType("text/html;charset=UTF-8");

//        // 不过滤的uri
//        String[] notFilter = new String[] { "/user/login", "/user/tologin.do","/home",""};
//
//        String uri = request.getRequestURI();
//        System.out.println("URL---->"+uri);
//        boolean doFilter = true;
//        for (String s : notFilter) {
//            if (uri.indexOf(s) != -1) {
//                
//                // 如果uri中包含不过滤的uri，则不进行过滤
//                doFilter = false;
//                break;
//            }
//        }
//
//        if(doFilter)
//        {
//            Object obj = request.getSession().getAttribute("user");
//            if (null == obj)
//            {
//                System.out.println("------------------");
//                boolean isAjaxRequest = isAjaxRequest(request);                                                          
//                if (isAjaxRequest)                                                                             
//                {                                                                                              
//                    response.setCharacterEncoding("UTF-8");                                                    
//                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");  
//                    return ;                                                                                   
//                }                                                                                              
//                response.sendRedirect("/ewt-netloan/person/tologin");                                                     
//                return;                                                                                        
//            }                                                                                                  
//            else                                                                                               
//            {                                                                                                  
//                // 如果session中存在登录者实体，则继续                                                         
//                filterChain.doFilter(request, response);                                                       
//            }                                                                                                  
//        }
//        else
//        {
//            filterChain.doFilter(request, response);
//        }
                                                                                                           
    }

    /** 判断是否为Ajax请求                                                     
     * <功能详细描述>                                                          
     * @param request                                                          
     * @return 是true, 否false                                                 
     * @see [类、类#方法、类#成员]                                             
     */                                                                        
    public static boolean isAjaxRequest(HttpServletRequest request)            
    {                                                                          
        String header = request.getHeader("X-Requested-With");                 
        if (header != null && "XMLHttpRequest".equals(header))                 
            return true;                                                       
        else                                                                   
            return false;                                                      
    }                                                                          
                                                                           


}
