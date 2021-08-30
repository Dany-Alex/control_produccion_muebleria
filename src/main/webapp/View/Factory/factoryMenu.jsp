<%
    //session=request.getSession();
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setHeader("Expires", "0"); // Proxies.

    if (session.getAttribute("userAttribute") == null) {
        System.out.println("redireccion al index");
        response.sendRedirect("index.jsp");
    } else {
        System.out.println("no redireccion al index - menu");
        System.out.println("S --" + session.getAttribute("userAttribute"));
        System.out.println("R --" + request.getParameter("userAttribute"));;

    }
%>

<jsp:include page="/Includes/header-factory.jsp"/>

<div class="img-bg-wrapper">

    <div class="overlay-img-bg-wrapper " >
        <iframe name="frameMenu" class="sizeAuto" >
            <div class=" text-center text-light d-flex justify-content-center">
                <h1 class="card-heading display-2 text-uppercase mt-5"><b>Area de fábrica</b></h1>  
            </div> 
        </iframe>
    </div>
</div>

<jsp:include page="/Includes/footer-factory.jsp"/>
