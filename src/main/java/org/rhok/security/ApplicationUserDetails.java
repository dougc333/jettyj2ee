package org.rhok.security;

import org.rhok.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApplicationUserDetails implements UserDetails
{
    public static final String ADMIN_ROLE = "ROLE_ADMIN";
    public static final String USER_ROLE = "ROLE_USER";
    
    /** The User this UserDetail is for */
    private User user;

    /**
     * Constructs an ApplicationUserDetails for the given user.
     * 
     * @param user the user that will these details will represent
     */
    public ApplicationUserDetails(User user)
    {
        super();
        this.user = user;
    }

    /**
     * Returns an array of ACEGI GrantedAurthories this user will have.
     * 
     * @return an array of GrantedAuthority[] the user will have
     * @see GrantedAuthority
     */
    public Collection<GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new GrantedAuthorityImpl(USER_ROLE));
        if(user.isAdmin())
        {
            grantedAuths.add(new GrantedAuthorityImpl(ADMIN_ROLE));
        }

        return grantedAuths;
    }

    /**
     * Return the user this detail is for
     *
     * @return the User
     */
    public User getUser()
    {
        return user;
    }

    /**
     * Returns the user's password.
     *
     * @return the user's password
     */
    public String getPassword()
    {
        return user.getPassword();
    }

    /**
     * Returns the user's username.
     * 
     * @return the user's username
     */
    public String getUsername()
    {
        return user.getUsername();
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return true if the uesr's account has expired
     */
    public boolean isAccountNonExpired()
    {
        return user.isAccountNonExpired();
    }

    /**
     * Indicates whether the user's account has been locked.
     *
     * @return true if the user's account has been locked
     */
    public boolean isAccountNonLocked()
    {
        return user.isAccountNonLocked();
    }

    /**
     * Indicates whether the user's credentials has expired.
     *
     * @return true if the user's credentials have expired
     */
    public boolean isCredentialsNonExpired()
    {
        return user.isCredentialsNonExpired();
    }

    /**
     * Indicates whether the user's account is enabled.
     *
     * @return true if the user's account is enabled
     */
    public boolean isEnabled()
    {
        return user.isEnabled();
    }
}
