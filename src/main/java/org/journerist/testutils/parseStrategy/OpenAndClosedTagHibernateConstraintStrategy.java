package org.journerist.testutils.parseStrategy;

public class OpenAndClosedTagHibernateConstraintStrategy extends RegexpHibernateConstraintTagStrategy {

    private static final String regexp = "<(?<relation>one-to-one|many-to-one)(.|\\n|\\r\\n)*?class=\"(?<entityClass>(\\w|\\.)*)\\\"(.|\\n|\\r\\n)*?(<\\/one-to-one>|many-to-one>)";

    @Override
    protected String getRegexpString() {
        return regexp;
    }

}
