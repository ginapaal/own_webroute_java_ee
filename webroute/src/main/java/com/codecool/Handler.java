package com.codecool;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

public class Handler implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {
        // creating object of TestPath class
        Class<TestPath> testObj = TestPath.class;

        for (Method method : testObj.getDeclaredMethods()) {

            // get annotated methods
            if (method.isAnnotationPresent(WebRoute.class)) {

                WebRoute webRoute = method.getAnnotation(WebRoute.class);

                // if path given in annotation equals the route in browser
                if (webRoute.path().equals(httpExchange.getRequestURI().getPath())) {
                    try {
                        method.invoke(testObj.newInstance(), httpExchange);
                        System.out.println(String.format("Method name is %s", method.getName()));
                        // without this return statement app throws IOException :(headers already sent)
                        // code exits at this point
                        return;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }

        }
        // if annotation path doesn't equal url given in browser
        try {
            String response = "QUE?";
            System.out.println(response + httpExchange.getRequestURI().getPath());
            httpExchange.sendResponseHeaders(404, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
