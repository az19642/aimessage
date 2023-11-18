package data_access;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import use_case.password_generator.PasswordGeneratorUserDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a data access object for generating secure passwords using the GPT model from OpenAI.
 */
public class GPTDataAccessObject implements PasswordGeneratorUserDataAccessInterface {
    private final OpenAiService service;

    /**
     * Initializes a new GPTDataAccessObject with the provided OpenAI API key.
     *
     * @param openaiApiKey The API key for accessing the OpenAI service.
     */
    public GPTDataAccessObject(String openaiApiKey) {
        this.service = new OpenAiService(openaiApiKey);
    }

    /**
     * Get the OpenAiService instance associated with this data access object.
     *
     * @return The OpenAiService instance used for interacting with the OpenAI API.
     */
    public OpenAiService getService() {
        return service;
    }

    /**
     * Generates a secure password based on the provided prompt using the GPT model.
     *
     * @param prompt The prompt for generating the password.
     * @return The generated secure password as a string.
     */
    public String generateSecurePassword(String prompt) {
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), prompt);
        messages.add(userMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-4")
                .messages(messages)
                .maxTokens(10)
                .build();

        try {
            ChatMessage responseMessage = service.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage();
            return responseMessage.getContent();
        } catch (Exception e) {
            return "Error: Failed to generate a password.";
        }
    }
}

