        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setHeader("Expires", "0"); // Proxies.

        %>

<div class="container d-flex justify-content-center ">
    <div class="card mx-5 my-5 bg-dark ">
        <div class="card-body bg-danger">
            <h2 class="card-heading display-5 text-uppercase text-light">Log In</h2>
            <form action="loginController" method="POST">
                <div class="row  mx-4 my-4 mb-5 ">

                    <div class="col d-grid gap-2">
                        <div class="form-group">
                            <label for="inputUserTypeLogin" class="sr-only mt-2 text-light">Tipo de Usuario</label>
                            <select class="form-control form-select rounded-pill border-1 border-dark shadow-sm px-4" name="inputUserTypeLogin">
                                <option>Ensamblador</option>
                                <option>Vendedor</option>
                                <option>Financiero</option>

                            </select>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="inputUserLogin" class="sr-only mt-2 text-light">Usuario</label>
                            <input 
                                name="inputUserLogin"
                                type="text" 
                                placeholder="Usuario" 
                                required="" 
                                autofocus="" 
                                class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                                > 
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="inputPasswordLogin" class="sr-only mt-2 text-light">Password</label>
                            <input 

                                name="inputPasswordLogin"
                                type="password" 
                                placeholder="Password" 
                                required=""
                                class="form-control rounded-pill border-1 border-dark  shadow-sm px-4 text-danger"
                                > 
                        </div>
                    </div>


                    <div class="col" style=" margin-top: 2rem">
                        <div class="form-group d-grid gap-2 ">
                            <button type="submit" name="action" value="login" class="btn btn-light btn-block text-uppercase mb-2 rounded-pill shadow-sm ">Entrar</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

