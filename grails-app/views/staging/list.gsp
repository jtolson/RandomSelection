<%@ page import="com.tci.utilities.Staging" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'staging.label', default: 'Staging')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-staging" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-staging" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="customerDataFileName"
                              title="${message(code: 'staging.customerDataFileName.label', default: 'Customer Data File Name')}"/>

            <g:sortableColumn property="customerId"
                              title="${message(code: 'staging.customerId.label', default: 'Customer Id')}"/>

            <g:sortableColumn property="studentName"
                              title="${message(code: 'staging.studentName.label', default: 'Student Name')}"/>

            <g:sortableColumn property="studentNumber"
                              title="${message(code: 'staging.studentNumber.label', default: 'Student Number')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${stagingInstanceList}" status="i" var="stagingInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${stagingInstance.id}">${fieldValue(bean: stagingInstance, field: "customerDataFileName")}</g:link></td>

                <td>${fieldValue(bean: stagingInstance, field: "customerId")}</td>

                <td>${fieldValue(bean: stagingInstance, field: "studentName")}</td>

                <td>${fieldValue(bean: stagingInstance, field: "studentNumber")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${stagingInstanceTotal}"/>
    </div>
</div>
</body>
</html>
