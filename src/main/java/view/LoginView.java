package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;

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

    public LoginView(LoginViewModel loginViewModel, SignupViewModel signupViewModel, LoginController controller,
                     ViewManagerModel viewManagerModel) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;

        GridLayout gridLayout = new GridLayout(4, 3);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        JPanel mainPanel = new JPanel(gridLayout);

        JLabel usernameLabel = new JLabel(LoginViewModel.USERNAME_LABEL);
        JLabel passwordLabel = new JLabel(LoginViewModel.PASSWORD_LABEL);

        usernameLabel.setHorizontalAlignment(JLabel.RIGHT);
        passwordLabel.setHorizontalAlignment(JLabel.RIGHT);

        logIn = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        cancel = new JButton(LoginViewModel.CANCEL_BUTTON_LABEL);

        logIn.addActionListener(evt -> {
            if (evt.getSource().equals(logIn)) {
                LoginState currentState = loginViewModel.getState();
                loginController.execute(
                        currentState.getUsername(),
                        currentState.getPassword()
                );
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

        mainPanel.add(usernameLabel);
        mainPanel.add(usernameInputField);
        mainPanel.add(new JLabel(""));

        mainPanel.add(passwordLabel);
        mainPanel.add(passwordInputField);
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(logIn);
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(cancel);
        mainPanel.add(new JLabel(""));

        JPanel pseudoMarginsPanel = new JPanel(new GridLayout(3, 1));
        pseudoMarginsPanel.add(new JLabel(""));
        pseudoMarginsPanel.add(mainPanel);
        pseudoMarginsPanel.add(new JLabel(""));

        this.add(pseudoMarginsPanel);
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

    /**
     * Sets the fields based on the provided LoginState.
     *
     * @param state The LoginState to set the fields from.
     */
    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

}