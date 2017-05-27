/**
 * Created by trancy on 2017/5/26.
 */
function Mask() {
}
Mask.prototype = {
    show: function (obj) {
        $("#mask").show();
        obj.show();
    },
    close: function (obj) {
        $("#mask").hide();
        obj.hide();
    },
}