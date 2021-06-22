$(document).ready( function () {
    $('#project-table-content').DataTable({
        lengthChange: false
    });
} );


function btn_FilterProjects_onclick(){
    var categoryEle = document.getElementById("category");
    var statusEle = document.getElementById("status");
    var searchInput = document.getElementById("search_input").value;
    var data = {
        "categoryId": categoryEle.value,
        "statusId": statusEle.value,
        "searchInput": searchInput
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

