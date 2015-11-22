package org.journerist.testutils.parseStrategy.interfaces;

import java.util.List;

public interface ConstraintTagStrategy {
    List<String> getRelationNames(String fileContent);

    String getModifiedResourceContent(String fileContent, List<String> relations, List<String> existingEntityClassNames);
}
