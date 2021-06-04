let index = {
    init: function () {
        $("#btn-save").on("click", ()=>{ // function(){} => {} this를 바인딩하기 위해
            this.save();
        });
        $("#btn-delete").on("click", ()=>{ // function(){} => {} this를 바인딩하기 위해
            this.deleteById();
        });
        $("#btn-update").on("click", ()=>{ // function(){} => {} this를 바인딩하기 위해
            this.update();
        });
    },

    save: function () {
        // alert('user의 save함수 호출됨')
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type : "POST",
            url : "/api/board",
            data : JSON.stringify(data),
            contentType : "application/json; charset=utf-8",
            dataType : "json"
        }).done(function (resp){
            alert("Upload Complete.")
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    deleteById: function () {
        let id = $("#id").text();

        $.ajax({
            type : "DELETE",
            url : "/api/board/" + id,
            dataType : "json"
        }).done(function (resp){
            alert("Delete Complete.")
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update: function () {
        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type : "PUT",
            url : "/api/board/" + id,
            data : JSON.stringify(data),
            contentType : "application/json; charset=utf-8",
            dataType : "json"
        }).done(function (resp){
            alert("Modify Complete.")
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

index.init();