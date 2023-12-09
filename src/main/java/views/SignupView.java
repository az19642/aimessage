package views;

import interface_adapters.ViewManagerModel;
import services.generate_password.interface_adapters.GeneratePasswordController;
import services.generate_password.interface_adapters.GeneratePasswordState;
import services.generate_password.interface_adapters.GeneratePasswordViewModel;
import services.login.interface_adapters.LoginViewModel;
import services.signup.SignupState;
import services.signup.interface_adapters.SignupController;
import services.signup.interface_adapters.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * Represents the view for the signup screen.
 * Allows users to input their username and password, and provides signup and cancel buttons.
 */
public class SignupView extends JPanel implements PropertyChangeListener {
    private static SignupView instance;
    public final String viewName = "sign up";
    private final SignupViewModel signupViewModel;
    private final GeneratePasswordViewModel generatePasswordViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JComboBox<String> languageDropdown;
    private final SignupController signupController;
    private final GeneratePasswordController generatePasswordController;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JButton signUp;
    private final JButton goToLogin;
    private final JButton generatePassword;
    private final Font helveticaFontTwelve = new Font("Helvetica", Font.PLAIN, 12);
    private final Font helveticaFontFourteen = new Font("Helvetica", Font.PLAIN, 14);

    private SignupView(SignupController signUpController, SignupViewModel signupViewModel,
                       GeneratePasswordViewModel generatePasswordViewModel, LoginViewModel loginViewModel,
                       ViewManagerModel viewManagerModel, GeneratePasswordController generatePasswordController) {

        this.signupController = signUpController;
        this.signupViewModel = signupViewModel;
        this.generatePasswordViewModel = generatePasswordViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.generatePasswordController = generatePasswordController;

        signupViewModel.addPropertyChangeListener(this);

        // Set up main panel with grid layout
        GridLayout gridLayout = new GridLayout(12, 3);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        JPanel mainPanel = new JPanel(gridLayout);

        JLabel titleLabel = new JLabel(SignupViewModel.TITLE_LABEL);
        JLabel groupLabel = new JLabel(SignupViewModel.GROUP_LABEL);
        JLabel usernameLabel = new JLabel(SignupViewModel.USERNAME_LABEL);
        JLabel passwordLabel = new JLabel(SignupViewModel.PASSWORD_LABEL);
        JLabel repeatPasswordLabel = new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL);
        JLabel preferredLanguageLabel = new JLabel(SignupViewModel.PREFERRED_LANGUAGE_LABEL);
        JLabel haveAccountLabel = new JLabel(SignupViewModel.HAVE_ACCOUNT_LABEL);

        languageDropdown = new JComboBox<>(SignupViewModel.languages);
        generatePassword = new JButton(SignupViewModel.GENERATE_PASSWORD_LABEL);
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        goToLogin = new JButton(SignupViewModel.GO_TO_LOGIN_BUTTON_LABEL);

        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        groupLabel.setFont(helveticaFontTwelve);
        usernameLabel.setFont(helveticaFontFourteen);
        passwordLabel.setFont(helveticaFontFourteen);
        repeatPasswordLabel.setFont(helveticaFontFourteen);
        preferredLanguageLabel.setFont(helveticaFontFourteen);
        haveAccountLabel.setFont(helveticaFontFourteen);

        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        groupLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setHorizontalAlignment(JLabel.RIGHT);
        passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
        repeatPasswordLabel.setHorizontalAlignment(JLabel.RIGHT);
        preferredLanguageLabel.setHorizontalAlignment(JLabel.RIGHT);
        haveAccountLabel.setHorizontalAlignment(JLabel.RIGHT);

        signUp.setFont(helveticaFontTwelve);
        goToLogin.setFont(helveticaFontTwelve);
        generatePassword.setFont(helveticaFontTwelve);

        languageDropdown.setFont(helveticaFontTwelve);

        // Styling for input fields
        Color inputFieldBackground = new Color(240, 240, 240);

        usernameInputField.setBackground(inputFieldBackground);

        passwordInputField.setBackground(inputFieldBackground);

