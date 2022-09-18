# TestRefactoringMiner
build maven
change tmp folder and link to the github project in ListAllCommits.java


Repository repo = gitService.cloneIfNotExists("tmp/refactoring-toy-example", "https://github.com/danilofes/refactoring-toy-example.git");


run ListAllCommits
This will create json file with all commits and all detected refactorings 
From the json file choose start and end commit code and change it in the ListBetweenCommits.java

 miner.detectBetweenCommits(repo,  "802e21bffe95f0740f44d1a45e3c22adae0ba48c","0e193b7d02902c6f2abf7c88eebe937d1ac5fc51", new RefactoringHandler()

Run ListBetweenCommits.java will generate a json file with all refactorings between the 2 selected commits.
If empty swap the end and start codes.