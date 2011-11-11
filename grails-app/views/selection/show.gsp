<%@ page import="com.tci.utilities.Selection" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'selection.label', default: 'Selection')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-selection" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-selection" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list selection">

        <g:if test="${selectionInstance?.selectionDate}">
            <li class="fieldcontain">
                <span id="selectionDate-label" class="property-label"><g:message code="selection.selectionDate.label"
                                                                                 default="Selection Date"/></span>

                <span class="property-value" aria-labelledby="selectionDate-label"><g:formatDate
                        date="${selectionInstance?.selectionDate}"/></span>

            </li>
        </g:if>

        <g:if test="${selectionInstance?.selectionTypeCode}">
            <li class="fieldcontain">
                <span id="selectionTypeCode-label" class="property-label"><g:message
                        code="selection.selectionTypeCode.label" default="Selection Type Code"/></span>

                <span class="property-value" aria-labelledby="selectionTypeCode-label"><g:fieldValue
                        bean="${selectionInstance}" field="selectionTypeCode"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${selectionInstance?.id}"/>
            <g:link class="edit" action="edit" id="${selectionInstance?.id}"><g:message code="default.button.edit.label"
                                                                                        default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
