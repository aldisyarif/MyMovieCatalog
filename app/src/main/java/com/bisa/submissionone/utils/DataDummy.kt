package com.bisa.submissionone.utils

import com.bisa.submissionone.data.source.local.entity.movies.ActorMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.DetailCatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.ActorTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.DetailCatalogTvEntity
import com.bisa.submissionone.data.source.remote.response.*

object DataDummy {

    fun generateListMovie(): List<CatalogMovieEntity>{
            val listMovie = ArrayList<CatalogMovieEntity>()

            listMovie.add(
                    CatalogMovieEntity(
                            dataId = "move1",
                            imagePoster = "https://www.themoviedb.org/t/p/original/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                            title = "A Star Is Born",
                        favorite = false
                    )
            )
            listMovie.add(
                    CatalogMovieEntity(
                            dataId = "move2",
                            imagePoster = "https://image.tmdb.org/t/p/original/aQXTw3wIWuFMy0beXRiZ1xVKtcf.jpg",
                            title = "Alita: Battle Angel",
                        favorite = false
                    )
            )
            listMovie.add(
                    CatalogMovieEntity(
                            dataId = "move3",
                            imagePoster = "https://image.tmdb.org/t/p/original/4IWnPqNu34zY4ku3LQJw56MIHFc.jpg",
                            title = "Aquaman",
                        favorite = false
                    )
            )
            listMovie.add(
                    CatalogMovieEntity(
                            dataId = "move4",
                            imagePoster = "https://image.tmdb.org/t/p/original/xcaSYLBhmDzJ6P14bcKe0KTh3QV.jpgc",
                            title = "Bohemian Rhapsody",
                        favorite = false
                    )
            )
            listMovie.add(
                    CatalogMovieEntity(
                            dataId = "move5",
                            imagePoster = "https://image.tmdb.org/t/p/original/XAvFHWmWjBeJUQEHBbCcXvzdDZ.jpg",
                            title = "Cold Pursuit",
                        favorite = false
                    )
            )
            listMovie.add(
                    CatalogMovieEntity(
                            dataId = "move6",
                            imagePoster = "https://image.tmdb.org/t/p/original/7568G5PAdQweNfTiuwzlssOxueB.jpg",
                            title = "Creed II",
                        favorite = false
                    )
            )
            listMovie.add(
                    CatalogMovieEntity(
                            dataId = "move7",
                            imagePoster = "https://image.tmdb.org/t/p/original/heyvaoVLGC8lcB4FFoz65EBI8xF.jpg",
                            title = "Fantastic Beasts: The Crimes of Grindelwald",
                        favorite = false
                    )
            )
            listMovie.add(
                    CatalogMovieEntity(
                            dataId = "move8",
                            imagePoster = "https://image.tmdb.org/t/p/original/ngBFDOsx13sFXiMweDoL54XYknR.jpg",
                            title = "Glass",
                        favorite = false
                    )
            )
            listMovie.add(
                    CatalogMovieEntity(
                            dataId = "move9",
                            imagePoster = "https://image.tmdb.org/t/p/original/h3KN24PrOheHVYs9ypuOIdFBEpX.jpg",
                            title = "How to Train Your Dragon: The Hidden World",
                        favorite = false
                    )
            )
            listMovie.add(
                    CatalogMovieEntity(
                            dataId = "move10",
                            imagePoster = "https://image.tmdb.org/t/p/original/kbGO5mHPK7rh516MgAIJUQ9RvqD.jpg",
                            title = "Avengers: Infinity War",
                        favorite = false
                    )
            )

            return listMovie
    }

