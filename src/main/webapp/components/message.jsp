<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${requestScope.error != null}">
    <p class="text-bg-warning">${requestScope.error}</p>
</c:if>
<c:if test="${requestScope.message != null}">
    <p class="text-bg-primary">${requestScope.message}</p>
</c:if>