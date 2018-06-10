<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/rating" method="get">
        <label>
            <select name="style">
                <c:forEach var="style" items="${styles}">
                    <option value="${style}">${style}</option>
                </c:forEach>
            </select>
        </label><br>
        <label>
            <select name="ageCategory">
                <c:forEach var="ageCategory" items="${ageCategories}">
                    <option value="${ageCategory}">${ageCategory}</option>
                </c:forEach>
            </select>
        </label><br>
        <label>
            <select name="league">
                <c:forEach var="league" items="${leagues}">
                    <option value="${league}">${league}</option>
                </c:forEach>
            </select>
        </label><br>
        Page : <input type="text" name="page" value="" required><br>
        Limit : <input type="text" name="limit" value="" required><br>
        <input type="submit" value="Show results">
    </form>
</div>

<div>
    <c:forEach items="${requestScope.ratings}" var="rating">
        ${rating.dancer.name} ${rating.dancer.secondName} ${rating.dancer.ageCategory} ${rating.dancer.league}
        ${rating.style} ${rating.value}<br>
    </c:forEach>
</div>
</body>
</html>
