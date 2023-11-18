package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Model representing the active view in the application.
 */
public class ViewManagerModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String activeViewName;

    /**
     * Gets the name of the currently active view.
     *
     * @return The name of the currently active view.
     */
    public String getActiveView() {
        return activeViewName;
    }

    /**
     * Sets the currently active view.
     *
     * @param activeView The name of the view to set as active.
     */
    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
    }

    /**
     * Notifies listeners that the active view property has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }

    /**
     * Adds a property change listener to the model.
     *
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}