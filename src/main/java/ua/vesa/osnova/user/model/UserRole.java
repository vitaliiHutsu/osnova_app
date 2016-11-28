package ua.vesa.osnova.user.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_roles")
public class UserRole implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id",
            unique = true, nullable = false)
    private Integer userRoleId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String role;

    public UserRole() {
    }

    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
