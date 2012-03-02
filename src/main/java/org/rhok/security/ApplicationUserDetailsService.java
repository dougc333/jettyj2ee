package org.rhok.security;

import org.rhok.domain.User;
import org.rhok.service.DomainObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApplicationUserDetailsService implements UserDetailsService
{
    @Autowired
    private DomainObjectService domainObjectService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException
    {
        StringBuilder query = new StringBuilder("select u from User u where u.username=:username");
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);

        User user = domainObjectService.getByQuery(query.toString(), params);

        if(user != null)
        {
            return new ApplicationUserDetails(user);
        }

        throw new UsernameNotFoundException("User Not Found");
    }
}