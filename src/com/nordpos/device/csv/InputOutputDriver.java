/**
 *
 * NORD POS is a fork of Openbravo POS.
 *
 * Copyright (C) 2009-2015 Nord Trading Ltd. <http://www.nordpos.com>
 *
 * This file is part of NORD POS.
 *
 * NORD POS is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * NORD POS is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * NORD POS. If not, see <http://www.gnu.org/licenses/>.
 */
package com.nordpos.device.csv;

import com.nordpos.device.plu.DeviceInputOutput;
import com.nordpos.device.plu.DeviceInputOutputNull;
import com.nordpos.device.plu.InputOutputInterface;
import com.nordpos.device.util.StringParser;
import java.io.File;

/**
 *
 * @author Andrey Svininykh <svininykh@gmail.com>
 * @version NORD POS 3.0
 */
public class InputOutputDriver implements InputOutputInterface {

    public static final byte[] EOL_DOS = {0x0D, 0x0A}; // Print and carriage return
    public static final byte[] EOL_UNIX = {0x0A};

    @Override
    public DeviceInputOutput getDeviceIO(String sProperty) throws Exception {
        StringParser sp = new StringParser(sProperty);
        String sType = sp.nextToken(':');
        String sParam1 = sp.nextToken(',');
        String sParam2 = sp.nextToken(',');

        switch (sType) {
            case "csv":
                return new FileCSVInputOutput(new File(sParam1), new File(sParam2));
            default:
                return new DeviceInputOutputNull();
        }

    }

}
