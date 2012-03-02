<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="title" %>

<fmt:setBundle basename="resourcebundles.Main"/>
<fmt:message key="title" var="defaultTitle"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title><c:out value="${title}" default="${defaultTitle}"/></title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/static/js/yui/3.0.0/build/cssreset/reset-min.css"/>" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/default.css" />" />

        <script type="text/javascript" src="<c:url value="/static/js/yui/3.0.0/build/yui/yui-min.js" />"></script>
    </head>
    <body>
    <div id="container">
        <div id="head">
            <div id="header_content">
                <div id="headnav">
                    <ul>
                        <security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
                            <li><strong>Welcome: </strong><security:authentication property="principal.username" /></li>
                            <li><a href="<c:url value="/j_spring_security_logout" />"><fmt:message key="link.logout" /></a></li>
                        </security:authorize>
                    </ul>
                </div>
            </div>
        </div>
        <div id="content">
            <jsp:doBody />
        </div>
        <div style="clear:both"></div>
        <div id="footer">
            <div id="copy"><fmt:message key="copyright" /></div>
        </div>
    </div>
    </body>
</html>

