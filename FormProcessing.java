
package com;

import java.util.regex.PatternSyntaxException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.*;

public class FormProcessing {
    
    String name="Sam78@";

    public FormProcessing() {
        validate();
    }
    
    public void validate()
    {
        try
        {
            String validateString="([a-zA-Z]+)(@)";
            Pattern  p=Pattern.compile(validateString);
            Matcher m=p.matcher(name);
            
            if(m.matches())
            {
                 JOptionPane.showMessageDialog(null,"valid name");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Invalid name");
            }
        }
        catch(PatternSyntaxException er)
        {
            JOptionPane.showMessageDialog(null,er.getMessage());
        }
    }
    /**
    public static void main(String[] args) {
        new FormProcessing();
    }**/
    
}
