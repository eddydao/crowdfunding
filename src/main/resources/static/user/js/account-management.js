function disableEditForm(value){
    $("#firstName").attr("disabled", value);
    $("#lastName").attr("disabled", value);
    $("#email").attr("disabled", value);
    $("#address").attr("disabled", value);
    $("#phoneNum").attr("disabled", value);
    $("#countryId").attr("disabled", value);
}

function btn_edit_onclick(){
    disableEditForm(false);
    $("#card-footer").css("display", "block");
}

function btn_cancel_onclick(){
    disableEditForm(true);
    $("#card-footer").css("display", "none");
}

function btn_save_onclick(){
    let firstName = $("#firstName").val();
    let lastName = $("#lastName").val();
    let email = $("#email").val();
    let address = $("#address").val();
    let phoneNum = $("#phoneNum").val();
    let countryId = $("#countryId").val();


    var emailPattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
    if(!emailPattern.test(email)){

    }
}

$("#commentForm").validate();

