<%--
  Created by IntelliJ IDEA.
  User: Ressigui
  Date: 23/12/2021
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #4a7922">

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Personnes</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${personne != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${personne == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${personne != null}">
                                Edit personne
                            </c:if>
                            <c:if test="${personne == null}">
                                Add New personne
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${personne != null}">
                        <input type="hidden" name="id" value="<c:out value='${personne.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>User Name</label> <input type="text"
                                                        value="<c:out value='${personne.name}' />" class="form-control"
                                                        name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Prenom</label> <input type="text"
                                                         value="<c:out value='${personne.prenom}' />" class="form-control"
                                                         name="Prenom">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User tel</label> <input type="text"
                                                           value="<c:out value='${personne.tel}' />" class="form-control"
                                                           name="tel">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
