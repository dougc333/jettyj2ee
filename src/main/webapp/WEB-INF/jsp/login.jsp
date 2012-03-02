<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <div id="signup_container">
            <%--<h1 class="logo">Project rhok</h1>--%>
            <c:choose>
                <c:when test="${success}">
                    <h2 style="width:500px;margin:25px auto;" class="success" >Thanks for registering with us!</h2>
                </c:when>
                <c:otherwise>
                    <div style="background-color:#e0e0e0;width:740px;height:175px;border:2px solid #404040;">
                        <div id="signup_form_container">
                            <form id="signup_form" action="<c:url value="/j_spring_security_check"/>" method="POST">
                                <fieldset>
                                    <legend><span>Login</span></legend>
                                    <div class="field">
                                        <label class="placeholder" for="username"><span>Username</span></label>
                                        <input class="text" type="text" id="username" name="j_username" />
                                    </div>
                                    <div class="field zip-code">
                                        <label class="placeholder zip-code" for="password"><span>Passwd</span></label>
                                        <input class="text" type="password" id="password" name="j_password"/>
                                    </div>
                                    <div class="submit-button">
                                    <input type="image" src="<c:url value="/static/images/submit.png"/>" height="66" width="175" alt="Submit"/>
                                </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <script type="text/javascript">
        YUI().use('node', function(Y) {
            var init = function() {
                var nodes = Y.all('#signup_form input.text');

                var onFocus = function(e) {
                    var label = e.currentTarget.get('parentNode').one('label'); // e.currentTarget === input
                    label.setStyle('display', 'none');
                };
                var onBlur = function(e) {
                    if(e.currentTarget.get('value') == '') {
                        var label = e.currentTarget.get('parentNode').one('label'); // e.currentTarget === input
                        label.setStyle('display', '');
                    }
                };


                nodes.on('focus', onFocus);
                nodes.on('blur', onBlur);
                nodes.set('value', '');
            };
            Y.on('domready', init);


        });
    </script>
    </body>
</html>


