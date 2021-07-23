function btn_submitReview_onclick(){
    debugger
    $.ajax({
        url: "/creator/project/" + projectId + "/confirm-submit" ,
        success: function(data){
            console.log(data);
            $("#submitReviewConfirmationModalHolder").html(data);
            $("#submitReviewConfirmationModal").modal("show");
        },
        error: function(data){
            console.log(data);
        }

    })
}

function submitReview(projectId){
    debugger
    let data = {
        "projectId": projectId
    };
    $("#submitReviewConfirmationModal").modal("hide");
    $.ajax({
        url: "/creator/submit-to-review",
        data: data,
        type: "POST",
        success: function(msg) {
            console.log(msg);
            window.location.href = "/creator/project/" + projectId + "/project-review";
            toastr.success('Submit review succeeded');
        },
        error: function(msg) {
            console.log(msg);
            toastr.error('Submit review failed');
        }
    })
}

// var dataForm = new FormData();
// dataForm.append("id", categoryId);
// $("#categoryDelConfirmation").modal("hide");
// $.ajax({
//     url: "/admin/category/del",
//     cache: false,
//     contentType : false,
//     processData: false,
//     data: dataForm,
//     type: "POST",
//     success: function(data){
//         console.log(data);
//         $("#category-table").html(data);
//         toastr.success('Delete success');
//     },
//     error: function(data){
//         console.log(data);
//         toastr.warning('Delete fail');
//     }
// })