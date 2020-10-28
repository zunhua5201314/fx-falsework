package com.epri.fx.client.exception;

import javafx.application.Platform;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @description:
 * @className: AppRunException
 * @author: liwen
 * @date: 2020/8/5 23:58
 */
public class AppRunException {

    public static void showError(Thread t, Throwable e) {
        System.err.println("***Default exception handler***");
        if (Platform.isFxApplicationThread()) {
            showErrorDialog(e);
        } else {
            System.err.println("An unexpected error occurred in " + t);

        }
    }

    private static void showErrorDialog(Throwable e) {
        StringWriter errorMsg = new StringWriter();
        e.printStackTrace();
//        e.printStackTrace(new PrintWriter(errorMsg));

//        Stage dialog = new Stage();
//        dialog.initModality(Modality.APPLICATION_MODAL);
//        FXMLLoader loader = new FXMLLoader(Main.class.getResource("Error.fxml"));
//        try {
//            Parent root = loader.load();
//            ((ErrorController)loader.getController()).setErrorText(errorMsg.toString());
//            dialog.setScene(new Scene(root, 250, 400));
//            dialog.show();
//        } catch (IOException exc) {
//            exc.printStackTrace();
//        }
    }
}
