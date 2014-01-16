package com.quantasnet.qlan.web.domain;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
public class User extends QlanDomainBase implements UserDetails {
	private static final long serialVersionUID = 5923607403864940973L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "Username is required.")
	@Size(min = 4, max = 20)
	@Column(name = "user_name", nullable = false, unique = true)
	private String userName;

	@NotEmpty(message = "Password is required.")
	@Pattern(regexp = "^(?=.*[0-9])(?=\\S+$).{8,}$", message = "Password must be at least 8 characters and contain a number.")
	@Column(name = "user_password", nullable = false)
	private String password;

	@NotEmpty(message = "First name is required.")
	@Size(min = 2, max = 30)
	@Column(name = "user_first_name", nullable = false)
	private String firstName;

	@NotEmpty(message = "Last name is required.")
	@Size(min = 2, max = 30)
	@Column(name = "user_last_name", nullable = false)
	private String lastName;

	@NotEmpty
	@Email
	@Column(name = "user_email", nullable = false, unique = true)
	private String email;

	@Column(name = "user_gravatar_hash")
	private String gravatarHash;

	@Column(name = "user_active", nullable = false)
	private boolean active;

	@ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
	private Set<Role> roles;

	public Long getId() {

		return id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGravatarHash() {
		return gravatarHash;
	}

	public void setGravatarHash(String gravatarHash) {
		this.gravatarHash = gravatarHash;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	// UserDetails methods

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles();
	}

	@Override
	public String getUsername() {
		return getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// No-op
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// No-op
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// No-op
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive();
	}
}
