function showComment(projectId){

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
};

function btn_editProject_onclick(){
    let projectId = $("#inp-project-id").val();

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

function btn_deleteProject_onclick(projectId){
    let data = {
        "projectId": projectId
    };
    $.ajax({
        url: "/creator/project/delete-confirmation" ,
        data: data,
        success: function(data){
            console.log(data);
            $("#projectDelConfirmationModalHolder").html(data);
            $("#projectDelConfirmation").modal("show");
        },
        error: function(data){
            console.log(data);
        }
    })
}

function submitDeleteProject(projectId){
    let data = {
        "projectId": projectId
    };
    $("#projectDelConfirmation").modal("hide");
    $.ajax({
        url: "/creator/project/delete-project" ,
        data: data,
        type: "POST",
        success: function(data){
            console.log(data);
            $("#project-table").html(data);
            toastr.success('Delete successfully');
        },
        error: function(data){
            console.log(data);
        }
    })
}