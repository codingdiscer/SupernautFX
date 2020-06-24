package org.consensusj.supernautfx.sample.hello;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.consensusj.supernaut.Launcher;
import org.consensusj.supernaut.logging.JavaLoggingSupport;
import org.consensusj.supernautfx.micronaut.SfxFxmlLoaderFactory;
import org.consensusj.supernautfx.SfxForegroundApp;
import org.consensusj.supernautfx.micronaut.MicronautSfxLauncher;
import org.consensusj.supernautfx.test.NoopBackgroundApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.IOException;
import java.net.URL;

/**
 * A simple Supernaut.fx App implementing {@link SfxForegroundApp}.
 */
@Singleton
public class HelloForegroundApp implements SfxForegroundApp {
    private static final Logger log = LoggerFactory.getLogger(HelloForegroundApp.class);
    private final SfxFxmlLoaderFactory loaderFactory;


    public static void main(String[] args) {
        JavaLoggingSupport.configure(HelloForegroundApp.class, "org.consensusj.supernautfx.sample.testapp");
        getLauncher().launch(args);
    }

    private static Launcher getLauncher() {
        return new MicronautSfxLauncher(NoopBackgroundApp.class, HelloForegroundApp.class, true);
    }

    public HelloForegroundApp(SfxFxmlLoaderFactory loaderFactory) {
        log.info("Constructing Hello");
        this.loaderFactory = loaderFactory;
    }

    @Override
    public void init() {
        log.info("Initializing Hello");
    }

    @Override
    public void start(SfxMainView mainView) throws IOException {
        Stage primaryStage = mainView.optionalStage().orElseThrow();
        log.info("Starting Hello");
        FXMLLoader loader = loaderFactory.get(getFXMLUrl("MainWindow.fxml"));
        log.debug("primaryStage root FXML: {}", loader.getLocation());
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.setTitle("SupernautFX Hello");
        primaryStage.show();
    }
    
    private URL getFXMLUrl(String fileName) {
        return HelloForegroundApp.class.getResource(fileName);
    }

}