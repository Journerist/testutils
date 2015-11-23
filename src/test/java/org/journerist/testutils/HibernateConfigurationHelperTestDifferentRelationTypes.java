package org.journerist.testutils;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(HierarchicalContextRunner.class)
public class HibernateConfigurationHelperTestDifferentRelationTypes {

    private HibernateConfigurationHelper configHelper;

    public class FilterConstraintsByIncludes {
        @Before
        public void setUp() throws Exception {
            configHelper = new HibernateConfigurationHelper();
            configHelper.addResource("DifferentRelationTypes.hbm.xml");
        }

        @Test
        public void replacesAllConstraints() throws Exception {
            configHelper.executeConstraintRemoval();
            assertEquals(allConstraintsReplaced, configHelper.getResources().get(0));
        }

        @Test
        public void keeps_OneToOne() throws Exception {
            configHelper.addResource("DRT_OneToOne.xml");
            configHelper.executeConstraintRemoval();
            assertEquals(keepsOneToOneStr, configHelper.getResources().get(0));
        }

        @Test
        public void keeps_OneToMany() throws Exception {
            configHelper.addResource("DRT_OneToMany.xml");
            configHelper.executeConstraintRemoval();
            assertEquals(keepsOneToManyStr, configHelper.getResources().get(0));
        }

        @Test
        public void keeps_ManyToOne() throws Exception {
            configHelper.addResource("DRT_ManyToOne.xml");
            configHelper.executeConstraintRemoval();
            assertEquals(keepsManyToOneStr, configHelper.getResources().get(0));
        }

        @Test
        public void keeps_ManyToMany() throws Exception {
            configHelper.addResource("DRT_ManyToMany.xml");
            configHelper.executeConstraintRemoval();
            assertEquals(keepsManyToManyStr, configHelper.getResources().get(0));
        }

        @Test
        public void keeps_ManyToMany_and_ManyToOne() throws Exception {
            configHelper.addResource("DRT_ManyToMany.xml");
            configHelper.addResource("DRT_ManyToOne.xml");

            configHelper.executeConstraintRemoval();
            assertEquals(keepsManyToMany_and_ManyToOneStr, configHelper.getResources().get(0));
        }

        String keepsManyToMany_and_ManyToOneStr = "<!DOCTYPE hibernate-mapping PUBLIC\n" +
                "        \"-//Hibernate/Hibernate Mapping DTD//EN\"\n" +
                "        \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\" >\n" +
                "\n" +
                "<hibernate-mapping>\n" +
                "    <class name=\"org.journerist.testutils.entity.DifferentRelationTypes\" table=\"PLAYER\">\n" +
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
                "\n" +
                "        \n" +
                "\n" +
                "        <many-to-one name=\"studentAddress\" class=\"org.journerist.testutils.entity.ManyToOne\" column=\"STUDENT_ADDRESS\" cascade=\"all\" not-null=\"true\" />\n" +
                "\n" +
                "        \n" +
                "\n" +
                "        <set name=\"categories\" table=\"stock_category\"\n" +
                "             inverse=\"false\" lazy=\"true\" fetch=\"select\" cascade=\"all\" >\n" +
                "            <key>\n" +
                "                <column name=\"STOCK_ID\" not-null=\"true\" />\n" +
                "            </key>\n" +
                "            <many-to-many class=\"org.journerist.testutils.entity.ManyToMany\">\n" +
                "                <column name=\"CATEGORY_ID\" not-null=\"true\" />\n" +
                "            </many-to-many>\n" +
                "        </set>\n" +
                "    </class>\n" +
                "</hibernate-mapping>";

        String keepsManyToManyStr = "<!DOCTYPE hibernate-mapping PUBLIC\n" +
                "        \"-//Hibernate/Hibernate Mapping DTD//EN\"\n" +
                "        \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\" >\n" +
                "\n" +
                "<hibernate-mapping>\n" +
                "    <class name=\"org.journerist.testutils.entity.DifferentRelationTypes\" table=\"PLAYER\">\n" +
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
                "\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "\n" +
                "        <set name=\"categories\" table=\"stock_category\"\n" +
                "             inverse=\"false\" lazy=\"true\" fetch=\"select\" cascade=\"all\" >\n" +
                "            <key>\n" +
                "                <column name=\"STOCK_ID\" not-null=\"true\" />\n" +
                "            </key>\n" +
                "            <many-to-many class=\"org.journerist.testutils.entity.ManyToMany\">\n" +
                "                <column name=\"CATEGORY_ID\" not-null=\"true\" />\n" +
                "            </many-to-many>\n" +
                "        </set>\n" +
                "    </class>\n" +
                "</hibernate-mapping>";

        String keepsManyToOneStr = "<!DOCTYPE hibernate-mapping PUBLIC\n" +
                "        \"-//Hibernate/Hibernate Mapping DTD//EN\"\n" +
                "        \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\" >\n" +
                "\n" +
                "<hibernate-mapping>\n" +
                "    <class name=\"org.journerist.testutils.entity.DifferentRelationTypes\" table=\"PLAYER\">\n" +
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
                "\n" +
                "        \n" +
                "\n" +
                "        <many-to-one name=\"studentAddress\" class=\"org.journerist.testutils.entity.ManyToOne\" column=\"STUDENT_ADDRESS\" cascade=\"all\" not-null=\"true\" />\n" +
                "\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "    </class>\n" +
                "</hibernate-mapping>";

        String keepsOneToManyStr = "<!DOCTYPE hibernate-mapping PUBLIC\n" +
                "        \"-//Hibernate/Hibernate Mapping DTD//EN\"\n" +
                "        \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\" >\n" +
                "\n" +
                "<hibernate-mapping>\n" +
                "    <class name=\"org.journerist.testutils.entity.DifferentRelationTypes\" table=\"PLAYER\">\n" +
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
                "\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "\n" +
                "        <set name=\"stockDailyRecords\" table=\"stock_daily_record\"\n" +
                "             inverse=\"true\" lazy=\"true\" fetch=\"select\">\n" +
                "            <key>\n" +
                "                <column name=\"STOCK_ID\" not-null=\"true\" />\n" +
                "            </key>\n" +
                "            <one-to-many class=\"org.journerist.testutils.entity.OneToMany\" />\n" +
                "        </set>\n" +
                "\n" +
                "        \n" +
                "    </class>\n" +
                "</hibernate-mapping>";

        String keepsOneToOneStr = "<!DOCTYPE hibernate-mapping PUBLIC\n" +
                "        \"-//Hibernate/Hibernate Mapping DTD//EN\"\n" +
                "        \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\" >\n" +
                "\n" +
                "<hibernate-mapping>\n" +
                "    <class name=\"org.journerist.testutils.entity.DifferentRelationTypes\" table=\"PLAYER\">\n" +
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
                "\n" +
                "        <one-to-one name=\"stockDetail\" class=\"org.journerist.testutils.entity.OneToOne\"\n" +
                "                    cascade=\"save-update\"></one-to-one>\n" +
                "\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "    </class>\n" +
                "</hibernate-mapping>";

        String allConstraintsReplaced = "<!DOCTYPE hibernate-mapping PUBLIC\n" +
                "        \"-//Hibernate/Hibernate Mapping DTD//EN\"\n" +
                "        \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\" >\n" +
                "\n" +
                "<hibernate-mapping>\n" +
                "    <class name=\"org.journerist.testutils.entity.DifferentRelationTypes\" table=\"PLAYER\">\n" +
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
                "\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "\n" +
                "        \n" +
                "    </class>\n" +
                "</hibernate-mapping>";
    }


}
