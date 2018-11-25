
mfilenames <- paste("MenTxt/", 1999:2012, ".txt", sep = "")
menFiles <- lapply(mfilenames, readLines)
names(menFiles) <- 1999:2012
menResMat <- lapply(menFiles, extractVariables)

menDF <- mapply(createDF, menResMat, year = 1999:2012, sex = rep("M", 14), SIMPLIFY=FALSE)
cbMen <- do.call(rbind, menDF)
save(cbMen, file = "cbMen.rda")

dim(cbMen)
