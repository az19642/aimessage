package views;

import interface_adapter.ViewManagerModel;
import services.contact_mutation.interface_adapters.MutatingContactsController;
import services.contacts_view_sync.interface_adapters.LoadContactsToViewController;
import services.conversation.interface_adapters.ConversationState;
import services.conversation.interface_adapters.ConversationViewModel;
import services.logged_in.LoggedInState;
import services.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

/**
 * Represents the view for the logged-in screen.
 * Displays user information and provides a logout button.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {
    public final String viewName = "logged in";
    private final JButton addButton;
    private final JTextField contactInputField = new JTextField(15);
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MutatingContactsController mutatingContactsController;
    private final LoadContactsToViewController loadContactsToViewController;
    private final JList<Map.Entry<String, String>> contactToLastMessage;
    private final Font helveticaFontFifteen = new Font("Helvetica", Font.BOLD, 15);
    private final ConversationViewModel conversationViewModel;

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
                        MutatingContactsController mutatingContactsController,
                        ConversationViewModel conversationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.conversationViewModel = conversationViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.loadContactsToViewController = loadContactsToViewController;
        this.mutatingContactsController = mutatingContactsController;

        JPanel buttons = new JPanel();
        addButton = new JButton(LoggedInViewModel.ADD_BUTTON_LABEL);
        addButton.addActionListener(evt -> {
            mutatingContactsController.execute(contactInputField.getText(), true);
            loadContactsToViewController.execute();
        });
        buttons.add(contactInputField);
        buttons.add(addButton);

        JLabel titleLabel = new JLabel(LoggedInViewModel.TITLE_LABEL);
        titleLabel.setFont(helveticaFontFifteen);
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);

        contactToLastMessage = new JList<>();
        contactToLastMessage.setCellRenderer(new ContactToLastMessageCellRenderer());
        contactToLastMessage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Map.Entry<String, String> selectedEntry = contactToLastMessage.getSelectedValue();
                    String selectedContact = contactToLastMessage.getSelectedValue().getKey();
                    viewManagerModel.setActiveView("conversation");
                    viewManagerModel.firePropertyChanged();
                    ConversationState conversationState = conversationViewModel.getState();
                    conversationState.setContactName(selectedContact);
                    conversationViewModel.setState(conversationState);
                    conversationViewModel.firePropertyChanged();
                }
            }

            // Both mouseReleased and mousePressed are needed to support both Windows and Mac
            @Override
            public void mouseReleased(MouseEvent evt) {
                rightClick(evt);
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                rightClick(evt);
            }

            private void rightClick(MouseEvent evt) {
                Map.Entry<String, String> selectedEntry = contactToLastMessage.getSelectedValue();
                if (selectedEntry != null) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem sendMessage = new JMenuItem("Send message");
                    JMenuItem deleteContact = new JMenuItem("Delete contact");
                    deleteContact.addActionListener(evtPrime -> {
                        String selectedContact = contactToLastMessage.getSelectedValue().getKey();
                        mutatingContactsController.execute(selectedContact, false);
                        loadContactsToViewController.execute();
                    });
                    sendMessage.addActionListener(evtPrime -> {
                        String selectedContact = contactToLastMessage.getSelectedValue().getKey();
                        viewManagerModel.setActiveView("conversation");
                        viewManagerModel.firePropertyChanged();
                        ConversationState conversationState = conversationViewModel.getState();
                        conversationState.setContactName(selectedContact);
                        conversationViewModel.setState(conversationState);
                        conversationViewModel.firePropertyChanged();
                    });
                    if (evt.isPopupTrigger()) {
                        popupMenu.add(sendMessage);
                        popupMenu.add(deleteContact);
                        popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                    }
                }
            }
        });

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
            String cellText = String.format("<html><div style='margin: 5px;'><p> <b>%s</b> " +
                    "</p><p>%s</p></div></html>", contact.getKey(), contact.getValue());
            setText(cellText);
            customizeCellAppearance(list, isSelected);
            return this;
        }

        private void customizeCellAppearance(JList<?> list, boolean isSelected) {
            setOpaque(true);
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
     * Handles property change events.
     *
     * @param evt The PropertyChangeEvent representing the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        // if the contactToLastMessage map is null (not the same as empty), then the user has just logged in
        if (state.getContactToLastMessage() == null) {
            loadContactsToViewController.execute();
        }
        DefaultListModel<Map.Entry<String, String>> listModel = new DefaultListModel<>();
        for (Map.Entry<String, String> contact : state.getContactToLastMessage().entrySet()) {
            listModel.addElement(contact);
        }
        contactToLastMessage.setModel(listModel);

        String mutatingContactsStatus = state.getMutatingContactsStatus();

        // if the status is non-empty, then the user has tried to add/remove a contact
        if (!mutatingContactsStatus.isEmpty() && !mutatingContactsStatus.equals("PASS")) {
            JOptionPane.showMessageDialog(this, state.getMutatingContactsStatus());
            state.setMutatingContactsStatus("");
        }
    }
}