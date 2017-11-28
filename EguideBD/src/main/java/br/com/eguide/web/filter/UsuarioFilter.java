package br.com.eguide.web.filter;

import br.com.eguide.web.LoginBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UsuarioFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

        String url = req.getRequestURL().toString();
        if (loginBean == null) {
            res.sendRedirect("/EguideBD/login.xhtml");
            return;
        }
        if ((url.contains("/admin") || url.contains("/user")) && loginBean.getUsuario() == null) {
            res.sendRedirect("/EguideBD/login.xhtml");
        } else {
            try {
                if (loginBean.isNivel("admin")) {
                    chain.doFilter(request, response);
                } else if (loginBean.isNivel("user") && url.contains("/user")) {
                    chain.doFilter(request, response);
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void destroy() {
    }

}
