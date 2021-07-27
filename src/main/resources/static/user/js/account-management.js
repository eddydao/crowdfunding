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
    let userId = $("#userId").val();
    let firstName = $("#firstName").val();
    let lastName = $("#lastName").val();
    let email = $("#email").val();
    let address = $("#address").val();
    let phoneNum = $("#phoneNum").val();
    let countryId = $("#countryId").val();

    var data = {
        "userId": userId,
        "firstName": firstName,
        "lastName": lastName,
        "email": email,
        "address": address,
        "phoneNum": phoneNum,
        "countryId": countryId
    };

    $.ajax({
        url: "/user/save-account-info",
        data: data,
        type: "POST",
        success: function(msg) {
            console.log(msg);
            disableEditForm(true);
            $("#card-footer").css("display", "none");
            $("#div-account-form").html(msg);
            toastr.success('Save information success');
        },
        error: function(msg) {
            console.log(msg);
            toastr.error('Save information fail');
        }
    })
}

function link_change_password_onclick(){
    $("#div-change-password").css("display", "block");
}

function btn_cancel_password_change_onclick(){
    $("#old-password").val("");
    $("#new-password").val("");
    $("#confirm-password").val("");
    $("#div-change-password").css("display", "none");
}

function btn_save_password_change_onclick(){
    let userId = $("#userId").val();
    let password = $("#new-password").val();

    var data = {
        "id": userId,
        "password": password
    };

    $.ajax({
        url: "/user/save-password",
        data: data,
        type: "POST",
        success: function(msg) {
            console.log(msg);
            $("#div-change-password").css("display", "none");
            $("#div-account-form").html(msg);
            toastr.success('Password changed');
        },
        error: function(msg) {
            console.log(msg);
            toastr.error('Password changing failed');
        }
    })

}


$(document).ready( function () {
    $("#account_form").validate();
});



