var page = 1;

$.ajax({
    type: "GET",
    url: "https://api.themoviedb.org/3/discover/movie?api_key=" +
        "" +
        "&language=ko&include_adult=false&include_video=false&" +
        `page=${page}` +
        "&vote_average.gte=0&vote_average.lte=60&with_watch_monetization_types=flatrate",
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
                str += '<div class="movieScore">' + '<span>' + `TMDB${item.vote_average} 망작선` + '</span>' + '</div>',
                str += '<div class="movieTitle">' + item.title + '</div>',
                str += '</div>',
            $('.movieList').append(str),
            ]);
        });
    }
})





