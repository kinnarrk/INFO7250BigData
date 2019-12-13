<!-- Sidebar Holder -->
        <nav id="sidebar">
            <div class="sidebar-header">
                <h3>INFO 7250</h3>
            </div>
            
            <% String current = request.getParameter("current");%>

            <ul class="list-unstyled components">
                <p>Big Data: Kinnar Kansara</p>
<!--                <li class="active">
                    <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Home</a>
                    <ul class="collapse list-unstyled" id="homeSubmenu">
                        <li>
                            <a href="#">Home 1</a>
                        </li>
                        <li>
                            <a href="#">Home 2</a>
                        </li>
                        <li>
                            <a href="#">Home 3</a>
                        </li>
                    </ul>
                </li>-->
<!--                <li>
                    <a href="#">About</a>
                    <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Pages</a>
                    <ul class="collapse list-unstyled" id="pageSubmenu">
                        <li>
                            <a href="#">Page 1</a>
                        </li>
                        <li>
                            <a href="#">Page 2</a>
                        </li>
                        <li>
                            <a href="#">Page 3</a>
                        </li>
                    </ul>
                </li>-->
                <li <% if(current.equals("daily_delay")) out.println("class='active'"); else out.println(""); %>>
                    <a href="/DailyDelayController">Daily Delays</a>
                </li>
                <li>
                    <a href="/DailyCancelController">Daily Cancellations</a>
                </li>
            </ul>

            <ul class="list-unstyled CTAs">
                <li>
                    <a href="http://stat-computing.org/dataexpo/2009/the-data.html" target="_blank" class="download">Dataset</a>
                </li>
                <li>
                    <a href="https://github.com/kinnarrk/INFO7200BigData" target="_blank" class="article"><i class="fab fa-github"></i> Github repo</a>
                </li>
            </ul>
        </nav>
