package views;

import interface_adapters.ViewManagerModel;
import services.login.LoginState;
import services.login.interface_adapters.LoginController;
import services.login.interface_adapters.LoginViewModel;
import services.signup.interface_adapters.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Represents the view for the login screen.
 * Allows users to input their username and password, and provides login and cancel buttons.
 */
public class LoginView extends JPanel implements PropertyChangeListener {

    private static LoginView instance;
    public final String viewName = "log in";
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JButton logIn;
    private final JButton cancel;
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JLabel usernameErrorField = new JLabel();
    private final JLabel passwordErrorField = new JLabel();
    private final LoginController loginController;
    private final Font helveticaFontTwelve = new Font("Helvetica", Font.PLAIN, 12);
    private final Font helveticaFontFourteen = new Font("Helvetica", Font.PLAIN, 14);

    private LoginView(LoginViewModel loginViewModel, SignupViewModel signupViewModel, LoginController loginController,
                      ViewManagerModel viewManagerModel) {

        this.loginController = loginController;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;

        loginViewModel.addPropertyChangeListener(this);

        GridLayout gridLayout = new GridLayout(10, 3);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        JPanel mainPanel = new JPanel(gridLayout);

        JLabel usernameLabel = new JLabel(LoginViewModel.USERNAME_LABEL);
        JLabel passwordLabel = new JLabel(LoginViewModel.PASSWORD_LABEL);

        logIn = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        cancel = new JButton(LoginViewModel.CANCEL_BUTTON_LABEL);

        usernameLabel.setFont(helveticaFontFourteen);
        passwordLabel.setFont(helveticaFontFourteen);

        usernameLabel.setHorizontalAlignment(JLabel.RIGHT);
        passwordLabel.setHorizontalAlignment(JLabel.RIGHT);

        logIn.setFont(helveticaFontTwelve);
        cancel.setFont(helveticaFontTwelve);

        // Styling for input fields
        Color inputFieldBackground = new Color(240, 240, 240);

        usernameInputField.setBackground(inputFieldBackground);

        passwordInputField.setBackground(inputFieldBackground);

        addListeners();

        addComponents(mainPanel, usernameLabel, passwordLabel);

        mainPanel.setBackground(Color.WHITE);

        this.add(mainPanel);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }


    /**
     * Returns the singleton instance of LoginView.
     * If the instance is null, it initializes it with the provided parameters.
     *
     * @param loginViewModel   The ViewModel associated with the Login functionality.
     * @param signupViewModel  The ViewModel associated with the Signup functionality.
     * @param loginController  The controller associated with Login functionality.
     * @param viewManagerModel The Model associated with View Manager.
     * @return the singleton instance of LoginView.
     */
    public static LoginView getInstance(LoginViewModel loginViewModel, SignupViewModel signupViewModel,
                                        LoginController loginController, ViewManagerModel viewManagerModel) {
        if (instance == null) {
            instance = new LoginView(loginViewModel, signupViewModel, loginController, viewManagerModel);
        }
        return instance;
    }

    private void addListeners() {
        logIn.addActionListener(evt -> {
            if (evt.getSource().equals(logIn)) {
                LoginState currentState = loginViewModel.getState();
                loginController.execute(currentState.getUsername(), currentState.getPassword());
            }
        });

        cancel.addActionListener(evt -> {
            if (evt.getSource().equals(cancel)) {
                viewManagerModel.setActiveView(signupViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        passwordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private void addComponents(JPanel mainPanel, JLabel usernameLabel, JLabel passwordLabel) {
        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));

        mainPanel.add(usernameLabel);
        mainPanel.add(usernameInputField);
        mainPanel.add(new JLabel(""));

        mainPanel.add(passwordLabel);
        mainPanel.add(passwordInputField);
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(logIn);
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(cancel);
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));
    }

    /**
     * Handles property change events.
     *
     * @param evt The PropertyChangeEvent representing the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        if (state.getLoginError() != null) {
            JOptionPane.showMessageDialog(this, state.getLoginError());
        }

    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

    public JButton getCancelButton() {
        return cancel;
    }

    public JButton getLogInButton() {
        return logIn;
    }
}