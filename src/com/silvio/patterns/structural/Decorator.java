package com.silvio.patterns.structural;

//design the component interface
abstract interface IWebPage {
    public abstract void display();
}

//implement the interface with your base concrete component class
class BasicWebPage implements IWebPage {
    public void display() {
        System.out.println("Rendering any HTML");
    }
}

//implement the interface with your abstract decorator class
abstract class WebPageDecorator implements IWebPage {
    protected IWebPage page;

    public WebPageDecorator(IWebPage webpage) { this.page = webpage; }
    public void display() { this.page.display(); }
}

//inherit from the abstract decorator and implement the component interface with concrete decorator cl
class AuthorizedWebPage extends WebPageDecorator {

    public AuthorizedWebPage(IWebPage decoratedPage) { super(decoratedPage); }
    public void authorizedUser() { System.out.println("Authorizing user"); }

    public void display() {
        super.display();
        this.authorizedUser();
    }
}

//inherit from the abstract decorator and implement the component interface with concrete decorator cl
class AuthenticatedWebPage extends WebPageDecorator {

    public AuthenticatedWebPage(IWebPage decoratedPage) { super(decoratedPage); }
    public void authenticateUser() { System.out.println("Authenticating user"); }

    public void display() {
        super.display();
        this.authenticateUser();
    }
}

class DecoratorExample {
    public static void main(String[] args) {
        IWebPage myPage = new BasicWebPage();
        myPage = new AuthorizedWebPage(myPage);
        myPage = new AuthenticatedWebPage(myPage);
        myPage.display();
    }
}