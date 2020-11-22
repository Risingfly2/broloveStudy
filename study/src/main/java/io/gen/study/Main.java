package io.gen.study;

import io.gen.antlr.MuLexer;
import io.gen.antlr.MuParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception{

//        File file = new File("lex.txt");
//        File[] subs = file.listFiles();
//        for (File file1:  subs){
//            System.out.println(file1.getName());
//        }

        String str = "1+1";
        MuLexer lexer = new MuLexer(new ANTLRFileStream(str));
        MuParser parser = new MuParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.parse();
        EvalVisitor visitor = new EvalVisitor();
        visitor.visit(tree);
    }


}
