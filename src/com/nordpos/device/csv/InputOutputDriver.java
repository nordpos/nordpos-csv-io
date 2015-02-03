/**
 *
 * Copyright (C) 2009-2015 Nord Trading Ltd. <http://www.nordpos.com>
 * All rights reserved.
 *
 */
package com.nordpos.device.csv;

import com.nordpos.device.labelprinter.DeviceLabelPrinter;
import com.nordpos.device.labelprinter.LabelPrinterInterface;
import com.nordpos.device.plu.DeviceInputOutput;
import com.nordpos.device.plu.DeviceInputOutputNull;
import com.nordpos.device.plu.InputOutputInterface;
import com.nordpos.device.traslator.UnicodeTranslatorInt;
import com.nordpos.device.traslator.UnicodeTranslator;
import com.nordpos.device.writter.WritterFile;
import com.nordpos.device.writter.WritterRXTX;
import com.nordpos.device.util.SerialPortParameters;
import com.nordpos.device.util.StringParser;

/**
 *
 * @author Andrey Svininykh <svininykh@gmail.com>
 * @version NORD POS 3.0
 */
public class InputOutputDriver implements InputOutputInterface {

    @Override
    public DeviceInputOutput getDeviceIO(String sProperty) throws Exception {
        StringParser sp = new StringParser(sProperty);
        String sPrinterType = sp.nextToken(':');
        String sPrinterParam1 = sp.nextToken(',');
        String sPrinterParam2 = sp.nextToken(',');
        
        switch (sPrinterType) {
            case "csv":
                return new DeviceInputOutputNull();
            default:
                return new DeviceInputOutputNull();
        }

    }

}
