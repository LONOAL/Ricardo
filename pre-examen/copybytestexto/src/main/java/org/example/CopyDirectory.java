package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

public class CopyDirectory {
    public static void main(String[] args) throws IOException {
        // Definir el directorio de origen y destino
        File sourceDir = new File("C:\\Users\\PC-LORENZO\\Downloads\\mc server");
        File destinationDir = new File("C:\\Users\\PC-LORENZO\\Downloads\\aiaiaia");

        // Copiar el directorio
        copyDirectory(sourceDir.toPath(), destinationDir.toPath());
    }

    public static void copyDirectory(Path source, Path destination) throws IOException {
        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);

        Files.walkFileTree(source, options, Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                Path targetFile = destination.resolve(source.relativize(file));
                Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) throws IOException {
                Path newDir = destination.resolve(source.relativize(dir));
                Files.createDirectories(newDir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
