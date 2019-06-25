<%
    String u = (String) request.getSession().getAttribute("nivel");
    if (u != null) {
%>
    <jsp:forward page="home"/> 
<% } %>