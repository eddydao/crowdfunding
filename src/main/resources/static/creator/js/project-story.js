function returnToOverviewPage(){
    var project_id = $("#inp_project_id").val();
    window.location.href = "/creator/project/" + project_id;
}

$( document ).ready(function() {
    $('#editor').summernote({
        height: 550,
        maxHeight: 550,
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'italic','clear']],
            ['fontname', ['fontname']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['insert', ['link', 'picture', 'video']]
        ],
        callbacks:{
            onImageUpload: function(image){
                uploadImage(image[0], projectId);
            }
        }
    });

    if(projectEditable == '1' && projectStatusId == '6'){
        $('#editor').summernote('disable');
        $('#btn-save-story').attr("disabled", true);
    }
});