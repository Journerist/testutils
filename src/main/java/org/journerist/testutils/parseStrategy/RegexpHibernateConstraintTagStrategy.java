package org.journerist.testutils.parseStrategy;

import org.journerist.testutils.parseStrategy.interfaces.ConstraintTagStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RegexpHibernateConstraintTagStrategy  implements ConstraintTagStrategy {

    protected abstract String getRegexpString();

    public List<String> getRelationNames(String fileContent) {
        Matcher m = Pattern.compile(getRegexpString()).matcher(fileContent);
        List<String> relations = new ArrayList<String>();

        while (m.find()) {
            relations.add(m.group("relation"));
        }

        return relations;
    }

    public String getModifiedResourceContent(String fileContent, List<String> relations, List<String> existingEntityClassNames) {
        Matcher m = Pattern.compile(getRegexpString()).matcher(fileContent);

        while (m.find()) {
            String setContent = m.group(0);

            if (    relations.size() > 0 &&
                    existingEntityClassNames.indexOf(m.group("entityClass")) == -1) {

                fileContent = fileContent.replace(setContent, "");
            }
        }

        return fileContent;
    }
}
