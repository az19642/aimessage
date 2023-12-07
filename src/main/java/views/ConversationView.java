package views;

import interface_adapter.ViewManagerModel;
import services.conversation.interface_adapters.ConversationState;
import services.conversation.interface_adapters.ConversationViewModel;
import services.conversation.sync_conversation_view.interface_adapters.ConversationSyncController;
import services.send_message.interface_adapters.MessageSenderController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ConversationView extends JPanel implements PropertyChangeListener {
    public final String viewName = "conversation";
    private final JTextArea conversationHistory;
    private final JTextField messageInput;
    private final JButton sendButton;

    private final JButton backButton;
    private final JButton syncButton;
    private final ConversationViewModel conversationViewModel;

    private final MessageSenderController messageSenderController;
    private final ConversationSyncController conversationSyncController;

    public ConversationView(ConversationViewModel conversationViewModel,
                            MessageSenderController messageSenderController,
                            ConversationSyncController conversationSyncController,
                            ViewManagerModel viewManagerModel) {
        this.conversationViewModel = conversationViewModel;
        this.messageSenderController = messageSenderController;
        this.conversationSyncController = conversationSyncController;

        conversationHistory = new JTextArea();
        conversationHistory.setEditable(false);

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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ConversationState state = conversationViewModel.getState();

        if (state.getTimestampToMessage() == null) {
            conversationSyncController.execute(state.getContactName());
        }
        conversationHistory.setText("");

        for (Map.Entry<LocalDateTime, List<String>> entry : state.getTimestampToMessage().entrySet()) {
            String timestamp = entry.getKey().toString();
            String sender = entry.getValue().get(0);
            String message = entry.getValue().get(1);
            conversationHistory.append(String.format("%s %s %s\n", sender, message, timestamp));
        }
    }
}