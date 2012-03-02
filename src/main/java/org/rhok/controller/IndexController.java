package org.rhok.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.rhok.domain.Provider;
import org.rhok.domain.User;
import org.rhok.service.DomainObjectService;
import org.rhok.service.EmailService;
import org.rhok.service.EmailServiceImpl;
import org.rhok.validation.DomainObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

/**
 * @author 287 Development, 287dev.com
 */
@Controller
@RequestMapping(value = {IndexController.URL_MAPPING, "/"})
@SessionAttributes(IndexController.COMMAND_NAME)
public class IndexController
{
    public static final String COMMAND_NAME = "user";
    public static final int RANDOM_STRING_LENGTH = 6;
    public static final String URL_MAPPING = "/index.html";
    public static final String VIEW_NAME = "index";

    public static final Logger logger = Logger.getLogger(IndexController.class);

    
    private DomainObjectService domainObjectService;
    private EmailService emailService;
    private Validator validator;

    @Autowired
    public IndexController(DomainObjectService domainObjectService, EmailServiceImpl emailService, DomainObjectValidator validator)
    {
        this.domainObjectService = domainObjectService;
        this.emailService = emailService;
        this.validator = validator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handleGet(ModelMap model) throws Exception
    {
        model.addAttribute(new User());

        return VIEW_NAME;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(@ModelAttribute(COMMAND_NAME) User user, BindingResult result, ModelMap model)
    {
        validator.validate(user, result);

        if(result.hasErrors())
        {
            return VIEW_NAME;
        }

        domainObjectService.save(user);

//        sendValidationCode(user.getMobilePhone());

        model.addAttribute("success", true);

        return VIEW_NAME;
    }

    private void sendValidationCode(String mobilePhone)
    {
        String validationCode = RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH);

        for(Provider provider : Provider.values())
        {
            emailService.sendEmail(mobilePhone + provider.getEmailSuffix(), validationCode);
        }
    }
}