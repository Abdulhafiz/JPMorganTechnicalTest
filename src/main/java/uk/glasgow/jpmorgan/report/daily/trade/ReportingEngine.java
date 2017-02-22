package uk.glasgow.jpmorgan.report.daily.trade;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import uk.glasgow.jpmorgan.report.daily.error.InvalidTradeInputException;
import uk.glasgow.jpmorgan.report.daily.util.CurrencyType;
import uk.glasgow.jpmorgan.report.daily.util.ReportingConstant;

/**
 * J.P Morgan Java Technical Test
 * @author Abdul Hafiz
 *
 */
public class ReportingEngine {

    public static final Logger LOGGER = Logger.getLogger(ReportingEngine.class);
    
    /**
     * Executes the instruction from international market by validating the settlement date and calculating the trade amount
     * @param instruction - input
     * @return Instruction
     * @throws InvalidTradeInputException - in case of invalid/null input data
     */
    public Instruction executeTrade(Instruction recievedInstruction) throws InvalidTradeInputException {
        LOGGER.debug("Execute Trade Instruction Received from the client: START");
        if (recievedInstruction == null) {
            LOGGER.error("Instruction is null or emprty");
            throw new InvalidTradeInputException("Instruction is null or emprty");
        }

        Instruction executedInstruction = validateSettlementDate(recievedInstruction);
        executedInstruction = calculateTradeAmount(executedInstruction);
        LOGGER.debug("Execute Trade Instruction Received from the client : END");
        return executedInstruction;
    }

    /**
     * This method will check the settlement date is on working day. If not then it will change to the next working day
     * @param instruction
     * @return validated instruction
     * @throws InvalidTradeInputException
     */
    private Instruction validateSettlementDate(Instruction instruction) throws InvalidTradeInputException {
        LOGGER.debug("validating settlement date : START");
        Calendar settlementDate = instruction.getSettlementDate();

        if (settlementDate == null || instruction.getCurrency() == null) {
            LOGGER.error("Instruction currency or settlement date is invalid");
            throw new InvalidTradeInputException("Instruction currency or settlement date is invalid");
        }

        if (instruction.getCurrency().equalsIgnoreCase(CurrencyType.AED.name())
            || instruction.getCurrency().equalsIgnoreCase(CurrencyType.SAR.name())) {
            int dayOfWeek = settlementDate.get(Calendar.DAY_OF_WEEK);
            if (Calendar.FRIDAY == dayOfWeek) {
                updateDateToWorkingDays(settlementDate, ReportingConstant.DAY_2);
            } else if (Calendar.SATURDAY == dayOfWeek) {
                updateDateToWorkingDays(settlementDate, ReportingConstant.DAY_1);
            }

        } else {
            int dayOfWeek = settlementDate.get(Calendar.DAY_OF_WEEK);
            if (Calendar.SATURDAY == dayOfWeek) {
                updateDateToWorkingDays(settlementDate, ReportingConstant.DAY_2);
            } else if (Calendar.SUNDAY == dayOfWeek) {
                updateDateToWorkingDays(settlementDate, ReportingConstant.DAY_1);
            }
        }
        LOGGER.debug("validated settlement date : " + settlementDate);
        LOGGER.debug("validating settlement date : END");
        return instruction;
    }

    private Calendar updateDateToWorkingDays(Calendar settlementDate, Integer day) {
        settlementDate.add(Calendar.DATE, day);
        return settlementDate;
    }
    
    /**
     * 
     * @param instruction - without trade amount
     * @return Instruction  - with calculated trade amount
     * @throws InvalidTradeInputException
     */
    private Instruction calculateTradeAmount(Instruction instruction) throws InvalidTradeInputException {
        LOGGER.debug("Calculate Trade Amount : START");
        if (instruction.getPricePerUnit() == null || instruction.getUnits() == null
            || instruction.getAgreedFX() == null) {
            LOGGER.error("Instruction Price Per Unit or unit or agreedFx is invalid");
            throw new InvalidTradeInputException("Instruction Price Per Unit or unit or agreedFx is invalid");
        }
        double tradeAmount = instruction.getPricePerUnit() * instruction.getUnits() * instruction.getAgreedFX();
        instruction.setTradeAmount(tradeAmount);
        LOGGER.debug("Calculate Trade Amount : END");

        return instruction;
    }

    public Instructions organisedTradeReport(List<Instruction> instructionList) {
        LOGGER.debug("Organised Trade Report : START");
        Instructions allExecutedInstructions = new Instructions();

        for (Instruction instruction : instructionList) {
            if (ReportingConstant.BUY.equalsIgnoreCase(instruction.getInstructionType())) {
                allExecutedInstructions.getOutgoingBuyingInstructions().add(instruction);
            } else {
                allExecutedInstructions.getIncomingSellingInstructions().add(instruction);
            }
        }
        Collections.sort(allExecutedInstructions.getOutgoingBuyingInstructions());
        Collections.sort(allExecutedInstructions.getIncomingSellingInstructions());
        LOGGER.debug("Organised Trade Report : END");
        return allExecutedInstructions;
    }
}
