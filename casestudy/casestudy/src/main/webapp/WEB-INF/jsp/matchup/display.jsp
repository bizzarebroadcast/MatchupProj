<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<jsp:include page="../include/header.jsp"/>
<head>
    <!-- Include necessary CSS styles for grid layout -->
</head>
<body>

    <!-- Filter form with dropdown menus -->
 <section class="pt-5 pb-5">
     <div class="container-fluid">
         <div class="row">
             <div class="col-12">
                 <h2>Display Matchups</h2>

                 <!-- Filter form with dropdown menus -->
                 <form method="get" action="/matchup/display" class="row">

                     <div class="mt-3 col-md-6">
                         <label for="filterChar1" class="form-label">Filter by Character 1:</label>
                         <select id="filterChar1" name="filterChar1" class="form-control">
                             <option value="">All Characters</option>
                             <c:forEach var="character" items="${characters}">
                                 <option value="${character.name}">${character.name}</option>
                             </c:forEach>
                         </select>
                     </div>

                     <div class="mt-3 col-md-6">
                         <label for="filterChar2" class="form-label">Filter by Character 2:</label>
                         <select id="filterChar2" name="filterChar2" class="form-control">
                             <option value="">All Characters</option>
                             <c:forEach var="character" items="${characters}">
                                 <option value="${character.name}">${character.name}</option>
                             </c:forEach>
                         </select>
                     </div>

                     <div class="mt-3 col-md-12">
                         <input type="submit" class="btn btn-primary" value="Filter"/>
                     </div>
                 </form>
             </div>
         </div>
     </div>
 </section>
    <!-- Display matchups in a grid -->
     <div class="matchup-grid">
            <c:forEach var="matchup" items="${matchups}">
                    <div class="matchup-item">
                               <!-- Open the modal when clicking on the matchup item -->
                                           <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#commentModal${matchup.id}">
                                                       View Comments
                                                    </button>
                                                    <br>
                                                  <img src="${matchup.char1.imageUrl}" alt="${matchup.char1.name}" width="100"/>
                                                  <img src="${matchup.char2.imageUrl}" alt="${matchup.char2.name}" width="100"/>
                                                                <p>${matchup.char1.name} vs ${matchup.char2.name}</p>
                                                                <p>Uploaded by</p>
                                                                <p>${matchup.user.email}</p>
                                       </div>

                    <!-- Modal for comments -->
                    <div class="modal fade" id="commentModal${matchup.id}" tabindex="-1" role="dialog" aria-labelledby="commentModalLabel${matchup.id}" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="commentModalLabel${matchup.id}">${matchup.description}</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>


                                <div class="modal-body">
                                <iframe width="560" height="315" src="${matchup.url}" frameborder="0"
                                 allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

                                    <!-- Display comments here -->
                                    <c:forEach var="comment" items="${matchup.comments}">
                                        <p>${comment.user.email}: ${comment.comment}</p>
                                    </c:forEach>
                                </div>
                                <div class="modal-footer">
                                    <!-- Add a form to submit new comments -->
                                    <form action="/matchup/addComment" method="get">
                                        <input type="hidden" name="matchup" value="${matchup.description}">
                                        <textarea class="form-control" name="commentText" placeholder="Add your comment..." required></textarea>
                                        <button type="submit" class="btn btn-primary">Add Comment</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
        </div>
</body>
<jsp:include page="../include/footer.jsp"/>
</html>