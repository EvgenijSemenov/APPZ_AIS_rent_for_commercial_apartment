import form.LoginForm;

public class Main {

    public static void main(String[] args) {
        TestData.initTestDatabaseData();
        LoginForm.form = new LoginForm();
        LoginForm.form.setVisible(true);
    }

}
