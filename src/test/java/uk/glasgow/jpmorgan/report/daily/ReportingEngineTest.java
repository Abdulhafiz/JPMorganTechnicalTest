package uk.glasgow.jpmorgan.report.daily;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import uk.glasgow.jpmorgan.report.daily.error.InvalidTradeInputException;
import uk.glasgow.jpmorgan.report.daily.trade.Instruction;
import uk.glasgow.jpmorgan.report.daily.trade.Instructions;
import uk.glasgow.jpmorgan.report.daily.trade.ReportingEngine;

/**
 * J.P Morgan Java Technical Test
 * @author Abdul Hafiz
 *
 */

public class ReportingEngineTest {

    public static final Logger LOGGER = Logger.getLogger(ReportingEngineTest.class);
    
    private ReportingEngine reportingEngine = new ReportingEngine();
    Instruction instruction1 = null;
    Instruction instruction2 = null;
    Instruction instruction3 = null;
    Instruction instruction4 = null;
    Instruction instruction5 = null;
    Instruction instruction6 = null;
    Instruction instruction7 = null;
    Instruction instruction8 = null;
    Instruction instruction9 = null;
    Instruction instruction10 = null;
    Instruction instruction11 = null;
    Instruction instruction12 = null;

    @Before
    public void setUp() throws Exception {
    	
    	reportingEngine = new ReportingEngine();
    	
        // Weekends for SAR-Friday and AED-Saturday
        instruction1 =
                new Instruction(1l, "foo", "B", 0.50d, "SAR", getInputDate(2016, Calendar.JANUARY, 1), getInputDate(2016,
                        Calendar.JANUARY, 1), 200l, 100.25d);
        instruction2 =
                new Instruction(2l, "bar", "S", 0.22d, "AED", getInputDate(2016, Calendar.JANUARY, 5), getInputDate(2016,
                        Calendar.JANUARY, 2), 450l, 150.5d);

        // Weekends for USD-Saturday and GBP-Sunday
        instruction3 =
                new Instruction(3l, "tar", "S", 0.50d, "USD", getInputDate(2016, Calendar.JANUARY, 1), getInputDate(2016,
                        Calendar.JANUARY, 2), 200l, 100.25d);
        instruction4 =
                new Instruction(4l, "gar", "B", 0.22d, "GBP", getInputDate(2016, Calendar.JANUARY, 5), getInputDate(2016,
                        Calendar.JANUARY, 3), 450l, 150.5d);

        // Weekdays for GBP-Tuesday and SAR-Tuesday
        instruction5 =
                new Instruction(5l, "gar", "B", 0.50d, "GBP", getInputDate(2016, Calendar.JANUARY, 1), getInputDate(2016,
                        Calendar.JANUARY, 5), 200l, 100.25d);
        instruction6 =
                new Instruction(6l, "foo", "S", 0.22d, "SAR", getInputDate(2016, Calendar.JANUARY, 5), getInputDate(2016,
                        Calendar.JANUARY, 5), 450l, 150.5d);

        // Extra for generating report
        instruction7 =
                new Instruction(7l, "foo", "B", 0.50d, "SAR", getInputDate(2016, Calendar.JANUARY, 1), getInputDate(2016,
                        Calendar.JANUARY, 4), 200l, 100.25d);
        instruction8 =
                new Instruction(8l, "bar", "S", 0.22d, "AED", getInputDate(2016, Calendar.JANUARY, 5), getInputDate(2016,
                        Calendar.JANUARY, 4), 450l, 150.5d);

        instruction9 =
                new Instruction(9l, "tar", "S", 0.50d, "USD", getInputDate(2016, Calendar.JANUARY, 1), getInputDate(2016,
                        Calendar.JANUARY, 4), 200l, 100.25d);
        instruction10 =
                new Instruction(10l, "gar", "B", 0.22d, "GBP", getInputDate(2016, Calendar.JANUARY, 5), getInputDate(2016,
                        Calendar.JANUARY, 4), 450l, 150.5d);

        instruction11 =
                new Instruction(11l, "gar", "B", 0.50d, "GBP", getInputDate(2016, Calendar.JANUARY, 1), getInputDate(2016,
                        Calendar.JANUARY, 5), 100l, 100.25d);
        instruction12 =
                new Instruction(12l, "foo", "S", 0.22d, "SAR", getInputDate(2016, Calendar.JANUARY, 5), getInputDate(2016,
                        Calendar.JANUARY, 5), 400l, 150.5d);
    }

    @After
    public void tearDown() throws Exception {
    	reportingEngine = null;

    }

