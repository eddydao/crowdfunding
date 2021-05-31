
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

function saveCategory(){
    debugger
    var dataForm = new FormData();
    var newCategoryName = document.getElementById("category-name").value;
    dataForm.append("name", newCategoryName);
    $("#div-create-category").fadeOut(1000);
    document.getElementById("category-name").value = '';

    $.ajax({
        url: "/admin/category/save",
        ache: false,
        contentType : false,
        processData: false,
        data: dataForm,
        type: "POST",
        success: function(data){
            console.log(data);
            $("#category-table").html(data);
            toastr.success('Save success');
        },
        error: function(data){
            console.log(data);
            toastr.warning('Save fail');
        }
    })
}

function deleteCategory(categoryId){
    var dataForm = new FormData();
    dataForm.append("id", categoryId);
    $("#categoryDelConfirmation").modal("hide");
    $.ajax({
        url: "/admin/category/del",
        ache: false,
        contentType : false,
        processData: false,
        data: dataForm,
        type: "POST",
        success: function(data){
            console.log(data);
            $("#category-table").html(data);
            toastr.success('Delete success');
        },
        error: function(data){
            console.log(data);
            toastr.warning('Delete fail');
        }
    })
}