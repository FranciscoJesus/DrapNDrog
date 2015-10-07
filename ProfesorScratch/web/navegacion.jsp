<nav role="navigation" class="navbar navbar-default navbar-static-top">
    <div class="container">
        
        <div class="navbar-header">
            <a href="#" class="navbar-brand">Drag & Drop</a>
        </div>
        
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right nav-stacked">
                <li><a href="main.jsp">Nuevo Problema</a></li>
                <li><a href="ListaProblemasServlet">Problemas</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%= p.nombre + " " + p.apellido %><span class="caret"></span></a>
                    <ul role="menu" class="dropdown-menu">
                        <li><a href="LogoutServlet">Cerrar sesión</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>