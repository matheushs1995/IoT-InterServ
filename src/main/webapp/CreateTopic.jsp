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

            <div class="divMaster">
                <form id="form" class="form">
                    <span class="titleSection">
                        Specific Topic
                    </span>

                    <div class="divInput format-input" >
                        <span class="inputName">Topic</span>
                        <div id ="CC" class="chooseOrCreate">
                            <div id="inputTopic">
                                <select id="topic" class="inputSelect inputFormat" name="topicS">
                                    <c:forEach items="${topics}" var="topic"> 
                                        <option value="${topic}">${topic}</option>  
                                    </c:forEach> 
                                </select>	
                            </div>
                            <img class="iconMore" src="images/icons/more.png" onclick="createTopic()" width="43"/>
                        </div>
                    </div>

                    <div class="divInput format-input" >
                        <span class="inputName">Type of topic</span>
                        <div class="chooseOrCreate">
                            <select id ="typeTopic" class="inputSelect2 inputFormat" name="optType">
                                <option value="0">Producer</option>
                                <option value="1">Consumer</option>
                            </select>	

                        </div>
                    </div>


                    <div class="divInput validate-input">
                        <span class="inputName">Keywords (Separate with comma)</span>
                        <textarea class="input1 inputFormat" id="keyword" placeholder="Your keywords here..."></textarea>
                    </div>

                    <div class="divBtnForm">
                        <div class="btnForm">
                            <div class="nextDiv"></div>
                            <img class="nextB" src="images/next.jpg" onclick="nextStage()" />
                        </div>
                    </div>
                </form>
            </div>


        </div>

        <div id="dropDownSelect1"></div>

    </body>
</html>
