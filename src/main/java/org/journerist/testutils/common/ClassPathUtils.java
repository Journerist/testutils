package org.journerist.testutils.common;

import org.journerist.testutils.HibernateConfigurationHelper;
import sun.misc.IOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathUtils {
    public static String readFileFromClassPath(String url) throws FileNotFoundException {
        String hbmContent = null;

        InputStream in = HibernateConfigurationHelper.class.getClassLoader()
                .getResourceAsStream(url);
        try {
            int size = in.available();
            byte[] read = IOUtils.readFully(in, size, true);
            hbmContent = new String(read);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            throw new FileNotFoundException(url);
        }

        return hbmContent;
    }
}
