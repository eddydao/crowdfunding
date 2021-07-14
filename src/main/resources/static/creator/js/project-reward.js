// create new option form
function showCreateRewardArea(projectId){
    $.ajax({
        url: "/creator/project/" + projectId + "/reward/new-option",
        success: function(data){
            console.log(data);
            $("#createRewardHolder").html(data);
            $('html,body').animate({
                scrollTop: $("#createRewardArea").offset().top
            }, 'slow');
            $("#title-inp").focus();
        },
        error: function(data){
            console.log(data);
        }
    })
}
//  oepn add item form
function openNewOptionAddItemModal(projectId){
    debugger
    var dataForm = new FormData();
    dataForm.append("projectId", projectId);
    // dataForm.append("optionId", optionId);
    // dataForm.append("items", items);
    $.ajax({
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

// edit option form
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
    debugger
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
    debugger
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
            debugger
            console.log(data);
            $("#editRewardHolder").html(data);
        },
        error: function(data){
            console.log(data);
        }
    })
}

function openRemoveItemModal(projectId, optionId, itemId){
    debugger
    var data = {
        "projectId" : projectId,
        "optionId" : optionId,
        "newItemId" : itemId
    }
    $.ajax({
        url: "/creator/project/confirm-remove-item/",
        data: data,
        success: function(data){
            console.log(data);
            $("#removeItemModalHolder").html(data);
            $("#removeItemModal").modal("show");
        },
        error: function(data){
            console.log(data);
        }

    })
}

function removeFromItemList(){
    debugger
    var itemId = $("#item_id_input").val();
    var optionId = $("#option_id_input").val();
    var projectId = $("#project_id_input").val();
    var data  = {
        "projectId": projectId,
        "newItemId": itemId,
        "optionId" : optionId
    }
    $("#removeItemModal").modal("hide");
    $("#editRewardHolder").html("");
    $.ajax({
        url: "/creator/project/reward/remove-item",
        data: data,
        type: "POST",
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


function showCreateItemModal(projectId){
    var data = {"projectId" : projectId}

    $.ajax({
        url: "/creator/project/open-item-creation-modal",
        data: data,
        success: function(data){
            console.log(data);
            $("#itemCreationModalHolder").html(data);
            $("#addNewItem").modal("show");
        },
        error: function(data){
            console.log(data);
        }
    })
}


function showEditItemModal(projectId, itemId){
    debugger
    var data = {
        "projectId" : projectId,
        "itemId"    : itemId
    }

    $.ajax({
        url: "/creator/project/open-item-edit-modal",
        data: data,
        success: function(data){
            console.log(data);
            $("#itemCreationModalHolder").html(data);
            $("#editItem").modal("show");
        },
        error: function(data){
            console.log(data);
        }
    })
}

function saveNewItem(){
    var itemName = $("#item-name-inp").val();
    var projectId = $("#project-id-inp").val();
    var data  = {
        "projectId": projectId,
        "itemName": itemName
    }
    $("#addNewItem").modal("hide");
    $("#item-list-div").html("");
    $.ajax({
        url: "/creator/project/save-item",
        data: data,
        type: "POST",
        success: function(data){
            console.log(data);
            $("#item-list-div").html(data);
        },
        error: function(data){
            console.log(data);
        }
    })
}



function updateItem(){
    debugger
    var itemName = $("#item-name-inp").val();
    var itemId = $("#item-id-inp").val();
    var projectId = $("#project-id-inp").val();
    var data  = {
        "projectId": projectId,
        "itemName": itemName,
        "itemId"    : itemId
    }
    $("#editItem").modal("hide");
    $("#item-list-div").html("");
    $.ajax({
        url: "/creator/project/save-item",
        data: data,
        type: "POST",
        success: function(data){
            console.log(data);
            $("#item-list-div").html(data);
        },
        error: function(data){
            console.log(data);
        }
    })
}