package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.load_contacts_to_view.LoadContactsToViewController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.mutating_contacts.MutatingContactsController;

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
    private final JButton reloadButton;
    private final JButton addButton;
    private final JButton removeButton;
    private final JTextField contactInputField = new JTextField(15);
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MutatingContactsController mutatingContactsController;
    private final LoadContactsToViewController loadContactsToViewController;
    private final JList<Map.Entry<String, String>> contactToLastMessage;
    private final Font helveticaFontFifteen = new Font("Helvetica", Font.BOLD, 15);
    private final Font helveticaFontTwelve = new Font("Helvetica", Font.PLAIN, 12);


    /**
     * Creates a new LoggedInView.
     *
     * @param loggedInViewModel            The view model for the logged-in view.
     * @param viewManagerModel             The view manager model.
     * @param loadContactsToViewController The controller for loading contacts to the view.
     * @param mutatingContactsController   The controller for adding/removing contacts.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel,
                        LoadContactsToViewController loadContactsToViewController,
                        MutatingContactsController mutatingContactsController) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.loadContactsToViewController = loadContactsToViewController;
        this.mutatingContactsController = mutatingContactsController;

        JPanel buttons = new JPanel();

        JLabel titleLabel = new JLabel(LoggedInViewModel.TITLE_LABEL);

        reloadButton = new JButton("Reload contacts (for testing purposes)");
        addButton = new JButton(LoggedInViewModel.ADD_BUTTON_LABEL);
        removeButton = new JButton(LoggedInViewModel.DELETE_BUTTON_LABEL);

        titleLabel.setFont(helveticaFontFifteen);
        reloadButton.setFont(helveticaFontTwelve);


        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        reloadButton.addActionListener(this);

        buttons.add(titleLabel);
        buttons.add(contactInputField);
        buttons.add(addButton);
        buttons.add(removeButton);
        buttons.add(reloadButton);


        contactToLastMessage = new JList<>();
        contactToLastMessage.setCellRenderer(new ContactToLastMessageCellRenderer());

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);

        this.setLayout(new BorderLayout());

        titlePanel.setBackground(Color.WHITE);
        buttons.setBackground(Color.WHITE);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(new JScrollPane(contactToLastMessage), BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
    }

    private static class ContactToLastMessageCellRenderer extends JLabel implements ListCellRenderer<Map.Entry<String
            , String>> {
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
        if (evt.getSource() == reloadButton) {
            loadContactsToViewController.execute();
        } else if (evt.getSource() == addButton) {
            mutatingContactsController.execute(contactInputField.getText(), true);
        } else if (evt.getSource() == removeButton) {
            mutatingContactsController.execute(contactInputField.getText(), false);
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
            loadContactsToViewController.execute();
        } else if (state.getContactToLastMessage() != null) {
            DefaultListModel<Map.Entry<String, String>> listModel = new DefaultListModel<>();
            for (Map.Entry<String, String> contact : state.getContactToLastMessage().entrySet()) {
                listModel.addElement(contact);
            }
            contactToLastMessage.setModel(listModel);
        } else if (state.getMutatingContactsStatus().equals("PASS")) {
            JOptionPane.showMessageDialog(this, "Contact successfully added/removed");
            contactInputField.setText("");
            state.setMutatingContactsStatus("");
        } else {
            JOptionPane.showMessageDialog(this, state.getMutatingContactsStatus());
            state.setMutatingContactsStatus("");
        }
    }
}