var page = 1;
const key = ""

//ajax data 불러오기
function movieList(){
    $.ajax({
        type: "GET",
        url: "https://api.themoviedb.org/3/discover/movie?" +
            `api_key=${key}` +
            "&language=ko&include_adult=false&include_video=false&" +
            `page=${page}` +
            "&vote_average.gte=0&vote_average.lte=6&with_watch_monetization_types=flatrate",
        data: {},
        dataType:"json",
        success: function(data){
            // console.log(data)
            // console.log(data.results)

            let list = [];
            data.results.forEach((item)=>{
                list.push([
                    str = '<div>',
                    str += '<div class="moviePoster">' + `<img src=https://image.tmdb.org/t/p/w200${item.poster_path}>` + '</div>',
                    str += '<div class="movieScore">' + '<span>' + `TMDB ${item.vote_average} 망작선` + '</span>' + '</div>',
                    str += '<div class="movieTitle">' + item.title + '</div>',
                    str += '</div>',
                $('.movieList').append(str),
                ]);
            });
        }
    });
}
// 최초 목록 갱신
movieList();

// 글릭시 다음 페이지 갱신
$(document).ready(function() {
    $('.moreButton').click(function (){
        $('.moreButton').hide();
        page++
        //console.log(page)
        movieList();
    });
});

//스크롤 발생 이벤트 처리
$(document).ready(function(){
    $(window).scroll(function(){
        var scrT = $(window).scrollTop();
        //console.log(scrT);
        if(scrT == $(document).height() - $(window).height() && page != 1){
            page++
            movieList();
        }
    });
});







