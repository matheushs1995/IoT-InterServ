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
        <link rel="stylesheet" type="text/css" href="css/IoT10.css">
        <script src="js/IoT18.js"></script>
        <script type="text/javascript" src="js/d3/d3.js"></script>
        <script type="text/javascript" src="js/d3/d3.geom.js"></script>
        <script type="text/javascript" src="js/d3/d3.layout.js"></script>
        <script type="text/javascript" src="js/CodeFlower6.js"></script>
        <script type="text/javascript" src="js/dataConverter.js"></script>
        <script src="js/Visualization6.js"></script>
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
                            <p class="itemList"><a href="">Search</a></p>
                        </div>
                    </div>
                    <div class="containerDivTop_list">
                        <button class="btnList" onclick="openList('2')">
                            <span class="drop">Topic</span>
                            <span class="acc">Topic</span>
                        </button>
                        <div class="contentDivTop_list left" id="thisList_2">
                            <p class="itemList"><a href="CreateTopicController">Create</a></p><br>
                            <p class="itemList"><a href="">Search</a></p>
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

            <div id="divChoice">
                <div id="divInputChoice">
                    <div class="divInput format-input" >

                        <div id ="CC" class="chooseOrCreate">
                            <div id="inputTopic">
                                <span class="inputName" style="font-size: 15px; margin-right: 15px;">Topic:</span>
                                <select id="topic" class="inputSelect inputFormat" name="topicS" style="width: 50%; margin-right: 15px; background: #fff">
                                    <c:forEach items="${topics}" var="topic"> 
                                        <option value="${topic}">${topic}</option>  
                                    </c:forEach> 
                                </select>	
                                <button onclick="createVisualization()" class="button1" style="width: 35%">Create Visualization</button>
                            </div>

                        </div>
                        <input type="checkbox" style="margin-right: 10px; margin-top: 20px" id="gt"> <span class="inputName">Generic Topic</span>
                        <input type="checkbox" style="margin-right: 10px; margin-left: 10px; margin-top: 20px" id="at"> <span class="inputName">Alternative Topic</span>
                        <input type="checkbox" style="margin-right: 10px; margin-left: 10px; margin-top: 20px" id="iot"><span class="inputName"> IoT Device/Application </span>
                        <br>
                        <input type="checkbox" style="margin-right: 10px; margin-top: 10px" id="stp"> <span class="inputName">Specific Topic Producer</span>
                        <input type="checkbox" style="margin-right: 10px; margin-left: 10px; margin-top: 10px" id="stc"> <span class="inputName">Specific Topic Consumer </span>
                    </div>


                </div>
            </div>

            <div id="divVisualization">

                <div id="graph"></div>

            </div>

            <div id ="labelNodes">

            </div>

            <div id="dropDownSelect1"></div>

    </body>
</html>
