
var waiting = 0;

var topicEdit = "";

try {
    var topicCount = localStorage.getItem("topicCount");
} catch (err) {
    var topicCount = 0;
    localStorage.setItem("topicCount", "0");
}

function openList(x) {
    var cL = document.getElementsByClassName("contentDivTop_list");
    var i;
    for (i = 0; i < cL.length; i++) {
        var OcL = cL[i];
        if (cL[i] != document.getElementById("thisList_" + x)) {
            OcL.classList.remove("show_list");
        }
    }
    document.getElementById("thisList_" + x).classList.toggle("show_list");
}

function openSearch() {
    document.getElementById("thisSearch").classList.toggle("show_search");
    document.getElementById("btnSearch").getElementsByTagName("i")[0].classList.toggle("hidden");
    document.getElementById("btnSearch").getElementsByTagName("i")[1].classList.toggle("visible")
}

function openMenu() {
    document.getElementById("thisMenu").classList.toggle("show_menu");
    document.getElementById("btnMenu").getElementsByTagName("i")[0].classList.toggle("hidden");
    document.getElementById("btnMenu").getElementsByTagName("i")[1].classList.toggle("visible")
}

function createTopic() {
    document.getElementById("CC").innerHTML = "<div id='inputTopic'><input id='topic' class='input2 inputFormat' name='topicI' placeholder='Create a specific topic name...'/></div>"
            + "<img class='iconMore' src='images/icons/more.png' onclick='createTopic()' width='43'/>";

}
;

function createIot() {
    document.getElementById("CC").innerHTML = "<div id='inputIoT'><input id='iot' class='input9 inputFormat space1' name='iotI' style='height:43px;' type='text' disabled='disabled' value='New IoT'/>"
            + "<select id='typeIot' class='inputSelect inputFormat' style=' width:35%; height:43px;' onchange='if (this.selectedIndex) getAllTMC()'><option value=''>---</option><option value='p'>Producer</option><option value='c'>Consumer</option></select></div>"
            + "<img class='iconMore' src='images/icons/more.png' onclick='createIot()' width='43'/>";

    document.getElementById("createUnit").innerHTML = "";
    document.getElementById("spanTypeIot").innerHTML = "Type IoT";

}
;

function getAllTMC() {

    var temp;

    var type = document.getElementById("typeIot").value;
    if (type != "c" && type != "p")
        return;

    temp = "type=" + type;

    var iot = document.getElementById("iot").value;
    if (iot == "")
        return;

    if (iot != "New IoT")
        temp += "&iot=" + iot;



    $.get("GetTMCController?" + temp, function (data) {
        document.getElementById("topic").innerHTML = data;
    });

}
;


function createUnit() {

    if (document.getElementById("topic").length == 0)
        return;

    if (document.getElementById("unit").length == 0) {
        document.getElementById("inputUnit").innerHTML = "<input id='unit' class='input2 inputFormat' name='unitI' placeholder='Create a unit...'/>";
    } else {

        document.getElementById("createUnit").innerHTML = "<input id='unitSource' class='input3 inputFormat space1' name='unitI' placeholder='Unit...'/>"
                + "<button type='button' onclick='openDivFunction()' class='button1'>Function</button>";

        document.getElementById("inputUnit").innerHTML = "<select id='unit' class='input2 inputFormat' name='optUnits'>"
                + document.getElementById("unit").innerHTML + "</select>";

        document.getElementById("unit").className = "input2 inputFormat";

    }
    ;

}
;

function openDivFunction() {
    document.getElementById("divMaster").className = "divMaster prevent-click";
    document.getElementById("divMaster").style = " z-index: 9; filter: blur(2px);";
}
;

function closeDivFunction() {
    document.getElementById("divMaster").className = "divMaster";
    document.getElementById("divMaster").style = " z-index: 11; filter: blur(0px);";
}
;

function nextStage() {

    if (document.getElementById("topic").value == "") {
        alert("Fill in the field of the topic before submitting");
        return;
    }

    if (document.getElementById("topic").name == "topicS") {
        var typeTopic = "GetContextListController";
    } else {
        var typeTopic = "SetContextListController";
    }

    if (document.getElementById("keyword").value == "") {
        alert("Fill in the field of the keyword before submitting");
        return;
    }

    getDescription(typeTopic);

}
;

