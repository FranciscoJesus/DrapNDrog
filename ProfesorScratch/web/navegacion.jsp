<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="ListaProblemasServlet">Problemas</a></li>
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%= p.nombre + " " + p.apellido%><span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="LogoutServlet">Cerrar sesión</a></li>
                        </ul>
                    </li>
                </ul>
            </ul>
        </div>
    </div>
</nav>
