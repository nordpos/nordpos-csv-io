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

import com.nordpos.device.ticket.TicketPrinterException;
import com.nordpos.device.plu.DeviceInputOutput;
import com.nordpos.device.plu.DeviceInputOutputException;
import com.nordpos.device.plu.ProductIO;
import com.nordpos.device.reader.Reader;
import com.nordpos.device.writter.Writter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Andrey Svininykh <svininykh@gmail.com>
 * @version NORD POS 3.0
 */
public class FileCSVInputOutput implements DeviceInputOutput {

    private static final String SEPARATOR = ";";
    private final File inFile;
    private final Writter outFile;

    public FileCSVInputOutput(File inFile, Writter outFile) throws TicketPrinterException {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    @Override
    public void connectDevice() throws DeviceInputOutputException {
        outFile.init(null);
    }

    @Override
    public void disconnectDevice() {
        outFile.close();
    }

    @Override
    public void startDownloadProduct() throws DeviceInputOutputException {
        try {
            CSVParser parser = CSVParser.parse(inFile, StandardCharsets.UTF_8, CSVFormat.newFormat(';'));
            for (CSVRecord csvRecord : parser) {
//     ...
            }
        } catch (IOException ex) {
            Logger.getLogger(FileCSVInputOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ProductIO recieveProduct() throws DeviceInputOutputException {
        return null;
    }

    @Override
    public void startUploadProduct() throws DeviceInputOutputException {
        outFile.write("\"".concat("PRODUCT_CODE").concat("\""));
        outFile.write(SEPARATOR);
        outFile.write("\"".concat("PRODUCT_NAME").concat("\""));
        outFile.write(SEPARATOR);
        outFile.write("\"".concat("PRODUCT_QUANTITY").concat("\""));
        outFile.write(SEPARATOR);
        outFile.write("\"".concat("PRODUCT_PRICESELL").concat("\""));
        outFile.write("\n");
    }

    @Override
    public void sendProduct(ProductIO product) throws DeviceInputOutputException {
        outFile.write("\"".concat(product.getCode()).concat("\""));
        outFile.write(SEPARATOR);
        outFile.write("\"".concat(product.getName()).concat("\""));
        outFile.write(SEPARATOR);
        outFile.write("\"".concat(Double.toString(product.getQuantity())).concat("\""));
        outFile.write(SEPARATOR);
        outFile.write("\"".concat(Double.toString(product.getPriceSell())).concat("\""));
        outFile.write("\n");
    }

    @Override
    public void stopUploadProduct() throws DeviceInputOutputException {

        outFile.flush();
    }
}
