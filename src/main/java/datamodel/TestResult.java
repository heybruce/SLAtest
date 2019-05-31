package datamodel;

import java.time.Instant;

public class TestResult {

    private String testName;
    private String country;
    private String browser;
    private Instant timeStarted;
    private Instant timeFinished;
    private long timeElapsed;
    private String env;
    private Boolean isSuccess = true;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getTestName() {
        return testName;
    }

    public String getBrowser() {
        return browser;
    }

    public String getCountry() {
        return country;
    }

    public Instant getTimeFinished() {
        return timeFinished;
    }

    public Instant getTimeStarted() {
        return timeStarted;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public void setTimeFinished(Instant timeFinished) {
        this.timeFinished = timeFinished;
    }

    public void setTimeStarted(Instant timeStarted) {
        this.timeStarted = timeStarted;
    }
}
