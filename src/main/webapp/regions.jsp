<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="tranlong5252.foodsupplychain.constants.StatusLevel" %>
<html lang="en">
<head>
    <title>Supreme Logics Co</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    <%--    <link rel="stylesheet" href="css/table.css">--%>
</head>
<body>
<div class="header">
    <h1>Region Management</h1>
    <a href="${pageContext.request.contextPath}/">Home</a>
    <a href="Logout">Logout</a>
</div>

<div class="container">
    <c:if test="${requestScope.error != null}">
        <p>${requestScope.error}</p>
    </c:if>

    <h1>Regions</h1>
    <div class="find-object">
        <form action="Regions" id="searchRegions">
            <div class="input-group">
                <label>
                    <input type="text" name="regionName" placeholder="Find by name">
                </label>
                <button type="submit" class="btn btn-sm btn-secondary" name="action" value="searchRegions">
                    Search
                </button>
            </div>
        </form>
    </div>
    <div class="row">
        <div class="col">
            <div class="table-responsive">
                <table class="table table-sm table-primary table-striped table-hover caption-top">
                    <caption>List of users</caption>
                    <thead class="table-light">
                <tr>
                    <th rowspan="2" scope="col">ID</th>
                    <th rowspan="2" scope="col">Name</th>
                    <%--                    <th rowspan="2">Industrial and Agricultural status</th>--%>
                    <th colspan="3" scope="col">Population</th>
                    <th colspan="3" scope="col">Nature status</th>
                    <th rowspan="2" scope="col">Action</th>
                </tr>
                <tr>
                    <th scope="col">distribution</th>
                    <th scope="col">migration</th>
                    <th scope="col">urbanization</th>
                    <th scope="col">agricultureLand</th>
                    <th scope="col">forestLand</th>
                    <th scope="col">disaster</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${requestScope.regions != null}">
                    <c:forEach items="${requestScope.regions}" var="region">
                        <tr onclick="selectRegion(${region.id})">
                            <td>
                                <label class="col-form-label">
                                        ${region.id}
                                </label>
                                <form action="Regions" id="selectRegion_${region.id}">
                                    <input type="hidden" name="action" value="selectRegion">
                                    <input type="hidden" name="regionId" value="${region.id}">
                                </form>
                            </td>
                            <td class="">
                                <label>
                                    <input class="form-control-plaintext" type="text" name="regionName"
                                           value="${region.name}"
                                           form="editRegion_${region.id}" required>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="number" name="regionPopDis"
                                           value="${region.population.distribution}"
                                           step="any"
                                           form="editRegion_${region.id}" required>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="number" name="regionPopMig"
                                           value="${region.population.migration}"
                                           step="1"
                                           min="0" max="10"
                                           form="editRegion_${region.id}" required>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="number" name="regionPopUrb"
                                           value="${region.population.urbanization}"
                                           step="1"
                                           min="0" max="10"
                                           form="editRegion_${region.id}" required>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="number" name="regionNatAgri"
                                           step="any"
                                           min="0" max="100"
                                           value="${region.natureStatus.agricultureLand}"
                                           form="editRegion_${region.id}" required>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="number" name="regionNatFor"
                                           value="${region.natureStatus.forestLand}"
                                           step="any"
                                           min="0" max="100"
                                           form="editRegion_${region.id}" required>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="text" name="regionNatDis"
                                           value="${region.natureStatus.disaster}"
                                           form="editRegion_${region.id}" required>
                                </label>
                            </td>
                            <td>
                                <div class="btn-group btn-group-sm" role="group">
                                    <button type="submit" class="btn btn-secondary" name="action"
                                            form="editRegion_${region.id}"
                                            value="editRegion">Edit
                                    </button>
                                    <button type="submit" class="btn btn-danger" name="action"
                                            form="deleteRegion_${region.id}"
                                            value="deleteRegion">Delete
                                    </button>
                                </div>
                            </td>
                            <form action="Region" id="editRegion_${region.id}">
                                <div class="input-group">
                                    <input type="hidden" name="regionId" value="${region.id}">
                                </div>
                            </form>
                            <form action="Region" id="deleteRegion_${region.id}">
                                <div class="input-group">
                                    <input type="hidden" name="regionId" value="${region.id}">
                                </div>
                            </form>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
                    <tfoot class="table-light">
                    <tr>
                        <td></td>
                        <td>
                            <label>
                                <input class="form-control-plaintext" type="text" name="regionName" form="addRegion"
                                       placeholder="Enter name" required>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input class="form-control-plaintext" type="number" name="regionPopDis" form="addRegion"
                                       placeholder="Enter number" required>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input class="form-control-plaintext" type="number" name="regionPopMig" form="addRegion"
                                       placeholder="Enter number" required>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input class="form-control-plaintext" type="number" name="regionPopUrb" form="addRegion"
                                       placeholder="Enter number" required>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input class="form-control-plaintext" type="number" name="regionNatAgri"
                                       form="addRegion"
                                       placeholder="Enter number" required>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input class="form-control-plaintext" type="number" name="regionNatFor" form="addRegion"
                                       placeholder="Enter number" required>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input class="form-control-plaintext" type="text" name="regionNatDis" form="addRegion"
                                       placeholder="Type" required>
                            </label>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-sm btn-success" name="action" form="addRegion"
                                    value="addRegion">Add
                            </button>
                            <form action="Region" id="addRegion">

                            </form>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <c:if test="${sessionScope.region != null}">

            <div class="col">
                <h1>${sessionScope.region.name}</h1>
                <table class="table table-secondary table-striped table-hover table-sm">
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
                    <c:forEach items="${sessionScope.region.statuses}" var="status">
                        <tr class="status">
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="text" name="statusName_${status.id}"
                                           value="${status.name}"
                                           form="editRegion_${sessionScope.region.id}" required>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <select class="form-control-plaintext" name="statusLevel_${status.id}"
                                            form="editRegion_${sessionScope.region.id}" required>
                                        <c:forEach items="${StatusLevel.values()}" var="level">
                                            <option value="${level.value}" ${level.value == status.level.value ? 'selected' : ''}>${level}</option>
                                        </c:forEach>
                                    </select>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="number" name="statusValue_${status.id}"
                                           value="${status.value}"
                                           form="editRegion_${sessionScope.region.id}" min="0" max="100" step=".01"
                                           required>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="number"
                                           name="statusPotential_${status.id}"
                                           value="${status.potential}" form="editRegion_${sessionScope.region.id}"
                                           min="0"
                                           max="10" step="1" required>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input class="form-control-plaintext" type="number"
                                           name="statusDevelopment_${status.id}"
                                           value="${status.development}" form="editRegion_${sessionScope.region.id}"
                                           min="0" max="10" step="1" required>
                                </label>
                            </td>
                            <td>
                                <form action="Region" id="deleteRegionStatus">
                                    <input type="hidden" name="statusId" value="${status.id}">
                                    <input type="hidden" name="regionId" value="${sessionScope.region.id}">
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
                    <tfoot class="status">
                    <tr class="status">
                        <td>
                            <label>
                                <input class="form-control-plaintext" type="text" name="statusName"
                                       form="addRegionStatus_${sessionScope.region.id}"
                                       placeholder="Type" required>
                            </label>
                        </td>
                        <td>
                            <label>
                                <select class="form-control-plaintext" name="statusLevel"
                                        form="addRegionStatus_${sessionScope.region.id}" required>
                                    <c:forEach items="${StatusLevel.values()}" var="level">
                                        <option value="${level.value}">${level}</option>
                                    </c:forEach>
                                </select>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input class="form-control-plaintext" type="number" name="statusValue"
                                       form="addRegionStatus_${sessionScope.region.id}" min="0" max="100" step=".01"
                                       placeholder="Type"
                                       required>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input class="form-control-plaintext" type="number" name="statusPotential"
                                       form="addRegionStatus_${sessionScope.region.id}" min="0" max="10" step="1"
                                       placeholder="Type" required>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input class="form-control-plaintext" type="number" name="statusDevelopment"
                                       form="addRegionStatus_${sessionScope.region.id}"
                                       min="0" max="10" step="1" placeholder="Type" required>
                            </label>
                        </td>
                        <td>
                            <form action="Region" id="addRegionStatus_${sessionScope.region.id}">
                                <input type="hidden" name="regionId" value="${sessionScope.region.id}">
                            </form>
                            <button type="submit"
                                    class="btn btn-sm btn-success"
                                    name="action"
                                    form="addRegionStatus_${sessionScope.region.id}"
                                    value="addRegionStatus">Add
                            </button>
                        </td>
                    </tr>
                    </tfoot>
                    <caption>
                        <button type="submit" class="btn btn-sm btn-secondary"
                                name="action"
                                form="closeRegionStatus"
                                value="closeRegionStatus">Close
                        </button>
                        <form action="Regions" id="closeRegionStatus">
                            <input type="hidden" name="regionId" value="${sessionScope.region.id}">
                        </form>
                    </caption>
                </table>
            </div>
        </c:if>
    </div>
</div>
</body>
<script type="text/javascript">
    function selectRegion(id) {
        document.getElementById("selectRegion_" + id).submit();
    }
</script>
</html>