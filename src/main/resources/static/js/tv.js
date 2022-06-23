var page = 1;
const key = ""

//ajax data 불러오기
function tvList(){
    $.ajax({
        type: "GET",
        url: "https://api.themoviedb.org/3/discover/tv?" +
            `api_key=${key}` +
            "&language=ko&sort_by=popularity.desc&" +
            `page=${page}` +
            "&timezone=America%2FNew_York&&vote_average.gte=0&vote_average.lte=6&include_null_first_air_dates=false&with_watch_monetization_types=flatrate&with_status=0&with_type=0",
        data: {},
        dataType:"json",
        success: function(data){
            // console.log(data)
            // console.log(data.results)

            let list = [];
            data.results.forEach((item)=>{
                list.push([
                    str = '<div>',
                    str += '<div class="moviePoster">' + `<input type="image" src=https://image.tmdb.org/t/p/w200${item.poster_path}>` + '</div>',
                    str += '<div class="movieScore">' + '<span>' + `TMDB ${item.vote_average} 망작선` + '</span>' + '</div>',
                    str += '<div class="movieTitle">' + item.name + '</div>',
                    str += '</div>',
                    $('.movieList').append(str),
                ]);
            });
        }
    });
}
// 최초 목록 갱신
tvList();

// 글릭시 다음 페이지 갱신
$(document).ready(function() {
    $('.moreButton').click(function (){
        $('.moreButton').hide();
        page++
        //console.log(page)
        tvList();
    });
});

//스크롤 발생 이벤트 처리
$(document).ready(function(){
    $(window).scroll(function(){
        var scrT = $(window).scrollTop();
        //console.log(scrT);
        if(scrT == $(document).height() - $(window).height() && page != 1){
            page++
            tvList();
        }
    });
});







