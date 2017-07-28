/**
 * Created by lgw on 2017/3/29.
 */
var  localhost = "localhost";
var port = 8080;
function changeTime(mills) {
    var timestamp = new Date(mills);
    return timestamp.getFullYear()+"-"+handleTime((timestamp.getMonth()+1))+"-"+handleTime(timestamp.getDate())+" "
        +handleTime(timestamp.getHours())+":"+handleTime(timestamp.getMinutes())+":"+handleTime(timestamp.getSeconds());
}
function handleTime(tt) {
    if(parseInt(tt)<10){
        tt = "0"+tt;
    }
    return tt;
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return r[2]; return null;
}

//写cookies
function setCookie(name,value)
{
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
//读取cokkies
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

//删除cookies
function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
//如果需要设定自定义过期时间
//那么把上面的setCookie　函数换成下面两个函数就ok;
//程序代码
// function setCookie(name,value,time)
// {
//     var strsec = getsec(time);
//     var exp = new Date();
//     exp.setTime(exp.getTime() + strsec*1);
//     document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
// }
// function getsec(str)
// {
//     alert(str);
//     var str1=str.substring(1,str.length)*1;
//     var str2=str.substring(0,1);
//     if (str2=="s")
//     {
//         return str1*1000;
//     }
//     else if (str2=="h")
//     {
//         return str1*60*60*1000;
//     }
//     else if (str2=="d")
//     {
//         return str1*24*60*60*1000;
//     }
// }
//这是有设定过期时间的使用示例：
//s20是代表20秒
//h是指小时，如12小时则是：h12
//d是天数，30天则：d30
function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
