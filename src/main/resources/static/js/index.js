
const key = "";
var totalStr = "";

$(document).ready(function () {

    loadMovieLankJSON();

    function loadMovieLankJSON() {
        $.ajax({
            type: 'get',
            url: '/reviewInfo/index',
            success: function (data) {
                //console.log(data);
                let list = [];
                data.forEach(function(item,idx){
                    let $idx = idx+1; //등수용 함수
                    list.push([
                        str = `<form action=/movie/${item.id} method="GET">`,
                        str += '<div class="listGroup'+idx+'"> <span class="top"> &nbsp&nbsp&nbsp '+$idx+'위' + '</span>',
                        str += '<div class="movieScore"> <span class="reviewAvg">'+item.reviewAvg+'</span></div>',
                        str += '<div class="movieTitle"> </div>',
                        str += '<input type="checkbox" class="dataId" name="movieId" value="'+item.movieId+'" checked>',
                        str += '</div>',
                        str += '</form>'
                    ]);
                    totalStr += str;
                    $('.movieList').append(str)
                });
            }
        });
    }

    //0.5초 늦게 생성되어서 loadMovieLankJSON() 실행되고 나서 값 불러옴
    setTimeout(function () {
       mappingMovieJSON();
    }, 150);

});

function mappingMovieJSON() {
    let start = 0; //1등부터 시작
    let end = 29; //데이터 최대 30개
    for (start; start <= end; start++) {
        let $mappingMovieId = $(".listGroup" + start + " input[name=movieId]");
        let movieIdSend = $mappingMovieId.val();
        //console.log("저장되어 있는 "+start+"movieId :"+movieIdSend);
            $.ajax({
                url: "https://api.themoviedb.org/3/movie/"+`${movieIdSend}?api_key=${key}`+"&language=ko",
                type:'get',
                async:false,
                dataType:'json',
                success: function (data) {
                   //console.log(data);
                   //console.log("start : "+start);
                        let $TMDbAvg = data.vote_average;
                        let $title = data.title;
                        let $img = '<input type="image" name="poster" class="moviePoster" src="https://image.tmdb.org/t/p/w500'+data.poster_path+'">';
                        //console.log($TMDbAvg);
                        //console.log($title);
                        //console.log($img);
                        $(".listGroup" + start).prepend($img);
                        $(".listGroup" + start + " .movieScore").prepend('TMDb <span class="tmdbAvg">'+$TMDbAvg+'</span> 망작선');
                        $(".listGroup" + start + " .movieTitle").prepend($title);

                }
            }); //ajax end

    } //for end
} //mappingMovieJSON() end


