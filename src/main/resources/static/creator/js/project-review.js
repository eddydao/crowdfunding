function btn_submitReview_onclick(){
    debugger
    let data = {
        "projectId": projectId
    };
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