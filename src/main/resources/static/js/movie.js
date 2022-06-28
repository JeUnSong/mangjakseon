var page = 1;
const key = ""

//ajax data 불러오기
function movieList(){
    $.ajax({
        type: "GET",
        url: "https://api.themoviedb.org/3/discover/movie" +
            `?api_key=${key}` +
            "&language=ko&region=krr%2Cus" +
            "&sort_by=popularity.desc" +
            "&include_adult=false" +
            "&include_video=false" +
            `&page=${page}` +
            "&vote_average.gte=1" +
            "&vote_average.lte=6" +
            "&with_watch_monetization_types=flatrate",
        data: {},
        dataType:"json",
        success: function(data){

            // console.log(data)
            // console.log(data.results)

            let list = [];
            data.results.forEach((item)=>{
                list.push([
                    str = `<form action=/movie/${item.id} method="GET">`,
                    str += '<div>',
                    str += '<div class="moviePoster">' + `<input type="image" name="poster" src=https://image.tmdb.org/t/p/w500${item.poster_path}>` + '</div>',
                    str += '<div class="movieScore">' + '<span>' + `TMDB ${item.vote_average} 망작선` + '</span>' + '</div>',
                    str += '<div class="movieTitle">' + item.title + '</div>',
                    str += `<input type="checkbox" class="dataId" name="movieId" value=${item.id} checked>`,
                    str += '</div>',
                    str += '</form>',
                $('.movieList').append(str),
                ]);
            });
        }
    });
}
// 최초 목록 갱신
$(document).ready(function (){
    movieList();
})

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