function saveIoT() {

    if (document.getElementById("iot").value == "") {
        alert("Fill in the field of the IoT before submitting");
        return;
    }

    if (document.getElementById("keyword").value == "") {
        alert("Fill in the field of the keyword before submitting");
        return;
    }

    if (document.getElementById("topic").value == "") {
        alert("Choose one topic before submitting");
        return;
    }

    if (document.getElementById("createUnit").innerHTML == "") {

        if (document.getElementById("unit").value == "") {
            alert("Fill in the field of the unit before submitting");
            return;
        }

        var x = document.getElementById("iot").value + "#" + document.getElementById("keyword").value + "#" + document.getElementById("topic").value + "#" + document.getElementById("unit").value;
        x = convert(x);
        $.get("SetIoTController?values=" + x, function (d) {
            alert(d);
        });
    } else {

        if (document.getElementById("unitSource").value == 0) {
            alert("Fill in the field of the unit before submitting");
            return;
        }

        var func = getFunction();

        if (func == "") {
            alert("Add at least one operation before submitting");
            return;
        }

        if (document.getElementById("unit").value == "") {
            alert("choose a topic and then a unit before submitting");
            return;
        }
        var x = document.getElementById("iot").value + "#" + document.getElementById("keyword").value + "#" + document.getElementById("topic").value + "#" + document.getElementById("unitSource").value + "#" + func + "#" + document.getElementById("unit").value;
        x = convert(x);
        $.get("SetIoTController?values=" + x, function (d) {
            alert(d);
        });


    }

}
;

function getFunction() {
    var x = document.getElementsByClassName("addValue");

    if (x.length == 0)
        return "";

    var fun = x[0].id;

    for (var i = 1; i < x.length; i++) {
        fun += "," + x[i].id;
    }

    return  fun;


}
;

function getChildren(id) {
    var x = document.getElementById(id + "-S").value;
    $.get("GetChildrenController?parent=" + x, function (d) {
        if (d == "null") {
            x = document.getElementById(id + "-S").value;
            localStorage.setItem(id + topicCount, x);
            x = "<span class='inputName'>Create the context - Type of context: " + id + "</span><div id='" + id + "-divI' class='contextI' name='" + id + "'>";
            x += "<input id='" + id + "-I1' class='input5 inputFormat' placeholder='Category..'/> <input id='" + id + "-I2' class='input5 inputFormat' placeholder='Name..'/>";
            x += "</div> <img class='iconGetChildren' name='" + id + "' src='images/icons/arrow.png' onclick='createChildren(this.name)' width='40'/></div>";
            document.getElementById(id).innerHTML = x;
        } else {
            x = document.getElementById(id + "-S").value;
            localStorage.setItem(id + topicCount, x);
            document.getElementById(id + "-divS").attributes.name.value = x;
            d = d.split("_");
            document.getElementById(id + "-S").name = d[1];
            document.getElementById(id + "-S").innerHTML = d[0];
        }
    });
}
;

function changeToCreate(id) {
    var y = document.getElementById(id + "-divS").attributes.name.value;

    if (y != "") {
        localStorage.setItem(id + topicCount, y);
    }
    var x = "<span class='inputName'>Create the context - Type of context: " + id + "</span><div id='" + id + "-divI' class='contextI' name='" + id + "'>";
    x += "<input disabled='disabled' id='" + id + "-I1' class='input5 inputFormat' value='" + document.getElementById(id + "-S").name + "'/> <input id='" + id + "-I2' class='input5 inputFormat' placeholder='Name..'/>";
    x += "</div> <img class='iconGetChildren' name='" + id + "' src='images/icons/arrow.png' onclick='createChildren(this.name)' width='40'/></div>";
    document.getElementById(id).innerHTML = x;
}
;

