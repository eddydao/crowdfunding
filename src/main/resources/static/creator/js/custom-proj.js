// custom js of dev

$( document ).ready(function() {

    $('#editor').summernote({
        height: 550,
        maxHeight: 550,
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
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

    var thumbnailImg = document.getElementById("preview-img").src;
    if(thumbnailImg == null){
        $('#preview-img').attr('src', '../../images/bg-title-01.jpg');
    }
});

function uploadImage(image, projectId){
    var data = new FormData();
    data.append("image", image);
    data.append("projectId", projectId);
    $.ajax({
        url: "/creator/project/story/upload-image",
        cache: false,
        contentType : false,
        processData: false,
        data: data,
        type: "POST",
        success: function(filePath) {
            var image = $('<img>').attr('src', filePath).addClass("img-fluid");
            console.log(filePath);
            $('#editor').summernote("insertNode", image[0]);
        },
        error: function(filePath) {
            console.log(filePath);
        }
    })
}



var reader = new FileReader();
reader.onload = function (e) {
    var src = e.target.result;
    if (typeof(src) != 'undefined' && src != null){
        $('#preview-img').attr('src', src);
    }
}
   
function readURL(input) {
    if (input.files && input.files[0]) {
        reader.readAsDataURL(input.files[0]);
    }
}

$("#file-input").change(function(){
    readURL(this);
});

function showSpecEndDateInput() {
    if (document.getElementById('fixed-day').checked) {
        document.getElementById('inp-fixed-day').style.visibility = 'visible';
    }
    else { document.getElementById('inp-fixed-day').style.visibility = 'hidden'; }

    if (document.getElementById('spec-date').checked) {
        document.getElementById('inp-spec-date').style.visibility = 'visible';
    }
    else { document.getElementById('inp-spec-date').style.visibility = 'hidden';}

}

function openAddRewardModal(projectId){
    $.ajax({
        url: "/creator/project/" + projectId + "/create-reward-form",
        success: function(data){
            console.log(data);
            $("#createRewardModalHolder").html(data);
            $("#createRewardModal").modal("show");
        },
        error: function(data){
            console.log(data);
        }

    })
}

function openEditRewardModal(projectId, optionId){
    $.ajax({
        url: "/creator/project/" + projectId + "/reward/" + optionId,
        success: function(data){
            console.log(data);
            $("#editRewardModalHolder").html(data);
            $("#editRewardModal").modal("show");
        },
        error: function(data){
            console.log(data);
        }

    })
}

function openAddItemModal(projectId, optionId){
    var dataForm = new FormData();

    dataForm.append("projectId", projectId);
    dataForm.append("optionId", optionId);
    $.ajax({
        // url: "/creator/project/" + projectId + "/reward/" + optionId+ "/addItem",
        url:"/creator/project/reward/addItemModal",
        ache: false,
        contentType : false,
        processData: false,
        data: dataForm,
        type: "POST",
        success: function(data){
            console.log(data);
            $("#addItemModalHolder").html(data);
            $("#addItemModal").modal("show");
        },
        error: function(data){
            console.log(data);
        }
    })
}
