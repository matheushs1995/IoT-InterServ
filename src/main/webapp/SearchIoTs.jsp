<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Pragmatic IoT</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/IoT11.css">
        <script src="js/IoT19.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    </head>
    <body>

        <div class="divTop">
            <nav>
                <div class="containerDivTop_left">
                    <a class="home" href="index.jsp">
                        <i class="fa fa-home"></i>
                    </a>
                    <button id="btnMenu" onclick="openMenu()">
                        <i class="fa fa-bars"></i>
                        <i class="fa fa-close"></i>
                    </button>
                </div>
                <div class="containerDivTop_menu" id="thisMenu">
                    <div class="containerDivTop_list">
                        <button class="btnList" onclick="openList('1')">
                            <span class="drop">IoT</span>
                            <span class="acc">IoT</span>
                        </button>
                        <div class="contentDivTop_list left" id="thisList_1">
                            <p class="itemList"><a href="CreateIoTController">Create</a></p><br>
                            <p class="itemList"><a href="SearchIoTsController">Search</a></p>
                        </div>
                    </div>
                    <div class="containerDivTop_list">
                        <button class="btnList" onclick="openList('2')">
                            <span class="drop">Topic</span>
                            <span class="acc">Topic</span>
                        </button>
                        <div class="contentDivTop_list left" id="thisList_2">
                            <p class="itemList"><a href="CreateTopicController">Create</a></p><br>
                            <p class="itemList"><a href="SearchTopicsController">Search</a></p>
                        </div>
                    </div>
                    <div class="containerDivTop_list">
                        <button class="btnList" onclick="openList('3')">
                            <span class="drop">Tutorial</span>
                            <span class="acc">Tutorial</span>
                        </button>
                        <div class="contentDivTop_list center" id="thisList_3">
                            <p class="itemList">All</p><br><p class="itemList">IoT</p><br><p class="itemList">Topic</p><br><p class="itemList">Rule</p>
                        </div>
                    </div>
                    <div class="containerDivTop_list">
                        <form action="VisualizationController" method="get">    
                            <button class="btnList">
                                <span class="drop">Visualization</span>
                                <span class="acc">Visualization</span>
                            </button>

                        </form>
                    </div>
                </div>
            </nav>
        </div>

        <div class="divBody" style="background-image: url('images/Background.jpg');">

            <div id="divMaster" style='max-width: 70%; max-height: calc(100vh - 116px); overflow: auto;' class="divMaster">
                <form id="form" class="form">
                    <span class="titleSection">
                        IoTs:
                    </span>
                    <c:forEach items="${iots}" var="iot"> 
                        <div class='checkboxDiv input1 inputFormat2'>${iot}</div>  
                    </c:forEach> 
                </form>
            </div>

        </div>

        <div id="dropDownSelect1"></div>

    </body>
</html>