        repeatPasswordInputField.setBackground(inputFieldBackground);

        // Calls helper method to add appropriate listeners to components
        addListeners();

        // Calls helper method to add all components to mainPanel in the correct order
        addComponents(mainPanel, titleLabel, groupLabel, usernameLabel, passwordLabel, repeatPasswordLabel,
                preferredLanguageLabel, haveAccountLabel);

        mainPanel.setBackground(Color.WHITE);

        // Add everything to parent (this) frame signup view panel
        this.add(mainPanel);
        // Set parent layout to vertical box layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }


    /**
     * Returns the singleton instance of the SignupView class.
     * If the instance is null, it initializes it with the provided parameters.
     *
     * @param signupController           The SignupController to be used by the view.
     * @param signupViewModel            The SignupViewModel to be used by the view.
     * @param generatePasswordViewModel  The GeneratePasswordViewModel to be used by the view.
     * @param loginViewModel             The LoginViewModel to be used by the view.
     * @param viewManagerModel           The ViewManagerModel to be used by the view.
     * @param generatePasswordController The GeneratePasswordController to be used by the view.
     * @return The singleton instance of the SignupView class.
     */
    public static SignupView getInstance(SignupController signupController, SignupViewModel signupViewModel,
                                         GeneratePasswordViewModel generatePasswordViewModel,
                                         LoginViewModel loginViewModel, ViewManagerModel viewManagerModel,
                                         GeneratePasswordController generatePasswordController) {
        if (instance == null) {
            instance = new SignupView(signupController, signupViewModel, generatePasswordViewModel, loginViewModel,
                    viewManagerModel, generatePasswordController);
        }
        return instance;
    }

    private void addListeners() {
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
                signupViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        generatePassword.addActionListener(evt -> {
            if (evt.getSource().equals(generatePassword)) {
                generatePasswordController.execute("Generate a secure password.");
                GeneratePasswordState generatePasswordState = generatePasswordViewModel.getState();
                String generatedPassword = generatePasswordState.getGeneratedPassword();

                // Update signup state
                SignupState currentState = signupViewModel.getState();
                currentState.setPassword(generatedPassword);
                signupViewModel.setState(currentState);

                // Show popup of generated password
                JOptionPane.showMessageDialog(SignupView.this, generatedPassword);
                passwordInputField.setText(currentState.getPassword());
            }
        });

        signUp.addActionListener(evt -> {
            if (evt.getSource().equals(signUp)) {
                SignupState currentState = signupViewModel.getState();
                currentState.setPreferredLanguage(languageDropdown.getItemAt(languageDropdown.getSelectedIndex()));

                signupController.execute(currentState.getUsername(), currentState.getPassword(),
                        currentState.getRepeatPassword(), currentState.getPreferredLanguage());
            }
        });

        goToLogin.addActionListener(evt -> {
            if (evt.getSource().equals(goToLogin)) {
                viewManagerModel.setActiveView(loginViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });
    }

    private void addComponents(JPanel mainPanel, JLabel titleLabel, JLabel groupLabel, JLabel usernameLabel,
                               JLabel passwordLabel, JLabel repeatPasswordLabel, JLabel preferredLanguageLabel,
                               JLabel haveAccountLabel) {
        // Empty labels are for spacing
        mainPanel.add(new JLabel(""));
        mainPanel.add(titleLabel);
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(groupLabel);
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
        mainPanel.add(generatePassword);
        mainPanel.add(new JLabel(""));

        mainPanel.add(repeatPasswordLabel);
        mainPanel.add(repeatPasswordInputField);
        mainPanel.add(new JLabel(""));

        mainPanel.add(preferredLanguageLabel);
        mainPanel.add(languageDropdown);
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
    }

    /**
     * Handles property change events.
     *
     * @param evt The PropertyChangeEvent representing the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    public JButton getGeneratePasswordButton() {
        return generatePassword;
    }

    public JButton getSignUpButton() {
        return signUp;
    }

    public JButton getGoToLoginButton() {
        return goToLogin;
    }
}