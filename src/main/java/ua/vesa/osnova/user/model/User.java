package ua.vesa.osnova.user.model;

import org.hibernate.validator.constraints.Email;
import ua.vesa.osnova.user.valid.UserEmailValid;
import ua.vesa.osnova.user.valid.UserNameValid;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {


    @Id
    @NotNull
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @Column(name = "username", unique = true, nullable = false, length = 45)
    @UserNameValid
    @Size(min = 3, max = 20, message = "мин. 3 макс. 20")
    private String username;
    @Size(min = 6, message = "мин. 6")
    private String password;
    @Size(min = 3, max = 20, message = "мин. 6 макс. 20")
    private String position;
    @Size(min = 3, max = 20, message = "мин. 3 макс. 20")
    private String name;
    @Size(min = 3, max = 20, message = "мин. 3 макс. 20")
    private String surname;
    @Size(min = 10, max = 18, message = "некорректный телефон")
    private String phone;

    @Email(message = "некорректный e-mail")
    @UserEmailValid
    private String email;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<UserRole> userRoles = new HashSet<>(0);

    private Long date_register;

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public int getId() {
        return user_id;
    }

    public void setId(int user_id) {
        this.user_id = user_id;
    }

    public Long getDate_register() {
        return date_register;
    }

    public void setDate_register(Long date_register) {
        this.date_register = date_register;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set userRoles) {
        this.userRoles = userRoles;
    }
}
