/**
 * Created by lgw on 2017/3/29.
 */
var singleCount ;
var multipleCount ;
var judgeCount ;
var questionCount ;
var fillCount;
var total ;
var datas = {};
var user = getQueryString("loginName");
var cookie_user = getCookie("loginName");
if(!user || !cookie_user || cookie_user ==""){
    window.location.href = "./login.html";
}
if(cookie_user != user){
    window.location.href = "./login.html";
}
var role = 1;
var start = getQueryString("start");
var end = getQueryString("end");
var examId = parseInt(getQueryString("examinationId"));
var left = getQueryString("left");
var examName = getQueryString("testname");

$.get('http://'+localhost+':'+port+'/examination/exam/get',{'id':examId},function(data){
    testname = data.EXAMINATIOM_NAME;
    start = data.EXAMINATIOM_START_TIME;
    end = data.EXAMINATIOM_END_TIME;
    left = data.LEFTTIME;
    examId = parseInt(data.id);
    if( left > (end-start)/1000){
        window.location.href = "./begin.html?loginName="+user;
    }else if(left < 0){
        window.location.href = "./begin.html?loginName="+user;
    }
},'json');

function getAnsered(){
    $('.allquestionnumber').html($('.paperexamcontent').length);
    $('.yesdonumber').html($('#questionindex .btn-primary').length);
}
function gotoquestion(questid)
{
    $(".questionpanel").hide();
    $(".paperexamcontent").hide();
    $("#questions_"+questid).show();
    $("#questype_"+$("#questions_"+questid).attr('rel')).show();
}
function gotoindexquestion(index)
{
    $(".questionpanel").hide();
    $(".paperexamcontent").hide();
    $(".paperexamcontent").eq(index).show();
    $("#questype_"+$(".paperexamcontent").eq(index).attr('rel')).show();
}



