hasGenre <- function(genreVector, search) {
  vecLen <- length(genreVector)
  found <- sapply(1:vecLen, function(index) {
    if(genreVector[index] == search) {
      return(1)
    }
  })
  if(1 %in% found) {
    return(1)
  }
  return(0)
}

addGenreIndicators <- function() {
  movies$action <- sapply(movies$Genres, hasGenre, search = "Action")
  movies$adventure <- sapply(movies$Genres, hasGenre, search = "Adventure")
  movies$animation <- sapply(movies$Genres, hasGenre, search = "Animation")
  movies$childrens <- sapply(movies$Genres, hasGenre, search = "Children's")
  movies$comedy <- sapply(movies$Genres, hasGenre, search = "Comedy")
  movies$crime <- sapply(movies$Genres, hasGenre, search = "Crime")
  movies$documentary <- sapply(movies$Genres, hasGenre, search = "Documentary")
  movies$drama <- sapply(movies$Genres, hasGenre, search = "Drama")
  movies$fantasy <- sapply(movies$Genres, hasGenre, search = "Fantasy")
  movies$filmnoir <- sapply(movies$Genres, hasGenre, search = "Film-Noir")
  movies$horror <- sapply(movies$Genres, hasGenre, search = "Horror")
  movies$musical <- sapply(movies$Genres, hasGenre, search = "Musical")
  movies$mystery <- sapply(movies$Genres, hasGenre, search = "Mystery")
  movies$romance <- sapply(movies$Genres, hasGenre, search = "Romance")
  movies$scifi <- sapply(movies$Genres, hasGenre, search = "Sci-Fi")
  movies$thriller <- sapply(movies$Genres, hasGenre, search = "Thriller")
  movies$war <- sapply(movies$Genres, hasGenre, search = "War")
  movies$western <- sapply(movies$Genres, hasGenre, search = "Western")
}

createMaleRatings <- function() {
  maleRatings <- unlist(users$Ratings[which(users$Sex=="M")])
  movieID <- Re(maleRatings)
  rating <- Im(maleRatings)
  
  maleRatings <- data.frame(movieID, rating)
  maleRatings$genre <- sapply(maleRatings$movieID, function(ID){
    unlist(movies$Genres[movies$MovieID==ID])
  })
  rm(movieID,rating)
  
  maleRatings$action <- sapply(maleRatings$genre, hasGenre, search = "Action")
  maleRatings$adventure <- sapply(maleRatings$genre, hasGenre, search = "Adventure")
  maleRatings$animation <- sapply(maleRatings$genre, hasGenre, search = "Animation")
  maleRatings$childrens <- sapply(maleRatings$genre, hasGenre, search = "Children's")
  maleRatings$comedy <- sapply(maleRatings$genre, hasGenre, search = "Comedy")
  maleRatings$crime <- sapply(maleRatings$genre, hasGenre, search = "Crime")
  maleRatings$documentary <- sapply(maleRatings$genre, hasGenre, search = "Documentary")
  maleRatings$drama <- sapply(maleRatings$genre, hasGenre, search = "Drama")
  maleRatings$fantasy <- sapply(maleRatings$genre, hasGenre, search = "Fantasy")
  maleRatings$filmnoir <- sapply(maleRatings$genre, hasGenre, search = "Film-Noir")
  maleRatings$horror <- sapply(maleRatings$genre, hasGenre, search = "Horror")
  maleRatings$musical <- sapply(maleRatings$genre, hasGenre, search = "Musical")
  maleRatings$mystery <- sapply(maleRatings$genre, hasGenre, search = "Mystery")
  maleRatings$romance <- sapply(maleRatings$genre, hasGenre, search = "Romance")
  maleRatings$scifi <- sapply(maleRatings$genre, hasGenre, search = "Sci-Fi")
  maleRatings$thriller <- sapply(maleRatings$genre, hasGenre, search = "Thriller")
  maleRatings$war <- sapply(maleRatings$genre, hasGenre, search = "War")
  maleRatings$western <- sapply(maleRatings$genre, hasGenre, search = "Western")
  return(maleRatings)
}

