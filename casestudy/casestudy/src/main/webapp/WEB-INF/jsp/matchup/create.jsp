<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="../include/header.jsp"/>
<body>

<section class="pt-5 pb-5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6">
                <h2>Create Matchup</h2>
                <form method="get" action="/matchup/createSubmit" class="row">

                    <div class="mt-3 col-md-6">
                        <label for="char1name" class="form-label">Character 1:</label>
                        <select id="char1name" name="char1name" class="form-control" required>
                            <c:forEach var="character" items="${characters}">
                                <option value="${character.name}">${character.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mt-3 col-md-6">
                        <label for="char2name" class="form-label">Character 2:</label>
                        <select id="char2name" name="char2name" class="form-control" required>
                            <c:forEach var="character" items="${characters}">
                                <option value="${character.name}">${character.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mt-3 col-md-12">
                        <label for="url" class="form-label">Video URL:</label>
                        <input type="text" id="url" name="url" class="form-control" required/>
                    </div>

                    <div class="mt-3 col-md-12">
                        <label for="description" class="form-label">Description:</label>
                        <textarea id="description" name="description" class="form-control" required></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary mt-4 col-md-12">Create Matchup</button>
                </form>
            </div>
        </div>
    </div>
</section>

</body>
<jsp:include page="../include/footer.jsp"/>
</html>