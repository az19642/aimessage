package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.load_contacts_to_view.LoadContactsToViewController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

/**
 * Represents the view for the logged-in screen.
 * Displays user information and provides a logout button.
 */
public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    // Constants
    public final String viewName = "logged in";

    // Components
    private final JButton reload;
    private final JButton add;
    private final JButton delete;
    private final JTextField contactInputField = new JTextField(15);
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoadContactsToViewController loadContactsToViewController;
    private final JList<Map.Entry<String, String>> contactToLastMessage;
    private final Font helveticaFontFifteen = new Font("Helvetica", Font.BOLD, 15);
    private final Font helveticaFontTwelve = new Font("Helvetica", Font.PLAIN, 12);


    /**
     * Constructor for the logged-in view.
     *
     * @param loggedInViewModel           ViewModel for the logged-in state.
     * @param loadContactsToViewController Controller for loading contacts.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel,
                        LoadContactsToViewController loadContactsToViewController) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.loadContactsToViewController = loadContactsToViewController;

        JPanel buttons = new JPanel();

        JLabel titleLabel = new JLabel(LoggedInViewModel.TITLE_LABEL);

        reload = new JButton("Reload contacts (for testing purposes)");
        add = new JButton(LoggedInViewModel.ADD_BUTTON_LABEL);
        delete = new JButton(LoggedInViewModel.DELETE_BUTTON_LABEL);

        titleLabel.setFont(helveticaFontFifteen);
        reload.setFont(helveticaFontTwelve);

        Color inputFieldBackground = new Color(255, 255, 255);
        contactInputField.setBackground(inputFieldBackground);

        buttons.add(titleLabel);
        buttons.add(contactInputField);
        buttons.add(add);
        buttons.add(delete);
        buttons.add(reload);

        reload.addActionListener(this);

        contactToLastMessage = new JList<>();
        contactToLastMessage.setCellRenderer(new ContactToLastMessageCellRenderer());

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);

        this.setLayout(new BorderLayout());

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(new JScrollPane(contactToLastMessage), BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
    }

    private static class ContactToLastMessageCellRenderer extends JLabel implements ListCellRenderer<Map.Entry<String, String>> {
        @Override
        public Component getListCellRendererComponent(JList<? extends Map.Entry<String, String>> list,
                                                      Map.Entry<String, String> contact, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setText("<html><div style='margin: 5px;'>" +
                    "<b>" + contact.getKey() + "</b> | " + contact.getValue() +
                    "</div></html>");
            customizeCellAppearance(list, isSelected);
            return this;
        }

        private void customizeCellAppearance(JList<?> list, boolean isSelected) {
            setOpaque(true);

            // Set a nicer font
            setFont(new Font("Arial", Font.PLAIN, 14));

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
        }
    }

    /**
     * Reacts to a button click event.
     *
     * @param evt The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == reload) {
            loadContactsToViewController.execute(loggedInViewModel.getState().getUsername());
        }
    }

    /**
     * Handles property change events.
     *
     * @param evt The PropertyChangeEvent representing the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        if (state.getContactToLastMessage() == null) { // first time loading
            loadContactsToViewController.execute(state.getUsername());
        } else {
            DefaultListModel<Map.Entry<String, String>> listModel = new DefaultListModel<>();
            for (Map.Entry<String, String> contact : state.getContactToLastMessage().entrySet()) {
                listModel.addElement(contact);
            }
            contactToLastMessage.setModel(listModel);
        }
    }
}