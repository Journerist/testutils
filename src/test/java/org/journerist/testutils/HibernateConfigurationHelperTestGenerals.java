package org.journerist.testutils;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.journerist.testutils.HibernateConfigurationHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

@RunWith(HierarchicalContextRunner.class)
public class HibernateConfigurationHelperTestGenerals {

    private HibernateConfigurationHelper configHelper;

    public class Exceptions {
        @Test(expected = FileNotFoundException.class)
        public void throwsFileNotFoundExceptionOnUnknownMapping() throws Exception {
            configHelper = new HibernateConfigurationHelper();
            configHelper.addResource("notFound.hbm.xml");
        }
    }

    public class SimpleTwoFileOneRelation {
        @Before
        public void setUp() throws Exception {
            configHelper = new HibernateConfigurationHelper();
            configHelper.addResource("Player.hbm.xml");
        }

        @Test
        public void addingResourcesIncreaseTheResourceCount() throws Exception {
            assertEquals(1, configHelper.getResources().size());
        }

        @Test
        public void readTheCorrectResourceContent() throws Exception {
            //this hashset is correct if the string is like prented. KIS
            assertEquals(fullFileContent, configHelper.getResources().iterator().next());
        }


        @Test
        public void constraintRemovingIsExecutable() throws Exception {
            configHelper.executeConstraintRemoval();
            assertEquals(replacedFileContent, configHelper.getResources().iterator().next());
        }

        @Test
        public void itDoesNotRemoveClassesThatAreAdded() throws Exception {
            configHelper.addResource("PlayerText.hbm.xml");
            configHelper.executeConstraintRemoval();
            assertEquals(fullFileContent, configHelper.getResources().get(0));
        }

        @Test
        public void testName() throws Exception {


        }

        String fullFileContent = "<!DOCTYPE hibernate-mapping PUBLIC\n" +
                "        \"-//Hibernate/Hibernate Mapping DTD//EN\"\n" +
                "        \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\" >\n" +
                "\n" +
                "<hibernate-mapping>\n" +
                "    <class name=\"com.journerist.exampleForCleanArchitecture.entity.Player\" table=\"PLAYER\">\n" +
                "        <id name=\"playerId\" column=\"PLAYER_ID\" >\n" +
                "            <generator class=\"native\"/>\n" +
                "        </id>\n" +
                "        <property name=\"username\">\n" +
                "            <column name=\"USERNAME\" />\n" +
                "        </property>\n" +
                "        <property name=\"points\">\n" +
                "            <column name=\"POINTS\"/>\n" +
                "        </property>\n" +
                "\n" +
                "        <property name=\"gameCount\">\n" +
                "            <column name=\"GAME_COUNT\"/>\n" +
                "        </property>\n" +
                "        <property name=\"createdDate\" type=\"date\">\n" +
                "            <column name=\"CREATED_DATE\"/>\n" +
                "        </property>\n" +
                "        <set name=\"playerTexts\" table=\"PLAYER_TEXT\"\n" +
                "             inverse=\"true\" lazy=\"true\" fetch=\"select\">\n" +
                "            <key>\n" +
                "                <column name=\"TEXT_ID\" not-null=\"true\" />\n" +
                "            </key>\n" +
                "            <one-to-many class=\"com.journerist.exampleForCleanArchitecture.entity.PlayerText\" />\n" +
                "        </set>\n" +
                "    </class>\n" +
                "</hibernate-mapping>";

        String replacedFileContent = "<!DOCTYPE hibernate-mapping PUBLIC\n" +
                "        \"-//Hibernate/Hibernate Mapping DTD//EN\"\n" +
                "        \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\" >\n" +
                "\n" +
                "<hibernate-mapping>\n" +
                "    <class name=\"com.journerist.exampleForCleanArchitecture.entity.Player\" table=\"PLAYER\">\n" +
                "        <id name=\"playerId\" column=\"PLAYER_ID\" >\n" +
                "            <generator class=\"native\"/>\n" +
                "        </id>\n" +
                "        <property name=\"username\">\n" +
                "            <column name=\"USERNAME\" />\n" +
                "        </property>\n" +
                "        <property name=\"points\">\n" +
                "            <column name=\"POINTS\"/>\n" +
                "        </property>\n" +
                "\n" +
                "        <property name=\"gameCount\">\n" +
                "            <column name=\"GAME_COUNT\"/>\n" +
                "        </property>\n" +
                "        <property name=\"createdDate\" type=\"date\">\n" +
                "            <column name=\"CREATED_DATE\"/>\n" +
                "        </property>\n" +
                "        \n" +
                "    </class>\n" +
                "</hibernate-mapping>";
    }

    public class twoFileMultipleConstraints {
        @Before
        public void setUp() throws Exception {
            configHelper = new HibernateConfigurationHelper();
            configHelper.addResource("PlayerMultipleRelation.hbm.xml");
        }

        @Test
        public void noOtherMappingsAreGiven_doesRemoveAllConstraints() throws Exception {
            configHelper.executeConstraintRemoval();
            assertEquals(replacedFileContent, configHelper.getResources().get(0));
        }

