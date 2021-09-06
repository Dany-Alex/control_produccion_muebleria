<%-- 
    Document   : saleMenu
    Created on : 13 ago 2021, 9:25:09
    Author     : Artist
--%>
<%
    //session=request.getSession();
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setHeader("Expires", "0"); // Proxies.

    if (session.getAttribute("userAttribute") == null && session.getAttribute("typeUser")!="1") {
        System.out.println("redireccion al index");
        response.sendRedirect("index.jsp");
    } else {
        System.out.println("no redireccion al index - menu");
        System.out.println("S --" + session.getAttribute("userAttribute") +" ----- " +session.getAttribute("typeUser"));
        System.out.println("R --" + request.getParameter("userAttribute")+" ----- " +request.getParameter("typeUser"));;

    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <jsp:include page="/Includes/header-sale.jsp"/>    
<div class="img-bg-wrapper" >
    <div class="overlay-img-bg-wrapper " >
        
   
        
        <iframe name="frameMenuSale" class="sizeAuto" >
            <div class=" text-center text-light d-flex justify-content-center">

            </div> 
        </iframe>
        
        
    </div>
</div>

<jsp:include page="/Includes/footer-factory.jsp"/>