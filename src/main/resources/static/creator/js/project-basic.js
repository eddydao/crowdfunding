
$("project-basic-form").change(function() {
    $(this).data("changed","true");
});

$("input[type='reset']").click(function() {
    if($("project-basic-form").data("changed") == "true") {
        var discard = confirm("Some unsaved changes. Discard them ?");
        if(!discard) return false;
    }
});

$("project-basic-form").change(function() {
    $(this).data("changed","true");
});

function returnToOverviewPage(){
    var project_id = $("#inp_project_id").val();
    window.location.href = "/creator/project/" + project_id;
}


$(document).ready(function(){
    // if project editable is set to close - cannot be able to edit the information of project
    if (projectEditable == '1') {
        $("#projectName").attr('readOnly', true);
        $("#subTitle").attr('readOnly', true);
        $("#categoryId").attr('disabled', true);
        $("#file-input").attr('disabled', true);
        $("#start-date").attr('readOnly', true);
        $("#end-date").attr('readOnly', true);
        $("#text-input-goal").attr('readOnly', true);
        $("#btn_save").attr('disabled', true);
    }
})