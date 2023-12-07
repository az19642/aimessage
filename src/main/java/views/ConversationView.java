package views;

import interface_adapter.ViewManagerModel;
import services.conversation.interface_adapters.ConversationState;
import services.conversation.interface_adapters.ConversationViewModel;
import services.conversation.sync_conversation_view.interface_adapters.ConversationSyncController;
import services.send_message.interface_adapters.MessageSenderController;
import services.signup.SignupState;
import services.signup.interface_adapters.SignupViewModel;
import services.suggest_reply.interface_adapters.ReplySuggesterController;
import services.suggest_reply.interface_adapters.ReplySuggesterState;
import services.suggest_reply.interface_adapters.ReplySuggesterViewModel;
import services.text_to_speech.interface_adapters.TextToSpeechController;
import services.translate_message.interface_adapters.MessageTranslatorController;
import services.translate_message.interface_adapters.MessageTranslatorState;
import services.translate_message.interface_adapters.MessageTranslatorViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class ConversationView extends JPanel implements PropertyChangeListener {
    private static ConversationView instance;
    public final String viewName = "conversation";
    private final JList<Map.Entry<LocalDateTime, List<String>>> conversationHistory;
    private final JTextField messageInput;
    private final JButton sendButton;

    private final JButton backButton;
    private final JButton syncButton;
    private final ConversationViewModel conversationViewModel;
    private final ReplySuggesterViewModel replySuggesterViewModel;
    private final MessageTranslatorViewModel messageTranslatorViewModel;
    private final SignupViewModel signupViewModel;

    private final MessageSenderController messageSenderController;
    private final ConversationSyncController conversationSyncController;
    private final TextToSpeechController textToSpeechController;
    private final ReplySuggesterController replySuggesterController;
    private final MessageTranslatorController messageTranslatorController;

    private ConversationView(ConversationViewModel conversationViewModel,
                             MessageSenderController messageSenderController,
                             ConversationSyncController conversationSyncController,
                             TextToSpeechController textToSpeechController,
                             ReplySuggesterController replySuggesterController,
                             MessageTranslatorController messageTranslatorController,
                             ReplySuggesterViewModel replySuggesterViewModel,
                             MessageTranslatorViewModel messageTranslatorViewModel,
                             SignupViewModel signupViewModel,
                             ViewManagerModel viewManagerModel) {
        this.conversationViewModel = conversationViewModel;
        this.messageSenderController = messageSenderController;
        this.conversationSyncController = conversationSyncController;
        this.textToSpeechController = textToSpeechController;
        this.replySuggesterController = replySuggesterController;
        this.messageTranslatorController = messageTranslatorController;
        this.replySuggesterViewModel = replySuggesterViewModel;
        this.signupViewModel = signupViewModel;
        this.messageTranslatorViewModel = messageTranslatorViewModel;

        conversationHistory = new JList<>();
        conversationHistory.setCellRenderer(new ConversationHistoryCellRenderer());

        conversationHistory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    String selectedMessage = conversationHistory.getSelectedValue().getValue().get(1);
                    System.out.println(selectedMessage);
                    ConversationState conversationState = conversationViewModel.getState();
                    conversationState.setMessage(selectedMessage);
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
                Map.Entry<LocalDateTime, List<String>> selectedEntry = conversationHistory.getSelectedValue();
                if (selectedEntry != null) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem translateMessage = new JMenuItem("Translate message");
                    JMenuItem speakMessage = new JMenuItem("Speak message");
                    JMenuItem suggestReply = new JMenuItem("Suggest reply");
                    String selectedMessage = conversationHistory.getSelectedValue().getValue().get(1);
                    translateMessage.addActionListener(evtPrime -> {
                        SignupState signupState = signupViewModel.getState();
                        String preferredLanguage = signupState.getPreferredLanguage();
                        messageTranslatorController.execute(selectedMessage, preferredLanguage);

                        MessageTranslatorState messageTranslatorState = messageTranslatorViewModel.getState();
                        JOptionPane.showMessageDialog(ConversationView.this,
                                messageTranslatorState.getTranslatedMessage());
                    });
                    speakMessage.addActionListener(evtPrime -> {
                        textToSpeechController.execute(selectedMessage);
                    });
                    suggestReply.addActionListener(evtPrime -> {
                        String prompt = "Pretend as if you are my friend messaging me. I'll start: " + selectedMessage;
                        replySuggesterController.execute(prompt);
                        ReplySuggesterState replyState = replySuggesterViewModel.getState();
                        String suggestedReply = replyState.getGeneratedReply();

                        ConversationState conversationState = conversationViewModel.getState();
                        conversationState.setMessage(suggestedReply);
                        conversationViewModel.setState(conversationState);

                        messageInput.setText(conversationState.getMessage());
                    });
                    if (evt.isPopupTrigger()) {
                        popupMenu.add(translateMessage);
                        popupMenu.add(speakMessage);
                        popupMenu.add(suggestReply);
                        popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                    }
                }
            }
        });

        messageInput = new JTextField();
        sendButton = new JButton("Send");

        sendButton.addActionListener(evt -> {
            if (evt.getSource() == sendButton) {
                ConversationState conversationState = conversationViewModel.getState();
                String message = messageInput.getText();
                messageSenderController.execute(conversationState.getContactName(), message);
                conversationSyncController.execute(conversationState.getContactName());
                messageInput.setText("");
            }
        });

        syncButton = new JButton(ConversationViewModel.SYNC_BUTTON_LABEL);

        syncButton.addActionListener(evt -> {
            if (evt.getSource() == syncButton) {
                ConversationState conversationState = conversationViewModel.getState();
                conversationSyncController.execute(conversationState.getContactName());
            }
        });

        backButton = new JButton("Go back");
        backButton.addActionListener(evt -> {
            if (evt.getSource() == backButton) {
                conversationViewModel.setState(new ConversationState());
                viewManagerModel.setActiveView("logged in");
                viewManagerModel.firePropertyChanged();
            }
        });

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(conversationHistory), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(messageInput, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(syncButton, BorderLayout.EAST);
        topPanel.add(backButton, BorderLayout.WEST);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);

        conversationViewModel.addPropertyChangeListener(this);
    }

    private static class ConversationHistoryCellRenderer extends JLabel
            implements ListCellRenderer<Map.Entry<LocalDateTime, List<String>>> {
        @Override
        public Component getListCellRendererComponent(JList<? extends Map.Entry<LocalDateTime,
                List<String>>> list, Map.Entry<LocalDateTime, List<String>> entry, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            String ldtString = entry.getKey().toLocalTime().toString();
            String[] arr = ldtString.split(":");
            Integer hour = Integer.parseInt(arr[0]) + 5;
            String amOrPm = "am";

            if (hour > 12) {
                amOrPm = "pm";
                hour %= 12;
            }

            String timeFinal = hour.toString() + ":" + arr[1] + " " + amOrPm;

            String cellText = String.format("<html><div style='margin: 5px;'><p><b>%s - %s</b></p>" +
                    "<p>%s</p></div></html>", entry.getValue().get(0), timeFinal, entry.getValue().get(1));
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ConversationState state = conversationViewModel.getState();
        // if the contactToLastMessage map is null (not the same as empty), then the user has just logged in
        if (state.getTimestampToMessage() == null) {
            conversationSyncController.execute(state.getContactName());
        }

        DefaultListModel<Map.Entry<LocalDateTime, List<String>>> listModel = new DefaultListModel<>();
        for (Map.Entry<LocalDateTime, List<String>> entry : state.getTimestampToMessage().entrySet()) {
            listModel.addElement(entry);
        }
        conversationHistory.setModel(listModel);
//        ConversationState state = conversationViewModel.getState();
//
//        if (state.getTimestampToMessage() == null) {
//            conversationSyncController.execute(state.getContactName());
//        }
//        conversationHistory.setText("");
//
//        for (Map.Entry<LocalDateTime, List<String>> entry : state.getTimestampToMessage().entrySet()) {
//            String timestamp = entry.getKey().toString();
//            String sender = entry.getValue().get(0);
//            String message = entry.getValue().get(1);
//            conversationHistory.append(String.format("%s %s %s\n", sender, message, timestamp));
//        }
    }


    public JButton getBackButton() {
        return backButton;
    }
    public static ConversationView getInstance(ConversationViewModel conversationViewModel,
                                               MessageSenderController messageSenderController,
                                               ConversationSyncController conversationSyncController,
                                               TextToSpeechController textToSpeechController,
                                               ReplySuggesterController replySuggesterController,
                                               MessageTranslatorController messageTranslatorController,
                                               ReplySuggesterViewModel replySuggesterViewModel,
                                               MessageTranslatorViewModel messageTranslatorViewModel,
                                               SignupViewModel signupViewModel,
                                               ViewManagerModel viewManagerModel) {
        if (instance == null) {
            instance = new ConversationView(conversationViewModel,
                    messageSenderController,
                    conversationSyncController,
                    textToSpeechController,
                    replySuggesterController,
                    messageTranslatorController,
                    replySuggesterViewModel,
                    messageTranslatorViewModel,
                    signupViewModel,
                    viewManagerModel);
        }
        return instance;
    }
}
