<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <div class="col-sm-6">
        <ul class="list-inline right-topbar pull-right">
            <c:choose>
                <c:when test="${sessionScope.account == null}">
                    <li>
                        <a href="${pageContext.request.contextPath}/login">Đăng nhập</a> | 
                        <a href="${pageContext.request.contextPath}/register">Đăng ký</a>
                    </li> <!-- Đóng thẻ <li> này đúng cách -->
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="${pageContext.request.contextPath}/member/myaccount">${sessionScope.account.fullName}</a> | 
                        <a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a>
                    </li> <!-- Đóng thẻ <li> này đúng cách -->
                </c:otherwise>
            </c:choose>
            <li>
                <i class="search fa fa-search search-button"></i>
            </li>
        </ul>
    </div>
</body>
</html>