createFemaleRatings <- function() {
  femaleRatings <- unlist(users$Ratings[which(users$Sex=="F")])
  movieID <- Re(femaleRatings)
  rating <- Im(femaleRatings)
  
  femaleRatings <- data.frame(movieID, rating)
  femaleRatings$genre <- sapply(femaleRatings$movieID, function(ID){
    unlist(movies$Genres[movies$MovieID==ID])
  })
  rm(movieID,rating)
  
  femaleRatings$action <- sapply(femaleRatings$genre, hasGenre, search = "Action")
  femaleRatings$adventure <- sapply(femaleRatings$genre, hasGenre, search = "Adventure")
  femaleRatings$animation <- sapply(femaleRatings$genre, hasGenre, search = "Animation")
  femaleRatings$childrens <- sapply(femaleRatings$genre, hasGenre, search = "Children's")
  femaleRatings$comedy <- sapply(femaleRatings$genre, hasGenre, search = "Comedy")
  femaleRatings$crime <- sapply(femaleRatings$genre, hasGenre, search = "Crime")
  femaleRatings$documentary <- sapply(femaleRatings$genre, hasGenre, search = "Documentary")
  femaleRatings$drama <- sapply(femaleRatings$genre, hasGenre, search = "Drama")
  femaleRatings$fantasy <- sapply(femaleRatings$genre, hasGenre, search = "Fantasy")
  femaleRatings$filmnoir <- sapply(femaleRatings$genre, hasGenre, search = "Film-Noir")
  femaleRatings$horror <- sapply(femaleRatings$genre, hasGenre, search = "Horror")
  femaleRatings$musical <- sapply(femaleRatings$genre, hasGenre, search = "Musical")
  femaleRatings$mystery <- sapply(femaleRatings$genre, hasGenre, search = "Mystery")
  femaleRatings$romance <- sapply(femaleRatings$genre, hasGenre, search = "Romance")
  femaleRatings$scifi <- sapply(femaleRatings$genre, hasGenre, search = "Sci-Fi")
  femaleRatings$thriller <- sapply(femaleRatings$genre, hasGenre, search = "Thriller")
  femaleRatings$war <- sapply(femaleRatings$genre, hasGenre, search = "War")
  femaleRatings$western <- sapply(femaleRatings$genre, hasGenre, search = "Western")
  return(femaleRatings)
}

