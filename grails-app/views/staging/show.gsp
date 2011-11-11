<%@ page import="com.tci.utilities.Staging" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'staging.label', default: 'Staging')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-staging" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-staging" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list staging">

        <g:if test="${stagingInstance?.customerDataFileName}">
            <li class="fieldcontain">
                <span id="customerDataFileName-label" class="property-label"><g:message
                        code="staging.customerDataFileName.label" default="Customer Data File Name"/></span>

                <span class="property-value" aria-labelledby="customerDataFileName-label"><g:fieldValue
                        bean="${stagingInstance}" field="customerDataFileName"/></span>

            </li>
        </g:if>

        <g:if test="${stagingInstance?.customerId}">
            <li class="fieldcontain">
                <span id="customerId-label" class="property-label"><g:message code="staging.customerId.label"
                                                                              default="Customer Id"/></span>

                <span class="property-value" aria-labelledby="customerId-label"><g:fieldValue bean="${stagingInstance}"
                                                                                              field="customerId"/></span>

            </li>
        </g:if>

        <g:if test="${stagingInstance?.studentName}">
            <li class="fieldcontain">
                <span id="studentName-label" class="property-label"><g:message code="staging.studentName.label"
                                                                               default="Student Name"/></span>

                <span class="property-value" aria-labelledby="studentName-label"><g:fieldValue bean="${stagingInstance}"
                                                                                               field="studentName"/></span>

            </li>
        </g:if>

        <g:if test="${stagingInstance?.studentNumber}">
            <li class="fieldcontain">
                <span id="studentNumber-label" class="property-label"><g:message code="staging.studentNumber.label"
                                                                                 default="Student Number"/></span>

                <span class="property-value" aria-labelledby="studentNumber-label"><g:fieldValue
                        bean="${stagingInstance}" field="studentNumber"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${stagingInstance?.id}"/>
            <g:link class="edit" action="edit" id="${stagingInstance?.id}"><g:message code="default.button.edit.label"
                                                                                      default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
