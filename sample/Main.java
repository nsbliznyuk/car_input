package sample;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Main extends Application {

    double carX = 256;
    double carY = 256;
    double speed = 1;

    public void start(Stage theStage) {
        theStage.setTitle("Пример работы с клавиатурой");

        // Готовим корневой элемент и сцену для окна
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        // Указываем размер канвы и добавляем ее в корневой элемент
        Canvas canvas = new Canvas(512, 512);
        root.getChildren().add(canvas);

        // Создаем список с кодами нажатых клавиш
        ArrayList<String> input = new ArrayList<String>();

        // Обработка события нажатия на клавишу
        theScene.setOnKeyPressed(
                keyEvent -> {
                    // код клавиши
                    String code = keyEvent.getCode().toString();
                    if (!input.contains(code))
                        input.add(code);
                });

        // обработка "отпускания" клавиши
        theScene.setOnKeyReleased(
                keyEvent -> {
                    String code = keyEvent.getCode().toString();
                    input.remove(code);
                });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Создаем объекты изображений
        Image road = new Image("road.png");
        Image car = new Image("car.png");

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Каждый раз очищаем канву
                gc.clearRect(0, 0, 512, 512);

                // Меняем изображение, в зависимости от кода клавиши
                if (input.contains("LEFT")) {
                    carX -= 1 * speed;
                }

                if (input.contains("RIGHT")) {
                    carX += 1 * speed;
                }

                gc.drawImage(road, 0, 0);
                gc.drawImage(car, carX, carY);
            }
        }.start();

        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
