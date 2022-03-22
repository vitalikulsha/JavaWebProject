<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${order.approved}">
        <p style="color: green"><b>ОДОБРЕН</b></p>
        <c:if test="${order.approved}">
            <c:if test="${order.reserveStatus ne 'REFUND'}">
                <form action="${pageContext.request.contextPath}/reader/reader-orders" method="post">
                    <input type="hidden" name="orderId" value="${order.id}">
                    <input type="hidden" name="action" value="refund">
                    <input type="submit" value="Вернуть книгу">
                </form>
            </c:if>
        </c:if>
    </c:when>
    <c:otherwise>
        <p style="color:red"><b>НЕОДОБРЕН</b></p>
        <form action="${pageContext.request.contextPath}/reader/reader-orders" method="post">
            <input type="hidden" name="orderId" value="${order.id}">
            <input type="hidden" name="action" value="cancel">
            <input type="submit" value="Отменить заказ">
        </form>
    </c:otherwise>
</c:choose>