        @Test
        public void oneOfTwoMappingsAreGiven_doesRemoveTheCorrectOne_1() throws Exception {
            configHelper.addResource("PlayerText.hbm.xml");
            configHelper.executeConstraintRemoval();
            assertEquals(replacedTextTwoContent, configHelper.getResources().get(0));
        }

        @Test
        public void oneOfTwoMappingsAreGiven_doesRemoveTheCorrectOne_2() throws Exception {
            configHelper.addResource("PlayerTextTwo.hbm.xml");
            configHelper.executeConstraintRemoval();
            assertEquals(replacedTextOneContent, configHelper.getResources().get(0));
        }



        String replacedFileContent = "<!DOCTYPE hibernate-mapping PUBLIC\n" +
                "        \"-//Hibernate/Hibernate Mapping DTD//EN\"\n" +
                "        \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\" >\n" +
                "\n" +
                "<hibernate-mapping>\n" +
                "    <class name=\"com.journerist.exampleForCleanArchitecture.entity.Player\" table=\"PLAYER\">\n" +
                "        <id name=\"playerId\" column=\"PLAYER_ID\" >\n" +
                "            <generator class=\"native\"/>\n" +
                "        </id>\n" +
                "        <property name=\"username\">\n" +
                "            <column name=\"USERNAME\" />\n" +
                "        </property>\n" +
                "        <property name=\"points\">\n" +
                "            <column name=\"POINTS\"/>\n" +
                "        </property>\n" +
                "\n" +
                "        <property name=\"gameCount\">\n" +
                "            <column name=\"GAME_COUNT\"/>\n" +
                "        </property>\n" +
                "        <property name=\"createdDate\" type=\"date\">\n" +
                "            <column name=\"CREATED_DATE\"/>\n" +
                "        </property>\n" +
                "        \n" +
                "        \n" +
                "    </class>\n" +
                "</hibernate-mapping>";

        String replacedTextTwoContent = "<!DOCTYPE hibernate-mapping PUBLIC\n" +
                "        \"-//Hibernate/Hibernate Mapping DTD//EN\"\n" +
                "        \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\" >\n" +
                "\n" +
                "<hibernate-mapping>\n" +
                "    <class name=\"com.journerist.exampleForCleanArchitecture.entity.Player\" table=\"PLAYER\">\n" +
                "        <id name=\"playerId\" column=\"PLAYER_ID\" >\n" +
                "            <generator class=\"native\"/>\n" +
                "        </id>\n" +
                "        <property name=\"username\">\n" +
                "            <column name=\"USERNAME\" />\n" +
                "        </property>\n" +
                "        <property name=\"points\">\n" +
                "            <column name=\"POINTS\"/>\n" +
                "        </property>\n" +
                "\n" +
                "        <property name=\"gameCount\">\n" +
                "            <column name=\"GAME_COUNT\"/>\n" +
                "        </property>\n" +
                "        <property name=\"createdDate\" type=\"date\">\n" +
                "            <column name=\"CREATED_DATE\"/>\n" +
                "        </property>\n" +
                "        <set name=\"playerTexts\" table=\"PLAYER_TEXT\"\n" +
                "             inverse=\"true\" lazy=\"true\" fetch=\"select\">\n" +
                "            <key>\n" +
                "                <column name=\"TEXT_ID\" not-null=\"true\" />\n" +
                "            </key>\n" +
                "            <one-to-many class=\"com.journerist.exampleForCleanArchitecture.entity.PlayerText\" />\n" +
                "        </set>\n" +
                "        \n" +
                "    </class>\n" +
                "</hibernate-mapping>";

        String replacedTextOneContent = "<!DOCTYPE hibernate-mapping PUBLIC\n" +
                "        \"-//Hibernate/Hibernate Mapping DTD//EN\"\n" +
                "        \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\" >\n" +
                "\n" +
                "<hibernate-mapping>\n" +
                "    <class name=\"com.journerist.exampleForCleanArchitecture.entity.Player\" table=\"PLAYER\">\n" +
                "        <id name=\"playerId\" column=\"PLAYER_ID\" >\n" +
                "            <generator class=\"native\"/>\n" +
                "        </id>\n" +
                "        <property name=\"username\">\n" +
                "            <column name=\"USERNAME\" />\n" +
                "        </property>\n" +
                "        <property name=\"points\">\n" +
                "            <column name=\"POINTS\"/>\n" +
                "        </property>\n" +
                "\n" +
                "        <property name=\"gameCount\">\n" +
                "            <column name=\"GAME_COUNT\"/>\n" +
                "        </property>\n" +
                "        <property name=\"createdDate\" type=\"date\">\n" +
                "            <column name=\"CREATED_DATE\"/>\n" +
                "        </property>\n" +
                "        \n" +
                "        <set name=\"playerTextsTwo\" table=\"PLAYER_TEXT_TWO\"\n" +
                "             inverse=\"true\" lazy=\"true\" fetch=\"select\">\n" +
                "            <key>\n" +
                "                <column name=\"TEXT_ID\" not-null=\"true\" />\n" +
                "            </key>\n" +
                "            <one-to-many class=\"com.journerist.exampleForCleanArchitecture.entity.PlayerTextTwo\" />\n" +
                "        </set>\n" +
                "    </class>\n" +
                "</hibernate-mapping>";
    }
}
