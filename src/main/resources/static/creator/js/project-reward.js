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
            $("#editRewardHolder").html(data);
            $('html,body').animate({
                scrollTop: $("#editRewardArea").offset().top
            }, 'slow');
            $("#title-inp").focus();
        },
        error: function(data){
            console.log(data);
        }

    })
}

function openAddItemModal(projectId, optionId, items){
    var dataForm = new FormData();
    dataForm.append("projectId", projectId);
    dataForm.append("optionId", optionId);
    dataForm.append("items", items);
    $.ajax({
        // url: "/creator/project/" + projectId + "/reward/" + optionId+ "/addItem",
        url:"/creator/project/reward/addItemModal",
        ache: false,
        contentType : false,
        processData: false,
        data: dataForm,
        type: "POST",
        success: function(data){
            $("#addItemModalHolder").html(data);
            $("#addItemModal").modal("show");
        },
        error: function(data){
            console.log(data);
        }
    })
}

function addToItemList(){
    var itemId = $("#itemListInput option:selected").val();
    var optionId = $("#input-option-id").val();
    var projectId = $("#input-project-id").val();
    var data  = {
        "projectId": projectId,
        "newItemId": itemId,
        "optionId" : optionId
    }
    $("#addItemModal").modal("hide");
    $("#editRewardHolder").html("");
    $.ajax({
        url: "/creator/project/reward/addItemToList",
        data: data,
        success: function(data){
            console.log(data);
            $("#editRewardHolder").html(data);
        },
        error: function(data){
            console.log(data);
        }
    })
}


function saveNewProjectReward(){}