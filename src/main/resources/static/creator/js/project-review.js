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


function showComment(){
    $.ajax({
        url: "/creator/project/" + projectId + "/show-comment" ,
        success: function(data){
            console.log(data);
            $("#commentModalHolder").html(data);
            $("#commentModal").modal("show");
        },
        error: function(data){
            console.log(data);
        }

    })
}

function btn_returnToEditProject_onclick(){
    $.ajax({
        url: "/creator/project/" + projectId + "/return-to-edit" ,
        success: function(data){
            console.log(data);
            window.location.href = "/creator/project/" + projectId;
        },
        error: function(data){
            console.log(data);
        }

    })

}