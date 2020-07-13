package com.silvio.patterns.principles;

class InterfaceSegregationExample {
    public static void main(String[] args) {
        ClientSubsystem cs = new ClientSubsystem();
        cs.callIntegration("Message sent to backend");
    }
}

class ClientSubsystem {
    private IEnterpriseSubsystem ies;

    public void callIntegration(String msg) {
        System.out.println("Calling enterprise process msg: " + msg);
        ies = new EnterpriseSubsystem();
        ies.enterpriseProcess(msg);
    }
}

abstract interface IEnterpriseSubsystem {
    public void enterpriseProcess(String msg);
}

class EnterpriseSubsystem implements IEnterpriseSubsystem {

    private IBackEndSubsystem ibes;

    @Override
    public void enterpriseProcess(String msg) {
        System.out.println("Enterprise process received msg: " + msg);
        ibes = new BackEndSubsystem();
        ibes.backEndProcess(msg.toUpperCase());
    }
}

abstract interface IBackEndSubsystem {
    public void backEndProcess(String msg);
}

class BackEndSubsystem implements IBackEndSubsystem {
    @Override
    public void backEndProcess(String msg) {
        System.out.println("BackEnd process received msg: " + msg);
    }
}