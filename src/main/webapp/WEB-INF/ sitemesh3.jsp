<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<sitemesh>
  <mapping path="/*" decorator="web.jsp"/>
  <mapping path="/user/*" decorator="user.jsp"/>
  <mapping path="/admin/*" decorator="admin.jsp"/>
  <mapping path="/v1/api/*" exclude="true"/>
</sitemesh>