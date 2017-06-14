(function () {
bindUpload({
            "id": 'newPic',
            finish: function (file) {
                var newUploadId = getRandomId(10);
                var picSizeNow = $("div[name='detail_pic_group']").size();
                var txt = '<div class="form-group" name="detail_pic_group" id="' + newUploadId + '"><label class="col-sm-2 control-label"></label><div class="m-r col-sm-2">' +
                    '<img src="' + file + '" name="ImgDetailPic" class="r r-2x" width="100%" height="100px" id="img' + newUploadId + '" ></div><div class="m-r col-sm-3">' +
                    '<br><button class="btn btn-danger" onclick="removePic(\'' + newUploadId + '\')"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;&nbsp;<span>删除图片</span></button>' +
                    '<div class="radio i-checks"><label><input type="radio" name="detailpic" ' + (0 == picSizeNow ? "checked" : "") + ' value="' + newUploadId + '" ><i></i>设置为列表展示图</label></div></div></div>';
                $('#productDetailPicDiv').append(txt);
            }
});
function getRandomId(len) {
    len = len || 32;
    var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
    var maxPos = chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return (Date.parse(new Date()) / 1000) + pwd;
}

function removePic(index) {
    $("#" + index).remove();
}
});