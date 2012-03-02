package org.rhok.util;

import org.rhok.domain.User;
import org.rhok.security.ApplicationUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils
{
    public static ApplicationUserDetails getUserDetails()
    {
        return (ApplicationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static User getUser()
    {
        return ((ApplicationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

    public static Long getUserId()
    {
        return getUser().getId();
    }

    public static void resetAuthenticationToken(User user)
    {
        ApplicationUserDetails userDetails = new ApplicationUserDetails(user);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
    }
}