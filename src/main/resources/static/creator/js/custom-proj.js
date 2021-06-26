// custom js of dev

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
    // debugger
    // var thumbnailImg = document.getElementById("preview-img").src;
    // if(thumbnailImg == null){
    //     $('#preview-img').attr('src', '../../images/bg-title-01.jpg');
    // }
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
            console.log(data);
            $("#addItemModalHolder").html(data);
            $("#addItemModal").modal("show");
        },
        error: function(data){
            console.log(data);
        }
    })
}


try {

    //Team chart
    var ctx = document.getElementById("team-chart");
    if (ctx) {
        ctx.height = 150;
        var myChart = new Chart(ctx, {
            type: 'line',
            data: {
                // labels: ["2021-05-17", "2011", "2012", "2013", "2014", "2015", "2016"],
                labels: Object.keys(chartData),
                type: 'line',
                defaultFontFamily: 'Poppins',
                datasets: [{
                    // data: [0, 7, 3, 5, 2, 10, 7],
                    label: "PLedge",
                    backgroundColor: 'rgba(0,103,255,.15)',
                    borderColor: 'rgba(0,103,255,0.5)',
                    borderWidth: 3.5,
                    pointStyle: 'circle',
                    pointRadius: 5,
                    pointBorderColor: 'transparent',
                    pointBackgroundColor: 'rgba(0,103,255,0.5)',
                    data: Object.keys(chartData).map(function(key) {return chartData[key];})
                },]
            },
            options: {
                responsive: true,
                tooltips: {
                    mode: 'index',
                    titleFontSize: 12,
                    titleFontColor: '#000',
                    bodyFontColor: '#000',
                    backgroundColor: '#fff',
                    titleFontFamily: 'Poppins',
                    bodyFontFamily: 'Poppins',
                    cornerRadius: 3,
                    intersect: false,
                },
                legend: {
                    display: false,
                    position: 'top',
                    labels: {
                        usePointStyle: true,
                        fontFamily: 'Poppins',
                    },


                },
                scales: {
                    xAxes: [{
                        display: true,
                        gridLines: {
                            display: false,
                            drawBorder: false
                        },
                        scaleLabel: {
                            display: false,
                            labelString: 'Month'
                        },
                        ticks: {
                            fontFamily: "Poppins"
                        }
                    }],
                    yAxes: [{
                        display: true,
                        gridLines: {
                            display: false,
                            drawBorder: false
                        },
                        scaleLabel: {
                            display: true,
                            labelString: 'Value',
                            fontFamily: "Poppins"
                        },
                        ticks: {
                            fontFamily: "Poppins"
                        }
                    }]
                },
                title: {
                    display: false,
                }
            }
        });
    }


} catch (error) {
    console.log(error);
}

try {

    //pie chart
    var ctx = document.getElementById("pieChart");
    if (ctx) {
        ctx.height = 200;
        var myChart = new Chart(ctx, {
            type: 'pie',
            data: {
                datasets: [{
                    data: Object.keys(optionPercent).map(function(key) {return optionPercent[key];}),
                    // data: [45, 25, 20, 10],
                    backgroundColor: getBackgroundColorPieChart(Object.keys(optionPercent).length),
                    hoverBackgroundColor: getHoverBackgroundColorPieChart(Object.keys(optionPercent).length)
                    //     "rgba(0, 123, 255,0.9)",
                    //     "rgba(0, 123, 255,0.7)",
                    //     "rgba(0, 123, 255,0.5)",
                    //     "rgba(0,0,0,0.07)"
                    // ]

                }],
                // labels: [
                //     "Green",
                //     "Green",
                //     "Green"
                // ]
                labels: Object.keys(optionPercent)

            },
            options: {
                legend: {
                    position: 'top',
                    labels: {
                        fontFamily: 'Poppins'
                    }

                },
                responsive: true
            }
        });
    }


} catch (error) {
    console.log(error);
}

function getBackgroundColorPieChart(dataLength) {
    var colors = [];
    for(var i =0; i < dataLength; i++){
        colors.push("hsl(" + ( i / dataLength * 360) +" , 50%, 50%)" );
    }
    return colors;
}
function getHoverBackgroundColorPieChart(dataLength) {
    var colors = [];
    for(var i =0; i < dataLength; i++){
        colors.push("hsl(" + ( i / dataLength * 360) +" , 75%, 50%)" );
    }
    return colors;
}
