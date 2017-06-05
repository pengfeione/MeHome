jQuery.fn.areaSelector = function (argument, idHidden) {
    var areaData; //默认地区信息
    var thisId = $(this).attr("id");
    var inputId = idHidden;
    var contentId = thisId + 'AreaContent';
    var menuId = thisId + 'AreaMenu';
    var regionCode;
    var regionId;
    var cityCode;
    var cityId;
    var theRegion;
    var theId;


    var theCity;
    if (argument) {
        areaData = argument;
    } else {
        console.error('未传入区域参数');
    }
    //input框点击事件
    $(this).on('click', function () {

        $("#" + contentId).toggleClass('isBlock');
    })
    setCoordinate();
    createProvince();

    //设置html位置
    function setCoordinate() {
        var offsetX = $("#" + thisId).offset().top;
        var offsetY = $("#" + thisId).offset().left;
        console.log(offsetX + " : " + offsetY);
        $("#" + contentId).offset({
            top: offsetX - $("#" + thisId).parent().innerHeight() / 2,
            left: offsetY - $("#" + thisId).parent().innerWidth() / 2
        });
    }

    //切换页签
    $('#' + contentId + ' .area_menu ul li').on('click', function () {
        $(this).addClass('isClick').siblings().removeClass('isClick');
        var thisIndex = $(this).index() + 1;
        $('#' + contentId + ' .area_data_content div:nth-child(' + thisIndex + ')').addClass('isBlock').siblings().removeClass('isBlock');
    })

    /*选择操作响应*/
    function replyOperation() {

        $('#' + contentId + ' .data_province li').on('click', function () {
            regionCode = $(this).find('input').val();
            createRegion(regionCode);
            $('#' + contentId + ' .area_menu ul li:nth-child(2)').click();
            theRegion = $(this).children('a').text();
            $('#' + thisId).val(theRegion);
            regionId = $(this).children('a').children('input').val();
            $('#' + inputId).html(regionId);

        })

        $('#' + contentId + ' .data_region li').on('click', function () {
            cityCode = $(this).find('input').val();
            createCity(cityCode);
            $('#' + contentId + ' .area_menu ul li:nth-child(3)').click();
            theCity = $(this).children('a').text();
            $('#' + thisId).val(theRegion + ' / ' + theCity);
            cityId = $(this).children('a').children('input').val();
            $('#' + inputId).html(regionId + cityId);
        })

        $('#' + contentId + ' .data_city li').on('click', function () {
            $("#" + contentId).toggleClass('isBlock');
            $('#' + thisId).val(theRegion + ' / ' + theCity + ' / ' + $(this).children('a').text());
            $('#' + inputId).html(regionId + cityId + $(this).children('a').children('input').val());
        })
    }

    //创建省份
    function createProvince() {

        var provinceDomPartOne = '';
        var provinceDomPartTwo = '';
        var provinceDomPartThree = '';
        var provinceDomPartFour = '';
        for (var i = 0; i < areaData.length; i++) {
            var letter = makePy(areaData[i].region.name)[0].toUpperCase().charAt(0);
            switch (letter) {
                case "A":
                case "B":
                case "C":
                case "D":
                case "E":
                case "F":
                case "G":
                    provinceDomPartOne += '<li><a>' + areaData[i].region.name + '<input value=' + areaData[i].region.code + ' style="display:none"></a></li>';
                    break;

                case "H":
                case "I":
                case "J":
                case "K":
                case "L":
                case "M":
                case "N":
                    provinceDomPartTwo += '<li><a>' + areaData[i].region.name + '<input value=' + areaData[i].region.code + ' style="display:none"></a></li>';
                    break;

                case "O":
                case "P":
                case "Q":
                case "R":
                case "S":
                case "T":
                case "U":
                    provinceDomPartThree += '<li><a>' + areaData[i].region.name + '<input value=' + areaData[i].region.code + ' style="display:none"></a></li>';
                    break;

                case "V":
                case "W":
                case "X":
                case "Y":
                case "Z":
                    provinceDomPartFour += '<li><a>' + areaData[i].region.name + '<input value=' + areaData[i].region.code + ' style="display:none"></a></li>';
                    break;
            }
        }
        $('#' + contentId + ' .data_province .part_one ul').html(provinceDomPartOne);
        $('#' + contentId + ' .data_province .part_two ul').html(provinceDomPartTwo);
        $('#' + contentId + ' .data_province .part_three ul').html(provinceDomPartThree);
        $('#' + contentId + ' .data_province .part_four ul').html(provinceDomPartFour);
        replyOperation();
    }

    //创建市
    function createRegion(regionCode) {
        var regionDomPartOne = '';
        var regionDomPartTwo = '';
        var regionDomPartThree = '';
        var regionDomPartFour = '';

        for (var j = 0; j < areaData.length; j++) {
            if (areaData[j].region.code == regionCode) {
                for (var k = 0; k < areaData[j].region.state.length; k++) {
                    var letter = makePy(areaData[j].region.state[k].name)[0].toUpperCase().charAt(0);
                    switch (letter) {
                        case "A":
                        case "B":
                        case "C":
                        case "D":
                        case "E":
                        case "F":
                        case "G":
                            regionDomPartOne += '<li><a>' + areaData[j].region.state[k].name + '<input value="' + areaData[j].region.state[k].code + '" style="display:none"></a></li>';
                            break;

                        case "H":
                        case "I":
                        case "J":
                        case "K":
                        case "L":
                        case "M":
                        case "N":
                            regionDomPartTwo += '<li><a>' + areaData[j].region.state[k].name + '<input value="' + areaData[j].region.state[k].code + '" style="display:none"></a></li>';
                            break;

                        case "O":
                        case "P":
                        case "Q":
                        case "R":
                        case "S":
                        case "T":
                        case "U":
                            regionDomPartThree += '<li><a>' + areaData[j].region.state[k].name + '<input value="' + areaData[j].region.state[k].code + '" style="display:none"></a></li>';
                            break;

                        case "V":
                        case "W":
                        case "X":
                        case "Y":
                        case "Z":
                            regionDomPartFour += '<li><a>' + areaData[j].region.state[k].name + '<input value="' + areaData[j].region.state[k].code + '" style="display:none"></a></li>';
                            break;
                    }
                }
            }
        }

        $('#' + contentId + ' .data_region .part_one ul').html(regionDomPartOne);
        $('#' + contentId + ' .data_region .part_two ul').html(regionDomPartTwo);
        $('#' + contentId + ' .data_region .part_three ul').html(regionDomPartThree);
        $('#' + contentId + ' .data_region .part_four ul').html(regionDomPartFour);
        replyOperation();
    }

    //创建县区
    function createCity(cityCode) {
        var cityDomPartOne = '';
        var cityDomPartTwo = '';
        var cityDomPartThree = '';
        var cityDomPartFour = '';
        for (var j = 0; j < areaData.length; j++) {
            if (areaData[j].region.code == regionCode) {
                for (var k = 0; k < areaData[j].region.state.length; k++) {
                    if (areaData[j].region.state[k].code == cityCode) {
                        for (var l = 0; l < areaData[j].region.state[k].city.length; l++) {
                            var letter = makePy(areaData[j].region.state[k].city[l].name)[0].toUpperCase().charAt(0);
                            switch (letter) {
                                case "A":
                                case "B":
                                case "C":
                                case "D":
                                case "E":
                                case "F":
                                case "G":
                                    cityDomPartOne += '<li><a>' + areaData[j].region.state[k].city[l].name + '<input value="' + areaData[j].region.state[k].city[l].code + '" style="display:none"></a></li>';
                                    break;

                                case "H":
                                case "I":
                                case "J":
                                case "K":
                                case "L":
                                case "M":
                                case "N":
                                    cityDomPartTwo += '<li><a>' + areaData[j].region.state[k].city[l].name + '<input value="' + areaData[j].region.state[k].city[l].code + '" style="display:none"></a></li>';
                                    break;

                                case "O":
                                case "P":
                                case "Q":
                                case "R":
                                case "S":
                                case "T":
                                case "U":
                                    cityDomPartThree += '<li><a>' + areaData[j].region.state[k].city[l].name + '<input value="' + areaData[j].region.state[k].city[l].code + '" style="display:none"></a></li>';
                                    break;

                                case "V":
                                case "W":
                                case "X":
                                case "Y":
                                case "Z":
                                    cityDomPartFour += '<li><a>' + areaData[j].region.state[k].city[l].name + '<input value="' + areaData[j].region.state[k].city[l].code + '" style="display:none"></a></li>';
                                    break;
                            }
                        }
                    }
                }
            }
        }
        $('#' + contentId + ' .data_city .part_one ul').html(cityDomPartOne);
        $('#' + contentId + ' .data_city .part_two ul').html(cityDomPartTwo);
        $('#' + contentId + ' .data_city .part_three ul').html(cityDomPartThree);
        $('#' + contentId + ' .data_city .part_four ul').html(cityDomPartFour);
        replyOperation();
    }
}
