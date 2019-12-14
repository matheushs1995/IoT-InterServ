function createVisualization() {
    var myFlower = new CodeFlower("#graph", 554, 554);
    document.getElementById("divVisualization").style = "background:#ffffff87";
    document.getElementById("labelNodes").style = "background:#ffffff87;";
    document.getElementById("labelNodes").innerHTML = "<img src='images/viewOpt1.jpg' style='padding: 0px 0px 0px 0px; position: absolute;'>" ;
    myFlower.update(jsonData);
}
;
function getJsonOfTopic() {

    var topic = document.getElementById("topic").value;

    $.get("GetJsonOfTopicController?topic" + topic, function (data) {
        var jsonTopic = data;
        var myFlower = new CodeFlower("#graph", 554, 554);
        document.getElementById("divVisualization").style = "background:#ffffff87";
        myFlower.update(jsonTopic);
    });
}
;

var jsonData = {
    "name": "GT1",
    "group": "1",
    "size": 50,
    "children": [{
            "name": "AT1",
            "group": "2",
            "size": 50,

        },
        {
            "name": "AT2",
            "group": "2",
            "size": 50
        },
        {
            "name": "AT3",
            "group": "2",
            "size": 50
        },
        {
            "name": "AT4",
            "group": "2",
            "size": 50
        },{
            "name": "AT1",
            "group": "2",
            "size": 50,

        },
        {
            "name": "AT2",
            "group": "2",
            "size": 50
        },
        {
            "name": "AT3",
            "group": "2",
            "size": 50
        },
        {
            "name": "AT4",
            "group": "2",
            "size": 50
        },{
            "name": "AT1",
            "group": "2",
            "size": 50,

        },
        {
            "name": "AT2",
            "group": "2",
            "size": 50
        },
        {
            "name": "AT3",
            "group": "2",
            "size": 50
        },
        {
            "name": "AT4",
            "group": "2",
            "size": 50
        },{
            "name": "AT1",
            "group": "2",
            "size": 50,

        },
        {
            "name": "AT2",
            "group": "2",
            "size": 50
        },
        {
            "name": "AT3",
            "group": "2",
            "size": 50
        },
        {
            "name": "AT4",
            "group": "2",
            "size": 50
        },{
            "name": "AT1",
            "group": "2",
            "size": 50,

        },
        {
            "name": "AT2",
            "group": "2",
            "size": 50
        },
        {
            "name": "AT3",
            "group": "2",
            "size": 50
        },
        {
            "name": "AT4",
            "group": "2",
            "size": 50
        },{
            "name": "AT1",
            "group": "2",
            "size": 50,

        },
        {
            "name": "AT2",
            "group": "2",
            "size": 50
        },
        {
            "name": "AT3",
            "group": "2",
            "size": 50
        },
        {
            "name": "AT4",
            "group": "2",
            "size": 50
        },{
            "name": "AT1",
            "group": "2",
            "size": 50,

        },
        {
            "name": "AT2",
            "group": "2",
            "size": 50
        },
        {
            "name": "AT3",
            "group": "2",
            "size": 50
        },
        {
            "name": "AT4",
            "group": "2",
            "size": 50
        }, {

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        },{

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        },{

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        },{

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        },{

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        },{

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        },{

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        },{

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        },{

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "5",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        },{

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }, {

            "name": "ST1",
            "group": "3",
            "size": 50,
            "children": [{
                    "name": "IoT1",
                    "group": "4",
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }, {
                    "name": "IoT2",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT3",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT4",
                    "group": "4"
                    ,
                    "size": 50
                },
                {
                    "name": "IoT5",
                    "group": "4"
                    ,
                    "size": 50
                }]
        }
    ]
};


