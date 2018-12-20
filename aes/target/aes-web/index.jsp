<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>测试页面</title>
</head>
<body>
	<div>
		<div>
			<center>
				<p>
					<font style="color: #005CBF;" size="10">测试页面</font>
				</p>

				<table align="center" cellpadding="5px" cellspacing="5px">
					<tr>
						<td><a href="${pageContext.request.contextPath }/testQuery">查询测试</a></td>
						<td><a href="${pageContext.request.contextPath }/testInsert">插入测试</a></td>
					</tr>
				</table>
			</center>
		</div>
	</div>
</body>
</html>
