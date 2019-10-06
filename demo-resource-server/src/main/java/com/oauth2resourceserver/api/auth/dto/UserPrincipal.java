package com.oauth2resourceserver.api.auth.dto;// package com.example.demo.api.auth.dto;

// import java.util.Collection;
// import java.util.List;
// import java.util.Objects;
// import java.util.stream.Collectors;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.example.demo.api.auth.repository.User;

// import lombok.Getter;
// import lombok.Setter;

// public class UserPrincipal implements UserDetails {
//     @Getter @Setter private String id;
//     @Getter @Setter private String userName;
//     @Getter @Setter private String password;
//     @Getter @Setter private boolean isAccountNonExpired;
//     @Getter @Setter private boolean isAccountNonLocked;
//     @Getter @Setter private boolean isCredentialsNonExpired;
//     @Getter @Setter private boolean isEnabled;
//     @Getter @Setter private Collection<? extends GrantedAuthority> authorities;

//     public UserPrincipal() {
//     }
    
//     public UserPrincipal(User users) {
//         this.id = users.getId();
//         this.password = users.getPassword();
//         this.isAccountNonExpired = users.isAccountNonExpired();
//         this.isAccountNonLocked = users.isAccountNonLocked();
//         this.isCredentialsNonExpired = users.isCredentialsNonExpired();
//         this.isEnabled = users.isEnabled();
//     }

//     public UserPrincipal(String id, String password) {
//         this.setId(id);
//         this.setPassword(password);
//     }
//     public static UserPrincipal create(User user) {
//         List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
//                 new SimpleGrantedAuthority(role.getName().name())
//         ).collect(Collectors.toList());

//         return new UserPrincipal(
//                 user.getId(),
//                 user.getName(),
//                 user.getUsername(),
//                 user.getEmail(),
//                 user.getPassword(),
//                 authorities
//         );
//     }

//     public Long getId() {
//         return id;
//     }

//     public String getName() {
//         return name;
//     }

//     public String getEmail() {
//         return email;
//     }

//     @Override
//     public String getUsername() {
//         return username;
//     }

//     @Override
//     public String getPassword() {
//         return password;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return authorities;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }

//     @Override
//     public boolean equals(Object o) {
//         if (this == o) return true;
//         if (o == null || getClass() != o.getClass()) return false;
//         UserPrincipal that = (UserPrincipal) o;
//         return Objects.equals(id, that.id);
//     }

//     @Override
//     public int hashCode() {

//         return Objects.hash(id);
//     }
// }