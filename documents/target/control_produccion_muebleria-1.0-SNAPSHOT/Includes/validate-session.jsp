        <%
             //session=request.getSession();
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
           // response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
          //  response.setHeader("Expires", "0"); // Proxies.

            if (session.getAttribute("userAttribute") == null ) {
             System.out.println("redireccion al index");
                response.sendRedirect("index.jsp");
            }
        %>