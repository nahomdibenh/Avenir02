public class User {
    protected boolean funder = false;
    protected int userId = 0;
    protected String name =  null;
    protected String email = null;
    protected String currentPage = "feed";

    public User(boolean funder) {
        this.funder = funder;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void getFunder(boolean getFunder){
        this.funder = getFunder;
    }
    public void setFunder(boolean funder) {
        this.funder = funder;
    }
    
    public String getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
    
}
