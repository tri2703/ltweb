<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!-- BEGIN CONTENT -->
<div class="col-md-11 col-sm-11">
	<h1>Create an account</h1>
	<div class="content-form-page">
		<div class="row">
			<div class="col-md-7 col-sm-7">
				<form action="${pageContext.request.contextPath}/register"
					method="post" class="form-horizontal" role="form">
					<fieldset>
						<legend>Your personal details</legend>
						<div class="form-group">
							<label for="username" class="col-lg-4 control-label">User
								Name <span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="username"
									name="username" required>
							</div>
						</div>
						<div class="form-group">
							<label for="fullname" class="col-lg-4 control-label">Full
								Name <span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="fullname"
									name="fullname" required>
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-lg-4 control-label">Email <span
								class="require">*</span></label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="email" name="email"
									required>
							</div>
						</div>
						<div class="form-group">
							<label for="phone" class="col-lg-4 control-label">Phone</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="phone" name="phone">
							</div>
						</div>
					</fieldset>
					<fieldset>
						<legend>Your password</legend>
						<div class="form-group">
							<label for="password" class="col-lg-4 control-label">Password
								<span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="password" class="form-control" id="password"
									name="password" required>
							</div>
						</div>
						<div class="form-group">
							<label for="confirm-password" class="col-lg-4 control-label">Confirm
								password <span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="password" class="form-control"
									id="confirm-password" name="confirm-password" required>
							</div>
						</div>
					</fieldset>

					<div class="row">
						<div
							class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
							<button type="submit" class="btn btn-primary" value="Register">Create
								an account</button>
							<button type="button" class="btn btn-default">Cancel</button>
						</div>
					</div>
					<c:if test="${not empty alert}">
						<div class="alert">
							<p>${alert}</p>
						</div>
					</c:if>

					<a href="<c:url value='/login'/>">Back to Login</a>
				</form>
			</div>
			<div class="col-md-5 col-sm-5 pull-right">
				<div class="form-info">
					<h2>
						<em>Important</em> Information
					</h2>
					<p>Lorem ipsum dolor ut sit ame dolore adipiscing elit, sed sit
						nonumy nibh sed euismod ut laoreet dolore magna aliquarm erat sit
						volutpat. Nostrud exerci tation ullamcorper suscipit lobortis nisl
						aliquip commodo quat.</p>

					<p>Duis autem vel eum iriure at dolor vulputate velit esse vel
						molestie at dolore.</p>

					<button type="button" class="btn btn-default">More details</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END CONTENT -->





























<!--<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
    <h1>Register</h1>
    <form action="<c:url value='/register'/>" method="post">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div>
            <label for="fullname">Full Name:</label>
            <input type="text" id="fullname" name="fullname" required>
        </div>
        <div>
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" required>
        </div>
        <div>
            <input type="submit" value="Register">
        </div>
    </form>

    <c:if test="${not empty alert}">
        <div class="alert">
            <p>${alert}</p>
        </div>
    </c:if>

    <a href="<c:url value='/login'/>">Back to Login</a>
</body>
</html>
-->





