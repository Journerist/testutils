package org.journerist.testutils;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

public class HibernateConfigurationHelperTestRegressions {

    private HibernateConfigurationHelper configHelper;

    @Test
    public void throwsFileNotFoundExceptionOnUnknownMapping() throws Exception {
        configHelper = new HibernateConfigurationHelper();
        configHelper.addResource("regression/WrongParseCLRF.xml");
        configHelper.executeConstraintRemoval();
        String s = configHelper.getResources().get(0);
        Assert.assertEquals("<?xml version=\"1.0\"?>\r\n" +
                "<!DOCTYPE hibernate-mapping PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"\r\n" +
                "        \"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd\">\r\n" +
                "<hibernate-mapping package=\"org.journerist.testutils.entitiy.WrongParse1\">\r\n" +
                "    <class name=\"WrongParse1\" table=\"MANY_TO_ONE\">\r\n" +
                "\r\n" +
                "        <composite-id name=\"id\" class=\"Vehicle\">\r\n" +
                "            <key-property name=\"idVehicle\" type=\"java.lang.Integer\">\r\n" +
                "                <column name=\"ID_Vehicle\" precision=\"9\" scale=\"0\" />\r\n" +
                "            </key-property>\r\n" +
                "        </composite-id>\r\n" +
                "\r\n" +
                "        \r\n" +
                "\r\n" +
                "\r\n" +
                "        \r\n" +
                "\r\n" +
                "        <property name=\"seatKz\" type=\"string\">\r\n" +
                "            <column name=\"SEAT_KZ\" length=\"6\" not-null=\"true\" />\r\n" +
                "        </property>\r\n" +
                "\r\n" +
                "        <property name=\"changeDate\" type=\"string\">\r\n" +
                "            <column name=\"CHANGE_DATE\" length=\"14\" not-null=\"true\" />\r\n" +
                "        </property>\r\n" +
                "\r\n" +
                "    </class>\r\n" +
                "</hibernate-mapping>\r\n", s);
    }

}
