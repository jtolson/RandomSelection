<%@ page import="com.tci.utilities.Selection" %>



<div class="fieldcontain ${hasErrors(bean: selectionInstance, field: 'selectionDate', 'error')} required">
    <label for="selectionDate">
        <g:message code="selection.selectionDate.label" default="Selection Date"/>
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="selectionDate" precision="day" value="${selectionInstance?.selectionDate}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: selectionInstance, field: 'selectionTypeCode', 'error')} ">
    <label for="selectionTypeCode">
        <g:message code="selection.selectionTypeCode.label" default="Selection Type Code"/>

    </label>
    <g:textField name="selectionTypeCode" value="${selectionInstance?.selectionTypeCode}"/>
</div>

