<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="tranlong5252.foodsupplychain.constants.StatusLevel" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Supreme Logics Co</title>
    <%@include file="../components/providers.jsp" %>
</head>
<body>
<%@include file="../components/header.jsp" %>
<body>
<div class="container">
    <%@include file="../components/message.jsp" %>
    <%--    view company's region, there is 1 region, show as card--%>
    <div class="row">
        <div class="col-md">
            <div class="card">
                <div class="card-header bg-dark text-white">
                    ${requestScope.region.name}
                </div>
                <div class="card-body">
                    <%--                        private StatusList statusList;
                        private Population population;
                        private NatureStatus natureStatus;
                            private double distribution;
                        private int migration;
                        private int urbanization;

                            private double agricultureLand; //% decimal
                        private double forestLand;  //% decimal
                        private String disaster; //can be many value like earthquake, typhoon, tsunami, etc.
                    --%>
                    <div class="card-group">
                        <div class="card">
                            <div class="card-header bg-dark text-white">
                                Population
                            </div>
                            <div class="card-body">
                                <p class="card-text">Population: ${requestScope.region.population.distribution}</p>
                                <p class="card-text">Population: ${requestScope.region.population.migration}</p>
                                <p class="card-text">Population: ${requestScope.region.population.urbanization}</p>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-header bg-dark text-white">
                                Nature status
                            </div>
                            <div class="card-body">
                                <p class="card-text">Nature
                                    Status: ${requestScope.region.natureStatus.agricultureLand}</p>
                                <p class="card-text">Nature Status: ${requestScope.region.natureStatus.forestLand}</p>
                                <p class="card-text">Nature Status: ${requestScope.region.natureStatus.disaster}</p>
                            </div>
                        </div>
                    </div>
                    <c:if test="${not empty requestScope.region.statuses}">
                        <div class="card-footer">
                            <form action="Region" id="editRegion_${requestScope.region.id}">
                                <input type="hidden" name="regionId" value="${requestScope.region.id}">
                            </form>
                            <button type="submit" class="btn btn-sm btn-primary" name="action"
                                    form="editRegion_${requestScope.region.id}" value="editRegion">Edit
                            </button>
                        </div>
                        <h1>${requestScope.region.name}</h1>
                        <table class="table table-secondary table-striped table-hover table-sm table-bordered">
                            <thead class="status">
                            <tr class="status">
                                <th colspan="6">Industrial and Agricultural status</th>
                            <tr class="status">
                                <th>Name</th>
                                <th>Level</th>
                                <th>Value</th>
                                <th>Potential</th>
                                <th>Development</th>
                                <th>Action</th>
                            </tr>
                            <tbody class="status">
                            <c:forEach items="${requestScope.region.statuses}" var="status">
                                <tr class="status">
                                    <td>
                                        <label>
                                            <input class="form-control-plaintext" type="text"
                                                   name="statusName_${status.id}"
                                                   value="${status.name}"
                                                   form="editRegion_${requestScope.region.id}" required>
                                        </label>
                                    </td>
                                    <td>
                                        <label>
                                            <select class="form-control-plaintext" name="statusLevel_${status.id}"
                                                    form="editRegion_${requestScope.region.id}" required>
                                                <c:forEach items="${StatusLevel.values()}" var="level">
                                                    <option value="${level.value}" ${level.value == status.level.value ? 'selected' : ''}>${level}</option>
                                                </c:forEach>
                                            </select>
                                        </label>
                                    </td>
                                    <td>
                                        <label>
                                            <input class="form-control-plaintext" type="number"
                                                   name="statusValue_${status.id}"
                                                   value="${status.value}"
                                                   form="editRegion_${requestScope.region.id}" min="0" max="100"
                                                   step=".01"
                                                   required>
                                        </label>
                                    </td>
                                    <td>
                                        <label>
                                            <input class="form-control-plaintext" type="number"
                                                   name="statusPotential_${status.id}"
                                                   value="${status.potential}"
                                                   form="editRegion_${requestScope.region.id}"
                                                   min="0"
                                                   max="10" step="1" required>
                                        </label>
                                    </td>
                                    <td>
                                        <label>
                                            <input class="form-control-plaintext" type="number"
                                                   name="statusDevelopment_${status.id}"
                                                   value="${status.development}"
                                                   form="editRegion_${requestScope.region.id}"
                                                   min="0" max="10" step="1" required>
                                        </label>
                                    </td>
                                    <td>
                                        <form action="Region" id="deleteRegionStatus">
                                            <input type="hidden" name="statusId" value="${status.id}">
                                            <input type="hidden" name="regionId" value="${requestScope.region.id}">
                                        </form>
                                        <button type="submit" class="btn btn-sm btn-danger"
                                                name="action"
                                                form="deleteRegionStatus"
                                                value="deleteRegionStatus">Delete
                                        </button>

                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="../components/footer.jsp" %>
</html>
