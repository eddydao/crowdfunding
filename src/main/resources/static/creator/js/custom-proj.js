// custom js of dev

$( document ).ready(function() {
    $('#preview-img').attr('src', '../../images/bg-title-01.jpg');

    var enddate_input=$('input[name="end-date"]');
    var startdate_input=$('input[name="start-date"]');
    var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    var options={
        format: 'dd/mm/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
    };
    enddate_input.datepicker(options);
    startdate_input.datepicker(options);

});



var reader = new FileReader();
reader.onload = function (e) {
    var src = e.target.result;
    if (typeof(src) != 'undefined' && src != null){
        $('#preview-img').attr('src', src);
    }
}
   
function readURL(input) {
    if (input.files && input.files[0]) {
        reader.readAsDataURL(input.files[0]);
    }
}

$("#file-input").change(function(){
    readURL(this);
});

function showSpecEndDateInput() {
    if (document.getElementById('fixed-day').checked) {
        document.getElementById('inp-fixed-day').style.visibility = 'visible';
    }
    else { document.getElementById('inp-fixed-day').style.visibility = 'hidden'; }

    if (document.getElementById('spec-date').checked) {
        document.getElementById('inp-spec-date').style.visibility = 'visible';
    }
    else { document.getElementById('inp-spec-date').style.visibility = 'hidden';}

}

