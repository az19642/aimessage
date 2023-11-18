package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final SignupController signupController;

    private final JButton signUp;
    private final JButton goToLogin;

    public SignupView(SignupController controller, SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                      ViewManagerModel viewManagerModel) {

        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;

        signupViewModel.addPropertyChangeListener(this);

        // Set up main panel with grid layout
        GridLayout gridLayout = new GridLayout(6, 3);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        JPanel mainPanel = new JPanel(gridLayout);

        JLabel usernameLabel = new JLabel(SignupViewModel.USERNAME_LABEL);
        JLabel passwordLabel = new JLabel(SignupViewModel.PASSWORD_LABEL);
        JLabel repeatPasswordLabel = new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL);
        JLabel haveAccountLabel = new JLabel(SignupViewModel.HAVE_ACCOUNT_LABEL);

        usernameLabel.setHorizontalAlignment(JLabel.RIGHT);
        passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
        repeatPasswordLabel.setHorizontalAlignment(JLabel.RIGHT);
        haveAccountLabel.setHorizontalAlignment(JLabel.RIGHT);

        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        goToLogin = new JButton(SignupViewModel.GO_TO_LOGIN_BUTTON_LABEL);

        signUp.addActionListener(evt -> {
            if (evt.getSource().equals(signUp)) {
                SignupState currentState = signupViewModel.getState();

                signupController.execute(currentState.getUsername(), currentState.getPassword(),
                        currentState.getRepeatPassword(), ""
                );
            }
        });

        goToLogin.addActionListener(evt -> {
            if (evt.getSource().equals(goToLogin)) {
                viewManagerModel.setActiveView(loginViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SignupState currentState = signupViewModel.getState();
                String text = usernameInputField.getText() + e.getKeyChar();
                currentState.setUsername(text);
                signupViewModel.setState(currentState);
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
                SignupState currentState = signupViewModel.getState();
                currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                signupViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        repeatPasswordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                signupViewModel.setState(currentState); // Hmm, is this necessary?
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Add components to main panel in correct order; empty labels are for spacing
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameInputField);
        mainPanel.add(new JLabel(""));

        mainPanel.add(passwordLabel);
        mainPanel.add(passwordInputField);
        mainPanel.add(new JLabel(""));

        mainPanel.add(repeatPasswordLabel);
        mainPanel.add(repeatPasswordInputField);
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(signUp);
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));

        mainPanel.add(haveAccountLabel);
        mainPanel.add(goToLogin);
        mainPanel.add(new JLabel(""));

        // Create a grid panel to produce pseudo margins on the top and bottom; centers main panel
        JPanel pseudoMarginsPanel = new JPanel(new GridLayout(3, 1));
        pseudoMarginsPanel.add(new JLabel(""));
        pseudoMarginsPanel.add(mainPanel);
        pseudoMarginsPanel.add(new JLabel(""));

        // Add everything to (this) signup view panel
        this.add(pseudoMarginsPanel);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }
}