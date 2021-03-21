// custom js of dev
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