createGenderedGenrePrefs <- function() {
  genre <- sort(unique(unlist(movies$Genres)))
  genderedGenrePrefs <- data.frame(genre)
  rm(genre)
  
  # avg ratings
  actionRating <- mean(movies$AvgRating[movies$action == 1])
  adventureRating <- mean(movies$AvgRating[movies$adventure == 1])
  animationRating <- mean(movies$AvgRating[movies$animation == 1])
  childrensRating <- mean(movies$AvgRating[movies$childrens == 1])
  comedyRating <- mean(movies$AvgRating[movies$comedy == 1])
  crimeRating <- mean(movies$AvgRating[movies$crime == 1])
  documentaryRating <- mean(movies$AvgRating[movies$documentary == 1])
  dramaRating <- mean(movies$AvgRating[movies$drama == 1])
  fantasyRating <- mean(movies$AvgRating[movies$fantasy == 1])
  filmnoirRating <- mean(movies$AvgRating[movies$filmnoir == 1])
  horrorRating <- mean(movies$AvgRating[movies$horror == 1])
  musicalRating <- mean(movies$AvgRating[movies$musical == 1])
  mysteryRating <- mean(movies$AvgRating[movies$mystery == 1])
  romanceRating <- mean(movies$AvgRating[movies$romance == 1])
  scifiRating <- mean(movies$AvgRating[movies$scifi == 1])
  thrillerRating <- mean(movies$AvgRating[movies$thriller == 1])
  warRating <- mean(movies$AvgRating[movies$war == 1])
  westernRating <- mean(movies$AvgRating[movies$western == 1])
  
  genderedGenrePrefs$avgRating <- c(actionRating, adventureRating,
                                    animationRating, childrensRating, 
                                    comedyRating, crimeRating, documentaryRating, 
                                    dramaRating, fantasyRating, filmnoirRating, 
                                    horrorRating, musicalRating, mysteryRating, 
                                    romanceRating, scifiRating, thrillerRating, 
                                    warRating, westernRating)
  
  # avg score
  actionRating <- mean(movies$Score[movies$action == 1])
  adventureRating <- mean(movies$Score[movies$adventure == 1])
  animationRating <- mean(movies$Score[movies$animation == 1])
  childrensRating <- mean(movies$Score[movies$childrens == 1])
  comedyRating <- mean(movies$Score[movies$comedy == 1])
  crimeRating <- mean(movies$Score[movies$crime == 1])
  documentaryRating <- mean(movies$Score[movies$documentary == 1])
  dramaRating <- mean(movies$Score[movies$drama == 1])
  fantasyRating <- mean(movies$Score[movies$fantasy == 1])
  filmnoirRating <- mean(movies$Score[movies$filmnoir == 1])
  horrorRating <- mean(movies$Score[movies$horror == 1])
  musicalRating <- mean(movies$Score[movies$musical == 1])
  mysteryRating <- mean(movies$Score[movies$mystery == 1])
  romanceRating <- mean(movies$Score[movies$romance == 1])
  scifiRating <- mean(movies$Score[movies$scifi == 1])
  thrillerRating <- mean(movies$Score[movies$thriller == 1])
  warRating <- mean(movies$Score[movies$war == 1])
  westernRating <- mean(movies$Score[movies$western == 1])
  
  genderedGenrePrefs$avgScore <- c(actionRating, adventureRating,
                                    animationRating, childrensRating, 
                                    comedyRating, crimeRating, documentaryRating, 
                                    dramaRating, fantasyRating, filmnoirRating, 
                                    horrorRating, musicalRating, mysteryRating, 
                                    romanceRating, scifiRating, thrillerRating, 
                                    warRating, westernRating)
  
  # male ratings
  actionRating <- mean(maleRatings$rating[maleRatings$action == 1])
  adventureRating <- mean(maleRatings$rating[maleRatings$adventure == 1])
  animationRating <- mean(maleRatings$rating[maleRatings$animation == 1])
  childrensRating <- mean(maleRatings$rating[maleRatings$childrens == 1])
  comedyRating <- mean(maleRatings$rating[maleRatings$comedy == 1])
  crimeRating <- mean(maleRatings$rating[maleRatings$crime == 1])
  documentaryRating <- mean(maleRatings$rating[maleRatings$documentary == 1])
  dramaRating <- mean(maleRatings$rating[maleRatings$drama == 1])
  fantasyRating <- mean(maleRatings$rating[maleRatings$fantasy == 1])
  filmnoirRating <- mean(maleRatings$rating[maleRatings$filmnoir == 1])
  horrorRating <- mean(maleRatings$rating[maleRatings$horror == 1])
  musicalRating <- mean(maleRatings$rating[maleRatings$musical == 1])
  mysteryRating <- mean(maleRatings$rating[maleRatings$mystery == 1])
  romanceRating <- mean(maleRatings$rating[maleRatings$romance == 1])
  scifiRating <- mean(maleRatings$rating[maleRatings$scifi == 1])
  thrillerRating <- mean(maleRatings$rating[maleRatings$thriller == 1])
  warRating <- mean(maleRatings$rating[maleRatings$war == 1])
  westernRating <- mean(maleRatings$rating[maleRatings$western == 1])
  
  genderedGenrePrefs$avgMaleRating <- c(actionRating, adventureRating,
                                        animationRating, childrensRating, 
                                        comedyRating, crimeRating, documentaryRating, 
                                        dramaRating, fantasyRating, filmnoirRating, 
                                        horrorRating, musicalRating, mysteryRating, 
                                        romanceRating, scifiRating, thrillerRating, 
                                        warRating, westernRating)
  
  actionRating <- mean(femaleRatings$rating[femaleRatings$action == 1])
  adventureRating <- mean(femaleRatings$rating[femaleRatings$adventure == 1])
  animationRating <- mean(femaleRatings$rating[femaleRatings$animation == 1])
  childrensRating <- mean(femaleRatings$rating[femaleRatings$childrens == 1])
  comedyRating <- mean(femaleRatings$rating[femaleRatings$comedy == 1])
  crimeRating <- mean(femaleRatings$rating[femaleRatings$crime == 1])
  documentaryRating <- mean(femaleRatings$rating[femaleRatings$documentary == 1])
  dramaRating <- mean(femaleRatings$rating[femaleRatings$drama == 1])
  fantasyRating <- mean(femaleRatings$rating[femaleRatings$fantasy == 1])
  filmnoirRating <- mean(femaleRatings$rating[femaleRatings$filmnoir == 1])
  horrorRating <- mean(femaleRatings$rating[femaleRatings$horror == 1])
  musicalRating <- mean(femaleRatings$rating[femaleRatings$musical == 1])
  mysteryRating <- mean(femaleRatings$rating[femaleRatings$mystery == 1])
  romanceRating <- mean(femaleRatings$rating[femaleRatings$romance == 1])
  scifiRating <- mean(femaleRatings$rating[femaleRatings$scifi == 1])
  thrillerRating <- mean(femaleRatings$rating[femaleRatings$thriller == 1])
  warRating <- mean(femaleRatings$rating[femaleRatings$war == 1])
  westernRating <- mean(femaleRatings$rating[femaleRatings$western == 1])
  
  genderedGenrePrefs$avgFemaleRating <- c(actionRating, adventureRating, 
                                          animationRating, childrensRating, 
                                          comedyRating, crimeRating, 
                                          documentaryRating, dramaRating, 
                                          fantasyRating, filmnoirRating, horrorRating, 
                                          musicalRating, mysteryRating, romanceRating, 
                                          scifiRating, thrillerRating, warRating, 
                                          westernRating)
  rm(actionRating, adventureRating, animationRating,
     childrensRating, comedyRating, crimeRating,
     documentaryRating, dramaRating, fantasyRating,
     filmnoirRating, horrorRating, musicalRating,
     mysteryRating, romanceRating, scifiRating,
     thrillerRating, warRating, westernRating)
  
  genderedGenrePrefs$diff <- genderedGenrePrefs$avgMaleRating - genderedGenrePrefs$avgFemaleRating
  
  return(genderedGenrePrefs)
}

