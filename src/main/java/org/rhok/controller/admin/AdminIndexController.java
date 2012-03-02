package org.rhok.controller.admin;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.rhok.domain.Provider;
import org.rhok.domain.User;
import org.rhok.service.DomainObjectService;
import org.rhok.service.EmailService;
import org.rhok.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class AdminIndexController
{
    public static final String URL_MAPPING = "/admin/index.html";
    public static final String VIEW_NAME = "admin/index";

    private final static Logger logger = Logger.getLogger(AdminIndexController.class);

    private DomainObjectService domainObjectService;
    private EmailService emailService;

    @Autowired
    public AdminIndexController(DomainObjectService domainObjectService, EmailServiceImpl emailService)
    {
        this.domainObjectService = domainObjectService;
        this.emailService = emailService;
    }

    @RequestMapping(value = URL_MAPPING, method = RequestMethod.GET)
    public String showView(ModelMap model)
    {
        List<String> zips = domainObjectService.getAllByQuery("select distinct u.zipCode from User u");

        model.addAttribute("zips", zips);


        return VIEW_NAME;
    }

    @RequestMapping(value = URL_MAPPING, method = RequestMethod.POST)
    public String submitForm(@RequestParam("zips") Set<String> zips, @RequestParam("message") String message)
    {
        StringBuilder builder = new StringBuilder("('");
        builder.append(StringUtils.join(zips, "', '"));
        builder.append("')");

        List<User> usersInThisZip = domainObjectService.getAllByQuery("select u from User u where u.zipCode in " + builder.toString());

        for(User userInZip : usersInThisZip)
        {
            logger.debug("User in zip" + zips + ": " + userInZip.getMobilePhone());
            for(Provider provider : Provider.values())
            {
                emailService.sendEmail(userInZip.getMobilePhone() + provider.getEmailSuffix(), message);
            }
        }
        
        return VIEW_NAME;
    }
}
