/**
 * Created by lgw on 2017/3/27.
 */

function edit_edit() {
    var originDatas = $("#rangeTable").bootstrapTable("getSelections");

    if(originDatas.length ==1){
        $("#myModal1").modal();
        $("#myModal1").find(".modal-content").load('../admin/editTestRange.html');
    }else{
        alert('请选择修改内容');

    }
}



var singleScores = 0;
var multiScores = 0;
var judgeScores = 0;
var questionScores = 0;
var fillScores = 0;
function edit_checkthis(obj) {
    var content = $(obj).val();
    var lab = $(obj).parent().prev().html();
    if(content == ""){
        $(obj).next().html(lab+'不能为空');
        $("#edit").attr('disabled',"true");
    }else{
        $(obj).next().html('');
        $("#edit").removeAttr('disabled');
        var flag = $(".form-group").find("span");
        for(var i=0;i<flag.length;i++){
            if(flag[i].innerHTML!=""){
                $("#edit").attr('disabled',"true");
            }
        }
        singleScores = parseInt($("#edit_singlenum").val())*parseInt($("#edit_singlescore").val());
        multiScores = parseInt($("#edit_multiplenum").val())*parseInt($("#edit_multiplescore").val());
        judgeScores = parseInt($("#edit_judgenum").val())*parseInt($("#edit_judgescore").val());
        questionScores = parseInt($("#edit_questionnum").val())*parseInt($("#edit_questionscore").val());
        fillScores = parseInt($("#edit_fillnum").val())*parseInt($("#edit_fillscore").val());
        var totalScores = singleScores+multiScores+judgeScores+questionScores+fillScores;
        $("#edit_total").val(totalScores);

    }

}

function edit_testSubmit(){
    var params={};
    var start = $("#edit_start").val();
    if(!start || start == ""){
        return;
    }else{
        params.examinationStartTime=start;
    }
    var end = $("#edit_end").val();
    if(!end || end == ""){
        return;
    }else{
        params.examinationEndTime = end;
    }

    var testname = $("#edit_testname1").val();
    if(!testname || testname == ""){
        return;
    }else{
        params.examinationName = testname;
    }

    var singlenum = $("#edit_singlenum").val();
    if(!singlenum || singlenum == ""){
        return;
    }else{
        params.singleChoiceQuestionNum = parseInt(singlenum);
    }

    var singlescore = $("#edit_singlescore").val();
    if(!singlescore || singlescore == ""){
        return;
    }else{
        params.singleChoiceQuestionScore = parseInt(singlescore);
    }

    var multiplenum = $("#edit_multiplenum").val();
    if(!multiplenum || multiplenum == ""){
        return;
    }else{
        params.multipleChoiceQuestionNum = parseInt(multiplenum);
    }

    var multiplescore = $("#edit_multiplescore").val();
    if(!multiplescore || multiplescore == ""){
        return;
    }else{
        params.multipleChoiceQuestionScore = parseInt(multiplescore);
    }

    var judgenum = $("#edit_judgenum").val();
    if(!judgenum || judgenum == ""){
        return;
    }else{
        params.trueOrFalseQuestionNum = parseInt(judgenum);
    }

    var judgescore = $("#edit_judgescore").val();
    if(!judgescore || judgescore == ""){
        return;
    }else{
        params.trueOrFalseQuestionScore = parseInt(judgescore);
    }

    var fillnum = $("#edit_fillnum").val();
    if(!fillnum || fillnum == ""){
        return;
    }else{
        params.gapFillingNum = parseInt(fillnum);
    }

    var fillscore = $("#edit_fillscore").val();
    if(!fillscore || fillscore == ""){
        return;
    }else{
        params.gapFillingScore = parseInt(fillscore);
    }

    var questionnum = $("#edit_questionnum").val();
    if(!questionnum || questionnum == ""){
        return;
    }else{
        params.shortAnswerQustionNum = parseInt(questionnum);
    }

    var questionscore = $("#edit_questionscore").val();
    if(!questionscore || questionscore == ""){
        return;
    }else{
        params.shortAnswerQustionScore = parseInt(questionscore);
    }

    var total = $("#edit_total").val();
    if(!total || total == ""){
        return;
    }else{
        params.totalScore = parseInt(total);
    }

    var illustrate = $("#edit_illustrate").val();
    if(!illustrate || illustrate == ""){
        return;
    }else{
        params.examinationDesc = illustrate;
    }

    var  id = $("#edit_id").val();
    if(!id || id == ""){
        return;
    }else{
        params.id = parseInt(id);
    }
    $.ajax({
        type:"PUT",
        url:'http://'+localhost+':'+port+'/examination/examination/put',
        data:JSON.stringify(params),
        // dataType:'json',
        headers : {
            'Content-Type' : 'application/json'
        },
        success:function (datas) {
            alert(datas);
            $("#myModal1").modal('hide');
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