function pragmatic() {
    var x = localStorage.getItem("tempSuperContexts" + topicCount);

    x = x.split(",");
    if (x.length == 0)
        return;

    var values;

    if (document.getElementById(x[0] + "-S") != null) {
        var cont = document.getElementById(x[0] + "-S").value;
        if (cont != "") {
            values = cont;
        } else {
            return;
        }
    } else {
        if (document.getElementById(x[0] + "-I1") != null && document.getElementById(x[0] + "-I2") != null) {
            z = x[0];
            var w = document.getElementById(z + "-I1").value;
            var h = document.getElementById(z + "-I2").value;
            var y = localStorage.getItem(z + topicCount);

            if (y != null) {
                if (w == "" || h == "") {
                    values += y;
                } else {
                    values += y + "," + w + "-" + h;
                }
            } else {
                if (w == "") {
                    alert("Fill in the field of the category of type context '" + z + "' before submitting");
                    return;
                }
                if (h == "") {
                    alert("Fill in the field of the name of type context '" + z + "' before submitting");
                    return;
                }

                values += w + "-" + h;
            }

        }
    }

    for (var i = 1; i < x.length; i++) {

        values += ";";

        if (document.getElementById(x[i] + "-S") != null) {
            var cont = document.getElementById(x[i] + "-S").value;
            if (cont != "") {
                values += cont;
            } else {
                return;
            }
        } else {
            if (document.getElementById(x[i] + "-I1") != null && document.getElementById(x[i] + "-I2") != null) {
                z = x[i];
                alert(z);
                var w = document.getElementById(z + "-I1").value;
                var h = document.getElementById(z + "-I2").value;
                var y = localStorage.getItem(z + topicCount);
                alert(y);

                if (y != null) {
                    if (w == "" || h == "") {
                        values += y;
                    } else {
                        values += y + "," + w + "-" + h;
                    }
                } else {
                    if (w == "") {
                        alert("Fill in the field of the category of type context '" + z + "' before submitting");
                        return;
                    }
                    if (h == "") {
                        alert("Fill in the field of the name of type context '" + z + "' before submitting");
                        return;
                    }

                    values += w + "-" + h;
                }

            }
        }
    }

    localStorage.setItem("tempContexts" + topicCount, values);

    var typeTopic = localStorage.getItem("tempTypeTopic" + topicCount);
    if (typeTopic != "0") {
        var con1 = confirm("Do you want to limit contexts to the level at which they were inserted? For example, let's assume that one of the chosen contexts is a country, if I limit such context, the system will only search for contexts that have that exact name. However, if not limiting, it will consider the subcontexts of the context entered. In the case of a Country, subcontexts can be states, cities, neighborhoods, streets and other");

        if (con1) {
            typeTopic = "2";
        } else {
            typeTopic = "1";
        }

        localStorage.setItem("tempTypeTopic" + topicCount, typeTopic);
        saveWithoutPragmaticRules();

    } else {

        var con2 = confirm("do you want to add rules to the productions linked to this topic?");
        if (con2 == true) {
            $.get("SetPragmaticRulesController", function (d) {
                document.getElementById("form").innerHTML = d;
            });
        } else {
            saveWithoutPragmaticRules();
        }

    }

}

