<%@ taglib prefix="s" uri="/struts-tags" %>
<s:form action="/img-upload" method="post" enctype="multipart/form-data">
    <s:file name="upload" label="File"/>
    <s:submit/>
</s:form>