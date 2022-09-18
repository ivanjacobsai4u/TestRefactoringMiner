package org.example;

import org.eclipse.jgit.lib.Repository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.refactoringminer.api.GitHistoryRefactoringMiner;
import org.refactoringminer.api.GitService;
import org.refactoringminer.api.Refactoring;
import org.refactoringminer.api.RefactoringHandler;
import org.refactoringminer.rm1.GitHistoryRefactoringMinerImpl;
import org.refactoringminer.util.GitServiceImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ListBetweenCommits {
    public static void main(String[] args) throws Exception {
        JSONObject jsonObject = new JSONObject();
        GitService gitService = new GitServiceImpl();
        GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();

        Repository repo = gitService.cloneIfNotExists("tmp/refactoring-toy-example", "https://github.com/danilofes/refactoring-toy-example.git");

        miner.detectBetweenCommits(repo,  "802e21bffe95f0740f44d1a45e3c22adae0ba48c","0e193b7d02902c6f2abf7c88eebe937d1ac5fc51", new RefactoringHandler()
        {
          @Override
          public void handle(String commitId, List<Refactoring> refactorings) {
            System.out.println("Refactorings at " + commitId);
              try {
                    JSONArray ja = new JSONArray();
                   for (Refactoring ref : refactorings) {
                    ja.add(ref.toString());
                    System.out.println(ref.toString());
                    }

                    jsonObject.put(commitId, ja);
                    FileWriter file = new FileWriter("between_commits_history.json");
                    file.write(jsonObject.toJSONString());
                     file.close();
              } catch (IOException e) {
                  throw new RuntimeException(e);
              }

          }
        });
       /* UMLModel model1 = new UMLModelASTReader(new File("D:\\study\\rit\\DSCI.644.01-SW_Engineering_for_Data_Sci\\py4j")).getUmlModel();
        UMLModel model2 = new UMLModelASTReader(new File("D:\\study\\rit\\DSCI.644.01-SW_Engineering_for_Data_Sci\\py4j")).getUmlModel();
        UMLModelDiff modelDiff = model1.diff(model2);
        List<Refactoring> refactorings = modelDiff.getRefactorings();
                System.out.println(refactorings);*/
//            GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();
            /*miner.detectAtCommit("https://github.com/py4j/py4j.git",
                "f35b2c8eb8c320f173237e44d04eefb4634649a2", new RefactoringHandler() {
              @Override
              public void handle(String commitId, List<Refactoring> refactorings) {
                System.out.println("Refactorings at " + commitId);
                for (Refactoring ref : refactorings) {
                  System.out.println(ref.toString());
                }
              }
            }, 10);
       */
    }
        }