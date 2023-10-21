package com.example.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.ui.Messages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacterCountAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        //String selectedText = event.getDataContext().getData("EDITOR_SELECTED_TEXT").toString();
        String selectedText = event.getData(CommonDataKeys.EDITOR).getSelectionModel().getSelectedText();
        Pattern tokenPattern = Pattern.compile("[\\w]+|[^a-zA-Z0-9_\\s]");
        Pattern charPattern = Pattern.compile("\\S");
        Pattern rowPattern = Pattern.compile("(\\R|^)[\\s]*[\\S]+");

        Matcher matcher = tokenPattern.matcher(selectedText);
        int tokenNum = 0;
        while(matcher.find()) tokenNum++;

        matcher = charPattern.matcher(selectedText);
        int charNum = 0;
        while(matcher.find()) charNum++;

        matcher = rowPattern.matcher(selectedText);
        int rowNum = 0;
        while(matcher.find()) rowNum++;

        int characterCount = selectedText.length();
        event.getData(CommonDataKeys.EDITOR).getSelectionModel().getSelectedText();

        Messages.showMessageDialog("Character count: " + charNum+"\n"+
                        "token count: " + tokenNum+"\n"+
                        "row count: " + rowNum+"\n",
                "Character Count", Messages.getInformationIcon());
    }
}