    fun generateListTvShow(): List<CatalogTvEntity>{
        val listTvShow = ArrayList<CatalogTvEntity>()

        listTvShow.add(
                CatalogTvEntity(
                        dataId = "tv1",
                        imagePoster = "https://image.tmdb.org/t/p/original/elbLQbocvW9vwrHRjYTSjXr5BX5.jpg" ,
                        title = "The Arrow",
                    favorite = false
                )
        )

        listTvShow.add(
                CatalogTvEntity(
                        dataId = "tv2",
                        imagePoster = "https://image.tmdb.org/t/p/original/sAzw6I1G9JUxm86KokIDdQeWtaq.jpg" ,
                        title = "Doom Patrol",
                    favorite = false
                )
        )
        listTvShow.add(
                CatalogTvEntity(
                        dataId = "tv3",
                        imagePoster = "https://image.tmdb.org/t/p/original/igXpePfyVeuG50rvSVEay2u4I3R.jpg" ,
                        title = "Dragon Ball",
                    favorite = false
                )
        )
        listTvShow.add(
                CatalogTvEntity(
                        dataId = "tv4",
                        imagePoster = "https://image.tmdb.org/t/p/original/fANxNeH9JCXPrzNEfriGu1Y95dF.jpg" ,
                        title = "FAIRY TAIL",
                    favorite = false
                )
        )
        listTvShow.add(
                CatalogTvEntity(
                        dataId = "tv5",
                        imagePoster = "https://image.tmdb.org/t/p/original/hnK5vODlS1OIIF3Sw6T0RQyt0K3.jpg" ,
                        title = "Family Guy",
                    favorite = false
                )
        )
        listTvShow.add(
                CatalogTvEntity(
                        dataId = "tv6",
                        imagePoster = "https://image.tmdb.org/t/p/original/rkRqvadAVWzdnrS6bdcUAyJtfpy.jpg" ,
                        title = "The Flash",
                    favorite = false
                )
        )
        listTvShow.add(
                CatalogTvEntity(
                        dataId = "tv7",
                        imagePoster = "https://image.tmdb.org/t/p/original/xM8zPWNqwbgCZQNgOOH2YeM7Cu.jpg" ,
                        title = "Game of Thrones",
                    favorite = false
                )
        )
        listTvShow.add(
                CatalogTvEntity(
                        dataId = "tv8",
                        imagePoster = "https://image.tmdb.org/t/p/original/l0U4mNs2vp65AAbfH8v2ymij8T5.jpg" ,
                        title = "Gotham",
                    favorite = false
                )
        )
        listTvShow.add(
                CatalogTvEntity(
                        dataId = "tv9",
                        imagePoster = "https://image.tmdb.org/t/p/original/ym20NYY99jNH0OzSg4TgLLGsQF9.jpg" ,
                        title = "Grey's Anatomy",
                    favorite = false
                )
        )
        listTvShow.add(
                CatalogTvEntity(
                        dataId = "tv10",
                        imagePoster = "https://image.tmdb.org/t/p/original/ofjZbud67zO2wxQ48VgMVnkECQu.jpg" ,
                        title = "Hanna",
                    favorite = false
                )
        )

        return listTvShow
    }

    fun generateDetailMovie(): DetailCatalogMovieEntity {
        val detail =  DetailCatalogMovieEntity(
                dataId = "move2",
                imagePoster = "https://image.tmdb.org/t/p/original/xM8zPWNqwbgCZQNgOOH2YeM7Cu.jpg",
                tagline = "An angel falls. A warrior rises.",
                title = "Alita: Battle Angel",
                genre = "Action, Science Fiction, Adventure",
                release_date = "2019-01-31",
                duration = "122",
                score = "7.2",
                description = "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past."

        )

        return detail
    }

    fun generateDetailTvShow(): DetailCatalogTvEntity {
        val detail =  DetailCatalogTvEntity(
                dataId = "tv7",
                imagePoster = "https://image.tmdb.org/t/p/original/aQXTw3wIWuFMy0beXRiZ1xVKtcf.jpg",
                tagline = "Winter Is Coming",
                title = "Game of Thrones",
                genre = "Sci-Fi & Fantasy, Drama, Action & Adventure",
                release_date = "2011-04-17",
                duration = "60",
                score = "8.4",
                description = "The Great War has come, the Wall has fallen and the Night King's army of the dead marches towards Westeros. The end is here, but who will take the Iron Throne?"

        )

        return detail
    }

