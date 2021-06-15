function btn_Filter_onclick(){
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