function createChildren(id) {
    var x = document.getElementById(id + "-I1").value;
    if (x == "") {
        alert("Fill in the field of the category before submitting");
        return;
    }

    var y = document.getElementById(id + "-I2").value;
    if (y == "") {
        alert("Fill in the field of the name before submitting");
        return;
    }

    var w = localStorage.getItem(id + topicCount);
    if (w != null) {
        localStorage.setItem(id + topicCount, w + "," + x + "-" + y);
    } else {
        localStorage.setItem(id + topicCount, x + "-" + y);
    }

    // document.getElementById(id + "-parent").innerHTML = "- Parent: " + y;

    document.getElementById(id + "-divI").innerHTML = "<input id='" + id + "-I1' class='input5 inputFormat' placeholder='Category..'><input id='" + id + "-I2' class='input5 inputFormat' placeholder='Name..'>";
}
;
function getDescription(typeT) {
    if (typeof (Storage) !== "undefined") {
        topicCount++;
        localStorage.setItem("topicCount", topicCount);
        localStorage.setItem("tempTopic" + topicCount, document.getElementById("topic").value);
        localStorage.setItem("tempTypeTopic" + topicCount, document.getElementById("typeTopic").value);
        topicEdit = document.getElementById("topic").value;
        localStorage.setItem("tempKeyword" + topicCount, document.getElementById("keyword").value);
        localStorage.setItem("tempTypeT" + topicCount, typeT);

        if (document.getElementById("topic").getAttribute("name") != "topicS") {

            document.getElementsByClassName("divBody")[0].innerHTML = "<div class='divMaster loader'></div>";

            $.get("GetDescriptionsController?word=" + topicEdit, function (d) {

                document.getElementsByClassName("divBody")[0].innerHTML = "<div class='divMaster' style='max-width: 70%; max-height: calc(100vh - 116px); overflow: auto;'><form id='form' class='form'></form></div>";

                if (d != "") {
                    document.getElementById("form").innerHTML = "<span class='titleSection'>"
                            + "Choose the description that best fit your term:"
                            + "</span>"
                            + "<div class='divInput format-input' >" + d
                            + "</div>"
                            + "<div class='divBtnForm'><div class='btnForm'><div class='nextDiv'></div>"
                            + "<img class='nextB' src='images/next.jpg' onclick='getSimilarTopics()' /></div></div>";
                } else {
                    document.getElementById("form").innerHTML = "<span class='titleSection'>"
                            + "Add a description:"
                            + "</span>"
                            + "<textarea class='input1 inputFormat' id='inDesc' placeholder='Your description here...' spellcheck='false'></textarea>"
                            + "<div class='divBtnForm'><div class='btnForm'><div class='nextDiv'></div>"
                            + "<img class='nextB' src='images/next.jpg' onclick='getSimilarTopics2()' /></div></div>";

                }

            });
        } else {
            localStorage.setItem("tempTopicInformation" + topicCount,"");
            getNextStage();
        }
    } else {
        alert("Update your browser version to use this system");
    }

}
;

function getSimilarTopics() {
    if (!$("input[type='radio'][name='desc']:checked").val()) {
        alert("Select one of the options");
        return;
    }

    var value = $("input[type='radio'][name='desc']:checked").val();

    if (value == "od") {
        getSimilarTopics2();
    } else {
        $.get("GetSimilarTopicsController?topic=" + localStorage.getItem("tempTopic" + topicCount)
                + "&keywords=" + localStorage.getItem("tempKeyword" + topicCount)
                + "&id=" + value, function (d) {
                    if (d == "") {
                        return;
                    }

                    if (d == "e") {
                        console.log("Some information was manipulated");
                        return;
                    }

                    localStorage.setItem("tempTopicInformation" + topicCount, value + ";;");

                    if (d.substring(0, 2) == "ed") {
                        localStorage.setItem("tempSimilarTopic" + topicCount, d.substring(3, d.length));
                        prepareNextStage();
                    } else {
                        localStorage.setItem("tempSimilarTopic" + topicCount, "");
                        document.getElementById("form").innerHTML = "<span class='titleSection'>"
                                + "Maybe these topics adhere to your context:"
                                + "</span>"
                                + "<div class='divInput format-input' >" + d
                                + "</div>"
                                + "<div class='divBtnForm'><div class='btnForm'><div class='nextDiv'></div>"
                                + "<img class='nextB' src='images/next.jpg' onclick='prepareNextStage()' /></div></div>";

                    }
                });
    }


}
;

function getSimilarTopics2() {
    desc = document.getElementById("inDesc").value;

    if (desc == "") {
        alert("Enter a description");
        return;
    }

    $.get("GetSimilarTopicsController?topic=" + localStorage.getItem("tempTopic" + topicCount)
            + "&keywords=" + localStorage.getItem("tempKeyword" + topicCount)
            + "&description=" + desc, function (d) {

                if (d == "") {
                    return;
                }

                localStorage.setItem("tempTopicInformation" + topicCount, ";" + localStorage.getItem("tempKeyword" + topicCount) + ";" + desc);
                localStorage.setItem("tempSimilarTopic" + topicCount, "");

                document.getElementById("form").innerHTML = "<span class='titleSection'>"
                        + "Maybe these topics adhere to your context:"
                        + "</span>"
                        + "<div class='divInput format-input' >" + d
                        + "</div>"
                        + "<div class='divBtnForm'><div class='btnForm'><div class='nextDiv'></div>"
                        + "<img class='nextB' src='images/next.jpg' onclick='prepareNextStage()' /></div></div>";
            });
}
;

