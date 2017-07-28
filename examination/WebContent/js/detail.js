/**
 * Created by lgw on 2017/3/30.
 */
var datas_datil={};


function init_detail(){
    var single_datas_detail = datas_datil[0];
    var mulity_datas_detail = datas_datil[1];
    var judge_datas_detail = datas_datil[2];
    var fill_ddatas_detail = datas_datil[3];
    var ques_datas_detail = datas_datil[4];

    var single_content = '';
    var mulity_content = '';
    var judge_content = '';
    var fill_content = "";
    var ques_content = '';
    if(single_datas_detail.length == 0){
        $("#single_detail").css("display","none");

    }else{
        for(var i=0;i<single_datas_detail.length;i++){
            var title = single_datas_detail[i].QUESTION_DESC;
            var answer_a = single_datas_detail[i].ANSWER_A;
            var answer_b = single_datas_detail[i].ANSWER_B;
            var answer_c = single_datas_detail[i].ANSWER_C;
            var answer_d = single_datas_detail[i].ANSWER_D;
            var score = single_datas_detail[i].CHECK_SCORE;
            var right_answer = single_datas_detail[i].STANDARD_ANSWER;
            var your_answer = single_datas_detail[i].USER_ANSWER;
            var flag = "wrong";
            if(score != 0){
                flag = "right";
            }
            single_content += '<div class="box itembox paperexamcontent"><h4 class="title">第'+(i+1)
                +'题 </h4>'
                +'<div class="choice">'+title+'</div>'
                +'<div class="choice">'
                +'<p>A:'+answer_a+'</p><p>B:'+answer_b+'</p>	<p>C:'+answer_c+'</p><p>D:'+answer_d+'</p></div>'
                +'<div class="decidediv" style="position:relative;">'
                +'<div class="'+flag+'"></div>'
                +'<table class="table table-hover table-bordered">'
                +'<tbody><tr class="info">'
                +'<th colspan="2" style="border-top:0px;">本题得分：'+score+'分</th></tr>'
                +'<tr> <td width="15%">正确答案：</td> <td>'+right_answer+'</td> </tr>'
                +'<tr> <td>您的答案：</td> <td>'+your_answer+'</td> </tr>'
                +'<tr> <td>答案解析：</td> <td></td> </tr>'
                +'</tbody></table> </div> </div>';
        }
    }
    if(mulity_datas_detail.length == 0){
        $("#mulity_detail").css("display","none");
    }else{
        $("#mulity_detail").css("display","block");
        for(var j=0;j<mulity_datas_detail.length;j++){
            var title = mulity_datas_detail[j].QUESTION_DESC;
            var answer_a = mulity_datas_detail[j].ANSWER_A;
            var answer_b = mulity_datas_detail[j].ANSWER_B;
            var answer_c = mulity_datas_detail[j].ANSWER_C;
            var answer_d = mulity_datas_detail[j].ANSWER_D;
            var answer_e = mulity_datas_detail[j].ANSWER_E;
            var content_e = "";
            if(answer_e != ""){
                content_e = '<p>E:'+answer_e+'</p>';
            }
            var answer_f = mulity_datas_detail[j].ANSWER_F;
            var content_f = "";
            if(answer_f != ""){
                content_f = '<p>E:'+answer_f+'</p>';
            }
            var answer_g = mulity_datas_detail[j].ANSWER_G;
            var content_g = "";
            if(answer_g != ""){
                content_g = '<p>E:'+answer_g+'</p>';
            }
            var score = mulity_datas_detail[j].CHECK_SCORE;
            var right_answer = mulity_datas_detail[j].STANDARD_ANSWER;
            var your_answer = mulity_datas_detail[j].USER_ANSWER;
            var flag = "wrong";
            if(score != 0){
                flag = "right";
            }
            mulity_content += '<div class="box itembox paperexamcontent"><h4 class="title">第'+(j+1)
                +'题 </h4>'
                +'<div class="choice">'+title+'</div>'
                +'<div class="choice">'
                +'<p>A:'+answer_a+'</p><p>B:'+answer_b+'</p>	<p>C:'+answer_c+'</p><p>D:'+answer_d+'</p>'
                +content_e+content_f+content_g+'</div>'
                +'<div class="decidediv" style="position:relative;">'
                +'<div class="'+flag+'"></div>'
                +'<table class="table table-hover table-bordered">'
                +'<tbody><tr class="info">'
                +'<th colspan="2" style="border-top:0px;">本题得分：'+score+'分</th></tr>'
                +'<tr> <td width="15%">正确答案：</td> <td>'+right_answer+'</td> </tr>'
                +'<tr> <td>您的答案：</td> <td>'+your_answer+'</td> </tr>'
                +'<tr> <td>答案解析：</td> <td></td> </tr>'
                +'</tbody></table> </div> </div>';
        }
    }


    if(judge_datas_detail.length == 0){
        $("#judge_detail").css("display","none");
    }else{
        $("#judge_detail").css("display","block");
        for(var m=0;m<judge_datas_detail.length;m++){
            var title = judge_datas_detail[m].QUESTION_DESC;
            var score = judge_datas_detail[m].CHECK_SCORE;
            var right_answer = judge_datas_detail[m].STANDARD_ANSWER;
            var your_answer = judge_datas_detail[m].USER_ANSWER
            var flag = "wrong";
            if(score != 0){
                flag = "right";
            }

            judge_content += '<div class="box itembox paperexamcontent"><h4 class="title">第'+(m+1)
                +'题 </h4>'
                +'<div class="choice">'+title+'</div>'
                // +'<div class="choice">'
                // +'<p>正确</p><p>错误</p></div>'
                +'<div class="decidediv" style="position:relative;">'
                +'<div class="'+flag+'"></div>'
                +'<table class="table table-hover table-bordered">'
                +'<tbody><tr class="info">'
                +'<th colspan="2" style="border-top:0px;">本题得分：'+score+'分</th></tr>'
                +'<tr> <td width="15%">正确答案：</td> <td>'+right_answer+'</td> </tr>'
                +'<tr> <td>您的答案：</td> <td>'+your_answer+'</td> </tr>'
                +'<tr> <td>答案解析：</td> <td></td> </tr>'
                +'</tbody></table> </div> </div>';
        }
    }

    if(fill_ddatas_detail.length == 0){
        $("#fill_detail").css("display","none");
    }else {
        $("#fill_detail").css("display", "block");
        for (var n = 0; n < fill_ddatas_detail.length; n++) {
            var title = fill_ddatas_detail[n].QUESTION_DESC;
            var score = fill_ddatas_detail[n].CHECK_SCORE== null?0:fill_ddatas_detail[n].CHECK_SCORE;
            var right_answer = fill_ddatas_detail[n].STANDARD_ANSWER ;
            var your_answer = fill_ddatas_detail[n].USER_ANSWER;
            var flag = "wrong";
            if (score != 0) {
                flag = "right";
            }
            fill_content += '<div class="box itembox paperexamcontent"><h4 class="title">第' + (n + 1)
                + '题 </h4>'
                + '<div class="choice">' + title + '</div>'
                + '<div class="decidediv" style="position:relative;">'
                // +'<div class="'+flag+'"></div>'
                + '<table class="table table-hover table-bordered">'
                + '<tbody><tr class="info">'
                + '<th colspan="2" style="border-top:0px;">本题得分：' + score + '分</th></tr>'
                + '<tr> <td width="15%">正确答案：</td> <td>' + right_answer + '</td> </tr>'
                + '<tr> <td>您的答案：</td> <td>' + your_answer + '</td> </tr>'
                + '<tr> <td>答案解析：</td> <td></td> </tr>'
                + '</tbody></table> </div> </div>';
        }


        if (ques_datas_detail.length == 0) {
            $("#ques_detail").css("display", "none");
        } else {
            $("#ques_detail").css("display", "block");
            for (var n = 0; n < ques_datas_detail.length; n++) {
                var title = ques_datas_detail[n].QUESTION_DESC;
                var score = ques_datas_detail[n].CHECK_SCORE==null?0:ques_datas_detail[n].CHECK_SCORE;
                var right_answer = ques_datas_detail[n].STANDARD_ANSWER;
                var your_answer = ques_datas_detail[n].USER_ANSWER;
                var flag = "wrong";
                if (score != 0) {
                    flag = "right";
                }
                ques_content += '<div class="box itembox paperexamcontent"><h4 class="title">第' + (n + 1)
                    + '题 </h4>'
                    + '<div class="choice">' + title + '</div>'
                    + '<div class="decidediv" style="position:relative;">'
                    // +'<div class="'+flag+'"></div>'
                    + '<table class="table table-hover table-bordered">'
                    + '<tbody><tr class="info">'
                    + '<th colspan="2" style="border-top:0px;">本题得分：' + score + '分</th></tr>'
                    + '<tr> <td width="15%">正确答案：</td> <td>' + right_answer + '</td> </tr>'
                    + '<tr> <td>您的答案：</td> <td>' + your_answer + '</td> </tr>'
                    + '<tr> <td>答案解析：</td> <td></td> </tr>'
                    + '</tbody></table> </div> </div>';
            }
        }


        $("#single_detail").append(single_content);
        $("#mulity_detail").append(mulity_content);
        $("#judge_detail").append(judge_content);
        $("#fill_detail").append(fill_content);
        $("#ques_detail").append(ques_content);
    }

}