    fun generateActorMovie(): List<ActorMovieEntity>{
        val listActor = ArrayList<ActorMovieEntity>()

        listActor.add(
                ActorMovieEntity("https://www.themoviedb.org/t/p/original/DPnessSsWqVXRbKm93PtMjB4Us.jpg",
                        "Bradley Cooper",
                        "a1",
                        "move2")
        )
        listActor.add(
                ActorMovieEntity("https://www.themoviedb.org/t/p/original/4VogV8IgKrFYoVyjNuu5la1oMNp.jpg",
                        "Lady Gaga",
                        "a2",
                        "move2")
        )
        listActor.add(
                ActorMovieEntity("https://www.themoviedb.org/t/p/original/1K2IvGXFbKsgkExuUsRvy4F0c9e.jpg",
                        "Sam Elliot",
                        "a3",
                        "move2")
        )
        listActor.add(
                ActorMovieEntity("https://www.themoviedb.org/t/p/original/kq6APbPUbx0Mzoh6mK7k8Xoiw5m.jpg",
                        "Andrew Dice Clay",
                        "a4",
                        "move2")
        )
        listActor.add(
                ActorMovieEntity("https://www.themoviedb.org/t/p/original/avCWoO9fLwEhbT6cvu0TJcSj49g.jpg",
                        "Rafi Gavron",
                        "a5",
                        "move2")
        )

        return listActor
    }

    fun generateActorTvShow(): List<ActorTvEntity>{
        val listActor = ArrayList<ActorTvEntity>()

        listActor.add(
                ActorTvEntity("https://www.themoviedb.org/t/p/original/86jeYFV40KctQMDQIWhJ5oviNGj.jpg",
                        "Emilia Clarke",
                        "at1",
                        "tv7")
        )
        listActor.add(
                ActorTvEntity("https://www.themoviedb.org/t/p/original/xR2IBnBlUdyBe5hecaVdtRuQqUE.jpg",
                        "Lena Headey",
                        "at2",
                        "tv7")
        )
        listActor.add(
                ActorTvEntity("https://www.themoviedb.org/t/p/original/1hUAKYvSi4vZSYvTnD2TlqF6VVx.jpg",
                        "Sophie Turner",
                        "at3",
                        "tv7")
        )
        listActor.add(
                ActorTvEntity("https://www.themoviedb.org/t/p/original/wWTG27LBVTuHhIZ96aJcrkHuy8Z.jpg",
                        "Kit Harington",
                        "at4",
                        "tv7")
        )
        listActor.add(
                ActorTvEntity("https://www.themoviedb.org/t/p/original/lRsRgnksAhBRXwAB68MFjmTtLrk.jpg",
                        "Peter Dinklage",
                        "at5",
                        "tv7")
        )

        return listActor
    }



//Remote
    fun generateRemoteListMovie(): List<ResultsMovieItem>{
        val listMovie = ArrayList<ResultsMovieItem>()

        listMovie.add(
                ResultsMovieItem(
                        title = "A Star Is Born",
                        posterPath = "https://www.themoviedb.org/t/p/original/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                        id = 1
                )
        )
        listMovie.add(
                ResultsMovieItem(
                        title = "Alita: Battle Angel",
                        posterPath = "https://image.tmdb.org/t/p/original/aQXTw3wIWuFMy0beXRiZ1xVKtcf.jpg",
                        id = 2
                )
        )
        listMovie.add(
                ResultsMovieItem(
                        title = "Aquaman",
                        posterPath = "https://image.tmdb.org/t/p/original/4IWnPqNu34zY4ku3LQJw56MIHFc.jpg",
                        id = 3
                )
        )
        listMovie.add(
                ResultsMovieItem(
                        title = "Bohemian Rhapsody",
                        posterPath = "https://image.tmdb.org/t/p/original/xcaSYLBhmDzJ6P14bcKe0KTh3QV.jpgc",
                        id = 4
                )
        )
        listMovie.add(
                ResultsMovieItem(
                        title = "Cold Pursuit",
                        posterPath = "https://image.tmdb.org/t/p/original/XAvFHWmWjBeJUQEHBbCcXvzdDZ.jpg",
                        id = 5
                )
        )
        listMovie.add(
                ResultsMovieItem(
                        title = "Creed II",
                        posterPath = "https://image.tmdb.org/t/p/original/7568G5PAdQweNfTiuwzlssOxueB.jpg",
                        id = 6
                )
        )
        listMovie.add(
                ResultsMovieItem(
                        title = "Fantastic Beasts: The Crimes of Grindelwald",
                        posterPath = "https://image.tmdb.org/t/p/original/heyvaoVLGC8lcB4FFoz65EBI8xF.jpg",
                        id = 7
                )
        )
        listMovie.add(
                ResultsMovieItem(
                        title = "Glass",
                        posterPath = "https://image.tmdb.org/t/p/original/ngBFDOsx13sFXiMweDoL54XYknR.jpg",
                        id = 8
                )
        )
        listMovie.add(
                ResultsMovieItem(
                        title = "How to Train Your Dragon: The Hidden World",
                        posterPath = "https://image.tmdb.org/t/p/original/h3KN24PrOheHVYs9ypuOIdFBEpX.jpg",
                        id = 9
                )
        )
        listMovie.add(
                ResultsMovieItem(
                        title = "Avengers: Infinity War",
                        posterPath = "https://image.tmdb.org/t/p/original/kbGO5mHPK7rh516MgAIJUQ9RvqD.jpg",
                        id = 10
                )
        )
        return listMovie
    }

