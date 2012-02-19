package com.kyle.route66.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MatchValidator implements Validator {
	private static final Log log = LogFactory.getLog(MatchValidator.class);

	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		// Cast the value of the entered password to String.
        String input = (String) value;

        // Obtain the component and submitted value of the confirm password component.
        UIInput confirmComponent = (UIInput) component.getAttributes().get("confirm");
        String confirm = (String) confirmComponent.getSubmittedValue();
        
        log.debug("input1: " + input);
        log.debug("input2: " + confirm);
        
        String message = (String)component.getAttributes().get("Message");

        // Check if they both are filled in.
        if (input == null || input.isEmpty() || confirm == null || confirm.isEmpty()) {
            return; // Let required="true" do its job.
        }
        

        // Compare the password with the confirm password.
        if (!input.equals(confirm)) {
            confirmComponent.setValid(false); // So that it's marked invalid.
            throw new ValidatorException(new FacesMessage(message));
        }

        // You can even validate the minimum password length here and throw accordingly.
        // Or, if you're smart, calculate the password strength and throw accordingly ;)
	}
}

