package com.silvio.patterns.behavioral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

//create an abstract class
abstract class Middleware {
    private Middleware next;

    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(String email, String password);
    protected boolean checkNext(String email, String password) {
        return this.next == null ? true : this.next.check(email, password);
    }
}

//inherit from the abstract class and implement the abstract methods
class ThrottlingMiddleware extends Middleware {
    private int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    public boolean check(String email, String password) {
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        } else request++;

        if (request > requestPerMinute) System.out.println("Request limit exceeded!");
        //each processor is responsible for delegating to the next processor
        return checkNext(email, password);
    }
}

//inherit from the abstract class and implement the abstract methods
class UserExistsMiddleware extends Middleware {
    private Server server;

    public UserExistsMiddleware(Server server) { this.server = server; }

    public boolean check(String email, String password) {
        if (!server.hasEmail(email)) {
            System.out.println("This email is not registered!");
            return false;
        }
        if (!server.isValidPassword(email, password)) {
            System.out.println("Wrong password!");
            return false;
        }
        //each processor is responsible for delegating to the next processor
        return checkNext(email, password);
    }
}

//inherit from the abstract class and implement the abstract methods
class RoleCheckMiddleware extends Middleware {

    public boolean check(String email, String password) {
        if (email.equals("silvio@silvio.com")) {
            System.out.println("Hello, Silvio!");
            return true;
        }
        System.out.println("Hello, user!");
        //each processor is responsible for delegating to the next processor
        return checkNext(email, password);
    }
}

class Server {
    private Map<String, String> users = new HashMap<>();
    private Middleware middleware;

    public void setMiddleware(Middleware middleware) { this.middleware = middleware; }

    public boolean logIn(String email, String password) {
        if (middleware.check(email, password)) {
            System.out.println("Authorization have been successful!");
            return true;
        }
        return false;
    }

    public void register(String email, String password) { users.put(email, password); }
    public boolean hasEmail(String email) { return users.containsKey(email); }
    public boolean isValidPassword(String email, String password) { return users.get(email).equals(password); }
}

public class ChainOfResponsabilityExample {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    private static void init() {
        server = new Server();
        server.register("silvio@user.com", "silvio");
        server.register("user@user.com", "user");

        Middleware middleware = new ThrottlingMiddleware(2);
        middleware.linkWith(new UserExistsMiddleware(server))
                .linkWith(new RoleCheckMiddleware());

        server.setMiddleware(middleware);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean success;
        do {
            System.out.print("Enter email: ");
            String email = reader.readLine();
            System.out.print("Input password: ");
            String password = reader.readLine();
            success = server.logIn(email, password);
        } while (!success);
    }
}
