var page = 1;
const key = ""

//ajax data 불러오기
function actorList(){
    $.ajax({
        type: "GET",
        url: "https://api.themoviedb.org/3/person/popular?" +
            `api_key=${key}` +
            "&language=ko&" +
            `page=${page}`,
        data: {},
        dataType:"json",
        success: function(data){

            let list = [];
            data.results.forEach((item)=>{
                list.push([
                    str = `<form action=/actor/${item.id} method="GET">`,
                    str += '<div>',
                    str += '<div class="actorImg">' + `<input type="image" name="profile" src=https://image.tmdb.org/t/p/w500${item.profile_path} onerror="this.src='/assets/null/null.png';">` + '</div>',
                    str += '<div class="actorName">' + item.name + '</div>',
                    str += `<input type="checkbox" class="dataId" name="actorId" value=${item.id} checked>`,
                    str += '</div>',
                    str += '</form>',
                    $('.actorList').append(str),
                ]);
            });
        }
    });
}
// 최초 목록 갱신
$(document).ready(function() {
    actorList();
});

// 글릭시 다음 페이지 갱신
$(document).ready(function() {
    $('.moreButton').click(function (){
        $('.moreButton').hide();
        page++
        //console.log(page)
        actorList();
    });
});

//스크롤 발생 이벤트 처리
$(document).ready(function(){
    $(window).scroll(function(){
        var scrT = $(window).scrollTop();
        //console.log(scrT);
        if(scrT == $(document).height() - $(window).height() && page != 1){
            page++
            actorList();
        }
    });
});







