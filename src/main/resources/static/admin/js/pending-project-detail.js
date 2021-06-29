var tabId = '';
$(document).ready( function () {
    debugger
    if(isClosed == '1'){
        $("#comment-input").attr("readonly", true);
        $("#btn-saveCommetn").attr("readonly", true);
        $("#btn-closeReview").attr("readonly", true);
    }

    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("id") // activated tab

        if(target == 'nav-basic-info-tab'){
            $("#comment-input").val(basicTabComment);
        }else if(target == 'nav-reward-tab'){
            $("#comment-input").val(rewardTabComment);
        }else if(target == 'nav-story-tab'){
            $("#comment-input").val(storyTabComment);
        }
    });
} );


// save comment
function submitReviewForSection(){
    let reviewComment = document.getElementById("comment-input").value;

    if(reviewComment == ""){
        toastr.info('You need to enter the comment below to explain');
        document.getElementById("comment-input").focus();
        return;
    }
    $(".tab-pane.active").each(function(){
        tabId = $(this).attr("id");
    })
    var comment = document.getElementById("comment-input").value;
    let tabNumber = '';

    if(tabId == 'nav-basic-info'){
        tabNumber = '1';
    }else if(tabId == 'nav-reward'){
        tabNumber = '2';
    }else if(tabId = 'nav-reward'){
        tabNumber = '3';
    }
    var data = {
        "projectId": projectId,
        "section": tabNumber,
        "comment": comment
    };
debugger
    $.ajax({
        url: "/admin/project/save-comment",
        data: data,
        type: "POST",
        success: function(msg) {
            console.log(msg);
            toastr.success('Add comment success');
        },
        error: function(msg) {
            console.log(msg);
            toastr.success('Add comment fail');
        }
    })

}

// complete review after comment
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
        data: data,
        type: "POST",
        success: function(msg) {
            console.log(msg);
            $("#comment-input").attr("readonly", true);
            $("#btn-saveCommetn").attr("readonly", true);
            $("#btn-closeReview").attr("readonly", true);
        },
        error: function(msg) {
            console.log(msg);
        }
    })

    return;
}


