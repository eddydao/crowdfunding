// custom js of dev

$( document ).ready(function() {
    var thumbnailImg = document.getElementById("preview-img").src;
    if(thumbnailImg == null){
        $('#preview-img').attr('src', '../../images/bg-title-01.jpg');
    }
});



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

var toolbarOptions = [
    [{'header':1}, {'header':2}],
    ['bold', 'italic', 'underline'],
    ['blockquote'],
    [{ 'list': 'ordered'}, { 'list': 'bullet' }],
    [{ 'indent': '-1'}, { 'indent': '+1' }],
    [{ 'color': [] }, { 'background': [] }],
    ['image', 'link'],
    [{ 'align': [] }]
]
var options ={
    modules: {
        toolbar: {
            container: '#toolbar'
        }
    },
    placeholder: 'Your content here...',
    theme: 'snow'

}

var quill = new Quill('#editor', options);

function selectLocalImage() {
    const input = document.createElement('image-input');
    input.setAttribute('type', 'file');
    input.click();

    // Listen upload local image and save to server
    input.onchange = () => {
        const file = input.files[0];
        // upload to server here with js
        file.src = URL.createObjectURL(file);
        insertToEditor(file.src);
    };
}

function insertToEditor(url) {
    // push image url to rich editor.
    const range = editor.getSelection();
    quill.insertEmbed(range.index, 'image', `${url}`);
}

quill.getModule('toolbar').addHandler('image-upload', () => {
    selectLocalImage();
  });