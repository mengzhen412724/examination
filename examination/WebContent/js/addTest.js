/**
 * Created by lgw on 2017/3/27.
 */
var singleScores = 0;
var multiScores = 0;
var judgeScores = 0;
var questionScores = 0;
var fillScores = 0;
function checkthis(obj) {
    var content = $(obj).val();
    var lab = $(obj).parent().prev().html();
    if(content == ""){
        $(obj).next().html(lab+'不能为空');
        $("#save").attr('disabled',"true");
    }else{
        $(obj).next().html('');
        $("#save").removeAttr('disabled');
        var flag = $(".form-group").find("span");
        for(var i=0;i<flag.length;i++){
            if(flag[i].innerHTML!=""){
                $("#save").attr('disabled',"true");
            }
        }

        if(lab.indexOf('分数')>-1){
            var num = $(obj).parent().parent().prev().find("input").val();
            if(num ){
                if(lab.indexOf('单选')>-1){
                    singleScores = parseInt(content)*parseInt(num);
                }else if(lab.indexOf('多选')>-1){
                    multiScores = parseInt(content)*parseInt(num);
                }else if(lab.indexOf('判断')>-1){
                    judgeScores = parseInt(content)*parseInt(num);
                }else if(lab.indexOf('简答')>-1){
                    questionScores = parseInt(content)*parseInt(num);
                }else if(lab.indexOf('填空')>-1){
                    fillScores = parseInt(content)*parseInt(num);
                }
                var score = singleScores+multiScores+judgeScores+questionScores+fillScores;
                $("#total").val(score);
            }

        }else if(lab.indexOf('个数')>-1){
            var num = $(obj).parent().parent().next().find("input").val();
            if(num){
                if(lab.indexOf('单选')>-1){
                    singleScores = parseInt(content)*parseInt(num);
                }else if(lab.indexOf('多选')>-1){
                    multiScores = parseInt(content)*parseInt(num);
                }else if(lab.indexOf('判断')>-1){
                    judgeScores = parseInt(content)*parseInt(num);
                }else if(lab.indexOf('简答')>-1){
                    questionScores = parseInt(content)*parseInt(num);
                }else if(lab.indexOf('填空')>-1){
                    fillScores = parseInt(content)*parseInt(num);
                }
                var score = singleScores+multiScores+judgeScores+questionScores+fillScores;
                $("#total").val(score);
            }
        }
    }

}

function testSubmit(){
    var params= new Object();
    var start = $("#start").val();
    if(!start || start == ""){
        return;
    }else{
        params.examinationStartTime=start;
    }
    var end = $("#end").val();
    if(!end || end == ""){
        return;
    }else{
        params.examinationEndTime = end;
    }
    var testname = $("#testname1").val();
    if(!testname || testname == ""){
        return;
    }else{
        params.examinationName = testname;
    }
    var singlenum = $("#singlenum").val();
    if(!singlenum || singlenum == ""){
        return;
    }else{
        params.singleChoiceQuestionNum = parseInt(singlenum);
    }
    var singlescore = $("#singlescore").val();
    if(!singlescore || singlescore == ""){
        return;
    }else{
        params.singleChoiceQuestionScore = parseInt(singlescore);
    }
    var multiplenum = $("#multiplenum").val();
    if(!multiplenum || multiplenum == ""){
        return;
    }else{
        params.multipleChoiceQuestionNum = parseInt(multiplenum);
    }

    var multiplescore = $("#multiplescore").val();
    if(!multiplescore || multiplescore == ""){
        return;
    }else{
        params.multipleChoiceQuestionScore = parseInt(multiplescore);
    }

    var judgenum = $("#judgenum").val();
    if(!judgenum || judgenum == ""){
        return;
    }else{
        params.trueOrFalseQuestionNum = parseInt(judgenum);
    }

    var judgescore = $("#judgescore").val();
    if(!judgescore || judgescore == ""){
        return;
    }else{
        params.trueOrFalseQuestionScore = parseInt(judgescore);
    }

    var fillnum = $("#fillnum").val();
    if(!fillnum || fillnum == ""){
        return;
    }else{
        params.gapFillingNum = parseInt(fillnum);
    }

    var fillscore = $("#fillscore").val();
    if(!fillscore || fillscore == ""){
        return;
    }else{
        params.gapFillingScore = parseInt(fillscore);
    }

    var questionnum = $("#questionnum").val();
    if(!questionnum || questionnum == ""){
        return;
    }else{
        params.shortAnswerQustionNum = parseInt(questionnum);
    }

    var questionscore = $("#questionscore").val();
    if(!questionscore || questionscore == ""){
        return;
    }else{
        params.shortAnswerQustionScore = parseInt(questionscore);
    }

    var total = $("#total").val();
    if(!total || total == ""){
        return;
    }else{
        params.totalScore = parseInt(total);
    }

    var illustrate = $("#illustrate").val();
    if(!illustrate || illustrate == ""){
        return;
    }else{
        params.examinationDesc = illustrate;
    }
    // var param = [];
    // param.push(params);
    console.log(JSON.stringify(params));
    // console.log(params);
    // console.log(params.toJSON());
    $.ajax({
        type:"POST",
        url:'http://'+localhost+':'+port+'/examination/examination/add',
        data:JSON.stringify(params),
        // dataType:'json',
        headers : {
            'Content-Type' : 'application/json'
        },
        success:function (datas) {
            alert(datas);
            $("#myModal").modal('hide');
            table_refresh();
        }

    })




}











































// function testValidatior() {
//     $('form').bootstrapValidator({
//         message: 'This value is not valid',
//         feedbackIcons: {
//             valid: 'glyphicon glyphicon-ok',
//             invalid: 'glyphicon glyphicon-remove',
//             validating: 'glyphicon glyphicon-refresh'
//         },
//         fields: {
//             testName1: {
//                 message: '用户名验证失败',
//                 validators: {
//                     notEmpty: {
//                         message: '用户名不能为空'
//                     },
//                     stringLength: {
//                         min: 6,
//                         max: 18,
//                         message: '用户名长度必须在6到18位之间'
//                     },
//                     regexp: {
//                         regexp: /^[a-zA-Z0-9_]+$/,
//                         message: '用户名只能包含大写、小写、数字和下划线'
//                     }
//                 }
//             },
//             startTime: {
//                 validators: {
//                     notEmpty: {
//                         message: '邮箱不能为空'
//                     },
//                     emailAddress: {
//                         message: '邮箱地址格式有误'
//                     }
//                 }
//             },
//             endTime: {
//                 validators: {
//                     notEmpty: {
//                         message: '邮箱不能为空'
//                     },
//                     emailAddress: {
//                         message: '邮箱地址格式有误'
//                     }
//                 }
//             }
//         }
//     });
// }
