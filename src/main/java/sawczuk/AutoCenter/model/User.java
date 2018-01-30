package sawczuk.AutoCenter.model;

public class User {
    private Long id;
    private String username;
    private String password;
    private Long roleId;
    private Boolean active;

    public User() {
    }

    public User(Long id, String username, String password, Long roleId, Boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
