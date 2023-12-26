<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="tranlong5252.foodsupplychain.constants.StatusLevel" %>
<html>
<head>
    <title>Supreme Logics Co</title>
    <link rel="stylesheet" href="css/table.css">
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

    <div class="row">
        <div class="col">
            <h2>Products</h2>
            <table class="table table-primary">
                <thead>
                <tr>
                    <th rowspan="2">ID</th>
                    <th rowspan="2">Name</th>
                    <th rowspan="2">Industrial and Agricultural status</th>
                    <th colspan="3">Population</th>
                    <th colspan="3">Nature status</th>
                    <th rowspan="2">Action</th>
                </tr>
                <tr>
                    <th>distribution</th>
                    <th>migration</th>
                    <th>urbanization</th>
                    <th>agricultureLand</th>
                    <th>forestLand</th>
                    <th>disaster</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${requestScope.regions != null}">
                    <c:forEach items="${requestScope.regions}" var="region">
                        <tr>
                            <td>${region.id}</td>
                            <td>
                                <label>
                                    <input type="text" name="regionName" value="${region.name}"
                                           form="editRegion_${region.id}" required>
                                </label>
                            </td>

                            <td>
                                <table class="status">
                                    <thead class="status">
                                    <tr class="status">
                                        <th>Name</th>
                                        <th>Level</th>
                                        <th>Value</th>
                                        <th>Potential</th>
                                        <th>Development</th>
                                        <th>Action</th>
                                    </tr>
                                    <tbody class="status">
                                    <c:forEach items="${region.statuses}" var="status">
                                        <tr class="status">
                                            <td>
                                                <label>
                                                    <input type="text" name="statusName_${status.id}" value="${status.name}"
                                                           form="editRegion_${region.id}" required>
                                                </label>
                                            </td>
                                            <td>
                                                <label>
                                                    <select name="statusLevel_${status.id}" form="editRegion_${region.id}" required>
                                                        <c:forEach items="${StatusLevel.values()}" var="level">
                                                            <option value="${level.value}" ${level.value == status.level.value ? 'selected' : ''}>${level}</option>
                                                        </c:forEach>
                                                    </select>
                                                </label>
                                            </td>
                                            <td>
                                                <label>
                                                    <input type="number" name="statusValue_${status.id}" value="${status.value}"
                                                           form="editRegion_${region.id}" min="0" max="100" step=".01" required>
                                                </label>
                                            </td>
                                            <td>
                                                <label>
                                                    <input type="number" name="statusPotential_${status.id}"
                                                           value="${status.potential}" form="editRegion_${region.id}" min="0"
                                                           max="10" step="1" required>
                                                </label>
                                            </td>
                                            <td>
                                                <label>
                                                    <input type="number" name="statusDevelopment_${status.id}"
                                                           value="${status.development}" form="editRegion_${region.id}"
                                                           min="0" max="10" step="1" required>
                                                </label>
                                            </td>
                                            <td>
                                                <form action="Region" id="deleteRegionStatus">
                                                    <input type="hidden" name="statusId" value="${status.id}">
                                                    <input type="hidden" name="regionId" value="${region.id}">
                                                    <div class="input-group">
                                                        <button type="submit" class="btn btn-sm btn-secondary"
                                                                name="action"
                                                                value="deleteRegionStatus">Delete
                                                        </button>
                                                    </div>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr class="status">
                                        <td>
                                            <label>
                                                <input type="text" name="statusName" form="addRegionStatus_${region.id}"
                                                       placeholder="Type" required>
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <select name="statusLevel" form="addRegionStatus_${region.id}" required>
                                                    <c:forEach items="${StatusLevel.values()}" var="level">
                                                        <option value="${level.value}">${level}</option>
                                                    </c:forEach>
                                                </select>
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="number" name="statusValue"
                                                       form="addRegionStatus_${region.id}" min="0" max="100" step=".01" placeholder="Type" required>
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="number" name="statusPotential"
                                                       form="addRegionStatus_${region.id}" min="0" max="10" step="1"
                                                       placeholder="Type" required>
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="number" name="statusDevelopment"
                                                       form="addRegionStatus_${region.id}"
                                                       min="0" max="10" step="1" placeholder="Type" required>
                                            </label>
                                        </td>
                                        <td>
                                            <form action="Region" id="addRegionStatus_${region.id}">
                                                <input type="hidden" name="regionId" value="${region.id}">
                                                <div class="input-group">
                                                    <button type="submit"
                                                            class="btn btn-sm btn-secondary"
                                                            name="action"
                                                            value="addRegionStatus">Add
                                                    </button>
                                                </div>
                                            </form>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </td>

                            <td>
                                <label>
                                    <input type="text" name="regionPopDis" value="${region.population.distribution}"
                                           form="editRegion_${region.id}" required>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input type="text" name="regionPopMig" value="${region.population.migration}"
                                           form="editRegion_${region.id}" required>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input type="text" name="regionPopUrb" value="${region.population.urbanization}"
                                           form="editRegion_${region.id}" required>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input type="text" name="regionNatAgri"
                                           value="${region.natureStatus.agricultureLand}"
                                           form="editRegion_${region.id}" required>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input type="text" name="regionNatFor" value="${region.natureStatus.forestLand}"
                                           form="editRegion_${region.id}" required>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input type="text" name="regionNatDis" value="${region.natureStatus.disaster}"
                                           form="editRegion_${region.id}" required>
                                </label>
                            </td>
                            <td>
                                <form action="Region" id="editRegion_${region.id}">
                                    <input type="hidden" name="regionId" value="${region.id}">
                                    <div class="input-group">
                                        <button type="submit" class="btn btn-sm btn-secondary" name="action"
                                                value="editRegion">Edit
                                        </button>
                                    </div>
                                </form>
                                <form action="Region">
                                    <input type="hidden" name="regionId" value="${region.id}">
                                    <div class="input-group">
                                        <button type="submit" class="btn btn-sm btn-secondary" name="action"
                                                value="deleteRegion">Delete
                                        </button>
                                    </div>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <tr>
                    <td></td>
                    <td>
                        <label>
                            <input type="text" name="regionName" form="addRegion" placeholder="Enter name" required>
                        </label>
                    </td>
                    <td></td>
                    <td>
                        <label>
                            <input type="number" name="regionPopDis" form="addRegion" placeholder="Enter number" required>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input type="number" name="regionPopMig" form="addRegion" placeholder="Enter number" required>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input type="number" name="regionPopUrb" form="addRegion" placeholder="Enter number" required>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input type="number" name="regionNatAgri" form="addRegion" placeholder="Enter number" required>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input type="number" name="regionNatFor" form="addRegion" placeholder="Enter number" required>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input type="text" name="regionNatDis" form="addRegion" placeholder="Type" required>
                        </label>
                    </td>
                    <td>
                        <form action="Region" id="addRegion">
                            <div class="input-group">
                                <button type="submit" class="btn btn-sm btn-secondary" name="action"
                                        value="addRegion">Add
                                </button>
                            </div>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>