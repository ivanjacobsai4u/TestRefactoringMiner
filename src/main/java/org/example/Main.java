package org.example;

import gr.uom.java.xmi.UMLModel;
import gr.uom.java.xmi.UMLModelASTReader;
import gr.uom.java.xmi.diff.UMLModelDiff;
import org.eclipse.jgit.lib.Repository;
import org.refactoringminer.api.*;
import org.refactoringminer.rm1.GitHistoryRefactoringMinerImpl;
import org.refactoringminer.util.GitServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        GitService gitService = new GitServiceImpl();
        GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();

        Repository repo = gitService.cloneIfNotExists("tmp/refactoring-toy-example", "https://github.com/py4j/py4j.git");

        miner.detectAll(repo, "master", new RefactoringHandler() {
          @Override
          public void handle(String commitId, List<Refactoring> refactorings) {
            System.out.println("Refactorings at " + commitId);
            for (Refactoring ref : refactorings) {
              System.out.println(ref.toString());
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