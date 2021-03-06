/**
 *
 * Copyright 2017 Teclib.
 * Copyright 2010-2016 by the FusionInventory Development
 *
 * http://www.fusioninventory.org/
 * https://github.com/fusioninventory/fusioninventory-android
 *
 * ------------------------------------------------------------------------
 *
 * LICENSE
 *
 * This file is part of FusionInventory project.
 *
 * FusionInventory is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * FusionInventory is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * ------------------------------------------------------------------------------
 * @update    07/06/2017
 * @license   GPLv2 https://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * @link      https://github.com/fusioninventory/fusioninventory-android
 * @link      http://www.fusioninventory.org/
 * ------------------------------------------------------------------------------
 */

package org.flyve.inventory.categories;

import android.content.Context;

import org.flyve.inventory.FILog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class get all the information of the Environment
 */
public class Storage extends Categories {

    /*
     * The serialization runtime associates with each serializable class a version number,
     * called a serialVersionUID, which is used during deserialization to verify that the sender
     * and receiver of a serialized object have loaded classes for that object that are compatible
     * with respect to serialization. If the receiver has loaded a class for the object that has a
     * different serialVersionUID than that of the corresponding sender's class, then deserialization
     * will result in an  InvalidClassException
     *
     *  from: https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it
     */
    private static final long serialVersionUID = 3528873342443549732L;

    private Properties props;
    private Context xCtx;

    /**
     * Indicates whether some other object is "equal to" this one
     * @param obj the reference object with which to compare
     * @return boolean true if the object is the same as the one given in argument
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return (!super.equals(obj));
    }

    /**
     * Returns a hash code value for the object
     * @return int a hash code value for the object
     */
    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 89 * hash + (this.xCtx != null ? this.xCtx.hashCode() : 0);
        hash = 89 * hash + (this.props != null ? this.props.hashCode() : 0);
        return hash;
    }

    /**
     * This constructor load the context and the Hardware information
     * @param xCtx Context where this class work
     */
    public Storage(Context xCtx) {
        super(xCtx);

        this.xCtx = xCtx;

        try {
            props = System.getProperties();

            List<List<String>> values = getPartitionInformation();
            if(values!=null) {
                for (int i = 0; i < values.size(); i++) {
                    List<String> value = values.get(i);
                    if(!value.isEmpty()) {
                        Category c = new Category("STORAGES", "storages");
                        c.put("DESCRIPTION", new CategoryValue("MMC", "DESCRIPTION", "description"));
                        c.put("DISKSIZE", new CategoryValue(value.get(2), "DISKSIZE", "disksize"));
                        c.put("NAME", new CategoryValue(value.get(3), "NAME", "name"));
                        c.put("SERIALNUMBER", new CategoryValue("", "SERIALNUMBER", "serialNumber"));
                        c.put("TYPE", new CategoryValue("disk", "TYPE", "type"));

                        this.add(c);
                    }
                }
            }
        } catch (Exception ex) {
            FILog.e(ex.getMessage());
        }
    }

    private List<List<String>> getPartitionInformation() {
        try {
            // Run the command
            Process process = Runtime.getRuntime().exec("cat /proc/partitions | grep mmc");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            // Grab the results
            List<List<String>> values = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                List<String> value = getValues(line);

                if(!line.equals("") && !value.get(3).equalsIgnoreCase("name")) {
                    values.add(value);
                }
            }

            return values;
        } catch (IOException ex) {
            FILog.e(ex.getMessage());
        }

        return null;
    }

    private List<String> getValues(String line) {
        ArrayList<String> arr = new ArrayList<>();

        if(!line.trim().equals("")) {
            String[] arrLine = line.split(" ");
            for (int i = 0; i < arrLine.length; i++) {
                if (!arrLine[i].trim().equals("")) {
                    arr.add(arrLine[i]);
                }
            }
        }

        return arr;
    }

}
