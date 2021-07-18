
$("project-basic-form").change(function() {
    $(this).data("changed","true");
});

$("input[type='reset']").click(function() {
    if($("project-basic-form").data("changed") == "true") {
        var discard = confirm("Some unsaved changes. Discard them ?");
        if(!discard) return false;
    }
});

function returnToOverviewPage(){
    var project_id = $("#inp_project_id").val();
    window.location.href = "/creator/project/" + project_id;
}