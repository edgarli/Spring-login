package com.practice.login.entity;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table("login_user")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "LOGIN_USER_SEQ")
    private int id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone")
    private  String phone;

    @Column(name = "address")
    private  String address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profiles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public void setProfiles(List<UserProfile> profiles) {
        this.profiles = profiles;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_profile", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "user_profile_id", referencedColumnName = "id") })
    private List<UserProfile> profiles = new ArrayList<UserProfile>();

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", profiles=" + profiles + "]";
    }
}
