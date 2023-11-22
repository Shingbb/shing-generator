package com.shing.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "ASCIIArt", version = "ASCIIArt 1.0", mixinStandardHelpOptions = true)
public class ASCIIArt implements Runnable {

    @Option(names = {"-s", "--font-size"}, description = "Font size",required = false)
    int fontSize = 19;
    @Option(names = {"-l", "--language"}, description = "language")
    String language ="java";

    @Parameters(paramLabel = "<word>", defaultValue = "Hello, picocli",
            description = "Words to be translated into ASCII art.")
    private String[] words = {"Hello,", "picocli"};
    @Parameters(paramLabel = "<key>", defaultValue = "Hello, picocli",
            description = "key to be translated into ASCII art.")
    private String[] key = {"key,", "picocli"};

    @Override
    public void run() {
        // 自己实现业务逻辑
        System.out.println("fontSize = " + fontSize);
        System.out.println("words = " + String.join(",", words));
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ASCIIArt()).execute(args);
        System.exit(exitCode);
    }
}