var jsonData2 = {"name": "root", "children": [{"name": "src", "children": [{"name": "Propel", "children": [{"name": "Generator", "children": [{"name": "Builder", "children": [{"name": "Om", "children": [{"name": "ObjectBuilder.php", "size": 3040, "language": "PHP"}, {"name": "PeerBuilder.php", "size": 1046, "language": "PHP"}, {"name": "QueryBuilder.php", "size": 830, "language": "PHP"}, {"name": "TableMapBuilder.php", "size": 514, "language": "PHP"}, {"name": "AbstractOMBuilder.php", "size": 440, "language": "PHP"}, {"name": "QueryInheritanceBuilder.php", "size": 146, "language": "PHP"}, {"name": "AbstractPeerBuilder.php", "size": 131, "language": "PHP"}, {"name": "templates", "children": [{"name": "baseObjectMethods.php", "size": 104, "language": "PHP"}, {"name": "baseObjectMethodHook.php", "size": 44, "language": "PHP"}, {"name": "baseObjectMethodMagicCall.php", "size": 30, "language": "PHP"}, {"name": "tableMapInstancePool.php", "size": 23, "language": "PHP"}, {"name": "tableMapFields.php", "size": 16, "language": "PHP"}, {"name": "tableMapConstants.php", "size": 13, "language": "PHP"}, {"name": "tableMapClearRelatedInstancePool.php", "size": 6, "language": "PHP"}, {"name": "baseObjectAttributes.php", "size": 4, "language": "PHP"}]}, {"name": "AbstractObjectBuilder.php", "size": 50, "language": "PHP"}, {"name": "MultiExtendObjectBuilder.php", "size": 83, "language": "PHP"}, {"name": "ClassTools.php", "size": 61, "language": "PHP"}, {"name": "ExtensionQueryInheritanceBuilder.php", "size": 47, "language": "PHP"}, {"name": "ExtensionQueryBuilder.php", "size": 41, "language": "PHP"}, {"name": "ExtensionObjectBuilder.php", "size": 33, "language": "PHP"}, {"name": "ExtensionPeerBuilder.php", "size": 29, "language": "PHP"}, {"name": "InterfaceBuilder.php", "size": 28, "language": "PHP"}]}, {"name": "Util", "children": [{"name": "SchemaReader.php", "size": 250, "language": "PHP"}, {"name": "PropelTemplate.php", "size": 36, "language": "PHP"}, {"name": "ColumnValue.php", "size": 21, "language": "PHP"}, {"name": "DataRow.php", "size": 21, "language": "PHP"}]}, {"name": "DataModelBuilder.php", "size": 219, "language": "PHP"}, {"name": "Sql", "children": [{"name": "DataSQLBuilder.php", "size": 105, "language": "PHP"}, {"name": "Pgsql", "children": [{"name": "PgsqlDataSQLBuilder.php", "size": 48, "language": "PHP"}]}, {"name": "Mssql", "children": [{"name": "MssqlDataSQLBuilder.php", "size": 14, "language": "PHP"}]}, {"name": "Sqlite", "children": [{"name": "SqliteDataSQLBuilder.php", "size": 13, "language": "PHP"}]}, {"name": "Oracle", "children": [{"name": "OracleDataSQLBuilder.php", "size": 6, "language": "PHP"}]}, {"name": "Sqlsrv", "children": [{"name": "SqlsrvDataSQLBuilder.php", "size": 6, "language": "PHP"}]}, {"name": "Mysql", "children": [{"name": "MysqlDataSQLBuilder.php", "size": 6, "language": "PHP"}]}]}]}, {"name": "Behavior", "children": [{"name": "NestedSet", "children": [{"name": "NestedSetBehaviorObjectBuilderModifier.php", "size": 947, "language": "PHP"}, {"name": "NestedSetBehaviorQueryBuilderModifier.php", "size": 574, "language": "PHP"}, {"name": "NestedSetBehavior.php", "size": 70, "language": "PHP"}, {"name": "templates", "children": [{"name": "objectInsertAsLastChildOf.php", "size": 22, "language": "PHP"}, {"name": "objectSetLeft.php", "size": 4, "language": "PHP"}, {"name": "objectGetIterator.php", "size": 4, "language": "PHP"}]}]}, {"name": "Versionable", "children": [{"name": "VersionableBehaviorObjectBuilderModifier.php", "size": 469, "language": "PHP"}, {"name": "VersionableBehavior.php", "size": 212, "language": "PHP"}, {"name": "VersionableBehaviorQueryBuilderModifier.php", "size": 108, "language": "PHP"}]}, {"name": "Sortable", "children": [{"name": "SortableBehaviorObjectBuilderModifier.php", "size": 422, "language": "PHP"}, {"name": "SortableBehaviorPeerBuilderModifier.php", "size": 217, "language": "PHP"}, {"name": "SortableBehaviorQueryBuilderModifier.php", "size": 179, "language": "PHP"}, {"name": "SortableBehavior.php", "size": 64, "language": "PHP"}, {"name": "SortableBehaviorTableMapBuilderModifier.php", "size": 26, "language": "PHP"}, {"name": "templates", "children": [{"name": "tableMapSortable.php", "size": 4, "language": "PHP"}]}]}, {"name": "I18n", "children": [{"name": "I18nBehavior.php", "size": 227, "language": "PHP"}, {"name": "I18nBehaviorObjectBuilderModifier.php", "size": 187, "language": "PHP"}, {"name": "I18nBehaviorQueryBuilderModifier.php", "size": 53, "language": "PHP"}, {"name": "templates", "children": [{"name": "objectGetTranslation.php", "size": 24, "language": "PHP"}, {"name": "objectRemoveTranslation.php", "size": 18, "language": "PHP"}, {"name": "queryJoinWithI18n.php", "size": 8, "language": "PHP"}, {"name": "queryJoinI18n.php", "size": 7, "language": "PHP"}, {"name": "objectSetTranslation.php", "size": 7, "language": "PHP"}, {"name": "queryUseI18nQuery.php", "size": 6, "language": "PHP"}, {"name": "objectTranslatedColumnSetter.php", "size": 5, "language": "PHP"}, {"name": "objectSetLocale.php", "size": 5, "language": "PHP"}, {"name": "objectGetLocaleAlias.php", "size": 4, "language": "PHP"}, {"name": "objectGetLocale.php", "size": 4, "language": "PHP"}, {"name": "objectTranslatedColumnGetter.php", "size": 4, "language": "PHP"}, {"name": "objectSetLocaleAlias.php", "size": 4, "language": "PHP"}, {"name": "objectGetCurrentTranslation.php", "size": 4, "language": "PHP"}, {"name": "objectPostDelete.php", "size": 3, "language": "PHP"}, {"name": "objectAttributes.php", "size": 2, "language": "PHP"}, {"name": "objectClearReferences.php", "size": 2, "language": "PHP"}, {"name": "staticAttributes.php", "size": 1, "language": "PHP"}]}]}, {"name": "QueryCache", "children": [{"name": "QueryCacheBehavior.php", "size": 219, "language": "PHP"}]}, {"name": "Sluggable", "children": [{"name": "SluggableBehavior.php", "size": 216, "language": "PHP"}]}, {"name": "ConcreteInheritance", "children": [{"name": "ConcreteInheritanceBehavior.php", "size": 191, "language": "PHP"}, {"name": "ConcreteInheritanceParentBehavior.php", "size": 56, "language": "PHP"}]}, {"name": "Validate", "children": [{"name": "ValidateBehavior.php", "size": 168, "language": "PHP"}, {"name": "templates", "children": [{"name": "objectValidate.php", "size": 38, "language": "PHP"}, {"name": "objectLoadValidatorMetadata.php", "size": 6, "language": "PHP"}, {"name": "objectGetValidationFailures.php", "size": 4, "language": "PHP"}, {"name": "objectAttributes.php", "size": 2, "language": "PHP"}]}]}, {"name": "Archivable", "children": [{"name": "ArchivableBehavior.php", "size": 151, "language": "PHP"}, {"name": "ArchivableBehaviorObjectBuilderModifier.php", "size": 124, "language": "PHP"}, {"name": "ArchivableBehaviorQueryBuilderModifier.php", "size": 90, "language": "PHP"}, {"name": "templates", "children": [{"name": "queryArchive.php", "size": 24, "language": "PHP"}, {"name": "objectSaveWithoutArchive.php", "size": 19, "language": "PHP"}, {"name": "objectPopulateFromArchive.php", "size": 17, "language": "PHP"}, {"name": "objectArchive.php", "size": 16, "language": "PHP"}, {"name": "objectPreDelete.php", "size": 15, "language": "PHP"}, {"name": "objectGetArchive.php", "size": 10, "language": "PHP"}, {"name": "queryDeleteWithoutArchive.php", "size": 10, "language": "PHP"}, {"name": "objectRestoreFromArchive.php", "size": 8, "language": "PHP"}, {"name": "queryUpdateWithoutArchive.php", "size": 5, "language": "PHP"}, {"name": "objectDeleteWithoutArchive.php", "size": 5, "language": "PHP"}, {"name": "querySetArchiveOnUpdate.php", "size": 4, "language": "PHP"}, {"name": "querySetArchiveOnDelete.php", "size": 4, "language": "PHP"}]}]}, {"name": "AggregateColumn", "children": [{"name": "AggregateColumnRelationBehavior.php", "size": 119, "language": "PHP"}, {"name": "AggregateColumnBehavior.php", "size": 99, "language": "PHP"}, {"name": "templates", "children": [{"name": "queryFindRelated.php", "size": 14, "language": "PHP"}, {"name": "objectUpdateRelated.php", "size": 10, "language": "PHP"}, {"name": "objectCompute.php", "size": 9, "language": "PHP"}, {"name": "queryUpdateRelated.php", "size": 7, "language": "PHP"}, {"name": "objectUpdate.php", "size": 5, "language": "PHP"}]}]}, {"name": "Delegate", "children": [{"name": "DelegateBehavior.php", "size": 105, "language": "PHP"}]}, {"name": "Timestampable", "children": [{"name": "TimestampableBehavior.php", "size": 91, "language": "PHP"}]}, {"name": "AutoAddPk", "children": [{"name": "AutoAddPkBehavior.php", "size": 28, "language": "PHP"}]}]}, {"name": "Model", "children": [{"name": "Table.php", "size": 913, "language": "PHP"}, {"name": "Column.php", "size": 684, "language": "PHP"}, {"name": "Database.php", "size": 373, "language": "PHP"}, {"name": "ForeignKey.php", "size": 373, "language": "PHP"}, {"name": "Diff", "children": [{"name": "TableDiff.php", "size": 356, "language": "PHP"}, {"name": "TableComparator.php", "size": 183, "language": "PHP"}, {"name": "DatabaseDiff.php", "size": 162, "language": "PHP"}, {"name": "DatabaseComparator.php", "size": 81, "language": "PHP"}, {"name": "ColumnComparator.php", "size": 66, "language": "PHP"}, {"name": "ColumnDiff.php", "size": 59, "language": "PHP"}, {"name": "ForeignKeyComparator.php", "size": 44, "language": "PHP"}, {"name": "IndexComparator.php", "size": 26, "language": "PHP"}]}, {"name": "PropelTypes.php", "size": 242, "language": "PHP"}, {"name": "Domain.php", "size": 199, "language": "PHP"}, {"name": "Schema.php", "size": 165, "language": "PHP"}, {"name": "Behavior.php", "size": 156, "language": "PHP"}, {"name": "Index.php", "size": 141, "language": "PHP"}, {"name": "MappingModel.php", "size": 101, "language": "PHP"}, {"name": "ScopedMappingModel.php", "size": 71, "language": "PHP"}, {"name": "VendorInfo.php", "size": 70, "language": "PHP"}, {"name": "PhpNameGenerator.php", "size": 69, "language": "PHP"}, {"name": "Inheritance.php", "size": 67, "language": "PHP"}, {"name": "ColumnDefaultValue.php", "size": 50, "language": "PHP"}, {"name": "IdMethodParameter.php", "size": 50, "language": "PHP"}, {"name": "Unique.php", "size": 30, "language": "PHP"}, {"name": "ConstraintNameGenerator.php", "size": 26, "language": "PHP"}, {"name": "NameFactory.php", "size": 21, "language": "PHP"}, {"name": "NameGenerator.php", "size": 12, "language": "PHP"}, {"name": "IdMethod.php", "size": 7, "language": "PHP"}, {"name": "MappingModelInterface.php", "size": 6, "language": "PHP"}]}, {"name": "Platform", "children": [{"name": "DefaultPlatform.php", "size": 735, "language": "PHP"}, {"name": "MysqlPlatform.php", "size": 453, "language": "PHP"}, {"name": "PgsqlPlatform.php", "size": 381, "language": "PHP"}, {"name": "OraclePlatform.php", "size": 293, "language": "PHP"}, {"name": "MssqlPlatform.php", "size": 147, "language": "PHP"}, {"name": "SqlitePlatform.php", "size": 127, "language": "PHP"}, {"name": "PlatformInterface.php", "size": 39, "language": "PHP"}, {"name": "SqlsrvPlatform.php", "size": 9, "language": "PHP"}]}, {"name": "Reverse", "children": [{"name": "PgsqlSchemaParser.php", "size": 415, "language": "PHP"}, {"name": "MysqlSchemaParser.php", "size": 291, "language": "PHP"}, {"name": "OracleSchemaParser.php", "size": 172, "language": "PHP"}, {"name": "MssqlSchemaParser.php", "size": 166, "language": "PHP"}, {"name": "SqliteSchemaParser.php", "size": 125, "language": "PHP"}, {"name": "AbstractSchemaParser.php", "size": 95, "language": "PHP"}, {"name": "SchemaParserInterface.php", "size": 14, "language": "PHP"}, {"name": "SqlsrvSchemaParser.php", "size": 5, "language": "PHP"}]}, {"name": "Manager", "children": [{"name": "MigrationManager.php", "size": 242, "language": "PHP"}, {"name": "AbstractManager.php", "size": 192, "language": "PHP"}, {"name": "SqlManager.php", "size": 125, "language": "PHP"}, {"name": "ModelManager.php", "size": 104, "language": "PHP"}, {"name": "ReverseManager.php", "size": 89, "language": "PHP"}, {"name": "GraphvizManager.php", "size": 61, "language": "PHP"}]}, {"name": "Util", "children": [{"name": "QuickBuilder.php", "size": 207, "language": "PHP"}, {"name": "PhpParser.php", "size": 114, "language": "PHP"}, {"name": "SqlParser.php", "size": 111, "language": "PHP"}, {"name": "SchemaValidator.php", "size": 66, "language": "PHP"}]}, {"name": "Config", "children": [{"name": "GeneratorConfig.php", "size": 177, "language": "PHP"}, {"name": "QuickGeneratorConfig.php", "size": 91, "language": "PHP"}, {"name": "ArrayToPhpConverter.php", "size": 85, "language": "PHP"}, {"name": "XmlToArrayConverter.php", "size": 60, "language": "PHP"}, {"name": "GeneratorConfigInterface.php", "size": 15, "language": "PHP"}]}, {"name": "Command", "children": [{"name": "MigrationDiffCommand.php", "size": 129, "language": "PHP"}, {"name": "TestPrepareCommand.php", "size": 122, "language": "PHP"}, {"name": "MigrationDownCommand.php", "size": 115, "language": "PHP"}, {"name": "MigrationUpCommand.php", "size": 113, "language": "PHP"}, {"name": "MigrationMigrateCommand.php", "size": 113, "language": "PHP"}, {"name": "MigrationStatusCommand.php", "size": 113, "language": "PHP"}, {"name": "ModelBuildCommand.php", "size": 110, "language": "PHP"}, {"name": "AbstractCommand.php", "size": 82, "language": "PHP"}, {"name": "DatabaseReverseCommand.php", "size": 56, "language": "PHP"}, {"name": "ConfigConvertXmlCommand.php", "size": 55, "language": "PHP"}, {"name": "SqlBuildCommand.php", "size": 52, "language": "PHP"}, {"name": "SqlInsertCommand.php", "size": 41, "language": "PHP"}, {"name": "GraphvizGenerateCommand.php", "size": 39, "language": "PHP"}]}, {"name": "Exception", "children": [{"name": "EngineException.php", "size": 5, "language": "PHP"}, {"name": "BuildException.php", "size": 5, "language": "PHP"}, {"name": "ExceptionInterface.php", "size": 5, "language": "PHP"}, {"name": "ConstraintNotFoundException.php", "size": 5, "language": "PHP"}, {"name": "LogicException.php", "size": 5, "language": "PHP"}, {"name": "ClassNotFoundException.php", "size": 5, "language": "PHP"}, {"name": "RuntimeException.php", "size": 5, "language": "PHP"}, {"name": "SchemaException.php", "size": 5, "language": "PHP"}, {"name": "BehaviorNotFoundException.php", "size": 5, "language": "PHP"}, {"name": "InvalidArgumentException.php", "size": 5, "language": "PHP"}]}]}, {"name": "Runtime", "children": [{"name": "ActiveQuery", "children": [{"name": "ModelCriteria.php", "size": 1127, "language": "PHP"}, {"name": "Criteria.php", "size": 799, "language": "PHP"}, {"name": "Join.php", "size": 275, "language": "PHP"}, {"name": "Criterion", "children": [{"name": "AbstractCriterion.php", "size": 173, "language": "PHP"}, {"name": "AbstractModelCriterion.php", "size": 49, "language": "PHP"}, {"name": "BasicCriterion.php", "size": 45, "language": "PHP"}, {"name": "LikeCriterion.php", "size": 44, "language": "PHP"}, {"name": "SeveralModelCriterion.php", "size": 35, "language": "PHP"}, {"name": "LikeModelCriterion.php", "size": 28, "language": "PHP"}, {"name": "InCriterion.php", "size": 26, "language": "PHP"}, {"name": "RawModelCriterion.php", "size": 26, "language": "PHP"}, {"name": "InModelCriterion.php", "size": 25, "language": "PHP"}, {"name": "RawCriterion.php", "size": 24, "language": "PHP"}, {"name": "BasicModelCriterion.php", "size": 22, "language": "PHP"}, {"name": "CustomCriterion.php", "size": 17, "language": "PHP"}, {"name": "Exception", "children": [{"name": "InvalidValueException.php", "size": 6, "language": "PHP"}, {"name": "InvalidClauseException.php", "size": 6, "language": "PHP"}]}]}, {"name": "ModelWith.php", "size": 142, "language": "PHP"}, {"name": "ModelJoin.php", "size": 91, "language": "PHP"}, {"name": "InstancePoolTrait.php", "size": 52, "language": "PHP"}, {"name": "PropelQuery.php", "size": 19, "language": "PHP"}, {"name": "Exception", "children": [{"name": "UnknownModelException.php", "size": 6, "language": "PHP"}, {"name": "UnknownColumnException.php", "size": 6, "language": "PHP"}, {"name": "UnknownRelationException.php", "size": 6, "language": "PHP"}]}]}, {"name": "Util", "children": [{"name": "BasePeer.php", "size": 478, "language": "PHP"}, {"name": "PropelModelPager.php", "size": 225, "language": "PHP"}, {"name": "Profiler.php", "size": 151, "language": "PHP"}, {"name": "PropelColumnTypes.php", "size": 67, "language": "PHP"}, {"name": "PropelConditionalProxy.php", "size": 63, "language": "PHP"}, {"name": "PropelDateTime.php", "size": 60, "language": "PHP"}]}, {"name": "Connection", "children": [{"name": "ConnectionWrapper.php", "size": 273, "language": "PHP"}, {"name": "StatementWrapper.php", "size": 101, "language": "PHP"}, {"name": "ConnectionManagerMasterSlave.php", "size": 69, "language": "PHP"}, {"name": "ProfilerConnectionWrapper.php", "size": 68, "language": "PHP"}, {"name": "ConnectionManagerSingle.php", "size": 48, "language": "PHP"}, {"name": "ConnectionFactory.php", "size": 36, "language": "PHP"}, {"name": "ProfilerStatementWrapper.php", "size": 20, "language": "PHP"}, {"name": "ConnectionInterface.php", "size": 18, "language": "PHP"}, {"name": "StatementInterface.php", "size": 14, "language": "PHP"}, {"name": "ConnectionManagerInterface.php", "size": 11, "language": "PHP"}, {"name": "DebugPDO.php", "size": 7, "language": "PHP"}, {"name": "Exception", "children": [{"name": "ConnectionException.php", "size": 6, "language": "PHP"}, {"name": "RollbackException.php", "size": 5, "language": "PHP"}]}, {"name": "PropelPDO.php", "size": 6, "language": "PHP"}]}, {"name": "Map", "children": [{"name": "TableMap.php", "size": 261, "language": "PHP"}, {"name": "ColumnMap.php", "size": 222, "language": "PHP"}, {"name": "RelationMap.php", "size": 138, "language": "PHP"}, {"name": "DatabaseMap.php", "size": 87, "language": "PHP"}, {"name": "TableMapTrait.php", "size": 28, "language": "PHP"}, {"name": "Exception", "children": [{"name": "ColumnNotFoundException.php", "size": 6, "language": "PHP"}, {"name": "ForeignKeyNotFoundException.php", "size": 6, "language": "PHP"}, {"name": "TableNotFoundException.php", "size": 6, "language": "PHP"}, {"name": "RelationNotFoundException.php", "size": 6, "language": "PHP"}]}]}, {"name": "Adapter", "children": [{"name": "Pdo", "children": [{"name": "PdoAdapter.php", "size": 258, "language": "PHP"}, {"name": "MssqlAdapter.php", "size": 148, "language": "PHP"}, {"name": "OracleAdapter.php", "size": 107, "language": "PHP"}, {"name": "PgsqlAdapter.php", "size": 50, "language": "PHP"}, {"name": "MysqlAdapter.php", "size": 78, "language": "PHP"}, {"name": "SqlsrvAdapter.php", "size": 59, "language": "PHP"}, {"name": "SqliteAdapter.php", "size": 38, "language": "PHP"}, {"name": "PdoConnection.php", "size": 33, "language": "PHP"}, {"name": "PdoStatement.php", "size": 10, "language": "PHP"}]}, {"name": "MSSQL", "children": [{"name": "MssqlPropelPDO.php", "size": 83, "language": "PHP"}, {"name": "MssqlDebugPDO.php", "size": 6, "language": "PHP"}]}, {"name": "AdapterInterface.php", "size": 40, "language": "PHP"}, {"name": "AdapterFactory.php", "size": 20, "language": "PHP"}, {"name": "Exception", "children": [{"name": "ColumnNotFoundException.php", "size": 6, "language": "PHP"}, {"name": "AdapterException.php", "size": 6, "language": "PHP"}, {"name": "MalformedClauseException.php", "size": 6, "language": "PHP"}, {"name": "UnsupportedEncodingException.php", "size": 6, "language": "PHP"}]}]}, {"name": "Collection", "children": [{"name": "Collection.php", "size": 256, "language": "PHP"}, {"name": "ObjectCollection.php", "size": 143, "language": "PHP"}, {"name": "OnDemandCollection.php", "size": 124, "language": "PHP"}, {"name": "ArrayCollection.php", "size": 109, "language": "PHP"}, {"name": "OnDemandIterator.php", "size": 67, "language": "PHP"}, {"name": "Exception", "children": [{"name": "ReadOnlyModelException.php", "size": 6, "language": "PHP"}, {"name": "ModelNotFoundException.php", "size": 6, "language": "PHP"}, {"name": "UnsupportedRelationException.php", "size": 6, "language": "PHP"}]}]}, {"name": "ServiceContainer", "children": [{"name": "StandardServiceContainer.php", "size": 226, "language": "PHP"}, {"name": "ServiceContainerInterface.php", "size": 22, "language": "PHP"}]}, {"name": "Parser", "children": [{"name": "CsvParser.php", "size": 179, "language": "PHP"}, {"name": "XmlParser.php", "size": 113, "language": "PHP"}, {"name": "AbstractParser.php", "size": 41, "language": "PHP"}, {"name": "YamlParser.php", "size": 22, "language": "PHP"}, {"name": "JsonParser.php", "size": 21, "language": "PHP"}]}, {"name": "Formatter", "children": [{"name": "AbstractFormatter.php", "size": 141, "language": "PHP"}, {"name": "ArrayFormatter.php", "size": 106, "language": "PHP"}, {"name": "ObjectFormatter.php", "size": 84, "language": "PHP"}, {"name": "OnDemandFormatter.php", "size": 79, "language": "PHP"}, {"name": "SimpleArrayFormatter.php", "size": 59, "language": "PHP"}, {"name": "StatementFormatter.php", "size": 23, "language": "PHP"}]}, {"name": "Propel.php", "size": 121, "language": "PHP"}, {"name": "ActiveRecord", "children": [{"name": "NestedSetRecursiveIterator.php", "size": 61, "language": "PHP"}, {"name": "ActiveRecordInterface.php", "size": 5, "language": "PHP"}]}, {"name": "Validator", "children": [{"name": "Constraints", "children": [{"name": "UniqueValidator.php", "size": 24, "language": "PHP"}, {"name": "Unique.php", "size": 8, "language": "PHP"}]}]}, {"name": "Exception", "children": [{"name": "FileNotFoundException.php", "size": 9, "language": "PHP"}, {"name": "BadMethodCallException.php", "size": 5, "language": "PHP"}, {"name": "ClassNotFoundException.php", "size": 5, "language": "PHP"}, {"name": "RuntimeException.php", "size": 5, "language": "PHP"}, {"name": "PropelException.php", "size": 5, "language": "PHP"}, {"name": "LogicException.php", "size": 5, "language": "PHP"}, {"name": "InvalidArgumentException.php", "size": 5, "language": "PHP"}, {"name": "ExceptionInterface.php", "size": 5, "language": "PHP"}, {"name": "UnexpectedValueException.php", "size": 5, "language": "PHP"}]}]}, {"name": "Common", "children": [{"name": "Pluralizer", "children": [{"name": "StandardEnglishPluralizer.php", "size": 102, "language": "PHP"}, {"name": "SimpleEnglishPluralizer.php", "size": 9, "language": "PHP"}, {"name": "PluralizerInterface.php", "size": 6, "language": "PHP"}]}]}]}]}, {"name": "tests", "children": [{"name": "Propel", "children": [{"name": "Tests", "children": [{"name": "Runtime", "children": [{"name": "ActiveQuery", "children": [{"name": "ModelCriteriaTest.php", "size": 2319, "language": "PHP"}, {"name": "CriteriaTest.php", "size": 854, "language": "PHP"}, {"name": "CriteriaMergeTest.php", "size": 401, "language": "PHP"}, {"name": "ModelCriteriaSelectTest.php", "size": 358, "language": "PHP"}, {"name": "CriteriaFluidConditionTest.php", "size": 307, "language": "PHP"}, {"name": "CriteriaCombineTest.php", "size": 283, "language": "PHP"}, {"name": "SubQueryTest.php", "size": 239, "language": "PHP"}, {"name": "ModelWithTest.php", "size": 210, "language": "PHP"}, {"name": "ModelCriteriaHooksTest.php", "size": 151, "language": "PHP"}, {"name": "JoinTest.php", "size": 142, "language": "PHP"}, {"name": "Criterion", "children": [{"name": "InCriterionTest.php", "size": 131, "language": "PHP"}, {"name": "InModelCriterionTest.php", "size": 115, "language": "PHP"}, {"name": "LikeCriterionTest.php", "size": 103, "language": "PHP"}, {"name": "BasicCriterionTest.php", "size": 89, "language": "PHP"}, {"name": "LikeModelCriterionTest.php", "size": 71, "language": "PHP"}, {"name": "RawModelCriterionTest.php", "size": 40, "language": "PHP"}, {"name": "RawCriterionTest.php", "size": 40, "language": "PHP"}, {"name": "BasicModelCriterionTest.php", "size": 36, "language": "PHP"}, {"name": "SeveralModelCriterionTest.php", "size": 35, "language": "PHP"}, {"name": "CustomCriterionTest.php", "size": 18, "language": "PHP"}]}, {"name": "PropelQueryTest.php", "size": 104, "language": "PHP"}, {"name": "CriteriaFluidOperatorTest.php", "size": 84, "language": "PHP"}, {"name": "ModelJoinTest.php", "size": 68, "language": "PHP"}, {"name": "ModelCriteriaWithSchemaTest.php", "size": 49, "language": "PHP"}, {"name": "ModelCriteriaWithNamespaceTest.php", "size": 43, "language": "PHP"}, {"name": "ModelCriteriaForUseQuery.php", "size": 17, "language": "PHP"}, {"name": "TestableModelCriteria.php", "size": 11, "language": "PHP"}]}, {"name": "Formatter", "children": [{"name": "ObjectFormatterWithTest.php", "size": 555, "language": "PHP"}, {"name": "ArrayFormatterWithTest.php", "size": 423, "language": "PHP"}, {"name": "OnDemandFormatterWithTest.php", "size": 300, "language": "PHP"}, {"name": "OnDemandFormatterTest.php", "size": 162, "language": "PHP"}, {"name": "ObjectFormatterTest.php", "size": 107, "language": "PHP"}, {"name": "ArrayFormatterTest.php", "size": 99, "language": "PHP"}, {"name": "StatementFormatterTest.php", "size": 97, "language": "PHP"}, {"name": "SimpleArrayFormatterTest.php", "size": 56, "language": "PHP"}, {"name": "ObjectFormatterInheritanceTest.php", "size": 44, "language": "PHP"}]}, {"name": "Connection", "children": [{"name": "PropelPDOTest.php", "size": 377, "language": "PHP"}, {"name": "ConnectionManagerMasterSlaveTest.php", "size": 120, "language": "PHP"}, {"name": "ConnectionFactoryTest.php", "size": 58, "language": "PHP"}, {"name": "ConnectionManagerSingleTest.php", "size": 58, "language": "PHP"}]}, {"name": "ServiceContainer", "children": [{"name": "StandardServiceContainerTest.php", "size": 369, "language": "PHP"}]}, {"name": "Collection", "children": [{"name": "CollectionTest.php", "size": 347, "language": "PHP"}, {"name": "ObjectCollectionWithFixturesTest.php", "size": 219, "language": "PHP"}, {"name": "ArrayCollectionTest.php", "size": 190, "language": "PHP"}, {"name": "CollectionConvertTest.php", "size": 168, "language": "PHP"}, {"name": "ObjectCollectionTest.php", "size": 124, "language": "PHP"}, {"name": "OnDemandCollectionTest.php", "size": 61, "language": "PHP"}, {"name": "OnDemandIteratorTest.php", "size": 43, "language": "PHP"}]}, {"name": "Util", "children": [{"name": "BasePeerTest.php", "size": 284, "language": "PHP"}, {"name": "PropelModelPagerTest.php", "size": 217, "language": "PHP"}, {"name": "ProfilerTest.php", "size": 203, "language": "PHP"}, {"name": "PropelDateTimeTest.php", "size": 122, "language": "PHP"}, {"name": "BasePeerExceptionsTest.php", "size": 77, "language": "PHP"}, {"name": "PropelConditionalProxyTest.php", "size": 69, "language": "PHP"}]}, {"name": "Map", "children": [{"name": "TableMapTest.php", "size": 213, "language": "PHP"}, {"name": "DatabaseMapTest.php", "size": 134, "language": "PHP"}, {"name": "ColumnMapTest.php", "size": 123, "language": "PHP"}, {"name": "RelationMapTest.php", "size": 66, "language": "PHP"}, {"name": "GeneratedRelationMapWithSchemasTest.php", "size": 53, "language": "PHP"}, {"name": "RelatedMapSymmetricalWithSchemasTest.php", "size": 53, "language": "PHP"}, {"name": "GeneratedRelationMapTest.php", "size": 51, "language": "PHP"}, {"name": "RelatedMapSymmetricalTest.php", "size": 49, "language": "PHP"}]}, {"name": "ActiveRecordConvertTest.php", "size": 169, "language": "PHP"}, {"name": "Parser", "children": [{"name": "XmlParserTest.php", "size": 154, "language": "PHP"}, {"name": "YamlParserTest.php", "size": 130, "language": "PHP"}, {"name": "CsvParserTest.php", "size": 70, "language": "PHP"}, {"name": "JsonParserTest.php", "size": 64, "language": "PHP"}, {"name": "AbstractParserTest.php", "size": 40, "language": "PHP"}, {"name": "fixtures", "children": [{"name": "test_data.xml", "size": 6, "language": "XML"}]}]}, {"name": "Adapter", "children": [{"name": "DBAdapterTest.php", "size": 50, "language": "PHP"}, {"name": "Pdo", "children": [{"name": "OracleAdapterTest.php", "size": 63, "language": "PHP"}, {"name": "MysqlAdapterTest.php", "size": 60, "language": "PHP"}, {"name": "PgsqlAdapter.php", "size": 13, "language": "PHP"}]}]}, {"name": "ActiveRecordSerializeTest.php", "size": 78, "language": "PHP"}, {"name": "ActiveRecordTest.php", "size": 44, "language": "PHP"}, {"name": "Exception", "children": [{"name": "PropelExceptionTest.php", "size": 30, "language": "PHP"}]}, {"name": "PropelTest.php", "size": 27, "language": "PHP"}]}, {"name": "Generator", "children": [{"name": "Behavior", "children": [{"name": "NestedSet", "children": [{"name": "NestedSetBehaviorObjectBuilderModifierTest.php", "size": 1052, "language": "PHP"}, {"name": "NestedSetBehaviorObjectBuilderModifierWithScopeTest.php", "size": 479, "language": "PHP"}, {"name": "NestedSetBehaviorQueryBuilderModifierTest.php", "size": 475, "language": "PHP"}, {"name": "NestedSetBehaviorQueryBuilderModifierWithScopeTest.php", "size": 304, "language": "PHP"}, {"name": "TestCase.php", "size": 118, "language": "PHP"}, {"name": "NestedSetBehaviorTest.php", "size": 27, "language": "PHP"}, {"name": "Fixtures", "children": [{"name": "PublicTable9.php", "size": 11, "language": "PHP"}, {"name": "PublicTable10.php", "size": 7, "language": "PHP"}]}]}, {"name": "Versionable", "children": [{"name": "VersionableBehaviorObjectBuilderModifierTest.php", "size": 756, "language": "PHP"}, {"name": "VersionableBehaviorTest.php", "size": 374, "language": "PHP"}, {"name": "VersionableBehaviorQueryBuilderModifierTest.php", "size": 29, "language": "PHP"}, {"name": "TestCase.php", "size": 6, "language": "PHP"}]}, {"name": "I18n", "children": [{"name": "I18nBehaviorObjectBuilderModifierTest.php", "size": 361, "language": "PHP"}, {"name": "I18nBehaviorTest.php", "size": 247, "language": "PHP"}, {"name": "I18nBehaviorQueryBuilderModifierTest.php", "size": 232, "language": "PHP"}]}, {"name": "Archivable", "children": [{"name": "ArchivableBehaviorObjectBuilderModifierTest.php", "size": 332, "language": "PHP"}, {"name": "ArchivableBehaviorQueryBuilderModifierTest.php", "size": 242, "language": "PHP"}, {"name": "ArchivableBehaviorTest.php", "size": 156, "language": "PHP"}, {"name": "FooArchive.php", "size": 22, "language": "PHP"}, {"name": "FooArchiveQuery.php", "size": 21, "language": "PHP"}, {"name": "FooArchiveCollection.php", "size": 13, "language": "PHP"}]}, {"name": "Validate", "children": [{"name": "ValidateBehaviorTest.php", "size": 326, "language": "PHP"}, {"name": "I18nConcreteInheritanceHandleValidateBehaviorTest.php", "size": 99, "language": "PHP"}, {"name": "UniqueConstraintTest.php", "size": 46, "language": "PHP"}]}, {"name": "Sluggable", "children": [{"name": "SluggableBehaviorTest.php", "size": 294, "language": "PHP"}]}, {"name": "ConcreteInheritance", "children": [{"name": "ConcreteInheritanceBehaviorTest.php", "size": 274, "language": "PHP"}, {"name": "ConcreteInheritanceParentBehaviorTest.php", "size": 37, "language": "PHP"}, {"name": "ConcreteInheritanceBehaviorWithSchemaTest.php", "size": 23, "language": "PHP"}]}, {"name": "Sortable", "children": [{"name": "SortableBehaviorObjectBuilderModifierWithScopeTest.php", "size": 270, "language": "PHP"}, {"name": "SortableBehaviorObjectBuilderModifierTest.php", "size": 223, "language": "PHP"}, {"name": "TestCase.php", "size": 112, "language": "PHP"}, {"name": "SortableBehaviorQueryBuilderModifierWithScopeTest.php", "size": 98, "language": "PHP"}, {"name": "SortableBehaviorPeerBuilderModifierWithScopeTest.php", "size": 90, "language": "PHP"}, {"name": "SortableBehaviorQueryBuilderModifierTest.php", "size": 82, "language": "PHP"}, {"name": "SortableBehaviorPeerBuilderModifierTest.php", "size": 60, "language": "PHP"}, {"name": "SortableBehaviorTest.php", "size": 14, "language": "PHP"}]}, {"name": "AggregateColumn", "children": [{"name": "AggregateColumnBehaviorTest.php", "size": 225, "language": "PHP"}, {"name": "AggregateColumnBehaviorWithSchemaTest.php", "size": 66, "language": "PHP"}]}, {"name": "Delegate", "children": [{"name": "DelegateBehaviorTest.php", "size": 224, "language": "PHP"}]}, {"name": "Timestampable", "children": [{"name": "TimestampableBehaviorTest.php", "size": 186, "language": "PHP"}]}, {"name": "ObjectBehaviorTest.php", "size": 138, "language": "PHP"}, {"name": "PeerBehaviorTest.php", "size": 61, "language": "PHP"}, {"name": "AutoAddPk", "children": [{"name": "AutoAddPkBehaviorTest.php", "size": 57, "language": "PHP"}]}, {"name": "TableBehaviorTest.php", "size": 15, "language": "PHP"}, {"name": "AddClass", "children": [{"name": "AddClassBehaviorTest.php", "size": 12, "language": "PHP"}]}]}, {"name": "Builder", "children": [{"name": "Om", "children": [{"name": "GeneratedObjectTest.php", "size": 942, "language": "PHP"}, {"name": "QueryBuilderTest.php", "size": 916, "language": "PHP"}, {"name": "GeneratedObjectRelTest.php", "size": 507, "language": "PHP"}, {"name": "GeneratedPeerDoSelectTest.php", "size": 311, "language": "PHP"}, {"name": "GeneratedPeerDoDeleteTest.php", "size": 290, "language": "PHP"}, {"name": "GeneratedObjectWithFixturesTest.php", "size": 248, "language": "PHP"}, {"name": "GeneratedQueryArrayColumnTypeTest.php", "size": 201, "language": "PHP"}, {"name": "GeneratedObjectArrayColumnTypeTest.php", "size": 196, "language": "PHP"}, {"name": "GeneratedObjectLobTest.php", "size": 160, "language": "PHP"}, {"name": "GeneratedObjectTemporalColumnTypeTest.php", "size": 151, "language": "PHP"}, {"name": "QueryBuilderInheritanceTest.php", "size": 137, "language": "PHP"}, {"name": "TableMapBuilderTest.php", "size": 133, "language": "PHP"}, {"name": "AbstractOMBuilderNamespaceTest.php", "size": 130, "language": "PHP"}, {"name": "GeneratedObjectEnumColumnTypeTest.php", "size": 106, "language": "PHP"}, {"name": "AbstractOMBuilderTest.php", "size": 91, "language": "PHP"}, {"name": "GeneratedPeerLazyLoadTest.php", "size": 87, "language": "PHP"}, {"name": "GeneratedQueryEnumColumnTypeTest.php", "size": 75, "language": "PHP"}, {"name": "GeneratedPeerTest.php", "size": 75, "language": "PHP"}, {"name": "GeneratedQueryObjectColumnTypeTest.php", "size": 74, "language": "PHP"}, {"name": "GeneratedObjectObjectColumnTypeTest.php", "size": 66, "language": "PHP"}, {"name": "GeneratedObjectBooleanColumnTypeTest.php", "size": 63, "language": "PHP"}, {"name": "GeneratedObjectLazyLoadTest.php", "size": 62, "language": "PHP"}, {"name": "AbstractOMBuilderRelatedByTest.php", "size": 57, "language": "PHP"}, {"name": "ObjectBuilderTest.php", "size": 53, "language": "PHP"}, {"name": "GeneratedPeerEnumColumnTypeTest.php", "size": 48, "language": "PHP"}, {"name": "GeneratedObjectWithInterfaceTest.php", "size": 24, "language": "PHP"}, {"name": "Fixtures", "children": [{"name": "ComplexColumnTypeEntityWithConstructor.php", "size": 12, "language": "PHP"}]}]}, {"name": "NamespaceTest.php", "size": 227, "language": "PHP"}, {"name": "Util", "children": [{"name": "SchemaReaderTest.php", "size": 90, "language": "PHP"}, {"name": "PropelTemplateTest.php", "size": 35, "language": "PHP"}, {"name": "outerSchema.xml", "size": 7, "language": "XML"}, {"name": "innerSchema.xml", "size": 6, "language": "XML"}, {"name": "testSchema.xml", "size": 6, "language": "XML"}, {"name": "template.php", "size": 1, "language": "PHP"}]}]}, {"name": "Model", "children": [{"name": "ColumnTest.php", "size": 807, "language": "PHP"}, {"name": "TableTest.php", "size": 693, "language": "PHP"}, {"name": "ForeignKeyTest.php", "size": 353, "language": "PHP"}, {"name": "DatabaseTest.php", "size": 336, "language": "PHP"}, {"name": "Diff", "children": [{"name": "PropelDatabaseTableComparatorTest.php", "size": 322, "language": "PHP"}, {"name": "PropelTableColumnComparatorTest.php", "size": 197, "language": "PHP"}, {"name": "PropelTableIndexComparatorTest.php", "size": 172, "language": "PHP"}, {"name": "ColumnComparatorTest.php", "size": 160, "language": "PHP"}, {"name": "PropelTablePkColumnComparatorTest.php", "size": 157, "language": "PHP"}, {"name": "ForeignKeyComparatorTest.php", "size": 145, "language": "PHP"}, {"name": "PropelTableForeignKeyComparatorTest.php", "size": 142, "language": "PHP"}, {"name": "IndexComparatorTest.php", "size": 72, "language": "PHP"}]}, {"name": "ModelTestCase.php", "size": 309, "language": "PHP"}, {"name": "SchemaTest.php", "size": 173, "language": "PHP"}, {"name": "DomainTest.php", "size": 168, "language": "PHP"}, {"name": "BehaviorTest.php", "size": 136, "language": "PHP"}, {"name": "IndexTest.php", "size": 116, "language": "PHP"}, {"name": "NameFactoryTest.php", "size": 77, "language": "PHP"}, {"name": "PhpNameGeneratorTest.php", "size": 52, "language": "PHP"}, {"name": "VendorInfoTest.php", "size": 46, "language": "PHP"}, {"name": "InheritanceTest.php", "size": 39, "language": "PHP"}, {"name": "MappingModelTest.php", "size": 37, "language": "PHP"}, {"name": "UniqueTest.php", "size": 29, "language": "PHP"}, {"name": "ColumnDefaultValueTest.php", "size": 23, "language": "PHP"}]}, {"name": "Platform", "children": [{"name": "MysqlPlatformTest.php", "size": 562, "language": "PHP"}, {"name": "PgsqlPlatformTest.php", "size": 532, "language": "PHP"}, {"name": "PlatformMigrationTestProvider.php", "size": 511, "language": "PHP"}, {"name": "MssqlPlatformTest.php", "size": 499, "language": "PHP"}, {"name": "OraclePlatformTest.php", "size": 441, "language": "PHP"}, {"name": "OraclePlatformMigrationTest.php", "size": 335, "language": "PHP"}, {"name": "PlatformTestProvider.php", "size": 278, "language": "PHP"}, {"name": "SqlitePlatformTest.php", "size": 272, "language": "PHP"}, {"name": "PgsqlPlatformMigrationTest.php", "size": 247, "language": "PHP"}, {"name": "MysqlPlatformMigrationTest.php", "size": 174, "language": "PHP"}, {"name": "DefaultPlatformTest.php", "size": 107, "language": "PHP"}, {"name": "PlatformTestBase.php", "size": 17, "language": "PHP"}]}, {"name": "Config", "children": [{"name": "XmlToArrayConverterTest.php", "size": 286, "language": "PHP"}, {"name": "ArrayToPhpConverterTest.php", "size": 273, "language": "PHP"}, {"name": "GeneratorConfigTest.php", "size": 38, "language": "PHP"}]}, {"name": "Util", "children": [{"name": "PhpParserTest.php", "size": 161, "language": "PHP"}, {"name": "SchemaValidatorTest.php", "size": 127, "language": "PHP"}, {"name": "QuickBuilderTest.php", "size": 105, "language": "PHP"}, {"name": "SqlParserTest.php", "size": 67, "language": "PHP"}]}, {"name": "Reverse", "children": [{"name": "PgsqlSchemaParserTest.php", "size": 54, "language": "PHP"}, {"name": "MssqlSchemaParserTest.php", "size": 38, "language": "PHP"}, {"name": "MysqlSchemaParserTest.php", "size": 34, "language": "PHP"}]}, {"name": "Command", "children": [{"name": "AbstractCommandTest.php", "size": 29, "language": "PHP"}]}]}, {"name": "BookstoreTest.php", "size": 482, "language": "PHP"}, {"name": "FieldnameRelatedTest.php", "size": 315, "language": "PHP"}, {"name": "Helpers", "children": [{"name": "Bookstore", "children": [{"name": "BookstoreDataPopulator.php", "size": 278, "language": "PHP"}, {"name": "Behavior", "children": [{"name": "Testallhooksbehavior.php", "size": 144, "language": "PHP"}, {"name": "TestAuthor.php", "size": 51, "language": "PHP"}, {"name": "AddClassBehaviorBuilder.php", "size": 34, "language": "PHP"}, {"name": "TestAuthorSaveFalse.php", "size": 12, "language": "PHP"}, {"name": "TestAuthorDeleteFalse.php", "size": 12, "language": "PHP"}, {"name": "AddClassBehavior.php", "size": 7, "language": "PHP"}, {"name": "DonothingBehavior.php", "size": 6, "language": "PHP"}]}, {"name": "BookstoreTestBase.php", "size": 38, "language": "PHP"}, {"name": "BookstoreEmptyTestBase.php", "size": 10, "language": "PHP"}]}, {"name": "Schemas", "children": [{"name": "SchemasTestBase.php", "size": 26, "language": "PHP"}]}, {"name": "Namespaces", "children": [{"name": "NamespacesTestBase.php", "size": 16, "language": "PHP"}]}, {"name": "SchemaPlatform.php", "size": 9, "language": "PHP"}, {"name": "NoSchemaPlatform.php", "size": 9, "language": "PHP"}, {"name": "BaseTestCase.php", "size": 6, "language": "PHP"}]}, {"name": "Ticket520Test.php", "size": 170, "language": "PHP"}, {"name": "Common", "children": [{"name": "Pluralizer", "children": [{"name": "StandardEnglishPluralizerTest.php", "size": 83, "language": "PHP"}]}]}, {"name": "CharacterEncodingTest.php", "size": 68, "language": "PHP"}, {"name": "TestCase.php", "size": 5, "language": "PHP"}]}]}, {"name": "Fixtures", "children": [{"name": "etc", "children": [{"name": "xsl", "children": [{"name": "coverage-frames.xsl", "size": 523, "language": "XSLT"}, {"name": "phpunit-noframes.xsl", "size": 338, "language": "XSLT"}, {"name": "log.xsl", "size": 176, "language": "XSLT"}, {"name": "str.replace.function.xsl", "size": 50, "language": "XSLT"}]}]}, {"name": "bookstore", "children": [{"name": "schema.xml", "size": 316, "language": "XML"}, {"name": "behavior-validate-schema.xml", "size": 78, "language": "XML"}, {"name": "behavior-concrete-inheritance-schema.xml", "size": 55, "language": "XML"}, {"name": "behavior-aggregate-schema.xml", "size": 34, "language": "XML"}, {"name": "behavior-validate-triggers-schema.xml", "size": 34, "language": "XML"}, {"name": "behavior-sluggable-schema.xml", "size": 28, "language": "XML"}, {"name": "behavior-auto-add-pk-schema.xml", "size": 24, "language": "XML"}, {"name": "behavior-timestampable-schema.xml", "size": 18, "language": "XML"}, {"name": "behavior-schema.xml", "size": 11, "language": "XML"}, {"name": "behavior-add-class-schema.xml", "size": 8, "language": "XML"}]}, {"name": "schemas", "children": [{"name": "schema.xml", "size": 85, "language": "XML"}]}, {"name": "namespaced", "children": [{"name": "schema.xml", "size": 61, "language": "XML"}]}, {"name": "bookstore-packaged", "children": [{"name": "book.schema.xml", "size": 41, "language": "XML"}, {"name": "review.schema.xml", "size": 39, "language": "XML"}, {"name": "book_club_list.schema.xml", "size": 37, "language": "XML"}, {"name": "log.schema.xml", "size": 28, "language": "XML"}, {"name": "external", "children": [{"name": "author.schema.xml", "size": 28, "language": "XML"}]}, {"name": "media.schema.xml", "size": 27, "language": "XML"}, {"name": "publisher.schema.xml", "size": 14, "language": "XML"}]}, {"name": "generator", "children": [{"name": "config", "children": [{"name": "FoobarWithNS.php", "size": 5, "language": "PHP"}, {"name": "Foobar.php", "size": 4, "language": "PHP"}]}]}]}, {"name": "bootstrap.php", "size": 2, "language": "PHP"}]}, {"name": "resources", "children": [{"name": "xsd", "children": [{"name": "database.xsd", "size": 802, "language": "XSD"}, {"name": "custom_datatypes.xsd", "size": 7, "language": "XSD"}]}, {"name": "xsl", "children": [{"name": "dbd2propel.xsl", "size": 245, "language": "XSLT"}, {"name": "database.xsl", "size": 172, "language": "XSLT"}]}, {"name": "dtd", "children": [{"name": "database.dtd", "size": 131, "language": "DTD"}]}]}, {"name": "documentation", "children": [{"name": "css", "children": [{"name": "layout.css", "size": 264, "language": "CSS"}, {"name": "markdown.css", "size": 101, "language": "CSS"}, {"name": "base_syntax.css", "size": 68, "language": "CSS"}, {"name": "mobile.css", "size": 49, "language": "CSS"}, {"name": "syntax.css", "size": 17, "language": "CSS"}]}, {"name": "_layouts", "children": [{"name": "home.html", "size": 34, "language": "HTML"}, {"name": "base.html", "size": 32, "language": "HTML"}, {"name": "documentation.html", "size": 12, "language": "HTML"}, {"name": "default.html", "size": 6, "language": "HTML"}]}, {"name": "_includes", "children": [{"name": "footer.html", "size": 21, "language": "HTML"}, {"name": "navbar.html", "size": 17, "language": "HTML"}, {"name": "header.html", "size": 9, "language": "HTML"}]}, {"name": "js", "children": [{"name": "ga.js", "size": 8, "language": "Javascript"}, {"name": "jquery.tableofcontents.min.js", "size": 1, "language": "Javascript"}]}, {"name": "404.html", "size": 6, "language": "HTML"}, {"name": "_config.yml", "size": 2, "language": "YAML"}]}, {"name": "features", "children": [{"name": "bootstrap", "children": [{"name": "FeatureContext.php", "size": 40, "language": "PHP"}]}]}, {"name": "bin", "children": [{"name": "propel.php", "size": 22, "language": "PHP"}, {"name": "propel.bat", "size": 8, "language": "DOS Batch"}]}, {"name": ".travis.yml", "size": 13, "language": "YAML"}]};
