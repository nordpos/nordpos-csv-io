/**
 *
 * Copyright (C) 2009-2015 Nord Trading Ltd. <http://www.nordpos.com>
 * All rights reserved.
 *
 */
package com.nordpos.device.csv;

import com.nordpos.device.traslator.UnicodeTranslator;
import com.nordpos.device.ticket.TicketPrinterException;
import com.nordpos.device.plu.DeviceInputOutput;
import com.nordpos.device.plu.DeviceInputOutputException;
import com.nordpos.device.plu.ProductIO;
import com.nordpos.device.writter.Writter;

/**
 *
 * @author Andrey Svininykh <svininykh@gmail.com>
 * @version NORD POS 3.0
 */
public class FileCSVInputOutput implements DeviceInputOutput {

    public FileCSVInputOutput() throws TicketPrinterException {

    }

    @Override
    public void connectDevice() throws DeviceInputOutputException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void disconnectDevice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startDownloadProduct() throws DeviceInputOutputException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProductIO recieveProduct() throws DeviceInputOutputException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startUploadProduct() throws DeviceInputOutputException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendProduct(ProductIO product) throws DeviceInputOutputException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stopUploadProduct() throws DeviceInputOutputException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
