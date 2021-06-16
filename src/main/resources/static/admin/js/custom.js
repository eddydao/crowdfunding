function btn_Filter_pendingProject_onclick(){
    var e = document.getElementById("category");
    var category = {"categoryId": e.value};

    $.ajax({
        url: "/admin/project/filter",
        data:   category,
        type: "GET",
        success: function(data){
            console.log(data);
            $("#pending-project-table").html(data);
        },
        error: function(data){
            console.log(data);
        }
    })
}

