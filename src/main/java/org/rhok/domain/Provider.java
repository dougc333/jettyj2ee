package org.rhok.domain;

/**
 * @author 287 Development, 287dev.com
 */
public enum Provider
{
    ATT("@txt.att.net"),
    SPRINT("@messaging.sprintpcs.com"),
    T_MOBILE("@tmomail.net"),
    VERIZON_WIRELESS("@vtext.com");

    private final String emailSuffix;

    Provider(String emailSuffix)
    {
        this.emailSuffix = emailSuffix;
    }

    public String getEmailSuffix()
    {
        return emailSuffix;
    }
}
