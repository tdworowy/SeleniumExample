package Utils;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class TestLogger {
    private Logger Log;
    private FileAppender fileAppender;
    private String testName;
    private final String TestLogsDir = "TestLogs";


    public TestLogger(String testName) {
        Log = Logger.getLogger(testName);
        this.testName = testName;
        addLogAppender(TestLogsDir +"/"+testName +"/"+testName+".log");
    }
    private void addLogAppender(String fileName) {
        String  appenderName = "FileLogger";
        Logger.getRootLogger().removeAppender(appenderName);

        fileAppender = new FileAppender();
        fileAppender.setName(appenderName);
        fileAppender.setFile(fileName);
        fileAppender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
        fileAppender.setThreshold(Level.DEBUG);
        fileAppender.setAppend(true);
        fileAppender.activateOptions();
        Logger.getRootLogger().addAppender(fileAppender);

    }

    public Logger getLog() {
        return Log;
    }
    public String getTestLogsDir() {
        return TestLogsDir;
    }
    public String getTestName() {
        return testName;
    }
}
