package io.shiftleft.fuzzyc2cpg.filewalker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UnorderedWalker extends SourceFileWalker {

  private UnorderedFileWalkerImpl sourceFileWalkerImpl = new UnorderedFileWalkerImpl();

  public UnorderedWalker() {
    sourceFileWalkerImpl.setFilenameFilter(defaultFileNameFilter);
  }

  public void setFilenameFilter(String filter) {
    sourceFileWalkerImpl.setFilenameFilter(filter);
  }

  public void addListener(SourceFileListener listener) {
    sourceFileWalkerImpl.addListener(listener);
  }

  protected void walkExistingFileOrDirectory(String dirName)
      throws IOException {
    Path dir = Paths.get(dirName);
    Files.walkFileTree(dir, sourceFileWalkerImpl);
  }

}
