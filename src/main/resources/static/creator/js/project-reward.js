$(document).ready(function(){
    debugger
    // if project editable is set to close - cannot be able to edit the information of project
    if (projectEditable == '1') {
        $("#btn-add-reward").attr('disabled', true);
        $("#btn-add-item").attr('disabled', true);


        $(".btn_edit_reward").attr('disabled', true);
        $(".btn_del_reward").attr('disabled', true);
        $(".btn-edit-item").attr('disabled', true);
        $(".btn-edit-item").attr('disabled', true);



        // for(let i = 0 ; i < options.length; i++){
        //     let btnCardEdit = "#btn-card-edit" + options[i].optionId;
        //     let btnCarDel = "#btn-card-del" + options[i].optionId;
        //     $(btnCardEdit).attr('disabled', true);
        //     $(btnCarDel).attr('disabled', true);
        // }
        //
        // for(let i = 0 ; i < items.length; i++){
        //     let btnCardEdit = "#btn-item-edit" + items[i].itemId;
        //     let btnCarDel = "#btn-item-del" + items[i].itemId;
        //     $(btnCardEdit).attr('disabled', true);
        //     $(btnCarDel).attr('disabled', true);
        // }
    }
})
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

function btn_cancelCreateNewOption_onclick(){
    var optionId = $("#input-option-id").val();
    var projectId = $("#input-project-id").val();
    var data  = {
        "projectId": projectId,
        "optionId" : optionId
    }
    $("#createRewardHolder").html("");
    $("#addItemModalNewOption").modal("hide");
    $.ajax({
        url: "/creator/project/reward/cancel-create-new",
        data: data,
        success: function(data){
            debugger
            console.log(data);
            $("#reward-list-div").html(data);
        },
        error: function(data){
            console.log(data);
        }
    })
}


//  open add item form
function openNewOptionAddItemModal(projectId){
    var dataForm = new FormData();
    dataForm.append("projectId", projectId);
    $.ajax({
        url:"/creator/project/reward/addItemModalNewOption",
        ache: false,
        contentType : false,
        processData: false,
        data: dataForm,
        type: "POST",
        success: function(data){
            $("#addItemModalNewRewardHolder").html(data);
            $("#addItemModalNewOption").modal("show");
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

function btn_cancelEdit_onclick(){
    $("#editRewardHolder").html("");
    $("#editRewardArea").modal("hide");
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

function addToItemListNewOption(){
    var itemId = $("#itemListInput option:selected").val();
    var optionId = $("#input-option-id").val();
    var projectId = $("#input-project-id").val();
    var optionTitle = $("#title-inp").val();
    var optionMinFund = $("#amount-inp").val();
    var optionDes = $("#description-input").val();
    var data  = {
        "projectId": projectId,
        "newItemId": itemId,
        "optionId" : optionId,
        "optionName" : optionTitle,
        "fundMin": optionMinFund,
        "optionDescription": optionDes,
        "isTemp": 1,
    }
    $("#addItemModalNewOption").modal("hide");
    $("#createRewardArea").html("");
    $.ajax({
        url: "/creator/project/reward/addItemToListNewOption",
        data: data,
        success: function(data){
            debugger
            console.log(data);
            $("#createRewardHolder").html(data);
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
            debugger
            console.log(data);
            $("#editRewardHolder").html(data);
        },
        error: function(data){
            console.log(data);
        }
    })
}

function openRemoveItemModalNewOption(projectId, optionId, itemId){
    debugger
    var data = {
        "projectId" : projectId,
        "optionId" : optionId,
        "newItemId" : itemId
    }
    $.ajax({
        url: "/creator/project/confirm-remove-item-new-option/",
        data: data,
        success: function(data){
            console.log(data);
            $("#removeItemModalHolderNewOption").html(data);
            $("#removeItemModalNewOption").modal("show");
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

function removeFromItemListNewOption(){
    var itemId = $("#item_id_input").val();
    var optionId = $("#option_id_input").val();
    var projectId = $("#project_id_input").val();
    var data  = {
        "projectId": projectId,
        "newItemId": itemId,
        "optionId" : optionId
    }
    $("#removeItemModalNewOption").modal("hide");
    $("#createRewardHolder").html("");
    $.ajax({
        url: "/creator/project/reward/remove-item-new-option",
        data: data,
        type: "POST",
        success: function(data){
            console.log(data);
            $("#createRewardHolder").html(data);
            toastr.success("Remove successfully");
        },
        error: function(data){
            console.log(data);
            toastr.success("Remove failed");
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
            toastr.success("Remove successfully");
        },
        error: function(data){
            console.log(data);
            toastr.success("Remove failed");
        }
    })
}

function showDeleteOptionModal(projectId, optionId){
    var data = {
        "projectId" : projectId,
        "optionId"  : optionId
    };

    $.ajax({
        url: "/creator/project/reward/remove-confirmation",
        data: data,
        success: function(data){
            console.log(data);
            $("#removeRewardHolder").html(data);
            $("#removeRewardModal").modal("show");
        },
        error: function(data){
            console.log(data);
        }
    })
}

function deleteReward(projectId, optionId){
    var data = {
        "projectId" : projectId,
        "optionId"  : optionId
    };
    $("#removeRewardModal").modal("hide");
    $.ajax({
        url: "/creator/project/reward/remove",
        data: data,
        type: "POST",
        success: function(data){
            console.log(data);
            $("#reward-list-div").html(data);
            toastr.success('Delete successfully');
        },
        error: function(data){
            console.log(data);
            toastr.warning('Delete failed');
        }
    })
}

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
            toastr.success("Save successfully");
        },
        error: function(data){
            console.log(data);
            toastr.success("Save failed");
        }
    })
}



function updateItem(){
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
            toastr.success("Save successfully");
        },
        error: function(data){
            console.log(data);
            toastr.success("Save failed");
        }
    })
}


function showItemDeleteConfirmation(projectId, itemId){
    debugger
    var data ={
        "projectId" : projectId,
        "itemId" : itemId
    }
    $.ajax({
        url: "/creator/project/delete-item-confirmation",
        data: data,
        type: "POST",
        success: function(data){
            console.log(data);
            $("#itemDelConfirmationHolder").html(data);
            $("#itemDelConfirmation").modal("show");
        },
        error: function(data){
            console.log(data);
        }

    })
}

function deleteItem(projectId, itemId){
    var data ={
        "projectId": projectId,
        "itemId" : itemId
    }
    $("#itemDelConfirmation").modal("hide");
    $.ajax({
        url: "/creator/project/delete-item",
        data: data,
        type: "POST",
        success: function(data){
            console.log(data);
            $("#item-list-div").html(data);
            toastr.success('Delete successfully');
        },
        error: function(data){
            console.log(data);
            toastr.warning('Delete fail');
        }
    })
}