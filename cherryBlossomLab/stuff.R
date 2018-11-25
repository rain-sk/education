els <- readLines("MenTxt/2001.txt")
eqIndex <- grep("^===", els)
spacerRow <- els[eqIndex]
headerRow <- els[eqIndex - 1]
body <- els[ -(1:eqIndex) ]
headerRow <- tolower(headerRow)
ageStart <- regexpr("ag", headerRow)
ageStart
age <- substr(body, start = ageStart, stop = ageStart + 1)
head(age)
first3 <-  substr(els, 1, 3)
which(first3 == "===")
blankLocs <-  gregexpr(" ", spacerRow)
searchLocs <-  c(0, blankLocs[[1]])
Values <-  mapply(substr, list(body),
                start = searchLocs[ -length(searchLocs)] + 1,
                stop = searchLocs[ -1 ] - 1)
selectCols = function(colNames, headerRow, searchLocs) {
  sapply(colNames, function(name, headerRow, searchLocs)
  {
    startPos = regexpr(name, headerRow)[[1]]
    if (startPos == -1) return( c(NA, NA) )
    index = sum(startPos >= searchLocs)
    c(searchLocs[index] + 1, searchLocs[index + 1] - 1)
  },
  headerRow = headerRow, searchLocs = searchLocs )
}

convertTime <- function(charTime) {
  sapply(charTime, function(time) {
    timePieces <- strsplit(time, ":")
    timePieces <- sapply(timePieces, as.numeric)
    
    if(length(timePieces) == 2) {
      timePieces[1] + timePieces[2]/60
    } else {
      timePieces[1]*60 + timePieces[2] + timePieces[3]/60
    }
  })
}