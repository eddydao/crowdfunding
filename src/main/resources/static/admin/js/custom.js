// Category management region
function openDeleteModal(categoryId){
    $.ajax({
        url: "/admin/category/del-confirmation/" + categoryId ,
        success: function(data){
            console.log(data);
            $("#categoryDelConfirmationHolder").html(data);
            $("#categoryDelConfirmation").modal("show");
        },
        error: function(data){
            console.log(data);
        }

    })
}

function deleteCategory(categoryId){
    debugger
    var dataForm = new FormData();
    dataForm.append("categoryId", categoryId);
    $.ajax({
        url: "/admin/category/del",
        success: function(data){
            console.log(data);
        },
        error: function(data){
            console.log(data);
        }

    })
}