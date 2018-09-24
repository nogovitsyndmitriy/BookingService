$(document).ready(function () {
    $('.addRoomBtn').click(function () {
        addRoom($(this));
    });
    $('.ReduceRoomBtn').click(function () {
        reduceRoom($(this));
    });
});

function addRoom(element) {
    var roomId = $(element).attr('id');
    var json = JSON.stringify(roomId);
    console.log(json);
    $.ajax({
        type: 'get',
        url: contextUrl + '/frontController?command=addRoom&roomId=' + roomId,
        data: {command: 'addRoom', id: roomId}
    }).done(function (data) {
        $('#count' + roomId).text(data);
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

function reduceRoom(element) {
    var roomId = $(element).attr('id');
    $.ajax({
        type: 'post',
        url: contextUrl + '/frontController?command=reduceRoom&roomId=' + roomId,
        data: {command: 'reduceRoom', id: roomId}
    }).done(function (data) {
        $('#count' + roomId).text(data);
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}