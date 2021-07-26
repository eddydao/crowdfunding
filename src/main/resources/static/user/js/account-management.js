function disableEditForm(value){
    $("#firstName").attr("disabled", value);
    $("#lastName").attr("disabled", value);
    $("#email").attr("disabled", value);
    $("#address").attr("disabled", value);
    $("#phoneNum").attr("disabled", value);
    $("#countryId").attr("disabled", value);
    // $("#comment-input").attr("disabled", value);
}

function btn_edit_onclick(){
    disableEditForm(false);
}