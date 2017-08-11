/**
 * Common JS
 * Created by xiati on 2017/5/8.
 */

var Common = {

    /**
     * Common validate
     */
    validate : {
        isNotEnpty : function(str){

            return str!=null && str!='' && str.length > 0 && str != 'undefined';
        }
    },
    /**
     * Toast
     * @param type type:success，error，warn，info
     * @param message
     */
    toast : function(type, message){
        $("body").overhang({
            type: type,
            message: message,
            duration:1
        });
    },
    confirm : function(message, callback){
        $("body").overhang({
            type: "confirm",
            primary: "#40D47E",
            accent: "#27AE60",
            yesMessage: '确定',
            noMessage: '取消',
            yesColor: "#3498DB",
            message: message,
            callback: function () {
                var selection = $("body").data("overhangConfirm");
                if(selection){
                    callback();
                }
            }
        });
    },
    /**
     * 刷新验证码
     */
    reloadValiCode : function(){
        var time = new Date().getTime();
        document.getElementById("imgcheckCode").src = "/imgCodeServlet?time=" + time;
    }

};