function initPage(){
    var content = "";
    var title = "";
    var single =datas[0];
    var multi = datas[1];
    var judge = datas[2];
    var fill = datas[3];
    var ques = datas[4];

    
    //动态添加单选题目
    if(single.length == 0){
           $("#oneSection").css("display","none");

    }else{
        $("#oneSection").css("display","block");
        content += '<div class="box itembox questionpanel" id="questype_1"> <blockquote class="questype"><span>一</span>、单选题</blockquote> </div>';
        for(var i=0;i<single.length;i++){
            var id = single[i].QUESTION_ID;
            var str = '<a id="sign_'+id+'" href="javascript:;" onclick="javascript:gotoquestion('
                +id+')" class="btn btn-default">'+(i+1)+'</a>';
            $("#qt_1").append(str);
            content += '<div class="box itembox paperexamcontent" id="questions_'+id+'" rel="1"><h4 class="title"> 第'
                +(i+1)+'题<span class="pull-right"> <a class="btn btn-info qicon" href="javascript:;" onclick="javascript:signQuestion('+id
                +',this);"><i class="glyphicon glyphicon-bookmark"></i></a> <a name="question_'+id
                +'"> <input id="time_'+id+'" type="hidden" name="time['+id+']"/> </a> </span> </h4> <div class="choice">'+single[i].QUESTION_DESC
                +'</div> <div class="choice"> <p>A:'+single[i].ANSWER_A+'</p> <p>B:'+single[i].ANSWER_B
                +'</p> </p> <p>C:'+single[i].ANSWER_C +'</p></p> <p>D:'+single[i].ANSWER_D
                +'</p></div> <div class="selector"> <label class="radio-inline"><input type="radio" name="question['+id
                +']" rel="'+id+'" value="A" />A </label> <label class="radio-inline"><input type="radio" name="question['+id
                +']" rel="'+id+'" value="B" />B </label> <label class="radio-inline"><input type="radio" name="question['+id
                +']" rel="'+id+'" value="C" />C </label><label class="radio-inline"><input type="radio" name="question['+id
                +']" rel="'+id+'" value="D" />D </label></div>';
            if(i==0){
                content += '<div class="toolbar"> <a class="pull-right btn btn-primary" onclick="javascript:gotoindexquestion('+(i+1)+');">下一题<span class="glyphicon glyphicon-chevron-right"></span></a> </div>';
            }else{
                content += '<div class="toolbar"> <a class="btn btn-default" onclick="javascript:gotoindexquestion('+(i-1)
                    +');"><span class="glyphicon glyphicon-chevron-left"></span>上一题</a> '
                    +'<a class="pull-right btn btn-primary" onclick="javascript:gotoindexquestion('+(i+1)
                    +');">下一题<span class="glyphicon glyphicon-chevron-right"></span></a> </div>';
            }
            content += "</div>";
        }
    }
    //动态添加多选题目
    if(multi.length == 0){
           $("#twoSection").css("display","none");

    }else{
        $("#twoSection").css("display","block");
        content += '<div class="box itembox questionpanel" id="questype_2"> <blockquote class="questype"><span>二</span>、多选题</blockquote> </div>';
        for(var i=0;i<multi.length;i++){
            var id = multi[i].QUESTION_ID;
            var str = '<a id="sign_'+id+'" href="javascript:;" onclick="javascript:gotoquestion('
                +id+')" class="btn btn-default">'+(i+1)+'</a>';
            $("#qt_2").append(str);
            var choiceE = "";
            var selectE = "";
            if(multi[i].ANSWER_E != ""){
                choiceE = '<p>E:'+multi[i].ANSWER_E +'</p>';
                selectE = '<label class="checkbox-inline"><input type="checkbox" name="question['+id
                    +'][4]" rel="'+id+'" value="E" />E </label>';
            };
            var choiceF = "";
            var selectF ="";
            if(multi[i].ANSWER_F != ""){
                choiceE = '<p>F:'+multi[i].ANSWER_F +'</p>';
                selectE = '<label class="checkbox-inline"><input type="checkbox" name="question['+id
                    +'][4]" rel="'+id+'" value="F" />F </label>';
            };
            var choiceG = "";
            var selectG = "";
            if(multi[i].ANSWER_G != ""){
                choiceE = '<p>G:'+multi[i].ANSWER_G +'</p>';
                selectE = '<label class="checkbox-inline"><input type="checkbox" name="question['+id
                    +'][4]" rel="'+id+'" value="G" />G </label>';
            };
            content += '<div class="box itembox paperexamcontent" id="questions_'+id+'" rel="2"><h4 class="title"> 第'
                +(i+1)+'题<span class="pull-right"> <a class="btn btn-info qicon" href="javascript:;" onclick="javascript:signQuestion('+id
                +',this);"><i class="glyphicon glyphicon-bookmark"></i></a> <a name="question_'+id
                +'"> <input id="time_'+id+'" type="hidden" name="time['+id+']"/> </a> </span> </h4> <div class="choice">'+multi[i].QUESTION_DESC
                +'</div> <div class="choice"> <p>A:'+multi[i].ANSWER_A+'</p> <p>B:'+multi[i].ANSWER_B
                +'</p>  <p>C:'+multi[i].ANSWER_C +'</p><p>D:'+multi[i].ANSWER_D+'</p>  '
                +choiceE+choiceF+choiceG
                +'</div> <div class="selector"> '
                +'<label class="checkbox-inline"><input type="checkbox" name="question['+id +'][0]" rel="'+id+'" value="A" />A </label> '
                +'<label class="checkbox-inline"><input type="checkbox" name="question['+id +'][1]" rel="'+id+'" value="B" />B </label> '
                +'<label class="checkbox-inline"><input type="checkbox" name="question['+id +'][2]" rel="'+id+'" value="C" />C </label>'
                +'<label class="checkbox-inline"><input type="checkbox" name="question['+id +'][3]" rel="'+id+'" value="D" />D </label>'
                +selectE+selectF+selectG+'</div>';

            if(singleCount==0){
                content += '<div class="toolbar"> <a class="pull-right btn btn-primary" onclick="javascript:gotoindexquestion('+(singleCount+i+1)+');">下一题<span class="glyphicon glyphicon-chevron-right"></span></a> </div>';
            }else{
                content += '<div class="toolbar"> <a class="btn btn-default" onclick="javascript:gotoindexquestion('+(singleCount+i-1)
                    +');"><span class="glyphicon glyphicon-chevron-left"></span>上一题</a> '
                    +'<a class="pull-right btn btn-primary" onclick="javascript:gotoindexquestion('+(singleCount+i+1)
                    +');">下一题<span class="glyphicon glyphicon-chevron-right"></span></a> </div>';
            }
            content += "</div>";
        }
    }
    //动态添加判断题目
    if(judge.length == 0){
           $("#threeSection").css("display","none");

    }else{
        $("#threeSection").css("display","block");
        content += '<div class="box itembox questionpanel" id="questype_3"> <blockquote class="questype"><span>三</span>、判断题</blockquote> </div>';
        for(var i=0;i<judge.length;i++){
            var id = judge[i].QUESTION_ID;
            var str = '<a id="sign_'+id+'" href="javascript:;" onclick="javascript:gotoquestion('
                +id+')" class="btn btn-default">'+(i+1)+'</a>';
            $("#qt_3").append(str);
            content += '<div class="box itembox paperexamcontent" id="questions_'+id+'" rel="3"><h4 class="title"> 第'+(i+1)
                +'题<span class="pull-right"> <a class="btn btn-info qicon" href="javascript:;" onclick="javascript:signQuestion('+id
                +',this);"><i class="glyphicon glyphicon-bookmark"></i></a> <a name="question_'+id
                +'"> <input id="time_'+id+'" type="hidden" name="time['+id+']"/> </a> </span> </h4> <div class="choice">'+judge[i].QUESTION_DESC
                +'</div>  <div class="selector"> <label class="radio-inline"><input type="radio" name="question['+id
                +']" rel="'+id+'" value="true" />正确</label> <label class="radio-inline"><input type="radio" name="question['+id
                +']" rel="'+id+'" value="false" />错误</label></div>';
            if((singleCount+multipleCount)==0){
                content += '<div class="toolbar"> <a class="pull-right btn btn-primary" onclick="javascript:gotoindexquestion('+(singleCount+multipleCount+i+1)+');">下一题<span class="glyphicon glyphicon-chevron-right"></span></a> </div>';
            }else{
                content += '<div class="toolbar"> <a class="btn btn-default" onclick="javascript:gotoindexquestion('+(singleCount+multipleCount+i-1)
                    +');"><span class="glyphicon glyphicon-chevron-left"></span>上一题</a> '
                    +'<a class="pull-right btn btn-primary" onclick="javascript:gotoindexquestion('+(singleCount+multipleCount+i+1)
                    +');">下一题<span class="glyphicon glyphicon-chevron-right"></span></a> </div>';
            }
            content += "</div>";
        }
    }
    //动态添加填空题
    if(fill.length == 0){
        $("#fourSection").css("display","none");

    }else{
        $("#fourSection").css("display","block");
        content += '<div class="box itembox questionpanel" id="questype_4"> <blockquote class="questype"><span>四</span>、填空题</blockquote> </div>';
        for(var i=0;i<fill.length;i++){
            var id = fill[i].QUESTION_ID;
            var str = '<a id="sign_'+id+'" href="javascript:;" onclick="javascript:gotoquestion('
                +id+')" class="btn btn-default">'+(i+1)+'</a>';
            $("#qt_4").append(str);
            content += '<div class="box itembox paperexamcontent" id="questions_'+id+'" rel="4"><h4 class="title"> 第'+(i+1)+'题<span class="pull-right"> <a class="btn btn-info qicon" href="javascript:;" onclick="javascript:signQuestion('+id
                +',this);"><i class="glyphicon glyphicon-bookmark"></i></a> <a name="question_'+id
                +'"> <input id="time_'+id+'" type="hidden" name="time['+id+']"/> </a> </span> </h4> <div class="choice">'+fill[i].QUESTION_DESC
                +'</div> <div > <textarea style="width: 600px;height: 200px" name="question['+id+']" rel="'+id+'"></textarea></div>';
            if((singleCount+multipleCount+judgeCount)==0){
                content += '<div class="toolbar"> <a class="pull-right btn btn-primary" onclick="javascript:gotoindexquestion('+(singleCount+multipleCount+judgeCount+i+1)+');">下一题<span class="glyphicon glyphicon-chevron-right"></span></a> </div>';
            }else if((singleCount+multipleCount+judgeCount+i+1)==total){
                content += '<div class="toolbar"> <a class="btn btn-default" onclick="javascript:gotoindexquestion('+(singleCount+multipleCount+judgeCount+i-1)
                    +');"><span class="glyphicon glyphicon-chevron-left"></span>上一题</a></div> '
            }else{
                content += '<div class="toolbar"> <a class="btn btn-default" onclick="javascript:gotoindexquestion('+(singleCount+multipleCount+judgeCount+i-1)
                    +');"><span class="glyphicon glyphicon-chevron-left"></span>上一题</a> '
                    +'<a class="pull-right btn btn-primary" onclick="javascript:gotoindexquestion('+(singleCount+multipleCount+judgeCount+i+1)
                    +');">下一题<span class="glyphicon glyphicon-chevron-right"></span></a> </div>';
            }
            content += "</div>";
        }
    }
    //动态添加问答题
    if(ques.length == 0){
           $("#fiveSection").css("display","none");

    }else{
        $("#fiveSection").css("display","block");
        content += '<div class="box itembox questionpanel" id="questype_5"> <blockquote class="questype"><span>五</span>、简答题</blockquote> </div>';
        for(var i=0;i<ques.length;i++){
            var id = ques[i].QUESTION_ID;
            var str = '<a id="sign_'+id+'" href="javascript:;" onclick="javascript:gotoquestion('
                +id+')" class="btn btn-default">'+(i+1)+'</a>';
            $("#qt_5").append(str);
            content += '<div class="box itembox paperexamcontent" id="questions_'+id+'" rel="5"><h4 class="title"> 第'+(i+1)+'题<span class="pull-right"> <a class="btn btn-info qicon" href="javascript:;" onclick="javascript:signQuestion('+id
                +',this);"><i class="glyphicon glyphicon-bookmark"></i></a> <a name="question_'+id
                +'"> <input id="time_'+id+'" type="hidden" name="time['+id+']"/> </a> </span> </h4> <div class="choice">'+ques[i].QUESTION_DESC
                +'</div> <div > <textarea style="width: 600px;height: 200px" name="question['+id+']" rel="'+id+'"></textarea></div>';
            if((singleCount+multipleCount+judgeCount+fillCount)==0){
                content += '<div class="toolbar"> <a class="pull-right btn btn-primary" onclick="javascript:gotoindexquestion('+(singleCount+multipleCount+judgeCount+fillCount+i+1)+');">下一题<span class="glyphicon glyphicon-chevron-right"></span></a> </div>';
            }else if((singleCount+multipleCount+judgeCount+fillCount+i+1)==total){
                content += '<div class="toolbar"> <a class="btn btn-default" onclick="javascript:gotoindexquestion('+(singleCount+multipleCount+judgeCount+fillCount+i-1)
                    +');"><span class="glyphicon glyphicon-chevron-left"></span>上一题</a></div> '
            }else{
                content += '<div class="toolbar"> <a class="btn btn-default" onclick="javascript:gotoindexquestion('+(singleCount+multipleCount+judgeCount+fillCount+i-1)
                    +');"><span class="glyphicon glyphicon-chevron-left"></span>上一题</a> '
                    +'<a class="pull-right btn btn-primary" onclick="javascript:gotoindexquestion('+(singleCount+multipleCount+judgeCount+fillCount+i+1)
                    +');">下一题<span class="glyphicon glyphicon-chevron-right"></span></a> </div>';
            }
            content += "</div>";
        }
    }

    $(".box.itembox").after(content);
    $(".questionpanel").hide();
    $(".questionpanel:first").show();
    $(".paperexamcontent").hide();
    $(".paperexamcontent:first").show();
    var typett = $(".questype");
    for(var i=1;i<typett.length+1;i++){
        $(typett[i-1]).find("span").html(handleSort(i));
    }
    addListen();
}

function showAndHide() {

}


function handleSort(num){

    if(num == 1){
        return "一";
    }
    if(num == 2){
        return "二";
    }
    if(num == 3){
        return "三";
    }
    if(num == 4){
        return "四";
    }
    if(num == 5){
        return "五";
    }
}