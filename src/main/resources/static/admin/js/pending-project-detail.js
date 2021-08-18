var tabId = '';
$(document).ready( function () {
    if(isClosed == '1'){
        $("#comment-input").attr("readonly", true);
        $("#btn-saveComment").attr("disabled", true);
        $("#btn-closeReview").attr("disabled", true);
        document.getElementById("approve-card-div").style.display = 'block';
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
        toastr.error('You need to enter the comment below to explain');
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
            toastr.error('Add comment fail');
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
    $.ajax({
        url: "/admin/project/close-comment",
        data: data,
        type: "POST",
        success: function(msg) {
            console.log(msg);
            $("#comment-input").attr("readonly", true);
            $("#btn-saveComment").attr("disabled", true);
            $("#btn-closeReview").attr("disabled", true);
        },
        error: function(msg) {
            console.log(msg);
        }
    })
}

function btn_submitReviewResult_onclick(){
    let qualifiedResult = document.getElementById("select-approve").value;
    console.log(qualifiedResult);

    if(qualifiedResult == '-1'){
        toastr.error("Please select the result to continue");
        document.getElementById("select-approve").focus();
        return;
    }

    var data = {
        "projectId": projectId,
        "reviewResult": qualifiedResult,
    };

    $.ajax({
        url: "/admin/project/submit-review-result",
        data: data,
        type: "POST",
        success: function(msg) {
            toastr.success("Save success");
            setTimeout(function () {
                toastr.success("Redirect you to pending project page after 3 seconds");
            }, 2000)

            console.log(msg);
            setTimeout(function(){
                window.location.href = "/admin/project/pending-list"
            },3000)
        },
        error: function(msg) {
            console.log(msg);
            toastr.error("Save fail");
        }
    })

}


