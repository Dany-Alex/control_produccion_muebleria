<%-- 
    Document   : nav-user-info
    Created on : 5 sep 2021, 11:53:47
    Author     : Artist
--%>

<ul  class="navbar-nav">
    <li  class="nav-item  dropstart"> 
        <a class="btn btn-outline-light border-0 rounded-pill " href="#" id="navbarDropdownMenuLinkFactory" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <h5 class="mb-0 text-uppercase nav-user-name"><i class="fas fa-user-cog"></i> ${userAttribute} </h5> 
        </a>
        <div class="dropdown-menu bg-danger border-4 border-light text-white text-center mx-1" aria-labelledby="navbarDropdownMenuLinkFactory">
            <div class="nav-user-info mb-2 mt-2">
                <i class="fas fa-user fs-1"></i>
            </div> 
            <div class="nav-user-info mb-2 ">
                <i class="fs-5 text-uppercase">${userAttribute} </i>
            </div> 
            <form action="loginController" method="POST">
                <button 
                    type="submit" 
                    name="action" 
                    value="logout"  
                    class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill text-truncate mx-2">
                    <i class="fas fa-sign-out-alt"></i> Cerrar Sesion</button>
            </form>
        </div>
    </li>
</ul>
