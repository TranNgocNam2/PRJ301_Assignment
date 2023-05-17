package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import basicobject.Item;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <form action=\"loginServlet\" method=\"POST\">\n");
      out.write("            <input type=\"text\" name=\"t1\"/>\n");
      out.write("            <input type=\"text\" name=\"t2\"/>\n");
      out.write("            <input type=\"submit\" value=\"login\"/>\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("        ");

            String flag = (String) request.getAttribute("Error");
            if (flag != null) {
                out.print(flag);
            }
        
      out.write("\n");
      out.write("\n");
      out.write("        <hr/>\n");
      out.write("        Enter keyword to find items:\n");
      out.write("        <form action=\"findServlet\" method=\"POST\">\n");
      out.write("            <input type=\"text\" name=\"key\"/>\n");
      out.write("            <input type=\"submit\" value=\"find\"/>\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("        <!-- chỗ code này là để hiển thị kết quả sau khi find -->\n");
      out.write("        ");

            ArrayList<Item> list = (ArrayList) request.getAttribute("listofitem");
            if (list != null && list.size() > 0) {
        
      out.write("\n");
      out.write("        <table>\n");
      out.write("            <tr>\n");
      out.write("                <th>ID</th>\n");
      out.write("                <th>Name</th>\n");
      out.write("                <th>Image</th>\n");
      out.write("                <th>Price</th>\n");
      out.write("                <th>Choose</th>\n");
      out.write("            </tr>\n");
      out.write("            ");

                for (Item it : list) {
            
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td>");
      out.print(it.getId());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(it.getName());
      out.write("</td>\n");
      out.write("                <td><img src=\"");
      out.print(it.getImage());
      out.write("\" width=\"100px\" weight=\"100px\"/></td>\n");
      out.write("                <td>");
      out.print(it.getPrice() );
      out.write("</td>\n");
      out.write("                <td><a href=\"#\">Buy</a></td>\n");
      out.write("            </tr>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("        </table>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
