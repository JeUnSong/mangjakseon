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
            // console.log(data)
            // console.log(data.results)

            let list = [];
            data.results.forEach((item)=>{
                list.push([
                    str = '<div>',
                    str += '<div class="actorImg">' + `<img src=https://image.tmdb.org/t/p/w200${item.profile_path}>` + '</div>',
                    str += '<div class="actorName">' + item.name + '</div>',
                    str += '</div>',
                    $('.actorList').append(str),
                ]);
            });
        }
    });
}
// 최초 목록 갱신
actorList();

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







