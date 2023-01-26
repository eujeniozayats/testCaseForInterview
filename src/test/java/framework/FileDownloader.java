package framework;


import framework.browser.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class FileDownloader extends BaseEntity {

    private static int pollingInterval = 200;
    Path downloadsDirectory;

    public void verifyFileDownloaded() {
        info(BaseEntity.getLocale("loc.check.download"));

        File[] listOfFiles = BrowserFactory.folder.listFiles();

        assert listOfFiles != null;
        for (File listOfFile : listOfFiles) {
            listOfFile.isFile();
            listOfFile.isDirectory();
        }
    }

    public void deleteFileDownloaded() {
        info(BaseEntity.getLocale("loc.delete.download"));
        for (File file : Objects.requireNonNull(BrowserFactory.folder.listFiles())) file.delete();
        BrowserFactory.folder.delete();
    }

    public void waitForFileDownload(int totalTimeoutInMillis, String expectedFileName) {
        FluentWait<WebDriver> wait = new FluentWait(BaseEntity.browser.getDriver())
                .withTimeout(totalTimeoutInMillis, TimeUnit.MILLISECONDS)
                .pollingEvery(pollingInterval, TimeUnit.MILLISECONDS);
        File fileToCheck = getDownloadsDirectory()
                .resolve(expectedFileName)
                .toFile();

        wait.until((WebDriver wd) -> fileToCheck.exists());
    }

    public synchronized Path getDownloadsDirectory() {
        if (downloadsDirectory == null) {
            downloadsDirectory = BrowserFactory.folder.toPath();
        }
        return downloadsDirectory;
    }

    protected String formatLogMsg(final String message) {
        return message;
    }
}