genreConnectivity <- function() {
  # makes table with rows and columns with names of each genre
  genreConnections <- data.frame(row.names =c("action", "adventure", "animation",
                                              "childrens", "comedy", "crime", 
                                              "documentary", "drama", "fantasy", 
                                              "filmnoir", "horror", "musical", 
                                              "mystery", "romance", "scifi", 
                                              "thriller", "war", "western"))
  #initialize everything with 0
  genreConnections$"action" <- 0
  genreConnections$"adventure" <- 0
  genreConnections$"animation" <-  0
  genreConnections$"childrens" <- 0
  genreConnections$"comedy" <- 0 
  genreConnections$"crime" <- 0
  genreConnections$"documentary" <- 0
  genreConnections$"drama" <- 0
  genreConnections$"fantasy" <- 0
  genreConnections$"filmnoir" <- 0
  genreConnections$"horror" <- 0
  genreConnections$"musical" <- 0
  genreConnections$"mystery" <- 0
  genreConnections$"romance" <- 0
  genreConnections$"scifi" <- 0
  genreConnections$"thriller" <- 0
  genreConnections$"war" <- 0
  genreConnections$"western" <- 0
  
  # populate the table
  r <- 1
  genreConnections$adventure[r] <- sum(movies$action == 1 & movies$adventure == 1)
  genreConnections$animation[r] <- sum(movies$action == 1 & movies$animation == 1)
  genreConnections$childrens[r] <- sum(movies$action == 1 & movies$childrens == 1)
  genreConnections$comedy[r] <- sum(movies$action == 1 & movies$comedy == 1)
  genreConnections$crime[r] <- sum(movies$action == 1 & movies$crime == 1)
  genreConnections$documentary[r] <- sum(movies$action == 1 & movies$documentary == 1)
  genreConnections$drama[r] <- sum(movies$action == 1 & movies$drama == 1)
  genreConnections$fantasy[r] <- sum(movies$action == 1 & movies$fantasy == 1)
  genreConnections$filmnoir[r] <- sum(movies$action == 1 & movies$filmnoir == 1)
  genreConnections$horror[r] <- sum(movies$action == 1 & movies$horror == 1)
  genreConnections$musical[r] <- sum(movies$action == 1 & movies$musical == 1)
  genreConnections$mystery[r] <- sum(movies$action == 1 & movies$mystery == 1)
  genreConnections$romance[r] <- sum(movies$action == 1 & movies$romance == 1)
  genreConnections$scifi[r] <- sum(movies$action == 1 & movies$scifi == 1)
  genreConnections$thriller[r] <- sum(movies$action == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$action == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$action == 1 & movies$western == 1)
  r <- 2
  genreConnections$animation[r] <- sum(movies$adventure == 1 & movies$animation == 1)
  genreConnections$childrens[r] <- sum(movies$adventure == 1 & movies$childrens == 1)
  genreConnections$comedy[r] <- sum(movies$adventure == 1 & movies$comedy == 1)
  genreConnections$crime[r] <- sum(movies$adventure == 1 & movies$crime == 1)
  genreConnections$documentary[r] <- sum(movies$adventure == 1 & movies$documentary == 1)
  genreConnections$drama[r] <- sum(movies$adventure == 1 & movies$drama == 1)
  genreConnections$fantasy[r] <- sum(movies$adventure == 1 & movies$fantasy == 1)
  genreConnections$filmnoir[r] <- sum(movies$adventure == 1 & movies$filmnoir == 1)
  genreConnections$horror[r] <- sum(movies$adventure == 1 & movies$horror == 1)
  genreConnections$musical[r] <- sum(movies$adventure == 1 & movies$musical == 1)
  genreConnections$mystery[r] <- sum(movies$adventure == 1 & movies$mystery == 1)
  genreConnections$romance[r] <- sum(movies$adventure == 1 & movies$romance == 1)
  genreConnections$scifi[r] <- sum(movies$adventure == 1 & movies$scifi == 1)
  genreConnections$thriller[r] <- sum(movies$adventure == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$adventure == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$adventure == 1 & movies$western == 1)
  r <- 3
  genreConnections$childrens[r] <- sum(movies$animation == 1 & movies$childrens == 1)
  genreConnections$comedy[r] <- sum(movies$animation == 1 & movies$comedy == 1)
  genreConnections$crime[r] <- sum(movies$animation == 1 & movies$crime == 1)
  genreConnections$documentary[r] <- sum(movies$animation == 1 & movies$documentary == 1)
  genreConnections$drama[r] <- sum(movies$animation == 1 & movies$drama == 1)
  genreConnections$fantasy[r] <- sum(movies$animation == 1 & movies$fantasy == 1)
  genreConnections$filmnoir[r] <- sum(movies$animation == 1 & movies$filmnoir == 1)
  genreConnections$horror[r] <- sum(movies$animation == 1 & movies$horror == 1)
  genreConnections$musical[r] <- sum(movies$animation == 1 & movies$musical == 1)
  genreConnections$mystery[r] <- sum(movies$animation == 1 & movies$mystery == 1)
  genreConnections$romance[r] <- sum(movies$animation == 1 & movies$romance == 1)
  genreConnections$scifi[r] <- sum(movies$animation == 1 & movies$scifi == 1)
  genreConnections$thriller[r] <- sum(movies$animation == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$animation == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$animation == 1 & movies$western == 1)
  r <- 4
  genreConnections$comedy[r] <- sum(movies$childrens == 1 & movies$comedy == 1)
  genreConnections$crime[r] <- sum(movies$childrens == 1 & movies$crime == 1)
  genreConnections$documentary[r] <- sum(movies$childrens == 1 & movies$documentary == 1)
  genreConnections$drama[r] <- sum(movies$childrens == 1 & movies$drama == 1)
  genreConnections$fantasy[r] <- sum(movies$childrens == 1 & movies$fantasy == 1)
  genreConnections$filmnoir[r] <- sum(movies$childrens == 1 & movies$filmnoir == 1)
  genreConnections$horror[r] <- sum(movies$childrens == 1 & movies$horror == 1)
  genreConnections$musical[r] <- sum(movies$childrens == 1 & movies$musical == 1)
  genreConnections$mystery[r] <- sum(movies$childrens == 1 & movies$mystery == 1)
  genreConnections$romance[r] <- sum(movies$childrens == 1 & movies$romance == 1)
  genreConnections$scifi[r] <- sum(movies$childrens == 1 & movies$scifi == 1)
  genreConnections$thriller[r] <- sum(movies$childrens == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$childrens == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$childrens == 1 & movies$western == 1)
  r <- 5
  genreConnections$crime[r] <- sum(movies$comedy == 1 & movies$crime == 1)
  genreConnections$documentary[r] <- sum(movies$comedy == 1 & movies$documentary == 1)
  genreConnections$drama[r] <- sum(movies$comedy == 1 & movies$drama == 1)
  genreConnections$fantasy[r] <- sum(movies$comedy == 1 & movies$fantasy == 1)
  genreConnections$filmnoir[r] <- sum(movies$comedy == 1 & movies$filmnoir == 1)
  genreConnections$horror[r] <- sum(movies$comedy == 1 & movies$horror == 1)
  genreConnections$musical[r] <- sum(movies$comedy == 1 & movies$musical == 1)
  genreConnections$mystery[r] <- sum(movies$comedy == 1 & movies$mystery == 1)
  genreConnections$romance[r] <- sum(movies$comedy == 1 & movies$romance == 1)
  genreConnections$scifi[r] <- sum(movies$comedy == 1 & movies$scifi == 1)
  genreConnections$thriller[r] <- sum(movies$comedy == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$comedy == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$comedy == 1 & movies$western == 1)
  r <- 6
  genreConnections$documentary[r] <- sum(movies$crime == 1 & movies$documentary == 1)
  genreConnections$drama[r] <- sum(movies$crime == 1 & movies$drama == 1)
  genreConnections$fantasy[r] <- sum(movies$crime == 1 & movies$fantasy == 1)
  genreConnections$filmnoir[r] <- sum(movies$crime == 1 & movies$filmnoir == 1)
  genreConnections$horror[r] <- sum(movies$crime == 1 & movies$horror == 1)
  genreConnections$musical[r] <- sum(movies$crime == 1 & movies$musical == 1)
  genreConnections$mystery[r] <- sum(movies$crime == 1 & movies$mystery == 1)
  genreConnections$romance[r] <- sum(movies$crime == 1 & movies$romance == 1)
  genreConnections$scifi[r] <- sum(movies$crime == 1 & movies$scifi == 1)
  genreConnections$thriller[r] <- sum(movies$crime == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$crime == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$crime == 1 & movies$western == 1)
  r <- 7
  genreConnections$drama[r] <- sum(movies$documentary == 1 & movies$drama == 1)
  genreConnections$fantasy[r] <- sum(movies$documentary == 1 & movies$fantasy == 1)
  genreConnections$filmnoir[r] <- sum(movies$documentary == 1 & movies$filmnoir == 1)
  genreConnections$horror[r] <- sum(movies$documentary == 1 & movies$horror == 1)
  genreConnections$musical[r] <- sum(movies$documentary == 1 & movies$musical == 1)
  genreConnections$mystery[r] <- sum(movies$documentary == 1 & movies$mystery == 1)
  genreConnections$romance[r] <- sum(movies$documentary == 1 & movies$romance == 1)
  genreConnections$scifi[r] <- sum(movies$documentary == 1 & movies$scifi == 1)
  genreConnections$thriller[r] <- sum(movies$documentary == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$documentary == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$documentary == 1 & movies$western == 1)
  r <- 8
  genreConnections$fantasy[r] <- sum(movies$drama == 1 & movies$fantasy == 1)
  genreConnections$filmnoir[r] <- sum(movies$drama == 1 & movies$filmnoir == 1)
  genreConnections$horror[r] <- sum(movies$drama == 1 & movies$horror == 1)
  genreConnections$musical[r] <- sum(movies$drama == 1 & movies$musical == 1)
  genreConnections$mystery[r] <- sum(movies$drama == 1 & movies$mystery == 1)
  genreConnections$romance[r] <- sum(movies$drama == 1 & movies$romance == 1)
  genreConnections$scifi[r] <- sum(movies$drama == 1 & movies$scifi == 1)
  genreConnections$thriller[r] <- sum(movies$drama == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$drama == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$drama == 1 & movies$western == 1)
  r <- 9
  genreConnections$filmnoir[r] <- sum(movies$fantasy == 1 & movies$filmnoir == 1)
  genreConnections$horror[r] <- sum(movies$fantasy == 1 & movies$horror == 1)
  genreConnections$musical[r] <- sum(movies$fantasy == 1 & movies$musical == 1)
  genreConnections$mystery[r] <- sum(movies$fantasy == 1 & movies$mystery == 1)
  genreConnections$romance[r] <- sum(movies$fantasy == 1 & movies$romance == 1)
  genreConnections$scifi[r] <- sum(movies$fantasy == 1 & movies$scifi == 1)
  genreConnections$thriller[r] <- sum(movies$fantasy == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$fantasy == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$fantasy == 1 & movies$western == 1)
  r <- 10
  genreConnections$horror[r] <- sum(movies$filmnoir == 1 & movies$horror == 1)
  genreConnections$musical[r] <- sum(movies$filmnoir == 1 & movies$musical == 1)
  genreConnections$mystery[r] <- sum(movies$filmnoir == 1 & movies$mystery == 1)
  genreConnections$romance[r] <- sum(movies$filmnoir == 1 & movies$romance == 1)
  genreConnections$scifi[r] <- sum(movies$filmnoir == 1 & movies$scifi == 1)
  genreConnections$thriller[r] <- sum(movies$filmnoir == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$filmnoir == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$filmnoir == 1 & movies$western == 1)
  r <- 11
  genreConnections$musical[r] <- sum(movies$horror == 1 & movies$musical == 1)
  genreConnections$mystery[r] <- sum(movies$horror == 1 & movies$mystery == 1)
  genreConnections$romance[r] <- sum(movies$horror == 1 & movies$romance == 1)
  genreConnections$scifi[r] <- sum(movies$horror == 1 & movies$scifi == 1)
  genreConnections$thriller[r] <- sum(movies$horror == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$horror == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$horror == 1 & movies$western == 1)
  r <- 12
  genreConnections$mystery[r] <- sum(movies$musical == 1 & movies$mystery == 1)
  genreConnections$romance[r] <- sum(movies$musical == 1 & movies$romance == 1)
  genreConnections$scifi[r] <- sum(movies$musical == 1 & movies$scifi == 1)
  genreConnections$thriller[r] <- sum(movies$musical == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$musical == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$musical == 1 & movies$western == 1)
  r <- 13
  genreConnections$romance[r] <- sum(movies$mystery == 1 & movies$romance == 1)
  genreConnections$scifi[r] <- sum(movies$mystery == 1 & movies$scifi == 1)
  genreConnections$thriller[r] <- sum(movies$mystery == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$mystery == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$mystery == 1 & movies$western == 1)
  r <- 14
  genreConnections$scifi[r] <- sum(movies$romance == 1 & movies$scifi == 1)
  genreConnections$thriller[r] <- sum(movies$romance == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$romance == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$romance == 1 & movies$western == 1)
  r <- 15
  genreConnections$thriller[r] <- sum(movies$scifi == 1 & movies$thriller == 1)
  genreConnections$war[r] <- sum(movies$scifi == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$scifi == 1 & movies$western == 1)
  r <- 16
  genreConnections$war[r] <- sum(movies$thriller == 1 & movies$war == 1)
  genreConnections$western[r] <- sum(movies$thriller == 1 & movies$western == 1)
  r <- 17
  genreConnections$western[r] <- sum(movies$war == 1 & movies$western == 1)
  rm(r)
  
  return(genreConnections)
}
