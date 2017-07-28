var storage = window.localStorage;
var initData = {};
var formData = {};

function set(k,v,t){
	var _this = this;
	if(typeof(_this) == "object"&& Object.prototype.toString.call(_this).toLowerCase() == "[object object]" && !_this.length)
	{
		_this[k] = {'value':v,'time':t};
		storage.setItem('questions',$.toJSON(formData));
	}
}

function clearStorage()
{
	storage.removeItem('questions');
}

function getCheckboxValues(obj) {
	var s="";
	for(var i=0;i<7;i++){
		if($("[name='question["+obj+"]["+i+"]']").is(':checked')){
			s += $("[name='question["+obj+"]["+i+"]']").val();
		}
	}
	return s;
}

function submitPaper()
{
    var final = [];
    for(var i=0;i<singleCount;i++){
		var id = datas[0][i].QUESTION_ID;
		var value = $("input[name='question["+id+"]']:checked").val();
		if(!value){
			value = "";
		}
        var objectData = {
            'QUESTIONS_ID':id,
            'USER_NAME':decodeURI(user),
            'EXAMINATIOM_ID':examId,
            'MODIFY_TIME':null,
            'USER_ANSWER':value
        };
		final.push(objectData);
	}
	for(var j=0;j<multipleCount;j++){
    	var id = datas[1][j].QUESTION_ID;
    	var value = getCheckboxValues(id);
        var objectData = {
            'QUESTIONS_ID':id,
            'USER_NAME':decodeURI(user),
            'EXAMINATIOM_ID':examId,
            'MODIFY_TIME':null,
            'USER_ANSWER':value
        };
        final.push(objectData);
	}
	for(var m=0;m<judgeCount;m++){
        var id = datas[2][m].QUESTION_ID;
        var value = $("input[name='question["+id+"]']:checked").val();
        if(!value){
            value = "";
        }else{
        	if(value == "true"){
        		value = "对";
			}else{
                value = "错";
			}
		}
        var objectData = {
            'QUESTIONS_ID':id,
            'USER_NAME':decodeURI(user),
            'EXAMINATIOM_ID':examId,
            'MODIFY_TIME':null,
            'USER_ANSWER':value
        };
        final.push(objectData);
	}
    for(var k=0;k<fillCount;k++){
        var id = datas[3][k].QUESTION_ID;
        var value = $("[name='question["+id+"]']").val();
        var objectData = {
            'QUESTIONS_ID':id,
            'USER_NAME':decodeURI(user),
            'EXAMINATIOM_ID':examId,
            'MODIFY_TIME':null,
            'USER_ANSWER':value
        };
        final.push(objectData);
    }
	for(var n=0;n<questionCount;n++){
		var id = datas[4][n].QUESTION_ID;
		var value = $("[name='question["+id+"]']").val();
        var objectData = {
            'QUESTIONS_ID':id,
            'USER_NAME':decodeURI(user),
            'EXAMINATIOM_ID':examId,
            'MODIFY_TIME':null,
            'USER_ANSWER':value
        };
        final.push(objectData);
	}
	clearStorage();
	console.log(final);
	$('#submodal').modal('hide');
	// $('#form1').submit();
	$.ajax({
		type:"POST",
		url:"http://"+localhost+":"+port+"/examination/exam/submmit",
		data:JSON.stringify(final),
        headers : {
            'Content-Type' : 'application/json'
        },
		success:function(success){
            window.location.href = "./begin.html?info=试卷已提交，考试结束！&loginName="+user;
        },
		error:function (error) {
            window.location.href = "./begin.html?info=试卷已提交，考试结束！&loginName="+user;
        }
	})


}




function refreshRecord(){
	$('#form1 :input[type=text]').each(function(){
		var _= this;
		var _this=$(this);
		var p=[];
		p.push(_.name);
		p.push(_.value);
		set.apply(formData,p);
		markQuestion(_this.attr('rel'),true);
	});
	$('#form1 textarea').each(function(){
		var _= this;
		var _this=$(this);
		var p=[];
		p.push(_.name);
		p.push(_.value);
		set.apply(formData,p);
		markQuestion(_this.attr('rel'),true);
	});
}


function markQuestion(rel,isTextArea)
{
	var t = 0;
	var f = false;
	try
	{
		f = $('#form1 :input[rel='+rel+']');
	}catch(e)
	{
		f = false;
	}
	if(!f)return false;
	if(isTextArea)
	{
		if($('#form1 :input[rel='+rel+']').val() && $('#form1 :input[rel='+rel+']').val() != '' && $('#form1 :input[rel='+rel+']').val() != '<p></p>')t++;
	}
	else
	$('#form1 :input[rel='+rel+']').each(function(){if($(this).is(':checked') && $(this).val() && $(this).val() != '' && $(this).val() != '<p></p>')t++;});
	if(t > 0)
	{
		if(!$('#sign_'+rel).hasClass("btn-primary"))$('#sign_'+rel).addClass("btn-primary");
	}
	else
	{
		$('#sign_'+rel).removeClass("btn-primary");
	}
	// $('.yesdonumber').html($('#questionindex .btn-primary').length);
}

function batmark(rel,value)
{
	if(value && value != '')
	{
		if(!$('#sign_'+rel).hasClass("btn-primary"))$('#sign_'+rel).addClass("btn-primary");
	}
	else
	$('#sign_'+rel).removeClass("btn-primary");
	// $('.yesdonumber').html($('#modal-body .btn-primary').length);
}

function _markQuestion(rel)
{
	if(!$('#sign_'+rel).hasClass("btn-primary"))$('#sign_'+rel).addClass("btn-primary");
	// $('.yesdonumber').html($('#modal-body .btn-primary').length);
}

function signQuestion(id,obj)
{
	if($("#sign_"+id).hasClass("btn-danger")){
        $('#sign_'+id).removeClass('btn-danger');

	}else{
        $('#sign_'+id).addClass('btn-danger');

	}

	// $.get("index.php?exam-app-index-ajax-sign&questionid="+id+'&'+Math.random(),function(data){
     //    if(parseInt(data) != 1){
     //        $('#sign_'+id).removeClass('signBorder');
     //        $(obj).children("em").attr("class","icon-star-empty");
     //    }else{
     //        $('#sign_'+id).addClass('signBorder');
     //        $(obj).children("em").attr("class","icon-star");
     //    }
	// });
}
