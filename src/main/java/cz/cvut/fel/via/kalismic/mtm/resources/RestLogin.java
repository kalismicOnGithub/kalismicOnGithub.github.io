package cz.cvut.fel.via.kalismic.mtm.resources;

import cz.cvut.fel.via.kalismic.mtm.resources.model.LoginInfo;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("v1/security")
public class RestLogin {

    @Inject
    private HttpServletRequest request;

    // GET for login is a SECURITY ISSUE! Here it is used only for this demo to use only browser.
    @GET
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {
        // create session
        request.getSession(true);
        try {
            // login
            request.login(username, password);
            return Response.ok("Login OK").build();
        } catch (ServletException ex) {
            Logger.getLogger(RestLogin.class.getName()).log(Level.SEVERE, ex, () -> "Login failed for user '" + username + "' with reason: " + ex.getMessage());
            return Response.status(401 /*Unauthorized*/, "Login failed with reason: " + ex.getMessage()).build();
        }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(LoginInfo loginInfo) {
        String username = loginInfo.getUsername();
        String password = loginInfo.getPassword();
        try {
            // create session
            request.getSession(true);
            // login
            request.login(username, password);
            return Response.ok("Login OK").build();
        } catch (ServletException ex) {
            Logger.getLogger(RestLogin.class.getName()).log(Level.SEVERE, ex, () -> "Login failed for user '" + username + "' with reason: " + ex.getMessage());
            return Response.status(401 /*Unauthorized*/, "Login failed with reason: " + ex.getMessage()).build();
        }
    }

    @GET
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout() {
        try {
            request.logout();
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            // A good idea is to remove also the cookie from client
            NewCookie removeJSessionIDCookie = new NewCookie("JSESSIONID", null, request.getContextPath(), "", NewCookie.DEFAULT_VERSION, null, 0, new Date(), false, true);
            return Response
                    .ok("User logged out.")
                    .cookie(removeJSessionIDCookie)
                    .build();
        } catch (ServletException ex) {
            Logger.getLogger(RestLogin.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return Response.status(500, "Logout failed with reason: " + ex.getMessage()).build();
        }
    }
}
