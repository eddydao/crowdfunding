function showCreateProjectRewardArea(){
    $("#edit-reward").css('display', 'block');
    $('html,body').animate({
        scrollTop: $("#edit-reward").offset().top
    }, 'slow');
    $("#title-inp").focus();
}

function showEditRewardArea(projectId, optionId){



    $.ajax({
        url: "/creator/project/" + projectId + "/reward/" + optionId,
        success: function(data){
            console.log(data);
            debugger
            $("#editRewardHolder").html(data);
            // $("#editRewardArea").css('display', 'block');
            // $('html,body').animate({
            //     scrollTop: $("#editRewardArea").offset().top
            // }, 'slow');
        },
        error: function(data){
            console.log(data);
        }

    })
}

function addToItemList(){
    var itemId = $("#itemListInput option:selected").val();
    var data  = {
        "projectId": projectId,
        "newItemId": itemId
    }
    $.ajax({
        url: "creator/project/reward/addItemToList",
        success: function(data){
            console.log(data);
            $("#edit-reward").html(data);
        },
        error: function(data){
            console.log(data);
        }

    })
}


function saveNewProjectReward(){}