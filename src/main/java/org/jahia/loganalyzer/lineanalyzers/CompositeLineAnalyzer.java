package org.jahia.loganalyzer.lineanalyzers;

import org.jahia.loganalyzer.lineanalyzers.LineAnalyzer;
import org.jahia.loganalyzer.LogEntry;

import java.util.List;
import java.io.LineNumberReader;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Serge Huber
 * Date: 8 juil. 2008
 * Time: 10:31:22
 * To change this template use File | Settings | File Templates.
 */
public class CompositeLineAnalyzer implements LineAnalyzer {

    List<LineAnalyzer> lineAnalyzers;
    LineAnalyzer currentlyActiveAnalyzer = null;

    public CompositeLineAnalyzer(List<LineAnalyzer> lineAnalyzers) {
        this.lineAnalyzers = lineAnalyzers;        
    }

    public boolean isForThisAnalyzer(String line) {
        for (LineAnalyzer lineAnalyzer : lineAnalyzers) {
            if (lineAnalyzer.isForThisAnalyzer(line)) {
                if (lineAnalyzer != currentlyActiveAnalyzer) {
                    currentlyActiveAnalyzer.finishPreviousState();
                    currentlyActiveAnalyzer = lineAnalyzer;
                }
                return true;
            }
        }
        return false;
    }

    public void parseLine(String line, LineNumberReader lineNumberReader) throws IOException {
        for (LineAnalyzer lineAnalyzer : lineAnalyzers) {
            if (lineAnalyzer.isForThisAnalyzer(line)) {
                lineAnalyzer.parseLine(line, lineNumberReader);
            }
        }
    }

    public void finishPreviousState() {
        for (LineAnalyzer lineAnalyzer : lineAnalyzers) {
            lineAnalyzer.finishPreviousState();
        }
    }

    public void stop() throws IOException {
        for (LineAnalyzer lineAnalyzer : lineAnalyzers) {
            lineAnalyzer.stop();
        }
    }
}