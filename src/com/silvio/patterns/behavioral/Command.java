package com.silvio.patterns.behavioral;

import java.util.ArrayList;
import java.util.List;

interface TextFileOperation {
    String execute();
}

class OpenTextFileOperation implements TextFileOperation {

    private TextFile textFile;

    public OpenTextFileOperation(TextFile textFile) { this.textFile = textFile; }

    @Override
    public String execute() { return textFile.open(); }

    @Override
    public String toString() {
        return "OpenTextFileOperation {" +
                "textFile=" + textFile +
                '}';
    }
}

class SaveTextFileOperation implements TextFileOperation {

    private TextFile textFile;

    public SaveTextFileOperation(TextFile textFile) { this.textFile = textFile; }

    @Override
    public String execute() { return textFile.save(); }

    @Override
    public String toString() {
        return "SaveTextFileOperation {" +
                "textFile=" + textFile +
                '}';
    }
}

class TextFile {

    private String name;

    public TextFile(String name) { this.name = name; }
    public String open() { return "Opening file " + name; }
    public String save() { return "Saving file " + name; }

    @Override
    public String toString() {
        return "TextFile {" +
                "name='" + name + '\'' +
                '}';
    }
}

class TextFileOperationExecutor {

    private final List<TextFileOperation> textFileOperations = new ArrayList<>();

    public String executeOperation(TextFileOperation textFileOperation) {
        textFileOperations.add(textFileOperation);
        return textFileOperation.execute();
    }

    public void showTextFileOperations() {
        System.out.println("------ Operations executed ------");
        for (TextFileOperation operation: this.textFileOperations) {
            System.out.println(operation);
        }
    }
}

class CommandExample {

    public static void main(String[] args) {

        TextFileOperationExecutor executor = new TextFileOperationExecutor();
        executor.executeOperation(new OpenTextFileOperation(new TextFile("file1.txt")));
        executor.executeOperation(new SaveTextFileOperation(new TextFile("file1.txt")));
        executor.executeOperation(new OpenTextFileOperation(new TextFile("file2.txt")));

        //Summary
        executor.showTextFileOperations();
    }
}