function prepareNextStage() {
    
    if (localStorage.getItem("tempSimilarTopic" + topicCount) != "") {
        localStorage.setItem("tempTypeT" + topicCount, "GetContextListController");
        topicEdit = localStorage.getItem("tempSimilarTopic" + topicCount);
        document.getElementsByClassName("divBody")[0].innerHTML = "<div class='divMaster' style='max-width: 480px;'><form id='form' class='form'></form></div>";
        getNextStage(); 
    } else {
        if ($("input[type='radio'][name='gt']:checked").val() && $("input[type='radio'][name='gt']:checked").val()!= "none") {
            localStorage.setItem("tempTypeT" + topicCount, "GetContextListController");
            $.get("GetAlternativeTopicController?gt=" + $("input[type='radio'][name='gt']:checked").val(), function (d) {
                if (d == "") {
                    console.log("Some information was manipulated");
                    return;
                }
                topicEdit = d;
                localStorage.setItem("tempSimilarTopic" + topicCount, d);
                document.getElementsByClassName("divBody")[0].innerHTML = "<div class='divMaster' style='max-width: 480px;'><form id='form' class='form'></form></div>";
                getNextStage(); 
            });
        }else{
            document.getElementsByClassName("divBody")[0].innerHTML = "<div class='divMaster' style='max-width: 480px;'><form id='form' class='form'></form></div>";
            getNextStage(); 
        }
    }

};


function getNextStage() {

    $.get(localStorage.getItem("tempTypeT" + topicCount) + "?topic=" + topicEdit, function (d) {
        document.getElementById("form").innerHTML = d;
        if (localStorage.getItem("tempTypeT" + topicCount) == "GetContextListController") {
            var x = document.getElementsByClassName("divInput");
            if (x.length == 0)
                return alert("Error, no 'Type of Context' identified");
            var tempS = x[0].id;
            for (var i = 1; i < x.length; i++) {
                tempS += "," + x[i].id;
            }

            localStorage.setItem("tempSuperContexts" + topicCount, tempS);
        }
    });

}
;



function add1() {
    var value = document.getElementById("S").value;
    if (value == "")
        return;
    if (document.getElementById("an" + value) != null)
        return;
    $("#addedI").append("<div class='addList' id='a" + value + "'><span id='an" + value + "' class='addValue input4 inputFormat'>" + value + "<img name ='a" + value + "' class='iconDelete' src='images/icons/delete.png' onclick='del(this.name)' width='43'/></span></div>");
}
;
function add2() {
    var value = document.getElementById("I").value;
    if (value == "")
        return;
    if (document.getElementById("an" + value) != null)
        return;
    $("#addedI").append("<div class='addList' id='a" + value + "'><span id='an" + value + "' class='addValue input4 inputFormat'>" + value + "<img name ='a" + value + "' class='iconDelete' src='images/icons/delete.png' onclick='del(this.name)' width='43'/></span></div>");
}
;

function addOperation() {
    var v2 = document.getElementById("S1");
    if (v2.value == "") {
        alert("Choose an operator");

        return;
    }

    var v1 = document.getElementById("I1");
    if (v1.value == "") {
        alert("Add a value");
        return;
    }

    var realValue = v2.value + "_" + v1.value;
    v2 = v2.options[document.getElementById("S1").selectedIndex].text;

    if (v2 == "+" || v2 == "-" || v2 == "*" || v2 == "/") {
        v1 = "y = y" + v2 + v1.value;
    } else {
        if (v2 == "^") {
            v1 = "y = y<sup>" + v1.value + "</sup>";
        } else {
            v1 = "y = <sup>" + v1.value + "</sup>√y"
        }
    }

    $("#addedI").append("<div class='addList' id='a" + realValue + "'><span id='" + realValue + "' class='addValue input4 inputFormat' style='width: 75%'>" + v1 + "<img name ='a" + realValue + "' class='iconDelete' src='images/icons/delete.png' onclick='del(this.name)' width='43'/></span></div>");

}
;

