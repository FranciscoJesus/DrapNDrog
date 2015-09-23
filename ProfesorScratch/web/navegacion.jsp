                       
                        <nav role="navigation" class="navbar navbar-default navbar-static-top">
                            <div class="container">
                                <!-- Brand and toggle get grouped for better mobile display -->
                                <div class="navbar-header">
                                    <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                                        <span class="sr-only">Toggle navigation</span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                    </button>
                                    <a href="#" class="navbar-brand">Drag & Drop</a>
                                </div>
                                <!-- Collection of nav links and other content for toggling -->
                                <div id="navbarCollapse" class="collapse navbar-collapse">
                                    <ul class="nav navbar-nav navbar-right nav-stacked">
                                        <li><a href="main.jsp">Nuevo Problema</a></li>
                                        <li><a href="ListaProblemasServlet">Problemas</a></li>
                                            <!-- <ul class="nav navbar-nav"> -->
                                                <li class="dropdown">
                                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%= p.nombre + " " + p.apellido%><span class="caret"></span></a>
                                                    <ul role="menu" class="dropdown-menu">
                                                        <li><a href="LogoutServlet">Cerrar sesión</a></li>
                                                    </ul>
                                                </li>
                                            <!-- </ul> -->
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </nav>
