var context = '<div class="panel-body" id="step1">' +
    '<div class="form-horizontal">' +
    '<input type="hidden" name="houseId"' +
    'id="houseId">' +
    '<div class="form-group">' +
    '<label class="col-sm-2 control-label">房源名称</label>' +
    '<div class="m-r col-sm-3">' +
    '<input class="form-control" name="subject"' +
    'id="subject"/>' +
    '</div>' +
    '</div>' +
    '<div class="line line-dashed b-b line-lg pull-in"></div>' +
    '<div class="form-group">' +
    '<label class="col-sm-2 control-label">房源简介</label>' +
    '<div class="m-r col-sm-3">' +
    '<textarea rows="4" cols="20" class="form-control" id="summary" name="summary"></textarea>' +
    '' +
    '</div>' +
    '</div>' +
    '<div class="line line-dashed b-b line-lg pull-in"></div>' +
    '<div class="form-group">' +
    '<label class="col-sm-2 control-label">房源细节描述</label>' +
    '<div class="m-r col-sm-3">' +
    '<textarea rows="4" cols="20" class="form-control"' +
    'id="summary" name="summary"></textarea>' +
    '</div>' +
    '</div>' +
    '<div class="line line-dashed b-b line-lg pull-in"></div>' +
    '<div class="form-group">' +
    '<label class="col-sm-2 control-label">房源图片</label>' +
    '</div>' +
    '<div id="houseDetailPicDiv"></div>' +
    '<div class="form-group">' +
    '<label class="col-sm-2 control-label"></label>' +
    '<div class="m-r col-sm-2">' +
    '<a class="btn btn-info" href="javascript:void(0)" id="newPic"><i' +
    'class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;新增图片</a>' +
    '</div>' +
    '</div>' +
    '<div class="line line-dashed b-b line-lg pull-in"></div>' +
    '<div class="form-group">' +
    '<label class="col-sm-2 control-label">房源类型</label>' +
    '<div class="m-r col-sm-3">' +
    '<div class="col-sm-5 input-group m-b">' +
    '<div class="radio i-checks"><label><input type="radio" name="recipientType" value="1" checked><i></i>一室</label></div>' +
    '<div class="radio i-checks"><label><input type="radio" name="recipientType" value="2"><i></i>两室</label></div>' +
    '<div class="radio i-checks"><label><input type="radio" name="recipientType" value="3"><i></i>三室</label></div>' +
    '<div class="radio i-checks"><label><input type="radio" name="recipientType" value="4"> <i></i>四室</label></div>' +
    '</div>' +
    '</div>' +
    '</div>' +
    '<div class="line line-dashed b-b line-lg pull-in"></div>' +
    '<div class="form-group">' +
    '<label class="col-sm-2 control-label">房源户型</label>' +
    '<div class="col-sm-3">' +
    '<div class="input-group m-b">' +
    '<input class="form-control" name="room" value="1"><span class="input-group-addon">室</span>' +
    '<input class="form-control" name="hall" value="0"><span class="input-group-addon">厅</span>' +
    '<input class="form-control" name="toilet" value="0"><span class="input-group-addon">卫</span>' +
    '</div>' +
    '</div>' +
    '</div>' +
    '<div class="line line-dashed b-b line-lg pull-in"></div>' +
    '<div class="form-group">' +
    '<label class="col-sm-2 control-label" >面积</label>' +
    '<div class="m-r col-sm-3">' +
    '<div class="input-group m-b col-sm-3">' +
    '<input class="form-control" name="roomArea" value="1"><span class="input-group-addon">㎡</span>' +
    '</div>' +
    '</div>' +
    '</div>' +
    '<div class="line line-dashed b-b line-lg pull-in"></div>' +
    '<div class="form-group">' +
    '<label class="col-sm-2 control-label" >租金</label>' +
    '<div class="m-r col-sm-3">' +
    '<div class="input-group m-b col-sm-3">' +
    '<input class="form-control" name="roomRent" value="1"><span class="input-group-addon">分</span>' +
    '</div>' +
    '</div>' +
    '</div>' +
    '<div class="line line-dashed b-b line-lg pull-in"></div>' +
    '<div class="form-group form-inline">' +
    '<label class="col-sm-2 control-label">支付模式</label>' +
    '<div class="col-sm-4">' +
    '<div class="input-group m-b col-sm-3">' +
    '<span class="input-group-addon">压</span><input class="form-control" name="mortagageNum" value="3">\t<span class="input-group-addon">月</span>' +
    '</div>' +
    '<div class="input-group m-b col-sm-3">' +
    '<span class="input-group-addon">付</span><input class="form-control" name="payMentNum" value="4"><span class="input-group-addon">月</span>' +
    '</div>' +
    '</div>' +
    '</div>' +
    '<div class="line line-dashed b-b line-lg pull-in"></div>' +
    '<div class="form-group form-inline">' +
    '<label class="col-sm-2 control-label">房源状态</label>' +
    '<div class="col-sm-10">' +
    '' +
    '</div>' +
    '</div>' +
    '<div class="line line-dashed b-b line-lg pull-in"></div>' +
    '<div class="form-group">' +
    '<label class="col-sm-2 control-label">租赁周期</label>' +
    '<div class="m-r col-sm-3">' +
    '<input name="position" class="form-control"' +
    'id="position"/>' +
    '</div>' +
    '</div>' +
    '<div class="line line-dashed b-b line-lg pull-in"></div>' +
    '<div class="form-group">' +
    '<div class="col-sm-4 col-sm-offset-2">' +
    '<button type="reset" class="btn btn-default">重置</button>' +
    '<button type="button" class="btn btn-primary" id="btn_submit">' +
    '保存' +
    '</button>' +
    '</div>' +
    '</div>' +
    '</div>' +
    '</div>';

function wrapper(houseId, editAble) {
    zeroModal.show({
        title: '房源详情'
        content
        url: $("#netRulesUrl").val(),
        width: '40%',
        height: '60%',
        esc: true,
        cancel: true
    });
}
function requestData(houseId) {
    var data = {};
    data.houseId = houseId;
    $.ajax({
        type: "POST",
        async: true,
        url: "/api/house/list",
        contentType: "application/json",
        data: JSON.stringify(data),
        dataType: "json",
        success: function (ret) {
            $.each(ret['result'], function (item, val) {
                buildHouseTr(val);
            });
        },
        error: function (message) {
        }
    });
}