function addRule() {
    var value1 = document.getElementById("I1").value;
    if (value1 == "") {
        return;
    }

    var value2 = document.getElementById("S1").value;
    if (value2 == "") {
        return;
    }

    var value3 = document.getElementById("S2").value;
    var value4 = document.getElementById("I2").value;
    var realValue = "";
    if (value3 != "") {
        if (value4 != "") {
            if (value2 < value3) {
                realValue = value2 + "_" + value3 + "," + value1 + "," + value4;
            } else {
                realValue = value3 + "_" + value2 + "," + value4 + "," + value1;
            }

            value3 = document.getElementById("S2").options[document.getElementById("S2").selectedIndex].text;
        } else {
            realValue = value2 + "," + value1;
            value3 = "";
        }
    } else {
        realValue = value2 + "," + value1;
        value4 = "";
    }

    value2 = document.getElementById("S1").options[document.getElementById("S1").selectedIndex].text;
    ;
    var topic = document.getElementById("topics").value;
    if (topic == "") {
        return;
    }

    realValue += "/" + topic;
    if (document.getElementById("an" + realValue) != null)
        return;
    $("#addedI").append("<div class='addList' id='a" + realValue + "'><span id='" + realValue + "' class='addValue input4 inputFormat'>" + value1 + " " + value2 + " Value " + value3 + " " + value4 + "&emsp;&emsp;Topic: " + topic + "<img name ='a" + realValue + "' class='iconDelete' src='images/icons/delete.png' onclick='del(this.name)' width='43'/></span></div>");
}
;
function chargeSelect1() {
    var x = document.getElementById("S1").value;
    if (x == "")
        return;
    if (x == "FT1") {
        document.getElementById("S2").innerHTML = "<option value=''></option><option value='FT2'>&#60;</option><option value='FT5'>&#60;=</option> <option value='FT6'>!=</option>";
    } else {
        if (x == "FT2") {
            document.getElementById("S2").innerHTML = "<option value=''></option><option value='FT1'>&#62;</option><option value='FT4'>&#62;=</option><option value='FT6'>!=</option>";
        } else {
            if (x == "FT3") {
                document.getElementById("S2").innerHTML = "<option value=''></option>";
            } else {
                if (x == "FT4") {
                    document.getElementById("S2").innerHTML = "<option value=''></option><option value='FT2'>&#60;</option><option value='FT5'>&#60;=</option> <option value='FT6'>!=</option>";
                } else {
                    if (x == "FT5") {
                        document.getElementById("S2").innerHTML = "<option value=''></option><option value='FT1'>&#62;</option><option value='FT4'>&#62;=</option><option value='FT6'>!=</option>";
                    } else {
                        if (x == "FT6") {
                            document.getElementById("S2").innerHTML = "<option value=''></option><option value='FT1'>&#60;</option> <option value='FT2'>&#62;</option> <option value='FT4'>&#60;=</option> <option value='FT5'>&#62;=</option> <option value='FT6'>!=</option>";
                        } else {
                            document.getElementById("S2").innerHTML = "<option value=''></option>";
                        }
                    }
                }
            }
        }
    }
}
;
function del(id) {
    var item = document.getElementById(id);
    item.parentNode.removeChild(item)
}
;
function getContexts() {
    var x = document.getElementsByClassName("addValue");
    if (x.length == 0)
        return alert("Select/Create at least one type of context");
    var tempS = x[0].innerText;
    for (var i = 1; i < x.length; i++) {
        tempS += "," + x[i].innerText;
    }

    localStorage.setItem("tempSuperContexts" + topicCount, tempS);
    $.get("GetContextListController?SC=" + tempS, function (d) {
        document.getElementById("form").innerHTML = d;
    });
}
;
function saveWithoutPragmaticRules() {

    var insertValues = localStorage.getItem("tempTopic" + topicCount) + "#" + localStorage.getItem("tempKeyword" + topicCount) + "#" + localStorage.getItem("tempSuperContexts" + topicCount) + "#" + localStorage.getItem("tempContexts" + topicCount) + "##" + localStorage.getItem("tempTypeTopic" + topicCount)+"#"+localStorage.getItem("tempTopicInformation" + topicCount);
    alert(insertValues);
    insertValues = convert(insertValues);
    alert(insertValues);
    $.get("Save?values=" + insertValues, function (d) {
        alert(d);
    });
}
;
function saveWithPragmaticRules() {
    var x = document.getElementsByClassName("addValue");
    if (x.length == 0)
        return alert("Select/Create at least one type of context");
    var tempS = x[0].id;
    for (var i = 1; i < x.length; i++) {
        tempS += ";" + x[i].id;
    }

    var insertValues = localStorage.getItem("tempTopic" + topicCount) + "#" + localStorage.getItem("tempKeyword" + topicCount) + "#" + localStorage.getItem("tempSuperContexts" + topicCount) + "#" + localStorage.getItem("tempContexts" + topicCount) + "#" + tempS + "#" + localStorage.getItem("tempTypeTopic" + topicCount)+"#"+localStorage.getItem("tempTopicInformation" + topicCount);
    insertValues = convert(insertValues);
    $.get("Save?values=" + insertValues, function (d) {
        alert(d);
    });
}
;
function convert(input) {

    var output = "";
    for (var i = 0; i < input.length; i++) {
        output += input[i].charCodeAt(0).toString(2) + " ";
    }

    return output;
}
;

