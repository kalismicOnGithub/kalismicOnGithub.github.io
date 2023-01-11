package cz.cvut.fel.via.kalismic.mtm.resources.model;

public class MeInfo {

    private String username;
    private boolean isUser;
    private boolean isAdmin;

    public MeInfo() {
    }

    public MeInfo(String username, boolean isUser, boolean isAdmin) {
        this.username = username;
        this.isUser = isUser;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "MeInfo{" + "username=" + username + ", isUser=" + isUser + ", isAdmin=" + isAdmin + '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isIsUser() {
        return isUser;
    }

    public void setIsUser(boolean isUser) {
        this.isUser = isUser;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
