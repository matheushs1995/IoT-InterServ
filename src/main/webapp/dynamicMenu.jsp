<div class="divTop">
    <nav>
        <div class="container_left">
            <a class="home" href="#">
                <i class="fa fa-home"></i>
            </a>
            <button id="btnMenu" onclick="openMenu()">
                <i class="fa fa-bars"></i>
                <i class="fa fa-close"></i>
            </button>
        </div>
        <div class="container_menu" id="thisMenu">
            <div class="container_list">
                <button class="btnList" onclick="openList('1')">
                    <span class="drop">Produter</span>
                    <span class="acc">Accordion 1</span>
                </button>
                <div class="content_list left" id="thisList_1">
                    <p class="itemList">test</p><br><p class="itemList">test</p><br><p class="itemList">test</p><br><p class="itemList">test</p>
                </div>
            </div>
            <div class="container_list">
                <button class="btnList" onclick="openList('2')">
                    <span class="drop">Consumer</span>
                    <span class="acc">Accordion 2</span>
                </button>
                <div class="content_list left" id="thisList_2">
                    <p class="itemList">test</p><br><p class="itemList">test</p><br><p class="itemList">test</p><br><p class="itemList">test</p>
                </div>
            </div>
            <div class="container_list">
                <button class="btnList" onclick="openList('3')">
                    <span class="drop">Topic</span>
                    <span class="acc">Accordion 3</span>
                </button>
                <div class="content_list center" id="thisList_3">
                    <p class="itemList">test</p><br><p class="itemList">test</p><br><p class="itemList">test</p><br><p class="itemList">test</p>
                </div>
            </div>
            <div class="container_list">
                <button class="btnList" onclick="openList('4')">
                    <span class="drop">Pragmatic</span>
                    <span class="acc">Accordion 4</span>
                </button>
                <div class="content_list right" id="thisList_4">
                    <p class="itemList">test</p><br><p class="itemList">test</p><br><p class="itemList">test</p><br><p class="itemList">test</p>
                </div>
            </div>

        </div>

    </nav>
</div>