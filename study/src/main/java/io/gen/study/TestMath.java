package io.gen.study;

import io.gen.antlr.MathLexer;
import io.gen.antlr.MathParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Scanner;

public class TestMath {
    public static void main(String[] args) {

        MulOptVisitor optVisitor = new MulOptVisitor();
        optVisitor.visit(new MulOpt("1","*","2"));
        AddOptVisitor addOptVisitor = new AddOptVisitor();
        addOptVisitor.visit(new AddOpt("1", "+","2"));
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()){
//            String in = scanner.nextLine();
//            CharStream input = CharStreams.fromString(in);
//            MathLexer lexer = new MathLexer(input);
//            CommonTokenStream tokens = new CommonTokenStream(lexer);
//            MathParser parser = new MathParser(tokens);
//            ParseTree tree = parser.prog();
//            CalVisitor visitor = new CalVisitor();
//            visitor.visit(tree);
//        }

    }
}
