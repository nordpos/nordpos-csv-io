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
import com.nordpos.device.writter.Writter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
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

    private static final String PRODUCT_CODE = "PRODUCT_CODE";
    private static final String PRODUCT_NAME = "PRODUCT_NAME";
    private static final String PRODUCT_QUANTITY = "PRODUCT_QUANTITY";
    private static final String PRODUCT_PRICESELL = "PRODUCT_PRICESELL";
    private static final char SEPARATOR = ';';
    private static final char QUOTE = '"';
    
    private final File inFile;
    private final Writter outFile;
    private final byte[] bEndOfLine;
    private Iterator recIterator;

    public FileCSVInputOutput(File inFile, Writter outFile, byte[] bEndOfLine) throws TicketPrinterException {
        this.inFile = inFile;
        this.outFile = outFile;
        this.bEndOfLine = bEndOfLine;
    }

    @Override
    public void connectDevice() throws DeviceInputOutputException {
    }

    @Override
    public void disconnectDevice() {
    }

    @Override
    public void startDownloadProduct() throws DeviceInputOutputException {
        try {
            CSVFormat format = CSVFormat.EXCEL
                    .withHeader(PRODUCT_CODE, PRODUCT_NAME, PRODUCT_QUANTITY, PRODUCT_PRICESELL)
                    .withDelimiter(SEPARATOR)
                    .withIgnoreEmptyLines()
                    .withQuote(QUOTE)
                    .withSkipHeaderRecord();
            CSVParser parser = CSVParser.parse(inFile, StandardCharsets.UTF_8, format);
            recIterator = parser.iterator();
        } catch (IOException ex) {
            Logger.getLogger(FileCSVInputOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ProductIO recieveProduct() throws DeviceInputOutputException {
        CSVRecord record;
        ProductIO product = new ProductIO();
        while (recIterator.hasNext()) {
            record = (CSVRecord) recIterator.next();
            product.setCode(record.get(PRODUCT_CODE));
            String qty = record.get(PRODUCT_QUANTITY);
            product.setQuantity(Double.parseDouble(qty));
            return product;
        }
        return null;
    }

    @Override
    public void startUploadProduct() throws DeviceInputOutputException {
        outFile.write(PRODUCT_CODE);
        outFile.write(Character.toString(SEPARATOR));
        outFile.write(PRODUCT_NAME);
        outFile.write(Character.toString(SEPARATOR));
        outFile.write(PRODUCT_QUANTITY);
        outFile.write(Character.toString(SEPARATOR));
        outFile.write(PRODUCT_PRICESELL);
        outFile.write(bEndOfLine);
    }

    @Override
    public void sendProduct(ProductIO product) throws DeviceInputOutputException {
        outFile.write("\"".concat(product.getCode()).concat("\""));
        outFile.write(Character.toString(SEPARATOR));
        outFile.write("\"".concat(product.getName()).concat("\""));
        outFile.write(Character.toString(SEPARATOR));
        outFile.write(Double.toString(product.getQuantity()));
        outFile.write(Character.toString(SEPARATOR));
        outFile.write(Double.toString(product.getPriceSell()));
        outFile.write(bEndOfLine);
    }

    @Override
    public void stopUploadProduct() throws DeviceInputOutputException {
        outFile.close();
    }
}