function setUnit(id) {
    var x = document.getElementById("topic").value;
    if (x == "")
        return;

    var y = document.getElementById(id).value;
    if (y == "")
        return;

    if (document.getElementById("createUnit").innerHTML == "") {

        $.get("SetUnitController?topic=" + x + "&unit=" + y, function (d) {
            if (d != "") {

                document.getElementById("createUnit").innerHTML = "<select id ='unitSource' class='input3 inputFormat space1' name='optUnits' onchange='if (this.selectedIndex) setUnit(this.id)'>"
                        + document.getElementById("unit").innerHTML + "</select>"
                        + "<button type='button' onclick='openDivFunction()' class='button1'>Function</button>";
                document.getElementById("unit").className = "input2 inputFormat";

                var w = document.getElementById("unit").selectedIndex;
                document.getElementById("unitSource").selectedIndex = w;
                document.getElementById("inputUnit").innerHTML = "<select id='unit' class='input2 inputFormat' name='optUnits'>"
                        + d + "</select>";

                turnUnitInvisible(y);

            }
        });

    } else {
        $.get("SetUnitController?topic=" + x + "&unit=" + y, function (d) {
            if (d != "") {
                document.getElementById("unit").innerHTML = d;
            } else {
                getAllUnitAndSelectIndex();
            }
        });

        turnUnitInvisible(y);
    }

}

function getIndexOfValue(v) {
    var x = document.getElementById("unit");
    for (var i = 0; i < x.length; i++) {
        if (x[i].value == v) {
            return i;
        }
    }

    return -1;
}

function turnUnitInvisible(y) {
    var ve = document.getElementById("unit");
    for (var i = 0; i < ve.length; i++) {
        if (ve[i].value == y) {
            ve[i].style = "display:none";
        } else {
            ve[i].style = "display:relative";
        }
    }
}

function getAllUnit() {

    if (document.getElementById("topic").value == "")
        return;

    if (document.getElementById("unit").name == "optUnits") {
        if (document.getElementById("iot").value != "" && document.getElementById("topic").value != "") {
            $.get("GetAllUnitController", function (data) {
                if (data != "") {
                    document.getElementById("createUnit").innerHTML = "";
                    document.getElementById("inputUnit").innerHTML = data;
                } else {
                    createUnit();
                }
            });
        }

    }

}
;

function getAllUnitAndSelectIndex() {

    if (document.getElementById("topic").value == "")
        return;

    if (document.getElementById("unit").name == "optUnits") {
        if (document.getElementById("iot").value != "" && document.getElementById("topic").value != "") {
            $.get("GetAllUnitController", function (data) {
                if (data != "") {
                    var x = document.getElementById("unitSource").value;

                    document.getElementById("createUnit").innerHTML = "";
                    document.getElementById("inputUnit").innerHTML = data;

                    x = getIndexOfValue(x);
                    if (x != -1)
                        document.getElementById("unit").selectedIndex = x;

                } else {
                    createUnit();
                }
            });
        }

    }

}
;