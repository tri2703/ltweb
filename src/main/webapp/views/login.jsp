<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<form action="login" method="post">

	<c:if test="${alert !=null}">
		<h3 class="alert alert-danger">${alert}</h3>
	</c:if>
	<div class="container">
		<label for="uname"><b>Username</b></label> <input type="text"
			placeholder="Enter Username" name="uname" required> <label
			for="psw"><b>Password</b></label> <input type="password"
			placeholder="Enter Password" name="psw" required>

		<button type="submit">Login</button>
		<label> <input type="checkbox" checked="checked"
			name="remember"> Remember me
		</label>
	</div>

	<div class="container" style="background-color: #f1f1f1">
		<button type="button" class="cancelbtn">Cancel</button>
		<span class="psw">Forgot <a href="#">password?</a></span>
		<button type="button" class="registerbtn" onclick="window.location.href='/ltweb/register';">Register</button>
	</div>
	
</form>