    fun generateRemoteListTvShow(): List<ResultsTvShowItem>{
        val listTvShow = ArrayList<ResultsTvShowItem>()

        listTvShow.add(
                ResultsTvShowItem(
                        posterPath = "https://image.tmdb.org/t/p/original/elbLQbocvW9vwrHRjYTSjXr5BX5.jpg",
                        name = "The Arrow",
                        id = 1
                )
        )

        listTvShow.add(
                ResultsTvShowItem(
                        posterPath = "https://image.tmdb.org/t/p/original/sAzw6I1G9JUxm86KokIDdQeWtaq.jpg",
                        name = "Doom Patrol",
                        id = 2
                )
        )
        listTvShow.add(
                ResultsTvShowItem(
                        posterPath = "https://image.tmdb.org/t/p/original/igXpePfyVeuG50rvSVEay2u4I3R.jpg",
                        name = "Dragon Ball",
                        id = 3
                )
        )
        listTvShow.add(
                ResultsTvShowItem(
                        posterPath = "https://image.tmdb.org/t/p/original/fANxNeH9JCXPrzNEfriGu1Y95dF.jpg",
                        name = "FAIRY TAIL",
                        id = 4
                )
        )
        listTvShow.add(
                ResultsTvShowItem(
                        posterPath = "https://image.tmdb.org/t/p/original/hnK5vODlS1OIIF3Sw6T0RQyt0K3.jpg",
                        name = "Family Guy",
                        id = 5
                )
        )
        listTvShow.add(
                ResultsTvShowItem(
                        posterPath = "https://image.tmdb.org/t/p/original/rkRqvadAVWzdnrS6bdcUAyJtfpy.jpg",
                        name = "The Flash",
                        id = 6
                )
        )
        listTvShow.add(
                ResultsTvShowItem(
                        posterPath = "https://image.tmdb.org/t/p/original/xM8zPWNqwbgCZQNgOOH2YeM7Cu.jpg",
                        name = "Game of Throne",
                        id = 7
                )
        )
        listTvShow.add(
                ResultsTvShowItem(
                        posterPath = "https://image.tmdb.org/t/p/original/l0U4mNs2vp65AAbfH8v2ymij8T5.jpg",
                        name = "Gotham",
                        id = 8
                )
        )
        listTvShow.add(
                ResultsTvShowItem(
                        posterPath = "https://image.tmdb.org/t/p/original/ym20NYY99jNH0OzSg4TgLLGsQF9.jpg",
                        name = "Grey's Anatomy",
                        id = 9
                )
        )
        listTvShow.add(
                ResultsTvShowItem(
                        posterPath = "https://image.tmdb.org/t/p/original/ofjZbud67zO2wxQ48VgMVnkECQu.jpg",
                        name = "Hanna",
                        id = 10
                )
        )

        return listTvShow
    }

