package org.journerist.testutils;

import org.hibernate.cfg.Configuration;
import org.journerist.testutils.common.ClassPathUtils;
import org.journerist.testutils.parseStrategy.MultiLineHibernateConstraintTagStrategy;
import org.journerist.testutils.parseStrategy.SimpleHibernateConstraintTagStrategy;
import org.journerist.testutils.parseStrategy.interfaces.ConstraintTagStrategy;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HibernateConfigurationHelper {

    List<String> resources = new ArrayList<String>();
    Charset charset = StandardCharsets.UTF_8;

    List<ConstraintTagStrategy> constraintTagStrategies = new ArrayList<ConstraintTagStrategy>(Arrays.asList(
            new SimpleHibernateConstraintTagStrategy(),
            new MultiLineHibernateConstraintTagStrategy()
    ));

    public void addAllResourcesTo(Configuration configuration) {
        executeConstraintRemoval();
        for(String content : resources) {
            InputStream stream = new ByteArrayInputStream(content.getBytes(charset));
            configuration.addInputStream(stream);
        }
    }

    public void addResource(String url) throws FileNotFoundException {
        String fileContent = ClassPathUtils.readFileFromClassPath(url);
        if (fileContent.length() > 0) {
            resources.add(fileContent);
        }
    }

    public List<String> getResources() {
        return resources;
    }

    public void executeConstraintRemoval() {
        List<String> existingEntityClassNames = parseExistingEntityClassNames();
        resources = modifyResources(existingEntityClassNames);
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    private List<String> modifyResources(List<String> existingEntityClassNames) {
        List<String> modifiedResources = new ArrayList<String>();
        for (String fileContent : resources) {
            modifiedResources.add(
                    getModifiedResourceContent(
                            fileContent,
                            getRelationNames(fileContent),
                            existingEntityClassNames)
            );

        }
        return modifiedResources;
    }

    private List<String> getRelationNames(String fileContent) {
        List<String> relations = new ArrayList<String>();

        for (ConstraintTagStrategy s : constraintTagStrategies) {
            relations.addAll(s.getRelationNames(fileContent));
        }

        return relations;
    }

    private String getModifiedResourceContent(String fileContent, List<String> relations, List<String> existingEntityClassNames) {

        for (ConstraintTagStrategy s : constraintTagStrategies) {
            fileContent = s.getModifiedResourceContent(fileContent, relations, existingEntityClassNames);
        }

        return fileContent;
    }

    private List<String> parseExistingEntityClassNames() {
        List<String> existingEntities = new ArrayList<String>();
        for (String fileContent : resources) {
            String hbmEntitiyClass;
            Matcher m = Pattern.compile("<class name=\"((\\w|\\.)*)\" (.*)>").matcher(fileContent);
            while (m.find()) {
                hbmEntitiyClass = m.group(1);
                existingEntities.add(hbmEntitiyClass);
            }
        }

        return existingEntities;
    }
}
