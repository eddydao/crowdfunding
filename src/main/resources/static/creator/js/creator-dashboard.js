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