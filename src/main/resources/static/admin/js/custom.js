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
    var dataForm = new FormData();
    dataForm.append("id", categoryId);
    $.ajax({
        url: "/admin/category/del",
        ache: false,
        contentType : false,
        processData: false,
        data: dataForm,
        type: "POST",
        success: function(data){
            console.log("success");
            // $("#categoryDelConfirmation").modal("hide");
            // alert("Success");
        },
        error: function(data){
            console.log(data);
        }

    })
}