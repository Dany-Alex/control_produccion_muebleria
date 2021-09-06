<%-- 
    Document   : navbar-factory
    Created on : 5 sep 2021, 12:16:19
    Author     : Artist
--%>

<nav class="navbar navbar-expand-lg navbar-dark bg-transparent">
            <div class="container-fluid">
                <button class="navbar-toggler mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <h4 class="text-light text-center text-truncate"><i class="fas fa-home"></i> MI MUEBLERIA</a></h4>
                

                <div class="collapse navbar-collapse" id="navbar">
                    <ul class="navbar-nav me-auto mx-3">
                        <li class="nav-item dropdown ">
                            <a class="btn btn-outline-light border-0 text-uppercase rounded-pill dropdown-toggle mx-2" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-couch"></i> Mueble</a>
                            <div class="dropdown-menu bg-transparent border-4 border-light text-white text-center text-truncate">
                                <div class="btn-group-vertical mx-2">
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill" 
                                       href="factoryPrincipalController?menu-factory=assemble-furniture" target="frameMenu" >
                                       <i class="fas fa-hammer"></i> Ensamblar
                                    </a>
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill" 
                                       href="factoryPrincipalController?menu-factory=register-furniture" target="frameMenu">
                                       <i class="fas fa-check"></i> Registrar
                                    </a>

                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill" 
                                       href="factoryPrincipalController?menu-factory=info-furniture" target="frameMenu" >
                                      <i class="fas fa-info-circle"></i>  Información
                                    </a>

                                </div>



                            </div>
                        </li>


                        <li class="nav-item dropdown">
                            <a class="btn btn-outline-light border-0 text-uppercase rounded-pill dropdown-toggle mx-2" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-puzzle-piece"></i> Pieza</a>
                            <div class="dropdown-menu bg-transparent border-4 border-light text-white text-center text-truncate ">
                                <div class="btn-group-vertical mx-2">
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill" 
                                       href="factoryPrincipalController?menu-factory=admin-piece" target="frameMenu" >
                                      <i class="fas fa-cog"></i> Administrar
                                    </a>
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill" 
                                       href="factoryPrincipalController?menu-factory=info-piece" target="frameMenu">
                                      <i class="fas fa-info-circle"></i> Información
                                    </a>

                                </div>


                            </div>
                        </li>
                    </ul>
                    <jsp:include page="/Includes/nav-user-info.jsp"/>
                </div>
            </div>
        </nav>
