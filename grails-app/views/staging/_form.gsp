<%@ page import="com.tci.utilities.Staging" %>



<div class="fieldcontain ${hasErrors(bean: stagingInstance, field: 'customerDataFileName', 'error')} ">
    <label for="customerDataFileName">
        <g:message code="staging.customerDataFileName.label" default="Customer Data File Name"/>

    </label>
    <g:textField name="customerDataFileName" value="${stagingInstance?.customerDataFileName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stagingInstance, field: 'customerId', 'error')} required">
    <label for="customerId">
        <g:message code="staging.customerId.label" default="Customer Id"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field type="number" name="customerId" required=""
             value="${fieldValue(bean: stagingInstance, field: 'customerId')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stagingInstance, field: 'studentName', 'error')} ">
    <label for="studentName">
        <g:message code="staging.studentName.label" default="Student Name"/>

    </label>
    <g:textField name="studentName" value="${stagingInstance?.studentName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stagingInstance, field: 'studentNumber', 'error')} required">
    <label for="studentNumber">
        <g:message code="staging.studentNumber.label" default="Student Number"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field type="number" name="studentNumber" required=""
             value="${fieldValue(bean: stagingInstance, field: 'studentNumber')}"/>
</div>