    /**
     * Received Settlement Date 1st January 2016, Weekend for SAR, changed to 3rd January 2016
     * @throws InvalidTradeInputException
     */
    @Test
    public void testExecuteTradeForSARSettelementDateNotTrueForWeekend() throws InvalidTradeInputException {
        Instruction instruction = reportingEngine.executeTrade(instruction1);
        Date validatedSettlementDate = instruction.getSettlementDate().getTime();
        Assert.assertNotEquals(validatedSettlementDate, getInputDate(2016, Calendar.JANUARY, 1).getTime());
    }

    /**
     *  Received Settlement Date 1st January 2016, Weekend for SAR, changed to 3rd January 2016
     * @throws InvalidTradeInputException
     */
    @Test
    public void testExecuteTradeForSARNewSettelementDateForWeekend() throws InvalidTradeInputException {
        Instruction instruction = reportingEngine.executeTrade(instruction1);
        Date validatedSettlementDate = instruction.getSettlementDate().getTime();
        Assert.assertEquals(validatedSettlementDate, getInputDate(2016, Calendar.JANUARY, 3).getTime());
    }

    /**
     * Received Settlement Date 3rd January 2016, Weekend for GBP, changed to 4th January 2016
     * @throws InvalidTradeInputException
     */
    @Test
    public void testExecuteTradeForGBPSettelementDateNotSameForWeekend() throws InvalidTradeInputException {
        Instruction instruction = reportingEngine.executeTrade(instruction4);
        Date validatedSettlementDate = instruction.getSettlementDate().getTime();
        Assert.assertNotEquals(validatedSettlementDate, getInputDate(2016, Calendar.JANUARY, 3).getTime());
    }

    /**
     * Received Settlement Date 3rd January 2016, Weekend for GBP, changed to 4th January 2016
     * @throws InvalidTradeInputException
     */
    @Test
    public void testExecuteTradeForGBPNewSettelementDateForWeekend() throws InvalidTradeInputException {
        Instruction instruction = reportingEngine.executeTrade(instruction4);
        Date validatedSettlementDate = instruction.getSettlementDate().getTime();
        Assert.assertEquals(validatedSettlementDate, getInputDate(2016, Calendar.JANUARY, 4).getTime());

    }

    /**
     * Received Settlement Date 5th January 2016, Weekdays for SAR, , No change made on Settlement Date
     * @throws InvalidTradeInputException
     */
    @Test
    public void testExecuteTradeForSARNewSettelementDateForWeekdays() throws InvalidTradeInputException {
        Instruction instruction = reportingEngine.executeTrade(instruction6);
        Date validatedSettlementDate = instruction.getSettlementDate().getTime();
        Assert.assertEquals(validatedSettlementDate, getInputDate(2016, Calendar.JANUARY, 5).getTime());

    }

    /**
     * Received Settlement Date 5th January 2016, Weekdays forr GBP, No change made on Settlement Date
     * @throws InvalidTradeInputException
     */
    @Test
    public void testExecuteTradeForGBPNewSettelementDateForWeekdays() throws InvalidTradeInputException {
        Instruction instruction = reportingEngine.executeTrade(instruction5);
        Date validatedSettlementDate = instruction.getSettlementDate().getTime();
        Assert.assertEquals(validatedSettlementDate, getInputDate(2016, Calendar.JANUARY, 5).getTime());
    }

    /**
     * This will separate Buying and Selling instructions, Rank them based on the highest amount.
     * @throws InvalidTradeInputException
     */
    @Test
    public void testExecuteTradeForReport() throws InvalidTradeInputException {

        List<Instruction> instructionList = new ArrayList<Instruction>();

        instructionList.add(reportingEngine.executeTrade(instruction1));
        instructionList.add(reportingEngine.executeTrade(instruction2));
        instructionList.add(reportingEngine.executeTrade(instruction3));
        instructionList.add(reportingEngine.executeTrade(instruction4));
        instructionList.add(reportingEngine.executeTrade(instruction5));
        instructionList.add(reportingEngine.executeTrade(instruction6));
        instructionList.add(reportingEngine.executeTrade(instruction7));
        instructionList.add(reportingEngine.executeTrade(instruction8));
        instructionList.add(reportingEngine.executeTrade(instruction9));
        instructionList.add(reportingEngine.executeTrade(instruction10));
        instructionList.add(reportingEngine.executeTrade(instruction11));
        instructionList.add(reportingEngine.executeTrade(instruction12));

        Instructions buyingSellingInstrs = reportingEngine.organisedTradeReport(instructionList);

        LOGGER.info("Buying/Outgoing Report: ");
        int rank = 1;
        for (Instruction instruction : buyingSellingInstrs.getOutgoingBuyingInstructions()) {
            LOGGER.info("Rank: " + rank + ": " + instruction);
            rank++;
        }

        LOGGER.info("Selling/Incoming Report: ");
        rank = 1;
        for (Instruction instruction : buyingSellingInstrs.getIncomingSellingInstructions()) {
            LOGGER.info("Rank: " + rank + ": " + instruction);
            rank++;
        }
    }

