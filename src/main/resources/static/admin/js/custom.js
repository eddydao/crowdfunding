function btn_Filter_onclick(){
    var e = document.getElementById("category");
    var category = e.value;

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