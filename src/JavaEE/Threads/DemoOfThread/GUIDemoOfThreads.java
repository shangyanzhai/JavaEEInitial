package JavaEE.Threads.DemoOfThread;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * 3. 用在图形化编程（GUI），有一个执行流不能被阻塞（前台执行流），这个需要响应用户的动作。如果要执行时间较长的任务，使用线程执行。
 */
public class GUIDemoOfThreads extends Application{
    static long fib(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox box = new VBox();
        TextField tf = new TextField();
        Button button = new Button("计算");
        button.setOnAction(evt -> {
            int n = Integer.valueOf(tf.getText());
            new Thread(() -> {
                long r = fib(n);
                Platform.runLater(() -> {
                    // 回到主线程更新 UI
                    tf.setText(String.valueOf(r));
                });
            });
        });

        box.getChildren().addAll(tf, button);

        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.setTitle("计算 fib");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(GUIDemoOfThreads.class, args);
    }
}
