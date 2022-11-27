/**
 * 
 */

var rangeDate = 31; // set limit day
var setSdate, setEdate;
$("#from").datepicker({
    dateFormat: 'yy-mm-dd',
    minDate: 0,
    onSelect: function(selectDate){
        var stxt = selectDate.split("-");
            stxt[1] = stxt[1] - 1;
        var sdate = new Date(stxt[0], stxt[1], stxt[2]);
        var edate = new Date(stxt[0], stxt[1], stxt[2]);
            edate.setDate(sdate.getDate() + rangeDate);

        $('#to').datepicker('option', {
            minDate: selectDate,
            beforeShow : function () {
                $("#to").datepicker( "option", "maxDate", edate );
                setSdate = selectDate;
                console.log(setSdate)
        }});
        //to 설정
    }
    //from 선택되었을 때
});

$("#to").datepicker({
    dateFormat: 'yy-mm-dd',
    onSelect : function(selectDate){
        setEdate = selectDate;
        console.log(setEdate)
    }
});
