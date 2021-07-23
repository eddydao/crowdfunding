function btn_submitReview_onclick(){
    debugger
    let data = {
        "projectId": projectId
    };
    $.ajax({
        url: "/creator/submit-to-review",
        data: data,
        type: "POST",
        success: function(data) {
            console.log(msg);
            toastr.error('Submit review succeeded');
            $("#project-review-noti-div").html(data);
        },
        error: function(msg) {
            console.log(msg);
            toastr.error('Submit review failed');
        }
    })
}