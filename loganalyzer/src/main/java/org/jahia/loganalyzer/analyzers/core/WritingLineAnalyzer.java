package org.jahia.loganalyzer.analyzers.core;

import org.jahia.loganalyzer.LogEntry;
import org.jahia.loganalyzer.LogParserConfiguration;
import org.jahia.loganalyzer.writers.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class common to all line analyzers that produce output directly (which is almost all of them except the
 * default and composite ones)
 *
 * User: Serge Huber
 * Date: July 8th, 2008
 * Time: 12:30:27
 */
public abstract class WritingLineAnalyzer implements LineAnalyzer {

    private List<LogEntryWriter> detailsLogEntryWriters = new ArrayList<LogEntryWriter>();
    private List<LogEntryWriter> summaryLogEntryWriters = new ArrayList<LogEntryWriter>();

    public WritingLineAnalyzer(File detailsOutputFile, File summaryOutputFile, char csvOutputSeparatorChar, LogEntry logEntry, LogEntry summaryLogEntry, LogParserConfiguration logParserConfiguration) throws IOException {
        // CSV Output setup
        File csvDetailsFile = new File(detailsOutputFile.getPath() + ".csv");
        LogEntryWriter csvDetailsLogEntryWriter = new CSVLogEntryWriter(csvDetailsFile, csvOutputSeparatorChar, logEntry);
        detailsLogEntryWriters.add(csvDetailsLogEntryWriter);
        File csvSummaryFile = new File(summaryOutputFile.getPath() + ".csv");
        LogEntryWriter csvSummaryLogEntryWriter = new CSVLogEntryWriter(csvSummaryFile, csvOutputSeparatorChar, summaryLogEntry);
        summaryLogEntryWriters.add(csvSummaryLogEntryWriter);

        // HTML Output setup
        File htmlDetailFile = new File(detailsOutputFile.getPath() + ".html");
        LogEntryWriter htmlDetailsLogEntryWriter = new HTMLLogEntryWriter(htmlDetailFile, logEntry, logParserConfiguration);
        detailsLogEntryWriters.add(htmlDetailsLogEntryWriter);
        File htmlSummaryFile = new File(summaryOutputFile.getPath() + ".html");
        LogEntryWriter htmlSummaryLogEntryWriter = new HTMLLogEntryWriter(htmlSummaryFile, summaryLogEntry, logParserConfiguration);
        summaryLogEntryWriters.add(htmlSummaryLogEntryWriter);

        // JSON Output setup
        File jsonDetailFile = new File(detailsOutputFile.getPath() + ".json");
        LogEntryWriter jsonDetailsLogEntryWriter = new JSONLogEntryWriter(jsonDetailFile, logEntry);
        detailsLogEntryWriters.add(jsonDetailsLogEntryWriter);
        File jsonSummaryFile = new File(summaryOutputFile.getPath() + ".json");
        LogEntryWriter jsonSummaryLogEntryWriter = new JSONLogEntryWriter(jsonSummaryFile, summaryLogEntry);
        summaryLogEntryWriters.add(jsonSummaryLogEntryWriter);

        File elasticSearchDetailFile = new File(detailsOutputFile.getPath() + ".es");
        LogEntryWriter elasticSearchDetailWriter = new ElasticSearchLogEntryWriter(elasticSearchDetailFile, logEntry, logParserConfiguration);
        detailsLogEntryWriters.add(elasticSearchDetailWriter);
        File elasticSearchSummaryFile = new File(summaryOutputFile.getPath() + ".es");
        LogEntryWriter elasticSearchSummaryWriter = new ElasticSearchLogEntryWriter(elasticSearchSummaryFile, logEntry, logParserConfiguration);
        summaryLogEntryWriters.add(elasticSearchSummaryWriter);

    }

    public void stop() throws IOException {
        for (LogEntryWriter detailLogEntryWriter : detailsLogEntryWriters) {
            detailLogEntryWriter.close();
        }
        for (LogEntryWriter summaryLogEntryWriter : summaryLogEntryWriters) {
            summaryLogEntryWriter.close();
        }
    }

    public void writeDetails(LogEntry logEntry) {
        for (LogEntryWriter detailsLogEntryWriter : detailsLogEntryWriters) {
            detailsLogEntryWriter.write(logEntry);
        }
    }

    public void writeSummary(LogEntry logEntry) {
        for (LogEntryWriter summaryLogEntryWriter : summaryLogEntryWriters) {
            summaryLogEntryWriter.write(logEntry);
        }
    }

}