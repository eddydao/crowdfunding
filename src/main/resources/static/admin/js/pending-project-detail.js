

function submitReviewForSection(){
    let reviewComment = document.getElementById("comment-input").value;

    if(reviewComment == ""){
        toastr.info('You need to enter the comment below to explain');
        document.getElementById("comment-input").focus();
        return;
    }
    var active = $( "#nav-tabContent" ).tabs( "option", "active" );
    var comment = document.getElementById("comment-input").value;
debugger

    var data = {
        "projectId": projectId,
        "section": active,
        "comment": comment
    };

    $.ajax({
        url: "/admin/project/save-comment",
        cache: false,
        contentType : false,
        processData: false,
        data: data,
        type: "POST",
        success: function(msg) {
            console.log(msg);
        },
        error: function(msg) {
            console.log(msg);
        }
    })

}

function completeReview(){
    var result = confirm("Are you sure to complete the review about this project? \n This can not be revert.");
    if(result){
        document.getElementById("approve-card-div").style.display = 'block';
    }


    var data = {
        "projectId": projectId,
        "isClose": "1",
    };
debugger
    $.ajax({
        url: "/admin/project/close-comment",
        cache: false,
        contentType : false,
        processData: false,
        data: data,
        type: "POST",
        success: function(msg) {
            console.log(msg);
        },
        error: function(msg) {
            console.log(msg);
        }
    })

    return;
}


