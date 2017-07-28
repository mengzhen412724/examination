/**
 * Created by lgw on 2017/3/31.
 */
function getScore() {
    var selects = $("#rangeTable").bootstrapTable('getSelections');
    var newSelects = $.parseJSON(JSON.stringify(selects));
    if(selects.length ==1){
        var examinationId = newSelects[0].ID;
        var start = newSelects[0].EXAMINATIOM_START_TIME;
        var testname = newSelects[0].EXAMINATIOM_NAME;
        window.open("./totalScore.html?examinationId="+examinationId+"&start="+start+"&testname="+testname);
    }else{
        alert('请选择查看考试！');

    }

}



function init_test_score(){
    $table = $('#testScore').bootstrapTable({
        method: 'get',
        url: 'http://'+localhost+':'+port+'/examination/examination/select',
        height:$(window).height() - 200,
        striped: true,
        pagination: false,
        dataType:'json',
        pageSize: 50,
        pageList: [10, 25, 50, 100, 200],
        search: false,
        sidePagination: "client",
        showColumns: false,
        showHeader:true,
        clickToSelect:true,
        minimunCountColumns: 2,
        checkboxHeader:true,
        singleSelect:true,
        columns: [{
            checkbox:true,
        },{
            field: 'EXAMINATIOM_NAME',
            title: '考试名称',
            width:150,
            sortable: true
        }, {
            field: 'EXAMINATIOM_START_TIME',
            title: '开始时间',
            width:200,
            formatter:function(value,row,index){
                return changeTime(value);
            },
            sortable: true
        }, {
            field: 'EXAMINATIOM_END_TIME',
            title: '结束时间',
            width:200,
            formatter:function(value,row,index){
                return changeTime(value);
            },
            sortable: true,
        }, {
            field: 'SINGLE_CHOICE_QUESTION_NUM',
            title: '单选题个数',
            width:150,
            sortable: true
        }, {
            field: 'SINGLE_CHOICE_QUESTION_SCORE',
            title: '单选分数',
            width:150,
            sortable: true
        }, {
            field: 'MULTIPLE_CHOICE_QUESTION_NUM',
            title: '多选个数',
            width:150,
            sortable: true
        }, {
            field: 'MULTIPLE_CHOICE_QUESTION_SCORE',
            title: '多选分数',
            width:150,
            sortable: true
        }, {
            field: 'TRUE_OF_FALSE_QUESTION_NUM',
            title: '判断个数',
            width:150,
            sortable: true
        }, {
            field: 'TRUE_OF_FALSE_QUESTION_SCORE',
            title: '判断分数',
            width:150,
            sortable: true
        }, {
            field: 'SHORT_ANSWER_QUESTION_NUM',
            title: '简答个数',
            width:150,
            sortable: true
        }, {
            field: 'SHORT_ANSWER_QUESTION_SCORE',
            title: '简答分数',
            width:150,
            sortable: true
        }, {
            field: 'GAP_FILLING_NUM',
            title: '填空个数',
            width:150,
            sortable: true
        }, {
            field: 'GAP_FILLING_SCORE',
            title: '填空分数',
            width:150,
            sortable: true
        }, {
            field: 'TOTAL_SCORE',
            title: '总分',
            width:150,
            sortable: true
        }, {
            field: 'EXAMINATIOM_DESC',
            title: '考试说明',
            width:150,
            sortable: true
        }],


    });
}
