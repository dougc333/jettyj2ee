<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

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
    <div id="container" class="signup" >
        <div id="head" style="background-color:#002957;width:100%;height:35px;">
            <div style="float:right;width:50px;margin:10px;" id="headnav">
                <security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
                    <a style="text-decoration:none;color:#fff;" href="<c:url value="/j_spring_security_logout" />"><fmt:message key="link.logout" /></a>
                </security:authorize>
            </div>
        </div>
        <div id="signup_container">
            <%--<h1 class="logo">Project rhok</h1>--%>
            <c:choose>
                <c:when test="${success}">
                    <h2 style="width:500px;margin:25px auto;" class="success" >Messages sent!</h2>
                </c:when>
                <c:otherwise>
                    <div style="background-color:#e0e0e0;width:740px;border:2px solid #404040;font-size:1.5em;padding:20px">
                        <h2 style="margin-bottom:5px;font-size:1.25em">Select Zip Codes:</h2>
                        <ul style="list-style:circle;border:1px solid #000;padding:5px;background:#fff;margin-bottom:20px">
                        <c:forEach items="${zips}" var="zip">
                            <c:if test="${not empty zip}">
                                <li style="list-style:disc;list-style-position:inside;"><a href="#" class="add-link">${zip}</a></li>
                            </c:if>
                        </c:forEach>
                        </ul>

                        <h2 style="margin-bottom:5px;font-size:1.25em">Sending to Zip Codes:</h2>
                        <div style="border:1px solid #000;padding:5px;background:#fff;margin-bottom:20px;" id="zips_to_send_to"></div>

                        <h2 style="margin-bottom:5px;font-size:1.25em">Message:</h2>
                        <form action="<c:url value="/admin/index.html" />" method="POST">
                            <input type="hidden" id="zips" name="zips" value="" />
                            <textarea style="width:100%;padding:5px;" rows="2" cols="70" name="message"></textarea><br />
                            <input style="margin-top:25px;" type="image" src="<c:url value="/static/images/submit.png"/>" height="66" width="175" alt="Submit"/>
                        </form>


                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <script type="text/javascript">
        YUI().use('node', function(Y) {
            var init = function() {
                var nodes = Y.all('.add-link');

                var handleClick = function(e) {
                    e.preventDefault();
                    var link = e.currentTarget;
                    var hidden = Y.one('#zips');
                    var div = Y.one('#zips_to_send_to');
                    hidden.set('value', hidden.get('value') === '' ? link.get('innerHTML') : hidden.get('value') + ',' + link.get('innerHTML'));
                    div.set('innerHTML', div.get('innerHTML') === '' ? link.get('innerHTML') : div.get('innerHTML') + ',' + link.get('innerHTML'));
                    var li = link.get('parentNode');
                    li.setStyle('display', 'none');
                };

                nodes.on('click', handleClick);
            };
            Y.on('domready', init);
        });
    </script>
    </body>
</html>