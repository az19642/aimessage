package interface_adapters;

import java.beans.PropertyChangeListener;

/**
 * Abstract class representing a ViewModel in the application.
 */
public abstract class ViewModel {

    private String viewName;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Gets the name of the associated view.
     *
     * @return The name of the associated view.
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * Notifies listeners that a property in the ViewModel has changed.
     */
    public abstract void firePropertyChanged();

    /**
     * Adds a property change listener to the ViewModel.
     *
     * @param listener The listener to be added.
     */
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);


}