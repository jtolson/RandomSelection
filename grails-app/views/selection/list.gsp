<%@ page import="com.tci.utilities.Selection" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'selection.label', default: 'Selection')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-selection" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-selection" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="selectionDate"
                              title="${message(code: 'selection.selectionDate.label', default: 'Selection Date')}"/>

            <g:sortableColumn property="selectionTypeCode"
                              title="${message(code: 'selection.selectionTypeCode.label', default: 'Selection Type Code')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${selectionInstanceList}" status="i" var="selectionInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${selectionInstance.id}">${fieldValue(bean: selectionInstance, field: "selectionDate")}</g:link></td>

                <td>${fieldValue(bean: selectionInstance, field: "selectionTypeCode")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${selectionInstanceTotal}"/>
    </div>
</div>
</body>
</html>