    fun generateRemoteDetailMovie(): DetailCatalogMovieResponse {
        val detail =  DetailCatalogMovieResponse(
                id = 2,
                tagline = "An angel falls. A warrior rises.",
                title = "Alita: Battle Angel",
                genres = arrayListOf(
                        GenresItem("Action", 28),
                        GenresItem("Science Fiction",878 ),
                        GenresItem("Adventure", 12)
                ),
                overview = "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                runtime = 122,
                posterPath = "https://image.tmdb.org/t/p/original/xM8zPWNqwbgCZQNgOOH2YeM7Cu.jpg",
                releaseDate = "2019-01-31",
                voteAverage = 7.2,
        )

        return detail
    }

    fun generateRemoteDetailTvShow(): DetailCatalogTvResponse {
        val detail =  DetailCatalogTvResponse(
                id = 7,
                tagline = "Winter Is Coming",
                genres = arrayListOf(
                        GenresTvItem("Sci-Fi & Fantasy", 10765),
                        GenresTvItem("Drama", 18),
                        GenresTvItem("Action & Adventure", 10759)
                ),
                firstAirDate = "2011-04-17",
                overview = "The Great War has come, the Wall has fallen and the Night King's army of the dead marches towards Westeros. The end is here, but who will take the Iron Throne?",
                posterPath = "https://image.tmdb.org/t/p/original/aQXTw3wIWuFMy0beXRiZ1xVKtcf.jpg",
                voteAverage = 8.4,
                name = "Game of Thrones",
                episodeRunTime = listOf(
                        60
                )
        )
        return detail
    }

    fun generateRemoteActorMovie(): List<CastMovieItem>{
        val listActor = ArrayList<CastMovieItem>()


        listActor.add(
                CastMovieItem("Bradley Cooper",
                        "https://www.themoviedb.org/t/p/original/DPnessSsWqVXRbKm93PtMjB4Us.jpg")
        )
        listActor.add(
                CastMovieItem(
                        "Lady Gaga",
                        "https://www.themoviedb.org/t/p/original/4VogV8IgKrFYoVyjNuu5la1oMNp.jpg")
        )
        listActor.add(
                CastMovieItem(
                        "Sam Elliot",
                        "https://www.themoviedb.org/t/p/original/1K2IvGXFbKsgkExuUsRvy4F0c9e.jpg")
        )
        listActor.add(
                CastMovieItem(
                        "Andrew Dice Clay",
                        "https://www.themoviedb.org/t/p/original/kq6APbPUbx0Mzoh6mK7k8Xoiw5m.jpg")
        )
        listActor.add(
                CastMovieItem(
                        "Rafi Gavron",
                        "https://www.themoviedb.org/t/p/original/avCWoO9fLwEhbT6cvu0TJcSj49g.jpg")
        )

        return listActor
    }

    fun generateRemoteActorTvShow(): List<CastTvItem>{
        val listActor = ArrayList<CastTvItem>()

        listActor.add(
                CastTvItem(
                        "Emilia Clarke",
                        "https://www.themoviedb.org/t/p/original/86jeYFV40KctQMDQIWhJ5oviNGj.jpg")
        )
        listActor.add(
                CastTvItem(
                        "Lena Headey",
                        "https://www.themoviedb.org/t/p/original/xR2IBnBlUdyBe5hecaVdtRuQqUE.jpg")
        )
        listActor.add(
                CastTvItem(
                        "Sophie Turner",
                        "https://www.themoviedb.org/t/p/original/1hUAKYvSi4vZSYvTnD2TlqF6VVx.jpg")
        )
        listActor.add(
                CastTvItem(
                        "Kit Harington",
                        "https://www.themoviedb.org/t/p/original/wWTG27LBVTuHhIZ96aJcrkHuy8Z.jpg")
        )
        listActor.add(
                CastTvItem(
                        "Peter Dinklage",
                        "https://www.themoviedb.org/t/p/original/lRsRgnksAhBRXwAB68MFjmTtLrk.jpg")
        )

        return listActor
    }


}