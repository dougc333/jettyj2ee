package org.rhok.controller;

import org.rhok.service.DomainObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 287 Development, 287dev.com
 */
@Controller
@RequestMapping(LoginController.URL_MAPPING)
public class LoginController
{
    public static final String PARAM_USERNAME = "username";
    public static final String URL_MAPPING = "/login.html";
    public static final String VIEW_NAME = "login";

    private DomainObjectService domainObjectService;

    @Autowired
    public LoginController(DomainObjectService domainObjectService)
    {
        this.domainObjectService = domainObjectService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handleRequest()
    {
        return VIEW_NAME;
    }
}
