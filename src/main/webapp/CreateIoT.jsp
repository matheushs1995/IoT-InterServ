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

            <div id="divMaster" class="divMaster">
                <form id="form" class="form">
                    <span class="titleSection">
                        Choose or create an IoT
                    </span>

                    <div class="divInput format-input" >
                        <span class="inputName space1">IoT</span><span id="spanTypeIot" class="inputName" style="margin-left:45%"></span>
                        <div id ="CC" class="chooseOrCreate">
                            <div id="inputIot">
                                <select id="iot" class="inputSelect inputFormat space1" style="width: 48.5%; height: 43px;" name="iotS">
                                    <option value=''><p style='text-align: center;'>---</p></option>
                                    <c:forEach items="${iots}" var="iot"> 
                                        <option value="${iot}">${iot}</option>  
                                    </c:forEach> 
                                </select>
                                <select id='typeIot' class='inputSelect inputFormat' style=' width:35%; height:43px;' onchange='if (this.selectedIndex)
                                            getAllTMC()'> <option value=''>---</option><option value='p'>Producer</option><option value='c'>Consumer</option></select>

                            </div>
                            <img class="iconMore" src="images/icons/more.png" onclick="createIot()" width="43"/>
                        </div>
                    </div>

                    <div class="divInput format-input" >
                        <span class="inputName">Specific Topic</span>
                        <div class="chooseOrCreate">
                            <select id ="topic" class="inputSelect2 inputFormat" onchange="if (this.selectedIndex)
                                        getAllUnit()" name="optType">

                            </select>	
                        </div>
                    </div>

                    <div class="divInput format-input" >
                        <span class="inputName">Unit</span>
                        <div class="chooseOrCreate">
                            <div id="createUnit"></div>
                            <div id="inputUnit">
                                <select disabled id ="unit" class="inputSelect inputFormat" name="optUnits">
                                </select>	
                            </div>

                            <div id="img2">
                                <img class="iconMore" src="images/icons/more.png" onclick="createUnit()" width="43"/>
                            </div>
                        </div>
                    </div>

                    <div class="divInput validate-input">
                        <span class="inputName">Code Link (Optional)</span>
                        <input id="gitHubLink" class="input1 inputFormat" placeholder="GitHub Link..." style="height:43px">
                    </div>    

                    <div class="divInput validate-input">
                        <span class="inputName">Keywords (Separate with comma)</span>
                        <textarea class="input1 inputFormat" id="keyword" placeholder="Your keywords here..."></textarea>
                    </div>

                    <div class="divBtnForm">
                        <div class="btnForm">
                            <div class="nextDiv"></div>
                            <img class="nextB" src="images/next.jpg" onclick="saveIoT()" />
                        </div>
                    </div>


                </form>
            </div>
            <div id="divFunction">
                <span class="titleSection">
                    Function
                </span>
                <div class="divInput format-input"> 
                    <span class="inputName">Add operations in order:</span> 
                    <div class="chooseOrCreate"> 
                        <div id="divI"> 
                            <select id="S1" class="S input8 inputFormat space1" style="width:30%"> 
                                <option value=""></option> 
                                <option value="O1">+</option> 
                                <option value="O2">-</option> 
                                <option value="O3">*</option> 
                                <option value="O4">/</option> 
                                <option value="O5">^</option> 
                                <option value="O6">√</option> 
                            </select> 
                            <input id="I1" class="input7 inputFormat " name="I2" placeholder="Number..." style="width:40%"> 
                        </div> 
                        <img class="iconMore" src="images/icons/certain.png" onclick="addOperation()" width="43"> 
                    </div> 
                </div> 
                <div id="addedI" class="divInput"> 
                    <span class="inputName">Function:</span> 
                </div>
            </div>
            <div  class="divButton">
                <button type="button" onclick="saveIoT()" class="button1">Next</button>
            </div>   
        </div>

        <div id="dropDownSelect1"></div>

    </body>
</html>