    /**
     * This will test the Outgoing/Buying Report sorted in DESC order
     * @throws InvalidTradeInputException
     */
    @Test
    public void testExecuteTradeForOutgoingBuyingReportRanking() throws InvalidTradeInputException {

        List<Instruction> instructionList = new ArrayList<Instruction>();

        instructionList.add(reportingEngine.executeTrade(instruction1));
        instructionList.add(reportingEngine.executeTrade(instruction2));
        instructionList.add(reportingEngine.executeTrade(instruction3));
        instructionList.add(reportingEngine.executeTrade(instruction4));
        instructionList.add(reportingEngine.executeTrade(instruction5));
        instructionList.add(reportingEngine.executeTrade(instruction6));
        instructionList.add(reportingEngine.executeTrade(instruction7));
        instructionList.add(reportingEngine.executeTrade(instruction8));
        instructionList.add(reportingEngine.executeTrade(instruction9));
        instructionList.add(reportingEngine.executeTrade(instruction10));
        instructionList.add(reportingEngine.executeTrade(instruction11));
        instructionList.add(reportingEngine.executeTrade(instruction12));

        Instructions allInstructions = reportingEngine.organisedTradeReport(instructionList);

        List<Instruction> outgoingBuyingList = allInstructions.getOutgoingBuyingInstructions();

        Instruction[] arr = outgoingBuyingList.toArray(new Instruction[outgoingBuyingList.size()]);

        for (int i = 0; i < outgoingBuyingList.size() - 1; i++) {
            int j = i + 1;
            Assert.assertTrue(arr[i].getTradeAmount().compareTo(arr[j].getTradeAmount()) >= 0);
        }
    }

    /**
     * This will test the Incoming/Selling Report sorted in DESC order
     * @throws InvalidTradeInputException
     */
    @Test
    public void testExecuteTradeForIncomingSellingReportRanking() throws InvalidTradeInputException {

        List<Instruction> instructionList = new ArrayList<Instruction>();

        instructionList.add(reportingEngine.executeTrade(instruction1));
        instructionList.add(reportingEngine.executeTrade(instruction2));
        instructionList.add(reportingEngine.executeTrade(instruction3));
        instructionList.add(reportingEngine.executeTrade(instruction4));
        instructionList.add(reportingEngine.executeTrade(instruction5));
        instructionList.add(reportingEngine.executeTrade(instruction6));
        instructionList.add(reportingEngine.executeTrade(instruction7));
        instructionList.add(reportingEngine.executeTrade(instruction8));
        instructionList.add(reportingEngine.executeTrade(instruction9));
        instructionList.add(reportingEngine.executeTrade(instruction10));
        instructionList.add(reportingEngine.executeTrade(instruction11));
        instructionList.add(reportingEngine.executeTrade(instruction12));

        Instructions allInstructions = reportingEngine.organisedTradeReport(instructionList);

        List<Instruction> IncomingSellingList = allInstructions.getIncomingSellingInstructions();

        Instruction[] arr = IncomingSellingList.toArray(new Instruction[IncomingSellingList.size()]);

        for (int i = 0; i < IncomingSellingList.size() - 1; i++) {
            int j = i + 1;
            Assert.assertTrue(arr[i].getTradeAmount().compareTo(arr[j].getTradeAmount()) >= 0);
        }
    }

    /**
     * This will separate Buying and Selling instructions, Rank them based on the highest amount, Only for the given report date.
     * @throws InvalidTradeInputException
     */
    @Test
    public void testExecuteTradeForReportEveryDay() throws InvalidTradeInputException {

        Calendar reportingDate = getInputDate(2016, Calendar.JANUARY, 4);

        List<Instruction> instructionList = new ArrayList<Instruction>();

        instructionList.add(reportingEngine.executeTrade(instruction1));
        instructionList.add(reportingEngine.executeTrade(instruction2));
        instructionList.add(reportingEngine.executeTrade(instruction3));
        instructionList.add(reportingEngine.executeTrade(instruction4));
        instructionList.add(reportingEngine.executeTrade(instruction5));
        instructionList.add(reportingEngine.executeTrade(instruction6));
        instructionList.add(reportingEngine.executeTrade(instruction7));
        instructionList.add(reportingEngine.executeTrade(instruction8));
        instructionList.add(reportingEngine.executeTrade(instruction9));
        instructionList.add(reportingEngine.executeTrade(instruction10));
        instructionList.add(reportingEngine.executeTrade(instruction11));
        instructionList.add(reportingEngine.executeTrade(instruction12));

        Instructions buyingSellingInstrs = reportingEngine.organisedTradeReport(instructionList);

        LOGGER.info("Report on : " + reportingDate.getTime());

        LOGGER.info("Buying/Outgoing Report: ");
        int rank = 1;
        for (Instruction instruction : buyingSellingInstrs.getOutgoingBuyingInstructions()) {
            if (instruction.getSettlementDate().compareTo(reportingDate) == 0) {
                LOGGER.info("Rank: " + rank + ": " + instruction);
                rank++;
            }
        }

        LOGGER.info("Selling/Incoming Report: ");
        rank = 1;
        for (Instruction instruction : buyingSellingInstrs.getIncomingSellingInstructions()) {
            if (instruction.getSettlementDate().compareTo(reportingDate) == 0) {
                LOGGER.info("Rank: " + rank + ": " + instruction);
                rank++;
            }
        }
    }

