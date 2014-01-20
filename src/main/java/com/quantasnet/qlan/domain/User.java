package com.quantasnet.qlan.domain;

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
	@Size(min = 4, max = 255)
	@Column(name = "user_name", nullable = false, unique = true, length = 255)
	private String userName;

	@NotEmpty(message = "Password is required.")
	@Size(min = 8, max = 255)
	@Pattern(regexp = "^(?=.*[0-9])(?=\\S+$).{8,}$", message = "Password must be at least 8 characters and contain a number.")
	@Column(name = "user_password", nullable = true, length = 255)
	private String password;

	@NotEmpty(message = "First name is required.")
	@Size(min = 2, max = 30)
	@Column(name = "user_first_name", nullable = true)
	private String firstName;

	@NotEmpty(message = "Last name is required.")
	@Size(min = 2, max = 30)
	@Column(name = "user_last_name", nullable = true)
	private String lastName;

	@NotEmpty
	@Email
	@Column(name = "user_email", nullable = true, unique = true)
	private String email;

	@Column(name = "user_image_url", length = 255)
	private String imageUrl;

	@Column(name = "steam")
	private boolean steam;
	
	@Column(name = "steam_online")
	private boolean steamOnline;
	
	@Column(name = "steam_current_game", nullable = true, length = 255)
	private String steamGame;

	@Column(name = "steam_id", nullable = true)
	private Long steamId;
	
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isSteam() {
		return steam;
	}
	
	public void setSteam(boolean steam) {
		this.steam = steam;
	}
	
	public boolean isSteamOnline() {
		return steamOnline;
	}
	
	public void setSteamOnline(boolean steamOnline) {
		this.steamOnline = steamOnline;
	}
	
	public void setSteamGame(String steamGame) {
		this.steamGame = steamGame;
	}
	
	public String getSteamGame() {
		return steamGame;
	}
	
	public void setSteamId(Long steamId) {
		this.steamId = steamId;
	}
	
	public Long getSteamId() {
		return steamId;
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
	
	public boolean isAdmin() {
		if (null != roles && !roles.isEmpty()) {
			for (final Role role : roles) {
				if (role.getRoleName().equals(Role.ADMIN)) {
					return true;
				}
			}
		}
		return false;
	}
}
