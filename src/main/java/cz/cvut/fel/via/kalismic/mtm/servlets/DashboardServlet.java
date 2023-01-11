package cz.cvut.fel.via.kalismic.mtm.servlets;

import cz.cvut.fel.via.kalismic.mtm.services.AccountService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "dashboard", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

    @EJB
    private AccountService accountService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<meta name=\"author\" content=\"Michal Kalista\">");
            out.println("<title>MTM | dashboard</title>");
            out.println("<link rel=\"stylesheet\" href=\"style.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"page\">");
            out.println("<header>");
            out.println("<div class=\"account reverse logo\">");
            out.println("<a href=\"./dashboard\">M T M</a>");
            out.println("</div>");
            out.println("<div class=\"time\">");
            out.println("<h2></h2>");
            out.println("</div>");
            out.println("<div class=\"account\">");
            out.println("<a href=\"#\">Sign out</a>");
            out.println("</div>");
            out.println("</header>");
            out.println("<section>");
            out.println("<nav>");
            out.println("<h1>My maps</h1>");
            out.println("<form id=\"newMap\">");
            out.println("<input type=\"Submit\" value=\"New map\">");
            out.println("</form>");
            /*Cookie[] cookies = request.getCookies();
            int id = 0;
            for (Cookie c : cookies) {
                if (c.getName().equals("userId")) {
                    id = Integer.parseInt(c.getValue());
                }
            }
            List<MindMap> maps = new ArrayList<>();
            if (id != 0) {
                maps = accountService.getAllUserMaps(id);
            }
            if (!maps.isEmpty()) {
                out.println("<ul>");
                maps.stream().forEach(map -> {
                    out.print("<li>");
                    out.print("<div class=\"item\">");
                    out.print("<div class=\"mapName\">");
                    out.print("<a href=\"diagram?id=" + map.getId() + "\">" + map.getTitle() + "</a>");
                    out.print("</div>");
                    out.print("<form class=\"mapButton share\" data-id=\"" + map.getId() + "\" data-name=\"" + map.getTitle() + "\">");
                    out.println("<input type=\"Submit\" value=\"share\">");
                    out.println("</form>");
                    out.print("<form class=\"mapButton rename\" data-id=\"" + map.getId() + "\" data-name=\"" + map.getTitle() + "\">");
                    out.println("<input type=\"Submit\" value=\"rename\">");
                    out.println("</form>");
                    out.print("<form class=\"mapButton delete\" data-id=\"" + map.getId() + "\" data-name=\"" + map.getTitle() + "\">");
                    out.println("<input type=\"Submit\" value=\"delete\">");
                    out.println("</form>");
                    out.print("</div>");
                    out.println("</li>");
                });
                out.println("</ul>");
            } else {
                out.println("<p>No own maps to show.</p>");
            }
            out.println("<h1>Shared with me</h1>");
            List<MindMap> sharedMaps = new ArrayList<>();
            if (id != 0) {
                sharedMaps = accountService.getAllSharedMaps(id);
            }
            if (!sharedMaps.isEmpty()) {
                out.println("<ul>");
                sharedMaps.stream().forEach(map -> {
                    out.print("<li>");
                    out.print("<div class=\"item\">");
                    out.print("<div class=\"mapName\">");
                    out.print("<a href=\"diagram?id=" + map.getId() + "\">" + map.getTitle() + "</a>");
                    out.print("</div>");
                    out.print("<form class=\"mapButton rename\" data-id=\"" + map.getId() + "\" data-name=\"" + map.getTitle() + "\">");
                    out.println("<input type=\"Submit\" value=\"rename\">");
                    out.println("</form>");
                    out.print("</div>");
                    out.println("</li>");
                });
                out.println("</ul>");
            } else {
                out.println("<p>No own maps to show.</p>");
            }*/
            out.println("</nav>");
            out.println("</section>");
            out.println("<footer>");
            out.println("<span>Michal Kalista <a href=\"mailto:kalismic@fel.cvut.cz\"> kalismic@fel.cvut.cz</a></span>");
            out.println("</footer>");
            out.println("</div>");
            out.println("<script src=\"dashboard.js\"></script>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