    /**
     * Instruction is Null
     * @throws InvalidTradeInputException
     */
    @Test(expected = InvalidTradeInputException.class)
    public void testExecuteTradeWithExceptionForInstruction() throws InvalidTradeInputException {
        reportingEngine.executeTrade(null);
    }

    /**
     * Settlement Date is Null
     * @throws InvalidTradeInputException
     */
    @Test(expected = InvalidTradeInputException.class)
    public void testExecuteTradeWithExceptionForSettlementDate() throws InvalidTradeInputException {

        Instruction instructionExp =
                new Instruction(1L, "foo", "B", 0.50, "SAR", getInputDate(2016, Calendar.JANUARY, 1), null, 200l, 100.25d);

        reportingEngine.executeTrade(instructionExp);
    }

    /**
     * Currency is Null
     * @throws InvalidTradeInputException
     */
    @Test(expected = InvalidTradeInputException.class)
    public void testExecuteTradeWithExceptionForCurrency() throws InvalidTradeInputException {

        Instruction instructionExp =
                new Instruction(1L, "foo", "B", 0.50, null, getInputDate(2016, Calendar.JANUARY, 1), getInputDate(2016,
                        Calendar.JANUARY, 1), 200l, 100.25d);

        reportingEngine.executeTrade(instructionExp);
    }

    /**
     * AgreedFX is Null
     * @throws InvalidTradeInputException
     */
    @Test(expected = InvalidTradeInputException.class)
    public void testExecuteTradeWithExceptionForAgreedFX() throws InvalidTradeInputException {

        Instruction instructionExp =
                new Instruction(1L, "foo", "B", null, "SAR", getInputDate(2016, Calendar.JANUARY, 1), getInputDate(2016,
                        Calendar.JANUARY, 1), 200l, 100.25d);

        reportingEngine.executeTrade(instructionExp);
    }

    /**
     * Units is Null
     * @throws InvalidTradeInputException
     */
    @Test(expected = InvalidTradeInputException.class)
    public void testExecuteTradeWithExceptionForUnits() throws InvalidTradeInputException {

        Instruction instructionExp =
                new Instruction(1L, "foo", "B", 0.50, "SAR", getInputDate(2016, Calendar.JANUARY, 1), getInputDate(2016,
                        Calendar.JANUARY, 1), null, 100.25d);

        reportingEngine.executeTrade(instructionExp);
    }

    /**
     * Price Per Unit is Null
     * @throws InvalidTradeInputException
     */
    @Test(expected = InvalidTradeInputException.class)
    public void testExecuteTradeWithExceptionForPricePerUnit() throws InvalidTradeInputException {

        Instruction instructionExp =
                new Instruction(1L, "foo", "B", 0.50, "SAR", getInputDate(2016, Calendar.JANUARY, 1), getInputDate(2016,
                        Calendar.JANUARY, 1), 200l, null);

        reportingEngine.executeTrade(instructionExp);
    }

    /**
     * USD amount of a trade = Price per Unit * Units * AgreedFX
     * @throws InvalidTradeInputException
     */
    @Test
    public void testCalculateTradeAmount() throws InvalidTradeInputException {
        reportingEngine.executeTrade(instruction1);

        double actual = instruction1.getTradeAmount();
        double expected = instruction1.getPricePerUnit() * instruction1.getUnits() * instruction1.getAgreedFX();
        Assert.assertEquals(expected, actual, 0);
    }
   
    
    /**
     * HELPING METHOD
     * @param year
     * @param month
     * @param day
     * @return Calendar Date
     */
    private Calendar getInputDate(int year, int month, int day) {
        Calendar inputDate = Calendar.getInstance();
        inputDate.set(year, month, day);
        return inputDate;
    }
}
