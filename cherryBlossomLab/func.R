

#findColLocs - locates columns by finding blank spaces in the "=" row
findColLocs <- function(spacerRow) {
  
  spaceLocs <- gregexpr(" ", spacerRow)[[1]]          # populate vector with indices of ' ' in spacerRow
  
  rowLength <- nchar(spacerRow)                       # avoid problems with trailing ' '
  if (substring(spacerRow, rowLength, rowLength) != " ")
    return( c(0, spaceLocs, rowLength + 1))           # return column indicators
  else return(c(0, spaceLocs))
}


# selectCols - finds locations of columns we are searching for
selectCols <- function(colNames, headerRow, searchLocs) {
  
  # run findCol on each element in colNames
  sapply(colNames, function(name, headerRow, searchLocs) {
    startPos <- regexpr(name, headerRow)[[1]]         # finds the start position of the name we are looking for
    if (startPos == -1) return( c(NA, NA))            # ensure that a column with that name exists
    index <- sum(startPos >= searchLocs)              # the column's index in searchLocs
    c(searchLocs[index] + 1, searchLocs[index + 1])   # return this column's bounds
  },headerRow <- headerRow, searchLocs <- searchLocs )
}

extractVariables <- 
  function(file, varNames = c("name", "home", "ag", "gun",
                              "net", "time"))
  {
    # find the index of the row with =s
    eqIndex <- grep("^===", file)
    
    # extract the two key rows
    spacerRow <- file[eqIndex]
    headerRow <- tolower(file[ eqIndex - 1 ])
    
    # extract body
    body <- file[ -(1 : eqIndex) ]
    
    # remove annotations, footnotes, and blank lines
    removeLines <- c(grep("^\\#", body),grep("^\\*", body),grep("^[[:blank:]]*$", body))
    if(length(removeLines) > 0) body <- body[-(removeLines)]
    
    # Obtain the starting and ending positions of variables
    searchLocs <- findColLocs(spacerRow)
    locCols <- selectCols(varNames, headerRow, searchLocs)
    Values <- mapply(substr, list(body), start <- locCols[1, ], stop <- locCols[2, ])
    colnames(Values) <- varNames
    invisible(Values)
  }

createDF =
  function(Res, year, sex)
  {
    # Determine which time to use
    useTime <- if( !is.na(Res[1, 'net']) )
      Res[ , 'net']
    else if( !is.na(Res[1, 'gun']) )
      Res[ , 'gun']
    else
      Res[ , 'time']
    
    # Remove # and * and blanks from time
    useTime = gsub("[#\\*[:blank:]]", "", useTime)
    # Drop rows with no time
    Res = Res[ useTime != "", ]
    runTime = convertTime(useTime[ useTime != "" ])
    
    Results <- data.frame(year = rep(year, nrow(Res)),
                          sex = rep(sex, nrow(Res)),
                          name = Res[ , 'name'],
                          home = Res[ , 'home'],
                          age = as.numeric(Res[, 'ag']),
                          runTime = runTime,
                          stringsAsFactors = FALSE)
    invisible(Results)
  }

convertTime <- function(charTime) {
  sapply(charTime, function(time) {
    timePieces <- strsplit(time, ":")
    timePieces <- sapply(timePieces, as.numeric)
    
    if(length(timePieces) == 2) {
      as.numeric(timePieces[1]) + as.numeric(timePieces[2])/60
    } else {
      as.numeric(timePieces[1])*60 + as.numeric(timePieces[2]) + as.numeric(timePieces[3])/60
    }
  })
}