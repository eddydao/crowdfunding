function btn_Filter_pendingProject_onclick(){
    var e = document.getElementById("category");
    var category = {"categoryId": e.value};

    // var dataForm = new FormData();
    // dataForm.append("category", category);

    $.ajax({
        url: "/admin/project/filter",
        // cache: false,
        // contentType : false,
        // processData: false,
        data:   category,
        type: "GET",
        success: function(data){
            console.log(data);
            $("#pending-project-table").html(data);
            // toastr.success('Save success');
        },
        error: function(data){
            console.log(data);
            // toastr.warning('Save fail');
        }
    })
}

function btn_FilterProjects_onclick(){
    var categoryEle = document.getElementById("category");
    var statusEle = document.getElementById("status");
    var data = {
                    "categoryId": categoryEle.value,
                    "statusId": statusEle.value
                };


    $.ajax({
        url: "/admin/project/filter",
        data:   data,
        type: "GET",
        success: function(data){
            console.log(data);
            $("#project-table").html(data);
        },
        error: function(data){
            console.log(data);
        }
    })
}