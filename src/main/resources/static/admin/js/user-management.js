
$(document).ready( function () {
    $('#user-table').DataTable();
} );

function btn_addNewAdminAccount_onclick(){
    $.ajax({
        url: "/admin/user/add-new/" ,
        success: function(data){
            console.log(data);
            $("#addNewAccountModalHolder").html(data);
            $("#addNewAccountModal").modal("show");
        },
        error: function(data){
            console.log(data);
        }

    })
}


function btn_createNewAccount(){

    let username = $("#username").val();
    var data = {
        "username" : username
    };

    $.ajax({
        url: "/admin/user/save-new-account",
        data: data,
        type: "POST",
        success: function(data){
            console.log(data);
            $("#addNewAccountModal").modal("hide");
            $("#user-table-div").html(data);
            toastr.success('Save success');
        },
        error: function(data){
            console.log(data);
            toastr.warning('Save fail');
        }
    })
}

function openDeleteModal(userId){
    var data = {
        "userId" : userId
    };
    $.ajax({
        data: data,
        url: "/admin/user/open-delete-modal/" ,
        success: function(data){
            console.log(data);
            $("#adminUserDelConfirmationModalHolder").html(data);
            $("#adminUserDelConfirmation").modal("show");
        },
        error: function(data){
            console.log(data);
        }

    })
}

function deleteAdminUser(){
    let userId = $("#user-id").val();
    var data = {
        "id" : userId
    };

    $.ajax({
        url: "/admin/user/delete-admin-account",
        data: data,
        type: "POST",
        success: function(data){
            console.log(data);
            $("#adminUserDelConfirmation").modal("hide");
            $("#user-table-div").html(data);
            toastr.success('Save success');
        },
        error: function(data){
            console.log(data);
            toastr.warning('Save fail');
        }
    })

}