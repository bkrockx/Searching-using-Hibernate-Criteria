<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Edit Existing Record</h1>

<c:url var="saveUrl" value="/record/edit?id=${bookAttribute.bookId}" />
<form:form modelAttribute="bookAttribute"  method="POST" action="${saveUrl}">
	<table>
		<tr>
			<td><form:label path="bookId">BookId:</form:label></td>
			<td><form:input path="bookId" disabled="true"/></td>
		</tr>
	
		<tr>
			<td><form:label path="bookName">BookName:</form:label></td>
			<td><form:input path="bookName"/></td>
		</tr>
	</table>
	<input type="submit" value="Save" />
	
	<table width: 100%; text-align:center">
	<tr>

	<th>ChapterId</th>
		<th>ChapterName</th>
		<th>Add</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<tbody>
			<tr>
	
		<c:forEach items="${bookAttribute.chapter}" var="chapter">
				<c:url var="addCUrl" value="/chapter/add?id=${bookAttribute.bookId}" />
				<c:url var="editCUrl" value="/chapter/edit?bid=${bookAttribute.bookId}&cid=${chapter.chapterId}" />
				<c:url var="deleteCUrl" value="/chapter/delete?id=${chapter.chapterId}" />
				<td><c:out value="${chapter.chapterId}" /></td>
				<td><c:out value="${chapter.chapterName}"/></td>
				<td><a href="${addCUrl}">AddChapter</a></td>
				<td><a href="${editCUrl}">EditChapter</a></td>
				<td><a href="${deleteCUrl}">DeleteChapter</a></td>

			</tr>
			</c:forEach>
	
		
		<c:if test="${empty bookAttribute.chapter}">
			<tr>
				<c:url var="addCcUrl" value="/chapter/add?id=${bookAttribute.bookId}" />
				<td><a href="${addCUrl}">AddChapter</a></td>
			</tr>
		</c:if>
		
	
	</tbody>
</table>
</form:form>

</body>
</html>