package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;

@WebFilter("/*")
public class LoginFilter implements Filter {

    public LoginFilter() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String context_path = ((HttpServletRequest)request).getContextPath();
        String servlet_path = ((HttpServletRequest)request).getServletPath();

        if(!servlet_path.matches("/css.*")) {
            HttpSession session = ((HttpServletRequest)request).getSession();
            User login_user = (User)session.getAttribute("login_user");

            if(servlet_path.matches("/users.*") || servlet_path.matches("/posts.*")
                    || servlet_path.matches("/comments.*") || servlet_path.matches("/favorites.*")) {
                if(login_user == null) {
                    ((HttpServletResponse)response).sendRedirect(context_path + "/login");
                    return;
                }
            }
        }

	    chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
