/**
 * Created by trancy on 2017/5/26.
 */
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